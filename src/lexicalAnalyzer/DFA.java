package lexicalAnalyzer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import utils.LexType;
import utils.Token;

/**
 * @author 
 * 
 * DFA 自动机，返回一个单词的种类
 */


// 自动机状态集合
enum StateType {
    START,      // 开始
    INID,       // 标识符
    INNUM,      // 数字
    INCHAR,     // 字符
    INASSIGN,   // 赋值
    INCOMMENT,  // 注释
    INRANGE,    // 数组下标界限
    DONE,       // 完成
}

class DFA {
    private StateType state;
    private int pos;
    private FileInputStream fis;
    private Vector<Token> tokenList;
    private HashMap<String, LexType> reservedWords = new HashMap<String, LexType>();

    public DFA() {
        state = StateType.START;
        initHashMap();
    }

    public DFA(String program) {
        state = StateType.START;
        initHashMap();
    }

    public DFA(StateType state, String program) {
        this.state = state;
        initHashMap();
    }

    private void initHashMap() {
        reservedWords.put("program", LexType.PROGRAM);
        reservedWords.put("procedure", LexType.PROCEDURE);
        reservedWords.put("type", LexType.TYPE);
        reservedWords.put("var", LexType.VAR);
        reservedWords.put("if", LexType.IF);
        reservedWords.put("then", LexType.THEN);
        reservedWords.put("else", LexType.ELSE);
        reservedWords.put("fi", LexType.FI);
        reservedWords.put("while", LexType.WHILE);
        reservedWords.put("do", LexType.DO);
        reservedWords.put("endwh", LexType.ENDWH);
        reservedWords.put("read", LexType.READ);
        reservedWords.put("write", LexType.WRITE);
        reservedWords.put("array", LexType.ARRAY);
        reservedWords.put("of", LexType.OF);
        reservedWords.put("record", LexType.RECORD);
        reservedWords.put("return", LexType.RETURN);
        reservedWords.put("begin", LexType.BEGIN);
        reservedWords.put("end", LexType.END);
    }

    public void setFileInputStream(FileInputStream fis) {
        this.fis = fis;
    }

    // 重置DFA状态
    public void reset() {
        state = StateType.START;
    }

    // 回退一步
    private void back() {

    }

    // 判断是否为保留字
    private boolean isReservedWord(String word) {
        word = word.toLowerCase();
        return reservedWords.containsKey(word);
    }

    public Vector<Token> getTokenList() throws IOException {
        // ...

        fis.close();
        return tokenList;
    }
}
