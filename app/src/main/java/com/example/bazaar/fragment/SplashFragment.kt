package com.example.bazaar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.bazaar.R


class SplashFragment : Fragment(), Animation.AnimationListener {
    private var topAnimation: Animation? = null
    private var rightAnimation: Animation? = null
    private var leftAnimation: Animation? = null
    private var bottomAnimation1: Animation? = null
    private var bottomAnimation2: Animation? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageLogo = requireActivity().findViewById<ImageView>(R.id.imageViewMiniLogo)
        imageLogo.visibility = View.GONE

        topAnimation = AnimationUtils.loadAnimation(view.context, R.anim.top_anim)
        val topLogo = view.findViewById<ImageView>(R.id.splashLogoUp)
        topLogo.startAnimation(topAnimation)

        rightAnimation = AnimationUtils.loadAnimation(view.context, R.anim.right_anim)
        val downLogo = view.findViewById<ImageView>(R.id.splashLogoDown)
        downLogo?.startAnimation(rightAnimation)

        leftAnimation = AnimationUtils.loadAnimation(view.context, R.anim.left_anim)
        val splashText = view.findViewById<TextView>(R.id.textViewSplash)
        splashText?.startAnimation(leftAnimation)

        bottomAnimation1 = AnimationUtils.loadAnimation(view.context, R.anim.bottom_anim)
        val powerByText = view.findViewById<TextView>(R.id.textViewPoweredBy)
        powerByText?.startAnimation(bottomAnimation1)

        bottomAnimation2 = AnimationUtils.loadAnimation(view.context, R.anim.bottom_anim)
        val miniLogo = view.findViewById<ImageView>(R.id.splashLogo)
        miniLogo?.startAnimation(bottomAnimation2)

        topAnimation?.setAnimationListener(this)
    }

    override fun onAnimationStart(p0: Animation?) {
    }

    override fun onAnimationEnd(p0: Animation?) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.logFragment, LogInFragment())?.addToBackStack(null)?.commit()
    }

    override fun onAnimationRepeat(p0: Animation?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        topAnimation?.setAnimationListener(null)
        rightAnimation?.setAnimationListener(null)
        leftAnimation?.setAnimationListener(null)
        bottomAnimation1?.setAnimationListener(null)
        bottomAnimation2?.setAnimationListener(null)
    }
}