package section3.puyo

case class PassableBoard(bobas: List[PassableBoba], drawCurrent: Boolean, 
    p1: PassableBoba, p2: PassableBoba)