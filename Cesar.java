import java.util.*;
import java.io.*;

public class Cesar implements Interface {

  private static String texte;
  private static HashSet<String> dictionnaire;
  private int distance;
  private String motConnu;


/****************CONSTRUCTEUR POUR LE CHIFFRAGE ET DECHIFFRAGE**************/

  public Cesar(String t, int d){
    this.texte = t;
    this.distance = d;
  }

/****************CONSTRUCTEUR POUR LE DECRYPTAGE************************/

  public Cesar(String t, String m){
    this.texte = t;
    this.motConnu = m;
  }

  public Cesar(String t, HashSet<String> d){
    this.texte = t;

    this.dictionnaire = d;
  }

  public Cesar(String t){
    this.texte = t;

  }

/*****************SETTEUR POUR LA DISTANCE****************************/

public void setDistance(int d){
  this.distance = d;
}

/*******************Methode chiffrer**********************************/

  public String chiffrer(){

    String texte_chiffrer = "";
    char caractere;
    char caractere_chiffrer;

    for(int i = 0; i < texte.length(); i++){ //parcourt le texte
      caractere = (char)(texte.charAt(i)); //recupere chaque caractere
      if(caractere >= 'a' && caractere <= 'z'){ //verifie si le caractere est entre a et z
        caractere_chiffrer = (char) (caractere + distance); //chiffre le caractere en ajoutant la distance
        if(caractere_chiffrer > 'z'){ //verifie si on a depasse z
          caractere_chiffrer = (char)(caractere - 26 + distance); //si c'est le cas, retire 26 pour replacer le caractere dans l'alphabet
        }

        texte_chiffrer += caractere_chiffrer; //rajoute le caractere chiffre au texte chiffre

      }
      else
        texte_chiffrer += caractere;  //cas ou on a un caractere special qu'on ne doit pas chiffrer

    }
    return texte_chiffrer;
  }


  /********************Methode dechiffrer***************************/

  public String dechiffrer(){ //marche de la meme maniere que chiffrer

    String texte_dechiffrer = "";
    char caractere;
    char caractere_dechiffrer;

    for(int i = 0; i < texte.length(); i++){
      caractere = (char)(texte.charAt(i));
      if(caractere >= 'a' && caractere <= 'z'){
        caractere_dechiffrer = (char) (caractere - distance); //retire le decalafe
        if(caractere_dechiffrer < 'a'){ //verifie si on a depasse a
          caractere_dechiffrer = (char)(caractere + 26 - distance); //rajoute 26 pour placer le caractere dans l'alphabet
        }
        texte_dechiffrer += caractere_dechiffrer;
      }
      else
        texte_dechiffrer += caractere;
    }
    return texte_dechiffrer;
  }

/************************methode pour compter le decalage*************/

//renvoie dans les deux cas la valeur absolue du decalage entre a et b

public static int decalageLettre(char a, char b){
  int decalage = Math.abs((int)a - (int)b);
  return decalage;
}

public static int decalageLettre(int a, int b){
  int decalage = Math.abs(a - b);
  return decalage;
}


/***********methode pour trouver le decalage en connaissant un mot*********/

public int motConnu(){
  String tabString [] = texte.split("\\W+"); //recupere dans un tableau chaque mot du texte

  String tabMotConnu [] = new String[motConnu.length()]; //initialise un tableau de String de taille du mot connu

  int decalage = 0;
  int tailleMotConnu = motConnu.length();

  for(int i = 0; i < tabString.length; i++){ //parcourt le tableau des mots du texte
    if(tabString[i].length() == tailleMotConnu){ //si le mot i est de la meme taille que le mot connu:
      decalage = decalageLettre(tabString[i].charAt(0), motConnu.charAt(0)); //on regarde le decalage entre la premiere lettre du mot connu et la premiere lettre du mot i
        for(int j=0; j<tailleMotConnu; j++){
          if(!(decalage == (decalageLettre(tabString[i].charAt(j), motConnu.charAt(j))))){ //on verifie si chaque jieme lettre du mot i et du mot connu ont le meme decalage
          decalage = -1; //si c'est pas le cas on passe decalage a -1
          break; //on sort de la boucle
        }
        }
        if(decalage != -1) //si on a trouve un decalage identique pour toutes les lettres on return le decalage
          return decalage;
    }
  }
  return decalage;
}

/****************methode pour renvoyer l'indice de la valeur max************/

public static int maximum(int [] tableau){
  int max = 0;
  int t = 0;
  for(int i = 0; i < tableau.length; i++){
    if(max < tableau[i]){
      max = tableau[i];
      t = i;
    }
  }
  return t;
}

/***************************methode frequenceLettre*******************/

public static int frequenceLettre(){
  char frequences[] = {'e','t','a','o','n','i','s','r','h','l','d','c','u','m','f','p','w','g','b','y','v','k','x','j','q','z'};
  int[] compt = new int[26];
  int lettreMax;
  int decalage;
  String texteDechiffreTemp;
  char caractere;

  for (int i = 0; i < texte.length(); i++) { //parcourt le texte
    caractere = texte.charAt(i); //recupere le ieme caractere
    if (caractere >= 'a' && caractere <= 'z') //si ce caractere est dans l'alphabet
      compt[caractere - 'a']++; //incremente son nombre a sa position dans le tableau
  }

  lettreMax = maximum(compt) + 97; //recupere l'indice de la valeur max du tableau et donc la lettre qui est apparu le plus (+97 car l'alphabet commence a 97 en ASCII)

  for (int j = 0; j < frequences.length; j++){
    decalage = decalageLettre(frequences[j], lettreMax); //regarde le decalage entre la lettre Max et la jieme lettre de la frequence
    Cesar codeTemp = new Cesar(texte, decalage); //creer un code cesar avec ce decalage
    texteDechiffreTemp = codeTemp.dechiffrer(); //dechiffre ce code avec ce decalage
    if (verif(texteDechiffreTemp)) //verifie si on a au moins la moitié des mots dans le dictionnaire
      return decalage; //si oui on retourne le decalage correspondant
  }
  return -1; //si on a pas trouvé de decalage on retourne -1
  }


/***********************methode pour decrypter en force brute*******************/

public static int forceBrute(){
  Cesar codeTemp;
  String texteDechiffreTemp;
  for(int i = 1; i < 27; i++){ //parcourt tout les decalages possibles
    codeTemp = new Cesar(texte, i); //creer un code cesar avec le decalage i
    texteDechiffreTemp = codeTemp.dechiffrer(); //dechiffre avec ce decalage
    if(verif(texteDechiffreTemp)) //verifie si on a au moins la moitie des mots dans le dictionnaire
      return i; //si c'est le cas retourne le decalage correspondant
  }
  return -1; //retourne -1 s'il n'a trouvé aucun decalage
}


/********************methode pour verifier que les mots sont dans le dictionnaire********/

public static boolean verif(String txt){

  String [] tableauMot = txt.toString().split("\\W+"); //recupere le texte dans un tableau de mot
  int motCorrect = 0;

  for (int i = 0; i < tableauMot.length; i++){ //parcourt le tableau de mot

    if (dictionnaire.contains(tableauMot[i])) //si le dictionnaire contient ce mot, il incremente motCorrect
      motCorrect++;
  }

  double moyenne = (double)motCorrect / (double)(tableauMot.length); //calcule la moyenne de mot correct
  if (moyenne >= 0.5) //si c'est plus de la moitie on renvoie vrai
    return true;

  return false;
}

}
