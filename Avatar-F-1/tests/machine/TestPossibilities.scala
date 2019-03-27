package machine

object TestPossibilities extends App {
  val r1 = new Request("je cherche une piscine")
  Possibilities.recherche(r1)
  val reponse = new Request("je choisis la 1")
  Possibilities.choose(reponse)
  
}