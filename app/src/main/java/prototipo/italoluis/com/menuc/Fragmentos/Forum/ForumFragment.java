package prototipo.italoluis.com.menuc.Fragmentos.Forum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import prototipo.italoluis.com.menuc.Fragmentos.Forum.Firebase.FirebaseForumPreviewDataAuth;
import prototipo.italoluis.com.menuc.Fragmentos.Forum.Firebase.FirebaseForumPreviewViewHolder;
import prototipo.italoluis.com.menuc.R;

public class ForumFragment extends Fragment {

    private RecyclerView recyclerViewForum;
    private FloatingActionButton fabPostarForum;
    private ArrayList<FirebaseForumPreviewDataAuth> forumPreviewArrayList;
    private FirebaseRecyclerOptions<FirebaseForumPreviewDataAuth> forumPreviewoptions;
    private FirebaseRecyclerAdapter<FirebaseForumPreviewDataAuth, FirebaseForumPreviewViewHolder> forumPreviewAdapter;
    private DatabaseReference dbForum = FirebaseDatabase.getInstance().getReference().child("Forum");

    public ForumFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
//        forumPreviewAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
//        forumPreviewAdapter.stopListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerViewForum = getView().findViewById(R.id.lista_preview_forum);
        fabPostarForum = getView().findViewById(R.id.add_forum_fab);
        recyclerViewForum.setHasFixedSize(true);
        recyclerViewForum.setLayoutManager(new LinearLayoutManager(getContext()));
        forumPreviewArrayList = new ArrayList<>();
        dbForum.keepSynced(true);
        forumPreviewoptions = new FirebaseRecyclerOptions.Builder<FirebaseForumPreviewDataAuth>().
                setQuery(dbForum.orderByChild("data"), FirebaseForumPreviewDataAuth.class).build();
        recyclerViewForum.setAdapter(forumPreviewAdapter);
    }
}
