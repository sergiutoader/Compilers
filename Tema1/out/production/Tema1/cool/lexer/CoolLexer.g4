lexer grammar CoolLexer;

tokens { ERROR }

@header{
    package cool.lexer;	
}

@members{    
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
}

fragment NEW_LINE : '\r'? '\n';

STRING : '"' ('\\' NEW_LINE | .)*?
        (
          '"' { getString(); }
        | NEW_LINE { raiseError("Unterminated string constant"); }
        | EOF { raiseError("EOF in string constant"); }

        );


LINE_COMMENT
    : '--' .*? (NEW_LINE | EOF) -> skip
    ;
BLOCK_COMMENT
    : '(*'
      (BLOCK_COMMENT | .)*?
      ('*)' { skip(); } | EOF { raiseError("EOF in comment"); })
    ;

UNMATCHED_COMMENT_END : '*)' { raiseError("Unmatched *)"); };


// self and SELF_TYPE
fragment SELF : 'self';
fragment SELF_TYPE : 'SELF_TYPE';

// keywords
CLASS : ('c' | 'C')('l' | 'L')('a' | 'A')('s' | 'S')('s' | 'S');
ELSE : ('e' | 'E')('l' | 'L')('s' | 'S')('e' | 'E');
FI : ('f' | 'F')('i' | 'I');
IF : ('i' | 'I')('f' | 'F');
IN : ('i' | 'I')('n' | 'N');
INHERITS : ('i' | 'I')('n' | 'N')('h' | 'H')('e' | 'E')('r' | 'R')('i' | 'I')('t' | 'T')('s' | 'S');
ISVOID : ('i' | 'I')('s' | 'S')('v' | 'V')('o' | 'O')('i' | 'I')('d' | 'D');
LET : ('l' | 'L')('e' | 'E')('t' | 'T');
LOOP : ('l' | 'L')('o' | 'O')('o' | 'O')('p' | 'P');
POOL : ('p' | 'P')('o' | 'O')('o' | 'O')('l' | 'L');
THEN : ('t' | 'T')('h' | 'H')('e' | 'E')('n' | 'N');
WHILE : ('w' | 'W')('h' | 'H')('i' | 'I')('l' | 'L')('e' | 'E');
CASE : ('c' | 'C')('a' | 'A')('s' | 'S')('e' | 'E');
ESAC : ('e' | 'E')('s' | 'S')('a' | 'A')('c' | 'C');
NEW : ('n' | 'N')('e' | 'E')('w' | 'W');
OF : ('o' | 'O')('f' | 'F');
NOT : ('n' | 'N')('o' | 'O')('t' | 'T');

// true / false
TRUE : 't'('r' | 'R')('u' | 'U')('e' | 'E');
FALSE : 'f'('a' | 'A')('l' | 'L')('s' | 'S')('e' | 'E');

// other symbols
LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
PLUS : '+';
MINUS : '-';
TILDE : '~';
STAR : '*';
SLASH : '/';
COMMA : ',';
SEMICOLON : ';';
COLON : ':';
DOT : '.';
EQUALS : '=';
LT : '<';
LE : '<=';
ASSIGN : '<-';
ARROW : '=>';
ATSIGN : '@';

// Integer
fragment DIGIT : [0-9];
INT : DIGIT+;

// Identifiers
fragment LOWERCASE_LETTER : [a-z];
fragment UPPERCASE_LETTER : [A-Z];
fragment LETTER : (LOWERCASE_LETTER | UPPERCASE_LETTER);
fragment TYPE_ID : UPPERCASE_LETTER(LETTER | DIGIT | '_')*;
TYPE : TYPE_ID | SELF_TYPE;
OBJECT_ID : LOWERCASE_LETTER(LETTER | DIGIT | '_')* | SELF;

WS
    :   [ \n\f\r\t]+ -> skip
    ;

// unmatched characters
UNMATCHED : . {
    raiseError("Invalid character: " + getText());
};