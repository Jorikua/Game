package com.example.game.game

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.game.BuildConfig
import com.example.game.R
import com.example.game.extensions.inflate
import com.example.game.model.Move
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.fragment_game.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment : Fragment() {

  private val args: GameFragmentArgs by navArgs()
  private val viewModel by viewModel<GameViewModel>()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return container?.inflate(R.layout.fragment_game)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    banner.loadAd(AdRequest.Builder().build())

    val move = args.move
    viewModel.setupGame(gameGrid.rowCount, gameGrid.columnCount, move)

    gameGrid.forEach {
      it.setOnClickListener(cellClickListener)
    }

    viewModel.result.observe(this) {
      when (it) {
        Result.X,
        Result.O,
        Result.DRAW -> showDialog(it)
        Result.UNDEFINED -> {
          //game continues
        }
      }
    }

    viewModel.currentMove.observe(this) {
      setTurnText(it)
    }
  }

  private val cellClickListener = View.OnClickListener {
    if (it !is ImageView) return@OnClickListener
    if (it.drawable != null) return@OnClickListener

    val image = if (viewModel.currentMove.value == Move.O) BuildConfig.O_URL else BuildConfig.X_URL
    val placeholder =
      if (viewModel.currentMove.value == Move.O) R.drawable.ic_o_mark else R.drawable.ic_x_mark

    Glide.with(it)
      .load(image)
      .placeholder(placeholder)
      .into(it)

    val index = gameGrid.indexOfChild(it)
    viewModel.checkResult(index)
  }

  private fun showDialog(result: Result) {
    val title = when (result) {
      Result.O -> R.string.o_wins
      Result.X -> R.string.x_wins
      else -> R.string.a_draw
    }

    AlertDialog.Builder(requireContext())
      .setPositiveButton(android.R.string.ok) { _, _ -> findNavController().popBackStack() }
      .setNegativeButton(R.string.share) { _, _ ->
        findNavController().popBackStack()
        share()
      }
      .setCancelable(false)
      .setTitle(title)
      .show()
  }

  private fun setTurnText(move: Move) {
    val text =
      if (move == Move.O) getString(R.string.o_turn) else getString(R.string.x_turn)
    tvTurn.text = text
  }

  private fun share() {
    val sendIntent: Intent = Intent().apply {
      action = Intent.ACTION_SEND
      putExtra(Intent.EXTRA_TEXT, getString(R.string.like_game))
      type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
  }

  enum class Result(val value: Int) {
    DRAW(0),
    X(1),
    O(-1),
    UNDEFINED(-2)
  }
}