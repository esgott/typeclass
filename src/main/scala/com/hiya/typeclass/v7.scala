package com.hiya.typeclass

import cats.Monoid

/**
  * Monoid in cats
  */
object v7 extends App {

  case class MyType(num: Int, str: String)

  implicit val myTypeMonoid: Monoid[MyType] = new Monoid[MyType] {
    override def empty: MyType                         = MyType(0, "")
    override def combine(x: MyType, y: MyType): MyType = MyType(x.num + y.num, x.str + y.str)
  }

  println(Monoid.combineAll(List(MyType(0, "a"), MyType(1, "b"))))

}
