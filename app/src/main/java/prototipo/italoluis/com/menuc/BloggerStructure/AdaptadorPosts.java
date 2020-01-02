package prototipo.italoluis.com.menuc.BloggerStructure;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

import prototipo.italoluis.com.menuc.BlogModel.Item;
import prototipo.italoluis.com.menuc.R;

public class AdaptadorPosts extends RecyclerView.Adapter<AdaptadorPosts.PostViewHolder> {

    private Context context;
    private List<Item> items;

    public AdaptadorPosts(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public PostViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_posts, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder postViewHolder, int position) {
        final Item item = items.get(position);
        postViewHolder.titulo.setText(item.getTitle());

        Document document = Jsoup.parse(item.getContent());
        postViewHolder.descricao.setText(document.text());

        Elements elements = document.select("img");
        if(elements.isEmpty()) {
            postViewHolder.postImage.setImageResource(R.drawable.imagem_default);
        }else
            Glide.with(context).load(elements.get(0).attr("src")).into(postViewHolder.postImage);

        postViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesPosts.class);
                intent.putExtra("url", item.getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        ImageView postImage;
        TextView titulo;
        TextView descricao;


        public PostViewHolder( View itemView) {
            super(itemView);
            postImage = itemView.findViewById(R.id.postImagem);
            titulo = itemView.findViewById(R.id.titulo);
            descricao = itemView.findViewById(R.id.descrição);
        }
    }




}
