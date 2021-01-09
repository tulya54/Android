package com.example.myapplication;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnSwipeTouchListener implements OnTouchListener {

    private final GestureDetector gestureDetector;
    private float leftWidth, rightWidth;
    private int PERCENT_SIDE = 15;


    public OnSwipeTouchListener(Context ctx) {
        DisplayMetrics displayMetrics = ctx.getResources().getDisplayMetrics();
        float width = displayMetrics.widthPixels;
        leftWidth = (width) * PERCENT_SIDE / 100;
        rightWidth = (width) - leftWidth;
        gestureDetector = new GestureDetector(ctx, new GestureListener());
    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            boolean result = false;
            try {
                float diffX = event.getX();
                if(diffX <= leftWidth){
                    onLeftClick();
                } else if (diffX >= rightWidth) {
                    onRightClick();
                } else {
                    onCenterClick();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return result;
        }



        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeLeft();
                        } else {
                            onSwipeRight();
                        }
                    }
                    result = true;
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                }
                result = true;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public void onSwipeRight() { }

    public void onSwipeLeft() { }

    public void onSwipeTop() { }

    public void onSwipeBottom() { }

    public void onRightClick() { }

    public void onLeftClick() { }

    public void onCenterClick() { }
}
