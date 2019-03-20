package machine

import java.util.Arrays

object Analysis 
{
    val searchKeywords = List("chercher", "cherche", "aller", "trouver", "recherche")
    var hasSearch = false
    
    def addtokeyword(r: Request) = 
    {
     
    }
    
    
    def checkkeyword(s: List[String]): Unit =
    {
    
      
    }
    
    def analyse(r: Request): Unit = 
    {
      hasSearch = false
      println(r.toString)
      val words = r.rawInput.split(" ")
      for(w <- words) {
        for(s <- searchKeywords) {
          if(MyCorrection.distancedeHamming(w, s)) {
            println( w + " == " + s)
            hasSearch = true
          }
        }
      }
      if(hasSearch) {
        val keys = Data.getKeys()
        for(k <- keys) {
          for(w <- words) {
            println("kword : " + k)
            val keywords = k.split(" ")
            for(kw <- keywords) {
              if(kw.length > 2) {
                println("kword splitted filtered : " + kw)
                println("Checking " + w + " with " + kw)
                if(MyCorrection.distancedeHamming(w, kw)) {
                  println("Found : " + Data.getValue(k))
                  r.results ::= Data.getValue(k)
                  return
                }
              }
            }
          }
        }
      } else {
        println("No search keyword found")
      }
      
    }
    
    
    
}
