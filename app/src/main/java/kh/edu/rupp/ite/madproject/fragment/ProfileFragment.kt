package kh.edu.rupp.ite.madproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.madproject.databinding.FragmentProfileBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.madproject.adapter.UserPostsAdapter
import kh.edu.rupp.ite.madproject.model.Profile
import kh.edu.rupp.ite.madproject.model.State
import kh.edu.rupp.ite.madproject.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private val viewModel by viewModels<ProfileViewModel>()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Collect the StateFlow
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.profileDataState.collect { profileDataState ->
                when (profileDataState.state) {
                    State.loading -> {
                        showLoading()
                    }
                    State.success -> {
                        hideLoading()
                        fetchProfileData(profileDataState.data!!)
                    }
                    State.error -> {
                        hideLoading()
                        showErrorContent()
                    }
                }
            }
        }

        viewModel.loadProfileData()
    }

    private fun fetchProfileData(profileData: Profile) {
    Picasso.get()
        .load(profileData.profileImg)
        .into(binding.profileImage)
    binding.tvName.text = "${profileData.firstName} ${profileData.lastName}"
    binding.tvPosts.text = "${profileData.posts} posts"
    binding.tvFollowers.text = "${profileData.follower} followers"
    binding.tvFollowing.text = "${profileData.following} following"
    binding.tvBio.text = profileData.bio


    // Set up RecyclerView for user posts
    val userPostsAdapter = UserPostsAdapter(profileData.userPosts)
    binding.recyclerView.adapter = userPostsAdapter
    binding.recyclerView.layoutManager = LinearLayoutManager(context)
}

    private fun showLoading() {
        binding.lytContent.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.lytContent.visibility = View.VISIBLE
    }

    private fun showErrorContent() {
        binding.lytContent.visibility = View.GONE
        binding.lytError.visibility = View.VISIBLE
    }
}