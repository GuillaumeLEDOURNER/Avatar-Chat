package machine

import scala.collection.mutable.ListBuffer
import java.awt.Dimension
import scala.util.Random

import scala.swing._
import scala.swing.event._

case class SomethingHappened() extends Event {
  
}

object UI {
   
  var buffer = ListBuffer("<html><font color='green'>Avatar : Bienvenue sur le chat !</font></html>")
  
 
  
  val randomizer = new Random
  
  def init() {
    val frame = new MainFrame {
      title = "Avatar Chat"
     
      val header = Array[String]("Chat")
       
      val list = new ListView[String]() {
        listData = buffer
        fixedCellWidth = 800
        fixedCellHeight = 30
        background = new Color(255, 255, 255);
      }
      
      val inputField = new TextField {
      }
     
      val sendButton = new Button {
        text = "<html><font color='red'>Send</font></html>"
        maximumSize = new Dimension(20, 20)
        background = new Color(255, 255, 255);
        reactions += {
          case ButtonClicked(_) => {
            this.publish(SomethingHappened())
            list.listData = buffer += "<html><font color='black'>Moi : " + inputField.text + "</font></html>"
            val results = MachineImpl.ask(inputField.text);
            var isFirst = true
            for(r <- results) {
              if(isFirst) {
                list.listData = buffer += "<html><font color='black'>Avatar : " + r + "</font></html>"
              } else {
                list.listData = buffer += "<html><font color='black'>" + r + "</font></html>"
                isFirst = false
              }
            }
            inputField.text = ""
          }
        }
      }
     
      contents = new BoxPanel(Orientation.Vertical) {
        background = new Color(255, 255, 255);
        contents += new FlowPanel() {
          background = new Color(255, 255, 255);
          contents += new ScrollPane(list) {
            background = new Color(255, 255, 255);
            preferredSize = new Dimension(840, 350)
          }
        }
        contents += new BoxPanel(Orientation.Horizontal) {
          background = new Color(255, 255, 255);
          contents += inputField
          contents += sendButton
        }
      }
      pack()
      centerOnScreen()
      open()
    }
    frame.size = new Dimension(850,500)  
  }
}


