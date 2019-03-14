package machine

import scala.collection.mutable.ListBuffer
import java.awt.Dimension
import scala.util.Random

import scala.swing._
import scala.swing.event._

case class SomethingHappened() extends Event {
  
}

object UI {
   
  var buffer = ListBuffer("<html><font color='white'>Avatar : Wesh !</font></html>")
  
  var questions = Array("Une autre question ?", "Cherchez vous autre chose ?", "Que puis-je faire d'autre pour vous ?", "Très bonne question ! Autre chose ?", "")
  
  val randomizer = new Random
  
  def init() {
    val frame = new MainFrame {
      title = "Avatar Chat"
     
      val header = Array[String]("Chat")
       
      val list = new ListView[String]() {
        listData = buffer
        fixedCellWidth = 450
        fixedCellHeight = 20
        background = new Color(255, 255, 255);
      }
      
      val inputField = new TextField {
        maximumSize = new Dimension(400, 100)
      }
     
      val sendButton = new Button {
        text = "<html><font color='red'>Send</font></html>"
        maximumSize = new Dimension(100, 20)
        background = new Color(255, 255, 255);
        reactions += {
          case ButtonClicked(_) => {
            this.publish(SomethingHappened())
            list.listData = buffer += "<html><font color='black'>Moi : " + inputField.text + "</font></html>"
            inputField.text = ""
            list.listData = buffer += "<html><font color='black'>Avatar : " + questions(randomizer.nextInt(questions.size)) + "</font></html>"
          }
        }
      }
     
      contents = new BoxPanel(Orientation.Vertical) {
        background = new Color(255, 255, 255);
        contents += new FlowPanel() {
          background = new Color(255, 255, 255);
          contents += new ScrollPane(list) {
            background = new Color(255, 255, 255);
            preferredSize = new Dimension(500, 350)
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
    frame.size = new Dimension(500,500)  
  }
}


