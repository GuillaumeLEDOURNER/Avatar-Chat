package machine

object MachineImpl extends MachineDialogue{
  
  override def ask(s:String):List[String]=
  {
      val request = new Request(s)
      Analysis.analyse(request);
      println(request.results)
      request.results
  }
  
  // Pour la partie test par le client
  def reinit= ???
  def test(l:List[String]):List[String]= ???
}