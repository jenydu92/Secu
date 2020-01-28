import java.io.*;
import java.text.*;
import java.util.*;


class Decrypt extends Verification{


  public static void main(String[] args){
    long startTime = System.currentTimeMillis();


    if(args.length < 2)
        System.out.println("Veuillez entrer le bon nombre d'arguments");
    else {
      String type = args[0];
      String texte = recup_texte(args[1]);
      HashSet<String> dico = recup_dictionnaire();



    if(type_correct(type)){ //verifie si on a bien un type egal a c ou p ou v
      switch(type){

/****************************Cesar***************************/

        case "c":
          if(args.length < 3) //si on a pas assez d'arguments
            System.out.println("Veuillez entrer le bon nombre d'arguments");
          else {
            String strategie = args[2];
            Cesar code;
            if(strategie_correcte(strategie)){ //verifie si on a une strategie correcte

              int strat = Integer.parseInt(strategie);
              int decalage;
              switch (strat) {
                case 1:
                  if(args.length != 4)
                    System.out.println("Veuillez entrer le bon nombre d'arguments");
                  else {
                    String mot_connu = args[3]; //recupere le mot connu
                    mot_connu = mot_connu.toLowerCase(); //le passe en minuscule
                    code = new Cesar(texte, mot_connu); //creer un code cesar avec
                    decalage = code.motConnu(); //trouve le decalage
                    code.setDistance(decalage);
                    System.out.println(code.dechiffrer()); //Dechiffre avec ce decalage
                    break;
                  }
                case 2:

                  code = new Cesar(texte, dico); //creer un code cesar
                  decalage = code.frequenceLettre(); //trouve le decalage
                  if(decalage == -1){ //si on a trouve aucun decalage
                    System.out.println("Aucun decryptage trouve");
                    break;
                  }
                  else{
                    code.setDistance(decalage);
                    System.out.println(code.dechiffrer()); //dechiffre avec le decalage trouve
                    break;
                  }
                case 3:
                  code = new Cesar(texte, dico); //creer un code cesar
                  decalage = code.forceBrute(); //trouve le decalage par force brute
                  if(decalage == -1){
                    System.out.println("Aucun decryptage trouve");
                    break;
                  }
                  else{
                    code.setDistance(decalage);
                    System.out.println(code.dechiffrer()); //dechiffre avec le decalage trouve
                    break;
                  }
            }

          }
          else{
            System.out.println("Veuillez entrer une strategie correcte");
          }

        }
        break;

/******************************permutation******************************/

        case "p":// cas de permutation


          Permutation codeP = new Permutation(texte);//cree un code de permutation avec les arguments

          //codeP.frequence(texte);
          String[][] tab_cle = codeP.decrypter();
          codeP.setCle(tab_cle);
          System.out.println(codeP.dechiffrer());//affiche le texte chiffrer
          break;

/*****************************vigenere*********************************/

        case "v":
          if(args.length != 3)
            System.out.println("Veuillez entrer un nombre d'arguments correct");
          else{
            String tailleTemp = args[2]; //recupere la taille du mot cle
            int taille = Integer.parseInt(tailleTemp); //le parse en int
            Vigenere code = new Vigenere(texte); //creer un code vigenere
            String motDecr = code.decrypte(taille); //dechiffre le texte

            System.out.println(motDecr);
          }
      }
    }
    else
      System.out.println("Veuillez entrer un type correct");
  }



  long endTime = System.currentTimeMillis();
  System.err.println("Temps de : " + (endTime-startTime) + "ms");
}
}
