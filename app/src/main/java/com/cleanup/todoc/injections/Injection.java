package com.cleanup.todoc.injections;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by de Meeûs Augustin on 2019-12-14
 **/
public class Injection {

    public static ProjectDataRepository provideProjectDataSource(Context context){
        TodocDatabase database = TodocDatabase.getInstance(context);
        return new ProjectDataRepository(database.projectDao());
    }

    public static TaskDataRepository provideTaskDataSource(Context context){
        TodocDatabase database = TodocDatabase.getInstance(context);
        return new TaskDataRepository(database.taskDao());
    }

    public static Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory provideViewModelFactory(Context context){
        TaskDataRepository taskDataRepository = provideTaskDataSource(context);
        ProjectDataRepository projectDataRepository = provideProjectDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(projectDataRepository, taskDataRepository, executor);
    }
}
