package com.github.scalerock.snv.networking

class DevicesImgTest extends munit.FunSuite {
  test("null case") {
    assertEquals(getIconResourcePath(null), null)
  }

  test("all images exists") {
    for device <- Devices.values do
      val imagePath = getIconResourcePath(device)

      assertEquals(
        imagePath,
        s"/com/github/scalerock/snv/icons/Devices/${device.toString}.png"
      )
      
      val resource = getClass.getResource(imagePath)
      assert(resource != null, s"Resource not found: $imagePath")
  }
}
