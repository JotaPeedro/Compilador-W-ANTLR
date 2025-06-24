// Generated from GyhLang.g4 by ANTLR 4.7.2

	import java.util.ArrayList;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GyhLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PcInt=1, PcLer=2, PcReal=3, PcImprimir=4, PcSe=5, PcSenao=6, PcEntao=7, 
		PcEnqto=8, PcIni=9, PcFim=10, PCProg=11, PcDec=12, OpAritMult=13, OpAritDiv=14, 
		OpAritSoma=15, OpAritSub=16, DELIM=17, Atrib=18, OpRel=19, OpBol=20, AbrePar=21, 
		FechaPar=22, Var=23, Cadeia=24, NumInt=25, NumReal=26, WS=27, Coment=28;
	public static final int
		RULE_programa = 0, RULE_listaDeclaracoes = 1, RULE_declaracao = 2, RULE_listaComandos = 3, 
		RULE_comando = 4, RULE_comandoAtribuicao = 5, RULE_comandoEntrada = 6, 
		RULE_comandoSaida = 7, RULE_comandoCondicao = 8, RULE_comandoRepeticao = 9, 
		RULE_subAlgoritmo = 10, RULE_expressaoAritmetica = 11, RULE_termoAritmetico = 12, 
		RULE_fatorAritmetico = 13, RULE_expressaoRelacional = 14, RULE_termoRelacional = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "listaDeclaracoes", "declaracao", "listaComandos", "comando", 
			"comandoAtribuicao", "comandoEntrada", "comandoSaida", "comandoCondicao", 
			"comandoRepeticao", "subAlgoritmo", "expressaoAritmetica", "termoAritmetico", 
			"fatorAritmetico", "expressaoRelacional", "termoRelacional"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'INT'", "'LER'", "'REAL'", "'IMPRIMIR'", "'SE'", "'SENAO'", "'ENTAO'", 
			"'ENQTO'", "'INI'", "'FIM'", "'PROG'", "'DEC'", "'*'", "'/'", "'+'", 
			"'-'", "':'", "':='", null, null, "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PcInt", "PcLer", "PcReal", "PcImprimir", "PcSe", "PcSenao", "PcEntao", 
			"PcEnqto", "PcIni", "PcFim", "PCProg", "PcDec", "OpAritMult", "OpAritDiv", 
			"OpAritSoma", "OpAritSub", "DELIM", "Atrib", "OpRel", "OpBol", "AbrePar", 
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

	@Override
	public String getGrammarFileName() { return "GyhLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		private String _varName;
		private int _varType;
		private String _varValue;
		private Symbol _varSymbol;
		private SymbolTable SymbolTable=new SymbolTable();
		private Symbol.Tipo currentExprType;
		
		public void verificarVar(String nameVar){
		if(!SymbolTable.exists(nameVar)){
			System.err.println("Erro Semântico Variavel não declarada "+nameVar);
			}
		}
		
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


	public GyhLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramaContext extends ParserRuleContext {
		public List<TerminalNode> DELIM() { return getTokens(GyhLangParser.DELIM); }
		public TerminalNode DELIM(int i) {
			return getToken(GyhLangParser.DELIM, i);
		}
		public TerminalNode PcDec() { return getToken(GyhLangParser.PcDec, 0); }
		public ListaDeclaracoesContext listaDeclaracoes() {
			return getRuleContext(ListaDeclaracoesContext.class,0);
		}
		public TerminalNode PCProg() { return getToken(GyhLangParser.PCProg, 0); }
		public ListaComandosContext listaComandos() {
			return getRuleContext(ListaComandosContext.class,0);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(DELIM);
			setState(33);
			match(PcDec);
			setState(34);
			listaDeclaracoes();
			setState(35);
			match(DELIM);
			setState(36);
			match(PCProg);
			setState(37);
			listaComandos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListaDeclaracoesContext extends ParserRuleContext {
		public List<DeclaracaoContext> declaracao() {
			return getRuleContexts(DeclaracaoContext.class);
		}
		public DeclaracaoContext declaracao(int i) {
			return getRuleContext(DeclaracaoContext.class,i);
		}
		public ListaDeclaracoesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaDeclaracoes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterListaDeclaracoes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitListaDeclaracoes(this);
		}
	}

	public final ListaDeclaracoesContext listaDeclaracoes() throws RecognitionException {
		ListaDeclaracoesContext _localctx = new ListaDeclaracoesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_listaDeclaracoes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Var) {
				{
				{
				setState(39);
				declaracao();
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaracaoContext extends ParserRuleContext {
		public Token Var;
		public Token i;
		public Token r;
		public TerminalNode Var() { return getToken(GyhLangParser.Var, 0); }
		public TerminalNode DELIM() { return getToken(GyhLangParser.DELIM, 0); }
		public TerminalNode PcInt() { return getToken(GyhLangParser.PcInt, 0); }
		public TerminalNode PcReal() { return getToken(GyhLangParser.PcReal, 0); }
		public DeclaracaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterDeclaracao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitDeclaracao(this);
		}
	}

	public final DeclaracaoContext declaracao() throws RecognitionException {
		DeclaracaoContext _localctx = new DeclaracaoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaracao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			((DeclaracaoContext)_localctx).Var = match(Var);
			setState(46);
			match(DELIM);
			setState(49);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PcInt:
				{
				setState(47);
				((DeclaracaoContext)_localctx).i = match(PcInt);
				}
				break;
			case PcReal:
				{
				setState(48);
				((DeclaracaoContext)_localctx).r = match(PcReal);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			        String varName = (((DeclaracaoContext)_localctx).Var!=null?((DeclaracaoContext)_localctx).Var.getText():null);
			        if (SymbolTable.exists(varName)) {
			            System.err.println("Erro Semântico: Variável '" + varName + "' já foi declarada.");
			        } else {
			            Symbol.Tipo varType;
			            
			            // --- AQUI ESTÁ A CORREÇÃO DEFINITIVA ---
			            // A verificação agora é muito mais simples e direta.
			            // O rótulo '((DeclaracaoContext)_localctx).i' só terá um valor (não será nulo) se o token PcInt for encontrado.
			            if (((DeclaracaoContext)_localctx).i != null) {
			                varType = Symbol.Tipo.INT;
			            } else {
			                varType = Symbol.Tipo.REAL;
			            }
			            
			            Symbol symbol = new Symbol(varName, varType);
			            SymbolTable.add(symbol);
			        }
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListaComandosContext extends ParserRuleContext {
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public ListaComandosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaComandos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterListaComandos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitListaComandos(this);
		}
	}

	public final ListaComandosContext listaComandos() throws RecognitionException {
		ListaComandosContext _localctx = new ListaComandosContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_listaComandos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PcLer) | (1L << PcImprimir) | (1L << PcSe) | (1L << PcEnqto) | (1L << PcIni) | (1L << Var))) != 0)) {
				{
				{
				setState(53);
				comando();
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoContext extends ParserRuleContext {
		public ComandoAtribuicaoContext comandoAtribuicao() {
			return getRuleContext(ComandoAtribuicaoContext.class,0);
		}
		public ComandoEntradaContext comandoEntrada() {
			return getRuleContext(ComandoEntradaContext.class,0);
		}
		public ComandoSaidaContext comandoSaida() {
			return getRuleContext(ComandoSaidaContext.class,0);
		}
		public ComandoCondicaoContext comandoCondicao() {
			return getRuleContext(ComandoCondicaoContext.class,0);
		}
		public ComandoRepeticaoContext comandoRepeticao() {
			return getRuleContext(ComandoRepeticaoContext.class,0);
		}
		public SubAlgoritmoContext subAlgoritmo() {
			return getRuleContext(SubAlgoritmoContext.class,0);
		}
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitComando(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_comando);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Var:
				{
				setState(59);
				comandoAtribuicao();
				}
				break;
			case PcLer:
				{
				setState(60);
				comandoEntrada();
				}
				break;
			case PcImprimir:
				{
				setState(61);
				comandoSaida();
				}
				break;
			case PcSe:
				{
				setState(62);
				comandoCondicao();
				}
				break;
			case PcEnqto:
				{
				setState(63);
				comandoRepeticao();
				}
				break;
			case PcIni:
				{
				setState(64);
				subAlgoritmo();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoAtribuicaoContext extends ParserRuleContext {
		public Token Var;
		public TerminalNode Var() { return getToken(GyhLangParser.Var, 0); }
		public TerminalNode Atrib() { return getToken(GyhLangParser.Atrib, 0); }
		public ExpressaoAritmeticaContext expressaoAritmetica() {
			return getRuleContext(ExpressaoAritmeticaContext.class,0);
		}
		public ComandoAtribuicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoAtribuicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterComandoAtribuicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitComandoAtribuicao(this);
		}
	}

	public final ComandoAtribuicaoContext comandoAtribuicao() throws RecognitionException {
		ComandoAtribuicaoContext _localctx = new ComandoAtribuicaoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_comandoAtribuicao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			((ComandoAtribuicaoContext)_localctx).Var = match(Var);
			setState(68);
			match(Atrib);
			setState(69);
			expressaoAritmetica();
			checarAtribuicao((((ComandoAtribuicaoContext)_localctx).Var!=null?((ComandoAtribuicaoContext)_localctx).Var.getText():null), currentExprType);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoEntradaContext extends ParserRuleContext {
		public TerminalNode PcLer() { return getToken(GyhLangParser.PcLer, 0); }
		public TerminalNode Var() { return getToken(GyhLangParser.Var, 0); }
		public ComandoEntradaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoEntrada; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterComandoEntrada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitComandoEntrada(this);
		}
	}

	public final ComandoEntradaContext comandoEntrada() throws RecognitionException {
		ComandoEntradaContext _localctx = new ComandoEntradaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_comandoEntrada);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(PcLer);
			setState(73);
			match(Var);
			verificarVar(_input.LT(-1).getText());
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoSaidaContext extends ParserRuleContext {
		public TerminalNode PcImprimir() { return getToken(GyhLangParser.PcImprimir, 0); }
		public TerminalNode Var() { return getToken(GyhLangParser.Var, 0); }
		public TerminalNode Cadeia() { return getToken(GyhLangParser.Cadeia, 0); }
		public ComandoSaidaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoSaida; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterComandoSaida(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitComandoSaida(this);
		}
	}

	public final ComandoSaidaContext comandoSaida() throws RecognitionException {
		ComandoSaidaContext _localctx = new ComandoSaidaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_comandoSaida);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(PcImprimir);
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Var:
				{
				setState(77);
				match(Var);
				verificarVar(_input.LT(-1).getText());
				}
				break;
			case Cadeia:
				{
				setState(79);
				match(Cadeia);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoCondicaoContext extends ParserRuleContext {
		public TerminalNode PcSe() { return getToken(GyhLangParser.PcSe, 0); }
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
		}
		public TerminalNode PcEntao() { return getToken(GyhLangParser.PcEntao, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public TerminalNode PcSenao() { return getToken(GyhLangParser.PcSenao, 0); }
		public ComandoCondicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoCondicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterComandoCondicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitComandoCondicao(this);
		}
	}

	public final ComandoCondicaoContext comandoCondicao() throws RecognitionException {
		ComandoCondicaoContext _localctx = new ComandoCondicaoContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_comandoCondicao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(PcSe);
			setState(83);
			expressaoRelacional();
			setState(84);
			match(PcEntao);
			setState(85);
			comando();
			setState(88);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(86);
				match(PcSenao);
				setState(87);
				comando();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoRepeticaoContext extends ParserRuleContext {
		public TerminalNode PcEnqto() { return getToken(GyhLangParser.PcEnqto, 0); }
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
		}
		public ComandoContext comando() {
			return getRuleContext(ComandoContext.class,0);
		}
		public ComandoRepeticaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoRepeticao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterComandoRepeticao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitComandoRepeticao(this);
		}
	}

	public final ComandoRepeticaoContext comandoRepeticao() throws RecognitionException {
		ComandoRepeticaoContext _localctx = new ComandoRepeticaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_comandoRepeticao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(PcEnqto);
			setState(91);
			expressaoRelacional();
			setState(92);
			comando();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubAlgoritmoContext extends ParserRuleContext {
		public TerminalNode PcIni() { return getToken(GyhLangParser.PcIni, 0); }
		public ListaComandosContext listaComandos() {
			return getRuleContext(ListaComandosContext.class,0);
		}
		public TerminalNode PcFim() { return getToken(GyhLangParser.PcFim, 0); }
		public SubAlgoritmoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subAlgoritmo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterSubAlgoritmo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitSubAlgoritmo(this);
		}
	}

	public final SubAlgoritmoContext subAlgoritmo() throws RecognitionException {
		SubAlgoritmoContext _localctx = new SubAlgoritmoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_subAlgoritmo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(PcIni);
			setState(95);
			listaComandos();
			setState(96);
			match(PcFim);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressaoAritmeticaContext extends ParserRuleContext {
		public TermoAritmeticoContext e1;
		public Token op;
		public TermoAritmeticoContext e2;
		public List<TermoAritmeticoContext> termoAritmetico() {
			return getRuleContexts(TermoAritmeticoContext.class);
		}
		public TermoAritmeticoContext termoAritmetico(int i) {
			return getRuleContext(TermoAritmeticoContext.class,i);
		}
		public List<TerminalNode> OpAritSoma() { return getTokens(GyhLangParser.OpAritSoma); }
		public TerminalNode OpAritSoma(int i) {
			return getToken(GyhLangParser.OpAritSoma, i);
		}
		public List<TerminalNode> OpAritSub() { return getTokens(GyhLangParser.OpAritSub); }
		public TerminalNode OpAritSub(int i) {
			return getToken(GyhLangParser.OpAritSub, i);
		}
		public ExpressaoAritmeticaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoAritmetica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterExpressaoAritmetica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitExpressaoAritmetica(this);
		}
	}

	public final ExpressaoAritmeticaContext expressaoAritmetica() throws RecognitionException {
		ExpressaoAritmeticaContext _localctx = new ExpressaoAritmeticaContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expressaoAritmetica);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			((ExpressaoAritmeticaContext)_localctx).e1 = termoAritmetico();
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpAritSoma || _la==OpAritSub) {
				{
				{
				setState(99);
				((ExpressaoAritmeticaContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==OpAritSoma || _la==OpAritSub) ) {
					((ExpressaoAritmeticaContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				            // AQUI ESTÁ A MUDANÇA-CHAVE:
				            // Salvamos o tipo do lado esquerdo ANTES de processar o lado direito.
				            Symbol.Tipo tipoEsquerda = currentExprType;
				        
				setState(101);
				((ExpressaoAritmeticaContext)_localctx).e2 = termoAritmetico();

				            // Agora usamos o tipo salvo da esquerda e o tipo atual (da direita)
				            // para calcular o tipo final da expressão.
				            currentExprType = checarTipoExpressao(tipoEsquerda, currentExprType);
				        
				}
				}
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoAritmeticoContext extends ParserRuleContext {
		public FatorAritmeticoContext e1;
		public Token op;
		public FatorAritmeticoContext e2;
		public List<FatorAritmeticoContext> fatorAritmetico() {
			return getRuleContexts(FatorAritmeticoContext.class);
		}
		public FatorAritmeticoContext fatorAritmetico(int i) {
			return getRuleContext(FatorAritmeticoContext.class,i);
		}
		public List<TerminalNode> OpAritMult() { return getTokens(GyhLangParser.OpAritMult); }
		public TerminalNode OpAritMult(int i) {
			return getToken(GyhLangParser.OpAritMult, i);
		}
		public List<TerminalNode> OpAritDiv() { return getTokens(GyhLangParser.OpAritDiv); }
		public TerminalNode OpAritDiv(int i) {
			return getToken(GyhLangParser.OpAritDiv, i);
		}
		public TermoAritmeticoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termoAritmetico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterTermoAritmetico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitTermoAritmetico(this);
		}
	}

	public final TermoAritmeticoContext termoAritmetico() throws RecognitionException {
		TermoAritmeticoContext _localctx = new TermoAritmeticoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_termoAritmetico);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			((TermoAritmeticoContext)_localctx).e1 = fatorAritmetico();
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpAritMult || _la==OpAritDiv) {
				{
				{
				setState(110);
				((TermoAritmeticoContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==OpAritMult || _la==OpAritDiv) ) {
					((TermoAritmeticoContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				            // A mesma lógica: salvamos o tipo da esquerda.
				            Symbol.Tipo tipoEsquerda = currentExprType;
				        
				setState(112);
				((TermoAritmeticoContext)_localctx).e2 = fatorAritmetico();

				            // E usamos os tipos corretos para a checagem.
				            currentExprType = checarTipoExpressao(tipoEsquerda, currentExprType);
				        
				}
				}
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FatorAritmeticoContext extends ParserRuleContext {
		public Token Var;
		public TerminalNode NumInt() { return getToken(GyhLangParser.NumInt, 0); }
		public TerminalNode NumReal() { return getToken(GyhLangParser.NumReal, 0); }
		public TerminalNode Var() { return getToken(GyhLangParser.Var, 0); }
		public TerminalNode AbrePar() { return getToken(GyhLangParser.AbrePar, 0); }
		public ExpressaoAritmeticaContext expressaoAritmetica() {
			return getRuleContext(ExpressaoAritmeticaContext.class,0);
		}
		public TerminalNode FechaPar() { return getToken(GyhLangParser.FechaPar, 0); }
		public FatorAritmeticoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fatorAritmetico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterFatorAritmetico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitFatorAritmetico(this);
		}
	}

	public final FatorAritmeticoContext fatorAritmetico() throws RecognitionException {
		FatorAritmeticoContext _localctx = new FatorAritmeticoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_fatorAritmetico);
		try {
			setState(130);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NumInt:
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				match(NumInt);
				 
				            // Ação: Define o tipo da expressão atual como INT
				            currentExprType = Symbol.Tipo.INT; 
				        
				}
				break;
			case NumReal:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				match(NumReal);
				 
				            // Ação: Define o tipo da expressão atual como REAL
				            currentExprType = Symbol.Tipo.REAL; 
				        
				}
				break;
			case Var:
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				((FatorAritmeticoContext)_localctx).Var = match(Var);

				            String varName = (((FatorAritmeticoContext)_localctx).Var!=null?((FatorAritmeticoContext)_localctx).Var.getText():null);
				            verificarVar(varName); // Verifica se a variável foi declarada
				            if (SymbolTable.exists(varName)) {
				                // AÇÃO ESSENCIAL: Define o tipo da expressão como o tipo da variável encontrada
				                currentExprType = SymbolTable.get(varName).getType();
				            } else {
				                // Se a variável não existe, marca o tipo como inválido
				                currentExprType = Symbol.Tipo.INVALIDO;
				            }
				        
				}
				break;
			case AbrePar:
				enterOuterAlt(_localctx, 4);
				{
				setState(126);
				match(AbrePar);
				setState(127);
				expressaoAritmetica();
				setState(128);
				match(FechaPar);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressaoRelacionalContext extends ParserRuleContext {
		public List<TermoRelacionalContext> termoRelacional() {
			return getRuleContexts(TermoRelacionalContext.class);
		}
		public TermoRelacionalContext termoRelacional(int i) {
			return getRuleContext(TermoRelacionalContext.class,i);
		}
		public List<TerminalNode> OpBol() { return getTokens(GyhLangParser.OpBol); }
		public TerminalNode OpBol(int i) {
			return getToken(GyhLangParser.OpBol, i);
		}
		public ExpressaoRelacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoRelacional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterExpressaoRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitExpressaoRelacional(this);
		}
	}

	public final ExpressaoRelacionalContext expressaoRelacional() throws RecognitionException {
		ExpressaoRelacionalContext _localctx = new ExpressaoRelacionalContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expressaoRelacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			termoRelacional();
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpBol) {
				{
				{
				setState(133);
				match(OpBol);
				setState(134);
				termoRelacional();
				}
				}
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoRelacionalContext extends ParserRuleContext {
		public List<ExpressaoAritmeticaContext> expressaoAritmetica() {
			return getRuleContexts(ExpressaoAritmeticaContext.class);
		}
		public ExpressaoAritmeticaContext expressaoAritmetica(int i) {
			return getRuleContext(ExpressaoAritmeticaContext.class,i);
		}
		public TerminalNode OpRel() { return getToken(GyhLangParser.OpRel, 0); }
		public TerminalNode AbrePar() { return getToken(GyhLangParser.AbrePar, 0); }
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
		}
		public TerminalNode FechaPar() { return getToken(GyhLangParser.FechaPar, 0); }
		public TermoRelacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termoRelacional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterTermoRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitTermoRelacional(this);
		}
	}

	public final TermoRelacionalContext termoRelacional() throws RecognitionException {
		TermoRelacionalContext _localctx = new TermoRelacionalContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_termoRelacional);
		try {
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				expressaoAritmetica();
				setState(141);
				match(OpRel);
				setState(142);
				expressaoAritmetica();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(144);
				match(AbrePar);
				setState(145);
				expressaoRelacional();
				setState(146);
				match(FechaPar);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36\u0099\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\3\7\3+\n\3\f\3\16\3.\13\3\3\4\3\4\3\4\3\4\5\4\64"+
		"\n\4\3\4\3\4\3\5\7\59\n\5\f\5\16\5<\13\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6D"+
		"\n\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\5\tS\n\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\5\n[\n\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\7\rk\n\r\f\r\16\rn\13\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\7\16v\n\16\f\16\16\16y\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\5\17\u0085\n\17\3\20\3\20\3\20\7\20\u008a\n\20\f\20\16"+
		"\20\u008d\13\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0097\n\21"+
		"\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\4\3\2\21\22\3\2"+
		"\17\20\2\u0099\2\"\3\2\2\2\4,\3\2\2\2\6/\3\2\2\2\b:\3\2\2\2\nC\3\2\2\2"+
		"\fE\3\2\2\2\16J\3\2\2\2\20N\3\2\2\2\22T\3\2\2\2\24\\\3\2\2\2\26`\3\2\2"+
		"\2\30d\3\2\2\2\32o\3\2\2\2\34\u0084\3\2\2\2\36\u0086\3\2\2\2 \u0096\3"+
		"\2\2\2\"#\7\23\2\2#$\7\16\2\2$%\5\4\3\2%&\7\23\2\2&\'\7\r\2\2\'(\5\b\5"+
		"\2(\3\3\2\2\2)+\5\6\4\2*)\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\5\3\2"+
		"\2\2.,\3\2\2\2/\60\7\31\2\2\60\63\7\23\2\2\61\64\7\3\2\2\62\64\7\5\2\2"+
		"\63\61\3\2\2\2\63\62\3\2\2\2\64\65\3\2\2\2\65\66\b\4\1\2\66\7\3\2\2\2"+
		"\679\5\n\6\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;\t\3\2\2\2<:\3"+
		"\2\2\2=D\5\f\7\2>D\5\16\b\2?D\5\20\t\2@D\5\22\n\2AD\5\24\13\2BD\5\26\f"+
		"\2C=\3\2\2\2C>\3\2\2\2C?\3\2\2\2C@\3\2\2\2CA\3\2\2\2CB\3\2\2\2D\13\3\2"+
		"\2\2EF\7\31\2\2FG\7\24\2\2GH\5\30\r\2HI\b\7\1\2I\r\3\2\2\2JK\7\4\2\2K"+
		"L\7\31\2\2LM\b\b\1\2M\17\3\2\2\2NR\7\6\2\2OP\7\31\2\2PS\b\t\1\2QS\7\32"+
		"\2\2RO\3\2\2\2RQ\3\2\2\2S\21\3\2\2\2TU\7\7\2\2UV\5\36\20\2VW\7\t\2\2W"+
		"Z\5\n\6\2XY\7\b\2\2Y[\5\n\6\2ZX\3\2\2\2Z[\3\2\2\2[\23\3\2\2\2\\]\7\n\2"+
		"\2]^\5\36\20\2^_\5\n\6\2_\25\3\2\2\2`a\7\13\2\2ab\5\b\5\2bc\7\f\2\2c\27"+
		"\3\2\2\2dl\5\32\16\2ef\t\2\2\2fg\b\r\1\2gh\5\32\16\2hi\b\r\1\2ik\3\2\2"+
		"\2je\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2m\31\3\2\2\2nl\3\2\2\2ow\5\34"+
		"\17\2pq\t\3\2\2qr\b\16\1\2rs\5\34\17\2st\b\16\1\2tv\3\2\2\2up\3\2\2\2"+
		"vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2x\33\3\2\2\2yw\3\2\2\2z{\7\33\2\2{\u0085"+
		"\b\17\1\2|}\7\34\2\2}\u0085\b\17\1\2~\177\7\31\2\2\177\u0085\b\17\1\2"+
		"\u0080\u0081\7\27\2\2\u0081\u0082\5\30\r\2\u0082\u0083\7\30\2\2\u0083"+
		"\u0085\3\2\2\2\u0084z\3\2\2\2\u0084|\3\2\2\2\u0084~\3\2\2\2\u0084\u0080"+
		"\3\2\2\2\u0085\35\3\2\2\2\u0086\u008b\5 \21\2\u0087\u0088\7\26\2\2\u0088"+
		"\u008a\5 \21\2\u0089\u0087\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2"+
		"\2\2\u008b\u008c\3\2\2\2\u008c\37\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u008f"+
		"\5\30\r\2\u008f\u0090\7\25\2\2\u0090\u0091\5\30\r\2\u0091\u0097\3\2\2"+
		"\2\u0092\u0093\7\27\2\2\u0093\u0094\5\36\20\2\u0094\u0095\7\30\2\2\u0095"+
		"\u0097\3\2\2\2\u0096\u008e\3\2\2\2\u0096\u0092\3\2\2\2\u0097!\3\2\2\2"+
		"\r,\63:CRZlw\u0084\u008b\u0096";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}