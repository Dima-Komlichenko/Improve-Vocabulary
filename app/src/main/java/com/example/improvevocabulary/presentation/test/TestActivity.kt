package com.example.improvevocabulary.presentation.test

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.ActivityTestBinding
import com.example.improvevocabulary.presentation.tests.TypeOfTestInfo
import com.example.improvevocabulary.presentation.tests.TypeOfTestInfoConst
import com.example.improvevocabulary.utlis.AdMob
import com.google.android.gms.ads.FullScreenContentCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

const val ACTIVITY_STATE_INFO_CONST = "ACTIVITY_STATE_INFO_CONST"

enum class ActivityStateInfo { Created, Restored }

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private lateinit var viewModel: TestViewModel
    private lateinit var adMob: AdMob

    @Inject
    lateinit var testViewModelFactory: TestViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adMob = AdMob()
        adMob.loadAds(applicationContext)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as App).appComponent.inject(this)

        var activityState = savedInstanceState?.get(ACTIVITY_STATE_INFO_CONST) as ActivityStateInfo?
        if (activityState == ActivityStateInfo.Restored) return

        viewModel = ViewModelProvider(this, testViewModelFactory)[TestViewModel::class.java]
        viewModel.typeOfTestInfo =
            intent.getSerializableExtra(TypeOfTestInfoConst) as TypeOfTestInfo
        viewModel.initTests()
        setTestFragment()
    }

    private fun setTestFragment() {

        when (viewModel.typeOfTestInfo) {
            TypeOfTestInfo.Test -> {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fcv_test, TestFragment())
                    .commit()
            }
            TypeOfTestInfo.Practice -> {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fcv_test, PracticeFragment())
                    .commit()
            }
            TypeOfTestInfo.Repetition -> {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fcv_test, RepetitionFragment())
                    .commit()
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(ACTIVITY_STATE_INFO_CONST, ActivityStateInfo.Restored)
    }

    override fun onBackPressed() {
        if (viewModel.typeOfTestInfo != TypeOfTestInfo.Practice) {
            AlertDialog.Builder(this)
                .setTitle(R.string.exit)
                .setMessage(R.string.exit_test)
                .setPositiveButton(R.string.ok) { _, _ ->
                    setAdFinishHandler()
                    if (!adMob.show(this)) {
                        viewModel.isFinishTest.value = true
                        adMob.loadAds(applicationContext)
                    }
                }
                .setNegativeButton(R.string.back) { _, _ -> }
                .create()
                .show()
        } else {
            setAdFinishHandler()
            if (!adMob.show(this)) {
                viewModel.isFinishTest.value = true
                adMob.loadAds(applicationContext)
            }
        }

        viewModel.isFinishTest.observe(this) {
            if (it) super.onBackPressed()
        }
    }

    private fun setAdFinishHandler() {
        adMob.mInterstitialAd?.fullScreenContentCallback =
            object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(ContentValues.TAG, "Ad dismissed fullscreen content.")
                    viewModel.isFinishTest.value = true
                }
            }
    }
}









