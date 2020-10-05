package app.lingoknight.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.lingoknight.R
import app.lingoknight.database.Player
import app.lingoknight.database.Question
import app.lingoknight.database.Word
import app.lingoknight.databinding.FragmentChoosePlayerBinding
import app.lingoknight.databinding.FragmentGameBinding
import app.lingoknight.databinding.FragmentPracticeDetailBinding


class GameFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentGameBinding.inflate(inflater)
        // Giving the binding access to the GameViewModel
        binding.gameViewModel = viewModel
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this


        // Observes changes to player
        viewModel.player.observe(viewLifecycleOwner, Observer {
            viewBinding(binding)
        })
        // Observes changes to currentQuestion
        viewModel.currentQuestion.observe(viewLifecycleOwner, {
            viewModel.randomAnswers()
            viewBinding(binding)
        })

        binding.nextBtn.setOnClickListener {
            checkAnswer(binding)
            viewModel.checkAnswer()
        }

        return binding.root
    }

    private fun viewBinding(binding: FragmentGameBinding){
        binding.gameQuestion.text = viewModel.currentQuestion.value?.text
        binding.radioBtnAnswerOne.text = viewModel.answerList[0]
        binding.radioBtnAnswerTwo.text = viewModel.answerList[1]
        binding.radioBtnAnswerThree.text = viewModel.answerList[2]
        binding.radioBtnAnswerFour.text = viewModel.answerList[3]
        binding.wordImageGame.setImageResource(
            when (viewModel.currentQuestion.value?.wordId) {
                "king" -> R.drawable.king
                "knight" -> R.drawable.knight
                "princess" -> R.drawable.princess
                "witch" -> R.drawable.witch
                "fox" -> R.drawable.fox
                "purple" -> R.drawable.purple_monster
                "blue" -> R.drawable.blue_bird
                "dragon" -> R.drawable.dragon
                "monster" -> R.drawable.green_monster
                "green" -> R.drawable.green_troll
                "tree" -> R.drawable.tree
                "yellow" -> R.drawable.yellow_monster
                "horse" -> R.drawable.horse
                "goat" -> R.drawable.goat
                else -> R.drawable.oops_comic
            }
        )
    }


    fun checkAnswer(binding: FragmentGameBinding) {
        val radioId = binding.answerGroup.checkedRadioButtonId
        // Do nothing if nothing is checked (id == -1)
        if (-1 != radioId) {
            var answerIndex = 0
            when (radioId) {
                R.id.radioBtn_answerOne -> answerIndex = 0
                R.id.radioBtn_answerTwo -> answerIndex = 1
                R.id.radioBtn_answerThree -> answerIndex = 2
                R.id.radioBtn_answerFour -> answerIndex = 3
            }

            Log.d("testing", "answer Index is: $answerIndex  current Question correct answer: ${viewModel.currentQuestion.value?.answers?.get(0)}" +
                    " List answer : ${viewModel.answerList[answerIndex]} radioID is: $radioId")

            if(viewModel.currentQuestion.value?.answers?.get(0) == viewModel.answerList[answerIndex]){
                Log.d("testing", "Congratz you are correct!")
            }else{
                Log.d("testing", "to bad! you are not right!")
            }
        }

    }




}
