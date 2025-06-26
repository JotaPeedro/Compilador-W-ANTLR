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
		RULE_comandoSaida = 7, RULE_comandoCondicao = 8, RULE_expressaoRelacional = 9, 
		RULE_termoRelacional = 10, RULE_comandoRepeticao = 11, RULE_subAlgoritmo = 12, 
		RULE_expressaoAritmetica = 13, RULE_termoAritmetico = 14, RULE_fatorAritmetico = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "listaDeclaracoes", "declaracao", "listaComandos", "comando", 
			"comandoAtribuicao", "comandoEntrada", "comandoSaida", "comandoCondicao", 
			"expressaoRelacional", "termoRelacional", "comandoRepeticao", "subAlgoritmo", 
			"expressaoAritmetica", "termoAritmetico", "fatorAritmetico"
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
			            Symbol.Tipo varType;

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
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PcLer) | (1L << PcImprimir) | (1L << PcSe) | (1L << PcEnqto) | (1L << PcIni) | (1L << Var))) != 0)) {
				{
				{
				setState(54);
				comando();

										listCmd.addAll(listAux);
										listAux.removeAll(listAux);
										
										
				}
				}
				setState(61);
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
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Var:
				{
				setState(62);
				comandoAtribuicao();
				}
				break;
			case PcLer:
				{
				setState(63);
				comandoEntrada();
				}
				break;
			case PcImprimir:
				{
				setState(64);
				comandoSaida();
				}
				break;
			case PcSe:
				{
				setState(65);
				comandoCondicao();
				}
				break;
			case PcEnqto:
				{
				setState(66);
				comandoRepeticao();
				}
				break;
			case PcIni:
				{
				setState(67);
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
			setState(70);
			((ComandoAtribuicaoContext)_localctx).Var = match(Var);
			setState(71);
			match(Atrib);
			setState(72);
			((ComandoAtribuicaoContext)_localctx).exp = expressaoAritmetica();

			        // 2. A checagem semântica continua sendo o primeiro passo
			        checarAtribuicao((((ComandoAtribuicaoContext)_localctx).Var!=null?((ComandoAtribuicaoContext)_localctx).Var.getText():null), currentExprType);
			        
			        // 3. Capturamos o nome da variável e o conteúdo da expressão
			        _expVar = (((ComandoAtribuicaoContext)_localctx).Var!=null?((ComandoAtribuicaoContext)_localctx).Var.getText():null);
			        _expContent = (((ComandoAtribuicaoContext)_localctx).exp!=null?_input.getText(((ComandoAtribuicaoContext)_localctx).exp.start,((ComandoAtribuicaoContext)_localctx).exp.stop):null); // Usamos o rótulo para pegar o texto completo da expressão
			        
			        // 4. Criamos o objeto de comando com os valores capturados
			        CommandAtribuicao cmd = new CommandAtribuicao(_expVar, _expContent);
			        
			        // 5. Adicionamos o comando à lista para geração de código
			        listAux.add(cmd);
			    
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
			setState(75);
			match(PcLer);
			setState(76);
			match(Var);
			verificarVar(_input.LT(-1).getText());
										_readVar=_input.LT(-1).getText();
										CommandLeitura cmd = new CommandLeitura(_readVar);
										listAux.add(cmd);
										
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
			setState(79);
			match(PcImprimir);
			setState(84);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Var:
				{
				setState(80);
				((ComandoSaidaContext)_localctx).Var = match(Var);

				            String varName = (((ComandoSaidaContext)_localctx).Var!=null?((ComandoSaidaContext)_localctx).Var.getText():null);
				            verificarVar(varName);
				            
				            // Pega o tipo da variável da tabela de símbolos
				            Symbol.Tipo varType = SymbolTable.get(varName).getType();
				            
				            // Cria o comando com o nome da variável e seu tipo
				            CommandEscrita cmd = new CommandEscrita(varName, varType);
				            listCmd.add(cmd);
				        
				}
				break;
			case Cadeia:
				{
				setState(82);
				((ComandoSaidaContext)_localctx).Cadeia = match(Cadeia);

				            // Cria o comando com o texto da cadeia e o tipo CADEIA
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
			setState(86);
			match(PcSe);
			setState(87);
			((ComandoCondicaoContext)_localctx).exp = expressaoRelacional();
			setState(88);
			match(PcEntao);

			        // 1. Prepara as listas para os blocos TRUE e FALSE
			        ArrayList<Command> comandosTrue = new ArrayList<>();
			        ArrayList<Command> comandosFalse = new ArrayList<>();
			        
			        // Salva a lista principal atual
			        ArrayList<Command> listaPrincipalAntiga = listCmd;
			        
			        // 2. Redireciona a lista principal para a lista do bloco TRUE
			        listCmd = comandosTrue;
			    
			setState(90);
			comando();
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(91);
				match(PcSenao);

				            // 3. Se encontrar um SENAO, redireciona a lista para o bloco FALSE
				            listCmd = comandosFalse;
				        
				setState(93);
				comando();
				}
				break;
			}

			        // 4. AÇÃO FINAL: Restaura a lista principal e monta o comando IF
			        listCmd = listaPrincipalAntiga;
			        
			       String condicaoTexto = _input.getText((((ComandoCondicaoContext)_localctx).exp!=null?(((ComandoCondicaoContext)_localctx).exp.start):null), (((ComandoCondicaoContext)_localctx).exp!=null?(((ComandoCondicaoContext)_localctx).exp.stop):null));


			    condicaoTexto = condicaoTexto.replace("E", " && ").replace("OU", " || ");
			        
			        // Cria o comando IF com as listas que foram preenchidas.
			        // Se não houve SENAO, 'comandosFalse' estará simplesmente vazia.
			        CommandCondicao cmd = new CommandCondicao(condicaoTexto, comandosTrue, comandosFalse);
			        
			        // Adiciona o comando IF completo (já com seus filhos) à lista principal
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
		enterRule(_localctx, 18, RULE_expressaoRelacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			termoRelacional();
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpBol) {
				{
				{
				setState(99);
				match(OpBol);
				setState(100);
				termoRelacional();
				}
				}
				setState(105);
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
		enterRule(_localctx, 20, RULE_termoRelacional);
		try {
			setState(114);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				expressaoAritmetica();
				setState(107);
				match(OpRel);
				setState(108);
				expressaoAritmetica();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(110);
				match(AbrePar);
				setState(111);
				expressaoRelacional();
				setState(112);
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
		enterRule(_localctx, 22, RULE_comandoRepeticao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(PcEnqto);
			setState(117);
			((ComandoRepeticaoContext)_localctx).exp = expressaoRelacional();

			        // 1. Prepara a lista para o bloco de comandos do loop
			        ArrayList<Command> comandosLoop = new ArrayList<>();
			        ArrayList<Command> listaPrincipalAntiga = listCmd;
			        
			        // 2. Redireciona a lista principal para a lista do loop
			        listCmd = comandosLoop;
			    
			setState(119);
			comando();

			        // 3. Restaura a lista principal
			        listCmd = listaPrincipalAntiga;
			        
			        // 4. Captura a condição e cria o comando de repetição
			        String condicaoTexto = _input.getText((((ComandoRepeticaoContext)_localctx).exp!=null?(((ComandoRepeticaoContext)_localctx).exp.start):null), (((ComandoRepeticaoContext)_localctx).exp!=null?(((ComandoRepeticaoContext)_localctx).exp.stop):null));
			        condicaoTexto = condicaoTexto.replace("E", "&&").replace("OU", "||");
			        
			        // Supondo que você tenha uma classe CommandRepeticao
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
		enterRule(_localctx, 24, RULE_subAlgoritmo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(PcIni);
			setState(123);
			listaComandos();
			setState(124);
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
		enterRule(_localctx, 26, RULE_expressaoAritmetica);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			((ExpressaoAritmeticaContext)_localctx).e1 = termoAritmetico();
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpAritSoma || _la==OpAritSub) {
				{
				{
				setState(127);
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

				         
				            // Salva o tipo do lado esquerdo ANTES de processar o lado direito.
				            Symbol.Tipo tipoEsquerda = currentExprType;
				        
				setState(129);
				((ExpressaoAritmeticaContext)_localctx).e2 = termoAritmetico();

				            // Usa o tipo salvo da esquerda e o tipo atual (da direita)
				            // para calcular o tipo final da expressão.
				            currentExprType = checarTipoExpressao(tipoEsquerda, currentExprType);
				        
				}
				}
				setState(136);
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
			setState(137);
			((TermoAritmeticoContext)_localctx).e1 = fatorAritmetico();
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpAritMult || _la==OpAritDiv) {
				{
				{
				setState(138);
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

				            // salvamos o tipo da esquerda.
				            Symbol.Tipo tipoEsquerda = currentExprType;
				        
				setState(140);
				((TermoAritmeticoContext)_localctx).e2 = fatorAritmetico();

				            // E usa os tipos corretos para a checagem.
				            currentExprType = checarTipoExpressao(tipoEsquerda, currentExprType);
				        
				}
				}
				setState(147);
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
			setState(158);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NumInt:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				match(NumInt);
				 
				            //  Define o tipo da expressão atual como INT
				            currentExprType = Symbol.Tipo.INT; 
				        
				}
				break;
			case NumReal:
				enterOuterAlt(_localctx, 2);
				{
				setState(150);
				((FatorAritmeticoContext)_localctx).NumReal = match(NumReal);
				 
				                       // Antes de definir o tipo, checamos a precisão do literal.
				            checarPrecisaoReal((((FatorAritmeticoContext)_localctx).NumReal!=null?((FatorAritmeticoContext)_localctx).NumReal.getText():null));
				            currentExprType = Symbol.Tipo.REAL; 
				        
				}
				break;
			case Var:
				enterOuterAlt(_localctx, 3);
				{
				setState(152);
				((FatorAritmeticoContext)_localctx).Var = match(Var);

				            String varName = (((FatorAritmeticoContext)_localctx).Var!=null?((FatorAritmeticoContext)_localctx).Var.getText():null);
				            verificarVar(varName); // Verifica se a variável foi declarada
				            if (SymbolTable.exists(varName)) {
				                // Define o tipo da expressão como o tipo da variável encontrada
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
				setState(154);
				match(AbrePar);
				setState(155);
				expressaoAritmetica();
				setState(156);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36\u00a3\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\7\3,\n\3\f\3\16\3/\13\3\3\4\3\4\3\4\3\4\5"+
		"\4\65\n\4\3\4\3\4\3\5\3\5\3\5\7\5<\n\5\f\5\16\5?\13\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\5\6G\n\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\5\tW\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\na\n\n\3\n\3\n\3\13\3"+
		"\13\3\13\7\13h\n\13\f\13\16\13k\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\5\fu\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\7\17\u0087\n\17\f\17\16\17\u008a\13\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\7\20\u0092\n\20\f\20\16\20\u0095\13\20\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00a1\n\21\3\21\2\2\22\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \2\4\3\2\21\22\3\2\17\20\2\u00a3\2\"\3"+
		"\2\2\2\4-\3\2\2\2\6\60\3\2\2\2\b=\3\2\2\2\nF\3\2\2\2\fH\3\2\2\2\16M\3"+
		"\2\2\2\20Q\3\2\2\2\22X\3\2\2\2\24d\3\2\2\2\26t\3\2\2\2\30v\3\2\2\2\32"+
		"|\3\2\2\2\34\u0080\3\2\2\2\36\u008b\3\2\2\2 \u00a0\3\2\2\2\"#\7\23\2\2"+
		"#$\7\16\2\2$%\5\4\3\2%&\7\23\2\2&\'\7\r\2\2\'(\5\b\5\2()\b\2\1\2)\3\3"+
		"\2\2\2*,\5\6\4\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\5\3\2\2\2/-"+
		"\3\2\2\2\60\61\7\31\2\2\61\64\7\23\2\2\62\65\7\3\2\2\63\65\7\5\2\2\64"+
		"\62\3\2\2\2\64\63\3\2\2\2\65\66\3\2\2\2\66\67\b\4\1\2\67\7\3\2\2\289\5"+
		"\n\6\29:\b\5\1\2:<\3\2\2\2;8\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>\t"+
		"\3\2\2\2?=\3\2\2\2@G\5\f\7\2AG\5\16\b\2BG\5\20\t\2CG\5\22\n\2DG\5\30\r"+
		"\2EG\5\32\16\2F@\3\2\2\2FA\3\2\2\2FB\3\2\2\2FC\3\2\2\2FD\3\2\2\2FE\3\2"+
		"\2\2G\13\3\2\2\2HI\7\31\2\2IJ\7\24\2\2JK\5\34\17\2KL\b\7\1\2L\r\3\2\2"+
		"\2MN\7\4\2\2NO\7\31\2\2OP\b\b\1\2P\17\3\2\2\2QV\7\6\2\2RS\7\31\2\2SW\b"+
		"\t\1\2TU\7\32\2\2UW\b\t\1\2VR\3\2\2\2VT\3\2\2\2W\21\3\2\2\2XY\7\7\2\2"+
		"YZ\5\24\13\2Z[\7\t\2\2[\\\b\n\1\2\\`\5\n\6\2]^\7\b\2\2^_\b\n\1\2_a\5\n"+
		"\6\2`]\3\2\2\2`a\3\2\2\2ab\3\2\2\2bc\b\n\1\2c\23\3\2\2\2di\5\26\f\2ef"+
		"\7\26\2\2fh\5\26\f\2ge\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\25\3\2\2"+
		"\2ki\3\2\2\2lm\5\34\17\2mn\7\25\2\2no\5\34\17\2ou\3\2\2\2pq\7\27\2\2q"+
		"r\5\24\13\2rs\7\30\2\2su\3\2\2\2tl\3\2\2\2tp\3\2\2\2u\27\3\2\2\2vw\7\n"+
		"\2\2wx\5\24\13\2xy\b\r\1\2yz\5\n\6\2z{\b\r\1\2{\31\3\2\2\2|}\7\13\2\2"+
		"}~\5\b\5\2~\177\7\f\2\2\177\33\3\2\2\2\u0080\u0088\5\36\20\2\u0081\u0082"+
		"\t\2\2\2\u0082\u0083\b\17\1\2\u0083\u0084\5\36\20\2\u0084\u0085\b\17\1"+
		"\2\u0085\u0087\3\2\2\2\u0086\u0081\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086"+
		"\3\2\2\2\u0088\u0089\3\2\2\2\u0089\35\3\2\2\2\u008a\u0088\3\2\2\2\u008b"+
		"\u0093\5 \21\2\u008c\u008d\t\3\2\2\u008d\u008e\b\20\1\2\u008e\u008f\5"+
		" \21\2\u008f\u0090\b\20\1\2\u0090\u0092\3\2\2\2\u0091\u008c\3\2\2\2\u0092"+
		"\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\37\3\2\2"+
		"\2\u0095\u0093\3\2\2\2\u0096\u0097\7\33\2\2\u0097\u00a1\b\21\1\2\u0098"+
		"\u0099\7\34\2\2\u0099\u00a1\b\21\1\2\u009a\u009b\7\31\2\2\u009b\u00a1"+
		"\b\21\1\2\u009c\u009d\7\27\2\2\u009d\u009e\5\34\17\2\u009e\u009f\7\30"+
		"\2\2\u009f\u00a1\3\2\2\2\u00a0\u0096\3\2\2\2\u00a0\u0098\3\2\2\2\u00a0"+
		"\u009a\3\2\2\2\u00a0\u009c\3\2\2\2\u00a1!\3\2\2\2\r-\64=FV`it\u0088\u0093"+
		"\u00a0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}