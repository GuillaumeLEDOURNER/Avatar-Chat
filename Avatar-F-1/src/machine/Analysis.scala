package machine

import java.util.Arrays

object Analysis 
{
    //val searchKeywords = List("chercher", "cherche", "aller", "trouver", "recherche", "ou")
    
    val wordsKind = List[(String,String)](("F","Mairie"),("M","Théâtre"),("F","Gare"))
    
    var hasSearch = false
    
    def addtokeyword(r: Request) = 
    {
     
    }
    
    
    def getDeterminant(s: String): String =
    {
         for(kind <- wordsKind)
         {
            if (kind._2.toLowerCase() == s.toLowerCase())
            {
             
                return kind._1 match
                {
                  case "F" => "La"
                  case "M" => "Le"
                }
            }
         }
         ""
    }
    
    def analyse(r: Request): Unit = 
    {
      
      hasSearch = true
      /*println(r.toString)
      val words = r.rawInput.split(" ")
      for(w <- words) {
        for(s <- searchKeywords) {
          if(MyCorrection.distancedeHamming(w, s)) {
            println( w + " == " + s)
            hasSearch = true
          }
        }
      }*/
      val words = r.rawInput.split(" ")
      for(w <- words) {
        if(MyCorrection.distancedeHamming(w, "bonjour")) 
        {
          r.results ::= "Bonjour"
          if(words.length == 1) {
            return
          }
        }
      }
      if(hasSearch) {
        val keys = Data.getKeys()
        for(k <- keys) {
          for(w <- words) {
            val keywords = k.split(" ")
            r.keywords = keywords.toList;
            for(kw <- keywords) {
              if(kw.length > 2) {
                println("Checking : " + w + " with " + kw)
                if(MyCorrection.distancedeHamming(w, kw)) 
                {
                  //val det = getDeterminant(w)
                  r.results ::= "L'adresse de " + Data.getName(k) +" est : "+ Data.getValue(k)
                  println(r.results)
                  return
                }
              }
            }
          }
        }
      } else {
        println("No search keyword found")
      }
      r.results ::= "Je ne comprends pas votre demande"
      println(r.results)
      return
      
    }
    
    
    
}
