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
*/

package com.github.scalerock.snv

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

class Runner extends Application:
  override def start(stage: Stage): Unit =
    val fxmlUrl = getClass.getResource("gui/MainTopology.fxml")
    if fxmlUrl == null then
      System.err.println("FXML not found!")
      return

    val root: Parent = FXMLLoader.load(fxmlUrl)

    val cssUrl = getClass.getResource("gui/styles/style.css")
    if cssUrl == null then
      System.err.println("CSS not found!")
      return
    val css = cssUrl.toExternalForm
    val scene = new Scene(root)
    scene.getStylesheets.add(css)
    stage.setScene(scene)

    stage.setTitle("Simple Network View")
    stage.setResizable(true)
    stage.show()


object Main:
  def main(args: Array[String]): Unit =
    Application.launch(classOf[Runner], args*)
