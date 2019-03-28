package machine

object MachineImpl extends MachineDialogue{
  
  var counter = 0
  
  override def ask(s:String):List[String] =
  {
      val request = new Request(s)
      
    
      
      if (Traduction.traduct(request))
        Analysis.analyse(request);
     
     request.results.reverse
  }
    
  // Pour la partie test par le client
  def reinit = {
    
  }
  
  def test(l:List[String]):List[String] = {
      var result = List[String]()
      for(request <- l) {
        println(request)
        result = result ++ ask(request)
      }
      println(result)
      result
  }
}