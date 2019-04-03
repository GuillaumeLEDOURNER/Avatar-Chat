package machine
import scala.io.Source
object RechercheWeb {

  private var key = ""

   /**
   * Crée l'url de recherche sur le site l'internaute
   * @param rech l'établissement recherché
   * @return l'url de recherche complete
   */
  private def UrlRecherche(rech : String) : String ={
     val recherche = SujetRecherche(rech.split(" ").toList)
    "https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name="+recherche
  }

  /**
   * le nom de l'établissement recherché
   */
  private def SujetRecherche(sujet : List[String]) : String = {
    var b = false
    var req = ""    
    for(word <- sujet){
      if(word.equals("pizzeria")||word.equals("restaurant")||word.equals("crêperie")||b==true){
       if(b==true && !word.equals(sujet.last) ){
         req = req+word+"+"
       }
       else{
         if(word.equals(sujet.last)){                   
         req = req+word
       }}
       b =true
      }
    }
    key = req
    req   
  }     
  
  
  private def FetchSearchPage(url : String) :Html ={  UrlProcessor.fetch(url)}
  
  /**
   * Crée l'url du restaurant sur le site l'internaute
   * @param adresse La liste qui contient la fin du lien de l'url
   * @return l'url complete
   */
  private def UrlRestaurant(adresse : List[String]):String ={
    var head = "https://www.linternaute.com"
    head+adresse.head
  }
   
    /**
    * Recupere tous les url de la page html
    * @param h La page html
    * @return une liste contenant toutes les urls
    */
  private def filtreAnnonce(h : Html): List[(String)] = 
  {
      var res: List[String] = Nil        
        h match
        {
          case Tag(name,attributes,children) =>          
          attributes match
          {
               case x :: y=>                
                          if (x._1 == "href" && x._2.contains("restaurant") && MyCorrection.normalize(x._2).contains(MyCorrection.normalize(key)))
                        {
                             res = res ++  List((x._2)) ++ filtreAnnonce(Tag(name,Nil,children))
                        }         
                          else {
                            res = res ++ filtreAnnonce(Tag(name,y,children))
                          }

            case Nil =>                    
              for (n <- children)
                   {
                       res = res ++ filtreAnnonce(n)                        
                   } 
          }   
          case Text(t) => Nil
          
        }
        res
  }

  
    /**
    * Recupere l'addresse de l'établissement dans le fichier html
    * @param h La page html
    * @return une liste contenant l'addresse en premierre position
    */
   private def filtreAdresse(h : Html): List[(String)] = 
  {
      var res: List[String] = Nil        
        h match
        {
          case Tag(name,attributes,children) =>        
          attributes match
          {
             case x :: y=>               
                          if (x._1 == "itemprop" && x._2 == "streetAddress" )
                        {
                             res = res ++  List((children.toString())) ++ filtreAdresse(Tag(name,Nil,children))
                        }                          
                          else {                           
                            res = res ++ filtreAdresse(Tag(name,y,children))
                          }
             case Nil =>                    
               for (n <- children)
                   {
                       res = res ++ filtreAdresse(n)    
                   } 
          } 
          case Text(t) => Nil            
        }
        res
  }
   
   /**
    * Extrait l'adresse de la liste de String
    * @param l la liste de string
    * @return l'adresse
    */
   private def extraction(liste : List[String]) : String = { 
     var l = liste.toString()
     var l2 = l.split("Text")
     MyCorrection.normalize(l2.last.toString())
   }

  /**
   * A partir d'une phrase envoyée par l'utilisateur, retourne l'adresse de l'établissement si la recherche contient les mots "pizzeria", "crêperie" ou "restaurant"
   * @param s La phrase entrée par l'utilisateur
   * @return l'adresse de l'établissement
   */
   def rechercheWeb(s : String) : String = {
     val urlRech = UrlRecherche(s)
     if (urlRech != "https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name=") {
       extraction(filtreAdresse(FetchSearchPage(UrlRestaurant(filtreAnnonce(FetchSearchPage(urlRech))))))
     } 
     else {
       "Je ne comprends pas votre demande"
     }    
   }

}

 object Truc extends App {
    println(RechercheWeb.rechercheWeb("je cherche la pizzeria la tomate")) //18 rue saint georges
    
    println(RechercheWeb.rechercheWeb("je cherche la abc")) //Je ne comprends pas votre demande
     
 }

