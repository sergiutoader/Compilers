// Generated from /home/student/Compilers/Tema1/out/production/Tema1/cool/parser/CoolParser.g4 by ANTLR 4.8

    package cool.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, STRING=2, LINE_COMMENT=3, BLOCK_COMMENT=4, UNMATCHED_COMMENT_END=5, 
		SELF=6, SELF_TYPE=7, CLASS=8, ELSE=9, FI=10, IF=11, IN=12, INHERITS=13, 
		ISVOID=14, LET=15, LOOP=16, POOL=17, THEN=18, WHILE=19, CASE=20, ESAC=21, 
		NEW=22, OF=23, NOT=24, TRUE=25, FALSE=26, LPAREN=27, RPAREN=28, LBRACE=29, 
		RBRACE=30, PLUS=31, MINUS=32, TILDE=33, STAR=34, SLASH=35, COMMA=36, SEMICOLON=37, 
		COLON=38, DOT=39, EQUALS=40, LT=41, LE=42, ASSIGN=43, ARROW=44, ATSIGN=45, 
		INT=46, TYPE_ID=47, OBJECT_ID=48, WS=49, UNMATCHED=50;
	public static final int
		RULE_program = 0, RULE_classs = 1, RULE_feature = 2, RULE_formal = 3, 
		RULE_expr = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "classs", "feature", "formal", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'*)'", "'self'", "'SELF_TYPE'", null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "'('", "')'", "'{'", "'}'", "'+'", 
			"'-'", "'~'", "'*'", "'/'", "','", "';'", "':'", "'.'", "'='", "'<'", 
			"'<='", "'<-'", "'=>'", "'@'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "STRING", "LINE_COMMENT", "BLOCK_COMMENT", "UNMATCHED_COMMENT_END", 
			"SELF", "SELF_TYPE", "CLASS", "ELSE", "FI", "IF", "IN", "INHERITS", "ISVOID", 
			"LET", "LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "NEW", "OF", 
			"NOT", "TRUE", "FALSE", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "PLUS", 
			"MINUS", "TILDE", "STAR", "SLASH", "COMMA", "SEMICOLON", "COLON", "DOT", 
			"EQUALS", "LT", "LE", "ASSIGN", "ARROW", "ATSIGN", "INT", "TYPE_ID", 
			"OBJECT_ID", "WS", "UNMATCHED"
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
	public String getGrammarFileName() { return "CoolParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CoolParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CoolParser.EOF, 0); }
		public List<ClasssContext> classs() {
			return getRuleContexts(ClasssContext.class);
		}
		public ClasssContext classs(int i) {
			return getRuleContext(ClasssContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(10);
				classs();
				setState(11);
				match(SEMICOLON);
				}
				}
				setState(15); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
			setState(17);
			match(EOF);
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

	public static class ClasssContext extends ParserRuleContext {
		public Token name;
		public Token parent_name;
		public TerminalNode CLASS() { return getToken(CoolParser.CLASS, 0); }
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public List<TerminalNode> TYPE_ID() { return getTokens(CoolParser.TYPE_ID); }
		public TerminalNode TYPE_ID(int i) {
			return getToken(CoolParser.TYPE_ID, i);
		}
		public TerminalNode INHERITS() { return getToken(CoolParser.INHERITS, 0); }
		public List<FeatureContext> feature() {
			return getRuleContexts(FeatureContext.class);
		}
		public FeatureContext feature(int i) {
			return getRuleContext(FeatureContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public ClasssContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterClasss(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitClasss(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitClasss(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClasssContext classs() throws RecognitionException {
		ClasssContext _localctx = new ClasssContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			match(CLASS);
			setState(20);
			((ClasssContext)_localctx).name = match(TYPE_ID);
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(21);
				match(INHERITS);
				setState(22);
				((ClasssContext)_localctx).parent_name = match(TYPE_ID);
				}
			}

			setState(25);
			match(LBRACE);
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OBJECT_ID) {
				{
				{
				setState(26);
				feature();
				setState(27);
				match(SEMICOLON);
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(34);
			match(RBRACE);
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

	public static class FeatureContext extends ParserRuleContext {
		public FormalContext formal;
		public List<FormalContext> formals = new ArrayList<FormalContext>();
		public TerminalNode OBJECT_ID() { return getToken(CoolParser.OBJECT_ID, 0); }
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode TYPE_ID() { return getToken(CoolParser.TYPE_ID, 0); }
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public List<FormalContext> formal() {
			return getRuleContexts(FormalContext.class);
		}
		public FormalContext formal(int i) {
			return getRuleContext(FormalContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public FeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFeature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFeature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFeature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeatureContext feature() throws RecognitionException {
		FeatureContext _localctx = new FeatureContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_feature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(OBJECT_ID);
			setState(37);
			match(LPAREN);
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OBJECT_ID) {
				{
				setState(38);
				((FeatureContext)_localctx).formal = formal();
				((FeatureContext)_localctx).formals.add(((FeatureContext)_localctx).formal);
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(39);
					match(COMMA);
					setState(40);
					((FeatureContext)_localctx).formal = formal();
					((FeatureContext)_localctx).formals.add(((FeatureContext)_localctx).formal);
					}
					}
					setState(45);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(48);
			match(RPAREN);
			setState(49);
			match(COLON);
			setState(50);
			match(TYPE_ID);
			setState(51);
			match(LBRACE);
			setState(52);
			expr(0);
			setState(53);
			match(RBRACE);
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

	public static class FormalContext extends ParserRuleContext {
		public TerminalNode OBJECT_ID() { return getToken(CoolParser.OBJECT_ID, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode TYPE_ID() { return getToken(CoolParser.TYPE_ID, 0); }
		public FormalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFormal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFormal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFormal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalContext formal() throws RecognitionException {
		FormalContext _localctx = new FormalContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_formal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(OBJECT_ID);
			setState(56);
			match(COLON);
			setState(57);
			match(TYPE_ID);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewContext extends ExprContext {
		public TerminalNode NEW() { return getToken(CoolParser.NEW, 0); }
		public TerminalNode TYPE_ID() { return getToken(CoolParser.TYPE_ID, 0); }
		public NewContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNew(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNew(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitNew(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringContext extends ExprContext {
		public TerminalNode STRING() { return getToken(CoolParser.STRING, 0); }
		public StringContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignmentContext extends ExprContext {
		public TerminalNode OBJECT_ID() { return getToken(CoolParser.OBJECT_ID, 0); }
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignmentContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsvoidContext extends ExprContext {
		public ExprContext e;
		public TerminalNode ISVOID() { return getToken(CoolParser.ISVOID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IsvoidContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterIsvoid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitIsvoid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitIsvoid(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FalseContext extends ExprContext {
		public TerminalNode FALSE() { return getToken(CoolParser.FALSE, 0); }
		public FalseContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFalse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArithmeticContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(CoolParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(CoolParser.MINUS, 0); }
		public TerminalNode STAR() { return getToken(CoolParser.STAR, 0); }
		public TerminalNode SLASH() { return getToken(CoolParser.SLASH, 0); }
		public ArithmeticContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterArithmetic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitArithmetic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitArithmetic(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileContext extends ExprContext {
		public ExprContext cond;
		public ExprContext body;
		public TerminalNode WHILE() { return getToken(CoolParser.WHILE, 0); }
		public TerminalNode LOOP() { return getToken(CoolParser.LOOP, 0); }
		public TerminalNode POOL() { return getToken(CoolParser.POOL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public WhileContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntContext extends ExprContext {
		public TerminalNode INT() { return getToken(CoolParser.INT, 0); }
		public IntContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegativeContext extends ExprContext {
		public TerminalNode TILDE() { return getToken(CoolParser.TILDE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NegativeContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNegative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNegative(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitNegative(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotContext extends ExprContext {
		public ExprContext e;
		public TerminalNode NOT() { return getToken(CoolParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenContext extends ExprContext {
		public ExprContext e;
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParenContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitParen(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TrueContext extends ExprContext {
		public TerminalNode TRUE() { return getToken(CoolParser.TRUE, 0); }
		public TrueContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterTrue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitTrue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitTrue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockContext extends ExprContext {
		public ExprContext expr;
		public List<ExprContext> subexpr = new ArrayList<ExprContext>();
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BlockContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LetContext extends ExprContext {
		public ExprContext expr;
		public List<ExprContext> expressions = new ArrayList<ExprContext>();
		public ExprContext body;
		public TerminalNode LET() { return getToken(CoolParser.LET, 0); }
		public List<TerminalNode> OBJECT_ID() { return getTokens(CoolParser.OBJECT_ID); }
		public TerminalNode OBJECT_ID(int i) {
			return getToken(CoolParser.OBJECT_ID, i);
		}
		public List<TerminalNode> COLON() { return getTokens(CoolParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(CoolParser.COLON, i);
		}
		public List<TerminalNode> TYPE_ID() { return getTokens(CoolParser.TYPE_ID); }
		public TerminalNode TYPE_ID(int i) {
			return getToken(CoolParser.TYPE_ID, i);
		}
		public TerminalNode IN() { return getToken(CoolParser.IN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> ASSIGN() { return getTokens(CoolParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(CoolParser.ASSIGN, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public LetContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterLet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitLet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitLet(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RelationalContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LT() { return getToken(CoolParser.LT, 0); }
		public TerminalNode LE() { return getToken(CoolParser.LE, 0); }
		public TerminalNode EQUALS() { return getToken(CoolParser.EQUALS, 0); }
		public RelationalContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterRelational(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitRelational(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitRelational(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdContext extends ExprContext {
		public TerminalNode OBJECT_ID() { return getToken(CoolParser.OBJECT_ID, 0); }
		public IdContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Specified_dispatchContext extends ExprContext {
		public ExprContext expr;
		public List<ExprContext> formal_params = new ArrayList<ExprContext>();
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode DOT() { return getToken(CoolParser.DOT, 0); }
		public TerminalNode OBJECT_ID() { return getToken(CoolParser.OBJECT_ID, 0); }
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public TerminalNode ATSIGN() { return getToken(CoolParser.ATSIGN, 0); }
		public TerminalNode TYPE_ID() { return getToken(CoolParser.TYPE_ID, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public Specified_dispatchContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterSpecified_dispatch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitSpecified_dispatch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitSpecified_dispatch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Unspecified_dispatchContext extends ExprContext {
		public ExprContext expr;
		public List<ExprContext> formal_params = new ArrayList<ExprContext>();
		public TerminalNode OBJECT_ID() { return getToken(CoolParser.OBJECT_ID, 0); }
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public Unspecified_dispatchContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterUnspecified_dispatch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitUnspecified_dispatch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitUnspecified_dispatch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfContext extends ExprContext {
		public ExprContext cond;
		public ExprContext thenBranch;
		public ExprContext elseBranch;
		public TerminalNode IF() { return getToken(CoolParser.IF, 0); }
		public TerminalNode THEN() { return getToken(CoolParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(CoolParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(CoolParser.FI, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IfContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CaseContext extends ExprContext {
		public ExprContext ref_expr;
		public ExprContext expr;
		public List<ExprContext> case_expr = new ArrayList<ExprContext>();
		public TerminalNode CASE() { return getToken(CoolParser.CASE, 0); }
		public TerminalNode OF() { return getToken(CoolParser.OF, 0); }
		public TerminalNode ESAC() { return getToken(CoolParser.ESAC, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> OBJECT_ID() { return getTokens(CoolParser.OBJECT_ID); }
		public TerminalNode OBJECT_ID(int i) {
			return getToken(CoolParser.OBJECT_ID, i);
		}
		public List<TerminalNode> COLON() { return getTokens(CoolParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(CoolParser.COLON, i);
		}
		public List<TerminalNode> TYPE_ID() { return getTokens(CoolParser.TYPE_ID); }
		public TerminalNode TYPE_ID(int i) {
			return getToken(CoolParser.TYPE_ID, i);
		}
		public List<TerminalNode> ARROW() { return getTokens(CoolParser.ARROW); }
		public TerminalNode ARROW(int i) {
			return getToken(CoolParser.ARROW, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public CaseContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitCase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitCase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				_localctx = new AssignmentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(60);
				match(OBJECT_ID);
				setState(61);
				match(ASSIGN);
				setState(62);
				expr(20);
				}
				break;
			case 2:
				{
				_localctx = new Unspecified_dispatchContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(63);
				match(OBJECT_ID);
				setState(64);
				match(LPAREN);
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << IF) | (1L << ISVOID) | (1L << LET) | (1L << WHILE) | (1L << CASE) | (1L << NEW) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << LPAREN) | (1L << LBRACE) | (1L << TILDE) | (1L << INT) | (1L << OBJECT_ID))) != 0)) {
					{
					setState(65);
					((Unspecified_dispatchContext)_localctx).expr = expr(0);
					((Unspecified_dispatchContext)_localctx).formal_params.add(((Unspecified_dispatchContext)_localctx).expr);
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(66);
						match(COMMA);
						setState(67);
						((Unspecified_dispatchContext)_localctx).expr = expr(0);
						((Unspecified_dispatchContext)_localctx).formal_params.add(((Unspecified_dispatchContext)_localctx).expr);
						}
						}
						setState(72);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(75);
				match(RPAREN);
				}
				break;
			case 3:
				{
				_localctx = new IfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(76);
				match(IF);
				setState(77);
				((IfContext)_localctx).cond = expr(0);
				setState(78);
				match(THEN);
				setState(79);
				((IfContext)_localctx).thenBranch = expr(0);
				setState(80);
				match(ELSE);
				setState(81);
				((IfContext)_localctx).elseBranch = expr(0);
				setState(82);
				match(FI);
				}
				break;
			case 4:
				{
				_localctx = new WhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(84);
				match(WHILE);
				setState(85);
				((WhileContext)_localctx).cond = expr(0);
				setState(86);
				match(LOOP);
				setState(87);
				((WhileContext)_localctx).body = expr(0);
				setState(88);
				match(POOL);
				}
				break;
			case 5:
				{
				_localctx = new BlockContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(90);
				match(LBRACE);
				setState(94); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(91);
					((BlockContext)_localctx).expr = expr(0);
					((BlockContext)_localctx).subexpr.add(((BlockContext)_localctx).expr);
					setState(92);
					match(COMMA);
					}
					}
					setState(96); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << IF) | (1L << ISVOID) | (1L << LET) | (1L << WHILE) | (1L << CASE) | (1L << NEW) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << LPAREN) | (1L << LBRACE) | (1L << TILDE) | (1L << INT) | (1L << OBJECT_ID))) != 0) );
				setState(98);
				match(RBRACE);
				}
				break;
			case 6:
				{
				_localctx = new LetContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(100);
				match(LET);
				setState(101);
				match(OBJECT_ID);
				setState(102);
				match(COLON);
				setState(103);
				match(TYPE_ID);
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(104);
					match(ASSIGN);
					setState(105);
					((LetContext)_localctx).expr = expr(0);
					((LetContext)_localctx).expressions.add(((LetContext)_localctx).expr);
					}
				}

				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(108);
					match(COMMA);
					setState(109);
					match(OBJECT_ID);
					setState(110);
					match(COLON);
					setState(111);
					match(TYPE_ID);
					setState(114);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(112);
						match(ASSIGN);
						setState(113);
						((LetContext)_localctx).expr = expr(0);
						((LetContext)_localctx).expressions.add(((LetContext)_localctx).expr);
						}
					}

					}
					}
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(121);
				match(IN);
				setState(122);
				((LetContext)_localctx).body = expr(14);
				}
				break;
			case 7:
				{
				_localctx = new CaseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(123);
				match(CASE);
				setState(124);
				((CaseContext)_localctx).ref_expr = expr(0);
				setState(125);
				match(OF);
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(126);
					match(OBJECT_ID);
					setState(127);
					match(COLON);
					setState(128);
					match(TYPE_ID);
					setState(129);
					match(ARROW);
					setState(130);
					((CaseContext)_localctx).expr = expr(0);
					((CaseContext)_localctx).case_expr.add(((CaseContext)_localctx).expr);
					setState(131);
					match(COMMA);
					}
					}
					setState(135); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==OBJECT_ID );
				setState(137);
				match(ESAC);
				}
				break;
			case 8:
				{
				_localctx = new NewContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(139);
				match(NEW);
				setState(140);
				match(TYPE_ID);
				}
				break;
			case 9:
				{
				_localctx = new IsvoidContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141);
				match(ISVOID);
				setState(142);
				((IsvoidContext)_localctx).e = expr(11);
				}
				break;
			case 10:
				{
				_localctx = new NegativeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(143);
				match(TILDE);
				setState(144);
				expr(9);
				}
				break;
			case 11:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145);
				match(NOT);
				setState(146);
				((NotContext)_localctx).e = expr(7);
				}
				break;
			case 12:
				{
				_localctx = new ParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(147);
				match(LPAREN);
				setState(148);
				((ParenContext)_localctx).e = expr(0);
				setState(149);
				match(RPAREN);
				}
				break;
			case 13:
				{
				_localctx = new IdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(151);
				match(OBJECT_ID);
				}
				break;
			case 14:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(152);
				match(INT);
				}
				break;
			case 15:
				{
				_localctx = new StringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(153);
				match(STRING);
				}
				break;
			case 16:
				{
				_localctx = new TrueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(154);
				match(TRUE);
				}
				break;
			case 17:
				{
				_localctx = new FalseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(155);
				match(FALSE);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(185);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(183);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmeticContext(new ExprContext(_parentctx, _parentState));
						((ArithmeticContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(158);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(159);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(160);
						((ArithmeticContext)_localctx).right = expr(11);
						}
						break;
					case 2:
						{
						_localctx = new RelationalContext(new ExprContext(_parentctx, _parentState));
						((RelationalContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(161);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(162);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUALS) | (1L << LT) | (1L << LE))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(163);
						((RelationalContext)_localctx).right = expr(9);
						}
						break;
					case 3:
						{
						_localctx = new Specified_dispatchContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(164);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(167);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==ATSIGN) {
							{
							setState(165);
							match(ATSIGN);
							setState(166);
							match(TYPE_ID);
							}
						}

						setState(169);
						match(DOT);
						setState(170);
						match(OBJECT_ID);
						setState(171);
						match(LPAREN);
						setState(180);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << IF) | (1L << ISVOID) | (1L << LET) | (1L << WHILE) | (1L << CASE) | (1L << NEW) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << LPAREN) | (1L << LBRACE) | (1L << TILDE) | (1L << INT) | (1L << OBJECT_ID))) != 0)) {
							{
							setState(172);
							((Specified_dispatchContext)_localctx).expr = expr(0);
							((Specified_dispatchContext)_localctx).formal_params.add(((Specified_dispatchContext)_localctx).expr);
							setState(177);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==COMMA) {
								{
								{
								setState(173);
								match(COMMA);
								setState(174);
								((Specified_dispatchContext)_localctx).expr = expr(0);
								((Specified_dispatchContext)_localctx).formal_params.add(((Specified_dispatchContext)_localctx).expr);
								}
								}
								setState(179);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(182);
						match(RPAREN);
						}
						break;
					}
					} 
				}
				setState(187);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 10);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 19);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\64\u00bf\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\6\2\20\n\2\r\2\16\2\21\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\5\3\32\n\3\3\3\3\3\3\3\3\3\7\3 \n\3\f\3\16\3#\13"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\7\4,\n\4\f\4\16\4/\13\4\5\4\61\n\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\7\6G\n\6\f\6\16\6J\13\6\5\6L\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\6\6a\n\6\r\6\16\6b\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6m\n\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6u\n\6"+
		"\7\6w\n\6\f\6\16\6z\13\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\6\6\u0088\n\6\r\6\16\6\u0089\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u009f\n\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\5\6\u00aa\n\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u00b2\n\6"+
		"\f\6\16\6\u00b5\13\6\5\6\u00b7\n\6\3\6\7\6\u00ba\n\6\f\6\16\6\u00bd\13"+
		"\6\3\6\2\3\n\7\2\4\6\b\n\2\4\4\2!\"$%\3\2*,\2\u00db\2\17\3\2\2\2\4\25"+
		"\3\2\2\2\6&\3\2\2\2\b9\3\2\2\2\n\u009e\3\2\2\2\f\r\5\4\3\2\r\16\7\'\2"+
		"\2\16\20\3\2\2\2\17\f\3\2\2\2\20\21\3\2\2\2\21\17\3\2\2\2\21\22\3\2\2"+
		"\2\22\23\3\2\2\2\23\24\7\2\2\3\24\3\3\2\2\2\25\26\7\n\2\2\26\31\7\61\2"+
		"\2\27\30\7\17\2\2\30\32\7\61\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32\33\3\2"+
		"\2\2\33!\7\37\2\2\34\35\5\6\4\2\35\36\7\'\2\2\36 \3\2\2\2\37\34\3\2\2"+
		"\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"$\3\2\2\2#!\3\2\2\2$%\7 \2\2%\5\3"+
		"\2\2\2&\'\7\62\2\2\'\60\7\35\2\2(-\5\b\5\2)*\7&\2\2*,\5\b\5\2+)\3\2\2"+
		"\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\61\3\2\2\2/-\3\2\2\2\60(\3\2\2\2\60"+
		"\61\3\2\2\2\61\62\3\2\2\2\62\63\7\36\2\2\63\64\7(\2\2\64\65\7\61\2\2\65"+
		"\66\7\37\2\2\66\67\5\n\6\2\678\7 \2\28\7\3\2\2\29:\7\62\2\2:;\7(\2\2;"+
		"<\7\61\2\2<\t\3\2\2\2=>\b\6\1\2>?\7\62\2\2?@\7-\2\2@\u009f\5\n\6\26AB"+
		"\7\62\2\2BK\7\35\2\2CH\5\n\6\2DE\7&\2\2EG\5\n\6\2FD\3\2\2\2GJ\3\2\2\2"+
		"HF\3\2\2\2HI\3\2\2\2IL\3\2\2\2JH\3\2\2\2KC\3\2\2\2KL\3\2\2\2LM\3\2\2\2"+
		"M\u009f\7\36\2\2NO\7\r\2\2OP\5\n\6\2PQ\7\24\2\2QR\5\n\6\2RS\7\13\2\2S"+
		"T\5\n\6\2TU\7\f\2\2U\u009f\3\2\2\2VW\7\25\2\2WX\5\n\6\2XY\7\22\2\2YZ\5"+
		"\n\6\2Z[\7\23\2\2[\u009f\3\2\2\2\\`\7\37\2\2]^\5\n\6\2^_\7&\2\2_a\3\2"+
		"\2\2`]\3\2\2\2ab\3\2\2\2b`\3\2\2\2bc\3\2\2\2cd\3\2\2\2de\7 \2\2e\u009f"+
		"\3\2\2\2fg\7\21\2\2gh\7\62\2\2hi\7(\2\2il\7\61\2\2jk\7-\2\2km\5\n\6\2"+
		"lj\3\2\2\2lm\3\2\2\2mx\3\2\2\2no\7&\2\2op\7\62\2\2pq\7(\2\2qt\7\61\2\2"+
		"rs\7-\2\2su\5\n\6\2tr\3\2\2\2tu\3\2\2\2uw\3\2\2\2vn\3\2\2\2wz\3\2\2\2"+
		"xv\3\2\2\2xy\3\2\2\2y{\3\2\2\2zx\3\2\2\2{|\7\16\2\2|\u009f\5\n\6\20}~"+
		"\7\26\2\2~\177\5\n\6\2\177\u0087\7\31\2\2\u0080\u0081\7\62\2\2\u0081\u0082"+
		"\7(\2\2\u0082\u0083\7\61\2\2\u0083\u0084\7.\2\2\u0084\u0085\5\n\6\2\u0085"+
		"\u0086\7&\2\2\u0086\u0088\3\2\2\2\u0087\u0080\3\2\2\2\u0088\u0089\3\2"+
		"\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u008c\7\27\2\2\u008c\u009f\3\2\2\2\u008d\u008e\7\30\2\2\u008e\u009f\7"+
		"\61\2\2\u008f\u0090\7\20\2\2\u0090\u009f\5\n\6\r\u0091\u0092\7#\2\2\u0092"+
		"\u009f\5\n\6\13\u0093\u0094\7\32\2\2\u0094\u009f\5\n\6\t\u0095\u0096\7"+
		"\35\2\2\u0096\u0097\5\n\6\2\u0097\u0098\7\36\2\2\u0098\u009f\3\2\2\2\u0099"+
		"\u009f\7\62\2\2\u009a\u009f\7\60\2\2\u009b\u009f\7\4\2\2\u009c\u009f\7"+
		"\33\2\2\u009d\u009f\7\34\2\2\u009e=\3\2\2\2\u009eA\3\2\2\2\u009eN\3\2"+
		"\2\2\u009eV\3\2\2\2\u009e\\\3\2\2\2\u009ef\3\2\2\2\u009e}\3\2\2\2\u009e"+
		"\u008d\3\2\2\2\u009e\u008f\3\2\2\2\u009e\u0091\3\2\2\2\u009e\u0093\3\2"+
		"\2\2\u009e\u0095\3\2\2\2\u009e\u0099\3\2\2\2\u009e\u009a\3\2\2\2\u009e"+
		"\u009b\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009d\3\2\2\2\u009f\u00bb\3\2"+
		"\2\2\u00a0\u00a1\f\f\2\2\u00a1\u00a2\t\2\2\2\u00a2\u00ba\5\n\6\r\u00a3"+
		"\u00a4\f\n\2\2\u00a4\u00a5\t\3\2\2\u00a5\u00ba\5\n\6\13\u00a6\u00a9\f"+
		"\25\2\2\u00a7\u00a8\7/\2\2\u00a8\u00aa\7\61\2\2\u00a9\u00a7\3\2\2\2\u00a9"+
		"\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\7)\2\2\u00ac\u00ad\7\62"+
		"\2\2\u00ad\u00b6\7\35\2\2\u00ae\u00b3\5\n\6\2\u00af\u00b0\7&\2\2\u00b0"+
		"\u00b2\5\n\6\2\u00b1\u00af\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2"+
		"\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6"+
		"\u00ae\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\7\36"+
		"\2\2\u00b9\u00a0\3\2\2\2\u00b9\u00a3\3\2\2\2\u00b9\u00a6\3\2\2\2\u00ba"+
		"\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\13\3\2\2"+
		"\2\u00bd\u00bb\3\2\2\2\24\21\31!-\60HKbltx\u0089\u009e\u00a9\u00b3\u00b6"+
		"\u00b9\u00bb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}