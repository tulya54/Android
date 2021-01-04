LayoutInflater iLayoutInflater = getActivity().getLayoutInflater();
        final DatePicker datePicker = (DatePicker)iLayoutInflater.inflate( R.layout.data_picker, null );


 datePicker.init(2000, 12, 30, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dataPicker = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
            }
        });
        
        
        <?xml version="1.0" encoding="utf-8"?>
<DatePicker xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:calendarViewShown="false"
    android:spinnersShown="true"
    android:datePickerMode="spinner"/>
