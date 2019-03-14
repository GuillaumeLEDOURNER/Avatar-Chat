package machine

import scala.collection.mutable.ListBuffer
import java.awt.Dimension

import scala.swing._
import scala.swing.event._

case class SomethingHappened() extends Event {
  
}

object UI {
 
  var rowData = Array("Bonjour que recherchez vous dans cet interface")
   
  var buffer = ListBuffer("Avatar : Bonjour que recherchez vous ?")
  
  def init() {
    val frame = new MainFrame {
      title = "Avatar Chat"
     
      val header = Array[String]("Chat")
       
      val list = new ListView[String]() {
        listData = buffer
        fixedCellWidth = 450
        fixedCellHeight = 20
        preferredSize = new Dimension(480, 400)
      }
      
      val inputField = new TextField 
      {
        maximumSize = new Dimension(400, 100)
      }
     
      val sendButton = new Button {
        text = "Send"
      reactions += {
        case ButtonClicked(_) => {
          this.publish(SomethingHappened())
            list.listData = buffer += "Moi : " + inputField.text
          }
        }
      }
     
      contents = new BoxPanel(Orientation.Vertical) {
        contents += new FlowPanel() {
          contents += new ScrollPane(list) {
            preferredSize = new Dimension(500, 400)
          }
        }
        contents += new BoxPanel(Orientation.Horizontal) {
          contents += inputField
          contents += sendButton
        }
      }
      pack()
      centerOnScreen()
      open()
    }
    frame.size = new Dimension(500,500)  
  }
}


