package com.example.parakeet.Parakeet_Database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.parakeet.MainActivity;
import com.example.parakeet.Parakeet_Database.Entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static final String DATABASE_NAME = "ParakeetDatabase";
    public static final String PARAKEET_TABLE = "ParakeetTable";
    private static volatile Database INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static Database getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (Database.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            Database.class,
                                DATABASE_NAME
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            databaseWriteExecutor.execute(  () -> {
               UserDAO userDao = INSTANCE.userDAO();
               userDao.deleteAll();
               User admin2 = new User("admin2", "admin2");
               admin2.setIs_admin(true);
               userDao.insert(admin2);
               User testUser1 = new User("testuser1", "testuser1");
               userDao.insert(testUser1);
            });
        }
    };

    public abstract UserDAO userDAO();
}
