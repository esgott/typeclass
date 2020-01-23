package com.hiya.typeclass

/**
  * Implicits
  *  See more: http://www.lihaoyi.com/post/ImplicitDesignPatternsinScala.html
  */
object v0 extends App {

  // ----------------------------------------
  // implicit parameter
  implicit val a: Int = 5

  def f1(p1: Int)(implicit p2: Int): Int = p2

  println(f1(2))

  implicit def f2(implicit p1: Int): String = p1.toString

  println(f2)

  // ----------------------------------------
  // implicit conversion :(
  implicit def booleanToString(b: Boolean): String = b.toString

  def f3(str: String): String = str

  println(f3(false))

  // ----------------------------------------
  // implicit class - monkey patching
  implicit class IntSyntax(n: Int) {
    def twice: Int = n * 2
  }

  println(3.twice)

  // ----------------------------------------
  // implicitly
  println(implicitly[Int])

  // ----------------------------------------
  // implicit resolution order:
  //  1) lexical scope
  //    - shadowing
  //    - overload resolution
  //  2) implicit scope
  //    - companion object of F
  //    - companion object of T in case of F[T]
  //    - companion objects of its base classes (both for F and T)

}
