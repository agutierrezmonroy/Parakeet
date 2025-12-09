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
import com.example.parakeet.Parakeet_Database.UserFishHabitatDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;


@RunWith(AndroidJUnit4.class)
public class FishDaoTest {

    private FishDatabase db;
    private FishDAO fishDao;
    private HabitatDAO habitatDao;
    private UserDAO userDao;
    private UserFishHabitatDAO uhfDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, FishDatabase.class)
                .allowMainThreadQueries()
                .build();

        fishDao = db.fishDAO();
        habitatDao = db.habitatDAO();
        userDao = db.userDAO();
        uhfDao = db.uhfDAO();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertFish_andGetAll_containsInsertedFish() throws Exception {
        User user = new User("TestUser", "pass123");
        long userId = userDao.insert(user);

        Habitat habitat = new Habitat("Deep Sea Biome", "Pacific Ocean");
        long[] habitatIds = habitatDao.insert(habitat);
        long habitatId = habitatIds[0];

        Fish fish = new Fish("FishExample", 10.5, 3.2, true, habitatId);
        fish.setFishUserId(userId);
        fishDao.insert(fish);

        List<Fish> allFish = LiveDataTestUtil.getOrAwaitValue(
                fishDao.getAllFish()
        );

        assertEquals(1, allFish.size());
        Fish result = allFish.get(0);

        assertEquals("FishExample", result.getSpecies());
        assertEquals(10.5, result.getLength(), 0.001);
        assertEquals(3.2, result.getWeight(), 0.001);
        assertTrue(result.isEdible());
        assertEquals(habitatId, result.getHabitat_id());
        assertEquals(userId, result.getFishUserId());
    }

    @Test
    public void deleteAll_makesTableEmpty() throws Exception {
        User user = new User("TestUser", "pass123");
        long userId = userDao.insert(user);

        Habitat habitat = new Habitat("Deep Sea Biome", "Pacific Ocean");
        long[] habitatIds = habitatDao.insert(habitat);
        long habitatId = habitatIds[0];

        Fish fish1 = new Fish("Fish1", 1.0, 1.0, true, habitatId);
        fish1.setFishUserId(userId);

        Fish fish2 = new Fish("Fish2", 2.0, 2.0, false, habitatId);
        fish2.setFishUserId(userId);

        fishDao.insert(fish1);
        fishDao.insert(fish2);

        fishDao.deleteAll();

        List<Fish> allFish = LiveDataTestUtil.getOrAwaitValue(
                fishDao.getAllFish()
        );
        assertTrue(allFish.isEmpty());
    }

    @Test
    public void insertMultipleFish_allReturnedInList() throws Exception {
        User user = new User("TestUser", "pass123");
        Habitat habitat = new Habitat("Deep Sea Biome", "Pacific Ocean");

        Fish fishA = new Fish("Anglerfish", 40.3, 110.0, false, 0);
        Fish fishB = new Fish("Sablefish", 32.3, 8.5, true, 0);

        uhfDao.insertUHF(user, habitat, fishA, fishB);

        List<Fish> allFish = LiveDataTestUtil.getOrAwaitValue(
                fishDao.getAllFish()
        );

        assertNotNull(allFish);
        assertEquals(2, allFish.size());

        long userId = user.getUserid();
        long habitatId = habitat.getHabitatId();

        for (Fish f : allFish) {
            assertEquals(userId, f.getFishUserId());
            assertEquals(habitatId, f.getHabitat_id());
        }
    }
}