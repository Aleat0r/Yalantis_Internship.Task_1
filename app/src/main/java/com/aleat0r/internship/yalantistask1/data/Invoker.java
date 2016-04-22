package com.aleat0r.internship.yalantistask1.data;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.aleat0r.internship.yalantistask1.R;
import com.aleat0r.internship.yalantistask1.activity.DetailsActivity;
import com.aleat0r.internship.yalantistask1.adapter.RecyclerViewAdapter;

/**
 * Created by Aleksandr Kovalenko on 22.04.2016.
 */
public class Invoker implements RecyclerViewAdapter.OnItemClickListener, AdapterView.OnItemClickListener {

    private Context mContext;

    public Invoker(Context context) {
        this.mContext = context;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Issue issue = (Issue) parent.getAdapter().getItem(position);
        invoke(issue);
    }

    @Override
    public void onItemClick(Issue issue) {
        invoke(issue);
    }

    private void invoke(Issue issue) {
        Intent intent = new Intent(mContext, DetailsActivity.class);
        intent.putExtra(mContext.getString(R.string.key_for_issue), issue);
        mContext.startActivity(intent);
    }
}