package com.github.scalerock.snv.math

class MathTest extends munit.FunSuite {

  test("center maps to (0,0)") {
    val vec: (Int, Int) = (0, 0)
    val scale: Double = 1.0
    val camera: (Int, Int) = (0, 0)
    val screan: (Int, Int) = (1920, 1080)

    val center = sholudBePrintBasedOnVec(vec, scale, screan, camera)
    assertEquals(center, Some((0, 0)))
  }

  test("point on right edge is included and mapped (scale = 1.0)") {
    val vec: (Int, Int) = (75, 0) // columns/2 for scale=1 -> 15*10/2 = 75
    val scale: Double = 1.0
    val camera: (Int, Int) = (0, 0)
    val screan: (Int, Int) = (1920, 1080)
    
    val expected = Some((900, 0))
    val got = sholudBePrintBasedOnVec(vec, scale, screan, camera)
    assertEquals(got, expected)
  }

  test("point just outside right edge returns None") {
    val vec: (Int, Int) = (76, 0)
    val scale: Double = 1.0
    val camera: (Int, Int) = (0, 0)
    val screan: (Int, Int) = (1920, 1080)

    val got = sholudBePrintBasedOnVec(vec, scale, screan, camera)
    assertEquals(got, None)
  }

  test("scale affects step sizes and mapping (scale = 2.0)") {
    val vec: (Int, Int) = (100, 0)
    val scale: Double = 2.0
    val camera: (Int, Int) = (0, 0)
    val screan: (Int, Int) = (1920, 1080)

    //scale=2: colStep = round(1920/30)=64 -> /10 = 6
    val expected = Some((600, 0))
    val got = sholudBePrintBasedOnVec(vec, scale, screan, camera)
    assertEquals(got, expected)
  }

}
