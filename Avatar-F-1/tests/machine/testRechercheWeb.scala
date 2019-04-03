package machine
import org.junit.Test
import org.junit.Assert._

class testRechercheWeb {
  
  @Test
  def testResultat{
    assertEquals("18 rue saint georges",RechercheWeb.rechercheWeb("je cherche la pizzeria la tomate"))
  }
  
  @Test
  def testSansResultat{
    assertEquals("Je ne comprends pas votre demande",RechercheWeb.rechercheWeb("je cherche le abc"))
  }
}