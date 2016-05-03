package com.aleat0r.internship.yalantistask1.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Aleksandr Kovalenko on 21.03.2016.
 */

public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int mHorizontalSpaceSize;

    public HorizontalSpaceItemDecoration(int spaceSize) {
        mHorizontalSpaceSize = spaceSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.right = mHorizontalSpaceSize;
    }
}
