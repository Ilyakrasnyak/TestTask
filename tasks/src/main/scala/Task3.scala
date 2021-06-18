import scala.annotation.tailrec
import scala.io.StdIn.readLine

/**
  * Написать функцию, которая принимает строку в консоли и говорит это валидный email или нет
  */
object Task3 extends App {

  @tailrec
  def run: Any = {
    if (isValidEmail(readLine())) {
      println("Email is valid")
    } else {
      println("Email is invalid")
    }
    run
  }

  def isValidEmail(str: String): Boolean =
    str match {
      case str if str.trim.isEmpty => false
      case str if emailRegex.findFirstMatchIn(str).isDefined => true
      case _ => false
    }
  val emailRegex =
    """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r

  run
}
