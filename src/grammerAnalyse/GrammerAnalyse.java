package grammerAnalyse;

/* S={aS||bS'}
 * S'={a||e}
 */
public class GrammerAnalyse implements GrammerAny {
    //private  JTextArea textArea1;
    public static int times;

    //private LineNumberHeaderView l;
    public GrammerAnalyse() {
        //this.textArea1=textArea1;
        GrammerAnalyse.times = 1;
        //this.l=z;
        Token.tokenIndex = 0;
    }

    public void ErrorMessage() {
		 /*if(GrammerAnalyse.times-->0) {
		 this.textArea1.setText(this.textArea1.getText()+"\n"+"程序在"+Token.token.rowcount+"行，第"+Token.token.positioninrow+"个词："+Token.token.id+"("+Token.token.name+")有误");
		 //System.out.println();
		 l.setErrnumber(Token.token.rowcount);
		 l.repaint();
		 }*/
    }

    public void Match(String s) {
        //System.out.println(s);
        if (s.equals(Token.token.id)) {
            Token.tokenIndex++;
            this.ReadToken();

        }
    }

    public void ReadToken() {
        if (Token.tokenIndex < Token.tokenList.size()) {
            Token.token = (Token.tokenList.get(Token.tokenIndex));

        }
    }

    public void Program(GrammerNode node) {
        if (Token.token.id.equals("PROGRAM")) {
            this.ProgramHead(GrammerTree.root.addSon(new GrammerNode("ProgramHead")));
            this.DeclarePart(GrammerTree.root.addSon(new GrammerNode("DeclarePart")));
            this.ProcBody(GrammerTree.root.addSon(new GrammerNode("ProcBody")));
        } else {
            System.out.println("程序头出错 PROGRAM");
            this.ErrorMessage();
            return;
        }
    }

    public void ProgramHead(GrammerNode node) {
        if (Token.token.id.equals("PROGRAM")) {
            this.Match("PROGRAM");
            node.addSon(new GrammerNode("PROGRAM"));
            this.ProgramName(node.addSon(new GrammerNode("ProgramName")));
        } else {
            System.out.println("程序头出错,PROGRAM不匹配");
            this.ErrorMessage();
            return;
        }
    }

    public void ProgramName(GrammerNode node) {
        if (Token.token.id.equals("ID")) {
            this.Match("ID");
            node.addSon(new GrammerNode("ID"));
        } else {
            System.out.println("程序名出错");
            this.ErrorMessage();
            return;
        }
    }

    public void DeclarePart(GrammerNode node) {
        if (Token.token.id.equals("TYPE") || Token.token.id.equals("VAR") || Token.token.id.equals("PROCEDURE") || Token.token.id.equals("BEGIN")) {
            this.TypeDecpart(node.addSon(new GrammerNode("TypeDecpart")));
            this.VarDecpart(node.addSon(new GrammerNode("VarDecpart")));
            this.ProcDecpart(node.addSon(new GrammerNode("ProcDecpart")));
        } else {
            System.out.println("程序声明出错");
            this.ErrorMessage();
            return;
        }
    }


    public void TypeDecpart(GrammerNode node) {
        if (Token.token.id.equals("VAR") || Token.token.id.equals("PROCEDURE") || Token.token.id.equals("BEGIN")) {
        } else if (Token.token.id.equals("TYPE")) {
            this.TypeDec(node.addSon(new GrammerNode("TypeDec")));
        } else {
            System.out.println("类型部分声明错误");
            this.ErrorMessage();
            return;
        }

    }

    public void TypeDec(GrammerNode node) {
        if (Token.token.id.equals("TYPE")) {
            this.Match("TYPE");
            node.addSon(new GrammerNode("TYPE"));
            this.TypeDecList(node.addSon(new GrammerNode("TypeDecList")));
        } else {
            System.out.println("类型声明错误");
            this.ErrorMessage();
            return;
        }
    }

    public void TypeDecList(GrammerNode node) {
        if (Token.token.id.equals("ID")) {
            this.TypeId(node.addSon(new GrammerNode("TypeId")));
            this.Match("=");
            node.addSon(new GrammerNode("="));
            this.TypeDef(node.addSon(new GrammerNode("TypeDef")));
            this.Match(";");
            node.addSon(new GrammerNode(";"));
            this.TypeDecMore(node.addSon(new GrammerNode("TypeDecMore")));
        } else {
            System.out.println("类型标识错误  TypeDecList");
            this.ErrorMessage();
            return;
        }
    }

