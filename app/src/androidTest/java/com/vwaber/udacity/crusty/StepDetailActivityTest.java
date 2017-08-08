package com.vwaber.udacity.crusty;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.vwaber.udacity.crusty.data.DataUtils;
import com.vwaber.udacity.crusty.data.Recipe;
import com.vwaber.udacity.crusty.data.Step;
import com.vwaber.udacity.crusty.ui.StepDetailActivity;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class StepDetailActivityTest {

    private Step mStep;
    private Recipe mRecipe;

    private Context mContext;

    private void init(){
        mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Resources resources = mContext.getResources();
        List<Recipe> recipes = DataUtils.getRecipes(resources.getString(R.string.data_url));
        assert recipes != null;
        mRecipe = recipes.get(0);
        mStep = mRecipe.getSteps().get(0);
    }

    @Rule
    public ActivityTestRule<StepDetailActivity> mActivityRule =
            new ActivityTestRule<StepDetailActivity>(StepDetailActivity.class){
                @Override
                protected Intent getActivityIntent() {
                    init();
                    Intent intent = new Intent(mContext, StepDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Recipe.PARCELABLE_EXTRA_KEY, mRecipe);
                    bundle.putParcelable(Step.PARCELABLE_EXTRA_KEY, mStep);
                    intent.putExtras(bundle);
                    return intent;
                }
    };

    @Test
    public void clickRecyclerViewItem_LaunchesIntent() {

        String text = mStep.getText();

        onView(allOf(isDescendantOfA(withId(R.id.fragment_container_main)), withId(R.id.tv_step_text)))
                .check(matches(withText(text)));

    }

}
