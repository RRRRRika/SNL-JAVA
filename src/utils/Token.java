package utils;

public class Token {
    public int line = 0;
    public LexType lex = LexType.DEFAULT;
    public String sem = "";

    public Token(int line, LexType lex, String sem) {
        this.line = line;
        this.lex = lex;
        this.sem = sem;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setLex(LexType lex) {
        this.lex = lex;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    @Override
    public String toString() {
        if (sem != "") {
            return "<" + Integer.toString(line) + ", " + lex.toString() + ", " + sem + ">";
        } else {
            return "<" + Integer.toString(line) + ", " + lex.toString() + ">";
        }
    }
}
