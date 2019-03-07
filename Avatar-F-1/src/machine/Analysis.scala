package machine

import java.util.Arrays

object Analysis 
{
    val verbe = List("chercher","cherche","cherchons","trouve","aller","rendre","trouver","allons","passer","trouvons"
        ,"marcher","bouger") 
    val lieux = List("gare","théâtre","mairie","hôtel","ville","rennes","bretagne","sncf","tnb","paillette","national","place")
     
    val r = new Request("hotel de ville")
    
    val l = verbe::lieux 
    
    
    def isValide(l:List[Char]) : Boolean= {
      var a = r.rawInput.split(" ")
      var c = 0
      for (i <- a){
        if(lieux.contains(i)){
          c += 1
          
        }
      }
      if(c>0){
        true 
      }
      else{false}
    }
    
    
    def analyse(r:Request) = {
      
    }
}
