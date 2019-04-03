package machine

import java.util.Arrays

object Analysis 
{
    var isWaitingForInput = false
  
   
    def addtokeyword(r: Request) = 
    {
     
    }
    
    def analyse(r: Request): Unit = 
    {     
      if(isWaitingForInput && Possibilities.choose(r)) {
        return
      }
      
      val words = r.rawInput.split(" ")
      for(w <- words) 
      {
          for (word <- Traduction.dictionary(Traduction.WordCategory.Politesse)(Traduction.currentLanguage))
          {
                if(MyCorrection.distancedeHamming(w, word))
                {
                     r.results ::= Traduction.dictionary(Traduction.WordCategory.Politesse)(Traduction.currentLanguage)(0)
                     
                      if (words.length==1)
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
            
              if(!MyCorrection.distancedeHamming(w, "theatre")) {
                if(MyCorrection.distancedeHamming(w, kw)) 
                {
                  r.results ::= Traduction.getAdressString( Data.getName(k))  + " : "+Data.getValue(k)
               
                  return
                }
              }
            }
          }
        }
      }
      if(!Possibilities.recherche(r)) {
        isWaitingForInput = false
        r.results ::= Traduction.expressionBase(Traduction.currentLanguage)(3)  // je ne comprend pas votre demande
      } else {
        isWaitingForInput = true
      }
      return
      
    }
    
    
    
}