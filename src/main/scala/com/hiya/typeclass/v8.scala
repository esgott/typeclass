package com.hiya.typeclass

import cats.Monoid
import cats.instances.int._
import cats.instances.string._

/**
  * Reuse Monoid for Int and String
  */
object v8 extends App {

  case class MyType(num: Int, str: String)

  implicit val myTypeMonoid: Monoid[MyType] = new Monoid[MyType] {

    override def empty: MyType = MyType(Monoid[Int].empty, Monoid[String].empty)

    override def combine(x: MyType, y: MyType): MyType =
      MyType(
        Monoid[Int].combine(x.num, y.num),
        Monoid[String].combine(x.str, y.str)
      )

  }

  println(Monoid.combineAll(List(MyType(0, "a"), MyType(1, "b"))))

}
