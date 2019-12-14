package com.cleanup.todoc.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by de Meeûs Augustin on 2019-12-14
 **/
public class ItemViewModel  extends ViewModel {
    // Repositories
    private final ProjectDataRepository projectDataRepository;
    private final TaskDataRepository taskDataRepository;
    private final Executor executor;

    public ItemViewModel(ProjectDataRepository projectDataRepository, TaskDataRepository taskDataRepository, Executor executor){
        this.projectDataRepository = projectDataRepository;
        this.taskDataRepository = taskDataRepository;
        this.executor = executor;
    }

    // For Project
    public LiveData<List<Project>> getListProject(){
        return projectDataRepository.getAllProjects();
    }

    public void insertProject(Project project){
        executor.execute(() -> {
            this.projectDataRepository.insertProject(project);
        });
    }

    // For Task
    public List<Task> getAllTask(){
        return this.taskDataRepository.getAllTasks().getValue();
    }

    public void insertTask(Task task){
        executor.execute(() -> {
            this.taskDataRepository.insertTask(task);
        });
    }

    public void deleteTask(int id){
        executor.execute(() -> {
            this.taskDataRepository.deleteTask(id);
        });
    }
}
