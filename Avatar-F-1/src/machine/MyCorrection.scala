package machine
import java.text.Normalizer

class MyCorrection extends Correction {

  /**
   * Calcul de la distance entre 2 mots
   * Les 2 mots sont considérés équivalents si la distance de Hamming est inférieure à 2
   * @param exp Le premier mot
   * @param data Le deuxieme mot
   * @return vrai si les 2 mots sont équivalents
   */
  def distancedeHamming(exp :String,data :String): Boolean ={
   var d = 4
   val ex = normalize(exp)
   val dat = normalize(data)
   var t1 = ex.length()
   var t2 = dat.length()
   var i= 0
   if (t1 == t2) {
     d = 0
     while ((d<=1) && (i < t1)){
      if (dat.charAt(i)!=ex.charAt(i)){
          d+=1
      }
      i+=1
     }
   } 
   (d<=1)
  }
  
  def normalize(exp : String): String ={
    Normalizer.normalize(exp,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase()
  }
  
  def lettreManquante(exp : String, data : String): String ={
    var d = 4
   val ex = normalize(exp)
   val dat = normalize(data)
   var t1 = ex.length()
   var t2 = dat.length()
   var i= 0
   var test =""
  
      if(t2==t1) normalize(exp)
      else if(t1 == t2 - 1){
        while(dat.charAt(i)==ex.charAt(i)){ test = test+dat.charAt(i); i+=1}
        test = test + dat.charAt(i)
        
        while(dat.charAt(i)==ex.charAt(i-1)){ test = test+ex.charAt(i-1); i+=1;println("ici:" +test)}
        if(distancedeHamming(test, data)){test}
      }
      else if (t1 - t2 >= 2) {
        exp
      }
    test
  }
}

object Truc extends App {
  var m : MyCorrection = new MyCorrection
  
  println(m.distancedeHamming("elle","elle"))
  println(m.distancedeHamming("elle","ella"))
  println(m.distancedeHamming("elle","elaa"))
  println(m.distancedeHamming("elle","abcd"))
  
  var a = "abcd"
  var b = "abed"
  println(a.charAt(0)==b.charAt(0))
  println(a.charAt(1)==b.charAt(1))
  println(a.charAt(2)==b.charAt(2))
  println(a.charAt(3)==b.charAt(3))   
  
  println("éà "+m.normalize("éà"))
  println("hotel "+m.lettreManquante("hotl", "hotel"))
}
