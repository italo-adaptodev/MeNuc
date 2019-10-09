package prototipo.italoluis.com.fireprot3.APIs;

import prototipo.italoluis.com.fireprot3.BlogModel.PostList;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class APIBloggerBasesFisicas {

    private final static String labelKeyBaseFisica = "labels=BASE+FÍSICA&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
//    private final static String labelKey2
//    private final static String labelKey3
//    private final static String labelKey4
//    private final static String labelKey5
//    private final static String labelKey6
//    private final static String labelKey7
//    private final static String labelKey8

    private final static String url = "https://www.googleapis.com/blogger/v3/blogs/537701014572510680/";

    public static PostServiceBaseFisica postService = null;

    public static PostServiceBaseFisica getService(int op){

        if(postService == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(PostServiceBaseFisica.class);
        }
        return postService;
    }

    public interface PostServiceBaseFisica {
        @GET("posts?"+labelKeyBaseFisica)
        retrofit2.Call<PostList> getPostList();
    }

    // criar varias interfaces "postservices", uma pra cada String label statica (8 no total)
}
