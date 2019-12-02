package com.assignment.nytimes;


import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = activityTestRule.getActivity();

    }

    @Test
    public void testLaunch() {
        View view = mainActivity.findViewById(R.id.nav_host_fragment);
        assert true;
    }


    @org.junit.After
    public void tearDown() throws Exception {
        mainActivity = null;
    }


}