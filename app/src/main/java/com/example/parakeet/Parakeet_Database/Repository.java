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
    private final UserDAO parakeetDAO;
    private ArrayList<User> users;
    private static Repository repository;

    private Repository(Application application){
        Database db = Database.getDatabase(application);
        this.parakeetDAO = db.userDAO();
        this.users = (ArrayList<User>) this.parakeetDAO.getAllUsers();
    }

    public static Repository getRepository(Application application){
        if (repository != null){
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
        } catch(InterruptedException | ExecutionException e){
            Log.d(MainActivity.TAG, "Problem getting Repository, thread error.");
        }

        return null;
    }

    public ArrayList<User> getUsers() {
        Future<ArrayList<User>> future = Database.databaseWriteExecutor.submit(
                new Callable<ArrayList<User>>() {
                    @Override
                    public ArrayList<User> call() throws Exception {
                        return (ArrayList<User>) parakeetDAO.getAllUsers();
                    }
                }

        );
        try{
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Log.i(MainActivity.TAG,"Problem when getting all GymLogs in the repository" );
        }

        return null;
    }

    public void insertUser(User... user){
        Database.databaseWriteExecutor.execute(()->
                parakeetDAO.insert(user));

    }
}
