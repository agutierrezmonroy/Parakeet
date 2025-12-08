package com.example.parakeet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.test.platform.app.InstrumentationRegistry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LiveDataTestUtil {

    public static <T> T getOrAwaitValue(final LiveData<T> liveData)
            throws InterruptedException, TimeoutException {

        final Object[] data = new Object[1];
        CountDownLatch latch = new CountDownLatch(1);

        final Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(T value) {
                data[0] = value;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };

        InstrumentationRegistry.getInstrumentation().runOnMainSync(
                () -> liveData.observeForever(observer)
        );

        if (!latch.await(2, TimeUnit.SECONDS)) {
            throw new TimeoutException("LiveData value was never set.");
        }

        return (T) data[0];
    }
}


