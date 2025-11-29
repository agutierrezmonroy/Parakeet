package com.example.parakeet.Parakeet_Database;

import android.app.Application;
import android.util.Log;

import com.example.parakeet.MainActivity;
import com.example.parakeet.Parakeet_Database.Entities.User;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Repository {

    private final UserDAO userDAO;
    private static Repository repository;

    private Repository(Application application) {
        Database db = Database.getDatabase(application);

        this.userDAO = db.userDAO();
    }

    public static Repository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }

        Future<Repository> future = Database.databaseWriteExecutor.submit(
                new Callable<Repository>() {
                    @Override
                    public Repository call() throws Exception {
                        return new Repository(application);
                    }
                }
        );
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d(MainActivity.TAG, "Problem getting Repository, thread error.");
        }

        return null;
    }



  /*  public void insertUser(User... user) {
        Database.databaseWriteExecutor.execute(() ->
             userDAO.insert(user));

    }
    */
}