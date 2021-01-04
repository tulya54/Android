for(int n = 0; n  mTabLayout.getTabCount(); n++){

        View tab = ((ViewGroup)mTabLayout.getChildAt(0)).getChildAt(n);

        if(tab != null && tab.getBackground() instanceof RippleDrawable){
            RippleDrawable rippleDrawable = (RippleDrawable)tab.getBackground();
            if (rippleDrawable != null) {
                rippleDrawable.setColor(ColorStateList.valueOf(rippleColor));
            }
        }
    }
