import scala.swing.{AbstractButton, Action, Button, Component, TextField}
import scala.swing.MenuBar.NoMenuBar.{listenTo, reactions}
import scala.swing.event.{ButtonClicked, ValueChanged}

object EventsHandlers{



  /**
    * This method is used to add a listener to a button
    * @param button
    * @param callback
    */
  def onClick(button:Button, callback: (AbstractButton)=> Unit) =
  {
    listenTo(button);
    reactions += {
      case ButtonClicked(b) => {
        callback(b);
      }
    }
  }
  def onChange(textField:TextField, callback: (TextField)=> Unit) =
  {
    listenTo(textField);
    reactions += {
      case ValueChanged(txt) => {
        val textField = txt.asInstanceOf[TextField];
        callback(textField);
      }
    }
  }

}
