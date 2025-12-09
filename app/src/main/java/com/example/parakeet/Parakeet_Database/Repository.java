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
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public class Repository {

    private final UserDAO userDAO;

    private final FishDAO fishDAO;
    private final HabitatDAO habitatDAO;

    private final FishDatabase db;
    private static Repository repository;

    public static Executor databaseExecutor = FishDatabase.databaseWriteExecutor;


    private Repository(Application application) {
        db = FishDatabase.getDatabase(application);

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


    public void insertUHF(User user, Habitat habitat, Fish fishA, Fish fishB){
        databaseExecutor.execute(() -> {
           db.runInTransaction(() -> {
               long userId = userDAO.insert(user);
               long habitatId = habitatDAO.insert(habitat)[0];

               fishA.setFishUserId((int) userId);
               fishA.setHabitat_id(habitatId);
               fishB.setFishUserId((int) userId);
               fishB.setHabitat_id(habitatId);

               fishDAO.insert(fishA, fishB);
           });
        });
    }

    public void insertUser(User... user) {
        databaseExecutor.execute(() ->
             userDAO.insert(user));

    }

    public void insertFish(Fish... fish) {
        databaseExecutor.execute(() ->
                fishDAO.insert(fish));

    }

    public LiveData<List<Fish>>getAllFish(){
        return fishDAO.getAllFish();
    }

    public void insertHabitat(Habitat... habitats) {
        databaseExecutor.execute(() -> {
                habitatDAO.insert(habitats) ;
        });
    }

    public LiveData<List<Habitat>>getAllHabitats(){
        return habitatDAO.getAllHabitats();
    }
}