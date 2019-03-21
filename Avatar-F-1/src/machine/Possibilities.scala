package machine

object Possibilities {
  def recherche(r :Request){
    var plusieurs = false
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
    			  
    			}
    		}
    	}
    }
  }
}