// Generated from /home/student/Compilers/Tema1/out/production/Tema1/cool/lexer/CoolLexer.g4 by ANTLR 4.8

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
		SELF=6, SELF_TYPE=7, CLASS=8, ELSE=9, FI=10, IF=11, IN=12, INHERITS=13, 
		ISVOID=14, LET=15, LOOP=16, POOL=17, THEN=18, WHILE=19, CASE=20, ESAC=21, 
		NEW=22, OF=23, NOT=24, TRUE=25, FALSE=26, LPAREN=27, RPAREN=28, LBRACE=29, 
		RBRACE=30, PLUS=31, MINUS=32, TILDE=33, STAR=34, SLASH=35, COMMA=36, SEMICOLON=37, 
		COLON=38, DOT=39, EQUALS=40, LT=41, LE=42, ASSIGN=43, ARROW=44, ATSIGN=45, 
		INT=46, TYPE_ID=47, OBJECT_ID=48, WS=49, UNMATCHED=50;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"STRING", "NEW_LINE", "LINE_COMMENT", "BLOCK_COMMENT", "UNMATCHED_COMMENT_END", 
			"SELF", "SELF_TYPE", "CLASS", "ELSE", "FI", "IF", "IN", "INHERITS", "ISVOID", 
			"LET", "LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "NEW", "OF", 
			"NOT", "TRUE", "FALSE", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "PLUS", 
			"MINUS", "TILDE", "STAR", "SLASH", "COMMA", "SEMICOLON", "COLON", "DOT", 
			"EQUALS", "LT", "LE", "ASSIGN", "ARROW", "ATSIGN", "DIGIT", "INT", "LOWERCASE_LETTER", 
			"UPPERCASE_LETTER", "LETTER", "TYPE_ID", "OBJECT_ID", "WS", "UNMATCHED"
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

	    
	    private void raiseError(String msg) {
	        setText(msg);
	        setType(ERROR);
	    }

	    private void getString() {
	        String oldText = getText();
	        // Remove double quotes
	        String newText = oldText.substring(1, oldText.length() - 1);

	        for (int i = 1; i < newText.length(); i++) {
	            if (newText.charAt(i) == '\n' && newText.charAt(i - 1) != '\\') {
	                raiseError("Unterminated string constant");
	                return;
	            }
	        }

	        newText.replaceAll("\\n", "\n");
	        newText.replaceAll("\\t", "\t");
	        newText.replaceAll("\\b", "\b");
	        newText.replaceAll("\\f", "\f");

	        // replace backslash
	        newText.replaceAll("\\\\", "");


	        if (newText.length() > 1024) {
	            raiseError("String constant too long");
	            return;
	        }

	         // Check if string contains null character
	        if (newText.contains("\u0000")) {
	            raiseError("String contains null character");
	            return;
	        }

	        setText(newText);
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
		case 0:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 3:
			BLOCK_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 4:
			UNMATCHED_COMMENT_END_action((RuleContext)_localctx, actionIndex);
			break;
		case 53:
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
			  raiseError("EOF in string constant"); 
			break;
		}
	}
	private void BLOCK_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			 skip(); 
			break;
		case 3:
			 raiseError("EOF in comment"); 
			break;
		}
	}
	private void UNMATCHED_COMMENT_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			 raiseError("Unmatched *)"); 
			break;
		}
	}
	private void UNMATCHED_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:

			    raiseError("Invalid character: " + getText());

			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\64\u0167\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\3\2\3\2\7\2r\n\2\f\2\16\2u\13\2\3"+
		"\2\3\2\3\2\3\2\5\2{\n\2\3\3\5\3~\n\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4\u0086"+
		"\n\4\f\4\16\4\u0089\13\4\3\4\3\4\5\4\u008d\n\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\7\5\u0096\n\5\f\5\16\5\u0099\13\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00a1"+
		"\n\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3"+
		"\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3"+
		"\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3"+
		"(\3)\3)\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3-\3.\3.\3/\3/\3\60\6\60\u0140\n"+
		"\60\r\60\16\60\u0141\3\61\3\61\3\62\3\62\3\63\3\63\5\63\u014a\n\63\3\64"+
		"\3\64\3\64\3\64\7\64\u0150\n\64\f\64\16\64\u0153\13\64\3\65\3\65\3\65"+
		"\3\65\7\65\u0159\n\65\f\65\16\65\u015c\13\65\3\66\6\66\u015f\n\66\r\66"+
		"\16\66\u0160\3\66\3\66\3\67\3\67\3\67\5s\u0087\u0097\28\3\4\5\2\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q*S+U,W-Y.[/]\2_\60a\2c\2e\2g\61i\62k\63m\64\3\2\27\4\2EEee"+
		"\4\2NNnn\4\2CCcc\4\2UUuu\4\2GGgg\4\2HHhh\4\2KKkk\4\2PPpp\4\2JJjj\4\2T"+
		"Ttt\4\2VVvv\4\2XXxx\4\2QQqq\4\2FFff\4\2RRrr\4\2YYyy\4\2WWww\3\2\62;\3"+
		"\2c|\3\2C\\\5\2\13\f\16\17\"\"\2\u0172\2\3\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2"+
		"\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2"+
		"\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2_"+
		"\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\3o\3\2\2\2\5}\3\2"+
		"\2\2\7\u0081\3\2\2\2\t\u00a0\3\2\2\2\13\u00a2\3\2\2\2\r\u00a7\3\2\2\2"+
		"\17\u00ac\3\2\2\2\21\u00b6\3\2\2\2\23\u00bc\3\2\2\2\25\u00c1\3\2\2\2\27"+
		"\u00c4\3\2\2\2\31\u00c7\3\2\2\2\33\u00ca\3\2\2\2\35\u00d3\3\2\2\2\37\u00da"+
		"\3\2\2\2!\u00de\3\2\2\2#\u00e3\3\2\2\2%\u00e8\3\2\2\2\'\u00ed\3\2\2\2"+
		")\u00f3\3\2\2\2+\u00f8\3\2\2\2-\u00fd\3\2\2\2/\u0101\3\2\2\2\61\u0104"+
		"\3\2\2\2\63\u0108\3\2\2\2\65\u010d\3\2\2\2\67\u0113\3\2\2\29\u0115\3\2"+
		"\2\2;\u0117\3\2\2\2=\u0119\3\2\2\2?\u011b\3\2\2\2A\u011d\3\2\2\2C\u011f"+
		"\3\2\2\2E\u0121\3\2\2\2G\u0123\3\2\2\2I\u0125\3\2\2\2K\u0127\3\2\2\2M"+
		"\u0129\3\2\2\2O\u012b\3\2\2\2Q\u012d\3\2\2\2S\u012f\3\2\2\2U\u0131\3\2"+
		"\2\2W\u0134\3\2\2\2Y\u0137\3\2\2\2[\u013a\3\2\2\2]\u013c\3\2\2\2_\u013f"+
		"\3\2\2\2a\u0143\3\2\2\2c\u0145\3\2\2\2e\u0149\3\2\2\2g\u014b\3\2\2\2i"+
		"\u0154\3\2\2\2k\u015e\3\2\2\2m\u0164\3\2\2\2os\7$\2\2pr\13\2\2\2qp\3\2"+
		"\2\2ru\3\2\2\2st\3\2\2\2sq\3\2\2\2tz\3\2\2\2us\3\2\2\2vw\7$\2\2w{\b\2"+
		"\2\2xy\7\2\2\3y{\b\2\3\2zv\3\2\2\2zx\3\2\2\2{\4\3\2\2\2|~\7\17\2\2}|\3"+
		"\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\7\f\2\2\u0080\6\3\2\2\2\u0081"+
		"\u0082\7/\2\2\u0082\u0083\7/\2\2\u0083\u0087\3\2\2\2\u0084\u0086\13\2"+
		"\2\2\u0085\u0084\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0088\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0088\u008c\3\2\2\2\u0089\u0087\3\2\2\2\u008a\u008d\5\5"+
		"\3\2\u008b\u008d\7\2\2\3\u008c\u008a\3\2\2\2\u008c\u008b\3\2\2\2\u008d"+
		"\u008e\3\2\2\2\u008e\u008f\b\4\4\2\u008f\b\3\2\2\2\u0090\u0091\7*\2\2"+
		"\u0091\u0092\7,\2\2\u0092\u0097\3\2\2\2\u0093\u0096\5\t\5\2\u0094\u0096"+
		"\13\2\2\2\u0095\u0093\3\2\2\2\u0095\u0094\3\2\2\2\u0096\u0099\3\2\2\2"+
		"\u0097\u0098\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u009a\3\2\2\2\u0099\u0097"+
		"\3\2\2\2\u009a\u009b\7,\2\2\u009b\u009c\7+\2\2\u009c\u009d\3\2\2\2\u009d"+
		"\u00a1\b\5\5\2\u009e\u009f\7\2\2\3\u009f\u00a1\b\5\6\2\u00a0\u0090\3\2"+
		"\2\2\u00a0\u009e\3\2\2\2\u00a1\n\3\2\2\2\u00a2\u00a3\7,\2\2\u00a3\u00a4"+
		"\7+\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\b\6\7\2\u00a6\f\3\2\2\2\u00a7"+
		"\u00a8\7u\2\2\u00a8\u00a9\7g\2\2\u00a9\u00aa\7n\2\2\u00aa\u00ab\7h\2\2"+
		"\u00ab\16\3\2\2\2\u00ac\u00ad\7U\2\2\u00ad\u00ae\7G\2\2\u00ae\u00af\7"+
		"N\2\2\u00af\u00b0\7H\2\2\u00b0\u00b1\7a\2\2\u00b1\u00b2\7V\2\2\u00b2\u00b3"+
		"\7[\2\2\u00b3\u00b4\7R\2\2\u00b4\u00b5\7G\2\2\u00b5\20\3\2\2\2\u00b6\u00b7"+
		"\t\2\2\2\u00b7\u00b8\t\3\2\2\u00b8\u00b9\t\4\2\2\u00b9\u00ba\t\5\2\2\u00ba"+
		"\u00bb\t\5\2\2\u00bb\22\3\2\2\2\u00bc\u00bd\t\6\2\2\u00bd\u00be\t\3\2"+
		"\2\u00be\u00bf\t\5\2\2\u00bf\u00c0\t\6\2\2\u00c0\24\3\2\2\2\u00c1\u00c2"+
		"\t\7\2\2\u00c2\u00c3\t\b\2\2\u00c3\26\3\2\2\2\u00c4\u00c5\t\b\2\2\u00c5"+
		"\u00c6\t\7\2\2\u00c6\30\3\2\2\2\u00c7\u00c8\t\b\2\2\u00c8\u00c9\t\t\2"+
		"\2\u00c9\32\3\2\2\2\u00ca\u00cb\t\b\2\2\u00cb\u00cc\t\t\2\2\u00cc\u00cd"+
		"\t\n\2\2\u00cd\u00ce\t\6\2\2\u00ce\u00cf\t\13\2\2\u00cf\u00d0\t\b\2\2"+
		"\u00d0\u00d1\t\f\2\2\u00d1\u00d2\t\5\2\2\u00d2\34\3\2\2\2\u00d3\u00d4"+
		"\t\b\2\2\u00d4\u00d5\t\5\2\2\u00d5\u00d6\t\r\2\2\u00d6\u00d7\t\16\2\2"+
		"\u00d7\u00d8\t\b\2\2\u00d8\u00d9\t\17\2\2\u00d9\36\3\2\2\2\u00da\u00db"+
		"\t\3\2\2\u00db\u00dc\t\6\2\2\u00dc\u00dd\t\f\2\2\u00dd \3\2\2\2\u00de"+
		"\u00df\t\3\2\2\u00df\u00e0\t\16\2\2\u00e0\u00e1\t\16\2\2\u00e1\u00e2\t"+
		"\20\2\2\u00e2\"\3\2\2\2\u00e3\u00e4\t\20\2\2\u00e4\u00e5\t\16\2\2\u00e5"+
		"\u00e6\t\16\2\2\u00e6\u00e7\t\3\2\2\u00e7$\3\2\2\2\u00e8\u00e9\t\f\2\2"+
		"\u00e9\u00ea\t\n\2\2\u00ea\u00eb\t\6\2\2\u00eb\u00ec\t\t\2\2\u00ec&\3"+
		"\2\2\2\u00ed\u00ee\t\21\2\2\u00ee\u00ef\t\n\2\2\u00ef\u00f0\t\b\2\2\u00f0"+
		"\u00f1\t\3\2\2\u00f1\u00f2\t\6\2\2\u00f2(\3\2\2\2\u00f3\u00f4\t\2\2\2"+
		"\u00f4\u00f5\t\4\2\2\u00f5\u00f6\t\5\2\2\u00f6\u00f7\t\6\2\2\u00f7*\3"+
		"\2\2\2\u00f8\u00f9\t\6\2\2\u00f9\u00fa\t\5\2\2\u00fa\u00fb\t\4\2\2\u00fb"+
		"\u00fc\t\2\2\2\u00fc,\3\2\2\2\u00fd\u00fe\t\t\2\2\u00fe\u00ff\t\6\2\2"+
		"\u00ff\u0100\t\21\2\2\u0100.\3\2\2\2\u0101\u0102\t\16\2\2\u0102\u0103"+
		"\t\7\2\2\u0103\60\3\2\2\2\u0104\u0105\t\t\2\2\u0105\u0106\t\16\2\2\u0106"+
		"\u0107\t\f\2\2\u0107\62\3\2\2\2\u0108\u0109\7v\2\2\u0109\u010a\t\13\2"+
		"\2\u010a\u010b\t\22\2\2\u010b\u010c\t\6\2\2\u010c\64\3\2\2\2\u010d\u010e"+
		"\7h\2\2\u010e\u010f\t\4\2\2\u010f\u0110\t\3\2\2\u0110\u0111\t\5\2\2\u0111"+
		"\u0112\t\6\2\2\u0112\66\3\2\2\2\u0113\u0114\7*\2\2\u01148\3\2\2\2\u0115"+
		"\u0116\7+\2\2\u0116:\3\2\2\2\u0117\u0118\7}\2\2\u0118<\3\2\2\2\u0119\u011a"+
		"\7\177\2\2\u011a>\3\2\2\2\u011b\u011c\7-\2\2\u011c@\3\2\2\2\u011d\u011e"+
		"\7/\2\2\u011eB\3\2\2\2\u011f\u0120\7\u0080\2\2\u0120D\3\2\2\2\u0121\u0122"+
		"\7,\2\2\u0122F\3\2\2\2\u0123\u0124\7\61\2\2\u0124H\3\2\2\2\u0125\u0126"+
		"\7.\2\2\u0126J\3\2\2\2\u0127\u0128\7=\2\2\u0128L\3\2\2\2\u0129\u012a\7"+
		"<\2\2\u012aN\3\2\2\2\u012b\u012c\7\60\2\2\u012cP\3\2\2\2\u012d\u012e\7"+
		"?\2\2\u012eR\3\2\2\2\u012f\u0130\7>\2\2\u0130T\3\2\2\2\u0131\u0132\7>"+
		"\2\2\u0132\u0133\7?\2\2\u0133V\3\2\2\2\u0134\u0135\7>\2\2\u0135\u0136"+
		"\7/\2\2\u0136X\3\2\2\2\u0137\u0138\7?\2\2\u0138\u0139\7@\2\2\u0139Z\3"+
		"\2\2\2\u013a\u013b\7B\2\2\u013b\\\3\2\2\2\u013c\u013d\t\23\2\2\u013d^"+
		"\3\2\2\2\u013e\u0140\5]/\2\u013f\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141"+
		"\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142`\3\2\2\2\u0143\u0144\t\24\2\2"+
		"\u0144b\3\2\2\2\u0145\u0146\t\25\2\2\u0146d\3\2\2\2\u0147\u014a\5a\61"+
		"\2\u0148\u014a\5c\62\2\u0149\u0147\3\2\2\2\u0149\u0148\3\2\2\2\u014af"+
		"\3\2\2\2\u014b\u0151\5c\62\2\u014c\u0150\5e\63\2\u014d\u0150\5]/\2\u014e"+
		"\u0150\7a\2\2\u014f\u014c\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u014e\3\2"+
		"\2\2\u0150\u0153\3\2\2\2\u0151\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152"+
		"h\3\2\2\2\u0153\u0151\3\2\2\2\u0154\u015a\5a\61\2\u0155\u0159\5e\63\2"+
		"\u0156\u0159\5]/\2\u0157\u0159\7a\2\2\u0158\u0155\3\2\2\2\u0158\u0156"+
		"\3\2\2\2\u0158\u0157\3\2\2\2\u0159\u015c\3\2\2\2\u015a\u0158\3\2\2\2\u015a"+
		"\u015b\3\2\2\2\u015bj\3\2\2\2\u015c\u015a\3\2\2\2\u015d\u015f\t\26\2\2"+
		"\u015e\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u015e\3\2\2\2\u0160\u0161"+
		"\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0163\b\66\4\2\u0163l\3\2\2\2\u0164"+
		"\u0165\13\2\2\2\u0165\u0166\b\67\b\2\u0166n\3\2\2\2\22\2sz}\u0087\u008c"+
		"\u0095\u0097\u00a0\u0141\u0149\u014f\u0151\u0158\u015a\u0160\t\3\2\2\3"+
		"\2\3\b\2\2\3\5\4\3\5\5\3\6\6\3\67\7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}