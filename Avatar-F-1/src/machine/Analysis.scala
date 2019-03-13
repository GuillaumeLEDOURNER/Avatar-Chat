package machine

import java.util.Arrays
class Analysis 
{
  
  
    
    val verbe = List("chercher","cherche","cherchons","trouve","aller","rendre","trouver","allons","passer","trouvons"
        ,"marcher","bouger") 
    val lieux = List("gare","théâtre","mairie","hôtel","ville","rennes","bretagne","sncf","tnb","paillette","national","place")
    
    val mairie = List("marie","hotel","ville","place","rennes")
    var m = 0 
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
    
    val l = verbe ++ lieux 
    
    
    def addtokeyword(r: Request) = {
      var a = r.rawInput.split(" ")
      for (i <- a){
        if(l.contains(i)){
          r.keywords = r.keywords:+i
        }
        else{
          val c = new MyCorrection
          for(j <- l){
            if(c.distancedeHamming(i, j)){
               r.keywords = r.keywords:+j
            }
        }
      }
    }
    }
    
    
    def checkkeyword(s: List[String]): Unit = {
      for(x <- s) {
      if(x.toLowerCase()=="théâtre"){
        if(s.contains("paillette"))p+=1
        else if(s.contains("national") && s.contains("bretagne")) t+=1
      }
      else{
        if (mairie.contains(x)) m=m+1
        if (TNB.contains(x))t=t+1
        if (gare.contains(x))g=g+1
        }
      }
      
    }
    
    
    def analyse(r:Request) = {
        addtokeyword(r)
      if (r.keywords.isEmpty) r.valid = false
      else {
        checkkeyword(r.keywords)
        r.valid = true
        
        if(m>=2 && (p+t+g==0)){
          r.valid = true
          r.adress = "Place de la mairie"
        }
        else if(p>0 && (m+t+g==0)){
          r.valid = true
          r.adress = "2, rue du Pré de Bris"
        }
        else if(t>0 &&(p+m+g==0)){
          r.valid = true
          r.adress = "1, rue Saint-Hélier"
        }
        else if(g>0 &&(p+m+t==0)){
          r.valid = true
          r.adress = "19, place de la gare"
        }
        else r.valid = false
      }
    }
    
    
    
}
