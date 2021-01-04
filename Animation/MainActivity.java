mImageGrey = (ImageView) findViewById(R.id.image_grey);
mImageOrange = (ImageView) findViewById(R.id.image_orange);

ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
  @Override
  public void onAnimationUpdate(ValueAnimator animation) {
    mImageOrange.setAlpha((Float) animation.getAnimatedValue());
  }
});

animator.setDuration(1500);
animator.setRepeatMode(ValueAnimator.REVERSE);
animator.setRepeatCount(-1);
animator.start();



public void animateImageView(final ImageView v) {
  final int orange = getResources().getColor(android.R.color.holo_orange_dark);

  final ValueAnimator colorAnim = ObjectAnimator.ofFloat(0f, 1f);
  colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
      float mul = (Float) animation.getAnimatedValue();
      int alphaOrange = adjustAlpha(orange, mul);
      v.setColorFilter(alphaOrange, PorterDuff.Mode.SRC_ATOP);
      if (mul == 0.0) {
        v.setColorFilter(null);
      }
    }
  });

  colorAnim.setDuration(1500);
  colorAnim.setRepeatMode(ValueAnimator.REVERSE);
  colorAnim.setRepeatCount(-1);
  colorAnim.start();

}

public int adjustAlpha(int color, float factor) {
  int alpha = Math.round(Color.alpha(color) * factor);
  int red = Color.red(color);
  int green = Color.green(color);
  int blue = Color.blue(color);
  return Color.argb(alpha, red, green, blue);
}
