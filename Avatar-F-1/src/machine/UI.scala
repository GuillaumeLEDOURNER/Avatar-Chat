package machine

import scala.collection.mutable.ListBuffer

import scala.swing._
import scala.swing.event._

case class SomethingHappened() extends Event {
  println("Yes, something indeed has been published!")
}

object UI  {
 
  def init() {
   
    var rowData = Array("Bonjour que recherchez vous dans cet interface")
   
    var buffer = ListBuffer("Paris", "New York", "Tokyo", "Berlin", "Copenhagen")
   
    val frame = new MainFrame {
     
      title = "Kikoo Chat"
     
      val header = Array[String]("Chat")
     
      val list = new ListView[String]() {
        listData = buffer
      }
     
      val sendButton = new Button {
        text = "Send"
        reactions += {
          case ButtonClicked(_) => {
            this.publish(SomethingHappened())
            println("Salut")
            list.listData = buffer += "Coucou"
          }
        }
      }
     
      contents = new BoxPanel(Orientation.Vertical) {
        contents += new FlowPanel() {
          contents += new ScrollPane(list)
        }
        contents += new TextField {
         
        }
      }
    }
  }
}
