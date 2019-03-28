package machine
import java.text.Normalizer

object MyCorrection extends Correction {

  /**
   * Calcul de la distance entre 2 mots
   * Les 2 mots sont considérés équivalents si la distance de Hamming est inférieure à 2
   * @param exp Le mot à tester, entré par l'utilisateur
   * @param data Le mot de la base de données
   * @return vrai si les 2 mots sont équivalents
   */
  def distancedeHamming(exp :String,data :String): Boolean ={
   val ex = normalize(exp)
   val dat = normalize(data)
   val t1 = ex.length();
   val t2 = dat.length();
   var d = t2-t1
   var i= 0; 
   var b = false
   if (t1 == t2) {
      d = 0
     while ((d<=1) && (i < t1)){
      if (dat.charAt(i)!=ex.charAt(i)){
          d+=1
      }
      i+=1
     }
      (d<=1)
   } else if (t2 - t1 == 1) {
    val ex2 = lettreManquante(ex, dat)
    b = distancedeHamming(ex2,data)
    (b && (ex2.length() == dat.length()))
   } else {
     false
   }
  }
  
  /**
   * Normalisation des mots
   * La fonction enlève le accents et caractères spéciaux de la chaine passée en paramètre
   * @param exp : le mot a normaliser
   * @return le mot normalisé
   */
  def normalize(exp : String): String ={
    var e = Normalizer.normalize(exp,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase()
    e = e.replaceAll("\\?", "")
    e = e.replaceAll("\\,", "")
    e = e.replaceAll("\\+", " ")
     e = e.replaceAll("\\-", " ")
     e = e.replaceAll("\\(", "")
     e = e.replaceAll("\\)", "")
    e
  }
  
  /**
   * Correction d'erreur de frappe, 1 lettre manquante au maximum
   * @param exp le mot entré par l'utilisateur, à tester
   * @param data le mot de la base de données à comparer
   * @return le mot corrigé si il y a une erreur de frappe, sinon une chaine vide
   */
  private def lettreManquante(exp : String, data : String): String ={
   val ex = normalize(exp)
   val dat = normalize(data)
   var t1 = ex.length()
   var t2 = dat.length()
   var i= 0
   var test =""

  if(t2==t1) normalize(exp)
  
  else if(t1 == t2 - 1){
    while(t1 !=i && dat.charAt(i)==ex.charAt(i)){ test = test+dat.charAt(i); i+=1 ; }
    test = test + dat.charAt(i) ;i +=1;
    
    while(i<t2 && dat.charAt(i)==ex.charAt(i-1)){ test = test+ex.charAt(i-1);i = i+1;}
    if(distancedeHamming(test, data)){test}
  }
  else if (t1 - t2 >= 2) {
    ""
  }
    test
  }
}