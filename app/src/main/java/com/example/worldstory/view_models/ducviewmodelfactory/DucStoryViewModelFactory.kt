package com.example.worldstory.view_models.ducviewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.worldstory.data.dbhelper.DatabaseHelper
import com.example.worldstory.data.ducrepository.DucDataRepository
import com.example.worldstory.view_models.ducviewmodel.DucStoryViewModel

class DucStoryViewModelFactory(private var context :Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DucStoryViewModel::class.java))
        {
            var dbHepler= DatabaseHelper.getInstance(context)

            var repository: DucDataRepository = DucDataRepository(dbHepler)
            return DucStoryViewModel(repository,context) as T
        }
        throw IllegalArgumentException("unknown view model class")
    }
}