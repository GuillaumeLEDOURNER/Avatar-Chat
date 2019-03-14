package machine
import org.junit.Test
import org.junit.Assert._

class TestData {
  
  @Test
  def test1{
    assertEquals(Data.getValue("Mairie"),"Place de la Mairie")
    assertEquals(Data.getValue("Rennes"),"Place de la Mairie")
    assertEquals(Data.getValue("Théâtre"),"ambigue")
    assertEquals(Data.getValue("Paillette"),"2, Rue du Pré de Bris")
    assertEquals(Data.getValue("Théâtre La Paillette"),"2, Rue du Pré de Bris")
    assertEquals(Data.getValue("Paillette"),"2, Rue du Pré de Bris")
    assertEquals(Data.getValue("Théâtre National de Bretagne"),"1, Rue Saint-Hélier")
    assertEquals(Data.getValue("Théâtre National"),"1, Rue Saint-Hélier")
    assertEquals(Data.getValue("National Théâtre"),"1, Rue Saint-Hélier")    
    assertEquals(Data.getValue("National de Bretagne"),"1, Rue Saint-Hélier")
    assertEquals(Data.getValue("Bretagne"),"1, Rue Saint-Hélier")
    assertEquals(Data.getValue("National"),"1, Rue Saint-Hélier")
    assertEquals(Data.getValue(" Gare SNCF"),"19, Place de la Gare")
    assertEquals(Data.getValue("SNCF"),"19, Place de la Gare")
    assertEquals(Data.getValue("Gare"),"Place de la Gare")
    
    
    
    
    
    
    
    
    
    
    
  }
}