package com.hiya.typeclass

import cats.Monoid
import cats.instances.int._
import cats.instances.string._
import cats.derived.auto.monoid._

/**
  * Automatic type class derivation with kittens
  */
object v9 extends App {

  case class MyType(num: Int, str: String)

  println(Monoid.combineAll(List(MyType(0, "a"), MyType(1, "b"))))

}
