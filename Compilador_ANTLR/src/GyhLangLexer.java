// Generated from GyhLang.g4 by ANTLR 4.7.2

	import java.util.ArrayList;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GyhLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PcInt=1, PcLer=2, PcReal=3, PcImprimir=4, PcSe=5, PcSenao=6, PcEntao=7, 
		PcEnqto=8, PcIni=9, PcFim=10, PCProg=11, PcDec=12, OpAritMult=13, OpAritDiv=14, 
		OpAritSoma=15, OpAritSub=16, Atrib=17, OpRel=18, OpBol=19, DELIM=20, AbrePar=21, 
		FechaPar=22, Var=23, Cadeia=24, NumInt=25, NumReal=26, WS=27, Coment=28;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PcInt", "PcLer", "PcReal", "PcImprimir", "PcSe", "PcSenao", "PcEntao", 
			"PcEnqto", "PcIni", "PcFim", "PCProg", "PcDec", "OpAritMult", "OpAritDiv", 
			"OpAritSoma", "OpAritSub", "Atrib", "OpRel", "OpBol", "DELIM", "AbrePar", 
			"FechaPar", "Var", "Cadeia", "NumInt", "NumReal", "WS", "Coment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'INT'", "'LER'", "'REAL'", "'IMPRIMIR'", "'SE'", "'SENAO'", "'ENTAO'", 
			"'ENQTO'", "'INI'", "'FIM'", "'PROG'", "'DEC'", "'*'", "'/'", "'+'", 
			"'-'", "':='", null, null, "':'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PcInt", "PcLer", "PcReal", "PcImprimir", "PcSe", "PcSenao", "PcEntao", 
			"PcEnqto", "PcIni", "PcFim", "PCProg", "PcDec", "OpAritMult", "OpAritDiv", 
			"OpAritSoma", "OpAritSub", "Atrib", "OpRel", "OpBol", "DELIM", "AbrePar", 
			"FechaPar", "Var", "Cadeia", "NumInt", "NumReal", "WS", "Coment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


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


	public GyhLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GyhLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\36\u00c9\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3"+
		"\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u008d\n\23\3\24\3\24"+
		"\3\24\5\24\u0092\n\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\7\30\u009c"+
		"\n\30\f\30\16\30\u009f\13\30\3\31\3\31\7\31\u00a3\n\31\f\31\16\31\u00a6"+
		"\13\31\3\31\3\31\3\32\6\32\u00ab\n\32\r\32\16\32\u00ac\3\33\6\33\u00b0"+
		"\n\33\r\33\16\33\u00b1\3\33\3\33\6\33\u00b6\n\33\r\33\16\33\u00b7\3\34"+
		"\6\34\u00bb\n\34\r\34\16\34\u00bc\3\34\3\34\3\35\3\35\7\35\u00c3\n\35"+
		"\f\35\16\35\u00c6\13\35\3\35\3\35\3\u00a4\2\36\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36\3\2\7\3\2c|\5\2\62;C\\c|"+
		"\3\2\62;\6\2\13\f\17\17\"\"\u00a2\u00a2\4\2\13\f\17\17\2\u00d5\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\3;\3\2\2\2\5"+
		"?\3\2\2\2\7C\3\2\2\2\tH\3\2\2\2\13Q\3\2\2\2\rT\3\2\2\2\17Z\3\2\2\2\21"+
		"`\3\2\2\2\23f\3\2\2\2\25j\3\2\2\2\27n\3\2\2\2\31s\3\2\2\2\33w\3\2\2\2"+
		"\35y\3\2\2\2\37{\3\2\2\2!}\3\2\2\2#\177\3\2\2\2%\u008c\3\2\2\2\'\u0091"+
		"\3\2\2\2)\u0093\3\2\2\2+\u0095\3\2\2\2-\u0097\3\2\2\2/\u0099\3\2\2\2\61"+
		"\u00a0\3\2\2\2\63\u00aa\3\2\2\2\65\u00af\3\2\2\2\67\u00ba\3\2\2\29\u00c0"+
		"\3\2\2\2;<\7K\2\2<=\7P\2\2=>\7V\2\2>\4\3\2\2\2?@\7N\2\2@A\7G\2\2AB\7T"+
		"\2\2B\6\3\2\2\2CD\7T\2\2DE\7G\2\2EF\7C\2\2FG\7N\2\2G\b\3\2\2\2HI\7K\2"+
		"\2IJ\7O\2\2JK\7R\2\2KL\7T\2\2LM\7K\2\2MN\7O\2\2NO\7K\2\2OP\7T\2\2P\n\3"+
		"\2\2\2QR\7U\2\2RS\7G\2\2S\f\3\2\2\2TU\7U\2\2UV\7G\2\2VW\7P\2\2WX\7C\2"+
		"\2XY\7Q\2\2Y\16\3\2\2\2Z[\7G\2\2[\\\7P\2\2\\]\7V\2\2]^\7C\2\2^_\7Q\2\2"+
		"_\20\3\2\2\2`a\7G\2\2ab\7P\2\2bc\7S\2\2cd\7V\2\2de\7Q\2\2e\22\3\2\2\2"+
		"fg\7K\2\2gh\7P\2\2hi\7K\2\2i\24\3\2\2\2jk\7H\2\2kl\7K\2\2lm\7O\2\2m\26"+
		"\3\2\2\2no\7R\2\2op\7T\2\2pq\7Q\2\2qr\7I\2\2r\30\3\2\2\2st\7F\2\2tu\7"+
		"G\2\2uv\7E\2\2v\32\3\2\2\2wx\7,\2\2x\34\3\2\2\2yz\7\61\2\2z\36\3\2\2\2"+
		"{|\7-\2\2| \3\2\2\2}~\7/\2\2~\"\3\2\2\2\177\u0080\7<\2\2\u0080\u0081\7"+
		"?\2\2\u0081$\3\2\2\2\u0082\u0083\7>\2\2\u0083\u008d\7?\2\2\u0084\u008d"+
		"\7>\2\2\u0085\u0086\7@\2\2\u0086\u008d\7?\2\2\u0087\u008d\7@\2\2\u0088"+
		"\u0089\7?\2\2\u0089\u008d\7?\2\2\u008a\u008b\7#\2\2\u008b\u008d\7?\2\2"+
		"\u008c\u0082\3\2\2\2\u008c\u0084\3\2\2\2\u008c\u0085\3\2\2\2\u008c\u0087"+
		"\3\2\2\2\u008c\u0088\3\2\2\2\u008c\u008a\3\2\2\2\u008d&\3\2\2\2\u008e"+
		"\u0092\7G\2\2\u008f\u0090\7Q\2\2\u0090\u0092\7W\2\2\u0091\u008e\3\2\2"+
		"\2\u0091\u008f\3\2\2\2\u0092(\3\2\2\2\u0093\u0094\7<\2\2\u0094*\3\2\2"+
		"\2\u0095\u0096\7*\2\2\u0096,\3\2\2\2\u0097\u0098\7+\2\2\u0098.\3\2\2\2"+
		"\u0099\u009d\t\2\2\2\u009a\u009c\t\3\2\2\u009b\u009a\3\2\2\2\u009c\u009f"+
		"\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\60\3\2\2\2\u009f"+
		"\u009d\3\2\2\2\u00a0\u00a4\7$\2\2\u00a1\u00a3\13\2\2\2\u00a2\u00a1\3\2"+
		"\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5"+
		"\u00a7\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00a8\7$\2\2\u00a8\62\3\2\2\2"+
		"\u00a9\u00ab\t\4\2\2\u00aa\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00aa"+
		"\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\64\3\2\2\2\u00ae\u00b0\t\4\2\2\u00af"+
		"\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2"+
		"\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b5\7\60\2\2\u00b4\u00b6\t\4\2\2\u00b5"+
		"\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2"+
		"\2\2\u00b8\66\3\2\2\2\u00b9\u00bb\t\5\2\2\u00ba\u00b9\3\2\2\2\u00bb\u00bc"+
		"\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\3\2\2\2\u00be"+
		"\u00bf\b\34\2\2\u00bf8\3\2\2\2\u00c0\u00c4\7%\2\2\u00c1\u00c3\n\6\2\2"+
		"\u00c2\u00c1\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5"+
		"\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c8\b\35\2\2"+
		"\u00c8:\3\2\2\2\f\2\u008c\u0091\u009d\u00a4\u00ac\u00b1\u00b7\u00bc\u00c4"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}