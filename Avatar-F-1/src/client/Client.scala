package client

import machine._
import automaticTester.TestAvatar

object Client extends App 
{
    SpeechSynthetizer.voice()
  
	  UI.init()
	  Traduction.init()
	  Data.openFile()
	  XMLSearch.init()
	  
	 //while (true)
	 // SpeechSynthetizer.say("éki nokolao ")
	// TestAvatar.check(MachineImpl)
	
}