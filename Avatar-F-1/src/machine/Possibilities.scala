package machine

object Possibilities {
  def recherche(r :Request){
    var plusieurs = false
    var nbReponses = 0
    var listReponses : List[String] = List()
    val words = r.rawInput.split(" ") 
    val keys = Data.getKeys()
    for(k <- keys) { //tous les mots clés de Data
    	for(w <- words) { //tous les mots de la requète
    		if(w.equals("un") || w.equals("une")){
    			plusieurs = true
    		}
    		if(plusieurs){
    			val keywords = k.split(" ")
    			r.keywords = keywords.toList
    			if(MyCorrection.distancedeHamming(k, w)){
    			  nbReponses +=1
    			}
    			
    		}
    		var i = nbReponses
    		while(nbReponses != 0){
    		  if(i == nbReponses){
    		    r.results ::= "J'ai " + nbReponses + " réponses possibles :"
    		  }
    		  listReponses ::= nbReponses + ") " +Data.getName(k) //liste des requètes numérotées
    		  nbReponses -=1
    		}
    		for(lr <- listReponses){
    		  r.results ::= lr
    		}
    		r.results ::= "Quel est votre choix?"
    	}
    }
  }
}