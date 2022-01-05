package com.example.tasktimerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tasktimerapp.database.Category
import com.example.tasktimerapp.database.relations.CategoryTasksRelationship
import com.example.tasktimerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

/////////////// I did this just to test //////////////////
        val result: List<CategoryTasksRelationship> =listOf()
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        viewModel.fetchCategoriesTasks("nn").observe(this@MainActivity, { task ->
                result + task
            })



        viewModel.fetchCategories().observe(this, {
            cate -> getC(cate)
        })




        binding.button.setOnClickListener { add() }
    }
/// for testing //
   private fun add() {
        viewModel.addCategory("test", R.drawable.day)
    }

    private fun getC(category: List<Category>) {
       // viewModel.fetchCategories()
        binding.imageCV.setBackgroundResource(category[0].categoryImage)
    }
}