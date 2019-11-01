package com.example.game.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.view.forEachIndexed
import androidx.gridlayout.widget.GridLayout
import com.example.game.extensions.px


class DividerGridLayout @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : GridLayout(context, attrs, defStyle) {

  private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

  init {
    with(paint) {
      color = Color.BLACK
      style = Paint.Style.STROKE
      strokeWidth = 5.px
    }
  }

  override fun dispatchDraw(canvas: Canvas) {
    forEachIndexed { i, view ->
      val left = view.left.toFloat()
      val top = view.top.toFloat()
      val bottom = view.bottom.toFloat()
      val right = view.right.toFloat()

      if (i % columnCount < columnCount - 1) {
        canvas.drawLine(right, top, right, bottom, paint)
      }

      if (i / rowCount == rowCount - 1) return@forEachIndexed
      canvas.drawLine(left, bottom, right, bottom, paint)
    }
    super.dispatchDraw(canvas)
  }
}