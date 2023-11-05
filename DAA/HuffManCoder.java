package Heap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HuffManCoder {
    HashMap<Character,String> encoder;
    HashMap<String ,Character> decoder;
    private class Node implements Comparable<Node>{
        Character data;
        int cost ; //frequency
        Node left;
        Node right;
        public Node(Character data,int cost){
            this.data=data;
            this.cost =cost;
            this.left=null;
            this.right=null;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    public HuffManCoder(String feeder) throws Exception{
                HashMap<Character,Integer> freq =new HashMap<>();
            for (int i = 0; i < feeder.length() ; i++) {
                char cc =feeder.charAt(i);
                if(freq.containsKey(cc)){
                    int ov =freq.get(cc);
                    ov += 1;
                    freq.put(cc,ov);
                }else{
                    freq.put(cc,1);
                }
            }
            Heap1<Node> heap =new Heap1<>();
            Set<Map.Entry<Character,Integer>> set =freq.entrySet();
            for(Map.Entry<Character,Integer> entry:set){
                Node node =new Node(entry.getKey(),entry.getValue());
                heap.insert(node);
            }
            while (heap.size()!=1){
                Node first =heap.remove();
                Node second =heap.remove();
                Node newNode =new Node('\0',first.cost +second.cost);
                newNode.left =first;
                newNode.right =second;

                heap.insert(newNode);
            }
            Node ft =heap.remove();
            this.encoder =new HashMap<>();
            this.decoder =new HashMap<>();
            this.initEncoderDecoder(ft,"");


    }

    private void initEncoderDecoder(Node node, String s) {
        if(node==null){
            return;
        }
        if(node.left==null && node.right==null){
            this.encoder.put(node.data,s);
            this.decoder.put(s,node.data);

        }
        initEncoderDecoder(node.left,s+"0");
        initEncoderDecoder(node.right,s+"1");
    }
    public String encode(String source){
        String ans="";
        for (int i = 0; i < source.length(); i++) {
            ans = ans +encoder.get(source.charAt(i));
        }
        return ans;
    }
    public String decode(String codedString){
        String  key ="";
        String ans ="";
        for (int i = 0; i < codedString.length(); i++) {
                key =key + codedString.charAt(i);
                if(decoder.containsKey(key)){
                    ans =ans+ decoder.get(key);
                    key="";
                }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
            String str ="abbccda";
            HuffManCoder hf =new HuffManCoder(str);
            String cs = hf.encode(str);
        System.out.println(cs);
            String  ds =hf.decode(cs);
        System.out.println("decoded string "+ ds);
    }
}
