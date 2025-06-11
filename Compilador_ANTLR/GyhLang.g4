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
	
	public void verificarVar(String nameVar){
	if(!SymbolTable.exists(nameVar)){
		System.out.println("Erro sintatico Variavel não declarada "+nameVar);
		}
	}
	

}



programa: DELIM PcDec  listaDeclaracoes DELIM PCProg listaComandos;

listaDeclaracoes : declaracao*;
declaracao       : Var 
 DELIM (PcInt {_varType=1;}| PcReal{_varType=0;}){_varName=_input.LT(-3).getText();
 						//_varType=_input.LT(-1).getText();
 						_varValue=null;
 						_varSymbol=new Symbol(_varName,_varType,_varValue);
 						if(!SymbolTable.exists(_varName)){
	 						SymbolTable.add(_varSymbol);
	 						System.out.println("Adicionei algo:  "+_varSymbol);
 						
 						}else{
 							System.out.println("Erro Semantico,tentando adicionar novamente \n ");
 						}
 						} ;

listaComandos : comando*;
comando:
      ( comandoAtribuicao
      | comandoEntrada
      | comandoSaida
      | comandoCondicao
      | comandoRepeticao
      | subAlgoritmo
      ) ;

comandoAtribuicao : Var{verificarVar(_input.LT(-1).getText());}

 Atrib expressaoAritmetica;
comandoEntrada    : PcLer Var{verificarVar(_input.LT(-1).getText());};
comandoSaida      : PcImprimir (Var{verificarVar(_input.LT(-1).getText());} | Cadeia);
comandoCondicao   : PcSe expressaoRelacional PcEntao comando (PcSenao comando)?;
comandoRepeticao  : PcEnqto expressaoRelacional comando;
subAlgoritmo      : PcIni listaComandos PcFim;

expressaoAritmetica: termoAritmetico ( (OpAritSoma | OpAritSub) termoAritmetico )*;
termoAritmetico: fatorAritmetico ( (OpAritMult | OpAritDiv) fatorAritmetico )*;
fatorAritmetico:
      NumInt
    | NumReal
    | Var{verificarVar(_input.LT(-1).getText());}
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

WS         : [ \t\r\n]+ -> skip;
Coment     : '#' ~[\r\n]* -> skip;