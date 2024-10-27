package kh.edu.rupp.ite.madproject.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kh.edu.rupp.ite.madproject.databinding.ActivityLandingBinding
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.madproject.R
import kh.edu.rupp.ite.madproject.fragment.HomeFragment
import kh.edu.rupp.ite.madproject.fragment.MessageFragment
import kh.edu.rupp.ite.madproject.fragment.NotificationFragment
import kh.edu.rupp.ite.madproject.fragment.ProfileFragment

class LandingActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLandingBinding


    private val homeFragment = HomeFragment()
    private val messageFragment = MessageFragment()
    private val notificationFragment = NotificationFragment()
    private val profileFragment = ProfileFragment()


    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add all fragments to the Activity
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        activeFragment = homeFragment
        fragmentTransaction.add(binding.lytFragment.id, homeFragment)

        fragmentTransaction.add(binding.lytFragment.id, messageFragment).hide(messageFragment)

        fragmentTransaction.add(binding.lytFragment.id, notificationFragment).hide(notificationFragment)

        fragmentTransaction.add(binding.lytFragment.id, profileFragment).hide(profileFragment)

        fragmentTransaction.commit()


        // Handle bottom navigation view item click
        binding.bottomNavigationView.setOnItemSelectedListener {
            handleOnNavigationItemSelected(it)
        }

    }

    private fun handleOnNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.mnuHome -> showFragment(homeFragment)

            R.id.mnuMessage -> showFragment(messageFragment)

            R.id.mnuNotification -> showFragment(notificationFragment)

            else -> showFragment(profileFragment)
        }

        return true
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.hide(activeFragment)
        fragmentTransaction.show(fragment)
        activeFragment = fragment
        fragmentTransaction.commit()
    }
}