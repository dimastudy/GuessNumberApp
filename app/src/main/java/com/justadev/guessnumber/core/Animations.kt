package com.justadev.guessnumber.core

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.LinearInterpolator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DELAY = 750L


fun animateAlpha(
    view: View,
    from: Float,
    to: Float,
    isDelayed: Boolean,
    scope: CoroutineScope,
    onEndAction: () -> Unit
) {
    val animator = ObjectAnimator.ofFloat(view, View.ALPHA, from, to)
    animator.duration = DELAY
    animator.addListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) {}

        override fun onAnimationEnd(animation: Animator) {
            if (isDelayed) {
                scope.launch{
                    delay(DELAY)
                    onEndAction()
                }
            } else {
                onEndAction()
            }
        }
        override fun onAnimationCancel(animation: Animator) {}

        override fun onAnimationRepeat(animation: Animator) {}

    })
    animator.interpolator = LinearInterpolator()
    animator.start()

}