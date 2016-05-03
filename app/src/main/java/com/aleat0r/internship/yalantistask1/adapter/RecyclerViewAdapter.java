package com.aleat0r.internship.yalantistask1.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleat0r.internship.yalantistask1.R;
import com.aleat0r.internship.yalantistask1.data.Issue;
import com.aleat0r.internship.yalantistask1.util.Utils;

import java.util.List;

/**
 * Created by Aleksandr Kovalenko on 22.04.2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.IssueViewHolder> {

    private Context mContext;
    private List<Issue> mModelsList;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewAdapter(Context context, List<Issue> modelsList, OnItemClickListener listener) {
        mContext = context;
        mModelsList = modelsList;
        mOnItemClickListener = listener;
    }

    @Override
    public IssueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.card_list_item, parent, false);
        return new IssueViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IssueViewHolder holder, int position) {

        Issue issue = mModelsList.get(position);

        holder.mTvCategoryTitle.setText(issue.getCategory());
        holder.mTvDescription.setText(issue.getFullText());
        holder.mTvLikesCount.setText(String.valueOf(issue.getLikeAmount()));
        holder.mIvCategoryIcon.setImageDrawable(ContextCompat.getDrawable(mContext, issue.getIconId()));

        holder.mTvDateCreated.setText(Utils.getFormatter(mContext).format(issue.getCreated()));
        String days = mContext.getResources().getString(R.string.days);
        holder.mTvDaysLeft.setText(String.valueOf(issue.getDaysAmount()).concat(" ").concat(days));
    }

    @Override
    public int getItemCount() {
        return mModelsList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Issue entity);
    }

    public class IssueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTvCategoryTitle;
        private TextView mTvDescription;
        private TextView mTvDaysLeft;
        private TextView mTvDateCreated;
        private TextView mTvLikesCount;
        private ImageView mIvCategoryIcon;

        public IssueViewHolder(View itemView) {
            super(itemView);

            mTvCategoryTitle = (TextView) itemView.findViewById(R.id.type_text_view);
            mIvCategoryIcon = (ImageView) itemView.findViewById(R.id.type_icon_image_view);
            mTvDescription = (TextView) itemView.findViewById(R.id.description_text_view);
            mTvDaysLeft = (TextView) itemView.findViewById(R.id.days_left_text_view);
            mTvDateCreated = (TextView) itemView.findViewById(R.id.date_text_view);
            mTvLikesCount = (TextView) itemView.findViewById(R.id.likes_text_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(mModelsList.get(position));
            }
        }
    }
}