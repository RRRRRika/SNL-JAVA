package utils;

public enum LexType {
    // 标识符  0
    ID,
    // 类型 无符号整数 单字符 保留字  1 - 2
    INTEGER, CHAR,
    // 字符 数字 3 - 4
    INTC, CHARC,
    // 单字符分界符  5 - 19
    // +, -, *, /, <, =, (, ), [, ], ,, ;, ., EOF, "",
    PLUS, MINUS, MUL, DIV, LT, EQ, LPREN, RPREN, LMIDREN, RMIDREN, COMMA, SEMI, DOT, EOF, BLANK,
    // 双字符分界符 :=  20
    ASSIGN,
    // 字符标识符 '  21
    SQTA,
    // 数组下标界限符 ..  22
    UNDERANGE,
    // 保留字  23 - 41
    PROGRAM, PROCEDURE, TYPE, VAR, IF, THEN, ELSE, FI, WHILE, DO, ENDWH, READ, WRITE, ARRAY, OF, RECORD, RETURN, BEGIN, END,
    // 注释开始、结束符 { }  42 - 43
    LBRACE, RBRACE,
    // 错误  44
    ERROR,
    // 保留  45
    DEFAULT,
}
