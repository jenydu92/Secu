import java.io.*;
import java.text.*;
import java.util.*;
import java.lang.*;
import java.io.*;
class Verification{
  private static String [] nouveau_fichiers;


/********************methode pour recupere le texte d'un fichier*******************/

  public static String recup_texte(String fichier){
    String texte = "";
    BufferedReader br = null;

    try{
      br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fichier))));
      int i = br.read(); //lit le fichier
      while(i != -1){ //tant qu'on est pas arrive a la fin
        texte += (char)(i); //rajoute le char dans le string
        i = br.read(); //passe au caractere suivant
      }
      br.close();
    }catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    texte = texte.toLowerCase(); //met le texte en minuscule
    return texte;
  }


/*****************methode pour tester si on a entre un type correct********/

  static boolean type_correct(String type){
    boolean test = false;
    if (type.equals("c") || type.equals("p") || type.equals("v"))
      test = true;
    return test;
  }


/*****************methode pour tester si un string est un int***********/

  static boolean int_correct(String cle){
    boolean test = true;
       try{
         Integer.parseInt(cle); //essaye de le parser en int
       } catch(NumberFormatException e){
         test = false; //s'il y a une erreur passe le boolean a false
       }
       return test;
  }

/****************methode pour tester si une strategie est correcte**************/

  static boolean strategie_correcte(String cle){
    boolean test = true;
       try{
         Integer.parseInt(cle); //essaye de le parser en int
         if (!(cle.equals("1") || cle.equals("2") || cle.equals("3")))
          test = false;

       } catch(NumberFormatException e){
         test = false; //s'il y a une erreur passe le boolean a false
       }
       return test;
  }


/*****************methode pour tester si c'est bien une permutation ***********/

//Vérifie si la clef contient une seule fois chaque lettre de l'alphabet
//peut importe l'ordre
static boolean is_permutationNew(String[][] tab_perm){
  int cpt = 0;
  for(int i=0; i<26; i++){
    for(int j=0; j<26; j++){
      if(tab_perm[0][i].equals(tab_perm[1][j])){
        cpt++;
      }
    }if(cpt != 1){
      return false;
    }else{
      cpt=0;
    }
  }
  return true;
}

//transforme une cle string en tableau de permutations
//exemple de cle de permutation : bcdefghijklmnopqrstuvwxyza
static String[][] clePermNew(String cle){
  String[][] tab_perm = {{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"},{"","","","","","","","","","","","","","","","","","","","","","","","","",""}};
  if(cle.length() == 26){
    for(int i=0; i<26; i++){
      tab_perm[1][i] = String.valueOf(cle.charAt(i));
    }
  }
  return tab_perm;
}

/******************methode pour tester si un string est un mot***********/

  static boolean string_type(String cle){
    boolean test = cle.matches("[a-zA-Z]+"); //verifie si le string matches avec l'alphabet
    return test;
  }


/******************methode pour recuperer le dictionnaire******************/

    public static HashSet<String> recup_dictionnaire(){

      HashSet<String> hs = new HashSet<String>();
      try{

        BufferedReader br = new BufferedReader(new FileReader(new File("words.txt")));
        String i = br.readLine();
        while (i != null){
          hs.add(i);
          i = br.readLine();
        }
        br.close();
      }
      catch (FileNotFoundException e){
        e.printStackTrace();
      }
      catch (IOException e){
        e.printStackTrace();
      }
      return hs;
    }
}
