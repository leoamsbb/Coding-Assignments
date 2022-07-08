package cricket

sealed trait Ball {
  def value: Any
}

case class Runs(value: Int) extends Ball

case class Special(value: String) extends Ball

object Ball {
  implicit def fromInput: Any => Ball = {
    case num: Int => Runs(num)
    case str: String => Special(str)
  }
}
