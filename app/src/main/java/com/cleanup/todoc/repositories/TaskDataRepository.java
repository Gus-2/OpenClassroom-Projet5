package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * Created by de Meeûs Augustin on 2019-12-14
 **/
public class TaskDataRepository {

    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao){
        this.taskDao = taskDao;
    }

    public LiveData<List<Task>> getAllTasks(){
        return this.taskDao.getAllTask();
    }

    public void insertTask(Task task){
        this.taskDao.insertTask(task);
    }

    public int deleteTask(int id){
        return this.taskDao.deleteTask(id);
    }
}
