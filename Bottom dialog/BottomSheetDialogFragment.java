@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
    BottomSheetDialog bottomSheetDialog=(BottomSheetDialog)super.onCreateDialog(savedInstanceState);
    bottomSheetDialog.setOnShowListener(dialog -> {
        BottomSheetDialog dialogc = (BottomSheetDialog) dialog;
        // When using AndroidX the resource can be found at com.google.android.material.R.id.design_bottom_sheet
        FrameLayout bottomSheet =  dialogc.findViewById(android.support.design.R.id.design_bottom_sheet);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    });
    return bottomSheetDialog;
}


//////////////////////////////////////////////////////////////////////
@NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
      @Override public void onShow(DialogInterface dialogInterface) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
        setupFullHeight(bottomSheetDialog);
      }
    });
    return  dialog;
  }


  private void setupFullHeight(BottomSheetDialog bottomSheetDialog) {
    FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
    BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
    ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();

    int windowHeight = getWindowHeight();
    if (layoutParams != null) {
      layoutParams.height = windowHeight;
    }
    bottomSheet.setLayoutParams(layoutParams);
    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
  }

  private int getWindowHeight() {
    // Calculate window height for fullscreen use
    DisplayMetrics displayMetrics = new DisplayMetrics();
    ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    return displayMetrics.heightPixels;
  }
