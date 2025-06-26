grammar GyhLang;

@header{
	import java.util.ArrayList;
}

@members{
	//---------------------------------------------------
	// Variáveis de Membro
	//---------------------------------------------------
	
	private String _expVar;
	private String _expContent;
	private String _readVar;


	// Objetos e Listas para Geração de Código
	private SymbolTable SymbolTable = new SymbolTable();
	private Symbol.Tipo currentExprType;
	private ArrayList<Command> listCmd = new ArrayList<Command>();
	private GyhProgram program = new GyhProgram();
	
	//---------------------------------------------------
	// Funções Auxiliares
	//---------------------------------------------------
	
	public void generateCommand(){
		program.generateTarget();
	}
	
	//Função para verificar se a variável utilizada foi declarada.

	public void verificarVar(String nameVar){
		if(!SymbolTable.exists(nameVar)){
			System.err.println("Erro Semântico Variavel não declarada "+nameVar);
		}
	}

	//Funcao para verificar a incompatibilidade de tipos entre INT e REAL (REAL -> INT).

	 public void checarAtribuicao(String varName, Symbol.Tipo tipoExpressao) {
        if (!SymbolTable.exists(varName)) {
            System.err.println("Erro Semântico: Variável '" + varName + "' não foi declarada.");
            return;
        }
        Symbol.Tipo tipoVariavel = SymbolTable.get(varName).getType();
        if (tipoVariavel == Symbol.Tipo.INT && tipoExpressao == Symbol.Tipo.REAL) {
            System.err.println("Erro Semântico: Incompatibilidade de tipo. Impossível atribuir um valor REAL a uma variável INT '" + varName + "'.");
        }
    }

    // Calcula o tipo resultante de uma expressão aritmética.

     private Symbol.Tipo checarTipoExpressao(Symbol.Tipo tipo1, Symbol.Tipo tipo2) {
        if (tipo1 == Symbol.Tipo.INVALIDO || tipo2 == Symbol.Tipo.INVALIDO) return Symbol.Tipo.INVALIDO;
        if (tipo1 == Symbol.Tipo.REAL || tipo2 == Symbol.Tipo.REAL) return Symbol.Tipo.REAL;
        return Symbol.Tipo.INT;
    }
    
    // Verifica se um número REAL literal perde precisão ou causa overflow.

    public void checarPrecisaoReal(String numeroLiteral) {
        try {
            java.math.BigDecimal original = new java.math.BigDecimal(numeroLiteral);
            double valorConvertido = original.doubleValue();
            
            if (Double.isInfinite(valorConvertido)) {
                System.err.println("ERRO LÉXICO: Overflow de magnitude no número '" + numeroLiteral + "'. O valor é grande demais.");
                return;
            }

            java.math.BigDecimal revertido = new java.math.BigDecimal(valorConvertido);

            if (original.compareTo(revertido) != 0) {
                System.err.println("ERRO LÉXICO: Overflow de precisão no número '" + numeroLiteral + "'. O número tem dígitos demais para serem representados com exatidão.");
            }
        } catch (NumberFormatException e) {
            System.err.println("ERRO LÉXICO: Formato inválido para o número '" + numeroLiteral + "'.");
        }
    }
}

// ------------------------------------------------------------------
// --- REGRAS DO PARSER ---
// ------------------------------------------------------------------

// Estrutura Principal do Programa
programa: 
	DELIM PcDec listaDeclaracoes DELIM PCProg listaComandos 
	{
		program.setVarTable(SymbolTable);
		System.out.println("Programa está na VarTable ");
		program.setCommand(listCmd);
		generateCommand();
	};

// Bloco de Declarações
listaDeclaracoes: 
	declaracao*;

declaracao:
    Var DELIM (i=PcInt | r=PcReal)
    {
        String varName = $Var.text;
        if (SymbolTable.exists(varName)) {
            System.err.println("Erro Semântico: Variável '" + varName + "' já foi declarada.");
        } else {
            Symbol.Tipo varType = ($i != null) ? Symbol.Tipo.INT : Symbol.Tipo.REAL;
            Symbol symbol = new Symbol(varName, varType);
            SymbolTable.add(symbol);
        }
    };

// Bloco de Comandos
listaComandos:comando*;

comando:
      ( comandoAtribuicao
      | comandoEntrada
      | comandoSaida
      | comandoCondicao
      | comandoRepeticao
      | subAlgoritmo
      );

// Definição dos Comandos Individuais
comandoAtribuicao:
    Var Atrib exp=expressaoAritmetica
    {
        checarAtribuicao($Var.text, currentExprType);  
        String varName = $Var.text;
        String expContent = $exp.text;
        CommandAtribuicao cmd = new CommandAtribuicao(varName, expContent);
        

        listCmd.add(cmd);
    };
comandoEntrada:
    PcLer Var
    {

        String varName = $Var.text;
        

        verificarVar(varName);

        CommandLeitura cmd = new CommandLeitura(varName);
        

        listCmd.add(cmd);
    };

