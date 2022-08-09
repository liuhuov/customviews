package com.example.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SuperDividerItemDecoration extends RecyclerView.ItemDecoration {

    private static Context context;

    /**
     * 默认分割线的颜色
     */
    private int dividerDefaultColor = 0xFFE1E5E8;

    /**
     * 分割线的颜色
     */
    private int dividerColor;
    /**
     * 分割线的宽度
     */
    private int dividerWidth;
    /**
     * 分割线距离左右两边的距离
     */
    private int dividerPadding;
    /**
     * 分割线距离左边的距离
     */
    private int dividerPaddingLeft;
    /**
     * 分割线距离右边的距离
     */
    private int dividerPaddingRight;

    /**
     * 分割线距离上边的距离
     */
    private int dividerPaddingTop;
    /**
     * 分割线距离下边的距离
     */
    private int dividerPaddingBottom;
    /**
     * 是否显示列表最后一条分割线
     */
    private boolean dividerIsShowLastDivide;


    /**
     * 分割线item的画笔
     */
    private Paint dividerPaint;

    /**
     * 分割线开始的位置（解决recyclerView添加头布局的时候，要从header下边的position位置算起）
     */
    private int dividerFromPosition = 0;


    /**
     * recyclerView布局方式（水平或者垂直）
     */
    private int orientation;

    public SuperDividerItemDecoration(Builder builder) {
        context = builder.context;

        dividerColor = builder.dividerColor == 0 ? dividerDefaultColor : builder.dividerColor;

        dividerWidth = builder.dividerWidth == 0 ? dpToPx(0.5f) : builder.dividerWidth;

        dividerPadding = dpToPx(builder.dividerPadding);

        dividerPaddingLeft = dpToPx(builder.dividerPaddingLeft);

        dividerPaddingRight = dpToPx(builder.dividerPaddingRight);

        dividerPaddingTop = dpToPx(builder.dividerPaddingTop);

        dividerPaddingBottom = dpToPx(builder.dividerPaddingBottom);

        dividerIsShowLastDivide = builder.dividerIsShowLastDivide;

        dividerFromPosition = builder.dividerFromPosition;

        orientation = builder.orientation;

        dividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dividerPaint.setColor(dividerColor);

        if (dividerPadding != 0) {
            dividerPaddingLeft = dividerPaddingRight = dividerPadding;
            dividerPaddingTop = dividerPaddingBottom = dividerPadding;
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int count = parent.getChildCount();

        Log.d("zhao","count: " + count);

        if (count > 0) {
            int showcount = dividerIsShowLastDivide ? count : count - 1;

            for (int i = dividerFromPosition; i < showcount; i++) {
                View view = parent.getChildAt(i);

                int itemBottom =view.getBottom();

                c.drawRect(parent.getPaddingLeft() + dividerPaddingLeft,
                        itemBottom,
                        parent.getWidth() - parent.getPaddingRight() - dividerPaddingRight,
                        itemBottom + dividerWidth,
                        dividerPaint);
            }
        }
    }

    public static class Builder {

        private Context context;
        private int dividerColor;
        private int dividerWidth;
        private int dividerPadding;
        private int dividerPaddingLeft;
        private int dividerPaddingRight;
        private int dividerPaddingTop;
        private int dividerPaddingBottom;
        private boolean dividerIsShowLastDivide;
        private int dividerFromPosition;
        private int orientation;

        public Builder (Context context) {
            this.context = context;
        }

        public Builder setDividerColor (int dividerColor) {
            this.dividerColor = dividerColor;

            return this;
        }

        public Builder setDividerWidth (int dividerWidth) {
            this.dividerWidth = dividerWidth;

            return this;
        }

        public Builder setDividerPadding (int dividerPadding) {
            this.dividerPadding = dividerPadding;

            return this;
        }

        public Builder setDividerPaddingLeft (int dividerPaddingLeft) {
            this.dividerPaddingLeft = dividerPaddingLeft;

            return this;
        }

        public Builder setDividerPaddingRight (int dividerPaddingRight) {
            this.dividerPaddingRight = dividerPaddingRight;

            return this;
        }

        public Builder setDividerPaddingTop (int dividerPaddingTop) {
            this.dividerPaddingTop = dividerPaddingTop;

            return this;
        }

        public Builder setDividerPaddingBottom (int dividerPaddingBottom) {
            this.dividerPaddingBottom = dividerPaddingBottom;

            return this;
        }

        public Builder setDividerIsShowLastDivide (boolean dividerIsShowLastDivide) {
            this.dividerIsShowLastDivide = dividerIsShowLastDivide;

            return this;
        }

        public Builder setDividerFromPosition (int dividerFromPosition) {
            this.dividerFromPosition = dividerFromPosition;

            return this;
        }

        public Builder setOrientation (int orientation) {
            this.orientation = orientation;

            return this;
        }

        public SuperDividerItemDecoration build() {
            return new SuperDividerItemDecoration(this);
        }
    }

    public static int dpToPx (float dp) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
}
