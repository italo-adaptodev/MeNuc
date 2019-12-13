package prototipo.italoluis.com.menuc.MainActivities;

import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import prototipo.italoluis.com.menuc.R;

public class SplashScreenActivity extends AppCompatActivity {

  private AnimatedVectorDrawable atomAnimation;

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);
    atomAnimation = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_anim_atomo);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      startAnimation(atomAnimation);
    }
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(SplashScreenActivity.this, CriarLoginActivity.class);
        startActivity(intent);
        finish();

      }
    },2500);
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  private void startAnimation(AnimatedVectorDrawable atomAnimation) {
    atomAnimation.registerAnimationCallback(new Animatable2.AnimationCallback() {
      @Override
      public void onAnimationStart(Drawable drawable) {
        super.onAnimationStart(drawable);
      }
    });
    atomAnimation.start();

  }
}