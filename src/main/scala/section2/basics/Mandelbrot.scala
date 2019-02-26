package section2.basics

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.image.WritableImage
import scalafx.scene.image.ImageView
import scalafx.scene.paint.Color
import scalafx.scene.input.MouseEvent

object Mandelbrot extends JFXApp {
  private var xmin = -1.5
  private var xmax = 0.5
  private var ymin = -1.0
  private var ymax = 1.0
  
  val maxCount = 1000
  def mandelCount(c: Complex): Int = {
    var z = new Complex(0, 0)
    var cnt = 0
    while (cnt < maxCount && z.mag < 4) {
      z = z * z + c
      cnt += 1
    }
    cnt
  }

  def colorFromCount(cnt: Int): Color = {
    if (cnt == maxCount) Color.Black
    else Color(1.0, 0.0, 0.0, math.log(cnt + 1) / math.log(maxCount))
  }
  
  def pixelToX(i: Double, img: WritableImage): Double = xmin + i * (xmax - xmin) / img.width()
  def pixelToY(j: Double, img: WritableImage): Double = ymin + j * (ymax - ymin) / img.height()
  
  def drawMandelbrot(img: WritableImage): Unit = {
    val writer = img.pixelWriter
    val start = System.nanoTime()
    for (j <- (0 until img.height().toInt).par) {
      val y = pixelToY(j, img)
      for (i <- (0 until img.width().toInt).par) {
        val x = pixelToX(i, img)
        val cnt = mandelCount(new Complex(x, y))
        val color = colorFromCount(cnt)
        writer.setColor(i, j, color)
      }
    }
    println("Took " + ((System.nanoTime() - start) * 1e-9) + " seconds.")
  }

  stage = new PrimaryStage {
    title = "Mandelbrot"
    scene = new Scene(1000, 1000) {
      val img = new WritableImage(1000, 1000)
      val imgView = new ImageView(img)
      content = imgView
      drawMandelbrot(img)
      imgView.onMouseClicked = (e: MouseEvent) => {
    	  val x = pixelToX(e.x, img)
        val y = pixelToY(e.y, img)
        new JuliaSet(new Complex(x, y))
      }
    }
  }
}

