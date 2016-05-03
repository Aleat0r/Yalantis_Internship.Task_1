package com.aleat0r.internship.yalantistask1.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aleat0r.internship.yalantistask1.R;
import com.aleat0r.internship.yalantistask1.activity.MainActivity;
import com.aleat0r.internship.yalantistask1.adapter.ListViewAdapter;
import com.aleat0r.internship.yalantistask1.data.Invoker;
import com.aleat0r.internship.yalantistask1.data.Issue;
import com.aleat0r.internship.yalantistask1.util.Utils;
import com.aleat0r.internship.yalantistask1.data.State;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

/**
 * Created by Aleksandr Kovalenko on 22.04.2016.
 */
public class ListViewFragment extends Fragment {

    private ListViewAdapter mAdapter;
    private Invoker mInvoker;
    private FloatingActionButton mFab;

    public static Fragment getInstance(State state) {
        Fragment fragment = new ListViewFragment();
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

        List<Issue> data = Utils.getModel(getContext(), state);
        mAdapter = new ListViewAdapter(getContext(), data);
        mInvoker = new Invoker(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(mInvoker);

        if (mFab != null) {
            mFab.attachToListView(listView);
        }

        return view;
    }

}

