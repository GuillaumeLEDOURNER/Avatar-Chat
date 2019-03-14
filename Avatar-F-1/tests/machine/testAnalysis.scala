package machine

object testAnalysis extends App {
  val r1 = new Request("je cherche l'hôtel")
  println(MyCorrection.distancedeHamming("cherche", "mairie"))
  Analysis.analyse(r1)
  println(r1.rawInput)
  println(r1.valid)
  println(r1.keywords)
  println(r1.results(0))
}