class Chiffre extends Verification{

  public static void main(String[] args){
    long startTime = System.currentTimeMillis();

    if(args.length != 3){ //verifie si on a bien trois arguments, a approfondir
      System.out.println("Veuillez entrer un nombre d'arguments corrects");
    }
    else{
      String type = args[0];
      String cle = args[1];
      String texte = recup_texte(args[2]); //fait appel a la methode recup_texte pour enregistrer le recup_texte

      if(type_correct(type)){ //verifie si on a bien un type egal a c ou p ou v
        switch(type){
          case "c": //cas de cesar
            if(int_correct(cle)){ //verifie si on a bien un int
              int cle_int = Integer.parseInt(cle); //transforme la cle en type int
              Cesar code = new Cesar(texte, cle_int); //cree un code cesar avec les arguments
              System.out.println(code.chiffrer()); //affiche le texte chiffre
              break;
            }
            else{
              System.out.println("Veuillez entrer une cle correcte");
              break;
            }

          case "p":// cas de permutation

            String[][] tab_cle = clePermNew(cle);

            if(is_permutationNew(tab_cle)){
              Permutation codeP = new Permutation(texte,tab_cle);//cree un code de permutation avec les arguments
              System.out.println(codeP.chiffrer());//affiche le texte chiffrer
              break;
            }
            else{
              System.out.println("Veuillez entrer une cle correcte");
              break;
            }

          case "v": //cas de Vigenere
            if(string_type(cle)){ //verifie si on a bien un string
              Vigenere code = new Vigenere(texte, cle); //cree un code Vigenere avec les arguments
              System.out.println(code.chiffrer()); //affiche le code chiffre
              break;
            }
            else{
              System.out.println("Veuillez entrer un mot clef correct");
              break;
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
