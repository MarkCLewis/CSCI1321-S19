package section3.basics

class Complex(val x: Double, val y: Double) {
  def +(c: Complex) = new Complex(x+c.x, y+c.y)
  def -(c: Complex) = new Complex(x-c.x, y-c.y)
  def *(c: Complex) = new Complex(x*c.x-y*c.y, x*c.y+y*c.x)
  def mag = math.sqrt(x*x+y*y)
}
