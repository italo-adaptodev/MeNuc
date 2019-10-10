package prototipo.italoluis.com.fireprot3.APIs;

import prototipo.italoluis.com.fireprot3.BlogModel.PostList;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class APIBlogger {

  private static final String key = "AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
  private static final String url = "https://www.googleapis.com/blogger/v3/blogs/537701014572510680/posts/?key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";

  public static PostService postService = null;

  public static PostService getService(){

    if(postService == null){

      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(url)
              .addConverterFactory(GsonConverterFactory.create())
              .build();

      postService = retrofit.create(PostService.class);
    }
    return postService;
  }

  public interface PostService {
    @GET
    retrofit2.Call<PostList> getPostList(@Url String url);

  }



}