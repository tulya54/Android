 if (url!!.startsWith("blob:")) {
                        var result = ""
                        when (mimetype) {
                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" -> {
                                result = "javascript: var xhr = new XMLHttpRequest();" +
                                        "xhr.open('GET', '" + url.substring(5) + "', true);" +
                                        "xhr.setRequestHeader('Content-type','application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8');" +
                                        "xhr.responseType = 'blob';" +
                                        "xhr.onload = function(e) {" +
                                        "    if (this.status == 200) {" +
                                        "        var blobZip = this.response;" +
                                        "        var reader = new FileReader();" +
                                        "        reader.readAsDataURL(blobZip);" +
                                        "        reader.onloadend = function() {" +
                                        "            base64data = reader.result;" +
                                        "            Android.getBase64FromBlobData(base64data);" +
                                        "        }" +
                                        "    }" +
                                        "};" +
                                        "xhr.send();"
                            }
                        }
                        if (result.length > 0) {



                        }
                        
                    }


  @JavascriptInterface
    @Throws(IOException::class)
    fun getBase64FromBlobData(base64Data: String?) {
        Timber.d("${THIS_CLASS}getBase64FromBlobData()-> ${base64Data}")

        convertBase64StringToPdfAndStoreIt(base64Data!!)

    }



 @Throws(IOException::class)
    private fun convertBase64StringToPdfAndStoreIt(base64PDf: String) {

        Log.e("base64PDf", base64PDf);
        var currentDateTime = DateFormat.getDateTimeInstance().format(Date());
        var calendar = Calendar.getInstance()
        ;
        var dwldsPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/Report" + calendar.getTimeInMillis() + "_.xlsx")
//"data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,",
        // var byteArr: ByteArray = android.util.Base64.decode()
        val excel = base64PDf.replaceFirst(
            "data:text/html;base64,",
            ""
        )

        var pdfAsBytes: ByteArray = android.util.Base64.decode(excel, 0)//default 0
        Log.e("bytearrya", "" + pdfAsBytes);

        val thread = Thread(Runnable {

        })
        var os: FileOutputStream
        os = FileOutputStream(dwldsPath, false);
        os.write(pdfAsBytes);
        os.flush();
        os.close();

        if (dwldsPath.exists()) {

            var dir   = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/Report" + calendar.getTimeInMillis() + "_.xlsx")

            val sendIntent =  Intent(Intent.ACTION_VIEW);
            var path = dir.getAbsolutePath ();

            var uri:Uri ;
            if (Build.VERSION.SDK_INT < 24) {
                uri = Uri.fromFile(dir);
            } else {
                var file: File  =  File(path)
              //  uri = Uri.fromFile(File(obj.FOLDER_FILE_PATH + ".provider", file))
                uri = FileProvider.getUriForFile(this, obj.FOLDER_FILE_PATH + ".provider", file)
//                    uri = Uri.parse("file://" + dir);
            }
            sendIntent.setDataAndType(uri, "application/vnd.ms-excel");
            sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            try {
                startActivity(sendIntent);
            } catch (e: Exception) {
              
            }

        }
    }
