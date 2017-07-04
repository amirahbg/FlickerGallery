package ir.ac.iut.amiiir.flickergallery;

import android.os.AsyncTask;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mGalleryTextView;
    private ArrayList<Photo> mPhotos;
    RecyclerView mGalleryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGalleryRecyclerView = (RecyclerView) findViewById(R.id.rv_gallery);
        mGalleryRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        new FlickerTask().execute();

    }

    private class FlickerTask extends AsyncTask<Void, Void, List<Photo>> {

        @Override
        protected List<Photo> doInBackground(Void... voids) {
            JSONObject jsonObject = new FlickerClient().getListOfPictures();
            return extractPhotos(jsonObject);
        }

        private ArrayList<Photo> extractPhotos(JSONObject jsonObject) {
            ArrayList<Photo> photos = new ArrayList<>();
            try {
                JSONArray jsonArray = jsonObject.getJSONObject("photos").getJSONArray("photo");
                for (int i = 0; i < jsonArray.length(); i++) {
                    Photo photo = new Photo();
                    photo.setTitle(jsonArray.getJSONObject(i).getString("title"));
                    photo.setUrl(jsonArray.getJSONObject(i).getString("url_m"));
                    photos.add(photo);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return photos;
        }

        @Override
        protected void onPostExecute(List<Photo> photos) {
            super.onPostExecute(photos);
            mPhotos = (ArrayList<Photo>) photos;
            mGalleryRecyclerView.setAdapter(new GalleryAdapter(mPhotos));
        }
    }

}
