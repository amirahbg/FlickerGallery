package ir.ac.iut.amiiir.flickergallery;

import java.util.UUID;

/**
 * Created by amiiir on 7/3/17.
 */

public class Photo {
    private UUID mId;
    private String mTitle;
    private String mUrl;

    public Photo() {
        mId = UUID.randomUUID();
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public UUID getId() {
        return mId;
    }
}
