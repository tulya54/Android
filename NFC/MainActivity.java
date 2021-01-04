 AlertDialog.Builder alertbox = new AlertDialog.Builder(getActivity());
                    alertbox.setTitle(getString(R.string.nfc_function));
                    alertbox.setPositiveButton(getString(R.string.Turn_on), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                startActivity(intent);
                            }
                        }
                    });
                    alertbox.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertbox.show();
