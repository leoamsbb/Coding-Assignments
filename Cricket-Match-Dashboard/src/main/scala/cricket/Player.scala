package cricket

import cricket.event.{BattingStartedEvent, FourHitEvent, MatchEvent, SixHitEvent, WicketDownEvent, WideBallEvent}


case class Player(name: String, battingRecord: List[MatchEvent] = Nil) {
  override def toString: String = s"$name$getAliveStatus $score $fours $sixes $noOfBallsFaced"

  private def getAliveStatus: String = {
    val isOut = battingRecord.contains(WicketDownEvent)
    val notBattedYet = !battingRecord.contains(BattingStartedEvent)

    (notBattedYet, isOut) match {
      case (false, false) => "*"
      case _ => ""
    }
  }

  private def score: Int = battingRecord
    .filterNot(_ == WideBallEvent)
    .map(_.runs).sum

  private def fours: Int = battingRecord.count(_ == FourHitEvent)

  private def sixes: Int = battingRecord.count(_ == SixHitEvent)

  private def noOfBallsFaced: Int = battingRecord
    .filterNot(_ == BattingStartedEvent)
    .filterNot(_ == WideBallEvent)
    .size

  def scoreWithExtras: Int = battingRecord.map(_.runs).sum

  def addEvent(event: MatchEvent): Player =
    this.copy(battingRecord = event :: battingRecord)
}
