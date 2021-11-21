parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    package cool.parser;
}

program
    :   (classs SEMICOLON)+ EOF
    ; 

classs : CLASS name=TYPE_ID (INHERITS parent_name=TYPE_ID)?
    LBRACE
        (feature SEMICOLON)*
    RBRACE;

feature : OBJECT_ID LPAREN (formals+=formal (COMMA formals+=formal)*)? RPAREN COLON TYPE_ID
    LBRACE
        expr
    RBRACE;

formal : OBJECT_ID COLON TYPE_ID;

expr :
    OBJECT_ID ASSIGN expr                                                                       # assignment
    | expr (ATSIGN TYPE_ID)? DOT OBJECT_ID
        LPAREN
            (formal_params+=expr (COMMA formal_params+=expr) *)?
        RPAREN                                                                                  # specified_dispatch
    | OBJECT_ID
         LPAREN
            (formal_params+=expr (COMMA formal_params+=expr) *)?
         RPAREN                                                                                 # unspecified_dispatch
    | IF cond=expr THEN thenBranch=expr ELSE elseBranch=expr FI                                 # if
    | WHILE cond=expr LOOP body=expr POOL                                                       # while
    | LBRACE (subexpr+=expr COMMA)+ RBRACE                                                      # block
    | LET OBJECT_ID COLON TYPE_ID (ASSIGN expressions+=expr)? (COMMA OBJECT_ID COLON TYPE_ID (ASSIGN expressions+=expr)?)* IN body=expr # let
    | CASE ref_expr=expr OF (OBJECT_ID COLON TYPE_ID ARROW case_expr+=expr COMMA)+ ESAC         # case
    | NEW TYPE_ID                                                                               # new
    | ISVOID e=expr                                                                             # isvoid
    | left=expr (PLUS | MINUS | STAR | SLASH) right=expr                                        # arithmetic
    | TILDE expr                                                                                # negative
    | left=expr (LT | LE | EQUALS) right=expr                                                   # relational
    | NOT e=expr                                                                                # not
    | LPAREN e=expr RPAREN                                                                      # paren
    | OBJECT_ID                                                                                 # id
    | INT                                                                                       # int
    | STRING                                                                                    # string
    | TRUE                                                                                      # true
    | FALSE                                                                                     # false
    ;