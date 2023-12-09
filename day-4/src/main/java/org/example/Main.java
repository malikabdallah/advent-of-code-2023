package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Main {


    public static Integer parseUnsignedInteger(String s) {
        return Integer.parseInt(s.replaceAll("\\D+", ""));
    }

    public static Integer parseSignedInteger(String s) {
        return Integer.parseInt(s.replaceAll("[^\\d-]+", ""));
    }

    public static List<Integer> extractUnsignedIntegers(String s) {
        var list = new ArrayList<Integer>();

        var p = Pattern.compile("\\d+");
        var m = p.matcher(s);

        while (m.find()) {
            list.add(Integer.parseInt(m.group()));
        }

        return list;
    }

    public static List<Integer> extractSignedIntegers(String s, boolean allowNegative) {
        var list = new ArrayList<Integer>();

        var p = Pattern.compile("-?\\d+");
        var m = p.matcher(s);

        while (m.find()) {
            list.add(Integer.parseInt(m.group()));
        }

        return list;
    }

    public static List<Long> extractUnsignedLongs(String s) {
        var list = new ArrayList<Long>();

        var p = Pattern.compile("\\d+");
        var m = p.matcher(s);

        while (m.find()) {
            list.add(Long.parseLong(m.group()));
        }

        return list;
    }

    public static void part2Solution(String[] input) {
        var sum = 0;
        var numberOfCards = new HashMap<Integer, Integer>();

        for (var line: input) {
            var parts = line.split(": ");
            int cardNumber = parseUnsignedInteger(parts[0]);
            numberOfCards.put(cardNumber, numberOfCards.getOrDefault(cardNumber, 0) + 1);

            var numberList = parts[1].split(" \\| ");
            var winningNumbers = extractUnsignedIntegers(numberList[0]);
            var myNumbers = extractUnsignedIntegers(numberList[1]);
            var matchedNumbersCount = myNumbers.stream().filter(winningNumbers::contains).count();
            var numberOfWonCards = numberOfCards.getOrDefault(cardNumber, 1);

            for (var cardOffset = 1; cardOffset <= matchedNumbersCount; cardOffset++) {
                numberOfCards.put(
                        cardNumber + cardOffset,
                        numberOfCards.getOrDefault(cardNumber + cardOffset, 0) + numberOfWonCards
                );
            }

            sum += numberOfCards.get(cardNumber);
        }

        System.out.println(String.valueOf(sum));
    }


    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input.txt");

        List<String> map=new ArrayList<>();
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    map.add(line);
                }

                String []tab=map.toArray(new String[0]);
                part2Solution(tab);


/*
                int cpt=1;
                Map<Integer,Integer> mapCard=new HashMap<>();
                mapCard.put(cpt,1);
                for(String s:tab){
                    String []firstSplit=s.split(":");
                    String []secondSplit=firstSplit[1].split("\\|");

                    String []winningNumbers=secondSplit[0].split("\\s+");
                    winningNumbers[0]="-1";
                    String []myNumber=secondSplit[1].split("\\s+");
                    myNumber[0]="-2";
                    //int pow=-1;
                    int pow=0;
                    for(String win:winningNumbers){
                        for(String num:myNumber){
                            if(win.equals(num)){
                               pow++;
                            }
                        }
                    }
                    mapCard.put(cpt,pow);
                    cpt++;


                    for(int i=1;i<=pow;i++){
                        if(mapCard.containsKey(cpt+i)){
                            int val=mapCard.get(cpt+i);
                            mapCard.put(cpt+i,val+1);
                        }else{
                            mapCard.put(cpt+i,1);

                        }
                    }
                    cpt++;


                    //cpt= (int) (cpt+Math.pow(2,pow));
                }

                int val=0;

                for(int i:mapCard.keySet()){
                    if(i<=tab.length){
                        val=val+mapCard.get(i);
                    }
                }



              methode(mapCard);
              */

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Hello world!");
    }


    public static void methode(Map<Integer,Integer>mapCard){
        Map<Integer,Integer>save=new HashMap<>();
        save.put(1,1);
        int victory=mapCard.get(1);
        for(int j=2;j<=1+victory;j++){
            if(save.containsKey(j)){
                int valeur=save.get(j);
                valeur++;
                save.put(j,valeur+1);
            }else{
                save.put(j,2);
            }
        }
/*
        int cpt=2;
        while(cpt<=mapCard.size()){
            int victoryCpt=mapCard.get(cpt);
            // recup mon 2
            for(int j=1;j<=victoryCpt;j++){
                if(save.containsKey(cpt+j)){
                    int valeur=save.get(cpt)+save.get(cpt+j);
                    save.put(cpt+j,valeur);
                }
            }
            cpt++;
        }

        cpt=2;
        while(cpt<=mapCard.size()){
                if(!save.containsKey(cpt)){
                    save.put(cpt,1);
                }
                cpt++;
        }



        int compteur=0;
        for(int i=1;i<=mapCard.size();i++){
            if(save.containsKey(i)) {
                compteur = compteur + save.get(i);

            }
        }
        System.out.println(compteur);

*/


    }
}