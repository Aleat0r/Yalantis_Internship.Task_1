package com.aleat0r.internship.yalantistask1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initRecyclerView();
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

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, getResources().getStringArray(R.array.images));
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
}
