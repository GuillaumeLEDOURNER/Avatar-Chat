package client

import machine._
import automaticTester.TestAvatar

object Client extends App {
	  UI.init()
	Data.openFile()
	
	TestAvatar.check(MachineImpl)
	

}
