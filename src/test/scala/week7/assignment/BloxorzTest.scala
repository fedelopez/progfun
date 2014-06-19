package week7.assignment

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BloxorzTest extends FunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) {
        case (block, move) => move match {
          case Left => block.left
          case Right => block.right
          case Up => block.up
          case Down => block.down
        }
      }
  }

  trait Level1 extends SolutionChecker {
    /* terrain for level 1*/

    val level =
      """ooo-------
        |oSoooo----
        |ooooooooo-
        |-ooooooooo
        |-----ooToo
        |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
  }

  test("terrain function level 1") {
    new Level1 {
      assert(terrain(Pos(0, 0)), "0,0")
      assert(terrain(Pos(0, 1)), "0,1")
      assert(terrain(Pos(0, 2)), "0,1")
      assert(!terrain(Pos(0, 3)), "0,3")
      assert(!terrain(Pos(0, 4)), "0,4")

      assert(terrain(Pos(1, 5)), "1,5")

      assert(!terrain(Pos(3, 0)), "3,0")

      assert(terrain(Pos(4, 9)), "4,9")
      assert(!terrain(Pos(4, 10)), "4,10")
      assert(!terrain(Pos(4, 11)), "4,11")
    }
  }

  test("terrain function level 1: negative") {
    new Level1 {
      assert(!terrain(Pos(-1, 0)), "-1, 0")
      assert(!terrain(Pos(0, -1)), "0, -1")
    }
  }

  test("findChar level 1") {
    new Level1 {
      assert(startPos == Pos(1, 1))
      assert(goal == Pos(4, 7))
    }
  }

  test("isStanding") {
    new Level1 {
      val block = new Block(Pos(1, 1), Pos(1, 1))
      assert(block.isStanding)
    }
  }

  test("isStanding: vertically laid down") {
    new Level1 {
      val block = new Block(Pos(3, 2), Pos(4, 2))
      assert(!block.isStanding)
    }
  }

  test("isStanding: horizontally laid down") {
    new Level1 {
      val block = new Block(Pos(1, 1), Pos(1, 2))
      assert(!block.isStanding)
    }
  }

  test("isLegal: block is entirely inside the terrain") {
    new Level1 {
      assert(new Block(Pos(1, 3), Pos(1, 4)).isLegal)
      assert(new Block(Pos(1, 4), Pos(1, 5)).isLegal)
      assert(!new Block(Pos(1, 5), Pos(1, 6)).isLegal)

      assert(!new Block(Pos(3, 0), Pos(3, 1)).isLegal)
      assert(!new Block(Pos(3, 0), Pos(3, 0)).isLegal)
      assert(new Block(Pos(3, 1), Pos(3, 2)).isLegal)
    }
  }

  test("startBlock: block at the start position of the game") {
    new Level1 {
      assert(startBlock.b1 == startPos)
      assert(startBlock.b2 == startPos)
    }
  }

  test("neighbors: start block") {
    new Level1 {
      val actual: List[(Block, Move)] = startBlock.neighbors
      assert(actual.length == 4)
      assert(actual.contains((new Block(Pos(-1, 1), Pos(0, 1)), Up)))
      assert(actual.contains((new Block(Pos(2, 1), Pos(3, 1)), Down)))
      assert(actual.contains((new Block(Pos(1, -1), Pos(1, 0)), Left)))
      assert(actual.contains((new Block(Pos(1, 2), Pos(1, 3)), Right)))
    }
  }

  test("neighbors: bounded block") {
    new Level1 {
      val actual: List[(Block, Move)] = new Block(Pos(2, 2), Pos(2, 2)).neighbors
      assert(actual.length == 4)
      assert(actual.contains((new Block(Pos(0, 2), Pos(1, 2)), Up)))
      assert(actual.contains((new Block(Pos(3, 2), Pos(4, 2)), Down)))
      assert(actual.contains((new Block(Pos(2, 0), Pos(2, 1)), Left)))
      assert(actual.contains((new Block(Pos(2, 3), Pos(2, 4)), Right)))
    }
  }

  test("neighbors: laid down block") {
    new Level1 {
      val actual: List[(Block, Move)] = new Block(Pos(2, 2), Pos(2, 3)).neighbors
      assert(actual.length == 4)
      assert(actual.contains((new Block(Pos(1, 2), Pos(1, 3)), Up)))
      assert(actual.contains((new Block(Pos(3, 2), Pos(3, 3)), Down)))
      assert(actual.contains((new Block(Pos(2, 1), Pos(2, 1)), Left)))
      assert(actual.contains((new Block(Pos(2, 4), Pos(2, 4)), Right)))
    }
  }

  test("legal neighbors: start block") {
    new Level1 {
      val actual: List[(Block, Move)] = startBlock.legalNeighbors
      assert(actual.length == 2)
      assert(actual.contains((new Block(Pos(2, 1), Pos(3, 1)), Down)))
      assert(actual.contains((new Block(Pos(1, 2), Pos(1, 3)), Right)))
    }
  }

  test("legal neighbors: bounded block becomes unbounded") {
    new Level1 {
      val actual: List[(Block, Move)] = new Block(Pos(2, 2), Pos(2, 2)).legalNeighbors
      assert(actual.length == 3)
      assert(actual.contains((new Block(Pos(0, 2), Pos(1, 2)), Up)))
      assert(actual.contains((new Block(Pos(2, 0), Pos(2, 1)), Left)))
      assert(actual.contains((new Block(Pos(2, 3), Pos(2, 4)), Right)))
    }
  }

  test("done") {
    new Level1 {
      assert(!done(startBlock))
      assert(!done(new Block(Pos(1, 3), Pos(1, 4))))
      assert(!done(new Block(Pos(1, 4), Pos(1, 5))))

      assert(done(new Block(goal, goal)))
    }
  }

  test("neighborsWithHistory: start block") {
    new Level1 {
      val actual: Stream[(Block, List[Move])] = neighborsWithHistory(startBlock, List(Left, Up))

      assert(actual.length === 2)
      assert(actual.contains(Block(Pos(1, 2), Pos(1, 3)), List(Right, Left, Up)))
      assert(actual.contains(Block(Pos(2, 1), Pos(3, 1)), List(Down, Left, Up)))
    }
  }

  test("optimal solution for level 1") {
    new Level1 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 1") {
    new Level1 {
      assert(solution.length == optsolution.length)
    }
  }
}
