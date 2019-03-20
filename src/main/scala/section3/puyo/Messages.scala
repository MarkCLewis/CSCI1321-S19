package section3.puyo

import scalafx.scene.input.KeyCode

case class KeyPressed(code: KeyCode)
case class KeyReleased(code: KeyCode)