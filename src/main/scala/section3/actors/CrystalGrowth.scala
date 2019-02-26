package section3.actors

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.image.WritableImage
import scalafx.scene.image.ImageView
import akka.actor.ActorSystem
import akka.actor.Props

object CrystalGrowth extends JFXApp {
  val system = ActorSystem("CrystalGrowth")

  val img = new WritableImage(1000, 1000)
  val actor1 = system.actorOf(Props(new Solution(img)), "Solution")

  stage = new JFXApp.PrimaryStage {
    title = "Crystals!"
    scene = new Scene(1000, 1000) {
      content = new ImageView(img)
    }
  }
}