comandoSaida:
    PcImprimir (
        Var {
            String varName = $Var.text;
            verificarVar(varName);
            Symbol.Tipo varType = SymbolTable.get(varName).getType();
            CommandEscrita cmd = new CommandEscrita(varName, varType);
            listCmd.add(cmd);
        } 
        | Cadeia {
            CommandEscrita cmd = new CommandEscrita($Cadeia.text, Symbol.Tipo.CADEIA);
            listCmd.add(cmd);
        }
    );

comandoCondicao:
    PcSe exp=expressaoRelacional PcEntao
    {
        ArrayList<Command> comandosTrue = new ArrayList<>();
        ArrayList<Command> comandosFalse = new ArrayList<>();
        ArrayList<Command> listaPrincipalAntiga = listCmd;
        listCmd = comandosTrue;
    }
    comando
    (
        PcSenao
        {
            listCmd = comandosFalse;
        }
        comando
    )?
    {
        listCmd = listaPrincipalAntiga;
        String condicaoTexto = _input.getText($exp.start, $exp.stop);
        condicaoTexto = condicaoTexto.replace("E", " && ").replace("OU", " || ");
        CommandCondicao cmd = new CommandCondicao(condicaoTexto, comandosTrue, comandosFalse);
        listCmd.add(cmd);
    };

comandoRepeticao:
    PcEnqto exp=expressaoRelacional
    {
        ArrayList<Command> comandosLoop = new ArrayList<>();
        ArrayList<Command> listaPrincipalAntiga = listCmd;
        listCmd = comandosLoop;
    }
    comando
    {
        listCmd = listaPrincipalAntiga;
        String condicaoTexto = _input.getText($exp.start, $exp.stop);
        condicaoTexto = condicaoTexto.replace("E", "&&").replace("OU", "||");
        CommandRepeticao cmd = new CommandRepeticao(condicaoTexto, comandosLoop);
        listCmd.add(cmd);
    };

subAlgoritmo:
	PcIni listaComandos PcFim;

// Definição das Expressões
expressaoRelacional: 
	termoRelacional ( OpBol  termoRelacional )*;
 
termoRelacional:
	expressaoAritmetica OpRel expressaoAritmetica
	| AbrePar expressaoRelacional FechaPar 
	;

expressaoAritmetica:
    e1=termoAritmetico
    (
        op=('+'|'-')
        {
            Symbol.Tipo tipoEsquerda = currentExprType;
        }
        e2=termoAritmetico
        {
            currentExprType = checarTipoExpressao(tipoEsquerda, currentExprType);
        }
    )*;

termoAritmetico:
    e1=fatorAritmetico
    (
        op=('*'|'/')
        {
            Symbol.Tipo tipoEsquerda = currentExprType;
        }
        e2=fatorAritmetico
        {
            currentExprType = checarTipoExpressao(tipoEsquerda, currentExprType);
        }
    )*;
	
fatorAritmetico:
      NumInt
        { currentExprType = Symbol.Tipo.INT; }
    | NumReal
        { 
            checarPrecisaoReal($NumReal.text);
            currentExprType = Symbol.Tipo.REAL; 
        }
    | Var
        {
            String varName = $Var.text;
            verificarVar(varName);
            if (SymbolTable.exists(varName)) {
                currentExprType = SymbolTable.get(varName).getType();
            } else {
                currentExprType = Symbol.Tipo.INVALIDO;
            }
        }
    | AbrePar expressaoAritmetica FechaPar
    ;

// ------------------------------------------------------------------
// --- REGRAS DO LEXER (TOKENS) ---
// ------------------------------------------------------------------

// Palavras-Chave
PcInt      : 'INT';
PcLer      : 'LER';
PcReal     : 'REAL';
PcImprimir : 'IMPRIMIR';
PcSe       : 'SE';
PcSenao    : 'SENAO';
PcEntao    : 'ENTAO';
PcEnqto    : 'ENQTO';
PcIni      : 'INI';
PcFim      : 'FIM';
PCProg     : 'PROG';
PcDec      : 'DEC';

// Operadores
OpAritMult : '*';
OpAritDiv  : '/';
OpAritSoma : '+';
OpAritSub  : '-';
Atrib      : ':=';
OpRel      : '<=' | '<' | '>=' | '>' | '==' | '!=';
OpBol      : 'E' | 'OU';

// Delimitadores e Símbolos
DELIM      : ':';
AbrePar    : '(';
FechaPar   : ')';

// Literais e Identificadores
Var        : [a-z]([a-zA-Z0-9])*;
Cadeia     : '"' .*? '"';
NumInt     : [0-9]+;
NumReal    : [0-9]+ '.' [0-9]+;

// Ignorados
WS         : [ \t\r\n\u00A0]+ -> skip;
Coment     : '#' ~[\t\r\n]* -> skip;