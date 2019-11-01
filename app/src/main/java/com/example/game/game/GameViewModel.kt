package com.example.game.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.game.model.Move
import kotlin.math.abs

class GameViewModel: ViewModel() {

  private val _result = MutableLiveData<GameFragment.Result>()
  val result = _result

  private val _currentMove = MutableLiveData<Move>()
  val currentMove = _currentMove

  private lateinit var rows: IntArray
  private lateinit var columns: IntArray
  private var dc1: Int = 0
  private var dc2: Int = 0
  private var numberOfColumns = 0
  private var numberOfRows = 0

  private var commonCount = 0
  private var totalCells = 0

  fun setupGame(numberOfRows: Int, numberOfColumns: Int, move: Move) {
    _currentMove.value = move

    this.numberOfColumns = numberOfColumns
    this.numberOfRows = numberOfRows

    totalCells = numberOfColumns * numberOfRows

    rows = IntArray(numberOfRows)
    columns = IntArray(numberOfColumns)
  }

  fun checkResult(index: Int) {

    val column = index % numberOfColumns
    val row = index / numberOfRows
    val result = checkForResult(row, column, currentMove.value!!)

    currentMove.value = if (currentMove.value == Move.O) Move.X else Move.O
    _result.value = result
  }

  private fun checkForResult(row: Int, column: Int, move: Move): GameFragment.Result {
    ++commonCount

    val result = if (move == Move.X) GameFragment.Result.X else GameFragment.Result.O
    rows[row] += result.value
    columns[column] += result.value

    if (row == column) {
      dc1 += result.value
    }
    if (column == numberOfColumns - row - 1) {
      dc2 += result.value
    }
    if (abs(rows[row]) == numberOfColumns
      || abs(columns[column]) == numberOfColumns
      || abs(dc1) == numberOfColumns
      || abs(dc2) == numberOfColumns
    ) {
      return result
    }

    if (commonCount == totalCells) {
      return GameFragment.Result.DRAW
    }

    return GameFragment.Result.UNDEFINED
  }
}