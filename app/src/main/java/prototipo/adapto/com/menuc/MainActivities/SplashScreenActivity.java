package prototipo.adapto.com.menuc.MainActivities;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import prototipo.adapto.com.menuc.R;

public class SplashScreenActivity extends AppCompatActivity {

  private ImageView atom;
  private TextView titulo;

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_splash_screen);
    atom = findViewById(R.id.atom_anim);
    titulo = findViewById(R.id.titulo);
    Handler handler = new Handler();
    animate(atom);
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(SplashScreenActivity.this, CriarLoginActivity.class);
        startActivity(intent);
        finish();

      }
    },2500);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public void animate(View view) {
    ImageView v = (ImageView) view;
    Drawable d = v.getDrawable();
    if (d instanceof AnimatedVectorDrawable) {
      AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;
      avd.start();
    } else if (d instanceof AnimatedVectorDrawableCompat) {
      AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
      avd.start();
    }
  }

}