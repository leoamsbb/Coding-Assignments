package cricket

class PlayerRSpec extends AnyFlatSpec {

  it should "be able to print the information in required format" in {
    val player = Player("P1", List(RunningRunsEvent(1), RunningRunsEvent(1), RunningRunsEvent(1)))
    player.toString shouldBe "P1 3 0 0 3"
  }

  it should "be print asterisk in front of the player name if he's not out" in {
    val player = Player("P1", List(BattingStartedEvent, RunningRunsEvent(1), RunningRunsEvent(1), RunningRunsEvent(1)))
    player.toString shouldBe "P1* 3 0 0 3"
  }

  it should "print correct string for player who is out" in {
    val player = Player("P1", List(BattingStartedEvent, RunningRunsEvent(1), RunningRunsEvent(1), RunningRunsEvent(1), WicketDownEvent))
    player.toString shouldBe "P1 3 0 0 4"
  }

  it should "calculate total runs without wide balls" in {
    val player = Player("P2", List(WideBallEvent, RunningRunsEvent(3), WicketDownEvent))
    player.toString shouldBe "P2 3 0 0 2"
  }

  it should "be able to append event to the player" in {
    val player = Player("P3", List(WideBallEvent, RunningRunsEvent(3))).addEvent(WicketDownEvent)
    player.toString shouldBe "P3 3 0 0 2"
  }

  it should "count number of balls correctly" in {
    val player = Player("P4", List(BattingStartedEvent, WideBallEvent, RunningRunsEvent(2), WicketDownEvent))
    player.toString shouldBe "P4 2 0 0 2"
  }
}
