package com.hiya.typeclass

/**
  * Ad-hoc polymorphism
  */
object v2 extends App {

  trait Adder[T] {
    def zero: T
    def add(a: T, b: T): T
  }

  implicit val intAdder: Adder[Int] = new Adder[Int] {
    override def zero: Int                = 0
    override def add(a: Int, b: Int): Int = a + b
  }

  intAdder.add(1, 2)

  def sum[T](numbers: List[T])(implicit adder: Adder[T]): T =
    numbers.fold(adder.zero)(adder.add)

  println(sum(List(1, 2)))

}
