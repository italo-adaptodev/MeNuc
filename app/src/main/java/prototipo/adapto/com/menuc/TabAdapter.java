package prototipo.adapto.com.menuc;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import prototipo.adapto.com.menuc.Fragmentos.Forum.ForumFragment;
import prototipo.adapto.com.menuc.Fragmentos.MenuPostagemFragment;

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
