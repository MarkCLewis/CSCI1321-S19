package section3.actors

import akka.actor.Actor
import scalafx.scene.image.WritableImage
import scalafx.scene.paint.Color
import akka.actor.Props
import scalafx.application.Platform

class Solution(img: WritableImage) extends Actor {
  import Solution._

  val writer = img.pixelWriter
  val reader = img.pixelReader.get
  
  // Draw a line across the bottom for precipitate to stick to.
  for (i <- 0 until img.width.value.toInt) writer.setColor(i, img.height.value.toInt - 1, Color.Black)

  // Create a bunch of children
  for (i <- 1 to 1000) {
    context.actorOf(Props(new Precipitate(img.width.value.toInt, img.height.value.toInt)))
  }
  // Send the initial message to the children to get things started. 
  for (c <- context.children) {
    c ! Precipitate.MoveFrom(util.Random.nextInt(img.width.value.toInt), 0)
  }

  /**
   * Receive required by all actors. Has cases for all the messages that this actor should get.
   */
  def receive = {
    case CheckLocation(x, y, oldX, oldY) =>
      if (reader.getArgb(x, y) != 0) {  // Color value 0 is the default for the image.
        Platform.runLater(writer.setColor(oldX, oldY, Color.Black))  // Draw a black dot at the old location.
        sender ! Precipitate.MoveFrom(util.Random.nextInt(img.width.value.toInt), 0)  // Move to the top again.
      } else sender ! Precipitate.MoveFrom(x, y)
    case m => println("Unhandled message in Solution: " + m)
  }
}

object Solution {
  case class CheckLocation(x: Int, y: Int, oldX: Int, oldY: Int)
}