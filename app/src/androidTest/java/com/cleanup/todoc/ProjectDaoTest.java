package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by de Meeûs Augustin on 2019-12-12
 **/
@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {

    private TodocDatabase database;

    private static Project project1 = new Project(1L, "Project Test", 0xffffff00);

    private static Project project2 = new Project(2L, "Project Test 2", 0xffffdf00);

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
    public void testGetAllTheProjects() throws InterruptedException{
        database.projectDao().insertProject(project1);
        assertEquals(1, LiveDataTestUtil.getValue(database.projectDao().getAllProjects()).size());


        database.projectDao().insertProject(project2);
        assertEquals(2, LiveDataTestUtil.getValue(database.projectDao().getAllProjects()).size());
    }
}
