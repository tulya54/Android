import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import kz.tech.esparta.auth.sign_in_and_reg.view.RegView;
import kz.tech.esparta.auth.sign_in_and_reg.view.SignInView;

public class SignInPagerAdapter extends PagerAdapter {
    private SignInView signInView;
    private RegView regView;
    public SignInPagerAdapter(SignInView signInView, RegView regView) {
        this.signInView = signInView;
        this.regView = regView;
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        switch (position) {
            case 0:
                container.addView(signInView);
                return signInView;
            case 1:
                container.addView(regView);
               return regView;

        }
        return super.instantiateItem(container, position);

    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
