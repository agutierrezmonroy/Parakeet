package com.example.parakeet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

    @Test
    public void loginIntentFactory_loginMode_setsExtraFalse() {
        Context context = ApplicationProvider.getApplicationContext();

        Intent intent = LoginActivity.loginActivityIntentFactory(context, false);

        ComponentName component = intent.getComponent();
        assertEquals(LoginActivity.class.getName(), component.getClassName());

        boolean extra = intent.getBooleanExtra("EXTRA_CREATE_ACCOUNT", true);
        assertFalse(extra);
    }

    @Test
    public void loginIntentFactory_createMode_setsExtraTrue() {
        Context context = ApplicationProvider.getApplicationContext();

        Intent intent = LoginActivity.loginActivityIntentFactory(context, true);

        ComponentName component = intent.getComponent();
        assertEquals(LoginActivity.class.getName(), component.getClassName());

        boolean extra = intent.getBooleanExtra("EXTRA_CREATE_ACCOUNT", false);
        assertTrue(extra);
    }

    @Test
    public void landingPageIntentFactory_setsUsernameCorrectly() {
        Context context = ApplicationProvider.getApplicationContext();
        String username = "TestUser123";

        Intent intent = LandingPageActivity.landingPageActivityIntentFactory(context, username);

        ComponentName component = intent.getComponent();
        assertEquals(LandingPageActivity.class.getName(), component.getClassName());

        String result = intent.getStringExtra("com.example.parakeet.username");
        assertEquals(username, result);
    }
}

