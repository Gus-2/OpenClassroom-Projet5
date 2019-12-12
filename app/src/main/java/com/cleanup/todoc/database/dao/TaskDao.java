package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * Created by de Meeûs Augustin on 2019-12-12
 **/
@Dao
public interface TaskDao {
    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getAllTask();

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertTask(Task task);

    @Query("DELETE FROM tasks WHERE id = :id")
    int deleteTask(int id);


}
