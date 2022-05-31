import lexicalAnalyzer.Scanner;

import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("test1.txt");
        Scanner s = new Scanner();
        s.setFile(file);
//        Vector<Token> v = s.getTokenList();
//        assert v != null;
//        for (Token token : v) {
//            System.out.println(token);
//        }
        s.getTokenListAnotherFormat("test.txt");
    }
}
