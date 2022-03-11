parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    package cool.parser;
}

program
    :   (classes+=classs SEMICOLON)+ EOF
    ; 

classs : CLASS name=TYPE (INHERITS parent_name=TYPE)?
    LBRACE
        (features+=feature SEMICOLON)*
    RBRACE;

feature : name=OBJECT_ID LPAREN (formals+=formal (COMMA formals+=formal)*)? RPAREN COLON type=TYPE
    LBRACE
        body=expr
    RBRACE                                                                                      # method
    |
    name=OBJECT_ID COLON type=TYPE (ASSIGN e=expr)?                                             # attribute;

formal : id=OBJECT_ID COLON type=TYPE;
local : id=OBJECT_ID COLON type=TYPE (ASSIGN expr)?;
case_branch : id=OBJECT_ID COLON type=TYPE ARROW e=expr;

expr :
    object=expr (ATSIGN type=TYPE)? DOT funcName=OBJECT_ID
        LPAREN
            (formal_params+=expr (COMMA formal_params+=expr) *)?
        RPAREN                                                                                  # specified_dispatch
    | funcName=OBJECT_ID
         LPAREN
            (formal_params+=expr (COMMA formal_params+=expr) *)?
         RPAREN                                                                                 # unspecified_dispatch
    | IF cond=expr THEN thenBranch=expr ELSE elseBranch=expr FI                                 # if
    | WHILE cond=expr LOOP body=expr POOL                                                       # while
    | LBRACE (subexpr+=expr SEMICOLON)+ RBRACE                                                  # block
    | LET local
        (COMMA local)* IN body=expr                                                             # let
    | CASE ref_expr=expr OF (case_branches+=case_branch SEMICOLON)+ ESAC                        # case
    | NEW id=TYPE                                                                               # new
    | ISVOID e=expr                                                                             # isvoid
    | LPAREN e=expr RPAREN                                                                      # paren
    | TILDE expr                                                                                # negative
    | left=expr operation=(STAR | SLASH) right=expr                                             # multDiv
    | left=expr operation=(PLUS | MINUS) right=expr                                             # plusMinus
    | left=expr operation=(LT | LE | EQUALS) right=expr                                         # relational
    | NOT e=expr                                                                                # not
    | id=OBJECT_ID ASSIGN e=expr                                                                # assignment
    | OBJECT_ID                                                                                 # id
    | INT                                                                                       # int
    | STRING                                                                                    # string
    | TRUE                                                                                      # true
    | FALSE                                                                                     # false
    ;