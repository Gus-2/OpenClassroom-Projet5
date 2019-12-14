package com.cleanup.todoc;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.Date;

/**
 * Created by de Meeûs Augustin on 2019-12-12
 **/
public interface DataInterface {

    Project project1 = new Project(1L, "Project Test", 0xffffff00);

    Project project2 = new Project(2L, "Project Test 2", 0xffffdf00);

    Task task1 = new Task(1,1L, "First task", new Date().getTime());

    Task task2 = new Task(2, 2L, "Seconde Task Test", new Date().getTime());

    Task task3 = new Task(3, 1L, "Third Task Test", new Date().getTime());
}
