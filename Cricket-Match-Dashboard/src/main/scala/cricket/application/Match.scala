package cricket.application

import cricket.application.ScoreCard.applyEvent
import cricket.event._
import cricket.{Over, Player, Setup}

object Match {

  def playMatch(setup: Setup): List[ScoreCard] = {
    val startMatchTeam1 = startOfTheMatch(setup.battingOrderTeam1)
    val scTeam1 = prepareScoreCard(startMatchTeam1, setup.oversTeam1)

    val startMatchTeam2 = startOfTheMatch(setup.battingOrderTeam2)
    val scTeam2 = prepareScoreCard(startMatchTeam2, setup.oversTeam2)

    List(scTeam1, scTeam2)
  }

  private def prepareScoreCard(startMatch: ScoreCard, overs: List[Over]) = {
    overs
      .flatMap(over => over.balls.flatMap(MatchEvent.fromBall) ::: List(OverChangeEvent, StrikeChangeEvent))
      .dropRight(1)
      .foldLeft(startMatch)(applyEvent)
  }

  private def startOfTheMatch(battingOrder: List[String]) = {
    val players = battingOrder.map(Player(_))
    val (first, second) = (players.head, players.tail.head)
    val startingBatting = players
      .updated(0, first.addEvent(BattingStartedEvent))
      .updated(1, second.addEvent(BattingStartedEvent))

    ScoreCard(players = startingBatting, batsman = (first.name, second.name))
  }
}
