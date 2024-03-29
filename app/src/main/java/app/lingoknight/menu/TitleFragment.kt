//Michael Carter
// 1910059

package app.lingoknight.menu

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import app.lingoknight.R
import app.lingoknight.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            if (Configuration.ORIENTATION_LANDSCAPE == resources.configuration.orientation) {
                DataBindingUtil.inflate<FragmentTitleBinding>(
                    inflater,
                    R.layout.fragment_title, container, false
                )

            } else {
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_title, container, false
                )

            }


        binding.btnPlay.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_titleFragment_to_choosePlayerFragment)
        }
        binding.btnPractice.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_titleFragment_to_practiceMainFragment)
        }

        return binding.root
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}
