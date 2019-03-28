package machine
import scala.io.Source
class RechercheWeb {

  var key = ""

  private def UrlRecherche(rech : String) : String ={

 val recherche = SujetRecherche(rech.split(" ").toList)

"https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name="+recherche
  
  }

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
  
  private def UrlRestaurant(adresse : List[String]):String ={
    var head = "https://www.linternaute.com"
    head+adresse.head
  }
   /**
    * Recupere tous les url de la page html
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
    * Recupere tous les url de la page html
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
   
   private def extraction(liste : List[String]) : String = { 
     var l = liste.toString()
    var l2 = l.split("Text")
     MyCorrection.normalize(l2.last.toString())
     /*liste match {
       case Nil => ""
       case List(a) => a match {
         case List(b) => extraction(b)
         case Text(t) => t
       }
     }*/
   }

  
   def rechercheWeb(s : String) : String = {
     extraction(filtreAdresse(FetchSearchPage(UrlRestaurant(filtreAnnonce(FetchSearchPage(UrlRecherche(s)))))))
   }
        


}

 object Truc extends App {
    val r = new RechercheWeb
       //println(MyCorrection.normalize("la-tomate"))
    
    println(r.rechercheWeb("je cherche la pizzeria la tomate"))
    
   
   
 }

