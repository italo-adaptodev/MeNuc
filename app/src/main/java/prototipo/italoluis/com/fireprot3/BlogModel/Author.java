package prototipo.italoluis.com.fireprot3.BlogModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("id")
    @Expose
    private String id;

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
