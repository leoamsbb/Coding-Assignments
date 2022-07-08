package cricket.application

class ScoreCardSpec extends AnyFlatSpec {

  "Scorecard" should "print the scorecard properly" in {
    val players = List(
      Player("P1", List(BattingStartedEvent, RunningRunsEvent(1), RunningRunsEvent(1), RunningRunsEvent(1))),
      Player("P2", List(BattingStartedEvent, RunningRunsEvent(1), RunningRunsEvent(1), RunningRunsEvent(2))),
      Player("P3"),
      Player("P4"),
      Player("P5")
    )
    val overs = 1
    val sc = ScoreCard(players, overs)

    sc.toString shouldBe
      """
        |Player Name Score 4s 6s Balls
        |P1* 3 0 0 3
        |P2* 4 0 0 3
        |P3 0 0 0 0
        |P4 0 0 0 0
        |P5 0 0 0 0
        |
        |Total: 7/0
        |
        |Overs: 1
        |""".stripMargin
  }

  it should "calculate number of wickets down correctly" in {
    val players = List(
      Player("P1", List(BattingStartedEvent, RunningRunsEvent(1), RunningRunsEvent(1), RunningRunsEvent(1), WicketDownEvent)),
      Player("P2", List(BattingStartedEvent, RunningRunsEvent(1), RunningRunsEvent(1), RunningRunsEvent(2), SixHitEvent)),
      Player("P3", List(BattingStartedEvent, FourHitEvent, FourHitEvent, WideBallEvent, WicketDownEvent)),
      Player("P4", List(BattingStartedEvent, RunningRunsEvent(1))),
      Player("P5")
    )
    val sc = ScoreCard(players, 2)

    sc.toString shouldBe
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
  }
}
