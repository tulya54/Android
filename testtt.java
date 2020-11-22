/*
float[] radius = {mTopLeftRadius, mTopLeftRadius, mTopRightRadius, mTopRightRadius, mBottomRightRadius,
            mBottomRightRadius, mBottomLeftRadius, mBottomLeftRadius};
 */
//textView.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_priority_one, 0, 0);
/*
dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                civNextLocation.setCircleBackgroundColor(Color.rgb(255, 255, 255));
                }
            });
 */


/*
 if (LocaleHelper.getLanguage(MainActivity.this).equalsIgnoreCase("en")) {
            spnLang.setSelection(0);
        } else if (LocaleHelper.getLanguage(MainActivity.this).equalsIgnoreCase("ru")) {
            spnLang.setSelection(1);
        } else if (LocaleHelper.getLanguage(MainActivity.this).equalsIgnoreCase("kk")) {
            spnLang.setSelection(2);
        }

Context context;

 context = LocaleHelper.setLocale(MainActivity.this, "en");
                        resources = context.getResources();
                        changeText(resources);
 */

/*
float[] radius = {mTopLeftRadius, mTopLeftRadius, mTopRightRadius, mTopRightRadius, mBottomRightRadius,
            mBottomRightRadius, mBottomLeftRadius, mBottomLeftRadius};
 */

/*
private void hideSoftkeyboard() {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService( Context.INPUT_METHOD_SERVICE );
        View f = getActivity().getCurrentFocus();
        if( null != f && null != f.getWindowToken() && EditText.class.isAssignableFrom( f.getClass() ) )
            imm.hideSoftInputFromWindow( f.getWindowToken(), 0 );
        else
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
 */

// ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);

//.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
//.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);

/*
private void showPopup(final View anchor) {
        PopupMenu popupMenu = new PopupMenu(anchor.getContext(), anchor);
        popupMenu.getMenu().add(R.id.menuGroup, R.id.menu1, Menu.NONE, "slot1");
        popupMenu.getMenu().add(R.id.menuGroup, R.id.menu1, Menu.NONE,"slot2");
        popupMenu.getMenu().add(R.id.menuGroup, R.id.menu1, Menu.NONE,"slot3");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(anchor.getContext(), item.getTitle() + "clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        popupMenu.show();
    }
 */



/*
 public static String getUserCountry(Context context) {
        //  Получение кода страны
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US);
            }
            else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toLowerCase(Locale.US);
                }
            }
        }
        catch (Exception e) { }
        return null;
    }
 */
//long currentTime = System.currentTimeMillis()
//System.currentTimeMillis()

/*
@Override
    public void onPause() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onPause();
    }
    @Override
    public void onResume() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onResume();
    }
 */

//Picasso.with(context).load(itemsModel.getPostPhoto()).fit().centerInside().into(holder.ivPostPhoto);
//Object objTimeStamp = ServerValue.TIMESTAMP;


/*
 Glide.with(context).load(arrayList.get(position))
                .centerCrop()
                .into(imageView);
 */

