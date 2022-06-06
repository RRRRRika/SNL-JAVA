import lexicalAnalyzer.Scanner;
import utils.Token;

import java.io.File;
import java.util.Vector;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("test1.txt");
        Scanner s = new Scanner();
        s.setFile(file);
        Vector<Token> v = s.getTokenList();
        assert v != null;
        for (Token token : v) {
            System.out.println(token);
        }
        s.getTokenListAnotherFormat("token.txt");
    }
}
