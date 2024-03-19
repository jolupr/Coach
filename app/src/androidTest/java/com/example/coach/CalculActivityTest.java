package com.example.coach;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.coach.vue.CalculActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.SystemClock;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
@RunWith(JUnit4.class)

public class CalculActivityTest {
    @Rule
    public ActivityScenarioRule<CalculActivity> rule = new ActivityScenarioRule<CalculActivity>(CalculActivity.class);

    @Test
    public void scenario(){
        onView(withId(R.id.btnCalc)).perform(click());
        SystemClock.sleep(5000);
    }
}