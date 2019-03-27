package machine

import scala.xml._
import scala.collection.breakOut

object XMLSearch {
  
  val pathToFile = "doc/vAr.xml"
  
  var keys = List[String]()
  var values = List[String]()
  
  var data = Map[String, String]()
  
  var bannedKeywords = List[String]()
  
  def init() {
    var file = XML.loadFile(pathToFile)
    
    val tree = for {
      organization <- file \\ "organization"
      if (organization \\ "city").text == "Rennes"
      node <- organization
    } yield { node }
    
    val placesNames = tree \ "name"

    var tmp = for(n <- placesNames) yield n.text
    keys = tmp.toList
    
    val addressesNames = for {
      addresses <- tree \\ "addresses"
      names = addresses \\ "name"
      numbers = addresses \\ "number"
      txt = (for(n <- numbers) yield n.text).mkString + ", " + (for(n <- names) yield n.text).mkString
    } yield { txt }
    
    values = addressesNames.toList
    println("Salut")
    data = (keys zip values)(breakOut): Map[String, String]
    
    checkForKeywordsToBan()
  }
  
  def checkForKeywordsToBan() {
    
    for(key <- data.keys)   
    {
    
       for(word <- key.split(" "))
       {
          var count = 0
          for(keyCheck <- data.keys)
          {
            if(keyCheck.contains(word))
            {
              count += 1
            }
          }
          if(count > 1)
          {
            if(!bannedKeywords.contains(word)) {
              bannedKeywords ::= word
            }
          }
       }
  
    }
    print(bannedKeywords)
  }
  
  def printAllCities(node : NodeSeq) {
    val result = node \\ "city"
    println(result)
  }
  
  def getKeys() : Array[String] = {
    data.keys.toArray
  }
  
  def getValue(key : String) : String = {
    var result = "";
     for (pair <- data) // on fait une itération pour chaque valeurs
     {
       if (pair._1 == key) // pair._1  = clée (nom du lieu)
       {
           val index = pair._2.indexOf(',', pair._2.indexOf(',')+1)
          if(index != -1) {
            result = pair._2.substring(0, index)
          } else {
            result = pair._2
          }
       }
     }
     
     result // adresse 
  }
  
  def getName(key : String) : String = {
    val index = key.indexOf(",")
    if(index != -1) {
      return key.substring(0, index)
    } else {
      return key
    }
  }
  
}