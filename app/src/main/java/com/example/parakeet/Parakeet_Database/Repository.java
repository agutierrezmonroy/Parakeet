package com.example.parakeet.Parakeet_Database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.parakeet.MainActivity;
import com.example.parakeet.Parakeet_Database.Entities.User;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Repository {

    private final UserDAO userDAO;
    private static Repository repository;

    private Repository(Application application) {
        FishDatabase db = FishDatabase.getDatabase(application);

        this.userDAO = db.userDAO();
    }

    public static Repository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }

        Future<Repository> future = FishDatabase.databaseWriteExecutor.submit(
                () -> new Repository(application)
        );
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d(MainActivity.TAG, "Problem getting Repository, thread error.");
        }

        return null;
    }

    public LiveData<User> getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }



    public void insertUser(User... user) {
        FishDatabase.databaseWriteExecutor.execute(() ->
             userDAO.insert(user));

    }

}