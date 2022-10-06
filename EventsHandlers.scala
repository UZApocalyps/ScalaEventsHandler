import java.awt.event
import java.awt.event.{KeyListener, MouseMotionListener}
import scala.swing.{AbstractButton, Action, Button, Component, TextField}
import scala.swing.MenuBar.NoMenuBar.{listenTo, reactions}
import scala.swing.event.{ButtonClicked, Event, Key, KeyEvent, KeyPressed, KeyTyped, MouseEvent, ValueChanged}

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

  /**
    * This method is used to add a listener to a text field
    * @param textField
    * @param callback
    */
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

  /**
   * This method is used to add a listener to a key press
   * @param component
   * @param callback
   */
  def onKeyDown(component:Component,callback: (KeyDownEvent)=> Unit)
  {
    component.peer.addKeyListener(new KeyListener {
      override def keyPressed(e: event.KeyEvent): Unit = {
        callback(new KeyDownEvent(e.getKeyChar,component));
      }
      override def keyTyped(e: event.KeyEvent): Unit = {
      }

      override def keyReleased(e: event.KeyEvent): Unit = {
      }
    });
  }

  /**
   * This method is used to add a listener to a key release
   * @param component
   * @param callback
   */
  def onKeyUp(component: Component, callback: (KeyDownEvent) => Unit) {
    component.peer.addKeyListener(new KeyListener {
      override def keyPressed(e: event.KeyEvent): Unit = {
      }

      override def keyTyped(e: event.KeyEvent): Unit = {
      }

      override def keyReleased(e: event.KeyEvent): Unit = {
        callback(new KeyDownEvent(e.getKeyChar, component));
      }
    });
  }

  /**
   * This method is used to add a listener to a mouse move
   * @param component
   * @param callback
   */
  def onMouseMove(component: Component,callback: (MouseEnterEvent)=> Unit)
  {
    component.peer.addMouseMotionListener(new MouseMotionListener {
      override def mouseMoved(e: event.MouseEvent): Unit = {

        if(component.bounds.contains(e.getX,e.getY))
        {
          callback(new MouseEnterEvent(component));
        }
      }

      override def mouseDragged(e: event.MouseEvent): Unit = {

      }
    });
  }
  class KeyDownEvent(key:Char, source:Component) extends Event{
    def getKey:Char = key;
    def getSource:Component = source;
  }

  class MouseEnterEvent(source: Component) extends Event{
    def getSource:Component = source;
  }
}
