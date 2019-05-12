package prototipo.italoluis.com.fireprot3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

import prototipo.italoluis.com.fireprot3.postsblog.Item;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private List<Item> items;

    public PostAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public PostViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder postViewHolder, int position) {
        final Item item = items.get(position);
        postViewHolder.titulo.setText(item.getTitle());

        Document document = Jsoup.parse(item.getContent());
        postViewHolder.descricao.setText(document.text());

        Elements elements = document.select("img");
        Glide.with(context).load(elements.get(0).attr("src")).into(postViewHolder.postImage);

        postViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, postDetalhe.class);
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

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Item> list) {
        items.addAll(list);
        notifyDataSetChanged();
    }
}