    public void TypeDecMore(GrammerNode node) {
        if (Token.token.id.equals("VAR") || Token.token.id.equals("PROCEDURE") || Token.token.id.equals("BEGIN")) {

        } else if (Token.token.id.equals("ID")) {
            this.TypeDecList(node.addSon(new GrammerNode("TypeDecList")));
        } else {
            System.out.println("类型定义错误 TypeDecMore ");
            this.ErrorMessage();
            return;
        }
    }

    public void TypeId(GrammerNode node) {
        if (Token.token.id.equals("ID")) {
            this.Match("ID");
            node.addSon(new GrammerNode("ID"));
        } else {
            System.out.println("类型声明出错  TypeId");
            this.ErrorMessage();
            return;
        }
    }

    public void TypeDef(GrammerNode node) {
        if (Token.token.id.equals("INTEGER") || Token.token.id.equals("CHAR")) {
            this.BaseType(node.addSon(new GrammerNode("BaseType")));
        } else if (Token.token.id.equals("ARRAY") || Token.token.id.equals("RECORD")) {
            this.StructureType(node.addSon(new GrammerNode("StructureType")));
        } else if (Token.token.id.equals("ID")) {
            this.Match("ID");
            node.addSon(new GrammerNode("ID"));
        } else {
            System.out.println("类型出错  TypeDef");
            this.ErrorMessage();
            return;
        }

    }

    public void BaseType(GrammerNode node) {
        if (Token.token.id.equals("INTEGER")) {
            this.Match("INTEGER");
            node.addSon(new GrammerNode("INTEGER"));
        } else if (Token.token.id.equals("CHAR")) {
            this.Match("CHAR");
            node.addSon(new GrammerNode("CHAR"));
        } else {
            System.out.println("类型出错  BaseDef");
            this.ErrorMessage();
            return;
        }

    }

    public void StructureType(GrammerNode node) {
        if (Token.token.id.equals("ARRAY")) {
            this.ArrayType(node.addSon(new GrammerNode("ArrayType")));
        } else if (Token.token.id.equals("RECORD")) {
            this.RecType(node.addSon(new GrammerNode("RecType")));
        } else {
            System.out.println("类型出错  StructureType");
            this.ErrorMessage();
            return;
        }
    }

    public void ArrayType(GrammerNode node) {
        if (Token.token.id.equals("ARRAY")) {
            this.Match("ARRAY");
            node.addSon(new GrammerNode("ARRAY"));
            this.Match("[");
            node.addSon(new GrammerNode("["));
            this.Low(node.addSon(new GrammerNode("Low")));
            this.Match("..");
            node.addSon(new GrammerNode(".."));
            this.Top(node.addSon(new GrammerNode("Top")));
            this.Match("]");
            node.addSon(new GrammerNode("]"));
            this.Match("OF");
            node.addSon(new GrammerNode("OF"));
            this.BaseType(node.addSon(new GrammerNode("BaseType")));
        } else {
            System.out.println("类型出错  ArrayType");
            this.ErrorMessage();
            return;
        }
    }


    public void Low(GrammerNode node) {
        if (Token.token.id.equals("INTC")) {
            this.Match("INTC");
            node.addSon(new GrammerNode("INTC"));
        } else {
            System.out.println("类型出错  Low");
            this.ErrorMessage();
            return;
        }
    }

    public void Top(GrammerNode node) {
        if (Token.token.id.equals("INTC")) {
            this.Match("INTC");
            node.addSon(new GrammerNode("INTC"));
        } else {
            System.out.println("类型出错  Top");
            this.ErrorMessage();
            return;
        }
    }

    public void RecType(GrammerNode node) {
        if (Token.token.id.equals("RECORD")) {
            this.Match("RECORD");
            node.addSon(new GrammerNode("RECORD"));
            this.FieldDecList(node.addSon(new GrammerNode("FieldDecList")));
            this.Match("END");
            node.addSon(new GrammerNode("END"));
        } else {
            System.out.println("类型出错  RecType");
            this.ErrorMessage();
            return;
        }
    }

