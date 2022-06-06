package grammerAnalyse;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CodeSNL {
    public static void main(String args[]) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //CodeSNLUI frame = new CodeSNLUI();
                    //frame.setVisible(true);
                    List<Token> tokens = new LinkedList<Token>();
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream("token.txt")));
                    String linestr;//按行读取 将每次读取一行的结果赋值
                    while ((linestr = br.readLine()) != null) {
                        //System.out.println(linestr);//输出每行的信息
                        tokens.add(new Token(linestr));

                    }
                    br.close();//关闭IO


                    Token.tokenList = tokens;
//                    for (int i = 0; i < Token.tokenList.size(); i++) {
//                        System.out.println(Token.tokenList.get(i).id + " ");
//                    }
                    GrammerAny ga = new GrammerAnalyse();
                    ga.ReadToken();
                    ga.Program(GrammerTree.root);
                    ga.Match("#");

                    System.out.println("程序语法分析完成！！！结果语法树已存入D:/tree.txt");
                    System.out.println("程序语法分析完成！！！结果文本已存入D:/treeText.txt");

                    GrammerTree tre = new GrammerTree();
                    //System.out.print(GrammerTree.root.Name);

                    tre.setDrawW(GrammerTree.root);

                    BufferedWriter bw = new BufferedWriter(new FileWriter("tree.txt"));
                    bw.write(GrammerTree.root.Name);
                    tre.drawSubTree(GrammerTree.root, bw);
						/*for(int space=0;space<11-GrammerTree.root.Name.length();space++) {
							bw.write(" ");
						}*/
                    bw.close();

                    BufferedWriter x = new BufferedWriter(new FileWriter("treeText.txt"));
                    x.write(GrammerTree.root.Name);

                    tre.drawSubTreeText(GrammerTree.root, x);
                    x.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

}

