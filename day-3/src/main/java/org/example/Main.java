package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input.txt");

        List<String>map=new ArrayList<>();
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    map.add(line);
                }

                String []tab=map.toArray(new String[0]);
                List<Donnee>mapDonnee=new ArrayList<>();
                List<DonneeGears>donneeGears=new ArrayList<>();
                for(int i=0;i<=tab.length-1;i++){
                    String s=tab[i];
                    for(int j=0;j<=s.length()-1;j++){
                      if(s.charAt(j)=='*'){
                          DonneeGears donneeGears1=new DonneeGears();
                          donneeGears1.setColonne(j);
                          donneeGears1.setLigne(i);
                          donneeGears.add(donneeGears1);
                      }
                    }
                }
                int cpt=0;
                for(int i=0;i<=tab.length-1;i++) {
                    String champs = tab[i];
                    List<Donnee>numbers =getListFromString(champs,i);
                    mapDonnee.addAll(numbers);
                }

                List<Donnee>donneesVerifie=new ArrayList<>();

                for(Donnee donnee:mapDonnee){
                    for(Integer col:donnee.getColonnes()){
                        //3 en haut
                        //2 mm ligne
                        //3 en bas
                        boolean estValide=verifHaut(donnee,col,tab)||
                                verifLigne(donnee,col,tab)||
                                verifBas(donnee,col,tab);

                        if(estValide){
                            System.out.println(donnee.getNumero());
                            cpt=cpt+donnee.getNumero();
                            donneesVerifie.add(donnee);
                            break;
                        }

                    }
                }
                int cptGears=0;
                for(DonneeGears donneeGears1:donneeGears){
                    List<Donnee>donneesAdjacente=new ArrayList<>();
                    for(Donnee donnee:donneesVerifie){
                        if(estAdjacente(donneeGears1,donnee)){
                            donneesAdjacente.add(donnee);
                        }
                    }
                    if(donneesAdjacente.size()==2){
                        cptGears=cptGears+donneesAdjacente.get(0).getNumero()*
                                donneesAdjacente.get(1).getNumero();

                    }
                }
                System.out.println(cptGears);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

         }


        System.out.println("Hello world!");
    }

    private static boolean estAdjacente(DonneeGears donneeGears1, Donnee donnee) {
        boolean verifBourrin=false;

        for(Integer col:donnee.getColonnes()){
            //haut gauche
            if(col-1==donneeGears1.getColonne() && donnee.getLigne()-1==donneeGears1.getLigne()){
                return  true;
            }

            //haut
            if(col==donneeGears1.getColonne() && donnee.getLigne()-1==donneeGears1.getLigne()){
                return  true;
            }

            //haut droite
            if(col+1==donneeGears1.getColonne() && donnee.getLigne()-1==donneeGears1.getLigne()){
                return  true;
            }

            //gauche
            if(col-1==donneeGears1.getColonne() && donnee.getLigne()==donneeGears1.getLigne()){
                return  true;
            }

            //droite
            if(col+1==donneeGears1.getColonne() && donnee.getLigne()==donneeGears1.getLigne()){
                return  true;
            }


            //bas gauche
            if(col-1==donneeGears1.getColonne() && donnee.getLigne()+1==donneeGears1.getLigne()){
                return  true;
            }


            //bas
            if(col==donneeGears1.getColonne() && donnee.getLigne()+1==donneeGears1.getLigne()){
                return  true;
            }

            //bas droit
            if(col+1==donneeGears1.getColonne() && donnee.getLigne()+1==donneeGears1.getLigne()){
                return  true;
            }

        }
        return false;


    }

    private static boolean verifBas(Donnee donnee, Integer col, String[] tab) {

        boolean estValide=false;
        if(donnee.getLigne()!=tab.length-1){
            //diag gauche
            String bas=tab[donnee.getLigne()+1];
            if(col!=0){
                if(bas.charAt(col-1)!='.'){
                    estValide=true;
                }
            }
            if(bas.charAt(col)!='.'){
                estValide=true;
            }
            if(col!=bas.length()-1){
                if(bas.charAt(col+1)!='.'){
                    estValide=true;
                }
            }
        }
        return estValide;
    }

    private static boolean verifLigne(Donnee donnee, Integer col, String[] tab) {
        boolean estValide=false;
        String lineHaut=tab[donnee.getLigne()];
        if(col!=0){
            if(lineHaut.charAt(col-1)!='.' && !Character.isDigit(lineHaut.charAt(col-1))){
                estValide=true;
            }
        }

        if(col!=lineHaut.length()-1){
            if(lineHaut.charAt(col+1)!='.' && !Character.isDigit(lineHaut.charAt(col+1))){
                estValide=true;
            }
        }

        return estValide;

    }

    private static boolean verifHaut(Donnee donnee,int col,String []tab) {
        boolean estValide=false;
        if(donnee.getLigne()!=0){
            //diag gauche
            String lineHaut=tab[donnee.getLigne()-1];
            if(col!=0){
                if(lineHaut.charAt(col-1)!='.'){
                    estValide=true;
                }
            }
            if(lineHaut.charAt(col)!='.'){
                estValide=true;
            }
            if(col!=lineHaut.length()-1){
                if(lineHaut.charAt(col+1)!='.'){
                    estValide=true;
                }
            }
        }
        return estValide;
    }

    private static List<Donnee> getListFromString(String champs,int ligne) {
        List<Donnee>donnees=new ArrayList<>();
        for(int j=0;j<=champs.length()-1;j++){
            String s="";
            if(Character.isDigit(champs.charAt(j))){
                int cpt=j;
                s=s+champs.charAt(j);
                List<Integer>colonnes=new ArrayList<>();
                colonnes.add(j);
                while(cpt+1<=champs.length()-1 && Character.isDigit(champs.charAt(cpt+1))){
                    s=s+champs.charAt(cpt+1);
                    colonnes.add(cpt+1);
                    cpt++;
                }
                Donnee donnee=new Donnee();
                donnee.setLigne(ligne);
                donnee.setNumero(Integer.parseInt(s));
                donnee.setColonnes(colonnes);
                j=j+s.length()-1;
                donnees.add(donnee);
            }

        }
        return donnees;
    }
}




class Donnee{
    public int numero;
    public List<Integer>colonnes=new ArrayList<>();

    public int ligne;

    public Donnee(){

    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getLigne() {
        return ligne;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Integer> getColonnes() {
        return colonnes;
    }

    public void setColonnes(List<Integer> colonnes) {
        this.colonnes = colonnes;
    }
}


class DonneeGears{
    int ligne;
    int colonne;

    public DonneeGears() {
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }
}