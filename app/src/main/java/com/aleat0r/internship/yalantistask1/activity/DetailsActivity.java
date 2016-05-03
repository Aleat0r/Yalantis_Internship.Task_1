package com.aleat0r.internship.yalantistask1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aleat0r.internship.yalantistask1.util.HorizontalSpaceItemDecoration;
import com.aleat0r.internship.yalantistask1.R;
import com.aleat0r.internship.yalantistask1.adapter.GalleryRecyclerAdapter;
import com.aleat0r.internship.yalantistask1.data.Issue;

import java.text.DateFormat;

public class DetailsActivity extends AppCompatActivity {

    private TextView mTvBranch;
    private TextView mTvStatus;
    private TextView mTvCreated;
    private TextView mTvRegistered;
    private TextView mTvSolveUntil;
    private TextView mTvResponsible;
    private TextView mTvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initToolbar();
        initRecyclerView();
        initTextViews();

        Intent intent = getIntent();
        Issue issue = (Issue) intent.getSerializableExtra(getString(R.string.key_for_issue));

        if (issue != null) {
            setData(issue);
        } else {
            finish();
        }
    }

    private void initTextViews() {
        mTvBranch = (TextView) findViewById(R.id.branch_text_view);
        mTvStatus = (TextView) findViewById(R.id.status_text_view);
        mTvCreated = (TextView) findViewById(R.id.created_text_view);
        mTvRegistered = (TextView) findViewById(R.id.registered_text_view);
        mTvSolveUntil = (TextView) findViewById(R.id.solve_until_text_view);
        mTvResponsible = (TextView) findViewById(R.id.responsible_text_view);
        mTvDescription = (TextView) findViewById(R.id.description_text_view);
    }

    private void initToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.request_code));
        }
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_images);
        recyclerView.addItemDecoration(new HorizontalSpaceItemDecoration((int) getResources().getDimension(R.dimen.recycler_view_margin)));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        GalleryRecyclerAdapter adapter = new GalleryRecyclerAdapter(this, getResources().getStringArray(R.array.images));
        recyclerView.setAdapter(adapter);
    }

    public void onClick(View view) {
        String text = view.getClass().getSimpleName();
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setData(Issue issue) {
        initDates(issue);
        mTvBranch.setText(issue.getCategory());
        mTvResponsible.setText(issue.getResponsible());
        mTvDescription.setText(issue.getFullText());

        switch (issue.getState()) {
            case IN_WORK:
                mTvStatus.setText(R.string.status_in_work);
                break;
            case DONE:
                mTvStatus.setText(R.string.status_done);
                break;
            case WAIT:
                mTvStatus.setText(R.string.status_wait);
                break;
        }
    }

    private void initDates(Issue issue) {
        DateFormat dateFormat = android.text.format.DateFormat.getMediumDateFormat(getApplicationContext());
        mTvRegistered.setText(dateFormat.format(issue.getRegistered()));
        mTvCreated.setText(dateFormat.format(issue.getCreated()));
        mTvSolveUntil.setText(dateFormat.format(issue.getDeadline()));
    }
}
