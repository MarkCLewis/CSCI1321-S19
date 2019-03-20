package section2.drmario

case class PassableBoard(cells: Seq[PassableCell], drawCurrent: Boolean,
    pp1: PassableCell, pp2: PassableCell)