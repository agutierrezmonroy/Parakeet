package com.example.parakeet.Parakeet_Database.viewHolders;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.parakeet.Parakeet_Database.Entities.Fish;
import com.example.parakeet.Parakeet_Database.Repository;

import java.util.List;

public class FishViewModel extends AndroidViewModel {
    private final Repository repository;
    private final LiveData<List<Fish>> allFishByID;

    public FishViewModel(Application application, int userID){
        super(application);
        repository = Repository.getRepository(application);
        assert repository != null;
        allFishByID = repository.getAllFishByUserId(userID);
    }

    public LiveData<List<Fish>> getAllFishByID(){
        return allFishByID;
    }

    public void insert(Fish fish){
        repository.insertFish(fish);
    }
}
