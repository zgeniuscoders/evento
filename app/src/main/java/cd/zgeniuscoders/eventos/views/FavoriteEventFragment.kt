package cd.zgeniuscoders.eventos.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cd.zgeniuscoders.eventos.databinding.FragmentFavoriteEventBinding


class FavoriteEventFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteEventBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteEventBinding.inflate(layoutInflater)
        return binding.root
    }

}