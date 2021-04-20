package com.project.technodom.main.presentation.activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.project.technodom.R
import com.project.technodom.app.Technodom
import com.project.technodom.global.di.replaceFragment
import com.project.technodom.global.extension.visible
import com.project.technodom.main.di.MainScope
import com.project.technodom.main.presentation.third.ThirdFragment
import com.project.technodom.main.presentation.first.FirstFragment
import com.project.technodom.main.presentation.second.SecondFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import timber.log.Timber


class MainActivity : MvpAppCompatActivity(), MainActivityView {

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    @ProvidePresenter
    fun providePresenter(): MainActivityPresenter {
        getKoin().getScopeOrNull(MainScope.MAIN_ACTIVITY_SCOPE)?.close()
        return getKoin().createScope(
            MainScope.MAIN_ACTIVITY_SCOPE,
            named(MainScope.MAIN_ACTIVITY_SCOPE)
        ).get()
    }

    override fun attachBaseContext(newBase: Context) {
        val newContext: Context?
        Timber.i("SADQQQQFKEF")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val displayMetrics =
                newBase?.resources?.displayMetrics
            val configuration =
                newBase?.resources?.configuration
            if (displayMetrics?.densityDpi != DisplayMetrics.DENSITY_DEVICE_STABLE) {
                // Current density is different from Default Density. Override it
                displayMetrics?.densityDpi = DisplayMetrics.DENSITY_DEVICE_STABLE
                configuration?.densityDpi = DisplayMetrics.DENSITY_DEVICE_STABLE
                newContext = newBase //baseContext.createConfigurationContext(configuration);
            } else {
                // Same density. Just use same context
                newContext = newBase
            }
        } else {
            // Old API. Screen zoom not supported
            newContext = newBase
        }

        super.attachBaseContext(Technodom.localeManager?.setLocale(newContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onFirstInit()

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val thirdFragment = ThirdFragment()

        makeCurrentFragment(firstFragment)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_first -> makeCurrentFragment(firstFragment)
                R.id.nav_second -> makeCurrentFragment(secondFragment)
                R.id.nav_third -> makeCurrentFragment(thirdFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }


    override fun openFirstFragment() {
        supportFragmentManager.replaceFragment(
            R.id.container,
            FirstFragment.newInstance(),
            FirstFragment.TAG
        )
    }


    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLongMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar(show: Boolean) {
        progressBarMain.visible(show)
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            super.onBackPressed()
        } else {
            val dialog = AlertDialog.Builder(this)
                .setMessage(getString(R.string.out))
                .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                    dialog.dismiss()
                    super.onBackPressed()
                }
                .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
            dialog.show()
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorAccent
                )
            )
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorAccent
                )
            )
        }
    }


}