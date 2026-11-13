package com.qrvault.app.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.qrvault.app.R
import com.qrvault.app.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivLogo.apply {
            scaleX = 0.4f; scaleY = 0.4f; alpha = 0f
            animate().scaleX(1f).scaleY(1f).alpha(1f)
                .setDuration(800).setInterpolator(OvershootInterpolator(1.4f)).start()
        }
        binding.tvAppName.apply {
            alpha = 0f; translationY = 60f
            animate().alpha(1f).translationY(0f).setStartDelay(450).setDuration(600).start()
        }
        binding.tvTagline.apply {
            alpha = 0f; translationY = 40f
            animate().alpha(1f).translationY(0f).setStartDelay(750).setDuration(600).start()
        }

        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, 2400)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
