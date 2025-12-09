package com.example.parakeet;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.work.testing.SynchronousExecutor;

import com.example.parakeet.Parakeet_Database.Entities.Fish;
import com.example.parakeet.Parakeet_Database.Entities.Habitat;
import com.example.parakeet.Parakeet_Database.Entities.User;
import com.example.parakeet.Parakeet_Database.HabitatDAO;
import com.example.parakeet.Parakeet_Database.FishDAO;
import com.example.parakeet.Parakeet_Database.UserDAO;
import com.example.parakeet.Parakeet_Database.FishDatabase;
import com.example.parakeet.Parakeet_Database.Repository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;


@RunWith(AndroidJUnit4.class)
public class FishDaoTest {

    private FishDatabase db;
    private FishDAO fishDao;

    private HabitatDAO habitatDAO;

    private UserDAO userDAO;

    private Repository repository;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, FishDatabase.class)
                .allowMainThreadQueries()
                .build();

        repository = Repository.getRepository(ApplicationProvider.getApplicationContext());
        fishDao = db.fishDAO();
        habitatDAO = db.habitatDAO();
        userDAO = db.userDAO();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertFish_andGetAll_containsInsertedFish() throws Exception {

        Habitat habitat1 = new Habitat("Deep Sea Biome", "Pacific Ocean");

        long[] habitatIds = habitatDAO.insert(habitat1);
        long habitatId = habitatIds[0];

        Fish fish = new Fish("FishExample", 10.5, 3.2, true, habitatId);
        fishDao.insert(fish);

        List<Fish> allFish = LiveDataTestUtil.getOrAwaitValue(
                fishDao.getAllFish()
        );

        assertEquals(1, allFish.size());
        Fish result = allFish.get(0);
        assertEquals(10.5, result.getLength(), 0.001);
        assertEquals(3.2, result.getWeight(), 0.001);
        assertTrue(result.isEdible());
    }

    @Test
    public void deleteAll_makesTableEmpty() throws Exception {

        Habitat habitat1 = new Habitat("Deep Sea Biome", "Pacific Ocean");

        long[] habitatIds = habitatDAO.insert(habitat1);
        long habitatId = habitatIds[0];

        fishDao.insert(new Fish("Fish1", 1.0, 1.0, true, habitatId));
        fishDao.insert(new Fish("Fish2", 2.0, 2.0, false, habitatId));

        fishDao.deleteAll();

        List<Fish> allFish = LiveDataTestUtil.getOrAwaitValue(
                fishDao.getAllFish()
        );

        assertTrue(allFish.isEmpty());
    }

    @Test
    public void insertMultipleFish_allReturnedInList() throws Exception {
        User user1 = new User("Test1", "pass123");
        Habitat habitat1 = new Habitat("Deep Sea Biome", "Pacific Ocean");


        Fish fishA = new Fish("Anglerfish", 40.3, 110, false, 0);
        Fish fishB = new Fish("Sablefish", 32.3, 8.5, true, 1);


        repository.insertUHF(user1, habitat1, fishA, fishB);

        LiveData<List<Fish>> liveData = repository.getAllFish();
        List<Fish> allFish = LiveDataTestUtil.getOrAwaitValue(liveData);

        assertNotNull(allFish);
        assertEquals(2, allFish.size());
    }
}

