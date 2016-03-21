package com.aleat0r.internship.yalantistask1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by Aleksandr Kovalenko on 21.03.2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ImageViewHolder> {

    private Context mContext;
    private String[] mImages;
    private static final String IMAGES_ASSETS_URL = "file:///android_asset/animals/";

    public RecyclerViewAdapter(Context context, String[] images) {
        mContext = context;
        mImages = images;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Picasso.with(mContext)
                .load(IMAGES_ASSETS_URL + mImages[position])
                .placeholder(R.mipmap.ic_launcher)
                .centerInside()
                .fit()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mImages.length;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public ImageViewHolder(final View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = itemView.getClass().getSimpleName() + "\n" + (mImages[getAdapterPosition()]);
                    Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
