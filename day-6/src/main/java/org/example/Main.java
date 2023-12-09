package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
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

    public static int combienDeDistance(BigInteger depart,BigInteger duree){
        int cpt=0;
        for( int i=depart.intValue();i<=duree.intValue();i++){
            cpt=cpt+depart.intValue();
        }
        return cpt;
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
                BigInteger distValue=new BigInteger(dist);
                BigInteger tempsValue=new BigInteger(temps);
                int cpt=0;
                int min=-1;
                int max=-1;

                /*System.out.println(beatRecord(Long.parseLong(temps),
                        Long.parseLong(dist)));
                */
                for (int i = 0; i <= distValue.intValue(); i++) {

                    if(max==-1) {
                        max = getMaximum(i, distValue, tempsValue);
                    }
                    if(min==-1){
                        min = getMinimum(i,distValue,tempsValue);
                    }

                    if(max!=-1 && min!=-1){
                        break;
                    }
                }



                // Print or use the 'ways' list as needed
                System.out.println(max-min);
                //33149631
                //52947592



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("H!");
    }

    private static Long beatRecord(Long time, Long distance) {
        long min=0;
        for(int i=1;i<distance;i++){
            long owndist=i*(distance-i);
            if(owndist>time){
                min=i;
                break;
            }
        }


        long max=0;
        for(long i=distance-1;i>=1;i--){
            long owndist=i*(distance-i);
            if(owndist>time){
                max=i;
                break;
            }
        }

        return max-min+1;
    }

    private static int getMaximum(int i, BigInteger distValue, BigInteger tempsValue) {
        if(!new BigInteger(String.valueOf(i)).equals(distValue)){
            BigInteger depart=distValue.subtract(new BigInteger(String.valueOf(i)));
            int res=combienDeDistance(depart,distValue);
            if(res>tempsValue.intValue()){
                return depart.subtract(new BigInteger(String.valueOf(1))).intValue();
            }
            //821573604
             // 940200res
        }
        return -1;
    }

    private static int getMinimum(int i ,BigInteger distValue,BigInteger tempsValue) {
            if(i!=0){
                int res=combienDeDistance(new BigInteger(String.valueOf(i)),distValue);
                if(res>tempsValue.intValue()){
                    return i;
                }
            }


        return -1;
    }
}