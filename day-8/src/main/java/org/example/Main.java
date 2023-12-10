package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Main {


    public static void sauvegarde(){
         /*
                    if(cpt==ordre.length()){
                        cpt=0;
                    }
                    if(ordre.charAt(cpt)=='L'){
                        index=0;
                    }else{
                        index=1;
                    }
                    trouve=true;
                    List<String>cursorsBis=new ArrayList<>();
                    for(String chaine:cursors){
                        String[] val=map.get(chaine);
                        if(!val[index].endsWith("Z")){
                            trouve=false;

                        }
                        if(index==0){
                            cursorsBis.add(val[0]);
                        }else{
                            cursorsBis.add(val[1]);

                        }
                    }
                    cursors=cursorsBis;
                    if(trouve==false){
                        cpt++;
                        compteur++;
                    }



        cpt=cpt==ordre.length()?0:cpt;
        index=ordre.charAt(cpt)=='L'?0:1;

                        if(cpt==ordre.length()){
                            cpt=0;
                        }


                        if(ordre.charAt(cpt)=='L'){
                            index=0;
                        }else{
                            index=1;
                        }


        trouve=true;
        List<String>cursorsBis=new ArrayList<>();
        for(String chaine:cursors){
            String[] val=map.get(chaine);
            if(!val[index].endsWith("Z")){
                trouve=false;

            }
            if(index==0){
                cursorsBis.add(val[0]);
            }else{
                cursorsBis.add(val[1]);

            }
        }
        cursors=cursorsBis;
        if(trouve==false){
            cpt++;
            compteur++;
        }

    }
                System.out.println("fin traitement");
                System.out.println("resultat "+compteur);
    // methode1(cpt,ordre,map);
*/
}


    public static void methode1(int cpt,String ordre,Map<String,String[]>map){
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
    }

    public static void main(String[] args) {
        System.out.println("DEBUT PROGRAMME!");
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
                System.out.println("fin de lecture fichier");
                System.out.println("debut traitement");

                List<String>cursors=new ArrayList<>();
                for(String val:map.keySet()){
                    if(val.endsWith("A")){
                        cursors.add(val);
                    }
                }
                boolean trouve=false;
                cpt=0;
                int index;
                int compteur=1;
                while(!trouve){

                    cpt = cpt == ordre.length() ? 0 : cpt;
                    final int indexInside = ordre.charAt(cpt) == 'L' ? 0 : 1;
                     AtomicBoolean trouveInside = new AtomicBoolean(true);
                    List<String> cursorsBis = cursors.parallelStream()
                            .map(chaine -> {
                                String[] val = map.get(chaine);

                                if (!val[indexInside].endsWith("Z")) {
                                    trouveInside.set(false); // No need for AtomicBoolean
                                }

                                return indexInside == 0 ? val[0] : val[1];
                            })
                            .collect(Collectors.toList());

                    cursors=cursorsBis;
                       System.out.println(cursors.toString());
                    trouve=trouveInside.get();
                    if (!trouve) {
                        cpt++;
                        compteur++;
                    } else {
                        cursors = cursorsBis;
                    }

/*
                        cpt=cpt==ordre.length()?0:cpt;
                        index=ordre.charAt(cpt)=='L'?0:1;


                        trouve=true;
                       // List<String>cursorsBis=new ArrayList<>();
                    List<String> cursorsBis = new CopyOnWriteArrayList<>();
                    AtomicReference<AtomicBoolean> trouveLoop= new AtomicReference<>(new AtomicBoolean(true));
                    // Parallelize the loop using parallelStream()

                    int finalIndex = index;
                    cursors.parallelStream().forEach(chaine -> {
                        String[] val = map.get(chaine);

                        if (!val[finalIndex].endsWith("Z")) {
                            trouveLoop.set(new AtomicBoolean(false));
                        }

                        if (finalIndex == 0) {
                            cursorsBis.add(val[0]);
                        } else {
                            cursorsBis.add(val[1]);
                        }
                    });

                        cursors=cursorsBis;
                        if(trouveLoop.get().get()==false){
                            cpt++;
                            compteur++;
                            trouve=false;
                        }else{
                            trouve=true;
                        }

 */

                }
                System.out.println("fin traitement");
                System.out.println("resultat "+compteur);
               // methode1(cpt,ordre,map);

            }catch(Exception e){

            }
        }
    }

}