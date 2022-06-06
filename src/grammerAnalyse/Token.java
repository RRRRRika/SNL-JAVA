package grammerAnalyse;

import java.util.List;

public class Token {
    public String id;
    public String name;
    public int rowcount;
    public int positioninrow;
    public static List<Token> tokenList;
    public static Token token;
    public static int tokenIndex = 0;

    /*public Token(String id,String name){
        this.id=id;this.name=name;
    }
    public Token(String id,String name,int rowcount,int positioninrow){
        this.id=id;this.name=name;this.rowcount=rowcount;this.positioninrow=positioninrow;
    }*/
    public Token(String id) {
        this.id = id;
    }
}

