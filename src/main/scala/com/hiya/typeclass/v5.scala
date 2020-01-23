package com.hiya.typeclass

/**
  * Syntactic sugar: context bound
  */
object v5 extends App {

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

  implicit def tupleAdder[X: Adder, Y: Adder]: Adder[(X, Y)] = new Adder[(X, Y)] {
    override def zero: (X, Y)                      = (Adder[X].zero, Adder[Y].zero)
    override def add(a: (X, Y), b: (X, Y)): (X, Y) = (Adder[X].add(a._1, b._1), Adder[Y].add(a._2, b._2))
  }

  Adder[Int].add(1, 2)

  def sum[T: Adder](numbers: List[T]): T =
    numbers.fold(Adder[T].zero)(Adder[T].add)

  println(sum(List(1, 2)))           //  3
  println(sum(List((1, 2), (2, 3)))) // (3, 5)

}
