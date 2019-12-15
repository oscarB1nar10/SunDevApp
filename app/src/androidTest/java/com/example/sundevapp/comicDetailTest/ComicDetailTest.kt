package com.example.sundevapp.comicDetailTest

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.sundevapp.R
import com.example.sundevapp.ui.comicDetail.ComicDetail
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ComicDetailTest {



    @Test
    fun testNavigationToInGameScreen() {
        // Create a mock NavController
        val mockNavController = mock(NavController::class.java)
        // Create a graphical FragmentScenario for the TitleScreen
        val titleScenario = launchFragmentInContainer<ComicDetail>()
        // Set the NavController property on the fragment
        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        val bundle = bundleOf("comic_detail" to "https://comicvine.gamespot.com/api/issue/4000-6/")
        // Verify that performing a click prompts the correct Navigation action
        onView(ViewMatchers.withId(R.id.imv_comic_img)).perform(ViewActions.click())
        verify(mockNavController).navigate(R.id.action_comicBook_to_comicDetail, bundle)
    }
}