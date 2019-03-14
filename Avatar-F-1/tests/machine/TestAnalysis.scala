
package machine
import org.junit.Test
import org.junit.Assert._

@Test
class TestAnalysis {
  assertEquals(Data.getValue("   Théâtre   La   Paillette   "),"2, Rue du Pré de Bris")
  assertEquals(Data.getValue("ThéâtreLaPaillette"),"2, Rue du Pré de Bris")

}