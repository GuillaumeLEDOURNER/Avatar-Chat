package machine

class Request(rawIn:String)
{
   
   
   val rawInput:String = rawIn;
   
   var valid:Boolean = false;
   
   var keywords:List[String] = List();
   
   var results:List[String]= List();
  
   override def toString() : String = {
      "Request : " + rawInput
   }

} 