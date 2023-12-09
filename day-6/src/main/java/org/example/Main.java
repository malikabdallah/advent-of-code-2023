package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void methode1(List<String>map){
        String[]distance=map.get(0).split(":");
        String[]time=map.get(1).split(":");
        String[]distances=distance[1].split("\\s+");
        String[]times=time[1].split("\\s+");
        List<Integer>ways=new ArrayList<>();
        for(int i=1;i<=distances.length-1;i++){
            int cpt=0;
            for( int vit=1; vit<=Integer.parseInt(distances[i]);vit++){
                int capaciteDep=(Integer.parseInt(distances[i])-vit)*vit;
                int z=1;
                if(capaciteDep>Integer.parseInt(times[i])){
                    cpt++;
                }

            }
            ways.add(cpt);

        }
        int product = ways.stream()
                .reduce(1, (a, b) -> a * b);

        System.out.println("Product: " + product);

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

                String[]distance=map.get(0).split(":");
                String[]time=map.get(1).split(":");
                String dist=distance[1].replaceAll("\\s+","");
                String temps=time[1].replaceAll("\\s+","");
                List<Integer>ways=new ArrayList<>();
                for(int i=1;i<=Integer.parseInt(dist);i++){
                    int cpt=0;
                    for( int vit=1; vit<=Integer.parseInt(dist);vit++){
                        int capaciteDep=(Integer.parseInt(dist)-vit)*vit;
                        int z=1;
                        if(capaciteDep>Integer.parseInt(temps)){
                            cpt++;
                        }

                    }
                    ways.add(cpt);

                }
                int x=0;
                System.out.println("ways "+ways.toString());

            } catch (Exception e) {

            }
        }
        System.out.println("Hello world!");
    }
}