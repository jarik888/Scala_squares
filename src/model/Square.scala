package model

class Square(val topLeft : Int, val topRight : Int, val bottomLeft : Int, val bottomRight : Int) {

  override def toString: String = {
    "%s %s %s %s".format(topLeft, topRight, bottomLeft, bottomRight)
  }

}