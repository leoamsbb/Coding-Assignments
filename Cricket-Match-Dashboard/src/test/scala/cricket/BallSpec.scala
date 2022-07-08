package cricket

class BallSpec extends AnyFlatSpec {

  "Ball" should "create correct type of object provided any input" in {
    val ball:Ball = 1
    ball shouldBe a [Runs]
    ball.value shouldBe 1

    val special:Ball = "Wd"
    special shouldBe a [Special]
    special.value shouldBe "Wd"
  }

}
