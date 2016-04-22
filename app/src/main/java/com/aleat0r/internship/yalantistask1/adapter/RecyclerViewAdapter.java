package com.aleat0r.internship.yalantistask1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleat0r.internship.yalantistask1.R;
import com.aleat0r.internship.yalantistask1.data.Issue;
import com.aleat0r.internship.yalantistask1.Utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Aleksandr Kovalenko on 22.04.2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.IssueViewHolder> {

    private Context mContext;
    private List<Issue> mModel;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewAdapter(Context mContext, List<Issue> model, OnItemClickListener listener) {
        this.mContext = mContext;
        initModel(model);
        mOnItemClickListener = listener;
    }

    private void initModel(Collection<Issue> data) {
        mModel = new ArrayList<>(data.size());
        mModel.addAll(data);
    }

    @Override
    public IssueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.card_list_item, parent, false);
        return new IssueViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IssueViewHolder holder, int position) {

        Issue issue = mModel.get(position);


        holder.categoryTitle.setText(issue.getCategory());
        holder.adrress.setText(issue.getFullText());
        holder.likesCount.setText(String.valueOf(issue.getLikeAmount()));
        holder.categoryIcon.setImageDrawable(mContext.getResources().getDrawable(issue.getIconId()));

        holder.dateCreated.setText(Utils.getFormatter(mContext).format(issue.getCreated()));
        String days = mContext.getResources().getString(R.string.days);
        holder.daysLeft.setText(String.valueOf(issue.getDaysAmount()).concat(" ").concat(days));

    }

    @Override
    public int getItemCount() {
        return mModel.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Issue entity);
    }

    public class IssueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView categoryTitle;
        private TextView adrress;
        private TextView daysLeft;
        private TextView dateCreated;
        private TextView likesCount;
        private ImageView categoryIcon;

        public IssueViewHolder(View itemView) {
            super(itemView);

            categoryTitle = (TextView) itemView.findViewById(R.id.type_text_view);
            categoryIcon = (ImageView) itemView.findViewById(R.id.type_icon_image_view);
            adrress = (TextView) itemView.findViewById(R.id.address_text_view);
            daysLeft = (TextView) itemView.findViewById(R.id.days_left_text_view);
            dateCreated = (TextView) itemView.findViewById(R.id.date_text_view);
            likesCount = (TextView) itemView.findViewById(R.id.likes_text_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(mModel.get(position));
            }
        }
    }
}