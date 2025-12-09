package com.example.parakeet.Parakeet_Database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import java.util.List;

import com.example.parakeet.MainActivity;
import com.example.parakeet.Parakeet_Database.Entities.Habitat;
import com.example.parakeet.Parakeet_Database.Entities.User;
import com.example.parakeet.Parakeet_Database.Entities.Fish;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Repository {

    private final UserDAO userDAO;

    private final FishDAO fishDAO;
    private final HabitatDAO habitatDAO;
    private static Repository repository;

    private Repository(Application application) {
        FishDatabase db = FishDatabase.getDatabase(application);

        this.userDAO = db.userDAO();
        this.fishDAO = db.fishDAO();
        this.habitatDAO = db.habitatDAO();
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

    public void insertFish(Fish... fish) {
        FishDatabase.databaseWriteExecutor.execute(() ->
                fishDAO.insert(fish));

    }

    public LiveData<List<Fish>>getAllFish(){
        return fishDAO.getAllFish();
    }

    public LiveData<List<Fish>> getAllFishByUserId(int userId) {
        return fishDAO.getAllFishByUserId(userId);
    }

    public void insertHabitat(Habitat... habitats) {
        FishDatabase.databaseWriteExecutor.execute(() ->
                habitatDAO.insert(habitats));

    }

    public LiveData<List<Habitat>>getAllHabitats(){
        return habitatDAO.getAllHabitats();
    }
}