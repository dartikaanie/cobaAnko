package com.example.sub1

import android.view.KeyEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.CursorMatchers.withRowString
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.sub1.listPertandingan.PertandinganActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testAppBehaviour() {
        onView(withId(R.id.match_navigation)).perform(click())
        onView(withId(R.id.searchMatch)).check(matches(isDisplayed()))
        onView(withId(R.id.searchMatch)).perform(click());
        onView(withId(R.id.searchMatch)).perform(typeText("arsenal"), pressKey(KeyEvent.KEYCODE_ENTER));
//        closeSoftKeyboard()
        Thread.sleep(5000)
        onView(withId(R.id.recycleMatch)).check(matches(isDisplayed()))
        onView(withId(R.id.recycleMatch)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        Thread.sleep(1000)
        onView(withContentDescription(R.string.favorites)).check(matches(isDisplayed()))
        onView(withContentDescription(R.string.favorites)).perform(click());

        Espresso.pressBack()
        onView(withId(R.id.favorit_navigation)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.recycle_fav)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withContentDescription(R.string.favorites)).check(matches(isDisplayed()))
        onView(withContentDescription(R.string.favorites)).perform(click());
        Espresso.pressBack()
    }




}