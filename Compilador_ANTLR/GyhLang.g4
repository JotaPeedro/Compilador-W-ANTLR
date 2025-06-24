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

}



programa: DELIM PcDec  listaDeclaracoes DELIM PCProg listaComandos;

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

listaComandos : comando*;
comando:
      ( comandoAtribuicao
      | comandoEntrada
      | comandoSaida
      | comandoCondicao
      | comandoRepeticao
      | subAlgoritmo
      ) ;

comandoAtribuicao : Var Atrib expressaoAritmetica{checarAtribuicao($Var.text, currentExprType);};
comandoEntrada    : PcLer Var{verificarVar(_input.LT(-1).getText());};
comandoSaida      : PcImprimir (Var{verificarVar(_input.LT(-1).getText());} | Cadeia);
comandoCondicao   : PcSe expressaoRelacional PcEntao comando (PcSenao comando)?;
comandoRepeticao  : PcEnqto expressaoRelacional comando;
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
// VERSÃO CORRETA
fatorAritmetico:
    NumInt
        { 
            //  Define o tipo da expressão atual como INT
            currentExprType = Symbol.Tipo.INT; 
        }
    | NumReal
        { 
            //  Define o tipo da expressão atual como REAL
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

expressaoRelacional: termoRelacional ( OpBol termoRelacional )*;
termoRelacional:
      expressaoAritmetica OpRel expressaoAritmetica
    | AbrePar expressaoRelacional FechaPar
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
