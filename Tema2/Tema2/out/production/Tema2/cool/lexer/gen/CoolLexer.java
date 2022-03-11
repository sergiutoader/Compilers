// Generated from /home/student/Compilers/Tema2/Tema2/out/production/Tema2/cool/lexer/CoolLexer.g4 by ANTLR 4.8

    package cool.lexer;	

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, STRING=2, LINE_COMMENT=3, BLOCK_COMMENT=4, UNMATCHED_COMMENT_END=5, 
		CLASS=6, ELSE=7, FI=8, IF=9, IN=10, INHERITS=11, ISVOID=12, LET=13, LOOP=14, 
		POOL=15, THEN=16, WHILE=17, CASE=18, ESAC=19, NEW=20, OF=21, NOT=22, TRUE=23, 
		FALSE=24, LPAREN=25, RPAREN=26, LBRACE=27, RBRACE=28, PLUS=29, MINUS=30, 
		TILDE=31, STAR=32, SLASH=33, COMMA=34, SEMICOLON=35, COLON=36, DOT=37, 
		EQUALS=38, LT=39, LE=40, ASSIGN=41, ARROW=42, ATSIGN=43, INT=44, TYPE=45, 
		OBJECT_ID=46, WS=47, UNMATCHED=48;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"NEW_LINE", "STRING", "LINE_COMMENT", "BLOCK_COMMENT", "UNMATCHED_COMMENT_END", 
			"SELF", "SELF_TYPE", "CLASS", "ELSE", "FI", "IF", "IN", "INHERITS", "ISVOID", 
			"LET", "LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "NEW", "OF", 
			"NOT", "TRUE", "FALSE", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "PLUS", 
			"MINUS", "TILDE", "STAR", "SLASH", "COMMA", "SEMICOLON", "COLON", "DOT", 
			"EQUALS", "LT", "LE", "ASSIGN", "ARROW", "ATSIGN", "DIGIT", "INT", "LOWERCASE_LETTER", 
			"UPPERCASE_LETTER", "LETTER", "TYPE_ID", "TYPE", "OBJECT_ID", "WS", "UNMATCHED"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'*)'", null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "'('", "')'", "'{'", "'}'", "'+'", "'-'", "'~'", "'*'", "'/'", 
			"','", "';'", "':'", "'.'", "'='", "'<'", "'<='", "'<-'", "'=>'", "'@'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "STRING", "LINE_COMMENT", "BLOCK_COMMENT", "UNMATCHED_COMMENT_END", 
			"CLASS", "ELSE", "FI", "IF", "IN", "INHERITS", "ISVOID", "LET", "LOOP", 
			"POOL", "THEN", "WHILE", "CASE", "ESAC", "NEW", "OF", "NOT", "TRUE", 
			"FALSE", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "PLUS", "MINUS", "TILDE", 
			"STAR", "SLASH", "COMMA", "SEMICOLON", "COLON", "DOT", "EQUALS", "LT", 
			"LE", "ASSIGN", "ARROW", "ATSIGN", "INT", "TYPE", "OBJECT_ID", "WS", 
			"UNMATCHED"
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

	    
	    private void raiseError(String msg) {
	        setText(msg);
	        setType(ERROR);
	    }

	    private void getString() {
	        String oldText = getText();

	        String newText = oldText.substring(1, oldText.length() - 1);

	        newText = newText.replace("\\\r\n", "\r\n")
	                        .replace("\\n", "\n")
	                        .replace("\\t", "\t")
	                        .replace("\\b", "\b")
	                        .replace("\\f", "\f");

	        for(int i = 0; i < newText.length() - 1;) {
	            if(newText.charAt(i) == '\\' && newText.charAt(i+1) != '\\') {
	                newText = newText.substring(0, i) + newText.substring(i+1);
	            } else {
	                i++;
	            }
	        }

	        newText = newText.replace("\\\\", "\\");

	        if (newText.length() > 1024) {
	            raiseError("String constant too long");
	        } else if (newText.contains("\u0000")) {
	            raiseError("String contains null character");
	        } else {
	         setText(newText);
	        }
	    }


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

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

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 1:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 3:
			BLOCK_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 4:
			UNMATCHED_COMMENT_END_action((RuleContext)_localctx, actionIndex);
			break;
		case 54:
			UNMATCHED_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 getString(); 
			break;
		case 1:
			 raiseError("Unterminated string constant"); 
			break;
		case 2:
			 raiseError("EOF in string constant"); 
			break;
		}
	}
	private void BLOCK_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			 skip(); 
			break;
		case 4:
			 raiseError("EOF in comment"); 
			break;
		}
	}
	private void UNMATCHED_COMMENT_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			 raiseError("Unmatched *)"); 
			break;
		}
	}
	private void UNMATCHED_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:

			    raiseError("Invalid character: " + getText());

			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\62\u0175\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\3\2\5\2s\n\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\7\3{\n\3\f\3\16\3~\13\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\u0087"+
		"\n\3\3\4\3\4\3\4\3\4\7\4\u008d\n\4\f\4\16\4\u0090\13\4\3\4\3\4\5\4\u0094"+
		"\n\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\7\5\u009d\n\5\f\5\16\5\u00a0\13\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\5\5\u00a8\n\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3"+
		"\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3"+
		"\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#"+
		"\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3"+
		"-\3.\3.\3/\3/\3\60\6\60\u0147\n\60\r\60\16\60\u0148\3\61\3\61\3\62\3\62"+
		"\3\63\3\63\5\63\u0151\n\63\3\64\3\64\3\64\3\64\7\64\u0157\n\64\f\64\16"+
		"\64\u015a\13\64\3\65\3\65\5\65\u015e\n\65\3\66\3\66\3\66\3\66\7\66\u0164"+
		"\n\66\f\66\16\66\u0167\13\66\3\66\5\66\u016a\n\66\3\67\6\67\u016d\n\67"+
		"\r\67\16\67\u016e\3\67\3\67\38\38\38\5|\u008e\u009e\29\3\2\5\4\7\5\t\6"+
		"\13\7\r\2\17\2\21\b\23\t\25\n\27\13\31\f\33\r\35\16\37\17!\20#\21%\22"+
		"\'\23)\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35=\36?\37A C!E\"G"+
		"#I$K%M&O\'Q(S)U*W+Y,[-]\2_.a\2c\2e\2g\2i/k\60m\61o\62\3\2\27\4\2EEee\4"+
		"\2NNnn\4\2CCcc\4\2UUuu\4\2GGgg\4\2HHhh\4\2KKkk\4\2PPpp\4\2JJjj\4\2TTt"+
		"t\4\2VVvv\4\2XXxx\4\2QQqq\4\2FFff\4\2RRrr\4\2YYyy\4\2WWww\3\2\62;\3\2"+
		"c|\3\2C\\\5\2\13\f\16\17\"\"\2\u0181\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U"+
		"\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2_\3\2\2\2\2i\3\2\2\2\2k\3\2"+
		"\2\2\2m\3\2\2\2\2o\3\2\2\2\3r\3\2\2\2\5v\3\2\2\2\7\u0088\3\2\2\2\t\u0097"+
		"\3\2\2\2\13\u00a9\3\2\2\2\r\u00ae\3\2\2\2\17\u00b3\3\2\2\2\21\u00bd\3"+
		"\2\2\2\23\u00c3\3\2\2\2\25\u00c8\3\2\2\2\27\u00cb\3\2\2\2\31\u00ce\3\2"+
		"\2\2\33\u00d1\3\2\2\2\35\u00da\3\2\2\2\37\u00e1\3\2\2\2!\u00e5\3\2\2\2"+
		"#\u00ea\3\2\2\2%\u00ef\3\2\2\2\'\u00f4\3\2\2\2)\u00fa\3\2\2\2+\u00ff\3"+
		"\2\2\2-\u0104\3\2\2\2/\u0108\3\2\2\2\61\u010b\3\2\2\2\63\u010f\3\2\2\2"+
		"\65\u0114\3\2\2\2\67\u011a\3\2\2\29\u011c\3\2\2\2;\u011e\3\2\2\2=\u0120"+
		"\3\2\2\2?\u0122\3\2\2\2A\u0124\3\2\2\2C\u0126\3\2\2\2E\u0128\3\2\2\2G"+
		"\u012a\3\2\2\2I\u012c\3\2\2\2K\u012e\3\2\2\2M\u0130\3\2\2\2O\u0132\3\2"+
		"\2\2Q\u0134\3\2\2\2S\u0136\3\2\2\2U\u0138\3\2\2\2W\u013b\3\2\2\2Y\u013e"+
		"\3\2\2\2[\u0141\3\2\2\2]\u0143\3\2\2\2_\u0146\3\2\2\2a\u014a\3\2\2\2c"+
		"\u014c\3\2\2\2e\u0150\3\2\2\2g\u0152\3\2\2\2i\u015d\3\2\2\2k\u0169\3\2"+
		"\2\2m\u016c\3\2\2\2o\u0172\3\2\2\2qs\7\17\2\2rq\3\2\2\2rs\3\2\2\2st\3"+
		"\2\2\2tu\7\f\2\2u\4\3\2\2\2v|\7$\2\2wx\7^\2\2x{\5\3\2\2y{\13\2\2\2zw\3"+
		"\2\2\2zy\3\2\2\2{~\3\2\2\2|}\3\2\2\2|z\3\2\2\2}\u0086\3\2\2\2~|\3\2\2"+
		"\2\177\u0080\7$\2\2\u0080\u0087\b\3\2\2\u0081\u0082\5\3\2\2\u0082\u0083"+
		"\b\3\3\2\u0083\u0087\3\2\2\2\u0084\u0085\7\2\2\3\u0085\u0087\b\3\4\2\u0086"+
		"\177\3\2\2\2\u0086\u0081\3\2\2\2\u0086\u0084\3\2\2\2\u0087\6\3\2\2\2\u0088"+
		"\u0089\7/\2\2\u0089\u008a\7/\2\2\u008a\u008e\3\2\2\2\u008b\u008d\13\2"+
		"\2\2\u008c\u008b\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008f\3\2\2\2\u008e"+
		"\u008c\3\2\2\2\u008f\u0093\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0094\5\3"+
		"\2\2\u0092\u0094\7\2\2\3\u0093\u0091\3\2\2\2\u0093\u0092\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\u0096\b\4\5\2\u0096\b\3\2\2\2\u0097\u0098\7*\2\2"+
		"\u0098\u0099\7,\2\2\u0099\u009e\3\2\2\2\u009a\u009d\5\t\5\2\u009b\u009d"+
		"\13\2\2\2\u009c\u009a\3\2\2\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2"+
		"\u009e\u009f\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a7\3\2\2\2\u00a0\u009e"+
		"\3\2\2\2\u00a1\u00a2\7,\2\2\u00a2\u00a3\7+\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\u00a8\b\5\6\2\u00a5\u00a6\7\2\2\3\u00a6\u00a8\b\5\7\2\u00a7\u00a1\3\2"+
		"\2\2\u00a7\u00a5\3\2\2\2\u00a8\n\3\2\2\2\u00a9\u00aa\7,\2\2\u00aa\u00ab"+
		"\7+\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\b\6\b\2\u00ad\f\3\2\2\2\u00ae"+
		"\u00af\7u\2\2\u00af\u00b0\7g\2\2\u00b0\u00b1\7n\2\2\u00b1\u00b2\7h\2\2"+
		"\u00b2\16\3\2\2\2\u00b3\u00b4\7U\2\2\u00b4\u00b5\7G\2\2\u00b5\u00b6\7"+
		"N\2\2\u00b6\u00b7\7H\2\2\u00b7\u00b8\7a\2\2\u00b8\u00b9\7V\2\2\u00b9\u00ba"+
		"\7[\2\2\u00ba\u00bb\7R\2\2\u00bb\u00bc\7G\2\2\u00bc\20\3\2\2\2\u00bd\u00be"+
		"\t\2\2\2\u00be\u00bf\t\3\2\2\u00bf\u00c0\t\4\2\2\u00c0\u00c1\t\5\2\2\u00c1"+
		"\u00c2\t\5\2\2\u00c2\22\3\2\2\2\u00c3\u00c4\t\6\2\2\u00c4\u00c5\t\3\2"+
		"\2\u00c5\u00c6\t\5\2\2\u00c6\u00c7\t\6\2\2\u00c7\24\3\2\2\2\u00c8\u00c9"+
		"\t\7\2\2\u00c9\u00ca\t\b\2\2\u00ca\26\3\2\2\2\u00cb\u00cc\t\b\2\2\u00cc"+
		"\u00cd\t\7\2\2\u00cd\30\3\2\2\2\u00ce\u00cf\t\b\2\2\u00cf\u00d0\t\t\2"+
		"\2\u00d0\32\3\2\2\2\u00d1\u00d2\t\b\2\2\u00d2\u00d3\t\t\2\2\u00d3\u00d4"+
		"\t\n\2\2\u00d4\u00d5\t\6\2\2\u00d5\u00d6\t\13\2\2\u00d6\u00d7\t\b\2\2"+
		"\u00d7\u00d8\t\f\2\2\u00d8\u00d9\t\5\2\2\u00d9\34\3\2\2\2\u00da\u00db"+
		"\t\b\2\2\u00db\u00dc\t\5\2\2\u00dc\u00dd\t\r\2\2\u00dd\u00de\t\16\2\2"+
		"\u00de\u00df\t\b\2\2\u00df\u00e0\t\17\2\2\u00e0\36\3\2\2\2\u00e1\u00e2"+
		"\t\3\2\2\u00e2\u00e3\t\6\2\2\u00e3\u00e4\t\f\2\2\u00e4 \3\2\2\2\u00e5"+
		"\u00e6\t\3\2\2\u00e6\u00e7\t\16\2\2\u00e7\u00e8\t\16\2\2\u00e8\u00e9\t"+
		"\20\2\2\u00e9\"\3\2\2\2\u00ea\u00eb\t\20\2\2\u00eb\u00ec\t\16\2\2\u00ec"+
		"\u00ed\t\16\2\2\u00ed\u00ee\t\3\2\2\u00ee$\3\2\2\2\u00ef\u00f0\t\f\2\2"+
		"\u00f0\u00f1\t\n\2\2\u00f1\u00f2\t\6\2\2\u00f2\u00f3\t\t\2\2\u00f3&\3"+
		"\2\2\2\u00f4\u00f5\t\21\2\2\u00f5\u00f6\t\n\2\2\u00f6\u00f7\t\b\2\2\u00f7"+
		"\u00f8\t\3\2\2\u00f8\u00f9\t\6\2\2\u00f9(\3\2\2\2\u00fa\u00fb\t\2\2\2"+
		"\u00fb\u00fc\t\4\2\2\u00fc\u00fd\t\5\2\2\u00fd\u00fe\t\6\2\2\u00fe*\3"+
		"\2\2\2\u00ff\u0100\t\6\2\2\u0100\u0101\t\5\2\2\u0101\u0102\t\4\2\2\u0102"+
		"\u0103\t\2\2\2\u0103,\3\2\2\2\u0104\u0105\t\t\2\2\u0105\u0106\t\6\2\2"+
		"\u0106\u0107\t\21\2\2\u0107.\3\2\2\2\u0108\u0109\t\16\2\2\u0109\u010a"+
		"\t\7\2\2\u010a\60\3\2\2\2\u010b\u010c\t\t\2\2\u010c\u010d\t\16\2\2\u010d"+
		"\u010e\t\f\2\2\u010e\62\3\2\2\2\u010f\u0110\7v\2\2\u0110\u0111\t\13\2"+
		"\2\u0111\u0112\t\22\2\2\u0112\u0113\t\6\2\2\u0113\64\3\2\2\2\u0114\u0115"+
		"\7h\2\2\u0115\u0116\t\4\2\2\u0116\u0117\t\3\2\2\u0117\u0118\t\5\2\2\u0118"+
		"\u0119\t\6\2\2\u0119\66\3\2\2\2\u011a\u011b\7*\2\2\u011b8\3\2\2\2\u011c"+
		"\u011d\7+\2\2\u011d:\3\2\2\2\u011e\u011f\7}\2\2\u011f<\3\2\2\2\u0120\u0121"+
		"\7\177\2\2\u0121>\3\2\2\2\u0122\u0123\7-\2\2\u0123@\3\2\2\2\u0124\u0125"+
		"\7/\2\2\u0125B\3\2\2\2\u0126\u0127\7\u0080\2\2\u0127D\3\2\2\2\u0128\u0129"+
		"\7,\2\2\u0129F\3\2\2\2\u012a\u012b\7\61\2\2\u012bH\3\2\2\2\u012c\u012d"+
		"\7.\2\2\u012dJ\3\2\2\2\u012e\u012f\7=\2\2\u012fL\3\2\2\2\u0130\u0131\7"+
		"<\2\2\u0131N\3\2\2\2\u0132\u0133\7\60\2\2\u0133P\3\2\2\2\u0134\u0135\7"+
		"?\2\2\u0135R\3\2\2\2\u0136\u0137\7>\2\2\u0137T\3\2\2\2\u0138\u0139\7>"+
		"\2\2\u0139\u013a\7?\2\2\u013aV\3\2\2\2\u013b\u013c\7>\2\2\u013c\u013d"+
		"\7/\2\2\u013dX\3\2\2\2\u013e\u013f\7?\2\2\u013f\u0140\7@\2\2\u0140Z\3"+
		"\2\2\2\u0141\u0142\7B\2\2\u0142\\\3\2\2\2\u0143\u0144\t\23\2\2\u0144^"+
		"\3\2\2\2\u0145\u0147\5]/\2\u0146\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148"+
		"\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149`\3\2\2\2\u014a\u014b\t\24\2\2"+
		"\u014bb\3\2\2\2\u014c\u014d\t\25\2\2\u014dd\3\2\2\2\u014e\u0151\5a\61"+
		"\2\u014f\u0151\5c\62\2\u0150\u014e\3\2\2\2\u0150\u014f\3\2\2\2\u0151f"+
		"\3\2\2\2\u0152\u0158\5c\62\2\u0153\u0157\5e\63\2\u0154\u0157\5]/\2\u0155"+
		"\u0157\7a\2\2\u0156\u0153\3\2\2\2\u0156\u0154\3\2\2\2\u0156\u0155\3\2"+
		"\2\2\u0157\u015a\3\2\2\2\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159"+
		"h\3\2\2\2\u015a\u0158\3\2\2\2\u015b\u015e\5g\64\2\u015c\u015e\5\17\b\2"+
		"\u015d\u015b\3\2\2\2\u015d\u015c\3\2\2\2\u015ej\3\2\2\2\u015f\u0165\5"+
		"a\61\2\u0160\u0164\5e\63\2\u0161\u0164\5]/\2\u0162\u0164\7a\2\2\u0163"+
		"\u0160\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0162\3\2\2\2\u0164\u0167\3\2"+
		"\2\2\u0165\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u016a\3\2\2\2\u0167"+
		"\u0165\3\2\2\2\u0168\u016a\5\r\7\2\u0169\u015f\3\2\2\2\u0169\u0168\3\2"+
		"\2\2\u016al\3\2\2\2\u016b\u016d\t\26\2\2\u016c\u016b\3\2\2\2\u016d\u016e"+
		"\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0170\3\2\2\2\u0170"+
		"\u0171\b\67\5\2\u0171n\3\2\2\2\u0172\u0173\13\2\2\2\u0173\u0174\b8\t\2"+
		"\u0174p\3\2\2\2\25\2rz|\u0086\u008e\u0093\u009c\u009e\u00a7\u0148\u0150"+
		"\u0156\u0158\u015d\u0163\u0165\u0169\u016e\n\3\3\2\3\3\3\3\3\4\b\2\2\3"+
		"\5\5\3\5\6\3\6\7\38\b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}