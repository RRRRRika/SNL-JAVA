package lexicalAnalyzer;

import utils.LexType;
import utils.Token;

import java.io.*;
import java.util.Vector;

/**
 * @author RRRRRika
 *
 * 词法分析器 Scanner，基于 DFA
 * 用于生成 token 列表
 */

public class Scanner {
    private File file;
    private final DFA dfa;

    public Scanner() {
        file = null;
        dfa = new DFA();
    }

    public Scanner(File file) throws NullPointerException {
        if (file == null) {
            throw new NullPointerException("file is null");
        }
        this.file = file;
        dfa = new DFA();
    }

    public Scanner(String path) throws FileNotFoundException {
        file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("file not found");
        }
        dfa = new DFA();
    }

    public void setFile(File file) throws FileNotFoundException {
        this.file = file;
        if (file == null) {
            throw new FileNotFoundException();
        }
    }

    public void setFile(String path) throws FileNotFoundException {
        file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("file not found");
        }
    }

    public Vector<Token> getTokenList() throws IOException {
        dfa.setFile(file);
        Vector<Token> v = (Vector<Token>) dfa.getTokenList().clone();
        dfa.reset();
        return v;
    }

    // 保存 tokenList 至文件
    public void getTokenListAndSave(String path) throws IOException {
        dfa.setFile(file);
        Vector<Token> v = (Vector<Token>) dfa.getTokenList().clone();
        dfa.reset();
        BufferedWriter rw = new BufferedWriter(new FileWriter(path));
        for (Token t : v) {
            rw.write(t.toString());
            rw.newLine();
        }
        rw.close();
    }

    public void getTokenListAnotherFormat(String path) throws Exception {
        dfa.setFile(file);
        Vector<Token> v = (Vector<Token>) dfa.getTokenList().clone();
        dfa.reset();
        BufferedWriter rw = new BufferedWriter(new FileWriter(path));
        for (Token t : v) {
            if ((t.lex.ordinal() >= 23 && t.lex.ordinal() <= 41) || (t.lex.ordinal() == 1 || t.lex.ordinal() == 2) || t.lex == LexType.ID) {
                rw.write(String.valueOf(t.lex));
            } else {
                rw.write(t.sem);
            }
            rw.newLine();
        }
        rw.close();
    }
}
