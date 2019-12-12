package com.cleanup.todoc.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

/**
 * Created by de Meeûs Augustin on 2019-12-12
 **/
@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    // SINGLETON
    private static volatile TodocDatabase INSTANCE;

    // DAO
    public abstract ProjectDao projectDao();
    public abstract TaskDao taskDao();

    // INSTANCE
    public static TodocDatabase getInstance(Context context) {
        if(INSTANCE == null){
            synchronized (TodocDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class, "Todocdatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase(){
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues project1 = new ContentValues();

                project1.put("id", 1L);
                project1.put("name", "Projet Tartampion");
                project1.put("color", 0xFFEADAD1);

                db.insert("projects", OnConflictStrategy.IGNORE, project1);

                ContentValues project2 = new ContentValues();

                project2.put("id", 2L);
                project2.put("name", "Projet Lucidia");
                project2.put("color", 0xFFB4CDBA);

                db.insert("projects", OnConflictStrategy.IGNORE, project2);

                ContentValues project3 = new ContentValues();

                project3.put("id", 3L);
                project3.put("name", "Projet Circus");
                project3.put("color", 0xFFA3CED2);

                db.insert("projects", OnConflictStrategy.IGNORE, project3);

            }
        };
    }
}
