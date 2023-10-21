package to.nishant.shoppin;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.IntRange;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewMargin extends RecyclerView.ItemDecoration {
    private int columns;
    private int margin;

    public RecyclerViewMargin(@IntRange(from=0)int margin ,@IntRange(from=0) int columns ) {
        this.margin = margin;
        this.columns=columns;

    }
    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildLayoutPosition(view);
        //we only add top margin to the first row
        //add left margin only to the first column
        if(position%columns==0){
            outRect.top = margin;
        }
        if(position%columns==0){
            outRect.left = 15;
        }



    }
}
