package adapto.com.menuc.Fragmentos.Forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import adapto.com.menuc.Fragmentos.Forum.Firebase.FirebaseForumPreviewDataAuth;
import adapto.com.menuc.Fragmentos.Forum.Firebase.FirebaseForumPreviewViewHolder;
import adapto.com.menuc.MainActivities.CriarPostagemForumActivity;
import adapto.com.menuc.MainActivities.DetalharPostagemForumActivity;
import adapto.com.menuc.R;

public class ForumFragment extends Fragment {

    private FirebaseRecyclerAdapter<FirebaseForumPreviewDataAuth, FirebaseForumPreviewViewHolder> forumPreviewAdapter;
    //private Query dbForum = FirebaseDatabase.getInstance().getReference().child("Forum-Teste");
    private Query dbForum = FirebaseDatabase.getInstance().getReference().child("Forum");
    private DatabaseReference dbRespostas = FirebaseDatabase.getInstance().getReference().child("Respostas");
    RecyclerView recyclerViewForum;
    FloatingActionButton fabPostarForum;
    ArrayList<FirebaseForumPreviewDataAuth> forumPreviewArrayList;
    FirebaseRecyclerOptions<FirebaseForumPreviewDataAuth> forumPreviewoptions;
    String key;

    public ForumFragment() {
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
        recyclerViewForum = getView().findViewById(R.id.lista_forum_preview);
        fabPostarForum = getView().findViewById(R.id.add_forum_fab);
        recyclerViewForum.setHasFixedSize(true);
        recyclerViewForum.setLayoutManager(new LinearLayoutManager(getContext()));
        forumPreviewArrayList = new ArrayList<>();
        dbForum.keepSynced(true);
        forumPreviewoptions = new FirebaseRecyclerOptions.Builder<FirebaseForumPreviewDataAuth>().
                setQuery(dbForum.orderByKey(), FirebaseForumPreviewDataAuth.class)
                .setLifecycleOwner(this)
                .build();
        createAdapter();
        recyclerViewForum.setAdapter(forumPreviewAdapter);
        fabPostarForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CriarPostagemForumActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createAdapter() {
        forumPreviewAdapter = new FirebaseRecyclerAdapter<FirebaseForumPreviewDataAuth, FirebaseForumPreviewViewHolder>(forumPreviewoptions) {
            @Override
            protected void onBindViewHolder(@NonNull final FirebaseForumPreviewViewHolder holder, final int position, @NonNull final FirebaseForumPreviewDataAuth model) {
                holder.forum_preview_autor.setText("Por: " + model.getfAutor());
                holder.forum_preview_qtdRespostas.setText(model.getfQtdComentarios() + "");
                holder.forum_preview_data.setText(model.getfDataPostagem());
                holder.forum_preview_titulo.setText(model.getfTitulo());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), DetalharPostagemForumActivity.class);
                        intent.putExtra("postagemKey", model.getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public FirebaseForumPreviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseForumPreviewViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.cardview_preview_forum, parent,false));
            }
        };
    }

}