/*
 protected URL stringToURL(String urlString){
        try{
            URL url = new URL(urlString);
            return url;
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap getBitmap(String company) {
        Bitmap bitmap = null;
        String sdCard = Environment.getExternalStorageDirectory().toString();
        File imageFile = new File(sdCard + "/Planner/Company/" + company);
        if (imageFile.exists()) {
            bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        }
        return bitmap;
    }

    private class LoadImage extends AsyncTask<URL,Void,Bitmap> {
        protected void onPreExecute(){

        }
        protected Bitmap doInBackground(URL...urls){
            URL url = urls[0];
            HttpURLConnection connection = null;
            try{
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
                return bmp;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return null;
        }
        protected void onPostExecute(Bitmap result){
            boolean b = onSaveImage(result);
            if (b) {
              if (company != null && result != null) {
                  if (!company.equals("")) {
                      adminView.setImageAndTextCompany(company, result);
                  }
              }
            }
        }
    }

    private boolean onSaveImage(Bitmap bitmap) {
        FileOutputStream fos = null;
        try {
            String sdCard = Environment.getExternalStorageDirectory().toString();
            File filePolyCard = new File(sdCard, "/Planner/");
            if (!filePolyCard.exists()) {
                filePolyCard.mkdir();
            }
            File fileWallet = new File(sdCard, "/Planner/" + "/Company/");
            if (!fileWallet.exists()) {
                fileWallet.mkdir();
            }
            fos = new FileOutputStream(sdCard + "/Planner/Company/" + company + ".jpg");
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 */
 
 
 
 
  public static ArrayList<GalleryPOJO> getAllShownImagesPath(Context context) {

        ArrayList<GalleryPOJO> listOfAllImages = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cur = contentResolver.query(uri, null, null, null, null);

        if (cur.moveToFirst()) {
            do {
                int pathIndex = cur.getColumnIndex(MediaStore.Audio.Media.DATA);

                int nameIndex = cur.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);

                String spath = cur.getString(pathIndex);
                String name = cur.getString(nameIndex);
                //    paths.add(spath.substring(4));
                //   songs.add(name);
                listOfAllImages.add(new GalleryPOJO(name, spath, 3, false));
            } while (cur.moveToNext());




        }
        return listOfAllImages;
    }
    
    
        public static ArrayList<GalleryPOJO> getAllShownImagesPath(Context context) {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<GalleryPOJO> listOfAllImages = new ArrayList<>();
        String absolutePathOfImage = null, title;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME };

        cursor = context.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
     //   column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            title = cursor.getString(column_index_folder_name);
            listOfAllImages.add(new GalleryPOJO(title, absolutePathOfImage, 1, false));
        }
        return listOfAllImages;
    }
    
    
    
     public static ArrayList<GalleryPOJO> getAllShownImagesPath(Context context) {

        ArrayList<GalleryPOJO> listOfAllImages = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.Video.Media.DATA , MediaStore.Video.Media.DISPLAY_NAME};
        Cursor cur = contentResolver.query(uri, projection, null, null, null);

        if (cur.moveToFirst()) {
            do {
                int pathIndex = cur.getColumnIndex(MediaStore.Video.Media.DATA);

                int nameIndex = cur.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME);

                String spath = cur.getString(pathIndex);
                String name = cur.getString(nameIndex);
                //    paths.add(spath.substring(4));
                //   songs.add(name);
                listOfAllImages.add(new GalleryPOJO(name, spath, 2, false));
            } while (cur.moveToNext());




        }
        return listOfAllImages;
    }
    
    
    
    public GradientDrawable getGradientDrawable(float[] radius, int[] colors, int borderWidth, int borderColor) {
        GradientDrawable gradientDrawable = null;
        float oneTopLeftRadius = radius[0];//  1
        float twoTopLeftRadius = radius[1];//  2
        float oneTopRightRadius = radius[2];//  3
        float twoTopRightRadius = radius[3];//  4
        float oneBottomRightRadius = radius[4];//  5
        float twoBottomRightRadius = radius[5];//  6
        float oneBottomLeftRadius = radius[6];//  7
        float twoBottomLeftRadius = radius[7];//  8
        gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors);
        gradientDrawable.setCornerRadii(new float[]{
                oneTopLeftRadius, twoTopLeftRadius,
                oneTopRightRadius, twoTopRightRadius,
                oneBottomRightRadius, twoBottomRightRadius,
                oneBottomLeftRadius, twoBottomLeftRadius});
        gradientDrawable.setStroke(
                borderWidth, // Border width
                borderColor // Border color
        );
        return gradientDrawable;
    }
    
    
    public static String getGeneratedString() {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String data = upperCase + lowerCase + numbers;
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(20);
        for (int i = 0; i < 20; i++) {
            stringBuilder.append(data.charAt(random.nextInt(data.length())));
        }
        return stringBuilder.toString();
    }
    
    
    
      public static URL stringToURL(String urlString){
        try{
            URL url = new URL(urlString);
            return url;
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    
     private GradientDrawable getGradientDrawable(int[] colors, String corner) {
        float radius = 20f;
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors);
        switch (corner) {
            case "LEFT":
                gradientDrawable.setCornerRadii(new float[]{radius, radius, 0, 0, 0, 0, radius, radius});
                break;
            case "RIGHT":
                gradientDrawable.setCornerRadii(new float[]{0, 0, radius, radius, radius, radius, 0, 0});
                break;
        }
        gradientDrawable.setStroke(
                1, // Border width
                Color.rgb(30, 144, 255) // Border color
        );
        return gradientDrawable;
    }
    private InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            String blockCharacterSet = "~#^|$%*!@/()-'\":;,?{}=!$^';,?×÷<>{}€£¥₩%~`¤♡♥_|《》¡¿°•○●□■◇◆♧♣▲▼▶◀↑↓←→☆★▪:-);-):-D:-(:'(: N.+";
            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };
    
    
      private void onCustomTextView(LinearLayout llContainer, String text, final String margin) {
        TextView textView = new TextView(context);
        int[] colorsStart = {Color.rgb(255, 255, 255), Color.rgb(245, 245, 245)};
        int[] colorsEnd =  {Color.rgb(245, 245, 245), Color.rgb(255, 255, 255)};
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[] {-android.R.attr.state_pressed }, getGradientDrawable(colorsStart, margin));
        stateListDrawable.addState(new int[] {}, getGradientDrawable(colorsEnd, margin));
        textView.setBackgroundDrawable(stateListDrawable);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.height = DimensionDP.sizeDP(40, context);
        params.weight = 1;
        textView.setLayoutParams(params);
        textView.setText(text);
        textView.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textView.setTextAppearance(android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Body1);
        } else {
            textView.setTextAppearance(context, android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Body1);
        }
        textView.setTextColor(Color.rgb(30, 144, 255));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (margin) {
                    case "LEFT": registrationAdminListener.onBackPressed(); break;
                    case "RIGHT": onInputRegistrationAdmin(); break;
                }
            }
        });
        llContainer.addView(textView);
    }
    
    
     private void onCustomEditText(LinearLayout llMain, EditText editText, String hint, String margin) {
        float radius = 20f;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(new float[]{radius, radius, radius, radius, radius, radius, radius, radius});
        gradientDrawable.setStroke(
                1, // Border width
                Color.rgb(206, 206, 206) // Border color
        );
        editText.setBackgroundDrawable(gradientDrawable);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.height = DimensionDP.sizeDP(40, context);
        switch (margin) {
            case "TOP": params.setMargins(0, 0, 0, 0); break;
            case "TOP_CENTER": params.setMargins(0, DimensionDP.sizeDP(10, context), 0, 0); break;
            case "CENTER": params.setMargins(0, DimensionDP.sizeDP(10, context), 0, 0); break;
            case "BOTTOM_CENTER": params.setMargins(0, DimensionDP.sizeDP(10, context), 0, 0); break;
            case "BOTTOM": params.setMargins(0, DimensionDP.sizeDP(10, context), 0, 0); break; }
        editText.setLayoutParams(params);
        editText.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        editText.setHint(hint);
        editText.setHintTextColor(Color.rgb(206, 206, 206));
        editText.setPadding(DimensionDP.sizeDP(10, context), 0, 0, 0);
        editText.setMaxLines(1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            editText.setTextAppearance(android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Body1);
        } else {
            editText.setTextAppearance(context, android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Body1);
        }
        editText.setTextColor(Color.rgb(0, 0, 0));
        llMain.addView(editText);
        if (editText == etPhone) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            int maxLength = 12;
            editText.setFilters(new InputFilter[] { filter , new InputFilter.LengthFilter(maxLength)});
        }
    }
    
    
    
    private GradientDrawable getGradientDrawable(float radius, int[] colors) {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors);
        gradientDrawable.setCornerRadii(new float[]{radius, radius, radius, radius, radius, radius, radius, radius});
        gradientDrawable.setStroke(
                1, // Border width
                Color.rgb(30, 144, 255) // Border color
        );
        return gradientDrawable;
    }
    
    
     private InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            String blockCharacterSet = "~#^|$%*!@/()-'\":;,?{}=!$^';,?×÷<>{}€£¥₩%~`¤♡♥_|《》¡¿°•○●□■◇◆♧♣▲▼▶◀↑↓←→☆★▪:-);-):-D:-(:'(: N.+";
            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };
    
    public class Language extends Application {
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LocaleSelect.onAttach(context, "en"));
    }
}



