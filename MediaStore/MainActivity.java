public class GetMediaStoreVideos {
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
}

public class GetMediaStoreImages {
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
}

public class GetMediaStoreAudio {
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
}