    public void FieldDecList(GrammerNode node) {
        if (Token.token.id.equals("INTEGER") || Token.token.id.equals("CHAR")) {
            this.BaseType(node.addSon(new GrammerNode("BaseType")));
            this.IdList(node.addSon(new GrammerNode("IdList")));
            this.Match(";");
            node.addSon(new GrammerNode(";"));
            this.FieldDecMore(node.addSon(new GrammerNode("FieldDecMore")));
        } else if (Token.token.id.equals("ARRAY")) {
            this.ArrayType(node.addSon(new GrammerNode("ArrayType")));
            this.IdList(node.addSon(new GrammerNode("IdList")));
            this.Match(";");
            node.addSon(new GrammerNode(";"));
            this.FieldDecMore(node.addSon(new GrammerNode("FieldDecMore")));
        } else {
            System.out.println("类型出错  FieldDecList");
            this.ErrorMessage();
            return;
        }
    }

    public void FieldDecMore(GrammerNode node) {
        if (Token.token.id.equals("END")) {

        } else if (Token.token.id.equals("INTEGER") || Token.token.id.equals("CHAR") || Token.token.id.equals("ARRAY")) {
            this.FieldDecList(node.addSon(new GrammerNode("FieldDecList")));
        } else {
            System.out.println("类型出错  FieldDecMore");
            this.ErrorMessage();
            return;
        }
    }

    public void IdList(GrammerNode node) {
        if (Token.token.id.equals("ID")) {
            this.Match("ID");
            node.addSon(new GrammerNode("ID"));
            this.FidMore(node.addSon(new GrammerNode("FidMore")));

        } else {
            System.out.println("类型出错  IdList");
            this.ErrorMessage();
            return;
        }
    }

    public void IdMore(GrammerNode node) {
        if (Token.token.id.equals(";")) {
        } else if (Token.token.id.equals(",")) {
            this.Match(",");
            node.addSon(new GrammerNode(","));
            this.IdList(node.addSon(new GrammerNode("IdList")));
        } else {
            System.out.println("类型出错  IdMore");
            this.ErrorMessage();
            return;
        }
    }


    //变量声明
    public void VarDecpart(GrammerNode node) {
        if (Token.token.id.equals("PROCEDURE") || Token.token.id.equals("BEGIN")) {
        } else if (Token.token.id.equals("VAR")) {
            this.VarDec(node.addSon(new GrammerNode("VarDec")));
        } else {
            System.out.println("变量声明出错  VarDecpart");
            this.ErrorMessage();
            return;
        }
    }

    public void VarDec(GrammerNode node) {
        if (Token.token.id.equals("VAR")) {
            this.Match("VAR");
            node.addSon(new GrammerNode("VAR"));
            this.VarDecList(node.addSon(new GrammerNode("VarDecList")));
        } else {
            System.out.println("变量声明出错  VarDec");
            this.ErrorMessage();
            return;
        }
    }

    public void VarDecList(GrammerNode node) {
        if (Token.token.id.equals("INTEGER") || Token.token.id.equals("CHAR") || Token.token.id.equals("ARRAY") || Token.token.id.equals("RECORD") || Token.token.id.equals("ID")) {
            this.TypeDef(node.addSon(new GrammerNode("TypeDef")));
            this.VarIdList(node.addSon(new GrammerNode("VarIdList")));
            this.Match(";");
            node.addSon(new GrammerNode(";"));
            this.VarDecMore(node.addSon(new GrammerNode("VarDecMore")));
        } else {
            System.out.println("变量声明出错  VarDecList");
            this.ErrorMessage();
            return;
        }
    }

    public void VarDecMore(GrammerNode node) {
        if (Token.token.id.equals("PROCEDURE") || Token.token.id.equals("BEGIN")) {
        } else if (Token.token.id.equals("INTEGER") || Token.token.id.equals("CHAR") || Token.token.id.equals("ARRAY") || Token.token.id.equals("RECORD") || Token.token.id.equals("ID")) {
            this.VarDecList(node.addSon(new GrammerNode("VarDecList")));
        } else {
            System.out.println("变量声明出错  VarDecMore");
            this.ErrorMessage();
            return;
        }
    }

    public void VarIdList(GrammerNode node) {
        if (Token.token.id.equals("ID")) {
            this.Match("ID");
            node.addSon(new GrammerNode("ID"));
            this.VarIdMore(node.addSon(new GrammerNode("VarIdMore")));
        } else {
            System.out.println("变量声明出错  VarIdList");
            this.ErrorMessage();
            return;
        }
    }

    public void VarIdMore(GrammerNode node) {
        if (Token.token.id.equals(";")) {
        } else if (Token.token.id.equals(",")) {
            this.Match(",");
            node.addSon(new GrammerNode(","));
            this.VarIdList(node.addSon(new GrammerNode("VarIdList")));
        } else {
            System.out.println("变量声明出错  VarIdMore");
            this.ErrorMessage();
            return;
        }
    }

