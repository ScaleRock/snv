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

import com.github.scalerock.snv.math.*
import com.github.scalerock.snv.networking.Devices
import javafx.fxml.FXML
import javafx.scene.control.{Button, ChoiceDialog, ContextMenu, MenuItem}
import javafx.scene.input.{MouseButton, MouseEvent, ScrollEvent}
import javafx.scene.layout.{HBox, Pane}
import javafx.scene.shape.Line
import scala.collection.JavaConverters.seqAsJavaListConverter

import scala.compiletime.uninitialized
import scala.language.postfixOps

class MainTopologyController:
  @FXML private var TopHbox: HBox = uninitialized
  @FXML private var TopFilesButton: Button = uninitialized
  @FXML private var TopEditButton: Button = uninitialized
  @FXML private var CenterMainMenu: Pane = uninitialized

  private val constextMenu: ContextMenu = new ContextMenu()
  private val Delete: MenuItem = new MenuItem("Delete")
  private val Add: MenuItem = new MenuItem("Add")
  private val Notes: MenuItem = new MenuItem("Notes")



  private var Scale: Double = 1.0
  private var IsMiniMenuOpen: Boolean = false
  private var MouseOnMenu: (Int, Int) = uninitialized

  private def drawLines(): Unit =
    if CenterMainMenu == null then return
    CenterMainMenu.getChildren.removeAll(
      CenterMainMenu.getChildren.filtered(_.isInstanceOf[Line])
    )


    val x = CenterMainMenu.getWidth
    val y = CenterMainMenu.getHeight

    val ((rows, columns), (rowStep, colStep)) = calculateGrid(this.Scale, x, y)



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
  private def setUpClickEvent(): Unit =
    if CenterMainMenu == null then return

    CenterMainMenu.setOnMouseClicked((event: MouseEvent) => {
      if event.getButton == MouseButton.PRIMARY && event.getClickCount == 2 then
        Scale = 1.0
        drawLines()
      else if event.getButton == MouseButton.PRIMARY && IsMiniMenuOpen then
        IsMiniMenuOpen = false
        constextMenu.hide()

      else if event.getButton == MouseButton.SECONDARY && event.getClickCount == 1 then
        if !IsMiniMenuOpen then
          IsMiniMenuOpen = true
          constextMenu.show(CenterMainMenu, event.getScreenX, event.getScreenY)
        else
          IsMiniMenuOpen = false
          constextMenu.hide()

    })

  private def setupMouseDragListener(): Unit = CenterMainMenu.setOnMouseMoved(event => MouseOnMenu = (event.getX.toInt, event.getY.toInt))

  def deleteOnAction(): Unit = ???
  private def addOnAction(): Unit =
    val options = Devices.values.map(_.toString).toList
    if options.isEmpty then return

    val cssUrl = getClass.getResource("styles/style.css")
    if cssUrl == null then
      System.err.println("CSS not found!")
      return
    val css = cssUrl.toExternalForm

    val dialog = new ChoiceDialog[String](options.head, options.asJava)
    dialog.getDialogPane.getStylesheets.add(css)
    dialog.getDialogPane.getStyleClass.add("popup-dialog")
    dialog.setTitle("Select device type")
    dialog.setHeaderText("Select one of the options")
    dialog.setContentText("Device:")

    val result = dialog.showAndWait()
    if result.isPresent then
      println(result.get)

  def notesOnAction(): Unit = ???
  
  @FXML
  private def initialize(): Unit =
    constextMenu.getItems.addAll(Add, Notes, Delete)
    constextMenu.getStyleClass.add("mini-menu")
    
    Delete.setOnAction(_ => deleteOnAction())
    Add.setOnAction(_ => addOnAction())
    Notes.setOnAction(_ => notesOnAction())
    
    CenterMainMenu.widthProperty.addListener((_, _, _) => drawLines())
    CenterMainMenu.heightProperty.addListener((_, _, _) => drawLines())

    setUpMouseEvents()
    setUpClickEvent()
    
    
