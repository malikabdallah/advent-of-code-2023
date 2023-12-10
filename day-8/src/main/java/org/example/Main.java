package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input.txt");


        int cpt=0;
        String ordre="";
        List<String>possibilites=new ArrayList<>();
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                Map<String,String[]>map=new HashMap<>();
                while ((line = reader.readLine()) != null) {
                    if(cpt==0){
                        ordre=line;
                    }else if(cpt!=1){
                       // possibilites.add(line);
                        String[]lines=line.split("=");

                        String[]valeur=lines[1].split(",");
                        valeur[0]=valeur[0].replace(" (","");
                        valeur[1]=valeur[1].replace(")","");
                        valeur[1]=valeur[1].replace(" ","");
                        String chaine=lines[0].replace(" ","");
                        map.put(chaine,valeur);
                    }
                    cpt++;
                }

                cpt=0;
                boolean trouve=false;
                String cursor="AAA";
                int id=0;
                while(!trouve){
                    if(cpt==ordre.length()){
                        cpt=0;
                    }
                    String[]res=map.get(cursor);
                    int index;
                    if(ordre.charAt(cpt)=='L'){
                        index=0;
                        cursor=res[index];

                    }else{
                        index=1;
                        cursor=res[index];


                    }
                    if(res[index].equals("ZZZ")){
                        trouve=true;
                        cpt++;
                    }else{
                        cpt++;
                    }
                    id++;

                }

                 System.out.println(id);
            }catch(Exception e){

            }
        }
    }
}