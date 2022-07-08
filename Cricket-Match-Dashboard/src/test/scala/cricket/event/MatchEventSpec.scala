package cricket.event

class MatchEventSpec extends AnyFlatSpec {

  "MatchEvent" should "return correct type of object depending on the input provided" in {
    val ball = fromBall(3)
    ball should have size 2
    ball.head shouldBe a [RunningRunsEvent]
    ball.head.runs shouldBe 3

    val four = fromBall(4)
    four.head shouldBe FourHitEvent
    four.head.runs shouldBe 4

    val six = fromBall(6)
    six.head shouldBe SixHitEvent
    six.head.runs shouldBe 6

    val wideBall = fromBall("Wd")
    wideBall.head shouldBe WideBallEvent
    wideBall.head.runs shouldBe 1

    val wicketBall = fromBall("W")
    wicketBall.head shouldBe WicketDownEvent
    wicketBall.head.runs shouldBe 0
  }

}
