package cricket.application

import cricket.Over.{incrementOver, isOverDone}
import cricket.Player
import cricket.event._

import scala.math.BigDecimal.RoundingMode

case class ScoreCard(players: List[Player] = Nil, overs: BigDecimal = 0.0, batsman: (String, String) = ("", "")) {
  override def toString: String =
    s"""
       |Player Name Score 4s 6s Balls
       |${players.mkString("\n")}
       |
       |Total: $calculateTotalScore/$numOfWicketsDown
       |
       |Overs: $overs
       |""".stripMargin

  private def calculateTotalScore = players
    .map(_.scoreWithExtras)
    .sum

  private def numOfWicketsDown = players
    .flatMap(_.battingRecord)
    .count(_ == WicketDownEvent)
}

object ScoreCard {

  def applyEvent(current: ScoreCard, event: MatchEvent): ScoreCard = {
    event match {
      case StrikeChangeEvent =>
        val (striker, nonStriker) = current.batsman
        current.copy(batsman = (nonStriker, striker))

      case OverChangeEvent =>
        val currentOver = current.overs
        if (isOverDone(currentOver))
          current.copy(overs = currentOver.setScale(0, RoundingMode.UP))
        else
          current

      case WicketDownEvent =>
        val nextBatsmanIndex = getNextBatsmanIndex(current.players)
        val updatedStriker = strikerUpdated(current.players, current.batsman._1, event)
        if (noBatsmanLeft(nextBatsmanIndex))
          current.copy(players = updatedStriker, overs = incrementOver(current.overs))
        else {
          val nextBatsman = current.players(nextBatsmanIndex).addEvent(BattingStartedEvent)
          val updatedNextBatsman = updatedStriker.updated(nextBatsmanIndex, nextBatsman)
          current.copy(players = updatedNextBatsman, batsman = (nextBatsman.name, current.batsman._2), overs = incrementOver(current.overs))
        }

      case _ =>
        val updatedPlayers = strikerUpdated(current.players, current.batsman._1, event)
        current.copy(players = updatedPlayers, overs = incrementOver(current.overs))
    }
  }


  private def strikerUpdated(players: List[Player], striker: String, event: MatchEvent) = {
    val strikerPlayerIndex = players.indexWhere(_.name == striker)
    val strikerPlayer = players(strikerPlayerIndex)
    players.updated(strikerPlayerIndex, strikerPlayer.addEvent(event))
  }

  private def getNextBatsmanIndex(players: List[Player]) =
    players.indexWhere(!_.battingRecord.contains(BattingStartedEvent))

  private def noBatsmanLeft(nextBatsmanIndex: Int) = nextBatsmanIndex == -1
}
