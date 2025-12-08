package com.example.parakeet;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.parakeet.Parakeet_Database.Entities.User;
import com.example.parakeet.Parakeet_Database.FishDatabase;
import com.example.parakeet.Parakeet_Database.UserDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UserDaoTest {

    private FishDatabase db;
    private UserDAO userDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, FishDatabase.class)
                .allowMainThreadQueries()
                .build();
        userDao = db.userDAO();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertUser_andGetByUsername_returnsUser() throws Exception {
        User user = new User("alice", "password123");
        userDao.insert(user);

        User result = LiveDataTestUtil.getOrAwaitValue(
                userDao.getUserByUsername("alice")
        );

        assertNotNull(result);
        assertEquals("alice", result.getUsername());
        assertEquals("password123", result.getPassword());
    }

    @Test
    public void deleteAll_clearsTable() throws Exception {
        userDao.insert(new User("alice", "pw"));
        userDao.insert(new User("bob", "pw"));

        userDao.deleteAll();

        User result = LiveDataTestUtil.getOrAwaitValue(
                userDao.getUserByUsername("alice")
        );

        assertNull(result);
    }

    @Test
    public void insertWithSameUsername_replacesUser() throws Exception {
        User first = new User("alice", "old");
        userDao.insert(first);

        User second = new User("alice", "new");
        userDao.insert(second);

        User result = LiveDataTestUtil.getOrAwaitValue(
                userDao.getUserByUsername("alice")
        );

        assertNotNull(result);
        assertEquals("alice", result.getUsername());
        assertEquals("new", result.getPassword());
    }
}

