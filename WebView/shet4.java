private boolean handleRequest(WebView view, String url) {
String filename;
if (!checkInternetConnection()) {
    ShowNetworkUnavailableDialog(false);
    return true;
}
else {
    if (url.contains("view/mys") || url.contains("view/myy") || url.contains("blob") || url.contains("view/mye")) {
        if (url.contains("view/mys")) {
            filename = getResources().getString(R.string.mys_file_name).concat(".pdf");
        } else if (url.contains("view/myy")) {
            filename = getResources().getString(R.string.form_file_name).concat(".pdf");
        } else if (url.contains("blob")) {
            filename = getResources().getString(R.string.mys_file_name).concat(".zip");
        } else {
            filename = getResources().getString(R.string.mye_file_name).concat(".pdf");
        }

        if (!url.contains("blob")) {
            String cookies = CookieManager.getInstance().getCookie(url);

            DownloadManager.Request downloadRequest = new DownloadManager.Request(Uri.parse(url));
            downloadRequest.addRequestHeader("cookie", cookies);
            downloadRequest.allowScanningByMediaScanner();
            downloadRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            downloadRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
            DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

            try {
                dm.enqueue(downloadRequest);
            } catch (SecurityException e) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_unavailable), Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            String blobURL = JavaScriptInterface.getBase64StringFromBlobUrl(url);
            mWebView.loadUrl(blobURL);
        }

        Toast.makeText(getApplicationContext(), getResources().getString(R.string.download_message), Toast.LENGTH_LONG).show();

        return true;
    } else if (!url.contains(getMetadata(getApplicationContext(), HOSTNAME))) {
        //Navigate to external site outside of webview e.g. Help site
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
        return true;
    } else if(url.contains(getResources().getString(R.string.about_url))) {
        mWebView.loadUrl(url);
        return false;
    } else {
        if (savedInstanceState == null) {
            mWebView.loadUrl(url);
        }
        return false;
    }
}
