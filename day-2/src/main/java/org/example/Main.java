package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static Map<String,Integer> map=new HashMap<>();

    public static void main(String[] args) {
        map.put("red",12);
        map.put("blue",14);
        map.put("green",13);
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input.txt");
        int nbGamesAdd=0;
        int powerGames=0;
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                int cpt=0;

                while ((line = reader.readLine()) != null) {
                    // Process each line as needed
                    cpt++;
                    int index=line.indexOf(":");
                    String game=line.substring(index+1);


                    String []games=game.split(";");
                    //boolean possible=true;
                    Map<String,Integer>nbMax=new HashMap<>();
                    nbMax.put("red",0);
                    nbMax.put("green",0);
                    nbMax.put("blue",0);
                    for(String jeu:games){
                        /*
                        if(possible==false){
                            break;
                        }

                         */
                        String[]pioches=jeu.split(",");
                        for(String pioche:pioches){
                            String p=pioche.trim();
                            StringBuilder numberBuilder = new StringBuilder();
                            for(char c:p.toCharArray()){
                                if(Character.isDigit(c)){
                                    numberBuilder.append(c);
                                }else{
                                    break;
                                }
                            }
                            int val=Integer.parseInt(numberBuilder.toString());
                            if(pioche.contains("red")){
                                /*
                                if(val>map.get("red")){
                                   // possible=false;
                                }

                                 */
                                if(nbMax.get("red")<val){
                                    nbMax.put("red",val);
                                }
                            }else if(pioche.contains("green")){
                                if(nbMax.get("green")<val){
                                    nbMax.put("green",val);
                                }
                                /*
                                if(val>map.get("green")){
                                  //  possible=false;
                                }

                                 */
                            }else{
                                if(nbMax.get("blue")<val){
                                    nbMax.put("blue",val);
                                }
                                /*
                                if(val>map.get("blue")){
                                 //   possible=false;
                                }

                                 */

                            }
                        }

                    }
                    int nb=0;
                    nb=nbMax.get("green")*nbMax.get("red")*nbMax.get("blue");
                    powerGames=powerGames+nb;
                    /*
                    if(possible){
                        nbGamesAdd=nbGamesAdd+cpt;
                    }

                     */

                }

                } catch (IOException e) {
                e.printStackTrace(); // Handle the exception according to your needs
            }
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found!");
        }


        System.out.println(powerGames);
    }
}