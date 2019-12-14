package com.cleanup.todoc.injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.ui.ItemViewModel;

import java.util.concurrent.Executor;

/**
 * Created by de Meeûs Augustin on 2019-12-14
 **/
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ProjectDataRepository projectDataRepository;
    private final TaskDataRepository taskDataRepository;
    private final Executor executor;

    public ViewModelFactory(ProjectDataRepository projectDataRepository, TaskDataRepository taskDataRepository, Executor executor){
        this.projectDataRepository = projectDataRepository;
        this.taskDataRepository = taskDataRepository;
        this.executor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ItemViewModel.class)){
            return (T) new ItemViewModel(projectDataRepository, taskDataRepository, executor);
        }
        throw new IllegalArgumentException("Unknow ViewModel class");
    }

}
