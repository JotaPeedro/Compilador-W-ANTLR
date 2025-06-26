grammar GyhLang;

@header{
	import java.util.ArrayList;
}

@members{
	private String _varName;
	private int _varType;
	private String _varValue;
	private Symbol _varSymbol;
	private SymbolTable SymbolTable=new SymbolTable();
	private Symbol.Tipo currentExprType;
	
	//--
	
	
	private String _expVar;
	private String _expContent;
	private String _writeVar;
	private String _readVar;
	private String cond;
	private ArrayList<Command>condTrue = new ArrayList<Command>();
	private ArrayList<Command>condFalse = new ArrayList<Command>();
	
	
	private ArrayList<Command>listCmd= new ArrayList<Command>();
	private ArrayList<Command>listAux= new ArrayList<Command>();
	private GyhProgram program =new GyhProgram();
	
	//--
	
	public void generateCommand(){
		program.generateTarget();
	};
	
	
	//Função para verificar se a variavel utilizada foi chamada,utilizada onde ocorrem o uso de variaveis
	public void verificarVar(String nameVar){
	if(!SymbolTable.exists(nameVar)){
		System.err.println("Erro Semântico Variavel não declarada "+nameVar);
		}
	}
	//Funcao para verificar a incompatibilidade de tipos entre INT e Float
	//primeiro verifica se a variavel foi declarada depois o tipo de atribuição,
	//se for tentar atribuir um real a um int ele da erro
	
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
     private Symbol.Tipo checarTipoExpressao(Symbol.Tipo tipo1, Symbol.Tipo tipo2) {
        if (tipo1 == Symbol.Tipo.INVALIDO || tipo2 == Symbol.Tipo.INVALIDO) return Symbol.Tipo.INVALIDO;
        if (tipo1 == Symbol.Tipo.REAL || tipo2 == Symbol.Tipo.REAL) return Symbol.Tipo.REAL;
        return Symbol.Tipo.INT;
    }
    

    public void checarPrecisaoReal(String numeroLiteral) {
        try {
            // Cria um número de alta precisão a partir do texto
            java.math.BigDecimal original = new java.math.BigDecimal(numeroLiteral);

            // Converte o número de alta precisão para double (o tipo da nossa linguagem)
            double valorConvertido = original.doubleValue();
            
            // Se o valor convertido for Infinito, é um overflow de magnitude
            if (Double.isInfinite(valorConvertido)) {
                System.err.println("ERRO LÉXICO: Overflow de magnitude no número '" + numeroLiteral + "'. O valor é grande demais.");
                return;
            }

            // Converte o double de volta para alta precisão para ver se algo foi perdido
            java.math.BigDecimal revertido = new java.math.BigDecimal(valorConvertido);

            // Compara o número original com o revertido. Se forem diferentes, houve perda de precisão.
            if (original.compareTo(revertido) != 0) {
                System.err.println("ERRO LÉXICO: Overflow de precisão no número '" + numeroLiteral + "'. O número tem dígitos demais para serem representados com exatidão.");
            }
        } catch (NumberFormatException e) {
            System.err.println("ERRO LÉXICO: Formato inválido para o número '" + numeroLiteral + "'.");
        }
    }
    

}



programa: DELIM PcDec  listaDeclaracoes DELIM PCProg listaComandos 
			{program.setVarTable(SymbolTable);
				System.out.println("Programa está na VarTable ");
			
			
			
			program.setCommand(listCmd);
			generateCommand();
			};




listaDeclaracoes : declaracao*;
declaracao:
    Var DELIM (i=PcInt | r=PcReal) // Rótulos individuais: 'i' para PcInt, 'r' para PcReal
    {
        String varName = $Var.text;
        if (SymbolTable.exists(varName)) {
            System.err.println("Erro Semântico: Variável '" + varName + "' já foi declarada.");
        } else {
            Symbol.Tipo varType;

            if ($i != null) {
                varType = Symbol.Tipo.INT;
            } else {
                varType = Symbol.Tipo.REAL;
            }
            
            Symbol symbol = new Symbol(varName, varType);
            SymbolTable.add(symbol);
        }
    };

