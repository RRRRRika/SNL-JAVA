package lexicalAnalyzer;

import utils.LexType;
import utils.Token;

import java.io.*;
import java.util.HashMap;
import java.util.Vector;

/**
 * @author RRRRRika
 *
 * DFA 自动机，返回 tokenList
 */

// 自动机状态集合
enum StateType {
    START, // 开始
    INID, // 标识符
    INNUM, // 数字
    INCHAR, // 字符
    INASSIGN, // 赋值
    INCOMMENT, // 注释
    INRANGE, // 数组下标界限
    DONE, // 完成
}

class DFA {
    private StateType state;
    private int inlinePos;
    private BufferedReader reader;
    private final Vector<Token> tokenList;
    private final HashMap<String, LexType> reservedWords = new HashMap<>();

    public DFA() {
        state = StateType.START;
        tokenList = new Vector<>();
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
        reservedWords.put("integer", LexType.INTEGER);
        reservedWords.put("char", LexType.CHAR);
    }

    public void setFile(File file) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(file));
    }

    // 回退一步
    private void ungetNextChar() {
        if (inlinePos > 0) {
            inlinePos--;
        }
    }

    // 读取一行
    private String readLine() throws IOException {
        String line = reader.readLine();
        if (line != null) {
            line += '\n';
        }
        return line;
    }

    // 判断是否为保留字
    private boolean isReservedWord(String word) {
        word = word.toLowerCase();
        return reservedWords.containsKey(word);
    }

    // 重置tokenList
    public void reset() {
        tokenList.removeAllElements();
    }

    public Vector<Token> getTokenList() throws IOException {
        int lines = 0;
        char ch;
        boolean inComment = false;
        String curLine;
        Token curToken = new Token();

        while ((curLine = readLine()) != null) {
//            System.out.println("read line : " + curLine);
            lines++;
            inlinePos = 0;

            while (inlinePos < curLine.length()) {
                StringBuilder sem = new StringBuilder();
                // 多行注释时
                if (inComment) {
                    state = StateType.INCOMMENT;
                } else {
                    state = StateType.START;
                }
                boolean error = false;

                while (state != StateType.DONE && inlinePos < curLine.length()) {
                    boolean save = true;
                    ch = curLine.charAt(inlinePos);
//                    System.out.print(ch);

                    switch (state) {
                        case START:
                            if (Character.isLetter(ch)) {
                                state = StateType.INID;
                            } else if (Character.isDigit(ch)) {
                                state = StateType.INNUM;
                            } else if (ch == '\'') {
                                save = false;
                                state = StateType.INCHAR;
                            } else if (ch == ':') {
                                state = StateType.INASSIGN;
                            } else if (ch == '{') {
                                save = false;
                                inComment = true;
                                state = StateType.INCOMMENT;
                            } else if (ch == '.') {
                                state = StateType.INRANGE;
                            } else if (ch == ' ' || ch == '\t' || ch == '\n') {
                                save = false;
                            } else {
                                state = StateType.DONE;

                                if (ch == '+') {
                                    curToken.setLex(LexType.PLUS);
                                } else if (ch == '-') {
                                    curToken.setLex(LexType.MINUS);
                                } else if (ch == '*') {
                                    curToken.setLex(LexType.MUL);
                                } else if (ch == '/') {
                                    curToken.setLex(LexType.DIV);
                                } else if (ch == '<') {
                                    curToken.setLex(LexType.LT);
                                } else if (ch == '=') {
                                    curToken.setLex(LexType.EQ);
                                } else if (ch == '(') {
                                    curToken.setLex(LexType.LPREN);
                                } else if (ch == ')') {
                                    curToken.setLex(LexType.RPREN);
                                } else if (ch == '[') {
                                    curToken.setLex(LexType.LMIDREN);
                                } else if (ch == ']') {
                                    curToken.setLex(LexType.RMIDREN);
                                } else if (ch == ',') {
                                    curToken.setLex(LexType.COMMA);
                                } else if (ch == ';') {
                                    curToken.setLex(LexType.SEMI);
                                } else {
                                    curToken.setLex(LexType.ERROR);
                                    error = true;
                                }
                            }
                            break;
                        case INID:
                            if (!(Character.isLetter(ch) || Character.isDigit(ch))) {
                                ungetNextChar();
                                save = false;
                                state = StateType.DONE;
                                curToken.setLex(LexType.ID);
                            }
                            break;
                        case INNUM:
                            if (!Character.isDigit(ch)) {
                                ungetNextChar();
                                save = false;
                                state = StateType.DONE;
                                curToken.setLex(LexType.INTC);
                            }
                            break;
                        case INCHAR:
                            if (Character.isDigit(ch) || Character.isLetter(ch)) {
                                char next = curLine.charAt(inlinePos + 1);
                                if (next == '\'') {
                                    inlinePos++;
                                    state = StateType.DONE;
                                    curToken.setLex(LexType.CHARC);
                                } else {
                                    ungetNextChar();
                                    ungetNextChar();
                                    state = StateType.DONE;
                                    curToken.setLex(LexType.ERROR);
                                    error = true;
                                }
                            } else {
                                ungetNextChar();
                                state = StateType.DONE;
                                curToken.setLex(LexType.ERROR);
                                error = true;
                            }
                            break;
                        case INASSIGN:
                            state = StateType.DONE;
                            if (ch == '=') {
                                curToken.setLex(LexType.ASSIGN);
                            } else {
                                ungetNextChar();
                                save = false;
                                curToken.setLex(LexType.ERROR);
                                error = true;
                            }
                            break;
                        case INCOMMENT:
                            save = false;
                            if (ch == '}') {
                                inComment = false;
                                state = StateType.START;
                            }
                            break;
                        case INRANGE:
                            state = StateType.DONE;
                            if (ch == '.') {
                                curToken.setLex(LexType.UNDERANGE);
                            } else {
                                ungetNextChar();
                                save = false;
                                curToken.setLex(LexType.DOT);
                            }
                            break;
                    }

                    if (save) {
                        sem.append(ch);
                    }

                    inlinePos++;
                }

                // 出错处理
                if (error) {
                    System.out.println("ERROR: line " + lines + ", position "
                            + inlinePos + ": " + sem);
                }

                curToken.setLine(lines);
                if (curToken.lex == LexType.ID) {
                    if (isReservedWord(sem.toString())) {
                        curToken.setLex(reservedWords.get(sem.toString()));
                    } else {
                        curToken.setSem(sem.toString());
                    }
                } else {
                    curToken.setSem(sem.toString());
                }

                // 因换行符可能出现sem为空的情况
                if (!sem.toString().equals("")) {
                    tokenList.add(curToken);
                    curToken = new Token();
                }

            }
        }

        curToken = new Token(lines, LexType.EOF, "");
        tokenList.add(curToken);

        reader.close();
        return tokenList;
    }
}
