package week6

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author fede
 */
@RunWith(classOf[JUnitRunner])
class QueriesWithForTest extends FunSuite {

  import QueriesWithFor.Book
  import OtherCollections.isPrime

  trait TestSets {
    val books: Set[Book] = Set(
      Book(title = "Structure and Interpretation of Computer Programs", authors = List("Abelson, Harald", "Sussman, Gerald J.")),
      Book(title = "Introduction to Functional Programming", authors = List("Bird, Richard", "Wadler, Phil")),
      Book(title = "Effective Java", authors = List("Bloch, Joshua")),
      Book(title = "Effective Java 2nd Edition", authors = List("Bloch, Joshua")),
      Book(title = "Java Puzzlers", authors = List("Bloch, Joshua", "Gafter, Neal")),
      Book(title = "Programming in Scala", authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill")))
  }

  test("find the titles of books whose author’s name is 'Bird'") {
    new TestSets {
      val res = for (b <- books; a <- b.authors if a.startsWith("Bird")) yield b.title
      assert(res.size === 1)
      assert(res.head === "Introduction to Functional Programming")
    }
  }

  test("find the titles of books whose author’s name is 'Bird' using high order functions") {
    new TestSets {
      val res1 = for (b <- books; a <- b.authors if a.startsWith("Bird")) yield b.title
      val mySolution = books.filter(book => book.authors.exists(author => author.startsWith("Bird"))).map(book => book.title)
      assert(res1.size === 1)
      assert(res1.head === "Introduction to Functional Programming")
    }
  }

  test("find the titles of books which have 'Program' in the title") {
    new TestSets {
      val res = for (b <- books; if b.title.contains("Program")) yield b.title
      assert(res.size === 3)
    }
  }

  test("find the names of all authors who have written at least two books present in the database") {
    new TestSets {
      val res = for {
        b1 <- books
        b2 <- books
        if b1 != b2
        a1 <- b1.authors
        a2 <- b2.authors
        if a1 == a2
      } yield a1

      assert(res.size === 1)
      assert(res.head === "Bloch, Joshua")
    }
  }

  test("with filter") {
    val res1 = for {
      i <- 1 until 10
      j <- 1 until i
      if isPrime(i + j)
    } yield (i, j)

    val res2 = (1 until 10) flatMap (i => (1 until i).withFilter(j => isPrime(i + j)).map(j => (i, j)))

    println(res1)
    assert(res1.equals(res2))
  }

}
