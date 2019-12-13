package prototipo.italoluis.com.menuc;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import prototipo.italoluis.com.menuc.Fragmentos.ForumFragment;
import prototipo.italoluis.com.menuc.Fragmentos.MenuPostagemFragment;

public class TabAdapter extends FragmentStatePagerAdapter {

    private String[] tituloAbas = {"POSTAGENS", "FÓRUM"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i){
            case 0:
                fragment = new MenuPostagemFragment();
                break;
            case 1:
                fragment = new ForumFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tituloAbas.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tituloAbas[position];
    }
}
