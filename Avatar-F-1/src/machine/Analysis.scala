package machine

import java.util.Arrays
object Analysis 
{
  
  
    
    val verbe = List("chercher","cherche","cherchons","trouve","aller","rendre","trouver","allons","passer","trouvons"
        ,"marcher","bouger") 
    val lieux = List("gare","théâtre","mairie","hôtel","ville","rennes","bretagne","sncf","tnb","paillette","national","place")
    
    val mairie = List("marie","hotel","ville","place","rennes")
    val paillete = List("paillete","théâtre")
    val TNB = List("théâtre","national","tnb","bretagne")
    val gare = List("gare","sncf")
    
    
    
    
     "je cherche l'hotel de ville de Lyon" 
    //isValide(r => true
    //keywords("hotel ville, 
    val r = new Request("hotel de ville")
    
    val l = verbe::lieux 
    
    
    def addtokeyword(l:List[Char]) = {
      var a = r.rawInput.split(" ")
      for (i <- a){
        if(lieux.contains(i)){
          r.keywords = r.keywords:+i
        }
      }
      
    }
    
    
    def analyse(r:Request) = {
        
    }
    
    
    
}
