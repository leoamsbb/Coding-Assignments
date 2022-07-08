package cricket.application

class MatchSpec extends AnyFlatSpec {

  val setup = Setup(
    numOfPlayers = 5,
    numOfOvers = 2,
    battingOrderTeam1 = List("P1", "P2", "P3", "P4", "P5"),
    oversTeam1 = List(List(1, 1, 1, 1, 1, 2), List("W", 4, 4, "Wd", "W", 1, 6)),
    battingOrderTeam2 = List("P6", "P7", "P8", "P9", "P10"),
    oversTeam2 = List(List(4, 6, "W", "W", 1, 1), List(6, 1, "W", "W"))
  )

  "Match" should "print correct scorecard after 1 over" in {
    val scoreCards = Match.playMatch(setup)
    val expectedSCTeam1 =
      """
        |Player Name Score 4s 6s Balls
        |P1 3 0 0 4
        |P2* 10 0 1 4
        |P3 8 2 0 3
        |P4* 1 0 0 1
        |P5 0 0 0 0
        |
        |Total: 23/2
        |
        |Overs: 2
        |""".stripMargin

    val expectedSCTeam2 =
      """
        |Player Name Score 4s 6s Balls
        |P6 10 1 1 3
        |P7* 8 0 1 3
        |P8 0 0 0 1
        |P9 1 0 0 2
        |P10 0 0 0 1
        |
        |Total: 19/4
        |
        |Overs: 1.4
        |""".stripMargin

    scoreCards.head.toString shouldBe expectedSCTeam1
    scoreCards.tail.head.toString shouldBe expectedSCTeam2
  }
}