public class LoadUrlImage extends AsyncTask<URL, Void, Bitmap> {
    private String folder, fileName;
    public LoadUrlImage(String folder, String fileName) {
        this.folder = folder;
        this.fileName = fileName;
    }
    protected void onPreExecute(){

    }
    protected Bitmap doInBackground(URL...urls){
        URL url = urls[0];
        HttpURLConnection connection = null;
        try{
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
            return bmp;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }
    protected void onPostExecute(Bitmap result){
        boolean b = FileSave.onSaveImage(result, folder, fileName);

    }
}



  public static boolean onSaveImage(Bitmap bitmap, String folder, String fileName) {
        FileOutputStream fos = null;
        try {
            String sdCard = Environment.getExternalStorageDirectory().toString();
            File filePolyCard = new File(sdCard, "/Planner/");
            if (!filePolyCard.exists()) {
                filePolyCard.mkdir();
            }
            File fileWallet = new File(sdCard, "/Planner/" + folder);//"/Company/"
            if (!fileWallet.exists()) {
                fileWallet.mkdir();
            }
            fos = new FileOutputStream(sdCard + "/Planner/" + folder + fileName + ".jpg");
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    
        public static Bitmap getBitmap(String folder, String path) {
        Bitmap bitmap = null;
        String sdCard = Environment.getExternalStorageDirectory().toString();
        File imageFile = new File(sdCard + "/Planner/"+ folder + "/" + path);
        if (imageFile.exists()) {
            bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        }
        return bitmap;
    }
    
    
    
    
