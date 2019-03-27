package machine

import scala.xml._
import scala.collection.breakOut

object XMLSearch {
  
  val pathToFile = "doc/vAr.xml"
  
  var keys = List[String]()
  var values = List[String]()
  
  var data = Map[String, String]()
  
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
      txt = (for(n <- names) yield n.text).mkString
    } yield { txt }
    
    values = addressesNames.toList
    
    data = (keys zip values)(breakOut): Map[String, String]
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
          result = pair._2 // pair._2 = valeur (adresse du lieu)
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