package cricket

import scala.language.implicitConversions

case class Setup(
                  numOfPlayers: Int,
                  numOfOvers: Int,
                  battingOrderTeam1: List[String],
                  oversTeam1: List[Over],
                  battingOrderTeam2: List[String],
                  oversTeam2: List[Over]
                )