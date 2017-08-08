package com.vwaber.udacity.crusty;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vwaber.udacity.crusty.data.Recipe;
import com.vwaber.udacity.crusty.ui.MainActivity;
import com.vwaber.udacity.crusty.ui.RecipeDetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void clickRecipeListItem_LaunchesIntent() {

        onView(withId(R.id.rv_recipe_list))
                .check(matches(isDisplayed()))
                .perform(actionOnItemAtPosition(0, click()));

        intended(hasComponent(RecipeDetailActivity.class.getName()));
        intended(hasExtraWithKey(Recipe.PARCELABLE_EXTRA_KEY));

    }


}