    //过程声明
    public void ProcDecpart(GrammerNode node) {
        if (Token.token.id.equals("BEGIN")) {
        } else if (Token.token.id.equals("PROCEDURE")) {
            this.ProcDec(node.addSon(new GrammerNode("ProcDec")));
        } else {
            System.out.println("过程声明出错  ProcDecpart");
            this.ErrorMessage();
            return;
        }
    }


    public void ProcDec(GrammerNode node) {
        if (Token.token.id.equals("PROCEDURE")) {
            this.Match("PROCEDURE");
            // System.out.println("函数ProcDec已匹配PROCEDURE******");
            this.ProcName(node.addSon(new GrammerNode("ProcName")));
            this.Match("(");
            node.addSon(new GrammerNode("("));
            this.ParamList(node.addSon(new GrammerNode("ParamList")));
            this.Match(")");
            node.addSon(new GrammerNode(")"));
            this.Match(";");
            node.addSon(new GrammerNode(";"));
            this.DeclarePart(node.addSon(new GrammerNode("DeclarePart")));/************************修改*******************************************/
            this.ProcBody(node.addSon(new GrammerNode("ProcBody")));
            this.ProcDecMore(node.addSon(new GrammerNode("ProcDecMore")));
        } else {
            System.out.println("过程声明出错  ProcDec");
            this.ErrorMessage();
            return;
        }
    }

    public void ProcDecMore(GrammerNode node) {
    }

    public void ProcName(GrammerNode node) {
        if (Token.token.id.equals("ID")) {
            this.Match("ID");
            node.addSon(new GrammerNode("ID"));
        } else {
            System.out.println("过程声明出错  ProcName");
            this.ErrorMessage();
            return;
        }
    }

    //参数声明
    public void ParamList(GrammerNode node) {
        if (Token.token.id.equals(")")) {
        } else if (Token.token.id.equals("INTEGER") || Token.token.id.equals("CHAR") || Token.token.id.equals("ARRAY") || Token.token.id.equals("RECORD") || Token.token.id.equals("ID")) {
            this.ParamDecList(node.addSon(new GrammerNode("ParamDecList")));
        } else {
            System.out.println("参数声明出错  ParamList");
            this.ErrorMessage();
            return;
        }
    }

    public void ParamDecList(GrammerNode node) {
        if (Token.token.id.equals("INTEGER") || Token.token.id.equals("CHAR") || Token.token.id.equals("ARRAY") || Token.token.id.equals("RECORD") || Token.token.id.equals("ID")) {
            this.Param(node.addSon(new GrammerNode("Param")));
            this.ParamMore(node.addSon(new GrammerNode("ParamMore")));
        } else {
            System.out.println("参数声明出错  ParamDecList");
            this.ErrorMessage();
            return;
        }
    }

    public void ParamMore(GrammerNode node) {
        if (Token.token.id.equals(")")) {
        } else if (Token.token.id.equals(";")) {
            this.Match(";");
            node.addSon(new GrammerNode(";"));
            this.ParamDecList(node.addSon(new GrammerNode("ParamDecList")));
        } else {
            System.out.println("参数声明出错  ParamMore");
            this.ErrorMessage();
            return;
        }
    }

    public void Param(GrammerNode node) {
        if (Token.token.id.equals("INTEGER") || Token.token.id.equals("CHAR") || Token.token.id.equals("ARRAY") || Token.token.id.equals("RECORD") || Token.token.id.equals("ID")) {
            this.TypeDef(node.addSon(new GrammerNode("TypeDef")));
            this.FormList(node.addSon(new GrammerNode("FormList")));

        } else if (Token.token.id.equals("VAR")) {
            this.Match("VAR");
            node.addSon(new GrammerNode("VAR"));
            this.TypeDef(node.addSon(new GrammerNode("TypeDef")));
            this.FormList(node.addSon(new GrammerNode("FormList")));
        } else {
            System.out.println("参数声明出错  Param");
            this.ErrorMessage();
            return;
        }
    }

    public void FormList(GrammerNode node) {
        if (Token.token.id.equals("ID")) {
            this.Match("ID");
            node.addSon(new GrammerNode("ID"));
            //System.out.println("FormList函数已匹配ID******");
            this.FidMore(node.addSon(new GrammerNode("FidMore")));
        } else {
            System.out.println("参数声明出错  FormList");
            this.ErrorMessage();
            return;
        }
    }

