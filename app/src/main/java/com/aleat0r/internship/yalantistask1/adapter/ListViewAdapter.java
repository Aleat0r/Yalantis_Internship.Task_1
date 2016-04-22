package com.aleat0r.internship.yalantistask1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleat0r.internship.yalantistask1.R;
import com.aleat0r.internship.yalantistask1.data.Issue;
import com.aleat0r.internship.yalantistask1.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr Kovalenko on 22.04.2016.
 */
public class ListViewAdapter extends BaseAdapter {

    private List<Issue> mModel;
    private Context mContext;

    public ListViewAdapter(Context context, List<Issue> model) {
        mContext = context;
        this.mModel = new ArrayList<>();
        if (model != null) {
            this.mModel.addAll(model);
        }
    }

    @Override
    public int getCount() {
        return mModel.size();
    }

    @Override
    public Issue getItem(int position) {
        return mModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mModel.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        IssueViewHolder holder;

        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(R.layout.card_list_item, parent, false);
            holder = new IssueViewHolder(v);
        } else {
            holder = (IssueViewHolder) v.getTag();
        }

        Issue issue = mModel.get(position);

        holder.categoryTitle.setText(issue.getCategory());
        holder.adrress.setText(issue.getFullText());
        holder.likesCount.setText(String.valueOf(issue.getLikeAmount()));
        holder.categoryIcon.setImageDrawable(mContext.getResources().getDrawable(issue.getIconId()));

        holder.dateCreated.setText(Utils.getFormatter(mContext).format(issue.getCreated()));
        String days = mContext.getResources().getString(R.string.days);
        holder.daysLeft.setText(String.valueOf(issue.getDaysAmount()).concat(" ").concat(days));

        v.setTag(holder);
        return v;
    }

    private class IssueViewHolder {

        private TextView categoryTitle;
        private TextView adrress;
        private TextView daysLeft;
        private TextView dateCreated;
        private TextView likesCount;
        private ImageView categoryIcon;

        public IssueViewHolder(View itemView) {
            categoryTitle = (TextView) itemView.findViewById(R.id.type_text_view);
            categoryIcon = (ImageView) itemView.findViewById(R.id.type_icon_image_view);
            adrress = (TextView) itemView.findViewById(R.id.address_text_view);
            daysLeft = (TextView) itemView.findViewById(R.id.days_left_text_view);
            dateCreated = (TextView) itemView.findViewById(R.id.date_text_view);
            likesCount = (TextView) itemView.findViewById(R.id.likes_text_view);
        }
    }
}
