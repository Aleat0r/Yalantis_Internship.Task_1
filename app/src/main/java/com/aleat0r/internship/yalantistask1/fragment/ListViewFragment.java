package com.aleat0r.internship.yalantistask1.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aleat0r.internship.yalantistask1.R;
import com.aleat0r.internship.yalantistask1.adapter.ListViewAdapter;
import com.aleat0r.internship.yalantistask1.data.Invoker;
import com.aleat0r.internship.yalantistask1.data.Issue;
import com.aleat0r.internship.yalantistask1.Utils.Utils;
import com.aleat0r.internship.yalantistask1.data.State;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

/**
 * Created by Aleksandr Kovalenko on 22.04.2016.
 */
public class ListViewFragment extends Fragment {

    private ListView mListView;
    private ListViewAdapter mAdapter;

    private Invoker mInvoker;

    public static Fragment getInstance(State state) {
        Fragment fragment = new ListViewFragment();
        Bundle params = new Bundle();
        params.putInt(Utils.KEY_STATE, state.getValue());
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        State state = State.WAIT;
        Bundle params = getArguments();
        if (params != null) {
            state = State.getByValue(params.getInt(Utils.KEY_STATE, -1));
        }

        List<Issue> data = Utils.getModel(getContext(), state);
        mAdapter = new ListViewAdapter(getContext(), data);
        mInvoker = new Invoker(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_view, container, false);
        mListView = (ListView) v.findViewById(R.id.list_view);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(mInvoker);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.attachToListView(mListView);
        return v;
    }

}

