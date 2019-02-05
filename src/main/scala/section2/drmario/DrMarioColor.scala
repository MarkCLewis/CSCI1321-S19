package section2.drmario

object DrMarioColor extends Enumeration {
  val Red, Yellow, Blue = Value
  
  def random(): DrMarioColor.Value = {
    values.toSeq(util.Random.nextInt(values.size))
  }
}