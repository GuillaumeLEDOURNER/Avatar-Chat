package machine
import scala.util.control.Breaks



object Possibilities {
  var plusieurs = false
  var nbReponses = 0
  var listReponses : List[String] = List()
  var recherche = ""
  var responses : List[String] = List()
  val L : List[String] = List("je","de", "une")
  XMLSearch.init()

  def recherche(r :Request){
    val words = r.rawInput.split(" ") 
    val keys = XMLSearch.getKeys()
    for(k <- keys) { //tous les mots clés de Data
    	for(w <- words) { //tous les mots de la requète  
    			val keywords = k.split(" ")
    			var j = 0
    			var found = false
    			while(j<keywords.length && !found) {
    			//println(keywords(j))
    			if(MyCorrection.distancedeHamming(w,keywords(j).toLowerCase()) && !L.contains(w)) {
    			  println("WORD: "+w+ " AND " + keywords(j).toLowerCase())
    			  nbReponses +=1
    			  listReponses = k::listReponses
    			  found=true
    			  //println(nbReponses)
    			  }
    			j=j+1
    			 }
    		}
    }
    println(nbReponses)
    println(listReponses)
    		var i = 0
    		
    		while(nbReponses != 0 && i<=nbReponses){
    		  if(i == 0){
    		    responses ::= "J'ai " + nbReponses + " réponses possibles :"
    		    r.results ::= "J'ai " + nbReponses + " réponses possibles :"
    		    i=i+1
    		  } else {
    		    for(k<-listReponses) {
    		  responses =  responses++ List((i + ") " +XMLSearch.getName(k))) //liste des requètes numérotées
    		  i=i+1
    		    }
    		  }
    		}
    		
    		responses = responses++List("Quel est votre choix?")
    		responses.foreach(x => println(x))
    		/*
    		for(lr <- responses){
    		  r.results ::= lr
    		}
    		r.results ::= "Quel est votre choix?"
   */
  }
    
  def choose(r: Request): Unit = {
    val words = r.rawInput.split(" ")
    var choice = false
    var i = 1
    var index = 0
    while(i <= nbReponses && !choice) {
    if (words.contains(i.toString())) {
      index = i
      choice = true
    }
    i=i+1
    }
   // println(index)
    r.results = List("L'adresse de " + XMLSearch.getName(responses(index).substring(3, responses(index).length())) +" est : "+ XMLSearch.getValue(responses(index).substring(3, responses(index).length())))
    println(r.results)
  }

  

}