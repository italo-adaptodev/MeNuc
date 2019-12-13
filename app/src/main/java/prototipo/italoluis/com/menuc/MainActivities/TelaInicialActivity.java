package prototipo.italoluis.com.menuc.MainActivities;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;

import prototipo.italoluis.com.menuc.R;
import prototipo.italoluis.com.menuc.SlidingTabLayout;
import prototipo.italoluis.com.menuc.TabAdapter;

public class TelaInicialActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("MeNuc");
        setSupportActionBar(toolbar);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        viewPager = findViewById(R.id.vp_pagina);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.tabAccent));
        slidingTabLayout.setDistributeEvenly(true);



        //Configuração do adapter
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        slidingTabLayout.setViewPager(viewPager);


    }
}
