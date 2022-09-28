package com.backdoor.walcartandroidtest.View.Activity

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.backdoor.walcartandroidtest.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup(){
        scenario = launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun checkAppRun(){
        onView(withId(R.id.main_activity_parent))
            .check(matches(isDisplayed()))
    }

}