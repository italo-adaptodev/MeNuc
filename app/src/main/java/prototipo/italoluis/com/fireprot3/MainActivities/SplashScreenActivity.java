package prototipo.italoluis.com.fireprot3.MainActivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import prototipo.italoluis.com.fireprot3.R;

public class SplashScreenActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);

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
}