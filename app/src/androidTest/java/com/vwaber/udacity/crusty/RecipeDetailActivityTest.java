package com.vwaber.udacity.crusty;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.vwaber.udacity.crusty.data.DataUtils;
import com.vwaber.udacity.crusty.data.Recipe;
import com.vwaber.udacity.crusty.data.Step;
import com.vwaber.udacity.crusty.ui.RecipeDetailActivity;
import com.vwaber.udacity.crusty.ui.StepDetailActivity;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class RecipeDetailActivityTest {

    @Rule
    public IntentsTestRule<RecipeDetailActivity> mActivityRule =
            new IntentsTestRule<RecipeDetailActivity>(RecipeDetailActivity.class) {
                @Override
                protected Intent getActivityIntent() {

                    Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
                    Resources resources = context.getResources();

                    List<Recipe> recipes = DataUtils.getRecipes(resources.getString(R.string.data_url));
                    assert recipes != null;
                    Recipe data = recipes.get(0);

                    Intent intent = new Intent(context, RecipeDetailActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Recipe.PARCELABLE_EXTRA_KEY, data);
                    intent.putExtras(bundle);

                    return intent;

                }
            };

    @Test
    public void clickRecyclerViewItem_LaunchesIntent() {

        onView(withId(R.id.rv_step_list))
                .check(matches(isDisplayed()))
                .perform(actionOnItemAtPosition(0, click()));

        intended(hasComponent(StepDetailActivity.class.getName()));
        intended(hasExtraWithKey(Recipe.PARCELABLE_EXTRA_KEY));
        intended(hasExtraWithKey(Step.PARCELABLE_EXTRA_KEY));

    }
}
