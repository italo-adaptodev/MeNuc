package prototipo.italoluis.com.menuc.Fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import prototipo.italoluis.com.menuc.R;

public class ForumFragment extends Fragment {

    public ForumFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forum, container, false);
        return view;
    }
}
