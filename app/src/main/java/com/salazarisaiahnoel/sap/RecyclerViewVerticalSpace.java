package com.salazarisaiahnoel.sap;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewVerticalSpace extends RecyclerView.ItemDecoration {
    private final int verticalSpaceHeight;

    public RecyclerViewVerticalSpace(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.bottom = verticalSpaceHeight;
            outRect.top = verticalSpaceHeight * 2;
        } else if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = verticalSpaceHeight * 2;
            outRect.top = verticalSpaceHeight;
        } else {
            outRect.bottom = verticalSpaceHeight;
            outRect.top = verticalSpaceHeight;
        }
    }
}
