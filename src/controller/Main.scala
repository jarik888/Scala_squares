package controller

import java.io.IOException

import service.TaskSolverService
import utils.SquaresParser

object Main {

  def main(args: Array[String]) {

      val sp = new SquaresParser

    if (args.length < 1 || args.apply(0).equals("-h") || args.apply(0).equals("--help")) {
      println("Fist program argument(\"args[0]\") must be path to input file")
    } else {
      try {

        val inputSquares = sp.readFromFile(args.apply(0))
        println("Please wait... It will take about 3 minutes.")
        val solution = new TaskSolverService(inputSquares).findSolution()
        println(sp.saveResult(inputSquares, solution))

      } catch {
        case io: IOException =>
          System.err.println("Txt file reading error!")
          io.printStackTrace()
        case ne: NumberFormatException =>
          System.err.println("Wrong input file format!")
          ne.printStackTrace()
        case e: Exception =>
          System.err.println("Program error: \n" + e)
          e.printStackTrace()
      }
    }

  }

}


