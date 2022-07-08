package cricket.event

import cricket.{Ball, Runs, Special}

import scala.language.implicitConversions


sealed trait MatchEvent {
  def runs: Int = 0
}

case object BattingStartedEvent extends MatchEvent

case object WicketDownEvent extends MatchEvent

case object OverChangeEvent extends MatchEvent

case object StrikeChangeEvent extends MatchEvent


case object WideBallEvent extends MatchEvent {
  override def runs: Int = 1
}

case object FourHitEvent extends MatchEvent {
  override def runs: Int = 4
}

case object SixHitEvent extends MatchEvent {
  override def runs: Int = 6
}

case class RunningRunsEvent(override val runs: Int) extends MatchEvent

object MatchEvent {
  def fromBall: Ball => List[MatchEvent] = {
    case Runs(4) => List(FourHitEvent)
    case Runs(6) => List(SixHitEvent)
    case Runs(b) if b % 2 == 1 => List(RunningRunsEvent(b), StrikeChangeEvent)
    case Runs(b) => List(RunningRunsEvent(b))
    case Special("Wd") => List(WideBallEvent)
    case Special("W") => List(WicketDownEvent)
    case _ => Nil
  }
}
