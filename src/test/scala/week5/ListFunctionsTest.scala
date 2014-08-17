package week5

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ListFunctionsTest extends FunSuite {

  import ListFunctions._

  test("init") {
    val result: List[Int] = init(List(1, 2, 3, 4, 5, 6))
    print(result)
    assert(result.length == 5)
    assert(result.equals(List(1, 2, 3, 4, 5)))
  }

  test("init on one item") {
    val result: List[Int] = init(List(1))
    print(result)
    assert(result.length == 0)
  }

  test("merge sort") {
    println(mergeSort(List(2, -4, 9, 7, 1)))
    println(mergeSort(List("banana", "pineapple", "mango", "apple")))
  }

  test("higher order") {
    val list: List[Int] = List(1, 2, 3, 4, 5)
    val map: List[Int] = list.map((x: Int) => x * 2)
    assert(map.equals(List(2, 4, 6, 8, 10)))
  }

  test("higher order: square list") {
    val list: List[Int] = List(1, 2, 3, 4, 5)
    assert(List(1, 4, 9, 16, 25).equals(squareList(list)))
  }

  test("higher order: square list with map") {
    val list: List[Int] = List(1, 2, 3, 4, 5)
    val map: List[Int] = list.map((x: Int) => x * x)
    assert(List(1, 4, 9, 16, 25).equals(map))
  }

  test("higher order: other operations") {
    val list: List[Int] = List(8, 1, -4, 7, 2, 5)

    println("filter   : " + (list filter (x => x > 0)))
    println("filterNot: " + (list filterNot (x => x > 0)))
    println("partition: " + (list partition (x => x > 0)))

    println("takeWhile: " + (list takeWhile (x => x > 0)))
    println("dropWhile: " + (list dropWhile (x => x > 0)))
    println("span     : " + (list span (x => x > 0)))
  }

  test("higher order: pack") {
    val list: List[String] = List("a", "a", "a", "b", "c", "c", "a")
    println(pack(list))
  }

  test("reducing") {
    val list: List[Int] = List(1, 2, 3, 4, 5)
    val result1: Int = list reduceLeft ((x: Int, y: Int) => x + y)
    val result2: Int = list reduceLeft (_ + _)
    println(result1)
    assert(result1 === 15)
    assert(result2 === 15)
  }

  test("folding") {
    val list: List[Int] = List(1, 2, 3, 4, 5)
    val acc: Int = 1
    val result: Int = list.foldLeft(acc)((x: Int, y: Int) => x + y)

    println(result)
    assert(result === 16) // if finds an empty list, fold left uses the accumulator "acc"
  }


}
