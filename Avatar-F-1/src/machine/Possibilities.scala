package machine



object Possibilities {
  var plusieurs = false
  var nbReponses = 0
  var listReponses : List[String] = List()
  var recherche = ""
  
  val L : List[String] = List("je","de", "une")
  XMLSearch.init()
  
  def recherche(r :Request){
    val words = r.rawInput.split(" ") 
    val keys = Data.getKeys()
    
    for(k <- keys) { //tous les mots clés de Data
    	for(w <- words) { //tous les mots de la requète
    	  
    		if(w.equals("un") || w.equals("une")){
    			plusieurs = true
    		}
    		
    		if(plusieurs){
    			val keywords = k.split(" ")
    			for(kw <- keywords){
    				if(MyCorrection.distancedeHamming(w, kw)){
    					nbReponses +=1
    				}
    			}
    		}
    		println(nbReponses)
    		var i = 0
    		
    		while(nbReponses != 0 && i<=nbReponses){
    		  
    		  if(i == 0)
    		  {
    		    r.results ::= "J'ai " + nbReponses + " réponses possibles :"
    		    i=i+1
    		  } 
    		  
    		  else 
    		  {
    		  listReponses ::= i + ") " + Data.getName(w) //liste des requètes numérotées
    		  i=i+1
    		  }
    		}
    		
    		
    	}
    }
    for(lr <- listReponses){
    		  r.results ::= lr
    		}
    		
    		r.results ::= "Quel est votre choix?"
  }
    
  def choose(r: Request): Unit = {
    val words = r.rawInput.split(" ")
    var choice = false
    var i = 1
    var index = 0
    while(i <= nbReponses && !choice) {
    if (words.contains(i)) {
      index = i
      choice = true
    }
    i=i+1
    }
    //r.results ::= "L'adresse de " + Data.getName(listReponses(index).substring(3, listReponses(index).length())) +" est : "+ Data.getValue(r.results(index).substring(3, r.results(index).length()))
                  
  }

  

}