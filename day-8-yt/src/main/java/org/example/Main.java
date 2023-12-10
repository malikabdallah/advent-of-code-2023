package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void methode1(){
        List<String>input= FileReaderUtil.read("input1.txt");
        int x=0;
        Map<String,Node>nodes=input.stream().skip(2).
                map(s->new Node(s)).collect(Collectors.toMap(n->n.name,n->n));

        String moveOrder=input.get(0);
        int index=0;
        Node currentNode=nodes.get("AAA");
        int steps=0;
        while(!"ZZZ".equals(currentNode.name)){
            char move=moveOrder.charAt(index);
            currentNode=nodes.get(move=='L'?currentNode.left:currentNode.right);
            steps++;
            index=(index+1)%moveOrder.length();
        }

        System.out.println(steps);
    }


    public static void methode2(){
        List<String>input=FileReaderUtil.read("input1.txt");
        String moveAndOrder=input.get(0);
        Map<String,Node>nodes=input.stream().skip(2).
                map(s->new Node(s)).collect(Collectors.toMap(n->n.name,n->n));

        List<Node>startNodes=nodes.values().stream().skip(2).filter(s->s.name.charAt(2)=='A')
                .toList();


        List<Loop>loops=startNodes.stream().map(n->findLoopNodes(n,moveAndOrder,nodes)).toList();

        BigInteger lowercommonmultiplier=loops.stream().map(l->BigInteger.valueOf(l.zIndices.get(0)))
                .reduce((a,b)->lcm(a,b))
                .orElse(BigInteger.ZERO);

        System.out.println(ANSI_GREEN_BACKGROUND+" "+lowercommonmultiplier);
        int x=0;
    }

    private static Loop findLoopNodes(Node n, String moveAndOrder, Map<String, Node> nodes) {

        List<String>visited=new ArrayList<>();
        Node currentNode=n;
        String x=currentNode.name+"-0";
        int index=0;
        while(!visited.contains(x)){
            visited.add(x);
            char move=moveAndOrder.charAt(index);
            currentNode=nodes.get(move=='L'? currentNode.left:currentNode.right);

            x=currentNode.name+"-"+index;
            index = (index+1)%moveAndOrder.length();

        }

        Loop loop=new Loop();
        loop.loopStart=visited.indexOf(x);
        loop.size=visited.size()-loop.loopStart;
        for(int i=0;i<visited.size();i++){
            if(visited.get(i).charAt(2)=='Z'){
                loop.zIndices.add(i);
            }
        }
        System.out.println(ANSI_BLUE_BACKGROUND+"# "+n.name+" "+loop.loopStart+" "+visited.size()+" "+
                loop.zIndices.stream().map(i->""+i).collect(Collectors.joining(",")));

        return loop;


    }

    public static void main(String[] args) {
      //  methode1();
        methode2();

    }


    public static BigInteger lcm(BigInteger number1, BigInteger number2) {
        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd);
    }


    static class Node{
        String name,left,right;

        public Node(String s){
            name=s.substring(0,3);
            left=s.substring(7,10);
            right=s.substring(12,15);
        }

    }

    static class Loop{
        int loopStart;
        int size;
        List<Integer>zIndices=new ArrayList<>();
    }
}