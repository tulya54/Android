 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//19
            this.evaluateJavascript("window.localStorage.setItem('$keyToken','$valueToken');", null)
            this.evaluateJavascript("window.localStorage.setItem('$keyLanguage','$valueLanguage');", null)
        } else {
            this.loadUrl("javascript:localStorage.setItem('$keyToken','$valueToken');")
            this.loadUrl("javascript:localStorage.setItem('$keyLanguage','$valueLanguage');")
        }
