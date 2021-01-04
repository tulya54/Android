String firstText = "FistText";
        String secondText = "SecondText";
        int startIndex = 0;
        int endIndex = firstText.length();
        SpannableString spannableString = new SpannableString(firstText + secondText);
        //spannableString.setSpan(new UnderlineSpan(), startIndex, endIndex, 0);
        spannableString.setSpan(new ForegroundColorSpan(Color.rgb(152, 152, 152)), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvText.setText(spannableString);
