package machine
import org.junit.Test
import org.junit.Assert._

class TestCorrection {

  // initialisation des objets sous test

  val c : MyCorrection = new MyCorrection
  
   @Test
  def test1_DistanceMemeTaille{    
    assertEquals(true,c.distancedeHamming("abcd","abcd"))
    assertEquals(true,c.distancedeHamming("abcd","abcc"))
    assertEquals(false,c.distancedeHamming("abcd","aaaa"))
    assertEquals(true,c.distancedeHamming("abcd-abc","abcd abc"))
    assertEquals(true,c.distancedeHamming("ABC","abc"))
  }
  
  @Test
  def test2_DistanceTaillesDifferentes{
    assertEquals(true,c.distancedeHamming("hotel","hotl"))
  }
  
}