    public void FidMore(GrammerNode node) {
        //System.out.println("FidMore函数中Token.token.id="+Token.token.id);
        if (Token.token.id.equals(")") || Token.token.id.equals(";")) {

        } else if (Token.token.id.equals(",")) {
            this.Match(",");
            node.addSon(new GrammerNode(","));
            this.FormList(node.addSon(new GrammerNode("FormList")));
        } else {

            System.out.println("参数声明出错  FidMore");
            this.ErrorMessage();
            return;
        }
    }

    //过程中的声明部分
    public void ProcDecPart(GrammerNode node) {
        if (Token.token.id.equals("TYPE") || Token.token.id.equals("VAR") || Token.token.id.equals("PROCEDURE") || Token.token.id.equals("BEGIN")) {
            this.DeclarePart(node.addSon(new GrammerNode("DeclarePart")));
        } else {
            System.out.println("过程中的声明部分出错  ProcDecPart");
            this.ErrorMessage();
            return;
        }
    }

    //过程体
    public void ProcBody(GrammerNode node) {
        if (Token.token.id.equals("BEGIN")) {
            this.ProgramBody(node.addSon(new GrammerNode("ProgramBody")));
        } else {
            System.out.println("过程体出错  ProcBody");
            this.ErrorMessage();
            return;
        }
    }

    //主程序体
    public void ProgramBody(GrammerNode node) {
        if (Token.token.id.equals("BEGIN")) {
            this.Match("BEGIN");
            node.addSon(new GrammerNode("BEGIN"));
            this.StmList(node.addSon(new GrammerNode("StmList")));
            this.Match("END");
            node.addSon(new GrammerNode("END"));
        } else {
            System.out.println("主程序体出错  ProgramBody");
            this.ErrorMessage();
            return;
        }
    }

    //语句序列
    public void StmList(GrammerNode node) {
        if (Token.token.id.equals("IF") || Token.token.id.equals("WHILE") || Token.token.id.equals("READ") || Token.token.id.equals("WRITE") || Token.token.id.equals("RETURN") || Token.token.id.equals("ID")) {
            this.Stm(node.addSon(new GrammerNode("Stm")));
            this.StmMore(node.addSon(new GrammerNode("StmMore")));
        } else {
            System.out.println("语句序列出错  StmList");
            this.ErrorMessage();
            return;
        }
    }

    public void StmMore(GrammerNode node) {
        if (Token.token.id.equals("END") || Token.token.id.equals("ELSE") || Token.token.id.equals("FI") || Token.token.id.equals("ENDWH")) {
        } else if (Token.token.id.equals(";")) {
            this.Match(";");
            node.addSon(new GrammerNode(";"));
            this.StmList(node.addSon(new GrammerNode("StmList")));
        } else {
            System.out.println("语句序列出错  StmMore");
            this.ErrorMessage();
            return;
        }
    }

    //语句
    public void Stm(GrammerNode node) {
        if (Token.token.id.equals("IF")) {
            this.ConditionalStm(node.addSon(new GrammerNode("ConditionalStm")));
        } else if (Token.token.id.equals("WHILE")) {
            this.LoopStm(node.addSon(new GrammerNode("LoopStm")));
        } else if (Token.token.id.equals("READ")) {
            this.InputStm(node.addSon(new GrammerNode("InputStm")));
        } else if (Token.token.id.equals("WRITE")) {
            this.OutputStm(node.addSon(new GrammerNode("OutputStm")));
        } else if (Token.token.id.equals("RETURN")) {
            this.ReturnStm(node.addSon(new GrammerNode("ReturnStm")));
        } else if (Token.token.id.equals("ID")) {
            this.Match("ID");
            node.addSon(new GrammerNode("ID"));
            this.AssCall(node.addSon(new GrammerNode("AssCall")));
        } else {
            System.out.println("语句出错  Stm");
            this.ErrorMessage();
            return;
        }
    }

    public void AssCall(GrammerNode node) {
        if (Token.token.id.equals(".") || Token.token.id.equals("[") || Token.token.id.equals(":=")) {
            this.AssignmentRest(node.addSon(new GrammerNode("AssignmentRest")));
        } else if (Token.token.id.equals("(")) {
            this.CallStmRest(node.addSon(new GrammerNode("CallStmRest")));
        } else {
            System.out.println("语句出错  AssCall");
            this.ErrorMessage();
            return;
        }
    }

