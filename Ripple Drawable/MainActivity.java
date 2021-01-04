if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int col = Color.BLUE;
            int g = Color.GRAY;
            ColorStateList pressedStates = ColorStateList.valueOf(Color.CYAN);

            GradientDrawable contentDrawable = new GradientDrawable();
            contentDrawable.setColor(Color.GRAY);
         //   contentDrawable.setCornerRadius(16);

            RippleDrawable rippleDrawable = new RippleDrawable(pressedStates, contentDrawable, null);
            textView.setBackgroundDrawable(rippleDrawable);
           // textView.setBackgroundColor(new RippleDrawable(ColorStateList.valueOf(col), g, null));
        }


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static Drawable generateRippleDrawable(final int color, Rect bounds) {
        ColorStateList list = ColorStateList.valueOf(color);
        Drawable mask = generateCircleDrawable(Color.WHITE);
        RippleDrawable rippleDrawable = new RippleDrawable(list, null, mask);
//        API 21
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            rippleDrawable.setBounds(bounds);
        }

//        API 22. Technically harmless to leave on for API 21 and 23, but not worth risking for 23+
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1) {
            int center = (bounds.left + bounds.right) / 2;
            rippleDrawable.setHotspotBounds(center, bounds.top, center, bounds.bottom);
        }

        return rippleDrawable;
    }


 /*  RippleDrawable rippleDrawable = (RippleDrawable)button.getBackground(); // assumes bg is a RippleDrawable

                        int[][] states = new int[][] { new int[] { android.R.attr.state_enabled} };
                        int[] colors = new int[] { Color.BLUE }; // sets the ripple color to blue

                        ColorStateList colorStateList = new ColorStateList(states, colors);
                        rippleDrawable.setColor(colorStateList);
                        button.setBackgroundColor(rippleDrawable);*/
