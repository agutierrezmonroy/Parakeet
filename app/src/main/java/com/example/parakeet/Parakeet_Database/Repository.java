package com.example.parakeet.Parakeet_Database;

import android.app.Application;
import android.util.Log;

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
            Log.d("TKl", "Problem getting Repository, thread error.");
        }

        return null;
    }

    public User getUserByUsername(String username) {
        Future<User> future = FishDatabase.databaseWriteExecutor.submit(
                () -> userDAO.getUserByUsername(username));

        try {
             return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.i("TKL", "Problem when getting user by username");
        }
        return null;

    }



    public void insertUser(User... user) {
        FishDatabase.databaseWriteExecutor.execute(() ->
             userDAO.insert(user));

    }

}