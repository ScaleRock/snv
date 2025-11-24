package com.github.scalerock.snv.math

// P(x, y) --(vec[x_1, y_1])-> P(x + x_1, y + y_1)
def vectorTranslation(point: (Int, Int), vec: (Int, Int)): (Int, Int) = (point._1 + vec._1, point._2 + vec._2)

def calculateGrid(scale: Double, x: Double, y: Double): ((Int, Int), (Int, Int)) =
  val rows: Int = Math.round(scale * 8).toInt
  val columns: Int = Math.round(scale * 15).toInt
  val rowStep = Math.round(y / rows).toInt
  val colStep = Math.round(x / columns).toInt
  ((rows, columns), (rowStep, colStep))


def sholudBePrintBasedOnVec(vec: (Int, Int),
                            scale: Double,
                            screan: (Int, Int),
                            camera: (Int, Int)): Option[(Int, Int)] =
  // The point grid is 10 times more accurate than calculateGrid
  var ((rows, columns), (rowStep, colStep)) = calculateGrid(scale, screan._1, screan._2)
  rows *= 10
  columns *= 10
  rowStep /= 10
  colStep /= 10

  if vec._1 >= camera._1 - columns / 2 &&
    vec._1 <= camera._1 + columns / 2 &&
    vec._2 >= camera._2 - rows / 2 &&
    vec._2 <= camera._2 + rows / 2 then
    Some(((vec._1 - camera._1) * colStep),
      ((vec._2 - camera._2) * rowStep))
  else
    None


def calculateVec(
                  screenX: Int,
                  screenY: Int,
                  scale: Double,
                  screen: (Int, Int),   // (widthPx, heightPx)
                  camera: (Int, Int)    // (worldX, worldY) - pozycja kamery (środek ekranu)
                ): Option[(Int, Int)] =
  val ((rows, columns), (rowStep, colStep)) = calculateGrid(scale, screen._1, screen._2)
  
  if rowStep <= 0 || colStep <= 0 || rows <= 0 || columns <= 0 then
    return None

  val (screenW, screenH) = screen
  
  val centerX = screenW / 2.0
  val centerY = screenH / 2.0
  
  val deltaCols = Math.round((screenX - centerX) / colStep).toInt
  val deltaRows = Math.round((screenY - centerY) / rowStep).toInt
  
  val worldX = camera._1 + deltaCols
  val worldY = camera._2 + deltaRows
  
  val halfW = columns / 2
  val halfH = rows / 2
  
  val visibleX = deltaCols >= -halfW && deltaCols <= halfW
  val visibleY = deltaRows >= -halfH && deltaRows <= halfH

  if visibleX && visibleY then Some(worldX, worldY) else None