listaComandos : (comando
						{
						listCmd.addAll(listAux);
						listAux.removeAll(listAux);
						
						}

)*;
comando:
      ( comandoAtribuicao
      | comandoEntrada
      | comandoSaida
      | comandoCondicao
      | comandoRepeticao
      | subAlgoritmo
      ) ;

comandoAtribuicao:
    Var Atrib exp=expressaoAritmetica // 1. Adicionamos o rótulo "exp="
    {
        // 2. A checagem semântica continua sendo o primeiro passo
        checarAtribuicao($Var.text, currentExprType);
        
        // 3. Capturamos o nome da variável e o conteúdo da expressão
        _expVar = $Var.text;
        _expContent = $exp.text; // Usamos o rótulo para pegar o texto completo da expressão
        
        // 4. Criamos o objeto de comando com os valores capturados
        CommandAtribuicao cmd = new CommandAtribuicao(_expVar, _expContent);
        
        // 5. Adicionamos o comando à lista para geração de código
        listAux.add(cmd);
    };





comandoEntrada    : PcLer Var
							{verificarVar(_input.LT(-1).getText());
							_readVar=_input.LT(-1).getText();
							CommandLeitura cmd = new CommandLeitura(_readVar);
							listAux.add(cmd);
							};





comandoSaida:
    PcImprimir (
        Var {
            String varName = $Var.text;
            verificarVar(varName);
            
            // Pega o tipo da variável da tabela de símbolos
            Symbol.Tipo varType = SymbolTable.get(varName).getType();
            
            // Cria o comando com o nome da variável e seu tipo
            CommandEscrita cmd = new CommandEscrita(varName, varType);
            listCmd.add(cmd);
        } 
        | Cadeia {
            // Cria o comando com o texto da cadeia e o tipo CADEIA
            CommandEscrita cmd = new CommandEscrita($Cadeia.text, Symbol.Tipo.CADEIA);
            listCmd.add(cmd);
        }
    );





comandoCondicao:
    PcSe exp=expressaoRelacional PcEntao
    {
        // 1. Prepara as listas para os blocos TRUE e FALSE
        ArrayList<Command> comandosTrue = new ArrayList<>();
        ArrayList<Command> comandosFalse = new ArrayList<>();
        
        // Salva a lista principal atual
        ArrayList<Command> listaPrincipalAntiga = listCmd;
        
        // 2. Redireciona a lista principal para a lista do bloco TRUE
        listCmd = comandosTrue;
    }
    comando // O parser analisa o bloco TRUE. Os comandos são adicionados a 'comandosTrue'.
    (
        PcSenao
        {
            // 3. Se encontrar um SENAO, redireciona a lista para o bloco FALSE
            listCmd = comandosFalse;
        }
        comando // O parser analisa o bloco FALSE. Os comandos são adicionados a 'comandosFalse'.
    )? // O bloco '?' indica que a parte do SENAO é opcional
    {
        // 4. AÇÃO FINAL: Restaura a lista principal e monta o comando IF
        listCmd = listaPrincipalAntiga;
        
       String condicaoTexto = _input.getText($exp.start, $exp.stop);


    condicaoTexto = condicaoTexto.replace("E", " && ").replace("OU", " || ");
        
        // Cria o comando IF com as listas que foram preenchidas.
        // Se não houve SENAO, 'comandosFalse' estará simplesmente vazia.
        CommandCondicao cmd = new CommandCondicao(condicaoTexto, comandosTrue, comandosFalse);
        
        // Adiciona o comando IF completo (já com seus filhos) à lista principal
        listCmd.add(cmd);
    };



