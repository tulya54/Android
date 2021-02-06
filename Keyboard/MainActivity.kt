/*
    fun hideKeyboard(activity: Activity) {
    val view = activity.currentFocus
    val methodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    assert(view != null)
    methodManager.hideSoftInputFromWindow(view!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}
private fun showKeyboard(activity: Activity) {
    val view = activity.currentFocus
    val methodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    assert(view != null)
    methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}
 override fun hideSoftKeyboard(activity: AppCompatActivity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val f = activity.currentFocus
        if (null != f && null != f.windowToken && EditText::class.java.isAssignableFrom(f.javaClass))
            imm.hideSoftInputFromWindow(f.windowToken, 0)
        else
            activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
    override fun showSoftKeyboard(editText: EditText, activity: AppCompatActivity) {
        val inputMethodManager : InputMethodManager  = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        editText.requestFocus();
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }
     */
