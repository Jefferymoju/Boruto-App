package com.jefferymoju.borutoapp.domain.model

import androidx.annotation.DrawableRes
import com.jefferymoju.borutoapp.R

sealed class OnBoardingPage(
    @DrawableRes // denote that this image variable will represent a drawable resource
    val image: Int,
    val title: String,
    val description: String
){
    object First: OnBoardingPage( // object for the onboarding page
        image = R.drawable.greetings,
        title = "Greetings",
        description = "Are you a Boruto fan? Because if you are we have great news for you!"
    )
    object Second: OnBoardingPage(
        image = R.drawable.explore,
        title = "Explore",
        description = "Find your favorite heroes and learn some of the things that you didn't know about them"
    )
    object Third: OnBoardingPage(
        image = R.drawable.power,
        title = "Power",
        description = "Check out your hero's and see how much strong they are compared to others"
    )
}
