package lexicalAnalyzer;

import utils.Token;

import java.io.*;
import java.util.Vector;

/**
 * @author RRRRRika
 * <p>
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
        return dfa.getTokenList();
    }

    // 保存 tokenList 至文件
    public void getTokenListAndSave(String path) throws IOException {
        dfa.setFile(file);
        Vector<Token> v = dfa.getTokenList();
        BufferedWriter rw = new BufferedWriter(new FileWriter(path));
        for (Token t : v) {
            rw.write(t.toString());
            rw.newLine();
        }
        rw.close();
    }
}
