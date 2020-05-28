package prototipo.italoluis.com.menuc.MainActivities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;

import prototipo.italoluis.com.menuc.R;
import prototipo.italoluis.com.menuc.SlidingTabLayout;
import prototipo.italoluis.com.menuc.TabAdapter;

public class TelaInicialActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("MeNuc");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.tabAccent));
        setSupportActionBar(toolbar);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        viewPager = findViewById(R.id.vp_pagina);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.tabAccent));
        slidingTabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            slidingTabLayout.setOutlineAmbientShadowColor(ContextCompat.getColor(this, R.color.tabAccent));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            slidingTabLayout.setOutlineSpotShadowColor(ContextCompat.getColor(this, R.color.tabAccent));
        }
        slidingTabLayout.setDistributeEvenly(true);

        //Configuração do adapter
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        slidingTabLayout.setViewPager(viewPager);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity(); // or finish();
    }
}
