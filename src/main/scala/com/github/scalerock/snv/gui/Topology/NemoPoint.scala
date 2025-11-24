/*
MIT License

Copyright (c) 2025 ScaleRock

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
* */

package com.github.scalerock.snv.gui.Topology

import com.github.scalerock.snv.math.*
import com.github.scalerock.snv.networking.{Device, Devices, getIconResourcePath}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.Pane

import scala.collection.mutable.ArrayBuffer

object NemoPoint {
    // The positions of any object are described as a vector from the point P(0;0)

    private var cameraPosition: (Int, Int) = (0, 0);
    private val objectList = new ArrayBuffer[Device]()

    private def getDeviceType(dev: String): Devices = Devices.values.find(_.toString == dev).orNull

    def addDevice(dev: String, x: Int, y: Int, scale: Double, menu: Pane): Unit =
        val dev_t: Devices = getDeviceType(dev)
        if dev_t == null then return

        val path = getIconResourcePath(dev_t)

        val imageTry = try
            val img = new Image(path)
            img
        catch
            case e: Throwable =>
                e.printStackTrace()
                null

        val icon = new ImageView(imageTry)
        icon.setPreserveRatio(true)
        icon.setCache(false)
        val vec = calculateVec(x, y, scale, (menu.getWidth.toInt, menu.getHeight.toInt), cameraPosition)
        if vec.isEmpty then
            println("vec error")
            return

        val device = new Device(dev_t, vec.get)
        objectList += device

        val ((_, _), (stepX, stepY)) = calculateGrid(scale, menu.getWidth.toInt, menu.getHeight.toInt)

        val gridX =
            if stepX > 0 then (x / stepX) * stepX else x
        val gridY =
            if stepY > 0 then (y /stepY) * stepY else y

        icon.setLayoutX(gridX - 25)
        icon.setLayoutY(gridY - 25)
        val appliedScale = 1.0 / scale

        icon.setFitWidth(50 * appliedScale)
        icon.setFitHeight(50 * appliedScale)

        menu.getChildren.add(icon)

}