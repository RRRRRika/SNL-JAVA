package lexicalAnalyzer;

/**
 * @author RRRRRika
 * 
 * DFA 自动机，返回一个单词的种类
 */

import utils.LexType;

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

public class DFA {
    private StateType state;
    private String word;

    public DFA() {
        state = StateType.START;
        word = "";
    }

    public DFA(String word) {
        state = StateType.START;
        this.word = word;
    }

    public DFA(StateType state, String word) {
        this.state = state;
        this.word = word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public LexType getLexType() {
        char ch;
        LexType lexType = LexType.DEFAULT;
        for (int i = 0; i < word.length(); i++) {
            ch = word.charAt(i);
            switch (state) {
                case START:
                    // ...
                    break;
                case INID:
                    // ...
                    break;
                case INNUM:
                    // ...
                    break;
                case INCHAR:
                    // ...
                    break;
                case INASSIGN:
                    // ...
                    break;
                case INCOMMENT:
                    // ...
                    break;
                case INRANGE:
                    // ...
                    break;
                case DONE:
                    // ...
                    break;
            
                default:
                    break;
            }
        }
        return lexType;
    }
}
