package com.example.parakeet.Parakeet_Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.parakeet.Parakeet_Database.Entities.User;
import com.example.parakeet.Parakeet_Database.typeConverters.LocalDateTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TypeConverters(LocalDateTypeConverter.class)
@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class FishDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "ParakeetDatabase";
    public static final String PARAKEET_TABLE = "ParakeetTable";
    private static volatile FishDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static FishDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (FishDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            FishDatabase.class,
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
