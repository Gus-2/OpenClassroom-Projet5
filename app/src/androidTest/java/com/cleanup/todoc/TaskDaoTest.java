package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.cleanup.todoc.DataInterface.project1;
import static com.cleanup.todoc.DataInterface.project2;
import static com.cleanup.todoc.DataInterface.task1;
import static com.cleanup.todoc.DataInterface.task2;
import static com.cleanup.todoc.DataInterface.task3;
import static org.junit.Assert.assertEquals;

/**
 * Created by de Meeûs Augustin on 2019-12-12
 **/
@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    private TodocDatabase database;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception{
        database.close();
    }

    @Test
    public void testGetAllTheTasks() throws InterruptedException{
        database.projectDao().insertProject(project1);
        database.projectDao().insertProject(project2);

        //database.taskDao().insertTask(task1);
        //database.taskDao().insertTask(task2);
        //database.taskDao().insertTask(task3);

        assertEquals(0, LiveDataTestUtil.getValue(database.taskDao().getAllTask()).size());
    }

    @Test
    public void testDeleteTask() throws InterruptedException{
        database.projectDao().insertProject(project1);
        database.projectDao().insertProject(project2);

        database.taskDao().insertTask(task1);
        database.taskDao().insertTask(task2);
        database.taskDao().insertTask(task3);

        database.taskDao().deleteTask(((int) task1.getId()));

        assertEquals(2, LiveDataTestUtil.getValue(database.taskDao().getAllTask()).size());

        database.taskDao().deleteTask(((int) task2.getId()));

        assertEquals(1, LiveDataTestUtil.getValue(database.taskDao().getAllTask()).size());

    }




}
