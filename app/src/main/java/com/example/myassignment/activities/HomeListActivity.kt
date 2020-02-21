package com.example.myassignment.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.myassignment.R
import com.example.myassignment.databinding.ActivityMainBinding
import com.example.myassignment.viewModel.HomeListActivityViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeListActivity : AppCompatActivity() {


    val viewModel: HomeListActivityViewModel by inject { parametersOf(this) }


    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.viewModel = viewModel
        setSupportActionBar(toolbar)

        observeListners()
        checkAndLoadToDoList()
    }

    @ExperimentalCoroutinesApi
    private fun checkAndLoadToDoList() {
        if (viewModel.toDoList.isNullOrEmpty())
            viewModel.fetchToDoListFromServer()

    }

    private fun observeListners() {
        viewModel.toDoItemClickListner.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelAllRequests()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}

