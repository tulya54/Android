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
