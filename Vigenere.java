import java.util.*;

public class Vigenere implements Interface{

  private static String texte;
  private String mot;


/**************CONSTRUCTEUR POUR LE CHIFFRAGE ET DECHIFFRAGE****************/

  public Vigenere(String t, String m){
    this.texte = t;
    this.mot = m;
  }

/**************CONSTRUCTEUR POUR LE DECRYPTAGE*********************/

    public Vigenere(String t){
      this.texte = t;
    }

/***************SETTEUR POUR L'ATTRIBUT MOT*********************/

    public void setMot(String m){
      this.mot = m;
    }

/************************methode chiffrer*****************************/

  public String chiffrer(){

    String texte_chiffrer = "";
    int cycle = 0;
    char caractere =' ';
    char caractere_chiffrer = ' ';
    int distance = 0;
    int tab_mot [] = new int[mot.length()]; //tableau contenant la position de chaque lettre du mot cle

    for(int j = 0; j < mot.length(); j ++){ //parcout le mot cle
      tab_mot[j] = lettreEnInt(mot.charAt(j)); //met la position dans l'alphabet de la lettre
    }

    for(int i = 0; i < texte.length(); i++){ //parcourt le texte
      caractere = (char)texte.charAt(i); //recupere un caractere
      distance=tab_mot[cycle]; //la distance passe à la position de la lettre du mot cle
      if(caractere >= 'a' && caractere <= 'z'){ //si le caractere est dans l'alphabet
        caractere_chiffrer = (char)(caractere+distance); //chiffre le caractere
        if(caractere_chiffrer > 'z') //verifie si on depasse z
          caractere_chiffrer = (char)(caractere - 26 + distance); //retire 26 pour etre dans l'alphabet
        texte_chiffrer += (char)caractere_chiffrer; //rajoute le caractere chiffre
        cycle = (cycle +1)%(mot.length()); //on passe a la lettre suivante dans le mot cle
      }
      else
        texte_chiffrer += caractere;
    }
    return texte_chiffrer;
  }

/*************************methode dechiffrer************************/

  public String dechiffrer(){ //fonctionne de la meme facon que chiffrer

    String texte_dechiffrer = "";
    int cycle = 0;
    char caractere =' ';
    char caractere_dechiffrer = ' ';
    int distance = 0;
    int tab_mot [] = new int[mot.length()];

    for(int j = 0; j < mot.length(); j ++){
      tab_mot[j] = lettreEnInt(mot.charAt(j));
    }

    for(int i = 0; i < texte.length(); i++){
      caractere = (char)texte.charAt(i);
      distance=tab_mot[cycle];
      if(caractere >= 'a' && caractere <= 'z'){
        caractere_dechiffrer = (char)(caractere-distance);
        if(caractere_dechiffrer < 'a')
          caractere_dechiffrer = (char)(caractere + 26 - distance);
        texte_dechiffrer += (char)caractere_dechiffrer;
        cycle = (cycle +1)%(mot.length());
      }
      else
        texte_dechiffrer += caractere;
    }
    return texte_dechiffrer;
  }


/******************methode pour trouver la position d'une lettre dans l'alphabet*************/

 private static int lettreEnInt(char lettre) {
    return (((int)lettre) - 97); //Dans la table ASCII l'alphabet commence à la 97 position
  }


/********************methode decrypte*************************/

    public String decrypte(int taille){

      int [] motCle = new int [taille]; //creer un tableau de int de la tailel du mot cle
      String mot="";
      String texte2 = texte.replaceAll("[^a-zA-Z]",""); //retire tout caractere qui n'est pas dans l'alphabet

      for(int j = 0; j < taille; j ++){
        String s = "";

        for(int i = j; i < texte2.length(); i+= taille){

          s += texte2.charAt(i); //rajoute toutes les lettres codées par la ieme lettre du mot cle
        }
        motCle[j]=frequenceLettre(s); //rajoute le decalage pour la jieme lettre du mot cle
      }
      String texte_dechiffrer = "";
      int cycle = 0;
      char caractere =' ';
      char caractere_dechiffrer = ' ';
      int distance = 0;

      for(int i = 0; i < texte.length(); i++){ //parcourt le texte et dechiffre de la meme maniere que dechiffre()
        caractere = (char)texte.charAt(i);
        distance=motCle[cycle];
        if(caractere >= 'a' && caractere <= 'z'){
          caractere_dechiffrer = (char)(caractere-distance);
          if(caractere_dechiffrer < 'a')
            caractere_dechiffrer = (char)(caractere + 26 - distance);
          texte_dechiffrer += (char)caractere_dechiffrer;
          cycle = (cycle +1)%(taille);
        }
        else
          texte_dechiffrer += caractere;
    }
    return texte_dechiffrer;


  }

/******************methode frequence**********************/

  public static int frequenceLettre(String txt){
    char frequences[] = {'e','t','a','o','n','i','s','r','h','l','d','c','u','m','f','p','w','g','b','y','v','k','x','j','q','z'};
    int[] compt = new int[26];
    int lettreMax;
    int decalage = 0;
    String texteDechiffreTemp;
    char caractere;

    for (int i = 0; i < txt.length(); i++) {
      caractere = txt.charAt(i);
      if (caractere >= 'a' && caractere <= 'z')
        compt[caractere - 'a']++;
    }

    lettreMax = maximum(compt) + 97; //En ASCII l'alphabet commence à 97
    decalage = decalageLettre(frequences[0], lettreMax); //renvoie le decalage entre la lettreMax et e

    return decalage;
  }

/****************methode qui renvoie l'indice de la valeur max du tableau***********/

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


/******************methodes qui renvoient le decalage entre a et b*****************/

    public static int decalageLettre(char a, char b){
      int decalage = Math.abs((int)a - (int)b);
      return decalage;
    }

    public static int decalageLettre(int a, int b){
      int decalage = Math.abs(a - b);

      return decalage;
    }


}
