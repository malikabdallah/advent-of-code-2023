package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int main1 = PokerHandEvaluator.evaluateHand("AAAAA");
        int main2 = PokerHandEvaluator.evaluateHand("333EA");
        int main3 = PokerHandEvaluator.evaluateHand("12345");
        int main4 = PokerHandEvaluator.evaluateHand("22AQS");
        int main5 = PokerHandEvaluator.evaluateHand("55557");
        int main6 = PokerHandEvaluator.evaluateHand("2233A");
        int main7 = PokerHandEvaluator.evaluateHand("QQQJJ");

        int x = 5;

        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input.txt");

        List<String> mains = new ArrayList<>();
        List<String> bets = new ArrayList<>();

        Map<String,String>map=new HashMap<>();
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String main = line.split(" ")[0];
                    String bet = line.split(" ")[1];
                    mains.add(main);
                    bets.add(bet);
                    map.put(main,bet);
                }

                String []mainsArray=mains.toArray(new String[0]);
                Arrays.sort(mainsArray,new PokerHandComparator());
                long cpt=0;
                for(int i=0;i<=mainsArray.length-1;i++){
                    long val=(i+1)*Long.parseLong(map.get(mainsArray[i]));
                    cpt=cpt+val;

                }
                System.out.println(cpt);
                int z=0;
            } catch (Exception e) {

            }
        }


    }

}

 class PokerHandComparator implements Comparator<String> {





     @Override
    public int compare(String hand1, String hand2) {

        int h1=PokerHandEvaluator.evaluateHand(hand1);
        int h2=PokerHandEvaluator.evaluateHand(hand2);

        if(h1<h2){
            return -1;
        }else if(h1>h2){
            return 1;
        }

        for(int i=0;i<=hand1.length()-1;i++){
            int v1=power(hand1.charAt(i));
            int v2=power(hand2.charAt(i));
            if(v1>v2){
                return 1;
            }else if(v1<v2){
                return -1;
            }
        }



/*
         Character[] characters1 = new Character[hand1.length()];
         for (int i = 0; i < hand1.length(); i++) {
             characters1[i] = hand1.charAt(i);
         }
         Arrays.sort(characters1, Comparator.comparingInt(PokerHandComparator::power));


         Character[] characters2 = new Character[hand2.length()];
         for (int i = 0; i < hand2.length(); i++) {
             characters2[i] = hand2.charAt(i);
         }
         Arrays.sort(characters1, Comparator.comparingInt(PokerHandComparator::power));
         Arrays.sort(characters2, Comparator.comparingInt(PokerHandComparator::power));

         for(int i=0;i<=characters1.length;i++){
             int v1=power(characters1[1]);
             int v2=power(characters2[1]);
             if(v1!=v2){
                 if(v1>v2){
                     return 1;
                 }else{
                     return 2;
                 }
             }

         }

 */

            return 0;

    }

     private static int power(Character car) {
          int cpt=0;
                 switch (car){

                     case 'A':
                         cpt=cpt+13;
                         break;
                     case 'K':
                         cpt=cpt+12;
                         break;
                     case 'Q':
                         cpt=cpt+11;
                         break;
                     case 'T':
                         cpt=cpt+10;
                         break;
                     case '9':
                         cpt=cpt+9;
                         break;
                     case '8':
                         cpt=cpt+8;
                         break;
                     case '7':
                         cpt=cpt+7;
                         break;
                     case '6':
                         cpt=cpt+6;
                         break;
                     case '5':
                         cpt=cpt+5;
                         break;
                     case '4':
                         cpt=cpt+4;
                         break;
                     case '3':
                         cpt=cpt+3;
                         break;
                     case '2':
                         cpt=cpt+2;
                         break;
                 }

             return cpt;

     }
 }



 class PokerHandEvaluator {


    public static int evaluateHand(String hand) {


        // Count occurrences of each card
        Map<String, Integer> cardCounts = new HashMap<>();
        for (Character card : hand.toCharArray()) {
            int x=41;
            if(!cardCounts.containsKey(card.toString())){
                cardCounts.put(card.toString(),  1);

            }else{
                cardCounts.put(card.toString(), cardCounts.get(card.toString())+1);

            }
        }

        // Check for different hand types
        if (fiveOfKind(cardCounts)) {
            return 6; // Royal Flush
        } else if (containsFourOfAKind(cardCounts)) {
            return 5; // Four of a Kind
        } else if (containsFullHouse(cardCounts)) {
            return 4; // Full House
        }else if (containsThreeOfAKind(cardCounts)) {
            return 3; // Three of a Kind
        } else if (containsTwoPair(cardCounts)) {
            return 2; // Two Pair
        } else if (containsOnePair(cardCounts)) {
            return 1; // One Pair
        } else {
            return 0; // High Card
        }
    }

    public static boolean containsOnePair(Map<String, Integer> cardCounts) {
        int cpt=0;
        for(String s:cardCounts.keySet()){
            if(cardCounts.get(s)==2){
                return true;
            }
        }
        return false;
    }

    public static boolean containsTwoPair(Map<String, Integer> cardCounts) {

        int cpt=0;
        for(String s:cardCounts.keySet()){
            if(cardCounts.get(s)==2){
                cpt++;
            }
        }
        return cpt==2;
    }

    public static boolean containsThreeOfAKind(Map<String, Integer> cardCounts) {
        for(String s:cardCounts.keySet()){
            if(cardCounts.get(s)==3){
               return true;
            }
        }
        return false;
    }

    public static boolean containsFullHouse(Map<String, Integer> cardCounts) {
        boolean three=false;
        boolean two=false;

        for(String s:cardCounts.keySet()){
            if(cardCounts.get(s)==2){
                two=true;
            }
            if(cardCounts.get(s)==3){
                three=true;
            }
        }
        return  three==true&&two==true;
    }

    public static boolean fiveOfKind(Map<String,Integer>cards) {
        return cards.size()==1;
    }

    public static boolean containsRoyalFlush(String[] cards) {
        // Your logic to check for a Royal Flush goes here
        // For simplicity, let's assume a Royal Flush is always present
        return true;
    }


    public static boolean containsFourOfAKind(Map<String, Integer> cardCounts) {
        int cpt=0;
        for(String s:cardCounts.keySet()){
            if(cardCounts.get(s)==4){
                cpt++;
            }
        }
        return cpt==1;
    }

    // Implement similar methods for other hand types

}