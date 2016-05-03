package com.aleat0r.internship.yalantistask1.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleat0r.internship.yalantistask1.R;
import com.aleat0r.internship.yalantistask1.data.Issue;
import com.aleat0r.internship.yalantistask1.util.Utils;

import java.util.List;

/**
 * Created by Aleksandr Kovalenko on 22.04.2016.
 */
public class ListViewAdapter extends BaseAdapter {

    private List<Issue> mModelsList;
    private Context mContext;

    public ListViewAdapter(Context context, List<Issue> modelsList) {
        mContext = context;
        mModelsList = modelsList;
    }

    @Override
    public int getCount() {
        return mModelsList.size();
    }

    @Override
    public Issue getItem(int position) {
        return mModelsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mModelsList.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IssueViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.card_list_item, parent, false);
            holder = new IssueViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (IssueViewHolder) convertView.getTag();
        }

        Issue issue = mModelsList.get(position);

        holder.mTvCategoryTitle.setText(issue.getCategory());
        holder.mTvDescription.setText(issue.getFullText());
        holder.mTvLikesCount.setText(String.valueOf(issue.getLikeAmount()));
        holder.mIvCategoryIcon.setImageDrawable(ContextCompat.getDrawable(mContext, issue.getIconId()));

        holder.mTvDateCreated.setText(Utils.getFormatter(mContext).format(issue.getCreated()));
        String days = mContext.getResources().getString(R.string.days);
        holder.mTvDaysLeft.setText(String.valueOf(issue.getDaysAmount()).concat(" ").concat(days));

        return convertView;
    }

    private class IssueViewHolder {

        private TextView mTvCategoryTitle;
        private TextView mTvDescription;
        private TextView mTvDaysLeft;
        private TextView mTvDateCreated;
        private TextView mTvLikesCount;
        private ImageView mIvCategoryIcon;

        public IssueViewHolder(View itemView) {
            mTvCategoryTitle = (TextView) itemView.findViewById(R.id.type_text_view);
            mIvCategoryIcon = (ImageView) itemView.findViewById(R.id.type_icon_image_view);
            mTvDescription = (TextView) itemView.findViewById(R.id.description_text_view);
            mTvDaysLeft = (TextView) itemView.findViewById(R.id.days_left_text_view);
            mTvDateCreated = (TextView) itemView.findViewById(R.id.date_text_view);
            mTvLikesCount = (TextView) itemView.findViewById(R.id.likes_text_view);
        }
    }
}
