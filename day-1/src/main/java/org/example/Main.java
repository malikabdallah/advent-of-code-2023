package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static List<String>list= Arrays.asList("one","two","three","four",
        "five","six","seven","eight","nine");
    public static  Map<String,Integer>map=new HashMap<>();



    public static List<Integer> getAllNumberFromString(String s){
        List<Integer>l=new ArrayList<>();
        for(int i=0;i<=s.length()-1;i++){
            if(Character.isDigit(s.charAt(i))){
                l.add(Integer.parseInt(String.valueOf(s.charAt(i))));
            }
        }
        return l;
    }


    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input.txt");
        map.put("one",1);
        map.put("two",2);
        map.put("three",3);
        map.put("four",4);
        map.put("five",5);
        map.put("six",6);
        map.put("seven",7);
        map.put("eight",8);
        map.put("nine",9);

        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;

                // Read lines until the end of the stream
                int cpt=0;
                while ((line = reader.readLine()) != null) {
                    // Process each line as needed

                    int first=getNumber(line,true);
                    int last=getNumber(new StringBuilder(line).reverse().toString(),false);
                   // List<Integer>nombres=getAllNumberFromString(line);
                   // cpt=cpt+(nombres.get(0)*10+nombres.get(nombres.size()-1));
                    cpt=cpt+(first*10+last);

                }
                writeInRessources(cpt);
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
    }

    private static int getNumber(String line, boolean b) throws IllegalArgumentException {
        StringBuilder numberBuilder = new StringBuilder();
        boolean foundNumber = false;

        for (char ch :line.toCharArray()) {
            if (Character.isDigit(ch) || Character.isLetter(ch)) {
                if(Character.isDigit(ch)){
                    return Integer.parseInt(String.valueOf(ch));
                }else{
                    numberBuilder.append(ch);
                    for(String s:list){
                        if(b){
                            if(numberBuilder.toString().contains(s)){
                                return stringToNumber(s);
                            }


                        }else{
                             String reverse=new StringBuilder(numberBuilder).reverse().toString();
                             int x=5;
                             if(reverse.contains(s)){
                                 return stringToNumber(s);
                             }


                        }

                    }
                }

            }
        }
        return 1;
        //throw new IllegalArgumentException("nombre introuvable");
    }

    private static int stringToNumber(String s) {

        return map.get(s);
    }

    private static void writeInRessources(int cpt) {
        /*
        String fileName = "output.txt";
        String content = "value ="+String.valueOf(cpt);

        // Get the path to the target directory
        Path targetDir = Paths.get("target");

        // Get the path to the file in the target directory
        Path filePath = targetDir.resolve(fileName.replace("/", File.separator));

        try {
            // Create or overwrite the file in the target directory
            Files.write(filePath, content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            System.out.println("File written successfully at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
        System.out.println(cpt);
    }
}