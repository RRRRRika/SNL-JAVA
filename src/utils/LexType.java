package utils;

public enum LexType {
    // 标识符  0
    ID,
    // 类型 无符号整数 单字符  1 - 2
    INTEGER, CHAR,
    // 单字符分界符  3 - 17
    // +, -, *, /, <, =, (, ), [, ], ,, ;, ., EOF, "",
    PLUS, MINUS, MUL, DIV, LT, EQ, LPREN, RPREN, LMIDREN, RMIDREN, COMMA, SEMI, DOT, EOF, BLANK,
    // 双字符分界符 :=  18
    ASSIGN,
    // 字符标识符 '  19
    SQTA,
    // 数组下标界限符 ..  20
    UNDERANGE,
    // 保留字  21 - 39
    PROGRAM, PROCEDURE, TYPE, VAR, IF, THEN, ELSE, FI, WHILE, DO, ENDWH, READ, WRITE, ARRAY, OF, RECORD, RETURN, BEGIN, END,
    // 注释开始、结束符 { }  40 - 41
    LBRACE, RBRACE,
    // 错误  42
    ERROR,
    // 保留  43
    DEFAULT,
}
