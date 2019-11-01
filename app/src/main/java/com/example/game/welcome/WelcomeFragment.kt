package com.example.game.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.game.R
import com.example.game.extensions.inflate
import com.example.game.model.Move
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment(){

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return container?.inflate(R.layout.fragment_welcome)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    btnStartX.setOnClickListener {
      navigateToGame(Move.X)
    }


    btnStartO.setOnClickListener {
      navigateToGame(Move.O)
    }
  }

  private fun navigateToGame(move: Move) {
    val direction = WelcomeFragmentDirections.actionWelcomeFragmentToGameFragment(move)
    findNavController().navigate(direction)
  }
}