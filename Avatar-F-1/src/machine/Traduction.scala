package machine

import scala.io.Source
import collection.mutable._

object Traduction 
{
  object Language extends Enumeration 
  {
     val Francais, Anglais, Espagnol, Allemand, Italien = Value
  }
  object WordCategory extends Enumeration
  {
    val Politesse,Recherche,NomLanguage = Value
  }
  val pathToFile = "doc/international.txt" 
  
  var currentLanguage:Language.Value = null
  
  val dictionary:Map[WordCategory.Value,Map[Language.Value,List[String]]] = Map[WordCategory.Value,Map[Language.Value,List[String]]]()
  
  val expressionBase:Map[Language.Value,List[String]] = Map[Language.Value,List[String]]()
  
  
  var askingLanguage:Language.Value = null;
  
  def exploreLanguage(l:Language.Value): Language.Value =
  {
      l match
      {
        case Language.Francais => Language.Anglais
        case Language.Anglais => Language.Espagnol
        case Language.Espagnol => Language.Allemand
        case Language.Allemand => Language.Italien
        case Language.Italien => Language.Francais
        
      }
  }
  def detectLanguage(rawInput:String): Language.Value = 
  {
    
      val splitted = rawInput.split(" ")
      
      for (w <- splitted)
      {
      for (category <- dictionary)
      {
           for (pair <- category._2)
           {
               for (word <- pair._2)
               {
                 if (w.toLowerCase() == word.toLowerCase())
                 {
                     if (currentLanguage != pair._1)
                     {
                       println(word)
                       return pair._1
                
                     }
                 }
               }
               
           }
      }
      }
      currentLanguage
  }
  def detectLanguageFromAnswer(rawInput:String): Language.Value = 
  {
    
    for (pair <- expressionBase)
    {
      for (word <- pair._2)
      {
        if (MyCorrection.distancedeHamming(rawInput, word))
        {
           return pair._1
        }
      }
    }
      currentLanguage
      
  }
  def traduct(r:Request) : Boolean =
  {
    
   
    if (Traduction.askingLanguage != null)
      {
            if (MyCorrection.distancedeHamming(r.rawInput, Traduction.expressionBase(Traduction.askingLanguage)(0)))
            {
                  
                  r.results ::= Traduction.expressionBase(Traduction.askingLanguage)(5)
                  Traduction.currentLanguage = Traduction.askingLanguage
                  Traduction.askingLanguage = null
                  return false
            }
            else
            {  
                Traduction.askingLanguage = Traduction.exploreLanguage(Traduction.askingLanguage)
                r.results ::= Traduction.expressionBase(Traduction.askingLanguage)(4);
                 return false
           }
         
      }
      
      
      val detectedLanguage = Traduction.detectLanguage(r.rawInput) 
      if (detectedLanguage != Traduction.currentLanguage )
      {
       
         r.results ::= Traduction.expressionBase(detectedLanguage)(4)
         Traduction.askingLanguage = detectedLanguage;
         
         return false
      }
      
      true
  }
  def getAdressString(value:String) : String = 
  {
    val exp = expressionBase(Traduction.currentLanguage)(2)
    
    exp.replaceAll("XXX", value)
  }
  def getNbResponses(number:Int) : String = 
  {
    val exp = expressionBase(Traduction.currentLanguage)(6)
    
    exp.replaceAll("XXX", number.toString())
   
  }
  def init():Unit = 
  {
    currentLanguage = Language.Francais
    
    var lines = Source.fromFile(pathToFile).getLines.toList   
    
    
    for (w <- WordCategory.values)
    {
       dictionary.put(w, Map[Language.Value,List[String]]())
    }
    
   for (w <- WordCategory.values)
   {
      lines = lines.drop(7)
      var i = 0
      for (l <- Language.values)
      {
       
        dictionary.get(w).get(l) = lines(i).split(" ")(1).split(",").toList
        i = i + 1
      }
   }
     
   
   lines = lines.drop(11)
     
   
    for (l <- Language.values)
    {
      
       expressionBase.put(l, lines.take(8).toList)
       lines = lines.drop(11)
     
   }
 
    
    
    
    
  }


  
}
