package com.aleat0r.internship.yalantistask1.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import com.aleat0r.internship.yalantistask1.R;
import com.aleat0r.internship.yalantistask1.adapter.IssuesPagerAdapter;
import com.aleat0r.internship.yalantistask1.data.State;
import com.aleat0r.internship.yalantistask1.fragment.ListViewFragment;
import com.aleat0r.internship.yalantistask1.fragment.RecyclerViewFragment;
import com.melnykov.fab.FloatingActionButton;

/**
 * Created by Aleksandr Kovalenko on 21.04.2016.
 */

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private List<Fragment> mFragments;
    private List<String> mFragmentNames;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        initToolbar();
        initDrawer();
        initFragments();
        initViewPager();
        initFab();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.all_appeals);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void initFragments() {
        mFragments = new ArrayList<>(3);
        mFragmentNames = new ArrayList<>(3);
        mFragments.add(RecyclerViewFragment.getInstance(State.IN_WORK));
        mFragmentNames.add(getString(R.string.first_tab));
        mFragments.add(RecyclerViewFragment.getInstance(State.DONE));
        mFragmentNames.add(getString(R.string.second_tab));
        mFragments.add(ListViewFragment.getInstance(State.WAIT));
        mFragmentNames.add(getString(R.string.third_tab));
    }

    private void initViewPager() {
        final ViewPager issuesPager = (ViewPager) findViewById(R.id.issues_view_pager);
        final IssuesPagerAdapter issuesPagerAdapter = new IssuesPagerAdapter(getSupportFragmentManager(), mFragments);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (tabLayout != null && issuesPager != null) {
            for (String fragmentName : mFragmentNames) {
                tabLayout.addTab(tabLayout.newTab().setText(fragmentName));
            }
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    issuesPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });

            issuesPager.setAdapter(issuesPagerAdapter);
            issuesPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        }
    }


    private void initFab() {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
    }

    public FloatingActionButton getFab() {
        return mFab;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