expressaoRelacional: termoRelacional ( OpBol  termoRelacional )*;
 
 
termoRelacional:
      expressaoAritmetica OpRel expressaoAritmetica
    | AbrePar expressaoRelacional FechaPar 
    ;


comandoRepeticao:
    PcEnqto exp=expressaoRelacional
    {
        // 1. Prepara a lista para o bloco de comandos do loop
        ArrayList<Command> comandosLoop = new ArrayList<>();
        ArrayList<Command> listaPrincipalAntiga = listCmd;
        
        // 2. Redireciona a lista principal para a lista do loop
        listCmd = comandosLoop;
    }
    comando // Analisa o bloco de comandos do loop
    {
        // 3. Restaura a lista principal
        listCmd = listaPrincipalAntiga;
        
        // 4. Captura a condição e cria o comando de repetição
        String condicaoTexto = _input.getText($exp.start, $exp.stop);
        condicaoTexto = condicaoTexto.replace("E", "&&").replace("OU", "||");
        
        // Supondo que você tenha uma classe CommandRepeticao
        CommandRepeticao cmd = new CommandRepeticao(condicaoTexto, comandosLoop);
        listCmd.add(cmd);
    };


subAlgoritmo      : PcIni listaComandos PcFim;

expressaoAritmetica:
    e1=termoAritmetico // Processa o primeiro termo, definindo currentExprType
    (
        // O loop para os operadores + e -
        op=('+'|'-')
        {
         
            // Salva o tipo do lado esquerdo ANTES de processar o lado direito.
            Symbol.Tipo tipoEsquerda = currentExprType;
        }
        e2=termoAritmetico // Processa o termo da direita, sobrescrevendo currentExprType
        {
            // Usa o tipo salvo da esquerda e o tipo atual (da direita)
            // para calcular o tipo final da expressão.
            currentExprType = checarTipoExpressao(tipoEsquerda, currentExprType);
        }
    )*;



termoAritmetico:
    e1=fatorAritmetico // Processa o primeiro fator, definindo currentExprType
    (
        // O loop para os operadores * e /
        op=('*'|'/')
        {
            // salvamos o tipo da esquerda.
            Symbol.Tipo tipoEsquerda = currentExprType;
        }
        e2=fatorAritmetico // Processa o fator da direita, sobrescrevendo currentExprType
        {
            // E usa os tipos corretos para a checagem.
            currentExprType = checarTipoExpressao(tipoEsquerda, currentExprType);
        }
    )*;
	
fatorAritmetico:
    NumInt
        { 
            //  Define o tipo da expressão atual como INT
            currentExprType = Symbol.Tipo.INT; 
        }
    | NumReal
        { 
                       // Antes de definir o tipo, checamos a precisão do literal.
            checarPrecisaoReal($NumReal.text);
            currentExprType = Symbol.Tipo.REAL; 
        }
    | Var
        {
            String varName = $Var.text;
            verificarVar(varName); // Verifica se a variável foi declarada
            if (SymbolTable.exists(varName)) {
                // Define o tipo da expressão como o tipo da variável encontrada
                currentExprType = SymbolTable.get(varName).getType();
            } else {
                // Se a variável não existe, marca o tipo como inválido
                currentExprType = Symbol.Tipo.INVALIDO;
            }
        }
    | AbrePar expressaoAritmetica FechaPar

    ;




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

OpAritMult : '*';
OpAritDiv  : '/';
OpAritSoma : '+';
OpAritSub  : '-';

DELIM      : ':';
Atrib      : ':=';
OpRel      : '<=' | '<' | '>=' | '>' | '==' | '!=';
OpBol      : 'E' | 'OU';
AbrePar    : '(';
FechaPar   : ')';

Var        : [a-z]([a-zA-Z0-9])*;
Cadeia     : '"' .*? '"';
NumInt     : [0-9]+;
NumReal    : [0-9]+ '.' [0-9]+;

WS         : [ \t\r\n\u00A0]+ -> skip;
Coment     : '#' ~[\t\r\n]* -> skip;
