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

package com.github.scalerock.snv.gui

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.input.ScrollEvent
import javafx.scene.layout.{HBox, Pane}
import javafx.scene.shape.Line

import scala.language.postfixOps

class MainTopologyController:
  @FXML private var TopHbox: HBox = _
  @FXML private var TopFilesButton: Button = _
  @FXML private var TopEditButton: Button = _
  @FXML private var CenterMainMenu: Pane = _

  private var Scale: Double = 1.0

  private def drawLines(): Unit =
    if CenterMainMenu == null then return
    CenterMainMenu.getChildren.removeAll(
      CenterMainMenu.getChildren.filtered(_.isInstanceOf[Line])
    )


    val x = CenterMainMenu.getWidth
    val y = CenterMainMenu.getHeight

    val rows: Int = Math.round(this.Scale * 8).toInt
    val columns: Int = Math.round(this.Scale * 15).toInt

    val rowStep = y / rows
    val colStep = x / columns

    for i <- 0 to rows do
      val line = new Line()
      val pos = i * rowStep
      line.setStartY(pos)
      line.setEndY(pos)
      line.setStartX(0)
      line.setEndX(x)
      line.getStyleClass.add("grid-line")
      CenterMainMenu.getChildren.add(line)
    for i <- 0 to columns do
      val line = new Line()
      val pos = i * colStep
      line.setStartX(pos)
      line.setEndX(pos)
      line.setStartY(0)
      line.setEndY(y)
      line.getStyleClass.add("grid-line")
      CenterMainMenu.getChildren.add(line)


  private def setUpMouseEvents(): Unit =
    if CenterMainMenu == null then return

    CenterMainMenu.setOnScroll((event: ScrollEvent) => {
      val deltaY = event.getDeltaY
      Scale = (Scale - deltaY / 80).max(0.5).min(10.0)
      drawLines()
    })


  @FXML
  private def initialize(): Unit = {
    CenterMainMenu.widthProperty.addListener((_, _, _) => drawLines())
    CenterMainMenu.heightProperty.addListener((_, _, _) => drawLines())

    setUpMouseEvents()

  }
