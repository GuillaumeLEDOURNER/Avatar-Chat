package machine
import org.junit.Test
import org.junit.Assert._

class TestCorrection {

  // initialisation des objets sous test

  
  
  @Test
  def test1_DistanceMemeTaille{    
    assertEquals(true,MyCorrection.distancedeHamming("abcd","abcd"))
    assertEquals(true,MyCorrection.distancedeHamming("abcd","abcc"))
    assertEquals(false,MyCorrection.distancedeHamming("abcd","aaaa"))
    assertEquals(true,MyCorrection.distancedeHamming("abcd-abc","abcd abc"))
    assertEquals(true,MyCorrection.distancedeHamming("ABC","abc"))
    assertTrue(MyCorrection.distancedeHamming("elle","elle"))
    assertTrue(MyCorrection.distancedeHamming("elle","ella"))
    assertFalse(MyCorrection.distancedeHamming("elle","elaa"))
    assertFalse(MyCorrection.distancedeHamming("elle","abcd"))
  }
  
  @Test
  def test2_DistanceTaillesDifferentes{
    assertEquals(true,MyCorrection.distancedeHamming("hotl","hotel"))//test
    assertEquals(false,MyCorrection.distancedeHamming("htl","hotel"))
    
    assertEquals(true,MyCorrection.distancedeHamming("avon","avion"))
    assertEquals(false,MyCorrection.distancedeHamming("savon","avion"))
    
    assertEquals(true,MyCorrection.distancedeHamming("are","gare"))
    assertEquals(true,MyCorrection.distancedeHamming("gae","gare"))
    
    assertEquals(true,MyCorrection.distancedeHamming("gar","gare"))
    
    assertEquals(false,MyCorrection.distancedeHamming("ga","gare"))
    
    assertEquals(true,MyCorrection.distancedeHamming("gre","gare"))
    assertEquals(true,MyCorrection.distancedeHamming("are","gare"))
    
    assertEquals(true,MyCorrection.distancedeHamming("aison","maison"))
    assertEquals(true,MyCorrection.distancedeHamming("mison","maison"))
    assertEquals(true,MyCorrection.distancedeHamming("mason","maison"))
    assertEquals(true,MyCorrection.distancedeHamming("maion","maison"))
    assertEquals(true,MyCorrection.distancedeHamming("maisn","maison"))
    assertEquals(true,MyCorrection.distancedeHamming("maiso","maison"))
    assertEquals(true,MyCorrection.distancedeHamming("maison","maison"))
    
    assertEquals(false,MyCorrection.distancedeHamming("hatl","hotel"))
    assertEquals(false,MyCorrection.distancedeHamming("hott","hotel"))
  
   assertEquals(false, MyCorrection.distancedeHamming("americaine", "vie"))
   assertEquals(false, MyCorrection.distancedeHamming("gare", "tnb"))
  }
  
}