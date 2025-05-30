package com.example.worldstory.view.ducfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentDucLoveBookStoriesBinding
import com.example.worldstory.duc.ducadapter.Duc_CardStoryItem_Adapter
import com.example.worldstory.view_models.ducviewmodel.DucStoryViewModel
import com.example.worldstory.view_models.ducviewmodelfactory.DucStoryViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Duc_LoveBookStories_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Duc_LoveBookStories_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentDucLoveBookStoriesBinding
    private val storyViewModel: DucStoryViewModel by viewModels {
        DucStoryViewModelFactory(requireContext())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDucLoveBookStoriesBinding.inflate(layoutInflater)
        val view = binding.root
        storyViewModel.storiesUserSessionLoved.observe(viewLifecycleOwner, Observer { stories ->
            //ket thuc hieu ung load
            binding.swipeRefreshLoveBookStoryFragment.isRefreshing = false
            //
            if(stories.isNullOrEmpty()){
                binding.txtStoryNotFoundLoveBookStoryFragment.visibility= View.VISIBLE
            }else{
                binding.txtStoryNotFoundLoveBookStoryFragment.visibility= View.GONE

            }
            var cardStoryAdapter = Duc_CardStoryItem_Adapter(view.context, ArrayList(stories))
            binding.recyclerCardStoryLoveBookFragment.apply {
                adapter = cardStoryAdapter
                layoutManager =
                    GridLayoutManager(view.context, 3, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
            }


        })

        setSwipeRefresh()
        // Inflate the layout for this fragment
        return view
    }

    private fun setSwipeRefresh() {
        binding.swipeRefreshLoveBookStoryFragment.setOnRefreshListener {
            storyViewModel.fetchStoriesUserSessionLoved()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoveBookStoriesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Duc_LoveBookStories_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}