    public void AssignmentRest(GrammerNode node) {
        if (Token.token.id.equals(".") || Token.token.id.equals("[") || Token.token.id.equals(":=")) {
            this.VariMore(node.addSon(new GrammerNode("VariMore")));
            this.Match(":=");
            node.addSon(new GrammerNode(":="));
            this.Exp(node.addSon(new GrammerNode("Exp")));
        } else {
            System.out.println("语句出错  AssignmentRest");
            this.ErrorMessage();
            return;
        }
    }

    public void ConditionalStm(GrammerNode node) {
        if (Token.token.id.equals("IF")) {
            this.Match("IF");
            node.addSon(new GrammerNode("IF"));
            this.RelExp(node.addSon(new GrammerNode("RelExp")));
            this.Match("THEN");
            node.addSon(new GrammerNode("THEN"));
            this.StmList(node.addSon(new GrammerNode("StmList")));
            this.Match("ELSE");
            node.addSon(new GrammerNode("ELSE"));
            this.StmList(node.addSon(new GrammerNode("StmList")));
            this.Match("FI");
            node.addSon(new GrammerNode("FI"));
        } else {
            System.out.println("语句出错  ConditionalStm");
            this.ErrorMessage();
            return;
        }
    }

    public void LoopStm(GrammerNode node) {
        if (Token.token.id.equals("WHILE")) {
            this.Match("WHILE");
            node.addSon(new GrammerNode("WHILE"));
            this.RelExp(node.addSon(new GrammerNode("RelExp")));
            this.Match("DO");
            node.addSon(new GrammerNode("DO"));
            this.StmList(node.addSon(new GrammerNode("StmList")));
            this.Match("ENDWH");
            node.addSon(new GrammerNode("ENDWH"));
        } else {
            System.out.println("语句出错  LoopStm");
            this.ErrorMessage();
            return;
        }
    }

    public void InputStm(GrammerNode node) {
        if (Token.token.id.equals("READ")) {
            this.Match("READ");
            node.addSon(new GrammerNode("READ"));

            this.Match("(");
            node.addSon(new GrammerNode("("));
            this.Invar(node.addSon(new GrammerNode("Invar")));
            this.Match(")");
            node.addSon(new GrammerNode(")"));
        } else {
            System.out.println("语句出错  InputStm");
            this.ErrorMessage();
            return;
        }
    }

    public void Invar(GrammerNode node) {
        if (Token.token.id.equals("ID")) {
            this.Match("ID");
            node.addSon(new GrammerNode("ID"));

        } else {
            System.out.println("语句出错  Invar");
            this.ErrorMessage();
            return;
        }
    }

    public void OutputStm(GrammerNode node) {
        if (Token.token.id.equals("WRITE")) {
            this.Match("WRITE");
            node.addSon(new GrammerNode("WRITE"));

            this.Match("(");
            node.addSon(new GrammerNode("("));
            this.Exp(node.addSon(new GrammerNode("Exp")));
            this.Match(")");
            node.addSon(new GrammerNode(")"));
        } else {
            System.out.println("语句出错  OutputStm");
            this.ErrorMessage();
            return;
        }
    }

    public void ReturnStm(GrammerNode node) {
        if (Token.token.id.equals("RETURN")) {
            this.Match("RETURN");
            node.addSon(new GrammerNode("RETURN"));

        } else {
            System.out.println("语句出错  ReturnStm");
            this.ErrorMessage();
            return;
        }
    }

    //过程调用
    public void CallStmRest(GrammerNode node) {
        if (Token.token.id.equals("(")) {
            this.Match("(");
            node.addSon(new GrammerNode("("));
            this.ActParamList(node.addSon(new GrammerNode("ActParamList")));
            this.Match(")");
            node.addSon(new GrammerNode(")"));
        } else {
            System.out.println("过程调用出错  CallStmRest");
            this.ErrorMessage();
            return;
        }
    }

    public void ActParamList(GrammerNode node) {
        if (Token.token.id.equals(")")) {
        } else if (Token.token.id.equals("(") || Token.token.id.equals("INTC") || Token.token.id.equals("ID")) {
            this.Exp(node.addSon(new GrammerNode("Exp")));
            this.ActParamMore(node.addSon(new GrammerNode("ActParamMore")));
        } else {
            System.out.println("过程调用出错  ActParamList");
            this.ErrorMessage();
            return;
        }
    }

