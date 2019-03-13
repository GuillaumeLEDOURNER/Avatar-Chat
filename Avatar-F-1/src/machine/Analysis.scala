package machine

import java.util.Arrays
object Analysis 
{
  
  
    
    val verbe = List("chercher","cherche","cherchons","trouve","aller","rendre","trouver","allons","passer","trouvons"
        ,"marcher","bouger") 
    val lieux = List("gare","théâtre","mairie","hôtel","ville","rennes","bretagne","sncf","tnb","paillette","national","place")
    
    val mairie = List("marie","hotel","ville","place","rennes")
    var m = 0 //
    val paillete = List("paillete","théâtre")
    var p = 0
    val TNB = List("théâtre","national","tnb","bretagne")
    var t = 0
    val gare = List("gare","sncf")
    var g = 0
    val phrases = List()
    
    
    
     "je cherche l'hotel de ville de Lyon" 
    //isValide(r => true
    //keywords("hotel ville, 
    val r = new Request("hotel de ville")
    
    val l = verbe::lieux 
    
    
    def addtokeyword(r: Request) = {
      var a = r.rawInput.split(" ")
      for (i <- a){
        if(lieux.contains(i)){
          r.keywords = r.keywords:+i
        }
      }
    }
    
    
    def checkkeyword(s: List[String]): Unit = {
      for(x <- s) {   
      if (mairie.contains(x)) m=m+1
      if (paillete.contains(x)) p=p+1
      if (TNB.contains(x))t=t+1
      if (gare.contains(x))g=g+1 
      }
    }
    
    
    def isValide(r: Request) = {
      addtokeyword(r)
      if (r.keywords.isEmpty) r.valid = false
      else {
        
        
      }
    }
    
    
    def analyse(r:Request) = {
        
    }
    
    
    
}
