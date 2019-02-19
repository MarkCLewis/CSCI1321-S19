package section3.basics

/**
This is a basic main for you to start off with.
*/
object HelloWorld {
	def main(args: Array[String]): Unit = {
		println("Hello World!")
	}
	
	def square(x: Double) = x*x
	
	def cube(x: Double) = x*x*x
	
	def makeAnimalSounds(animal: Animal): Unit = {
	  println(animal.sound())
	}
}

trait Animal {
  def age: Int
  def weight: Double
  def sound(): String
}

class Cow(val age: Int, val weight: Double) extends Animal {
  def sound() = "Moo"
}

class Giraffe extends Animal {
  val age = 10
  val weight = 786.0
  def sound() = "Bleat"
}