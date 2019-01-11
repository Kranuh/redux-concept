package timkranen.rdx.sample.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import timkranen.rdx.R
import timkranen.rdx.sample.SampleApp
import timkranen.rdx.sample.model.LoginViewState
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, MainFactory()).get(MainViewModel::class.java)

        loginButton.setOnClickListener {
            viewModel.login()
        }

        viewModel.viewState.observe(this, Observer { state ->
            state?.let {
                render(it)
            }
        })
    }

    private fun render(viewState: LoginViewState) {
        if(viewState.isLoading) {
            setLoadingState()
        } else {
            setUserState(viewState)
        }
    }

    private fun setLoadingState() {
        progressBar.visibility = View.VISIBLE
        loginButton.visibility = View.GONE
        userName.visibility = View.GONE
        userId.visibility = View.GONE
    }

    private fun setUserState(viewState: LoginViewState) {
        progressBar.visibility = View.GONE

        val isLoggedIn = !viewState.userName.isEmpty()
        val userVisibility = if(isLoggedIn) View.VISIBLE else View.GONE
        val buttonVisibility = if(isLoggedIn) View.GONE else View.VISIBLE

        userName.visibility = userVisibility
        userId.visibility = userVisibility
        loginButton.visibility = buttonVisibility

        userName.text = viewState.userName
        userId.text = viewState.userId
    }

    inner class MainFactory: ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(SampleApp.storeInstance) as T
            }

            throw IllegalArgumentException("")
        }

    }


}
