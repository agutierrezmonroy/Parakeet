package com.example.parakeet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class IntentFactoryTest {

    private final Context context = ApplicationProvider.getApplicationContext();
    private static final String USERNAME_KEY = "com.example.parakeet.username";
    private static final String EXTRA_CREATE_ACCOUNT = "EXTRA_CREATE_ACCOUNT";

    @Test
    public void mainActivityIntentFactory_targetsMainActivity() {
        Intent intent = MainActivity.mainActivityIntentFactory(context);

        ComponentName component = intent.getComponent();
        assertNotNull(component);
        assertEquals(MainActivity.class.getName(), component.getClassName());
    }

    @Test
    public void loginActivityIntentFactory_setsCreateAccountTrue() {
        Intent intent = LoginActivity.loginActivityIntentFactory(context, true);

        ComponentName component = intent.getComponent();
        assertNotNull(component);
        assertEquals(LoginActivity.class.getName(), component.getClassName());

        boolean createAccount = intent.getBooleanExtra(EXTRA_CREATE_ACCOUNT, false);
        assertTrue(createAccount);
    }

    @Test
    public void loginActivityIntentFactory_setsCreateAccountFalse() {
        Intent intent = LoginActivity.loginActivityIntentFactory(context, false);

        ComponentName component = intent.getComponent();
        assertNotNull(component);
        assertEquals(LoginActivity.class.getName(), component.getClassName());

        boolean createAccount = intent.getBooleanExtra(EXTRA_CREATE_ACCOUNT, true);
        assertFalse(createAccount);
    }

    @Test
    public void landingPageIntentFactory_putsUsernameExtra() {
        String expectedUsername = "TestUser123";

        Intent intent = LandingPageActivity.landingPageActivityIntentFactory(
                context,
                expectedUsername
        );

        ComponentName component = intent.getComponent();
        assertNotNull(component);
        assertEquals(LandingPageActivity.class.getName(), component.getClassName());

        String actualUsername = intent.getStringExtra(USERNAME_KEY);
        assertEquals(expectedUsername, actualUsername);
    }

}
