package ir.ac.iut.amiiir.flickergallery;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amiiir on 7/3/17.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.PhotoHolder> {
    ArrayList<Photo> mPhotos;
    private Context mContext;

    public GalleryAdapter(List<Photo> photos) {
        mPhotos = (ArrayList<Photo>) photos;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.gallery_item, parent, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        holder.bindPhoto(mPhotos.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    class PhotoHolder extends RecyclerView.ViewHolder {

        ImageButton mPhotoItem;
        TextView mTextView;
        public PhotoHolder(View itemView) {
            super(itemView);
            mPhotoItem = itemView.findViewById(R.id.ib_photo);
            mTextView = itemView.findViewById(R.id.txtv_title);


        }

        public void bindPhoto(Photo photo, Context context) {
            Picasso.with(context)
                    .load(photo.getUrl())
                    .resize(getScreenWidth()/2, getScreenHeight()/2)
                    .into(mPhotoItem);

            mPhotoItem.setImageResource(R.mipmap.ic_launcher);
            mTextView.setText(photo.getTitle());
        }

        public int getScreenWidth() {
            return Resources.getSystem().getDisplayMetrics().widthPixels;
        }

        public int getScreenHeight() {
            return Resources.getSystem().getDisplayMetrics().heightPixels;
        }
    }
}
