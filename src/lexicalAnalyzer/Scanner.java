package lexicalAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import utils.Token;

/**
 * @author RRRRRika
 * 
 * 词法分析器 Scanner，基于 DFA 
 * 用于生成 token 列表
 */

public class Scanner {
    private File file;
    private DFA dfa;

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

    public Vector<Token> getTokenList() throws FileNotFoundException, IOException {
        dfa.setFile(file);
        return dfa.getTokenList();
    }
}
