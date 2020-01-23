package com.hiya.typeclass

/**
  * Syntactic sugar: apply method
  */
object v4 extends App {

  trait Adder[T] {
    def zero: T
    def add(a: T, b: T): T
  }

  object Adder {
    def apply[T](implicit instance: Adder[T]): Adder[T] = instance
  }

  implicit val intAdder: Adder[Int] = new Adder[Int] {
    override def zero: Int                = 0
    override def add(a: Int, b: Int): Int = a + b
  }

  implicit def tupleAdder[X, Y](implicit xAdder: Adder[X], yAdder: Adder[Y]): Adder[(X, Y)] = new Adder[(X, Y)] {
    override def zero: (X, Y)                      = (xAdder.zero, yAdder.zero)
    override def add(a: (X, Y), b: (X, Y)): (X, Y) = (xAdder.add(a._1, b._1), yAdder.add(a._2, b._2))
  }

  Adder[Int].add(1, 2)

  def sum[T](numbers: List[T])(implicit adder: Adder[T]): T =
    numbers.fold(adder.zero)(adder.add)

  println(sum(List(1, 2)))           //  3
  println(sum(List((1, 2), (2, 3)))) // (3, 5)

}
