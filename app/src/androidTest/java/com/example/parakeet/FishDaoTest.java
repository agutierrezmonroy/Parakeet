package com.example.parakeet;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.parakeet.Parakeet_Database.Entities.Fish;
import com.example.parakeet.Parakeet_Database.FishDAO;
import com.example.parakeet.Parakeet_Database.FishDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;


@RunWith(AndroidJUnit4.class)
public class FishDaoTest {

    private FishDatabase db;
    private FishDAO fishDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, FishDatabase.class)
                .allowMainThreadQueries()
                .build();
        fishDao = db.fishDAO();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertFish_andGetAll_containsInsertedFish() throws Exception {
        Fish fish = new Fish(10.5, 3.2, true);
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
        fishDao.insert(new Fish(1.0, 1.0, true));
        fishDao.insert(new Fish(2.0, 2.0, false));

        fishDao.deleteAll();

        List<Fish> allFish = LiveDataTestUtil.getOrAwaitValue(
                fishDao.getAllFish()
        );

        assertTrue(allFish.isEmpty());
    }

    @Test
    public void insertMultipleFish_allReturnedInList() throws Exception {
        fishDao.insert(new Fish(1.0, 1.0, true));
        fishDao.insert(new Fish(2.0, 2.0, false));
        fishDao.insert(new Fish(3.5, 5.0, true));

        List<Fish> allFish = LiveDataTestUtil.getOrAwaitValue(
                fishDao.getAllFish()
        );

        assertEquals(3, allFish.size());
    }
}

