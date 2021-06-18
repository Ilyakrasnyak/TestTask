import scala.annotation.tailrec

/** Написать функцию, которая будет проверять являются ли два дерева одинаковыми.
  * Желателен рекурсивный подход без переменных.
  */

object Task2 extends App {

  case class TreeNode[+X](
      value: X,
      left: Option[TreeNode[X]],
      right: Option[TreeNode[X]]
  ) {}

  def isSameTree[X](
      tree1: Option[TreeNode[X]],
      tree2: Option[TreeNode[X]]
  ): Boolean = {
    (tree1, tree2) match {
      case (Some(tree1), Some(tree2)) =>
        tree1.value == tree2.value &&
          isSameTree(tree1.left, tree2.left) &&
          isSameTree(tree1.right, tree2.right)
      case (None, None) => true
      case _            => false
    }

  }

  type FlattenTree[_] = List[Option[TreeNode[_]]]
  def isSameTreeStackSave[X](
      tree1: Option[TreeNode[X]],
      tree2: Option[TreeNode[X]]
  ): Boolean = {
    @tailrec
    def loop(
        same: Boolean,
        ft1: FlattenTree[X],
        ft2: FlattenTree[X]
    ): Boolean = {
      if (same) {
        (ft1, ft2) match {
          case (Nil, Nil) => same
          case (t1 :: ts1, t2 :: ts2) =>
            (t1, t2) match {
              case (None, None) => loop(same, ts1, ts2)
              case (
                    Some(TreeNode(value1, l1, r1)),
                    Some(TreeNode(value2, l2, r2))
                  ) =>
                loop(value1 == value2, l1 :: r1 :: ts1, l2 :: r2 :: ts2)
              case _ => false
            }
        }
      } else false
    }
    loop(same = true, List(tree1), List(tree2))
  }

  val simpleP1 = TreeNode(1, None, None)
  val simpleQ1 = TreeNode(1, None, None)
  assert(isSameTree(Some(simpleP1), Some(simpleQ1)))
  assert(isSameTreeStackSave(Some(simpleP1), Some(simpleQ1)))

  val simpleP2 = TreeNode(1, None, None)
  val simpleQ2 = TreeNode(2, None, None)
  assert(!isSameTree(Some(simpleP2), Some(simpleQ2)))
  assert(!isSameTreeStackSave(Some(simpleP2), Some(simpleQ2)))

  val p = TreeNode(1, Some(TreeNode(2, None, None)), None)
  val q = TreeNode(1, None, Some(TreeNode(2, None, None)))
  assert(!isSameTree(Some(p), Some(q)))
  assert(!isSameTreeStackSave(Some(p), Some(q)))
}
