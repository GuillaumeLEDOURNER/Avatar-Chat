package machine

object testAnalysis extends App {
  val r1 = new Request("je cherche l'hôtel")
  val a = new Analysis
  val c = new MyCorrection
  println(c.distancedeHamming("cherche", "mairie"))
  a.analyse(r1)
  println(r1.rawInput)
  println(r1.valid)
  println(r1.keywords)
  println(r1.adress)
}