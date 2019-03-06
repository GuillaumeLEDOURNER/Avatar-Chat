package machine

class Request(rawIn:String)
{
   val rawInput:String = rawIn;
   
   var valid:Boolean = false;
   
   val keywords:List[String] = List();
   
   var adress:String= "";
}