package machine

object MachineImpl extends MachineDialogue{
  override def ask(s:String):List[String]=
  {
      val request = new Request(s)
      
      List()
     // Analysis.analyse(request);
      
      
     
  }
  
  // Pour la partie test par le client
  def reinit= ???
  def test(l:List[String]):List[String]= ???
}