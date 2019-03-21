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
   println(lines(0))
    for (l <- Language.values)
    {
     
      expressionBase.put(l, lines.take(8).toList)
       lines = lines.drop(10)
     
      
   }
   
    println(dictionary.get(WordCategory.Politesse).get(Language.Espagnol))
    
    
    
    
  }


  
}
