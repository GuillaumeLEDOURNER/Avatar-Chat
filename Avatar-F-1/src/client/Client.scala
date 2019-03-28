package client

import machine._
import automaticTester.TestAvatar

object Client extends App 
{

	  UI.init()
	  Traduction.init()
	  Data.openFile()
	  XMLSearch.init()
	 TestAvatar.check(MachineImpl)
	

}