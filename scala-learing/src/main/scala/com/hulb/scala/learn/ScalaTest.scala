package com.hulb.scala.learn

import scala.io.Source

object ScalaTest {
  def main(args: Array[String]): Unit = {
    println(1)

    /**
      * 符号字面量
      */
    println('ddd)

    val source = Source.fromFile("/Users/hulb/data/removedata")
    val param = source.mkString
    println(param.length)

  }

}


