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
		OpAritSoma=15, OpAritSub=16, Atrib=17, OpRel=18, OpBol=19, DELIM=20, AbrePar=21, 
		FechaPar=22, Var=23, Cadeia=24, NumInt=25, NumReal=26, WS=27, Coment=28;
	public static final int
		RULE_programa = 0, RULE_listaDeclaracoes = 1, RULE_declaracao = 2, RULE_listaComandos = 3, 
		RULE_comando = 4, RULE_comandoAtribuicao = 5, RULE_comandoEntrada = 6, 
		RULE_comandoSaida = 7, RULE_comandoCondicao = 8, RULE_comandoRepeticao = 9, 
		RULE_subAlgoritmo = 10, RULE_expressaoRelacional = 11, RULE_termoRelacional = 12, 
		RULE_expressaoAritmetica = 13, RULE_termoAritmetico = 14, RULE_fatorAritmetico = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "listaDeclaracoes", "declaracao", "listaComandos", "comando", 
			"comandoAtribuicao", "comandoEntrada", "comandoSaida", "comandoCondicao", 
			"comandoRepeticao", "subAlgoritmo", "expressaoRelacional", "termoRelacional", 
			"expressaoAritmetica", "termoAritmetico", "fatorAritmetico"
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

	@Override
	public String getGrammarFileName() { return "GyhLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

					program.setVarTable(SymbolTable);
					System.out.println("Programa está na VarTable ");
					program.setCommand(listCmd);
					generateCommand();
				
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
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Var) {
				{
				{
				setState(40);
				declaracao();
				}
				}
				setState(45);
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
			setState(46);
			((DeclaracaoContext)_localctx).Var = match(Var);
			setState(47);
			match(DELIM);
			setState(50);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PcInt:
				{
				setState(48);
				((DeclaracaoContext)_localctx).i = match(PcInt);
				}
				break;
			case PcReal:
				{
				setState(49);
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
			            Symbol.Tipo varType = (((DeclaracaoContext)_localctx).i != null) ? Symbol.Tipo.INT : Symbol.Tipo.REAL;
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
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PcLer) | (1L << PcImprimir) | (1L << PcSe) | (1L << PcEnqto) | (1L << PcIni) | (1L << Var))) != 0)) {
				{
				{
				setState(54);
				comando();
				}
				}
				setState(59);
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
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Var:
				{
				setState(60);
				comandoAtribuicao();
				}
				break;
			case PcLer:
				{
				setState(61);
				comandoEntrada();
				}
				break;
			case PcImprimir:
				{
				setState(62);
				comandoSaida();
				}
				break;
			case PcSe:
				{
				setState(63);
				comandoCondicao();
				}
				break;
			case PcEnqto:
				{
				setState(64);
				comandoRepeticao();
				}
				break;
			case PcIni:
				{
				setState(65);
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
		public ExpressaoAritmeticaContext exp;
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
			setState(68);
			((ComandoAtribuicaoContext)_localctx).Var = match(Var);
			setState(69);
			match(Atrib);
			setState(70);
			((ComandoAtribuicaoContext)_localctx).exp = expressaoAritmetica();

			        checarAtribuicao((((ComandoAtribuicaoContext)_localctx).Var!=null?((ComandoAtribuicaoContext)_localctx).Var.getText():null), currentExprType);  
			        String varName = (((ComandoAtribuicaoContext)_localctx).Var!=null?((ComandoAtribuicaoContext)_localctx).Var.getText():null);
			        String expContent = (((ComandoAtribuicaoContext)_localctx).exp!=null?_input.getText(((ComandoAtribuicaoContext)_localctx).exp.start,((ComandoAtribuicaoContext)_localctx).exp.stop):null);
			        CommandAtribuicao cmd = new CommandAtribuicao(varName, expContent);
			        

			        listCmd.add(cmd);
			    
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
		public Token Var;
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
			setState(73);
			match(PcLer);
			setState(74);
			((ComandoEntradaContext)_localctx).Var = match(Var);


			        String varName = (((ComandoEntradaContext)_localctx).Var!=null?((ComandoEntradaContext)_localctx).Var.getText():null);
			        

			        verificarVar(varName);

			        CommandLeitura cmd = new CommandLeitura(varName);
			        

			        listCmd.add(cmd);
			    
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
		public Token Var;
		public Token Cadeia;
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
			setState(77);
			match(PcImprimir);
			setState(82);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Var:
				{
				setState(78);
				((ComandoSaidaContext)_localctx).Var = match(Var);

				            String varName = (((ComandoSaidaContext)_localctx).Var!=null?((ComandoSaidaContext)_localctx).Var.getText():null);
				            verificarVar(varName);
				            Symbol.Tipo varType = SymbolTable.get(varName).getType();
				            CommandEscrita cmd = new CommandEscrita(varName, varType);
				            listCmd.add(cmd);
				        
				}
				break;
			case Cadeia:
				{
				setState(80);
				((ComandoSaidaContext)_localctx).Cadeia = match(Cadeia);

				            CommandEscrita cmd = new CommandEscrita((((ComandoSaidaContext)_localctx).Cadeia!=null?((ComandoSaidaContext)_localctx).Cadeia.getText():null), Symbol.Tipo.CADEIA);
				            listCmd.add(cmd);
				        
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
		public ExpressaoRelacionalContext exp;
		public TerminalNode PcSe() { return getToken(GyhLangParser.PcSe, 0); }
		public TerminalNode PcEntao() { return getToken(GyhLangParser.PcEntao, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
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
			setState(84);
			match(PcSe);
			setState(85);
			((ComandoCondicaoContext)_localctx).exp = expressaoRelacional();
			setState(86);
			match(PcEntao);

			        ArrayList<Command> comandosTrue = new ArrayList<>();
			        ArrayList<Command> comandosFalse = new ArrayList<>();
			        ArrayList<Command> listaPrincipalAntiga = listCmd;
			        listCmd = comandosTrue;
			    
			setState(88);
			comando();
			setState(92);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(89);
				match(PcSenao);

				            listCmd = comandosFalse;
				        
				setState(91);
				comando();
				}
				break;
			}

			        listCmd = listaPrincipalAntiga;
			        String condicaoTexto = _input.getText((((ComandoCondicaoContext)_localctx).exp!=null?(((ComandoCondicaoContext)_localctx).exp.start):null), (((ComandoCondicaoContext)_localctx).exp!=null?(((ComandoCondicaoContext)_localctx).exp.stop):null));
			        condicaoTexto = condicaoTexto.replace("E", " && ").replace("OU", " || ");
			        CommandCondicao cmd = new CommandCondicao(condicaoTexto, comandosTrue, comandosFalse);
			        listCmd.add(cmd);
			    
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
		public ExpressaoRelacionalContext exp;
		public TerminalNode PcEnqto() { return getToken(GyhLangParser.PcEnqto, 0); }
		public ComandoContext comando() {
			return getRuleContext(ComandoContext.class,0);
		}
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
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
			setState(96);
			match(PcEnqto);
			setState(97);
			((ComandoRepeticaoContext)_localctx).exp = expressaoRelacional();

			        ArrayList<Command> comandosLoop = new ArrayList<>();
			        ArrayList<Command> listaPrincipalAntiga = listCmd;
			        listCmd = comandosLoop;
			    
			setState(99);
			comando();

			        listCmd = listaPrincipalAntiga;
			        String condicaoTexto = _input.getText((((ComandoRepeticaoContext)_localctx).exp!=null?(((ComandoRepeticaoContext)_localctx).exp.start):null), (((ComandoRepeticaoContext)_localctx).exp!=null?(((ComandoRepeticaoContext)_localctx).exp.stop):null));
			        condicaoTexto = condicaoTexto.replace("E", "&&").replace("OU", "||");
			        CommandRepeticao cmd = new CommandRepeticao(condicaoTexto, comandosLoop);
			        listCmd.add(cmd);
			    
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
			setState(102);
			match(PcIni);
			setState(103);
			listaComandos();
			setState(104);
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
		enterRule(_localctx, 22, RULE_expressaoRelacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			termoRelacional();
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpBol) {
				{
				{
				setState(107);
				match(OpBol);
				setState(108);
				termoRelacional();
				}
				}
				setState(113);
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
		enterRule(_localctx, 24, RULE_termoRelacional);
		try {
			setState(122);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				expressaoAritmetica();
				setState(115);
				match(OpRel);
				setState(116);
				expressaoAritmetica();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				match(AbrePar);
				setState(119);
				expressaoRelacional();
				setState(120);
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
		enterRule(_localctx, 26, RULE_expressaoAritmetica);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			((ExpressaoAritmeticaContext)_localctx).e1 = termoAritmetico();
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpAritSoma || _la==OpAritSub) {
				{
				{
				setState(125);
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

				            Symbol.Tipo tipoEsquerda = currentExprType;
				        
				setState(127);
				((ExpressaoAritmeticaContext)_localctx).e2 = termoAritmetico();

				            currentExprType = checarTipoExpressao(tipoEsquerda, currentExprType);
				        
				}
				}
				setState(134);
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
		enterRule(_localctx, 28, RULE_termoAritmetico);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			((TermoAritmeticoContext)_localctx).e1 = fatorAritmetico();
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpAritMult || _la==OpAritDiv) {
				{
				{
				setState(136);
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

				            Symbol.Tipo tipoEsquerda = currentExprType;
				        
				setState(138);
				((TermoAritmeticoContext)_localctx).e2 = fatorAritmetico();

				            currentExprType = checarTipoExpressao(tipoEsquerda, currentExprType);
				        
				}
				}
				setState(145);
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
		public Token NumReal;
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
		enterRule(_localctx, 30, RULE_fatorAritmetico);
		try {
			setState(156);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NumInt:
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				match(NumInt);
				 currentExprType = Symbol.Tipo.INT; 
				}
				break;
			case NumReal:
				enterOuterAlt(_localctx, 2);
				{
				setState(148);
				((FatorAritmeticoContext)_localctx).NumReal = match(NumReal);
				 
				            checarPrecisaoReal((((FatorAritmeticoContext)_localctx).NumReal!=null?((FatorAritmeticoContext)_localctx).NumReal.getText():null));
				            currentExprType = Symbol.Tipo.REAL; 
				        
				}
				break;
			case Var:
				enterOuterAlt(_localctx, 3);
				{
				setState(150);
				((FatorAritmeticoContext)_localctx).Var = match(Var);

				            String varName = (((FatorAritmeticoContext)_localctx).Var!=null?((FatorAritmeticoContext)_localctx).Var.getText():null);
				            verificarVar(varName);
				            if (SymbolTable.exists(varName)) {
				                currentExprType = SymbolTable.get(varName).getType();
				            } else {
				                currentExprType = Symbol.Tipo.INVALIDO;
				            }
				        
				}
				break;
			case AbrePar:
				enterOuterAlt(_localctx, 4);
				{
				setState(152);
				match(AbrePar);
				setState(153);
				expressaoAritmetica();
				setState(154);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36\u00a1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\7\3,\n\3\f\3\16\3/\13\3\3\4\3\4\3\4\3\4\5"+
		"\4\65\n\4\3\4\3\4\3\5\7\5:\n\5\f\5\16\5=\13\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\5\6E\n\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\5\t"+
		"U\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n_\n\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\7\rp\n\r\f\r\16\rs\13\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16}\n\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\7\17\u0085\n\17\f\17\16\17\u0088\13\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\7\20\u0090\n\20\f\20\16\20\u0093\13\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u009f\n\21\3\21\2\2\22\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \2\4\3\2\21\22\3\2\17\20\2\u00a1\2\"\3\2\2"+
		"\2\4-\3\2\2\2\6\60\3\2\2\2\b;\3\2\2\2\nD\3\2\2\2\fF\3\2\2\2\16K\3\2\2"+
		"\2\20O\3\2\2\2\22V\3\2\2\2\24b\3\2\2\2\26h\3\2\2\2\30l\3\2\2\2\32|\3\2"+
		"\2\2\34~\3\2\2\2\36\u0089\3\2\2\2 \u009e\3\2\2\2\"#\7\26\2\2#$\7\16\2"+
		"\2$%\5\4\3\2%&\7\26\2\2&\'\7\r\2\2\'(\5\b\5\2()\b\2\1\2)\3\3\2\2\2*,\5"+
		"\6\4\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\5\3\2\2\2/-\3\2\2\2\60"+
		"\61\7\31\2\2\61\64\7\26\2\2\62\65\7\3\2\2\63\65\7\5\2\2\64\62\3\2\2\2"+
		"\64\63\3\2\2\2\65\66\3\2\2\2\66\67\b\4\1\2\67\7\3\2\2\28:\5\n\6\298\3"+
		"\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<\t\3\2\2\2=;\3\2\2\2>E\5\f\7\2?E"+
		"\5\16\b\2@E\5\20\t\2AE\5\22\n\2BE\5\24\13\2CE\5\26\f\2D>\3\2\2\2D?\3\2"+
		"\2\2D@\3\2\2\2DA\3\2\2\2DB\3\2\2\2DC\3\2\2\2E\13\3\2\2\2FG\7\31\2\2GH"+
		"\7\23\2\2HI\5\34\17\2IJ\b\7\1\2J\r\3\2\2\2KL\7\4\2\2LM\7\31\2\2MN\b\b"+
		"\1\2N\17\3\2\2\2OT\7\6\2\2PQ\7\31\2\2QU\b\t\1\2RS\7\32\2\2SU\b\t\1\2T"+
		"P\3\2\2\2TR\3\2\2\2U\21\3\2\2\2VW\7\7\2\2WX\5\30\r\2XY\7\t\2\2YZ\b\n\1"+
		"\2Z^\5\n\6\2[\\\7\b\2\2\\]\b\n\1\2]_\5\n\6\2^[\3\2\2\2^_\3\2\2\2_`\3\2"+
		"\2\2`a\b\n\1\2a\23\3\2\2\2bc\7\n\2\2cd\5\30\r\2de\b\13\1\2ef\5\n\6\2f"+
		"g\b\13\1\2g\25\3\2\2\2hi\7\13\2\2ij\5\b\5\2jk\7\f\2\2k\27\3\2\2\2lq\5"+
		"\32\16\2mn\7\25\2\2np\5\32\16\2om\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2"+
		"\2r\31\3\2\2\2sq\3\2\2\2tu\5\34\17\2uv\7\24\2\2vw\5\34\17\2w}\3\2\2\2"+
		"xy\7\27\2\2yz\5\30\r\2z{\7\30\2\2{}\3\2\2\2|t\3\2\2\2|x\3\2\2\2}\33\3"+
		"\2\2\2~\u0086\5\36\20\2\177\u0080\t\2\2\2\u0080\u0081\b\17\1\2\u0081\u0082"+
		"\5\36\20\2\u0082\u0083\b\17\1\2\u0083\u0085\3\2\2\2\u0084\177\3\2\2\2"+
		"\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\35"+
		"\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u0091\5 \21\2\u008a\u008b\t\3\2\2\u008b"+
		"\u008c\b\20\1\2\u008c\u008d\5 \21\2\u008d\u008e\b\20\1\2\u008e\u0090\3"+
		"\2\2\2\u008f\u008a\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\37\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0095\7\33\2"+
		"\2\u0095\u009f\b\21\1\2\u0096\u0097\7\34\2\2\u0097\u009f\b\21\1\2\u0098"+
		"\u0099\7\31\2\2\u0099\u009f\b\21\1\2\u009a\u009b\7\27\2\2\u009b\u009c"+
		"\5\34\17\2\u009c\u009d\7\30\2\2\u009d\u009f\3\2\2\2\u009e\u0094\3\2\2"+
		"\2\u009e\u0096\3\2\2\2\u009e\u0098\3\2\2\2\u009e\u009a\3\2\2\2\u009f!"+
		"\3\2\2\2\r-\64;DT^q|\u0086\u0091\u009e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}