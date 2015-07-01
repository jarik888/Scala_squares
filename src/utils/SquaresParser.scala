package utils

import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.Date

import model.Square

import scala.io.Source

class SquaresParser {

  def readFromFile(fileName : String) : List[Square] = {

    (for (line <- Source.fromFile(fileName).getLines()) yield {
      val corners = line.split(" ")
      new Square(corners.apply(0).toInt, corners.apply(1).toInt, corners.apply(2).toInt, corners.apply(3).toInt)
    }).toList

  }

  def saveResult(inputSquares : List[Square], solution : List[List[Square]]) : String = {

    var output = ""

    val dataFormat = new SimpleDateFormat("ddMMyy-hhmmss.SSS")
    val currentTimestamp = dataFormat.format(new Date())

    val writer = new PrintWriter( "squares-result-" + currentTimestamp + ".txt", "UTF-8")
    writer.println("---Input--")

    for (s <- inputSquares) {
      writer.println(s.toString())
    }

    writer.println("\n---Output---")

    if (solution.length < 1) {
      output += "No result"
      writer.println("No result")
    }

    for (squares <- solution) {
      for (s <- squares) {
        output += s.toString() + "\n"
        writer.println(s.toString())
      }
      output += "\n"
      writer.println()
    }

    writer.close()
    output
  }

}
