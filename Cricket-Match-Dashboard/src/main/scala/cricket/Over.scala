package cricket

case class Over(balls: List[Ball])

object Over {
  implicit def fromAny(args: List[Any]): Over = Over(args.map(Ball.fromInput))

  private val ONE = BigDecimal("1.0")
  private val overIncrementer = BigDecimal("0.1")

  def isOverDone(current: BigDecimal) = {
    val fractionalPart = current.remainder(ONE)
    fractionalPart >= 0.6
  }

  def incrementOver(current: BigDecimal) = {
    current + overIncrementer
  }
}