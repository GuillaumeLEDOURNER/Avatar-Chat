package machine

import scala.collection.JavaConversions._

import javax.sound.sampled.AudioInputStream
import marytts.LocalMaryInterface
import marytts.MaryInterface
import marytts.exceptions.MaryConfigurationException
import marytts.exceptions.SynthesisException
import marytts.util.data.audio.AudioPlayer

import marytts.language.fr.FrenchConfig

object SpeechSynthetizer {
    val marytts = new LocalMaryInterface
    val ap = new AudioPlayer
   
    val voices = List("dfki-pavoque-neutral-hsmm", "umc-pierre-hsmm", "istc-lucia-hsmm", "cmu-slt-hsmm")
   
    def voice() {
      try {
        marytts.setVoice(voices(0))
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
    }
   
    def getVoices() : java.util.Set[String] = {
        marytts.getAvailableVoices
    }
}