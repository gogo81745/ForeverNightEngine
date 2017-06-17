package moe.gogo.game.behavior

import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.properties.Gen
import io.kotlintest.specs.StringSpec
import moe.gogo.test.equal
import java.lang.Math.pow
import java.lang.Math.sqrt

class VectorTest : StringSpec() {

    init {
        "vector should equal"{
            (1..10).forEach {
                val (x1, y1) = generateXY()
                Vector(x1, y1) shouldBe Vector(x1, y1)
            }
        }
        "vector should not equal"{
            (1..10).forEach {
                val (x1, y1) = generateXY()
                val (x2, y2) = generateXY()
                Vector(x1, y1) shouldNotBe Vector(x2, y2)
            }
        }
        "vector should equal although have a little difference"{
            (1..10).forEach {
                val (x1, y1) = generateXY()
                Vector(x1 - 0.02, y1 + 0.02) shouldBe Vector(x1 + 0.02, y1 - 0.02)
            }
        }
        "plus vector"{
            (1..10).forEach {
                val (x1, y1) = generateXY()
                val (x2, y2) = generateXY()
                Vector(x1, y1) + Vector(x2, y2) shouldBe Vector(x1 + x2, y1 + y2)
            }
        }
        "minus vector"{
            (1..10).forEach {
                val (x1, y1) = generateXY()
                val (x2, y2) = generateXY()
                Vector(x1, y1) - Vector(x2, y2) shouldBe Vector(x1 - x2, y1 - y2)
            }
        }
        "times vector"{
            (1..10).forEach {
                val (x1, y1) = generateXY()
                val times = Gen.int().generate()
                Vector(x1, y1) * times shouldBe Vector(x1 * times, y1 * times)
            }
        }
        "vector length"{
            (1..10).forEach {
                val (x1, y1) = generateXY()
                Vector(x1, y1).length() should equal(sqrt(pow(x1.toDouble(), 2.0) + pow(y1.toDouble(), 2.0)).toFloat())
            }
        }
        "to point"{
            (1..10).forEach {
                val (x1, y1) = generateXY()
                Vector(x1, y1).toPoint() shouldBe Point(x1, y1)
            }
        }
    }


    fun generateXY() = Pair(Gen.float().generate(), Gen.float().generate())
}
