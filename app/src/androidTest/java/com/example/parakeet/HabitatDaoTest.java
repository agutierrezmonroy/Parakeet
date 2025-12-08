package com.example.parakeet;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.parakeet.Parakeet_Database.Entities.Habitat;
import com.example.parakeet.Parakeet_Database.FishDatabase;
import com.example.parakeet.Parakeet_Database.HabitatDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class HabitatDaoTest {

    private FishDatabase db;
    private HabitatDAO habitatDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, FishDatabase.class)
                .allowMainThreadQueries()
                .build();
        habitatDao = db.habitatDAO();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertHabitat_andGetAll_containsInsertedHabitat() throws Exception {
        Habitat habitat = new Habitat("River", "Pacific Northwest");
        habitatDao.insert(habitat);

        List<Habitat> allHabitats = LiveDataTestUtil.getOrAwaitValue(
                habitatDao.getAllHabitats()
        );

        assertEquals(1, allHabitats.size());
        Habitat result = allHabitats.get(0);
        assertEquals("River", result.getName());
        assertEquals("Pacific Northwest", result.getRegion());
    }

    @Test
    public void deleteAll_makesTableEmpty() throws Exception {
        habitatDao.insert(new Habitat("Lake", "Midwest"));
        habitatDao.insert(new Habitat("Ocean", "West Coast"));

        habitatDao.deleteAll();

        List<Habitat> allHabitats = LiveDataTestUtil.getOrAwaitValue(
                habitatDao.getAllHabitats()
        );

        assertTrue(allHabitats.isEmpty());
    }

    @Test
    public void insertMultipleHabitats_allReturnedInList() throws Exception {
        habitatDao.insert(new Habitat("River", "North"));
        habitatDao.insert(new Habitat("Lake", "South"));
        habitatDao.insert(new Habitat("Pond", "East"));

        List<Habitat> allHabitats = LiveDataTestUtil.getOrAwaitValue(
                habitatDao.getAllHabitats()
        );

        assertEquals(3, allHabitats.size());
    }
}
