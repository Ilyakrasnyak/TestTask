/**
  *  Есть строка, нужно используя методы коллекций скалы,
  *  сгруппировать буквы по символу,
  *  отсортировать группы по количеству символов и
  *  сджойнить группы в одну строку.
  *  Пример: “abcaba” -> “aaabbc” или “aacbc” -> “aaccb”
  */

object Task1 extends App {

  val t = "asdasdaaaweqbbbbasdasd"

  assert(groupAndSorting(t) == "aaaaaaassssddddbbbbwqe")

  def groupAndSorting(values: String) = {
    values.groupBy(identity)
    .toList
    .sortBy(v => (v._2.length, v._1))(Ordering.Tuple2[Int, Char].reverse)
    .map(_._2)
    .mkString("")
  }

}
