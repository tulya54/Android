PopupMenu menu = new PopupMenu(getActivity(), view);
        menu.getMenu().add("Exit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // your code
                return false;
            }
        });
        menu.show();
