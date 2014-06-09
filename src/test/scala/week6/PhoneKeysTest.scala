package week6

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author fede
 */
@RunWith(classOf[JUnitRunner])
class PhoneKeysTest extends FunSuite {

  import PhoneKeys._


  trait TestSets {
    val phoneNumber = "7225247386"
  }

  /**
   * The phone number “7225247386” should have the mnemonic "Scala is fun" as one element of the set of solution phrases.
   */
  test("translate") {
    new TestSets {
      val res = translate(phoneNumber)
      assert(res.contains("Scala is fun"))
    }
  }

  test("encode") {
    new TestSets {
      val res = encode(phoneNumber)
      assert(res.contains(List("Scala", "is", "fun")))
    }
  }

  test("wordCode") {
    assert(wordCode("Java") === "5282")
  }

}
