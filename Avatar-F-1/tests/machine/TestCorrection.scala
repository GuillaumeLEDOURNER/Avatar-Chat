package machine
import org.junit.Test
import org.junit.Assert._

class TestCorrection {

  // initialisation des objets sous test
  
  // tests
  @Test
  def test1_1{    
    assertTrue(Correction.hammingDistance("test", "test"))
    assertTrue(Correction.hammingDistance("tst", "test"))
    assertTrue(Correction.hammingDistance("est", "test"))
    assertTrue(Correction.hammingDistance("tes", "test"))
    assertFalse(Correction.hammingDistance("te", "test"))
    assertFalse(Correction.hammingDistance("ts", "test"))
    assertFalse(Correction.hammingDistance("st", "test"))
    assertFalse(Correction.hammingDistance("t", "test"))
    assertFalse(Correction.hammingDistance("e", "test"))
    assertFalse(Correction.hammingDistance("s", "test"))
    assertFalse(Correction.hammingDistance("t", "test"))
    
    
  }
  
}