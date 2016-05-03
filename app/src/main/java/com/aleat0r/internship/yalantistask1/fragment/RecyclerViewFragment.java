package com.aleat0r.internship.yalantistask1.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleat0r.internship.yalantistask1.R;
import com.aleat0r.internship.yalantistask1.activity.MainActivity;
import com.aleat0r.internship.yalantistask1.adapter.RecyclerViewAdapter;
import com.aleat0r.internship.yalantistask1.data.Invoker;
import com.aleat0r.internship.yalantistask1.data.Issue;
import com.aleat0r.internship.yalantistask1.data.State;
import com.aleat0r.internship.yalantistask1.util.Utils;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

/**
 * Created by Aleksandr Kovalenko on 22.04.2016.
 */
public class RecyclerViewFragment extends Fragment {

    private RecyclerViewAdapter mAdapter;
    private FloatingActionButton mFab;

    public static Fragment getInstance(State state) {
        Fragment fragment = new RecyclerViewFragment();
        Bundle params = new Bundle();
        params.putInt(Utils.KEY_STATE, state.getValue());
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof MainActivity) {
            mFab = ((MainActivity) getActivity()).getFab();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        State state = State.WAIT;
        Bundle params = getArguments();
        if (params != null) {
            state = State.getByValue(params.getInt(Utils.KEY_STATE, -1));
        }

        List<Issue> issueList = Utils.getModel(getContext(), state);
        mAdapter = new RecyclerViewAdapter(getContext(), issueList, new Invoker(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        if (mFab != null) {
            mFab.attachToRecyclerView(recyclerView);
        }

        return view;
    }
}
