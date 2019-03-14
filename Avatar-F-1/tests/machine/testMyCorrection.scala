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
    assertTrue(c.distancedeHamming("elle","elle"))
    assertTrue(c.distancedeHamming("elle","ella"))
    assertFalse(c.distancedeHamming("elle","elaa"))
    assertFalse(c.distancedeHamming("elle","abcd"))
  }
  
  @Test
  def test2_DistanceTaillesDifferentes{
    assertEquals(true,c.distancedeHamming("hotl","hotel"))
    assertEquals(false,c.distancedeHamming("htl","hotel"))
    
    assertEquals(true,c.distancedeHamming("avon","avion"))
    assertEquals(false,c.distancedeHamming("savon","avion"))
    
    assertEquals(true,c.distancedeHamming("are","gare"))
    assertEquals(true,c.distancedeHamming("gae","gare"))
    
    assertEquals(true,c.distancedeHamming("gar","gare"))
    
    assertEquals(false,c.distancedeHamming("ga","gare"))
    
    assertEquals(true,c.distancedeHamming("gre","gare"))
    assertEquals(true,c.distancedeHamming("are","gare"))
    
    assertEquals(true,c.distancedeHamming("aison","maison"))
    assertEquals(true,c.distancedeHamming("mison","maison"))
    assertEquals(true,c.distancedeHamming("mason","maison"))
    assertEquals(true,c.distancedeHamming("maion","maison"))
    assertEquals(true,c.distancedeHamming("maisn","maison"))
    assertEquals(true,c.distancedeHamming("maiso","maison"))
    assertEquals(true,c.distancedeHamming("maison","maison"))
    
    assertEquals(false,c.distancedeHamming("hatl","hotel"))
    assertEquals(false,c.distancedeHamming("hott","hotel"))
  
   assertEquals(false, c.distancedeHamming("americaine", "vie"))
   assertEquals(false, c.distancedeHamming("gare", "tnb"))
  }
  
}