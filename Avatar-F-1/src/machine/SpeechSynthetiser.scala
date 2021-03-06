package machine

import scala.collection.JavaConversions._
import marytts.LocalMaryInterface
import marytts.exceptions.MaryConfigurationException
import marytts.exceptions.SynthesisException
import marytts.util.data.audio.AudioPlayer

object SpeechSynthetizer {
    val marytts = new LocalMaryInterface
    val ap = new AudioPlayer
   
    val voices = List("dfki-pavoque-neutral-hsmm", "upmc-pierre-hsmm", "istc-lucia-hsmm", "cmu-slt-hsmm")
   
    def voice() {
      try {
        marytts.setVoice(voices(1))
      } catch {
        case e: MaryConfigurationException => println(e)
      }
    }
   
     def say(input : String) {
     
       
       try {
        val audio = marytts.generateAudio(input)
        ap.setAudio(audio)
        ap.start()
        ap.join()
        
           
      } catch {
        case e: SynthesisException => println(e)
      }
      finally
      {
         
      }
 
    }
   
    def getVoices() : java.util.Set[String] = {
        marytts.getAvailableVoices
    }
}