import java.util.*;
import java.io.*;

public class Permutation implements Interface {
  private static String texte;
  private String[][] perm;
  //private String motConnu;


/****************CONSTRUCTEUR POUR LE CHIFFRAGE ET DECHIFFRAGE**************/

  public Permutation(String t, String[][] p){
    this.texte = t;
    this.perm = p;
  }
/**************CONSTRUCTEUR POUR LE DECRYPTAGE*********************/

    public Permutation(String t){
      this.texte = t;
    }

/***************SETTEUR POUR L'ATTRIBUT MOT*********************/

    public void setCle(String[][] c){
      this.perm = c;
    }

/*******************Methode chiffrer**********************************/

  //Parcours le texte clair
  //transforme chaque lettre avec la permutation correspondant a la clef donner
  //stock resultat dans un nouveau string correspondant au texte chiffré
  public String chiffrer(){
  	String texte_chiffrer = "";
    char abc = ' ';
  	for(int i=0; i<texte.length()-1; i++){
      abc = texte.charAt(i);
      if(abc >= 'a' && abc <= 'z'){
  		  for(int j=0; j<26; j++){
  			   if(String.valueOf(texte.charAt(i)).equals(perm[0][j])){
  				    texte_chiffrer = texte_chiffrer + perm[1][j];
  			   }
        }
      }else{
          texte_chiffrer = texte_chiffrer + String.valueOf(abc);
  		}
  	}
  	return texte_chiffrer;
  }

/*************************methode dechiffrer************************/

  //Parcours le texte chiffrer
  //transforme chaque lettre avec la permutation correspondant a la clef donner
  //stock resultat dans un nouveau string correspondant au texte dechiffrer
  //exemple de permutation : bcdefghijklmnopqrstuvwxyza
  public String dechiffrer(){
  	String texte_dechiffrer = "";
    char abc = ' ';

  	for(int i=0; i<texte.length()-1; i++){
      abc = texte.charAt(i);
      if(abc >= 'a' && abc <= 'z'){
  		  for(int j=0; j<26; j++){
  			   if(String.valueOf(texte.charAt(i)).equals(perm[1][j])){
  				  texte_dechiffrer = texte_dechiffrer + perm[0][j];
  			   }
        }
      }else{
          texte_dechiffrer = texte_dechiffrer + String.valueOf(abc);
  		}
  	}
  	return texte_dechiffrer;
  }

/********************methode decrypte*************************/

  //decrypter va comparer les frequences des lettres du texte crypte
  //avec les frequences des lettres connues
  //Puis va creer et renvoyer une cle de permutation
  //Qui sera utiliser par la suite par dechiffre
  public String[][] decrypter(){

  	String[][] frequencesTexte = frequencesTexte(texte);
  	String frequences[] = {"e","t","a","o","n","i","s","r","h","l","d","c","u","m","f","p","w","g","b","y","v","k","x","j","q","z"};
  	//cle que l'on creer et renvoie
  	String[][] tab_perm = {{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"},{"","","","","","","","","","","","","","","","","","","","","","","","","",""}};

  	int max = -1;//max frequence du texte
  	int indiceMax = -1; //indice correspondant au max
  	int cpt = 0; //taille tableau 26

  	while(cpt < 26){
  		for(int i=0; i<26; i++){
  			if(Integer.parseInt(frequencesTexte[1][i]) > max){  //si on trouve un max
  				max = Integer.parseInt(frequencesTexte[1][i]);
  				indiceMax = i;
  			}
  		}frequencesTexte[1][indiceMax]="-1";   // on annule/supprime la frequence max
  		tab_perm = creationClef(tab_perm,frequences[cpt],frequencesTexte[0][indiceMax]); //on ajoute a la clef la nouvelle permutation
  		max = -1;
  		cpt++;
  	}
  	return tab_perm;
  }

  //permet d'ajouter les permutations trouvé a la clef
  public String[][] creationClef(String[][] tab_perm, String alphabet, String perm){
  	for(int i=0; i<26; i++){
  		if(tab_perm[0][i].equals(alphabet)){
  			tab_perm[1][i]= perm;
  		}
  	}return tab_perm;
  }

  //Calcul le nombre de chaque lettre du texte crypter
  //Renvoie un tableau de l'alphabet avec les valeurs de chaque lettre rencontrer
  public String[][] frequencesTexte(String texte){
    String[][] tab_perm = {{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","."},{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","."}};
    int fin = 0;
    int tmp;
    while(fin < texte.length()-1){//Parcours texte crypter
  		for(int i=0; i<26; i++){
	  		if((String.valueOf(texte.charAt(fin))).equals(tab_perm[0][i])){
	   			tmp = Integer.parseInt(tab_perm[1][i]);
          tmp++;
	   			tab_perm[1][i] = String.valueOf(tmp);//Incremente la valeur de la frequence de la lettre
		  	}
		  }tmp=0;
		  fin ++;
    }
    return tab_perm;
  }

/*public void frequence(String texte){
    String[][] tab_perm = frequencesTexte(texte);
    for(int i=0; i<26; i++){
      System.out.println(tab_perm[0][i] + " "+tab_perm[1][i]);
    }
  }*/



}//fin class
