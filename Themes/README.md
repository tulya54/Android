You might be using targetSdkVersion 30

Solution: change the theme.xml style from

<style name="AppTheme" parent="Theme.MaterialComponents.DayNight.NoActionBar">
to

<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
