package machine

import java.util.Arrays

object Analysis 
{
    def addtokeyword(r: Request) = 
    {
     
    }
    
    def analyse(r: Request): Unit = 
    {
      val words = r.rawInput.split(" ")
      for(w <- words) {
        if(MyCorrection.distancedeHamming(w, "bonjour") || MyCorrection.distancedeHamming(w, "salut")) 
        {
          r.results ::= "Bonjour"
          if(words.length == 1) {
            return
          }
        }
      }
      val keys = Data.getKeys()
      for(k <- keys) {
        for(w <- words) {
          val keywords = k.split(" ")
          r.keywords = keywords.toList;
          for(kw <- keywords) {
            if(kw.length > 2) {
              println("Checking : " + w + " with " + kw)
              if(!MyCorrection.distancedeHamming(w, "theatre")) {
                if(MyCorrection.distancedeHamming(w, kw)) 
                {
                  r.results ::= "L'adresse de " + Data.getName(k) +" est : "+ Data.getValue(k)
                  println(r.results)
                  return
                }
              }
            }
          }
        }
      }
      r.results ::= "Je ne comprends pas votre demande"
      println(r.results)
      return
      
    }
    
    def analyseXML(r: Request): Unit = 
    {
      val words = r.rawInput.split(" ")
      for(w <- words) {
        if(MyCorrection.distancedeHamming(w, "bonjour") || MyCorrection.distancedeHamming(w, "salut")) 
        {
          r.results ::= "Bonjour"
          if(words.length == 1) {
            return
          }
        }
      }
      val keys = XMLSearch.getKeys()
      for(k <- keys) {
        for(w <- words) {
          val keywords = k.split(" ")
          r.keywords = keywords.toList;
          for(kw <- keywords) {
            if(kw.length > 2) {
              println("Checking : " + w + " with " + kw)
              if(!MyCorrection.distancedeHamming(w, "theatre")) {
                if(MyCorrection.distancedeHamming(w, kw)) 
                {
                  r.results ::= "L'adresse de " + XMLSearch.getName(k) +" est : "+ XMLSearch.getValue(k)
                  println(r.results)
                  return
                }
              }
            }
          }
        }
      }
      r.results ::= "Je ne comprends pas votre demande"
      println(r.results)
      return
      
    }
    
    
    
}
