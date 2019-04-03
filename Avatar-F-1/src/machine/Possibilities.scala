package machine
import scala.util.control.Breaks
import util.control.Breaks._


object Possibilities {
  
  var isWaitingForInput = false;
  
  var plusieurs = false
  var nbReponses = 0
  var listReponses : List[String] = List()
  var recherche = ""
  var responses : List[String] = List()
  val L : List[String] = List("je","de", "une", "ou", "le","des","la","est")
  XMLSearch.init()

  def recherche(r :Request) : Boolean = {
   
		responses = List()
		nbReponses = 0
		listReponses = List()
    		
    val words = r.rawInput.split(" ") 
    val keys = XMLSearch.getKeys()
    for(k <- keys) { //tous les mots clés de Data
    	for(w <- words) { //tous les mots de la requète  
    			val keywords = k.split(" ")
    			var j = 0
    			var found = false
    			while(j<keywords.length && !found) {
    			//println(keywords(j))
    			if(w.toLowerCase()==keywords(j).toLowerCase() && !L.contains(w)) {
    			  //println("WORD: "+w+ " AND " + keywords(j).toLowerCase())
    			  nbReponses +=1
    			  listReponses = k::listReponses
    			  found=true
    			  //println(nbReponses)
    			  }
    			j=j+1
    			 }
    		}
    }
    //println(nbReponses)
    //println(listReponses)
    		var i = 0
    		
    		breakable {
      		while(nbReponses != 0 && i<=nbReponses){
      		  if(i == 0){
      		    r.results ::= Traduction.getNbResponses(nbReponses) 
      		    i=i+1
      		  } else {
      		    for(k<-listReponses)  {
          		  responses =  responses++ List((i + ") " +XMLSearch.getName(k))) //liste des requètes numérotées
          		  i=i+1
      		    }
      		    break
      		  }
      		}
    		}
    		//responses.foreach(x => println(x))
    		
    		for(lr <- responses){
    		  r.results ::= lr
    		}
    		
    		if(nbReponses > 0) {
    		  r.results ::= Traduction.expressionBase(Traduction.currentLanguage)(7)
    		  isWaitingForInput = true
    		  return true
    		} else {
    		  return false
    		}
  }
    
  def choose(r: Request): Boolean = {
    if(isWaitingForInput) {
      var index = 0
      try {
        index = r.rawInput.toInt - 1
      } catch {
        case _: Throwable => {
          println("Unable to parse to INT")
          isWaitingForInput = false
          return false
        }
      }
     
      if (index >= responses.length)
      {
         isWaitingForInput = false;
         return false;
      }
      val name = XMLSearch.getName(responses(index).substring(3, responses(index).length()))
      val adresse = XMLSearch.getValue(responses(index).substring(3, responses(index).length()))
      
      r.results ::= Traduction.getAdressString(name) + " " +adresse
      
      
      isWaitingForInput = false
      return true
    }
    isWaitingForInput = false
    false
  }

  

}