    public void ActParamMore(GrammerNode node) {
        if (Token.token.id.equals(")")) {
        } else if (Token.token.id.equals(",")) {
            this.Match(",");
            node.addSon(new GrammerNode(","));
            this.ActParamList(node.addSon(new GrammerNode("ActParamList")));
        } else {
            System.out.println("过程调用出错  ActParamMore");
            this.ErrorMessage();
            return;
        }
    }

    //条件表达式
    public void RelExp(GrammerNode node) {
        if (Token.token.id.equals("(") || Token.token.id.equals("INTC") || Token.token.id.equals("ID")) {
            this.Exp(node.addSon(new GrammerNode("Exp")));
            this.OtherRelE(node.addSon(new GrammerNode("OtherRelE")));
        } else {
            System.out.println("条件表达式出错  RelExp");
            this.ErrorMessage();
            return;
        }
    }

    public void OtherRelE(GrammerNode node) {
        if (Token.token.id.equals("<") || Token.token.id.equals("=")) {
            this.CmpOp(node.addSon(new GrammerNode("CmpOp")));
            this.Exp(node.addSon(new GrammerNode("Exp")));
        } else {
            System.out.println("条件表达式出错  OtherRelE");
            this.ErrorMessage();
            return;
        }
    }

    //算术表达式
    public void Exp(GrammerNode node) {
        if (Token.token.id.equals("(") || Token.token.id.equals("INTC") || Token.token.id.equals("ID")) {
            this.Term(node.addSon(new GrammerNode("Term")));
            this.OtherTerm(node.addSon(new GrammerNode("OtherTerm")));
        } else {
            System.out.println("算术表达式出错  Exp");
            this.ErrorMessage();
            return;
        }
    }

    public void OtherTerm(GrammerNode node) {
        if (Token.token.id.equals(")")
                || Token.token.id.equals("]")
                || Token.token.id.equals("DO")
                || Token.token.id.equals("THEN")
                || Token.token.id.equals("<")
                || Token.token.id.equals("=")
                || Token.token.id.equals(",")
                || Token.token.id.equals(";")
                || Token.token.id.equals("END")
                || Token.token.id.equals("ELSE")
                || Token.token.id.equals("FI")
                || Token.token.id.equals("ENDWH")) {

        } else if (Token.token.id.equals("+") || Token.token.id.equals("-")) {
            this.AddOp(node.addSon(new GrammerNode("AddOp")));
            this.Exp(node.addSon(new GrammerNode("Exp")));
        } else {
            System.out.println("算术表达式出错  OtherTerm");
            this.ErrorMessage();
            return;
        }
    }

    //项
    public void Term(GrammerNode node) {
        if (Token.token.id.equals("(") || Token.token.id.equals("INTC") || Token.token.id.equals("ID")) {
            this.Factor(node.addSon(new GrammerNode("Factor")));
            this.OtherFactor(node.addSon(new GrammerNode("OtherFactor")));
        } else {
            System.out.println("项出错  Term");
            this.ErrorMessage();
            return;
        }
    }

    public void OtherFactor(GrammerNode node) {
        if (Token.token.id.equals("+")
                || Token.token.id.equals("-")
                || Token.token.id.equals(")")
                || Token.token.id.equals("]")
                || Token.token.id.equals("DO")
                || Token.token.id.equals("THEN")
                || Token.token.id.equals("<")
                || Token.token.id.equals("=")
                || Token.token.id.equals(",")
                || Token.token.id.equals(";")
                || Token.token.id.equals("END")
                || Token.token.id.equals("ELSE")
                || Token.token.id.equals("FI")
                || Token.token.id.equals("ENDWH")) {

        } else if (Token.token.id.equals("*") || Token.token.id.equals("/")) {
            this.MultOp(node.addSon(new GrammerNode("MultOp")));
            this.Term(node.addSon(new GrammerNode("Term")));
        } else {
            System.out.println("项出错  OtherFactor");
            this.ErrorMessage();
            return;
        }
    }

    public void Factor(GrammerNode node) {
        if (Token.token.id.equals("(")) {
            this.Match("(");
            node.addSon(new GrammerNode("("));
            this.Exp(node.addSon(new GrammerNode("Exp")));
            this.Match(")");
            node.addSon(new GrammerNode(")"));
        } else if (Token.token.id.equals("INTC")) {
            this.Match("INTC");
            node.addSon(new GrammerNode("INTC"));
        } else if (Token.token.id.equals("ID")) {
            this.Variable(node.addSon(new GrammerNode("Variable")));
        } else {
            System.out.println("项出错  Factor");
            this.ErrorMessage();
            return;
        }
    }

