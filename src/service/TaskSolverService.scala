package service

import model.Square
/*import scala.util.control.Breaks._*/

// Bruteforcer
class TaskSolverService(val squares : List[Square]) {

  /*private val solutions : Array[List[Int]] = Array[List[Int]]()*/

  def findSolution(): List[List[Square]] = {

    val indexes = (for (i <- 0 to 11) yield i).toList

    // TODO: performance optimization (another data structures or AKKA?)
    // 12! = 479001600, calculation time ~ 3 min
    val solutions = indexes.permutations.filter(p => checkGreenCorners(p) && checkYellowCorners(p))

    /*perm(12)  // 12! = 479001600, calculation time ~ 3 min*/

    // covert from combinations to squares orders
    (for (squaresCombinations <- solutions) yield {
      (for (s <- squaresCombinations) yield {
        new Square(squares.apply(s:Int).topLeft, squares.apply(s:Int).topRight,
                   squares.apply(s:Int).bottomLeft, squares.apply(s:Int).bottomRight)
      }) : List[Square]
    }).toList
  }

  private def checkGreenCorners(perm : List[Int]) : Boolean = {
    // top
    if (squares.apply(perm.apply(0)).bottomRight + squares.apply(perm.apply(1)).bottomLeft
        + squares.apply(perm.apply(3)).topRight + squares.apply(perm.apply(4)).topLeft != 10) {
      return false
    }
    // left
    if (squares.apply(perm.apply(2)).bottomRight + squares.apply(perm.apply(3)).bottomLeft
        + squares.apply(perm.apply(6)).topRight + squares.apply(perm.apply(7)).topLeft != 10) {
      return false
    }
    // middle
    if (squares.apply(perm.apply(3)).bottomRight + squares.apply(perm.apply(4)).bottomLeft
        + squares.apply(perm.apply(7)).topRight + squares.apply(perm.apply(8)).topLeft != 10) {
      return false
    }
    // right
    if (squares.apply(perm.apply(4)).bottomRight + squares.apply(perm.apply(5)).bottomLeft
        + squares.apply(perm.apply(8)).topRight + squares.apply(perm.apply(9)).topLeft != 10) {
      return false
    }
    // bottom
    if (squares.apply(perm.apply(7)).bottomRight + squares.apply(perm.apply(8)).bottomLeft
        + squares.apply(perm.apply(10)).topRight + squares.apply(perm.apply(11)).topLeft != 10) {
      return false
    }

    true
  }

  private def checkYellowCorners(perm : List[Int]) : Boolean = {
    // top
    if (squares.apply(perm.apply(0)).topRight + squares.apply(perm.apply(1)).topLeft > 10) {
      return false
    }
    // top-left
    if (squares.apply(perm.apply(0)).bottomLeft + squares.apply(perm.apply(2)).topRight
        + squares.apply(perm.apply(3)).topLeft > 10) {
      return false
    }
    // top-right
    if (squares.apply(perm.apply(1)).bottomRight + squares.apply(perm.apply(4)).topRight
        + squares.apply(perm.apply(5)).topLeft > 10) {
      return false
    }
    // left
    if (squares.apply(perm.apply(2)).bottomLeft + squares.apply(perm.apply(6)).topLeft > 10) {
      return false
    }
    // right
    if (squares.apply(perm.apply(5)).bottomRight + squares.apply(perm.apply(9)).topRight > 10) {
      return false
    }
    // bottom-left
    if (squares.apply(perm.apply(6)).bottomRight + squares.apply(perm.apply(7)).bottomLeft
        + squares.apply(perm.apply(10)).topLeft > 10) {
      return false
    }
    // bottom-right
    if (squares.apply(perm.apply(8)).bottomRight + squares.apply(perm.apply(9)).bottomLeft
        + squares.apply(perm.apply(11)).topRight > 10) {
      return false
    }
    // bottom
    if (squares.apply(perm.apply(10)).bottomRight + squares.apply(perm.apply(11)).bottomLeft > 10) {
      return false
    }

    true
  }

  /*
  private def validatePermutation(perm : List[Int]) {
    if (checkGreenCorners(perm) && checkYellowCorners(perm)) {
      solutions +: perm
    }
  }

  //----------http://introcs.cs.princeton.edu/java/23recursion/PermutationsLex.java.html---------
  //---------converted-using-http://javatoscala.com/------------------------------------------------------
  private def perm(N: Int) {
    val a = Array.ofDim[Int](N)
    for (i <- 0 until N) {
      a(i) = i
    }
    validatePermutation(a.toList)
    while (hasNext(a)) {
      validatePermutation(a.toList)
    }
  }

  private def hasNext(a: Array[Int]): Boolean = {
    val N = a.length
    var k: Int = 0
    k = N - 2
    breakable {
      while (k >= 0) {
        if (a(k) < a(k + 1)) {
          break
        }
        k -= 1
      }
    }
    if (k == -1) {
      return false
    }
    var j = N - 1
    while (a(k) > a(j)) {
      j -= 1
    }
    swap(a, j, k)
    var r = N - 1
    var s = k + 1
    while (r > s) {
      swap(a, r, s)
      r -= 1
      s += 1
    }
    true
  }

  private def swap(a: Array[Int], i: Int, j: Int) {
    val temp = a(i)
    a(i) = a(j)
    a(j) = temp
  }
  //---------------------------------------------------------------------------------------------
  */
}
