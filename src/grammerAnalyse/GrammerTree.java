package grammerAnalyse;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

class GrammerNode {
    public int drawSonNumber;
    public List<GrammerNode> grammerTreeSon;
    public String Name;
    public int x;

    public boolean isFirstSon;
    public int[] drawx;

    public GrammerNode(String Name) {
        this.Name = Name;
        this.drawSonNumber = 0;
        this.x = 0;

        this.isFirstSon = false;
        this.drawx = new int[50];
        for (int i = 0; i < 50; i++) {
            drawx[i] = 1;
        }
        grammerTreeSon = new ArrayList<GrammerNode>();
    }

    public GrammerNode addSon(GrammerNode node) {
        if (this.grammerTreeSon.isEmpty()) {
            node.isFirstSon = true;
        }
        node.x = this.x + 1;
        this.grammerTreeSon.add(node);
        this.drawSonNumber++;
        return node;
    }
}

public class GrammerTree {
    public GrammerTree() {

    }

    public static int rowL = 2;
    public static int letterL = 15;
    public static GrammerNode root = new GrammerNode("Program");

    public void setDrawW(GrammerNode node) {
        if (!node.grammerTreeSon.isEmpty()) {
            int n = node.grammerTreeSon.get(node.grammerTreeSon.size() - 1).x - 1;
            for (int i = 0; i < node.grammerTreeSon.get(node.grammerTreeSon.size() - 1).grammerTreeSon.size(); i++) {
                node.grammerTreeSon.get(node.grammerTreeSon.size() - 1).grammerTreeSon.get(i).drawx[n] = 0;
            }
        }

        for (int j = 0; j < node.grammerTreeSon.size(); j++) {
            for (int i = 0; i < node.x - 1; i++) {
                node.grammerTreeSon.get(j).drawx[i] = node.drawx[i];
            }
            setDrawW(node.grammerTreeSon.get(j));
        }
    }

    public void drawSubTree(GrammerNode node, BufferedWriter bw) throws Exception {

        for (int j = 0; j < node.grammerTreeSon.size(); j++) {

            if (node.grammerTreeSon.get(j).isFirstSon) {
                //System.out.print("--- "+node.grammerTreeSon.get(j).Name);
                bw.write("--- " + node.grammerTreeSon.get(j).Name);
                for (int space = 0; space < 11 - node.grammerTreeSon.get(j).Name.length(); space++) {
                    bw.write(" ");
                }
                drawSubTree(node.grammerTreeSon.get(j), bw);
            } else {
                bw.newLine();
                for (int i = 0; i < node.grammerTreeSon.get(j).x; i++) {
                    if (node.grammerTreeSon.get(j).drawx[i] == 1)
                        bw.write("                   | ");
                    else
                        bw.write("                     ");
                }


                bw.newLine();
                for (int i = 0; i < node.grammerTreeSon.get(j).x; i++) {
                    if (node.grammerTreeSon.get(j).drawx[i] == 1)
                        bw.write("                   | ");
                    else
                        bw.write("                     ");
                }

                bw.write(node.grammerTreeSon.get(j).Name);
                for (int space = 0; space < 11 - node.grammerTreeSon.get(j).Name.length(); space++) {
                    bw.write(" ");
                }
                drawSubTree(node.grammerTreeSon.get(j), bw);
            }
        }

    }


    public void drawSubTreeText(GrammerNode node, BufferedWriter bw) throws Exception {

        for (int j = 0; j < node.grammerTreeSon.size(); j++) {

            if (node.grammerTreeSon.get(j).isFirstSon) {
                //System.out.print("--- "+node.grammerTreeSon.get(j).Name);
                bw.write("  " + node.grammerTreeSon.get(j).Name);
                for (int space = 0; space < 11 - node.grammerTreeSon.get(j).Name.length(); space++) {
                    bw.write(" ");
                }
                drawSubTreeText(node.grammerTreeSon.get(j), bw);
            } else {
                bw.newLine();
                for (int i = 0; i < node.grammerTreeSon.get(j).x; i++) {
                    if (node.grammerTreeSon.get(j).drawx[i] == 1)
                        bw.write("                 ");
                    else
                        bw.write("                 ");
                }


                bw.newLine();
                for (int i = 0; i < node.grammerTreeSon.get(j).x; i++) {
                    if (node.grammerTreeSon.get(j).drawx[i] == 1)
                        bw.write("                 ");
                    else
                        bw.write("                 ");
                }

                bw.write(node.grammerTreeSon.get(j).Name);
                for (int space = 0; space < 11 - node.grammerTreeSon.get(j).Name.length(); space++) {
                    bw.write(" ");
                }
                drawSubTreeText(node.grammerTreeSon.get(j), bw);
            }
        }

    }
}