    public void Variable(GrammerNode node) {
        if (Token.token.id.equals("ID")) {
            this.Match("ID");
            node.addSon(new GrammerNode("ID"));
            this.VariMore(node.addSon(new GrammerNode("VariMore")));
        } else {
            System.out.println("项出错  Variable");
            this.ErrorMessage();
            return;
        }
    }

    public void VariMore(GrammerNode node) {
        if (Token.token.id.equals("+")
                || Token.token.id.equals("*")
                || Token.token.id.equals("/")
                || Token.token.id.equals("-")
                || Token.token.id.equals(")")
                || Token.token.id.equals("]")
                || Token.token.id.equals("DO")
                || Token.token.id.equals("THEN")
                || Token.token.id.equals("<")
                || Token.token.id.equals("=")
                || Token.token.id.equals(",")
                || Token.token.id.equals(";")
                || Token.token.id.equals("END")
                || Token.token.id.equals("ELSE")
                || Token.token.id.equals("FI")
                || Token.token.id.equals("ENDWH")
                || Token.token.id.equals(":=")) {

        } else if (Token.token.id.equals("[")) {
            this.Match("[");
            node.addSon(new GrammerNode("["));
            this.Exp(node.addSon(new GrammerNode("Exp")));
            this.Match("]");
            node.addSon(new GrammerNode("]"));
        } else if (Token.token.id.equals(".")) {
            this.Match(".");
            node.addSon(new GrammerNode("."));
            this.FieldVar(node.addSon(new GrammerNode("FieldVar")));
        } else {
            System.out.println("项出错  VariMore");
            this.ErrorMessage();
            return;
        }

    }

    public void FieldVar(GrammerNode node) {
        if (Token.token.id.equals("ID")) {
            this.Match("ID");
            node.addSon(new GrammerNode("ID"));
            this.FieldVarMore(node.addSon(new GrammerNode("FieldVarMore")));
        } else {
            System.out.println("项出错  FieldVar");
            this.ErrorMessage();
            return;
        }

    }

    public void FieldVarMore(GrammerNode node) {
        if (Token.token.id.equals("+")
                || Token.token.id.equals("*")
                || Token.token.id.equals("/")
                || Token.token.id.equals("-")
                || Token.token.id.equals(")")
                || Token.token.id.equals("]")
                || Token.token.id.equals("DO")
                || Token.token.id.equals("THEN")
                || Token.token.id.equals("<")
                || Token.token.id.equals("=")
                || Token.token.id.equals(",")
                || Token.token.id.equals(";")
                || Token.token.id.equals("END")
                || Token.token.id.equals("ELSE")
                || Token.token.id.equals("FI")
                || Token.token.id.equals("ENDWH")
                || Token.token.id.equals(":=")) {

        } else if (Token.token.id.equals("[")) {
            this.Match("[");
            node.addSon(new GrammerNode("["));
            this.Exp(node.addSon(new GrammerNode("Exp")));
            this.Match("]");
            node.addSon(new GrammerNode("]"));
        } else {
            System.out.println("项出错  VariMore");
            this.ErrorMessage();
            return;
        }
    }

    public void CmpOp(GrammerNode node) {
        if (Token.token.id.equals("<")) {
            this.Match("<");
            node.addSon(new GrammerNode("<"));

        } else if (Token.token.id.equals("=")) {
            this.Match("=");
            node.addSon(new GrammerNode("="));
        } else {
            System.out.println("项出错  CmpOp");
            this.ErrorMessage();
            return;
        }


    }

    public void AddOp(GrammerNode node) {
        if (Token.token.id.equals("+")) {
            this.Match("+");
            node.addSon(new GrammerNode("+"));

        } else if (Token.token.id.equals("-")) {
            this.Match("-");
            node.addSon(new GrammerNode("-"));
        } else {
            System.out.println("项出错  AddOp");
            this.ErrorMessage();
            return;
        }

    }

    public void MultOp(GrammerNode node) {
        if (Token.token.id.equals("*")) {
            this.Match("*");
            node.addSon(new GrammerNode("*"));

        } else if (Token.token.id.equals("/")) {
            this.Match("/");
            node.addSon(new GrammerNode("/"));
        } else {
            System.out.println("项出错  MultOp");
            this.ErrorMessage();
            return;
        }

    }

}

