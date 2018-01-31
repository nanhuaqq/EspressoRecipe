package com.xty.espressorecipe;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.xty.espressorecipe.recipe.ListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Administrator on 2018/1/29 0029.
 */
@RunWith(AndroidJUnit4.class)
public class ListActivityEspressoTest {

    @Rule
    public ActivityTestRule<ListActivity> mActivityRule = new ActivityTestRule<>(ListActivity.class);

    @Test
    public void firstTest(){
        onView(allOf(withText("发财"), hasSibling(withText("hello"))))
                .perform(click());
    }
}
