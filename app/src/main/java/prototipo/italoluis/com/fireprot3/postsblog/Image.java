
package prototipo.italoluis.com.fireprot3.postsblog;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

  @SerializedName("url")
  @Expose
  private String url;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}