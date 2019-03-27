package machine

import scala.io.Source

object Data 
{
  val pathToFile = "doc/DonneesInitiales.txt" // constante qui représente le chemin au fichier de données
  
  var data = Map[String, String]() // Represente les données
  
  def openFile() { // ouvre le fichier texte, et stoque les données dans la map.
    
    // Source.fromFile permet de d'ouvrir un fichier
    for(line <- Source.fromFile(pathToFile).getLines) // récupère toute les lignes du fichier sous forme d'un tableau de String
    {
      println(line) // affiche la ligne actuelle
      val splitedLine = line.split(';') // on coupe la ligne en fonction du caractère ";"
      if(splitedLine.size >= 2) {
        data = data + (splitedLine(0) -> splitedLine(1))
      }
    }
  }
  
  def getKeys() : Array[String] =  // Récupère les clées de la map (qui correspondent au nom du lieu)
  {
     data.keys.toArray
  }
  
  def getValue(key:String) : String = // récupère l'adresse d'un lieu a partir de son nom 
  {
    var result = "";
     for (pair <- data) // on fait une itération pour chaque valeurs
     {
       if (pair._1 == key) // pair._1  =clée (nom du lieu)
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
