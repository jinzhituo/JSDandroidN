package jsjh.king.com.jsdandroidn.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import java.util.List;

import jsjh.king.com.jsdandroidn.model.bean.AddressBookBean;
import jsjh.king.com.jsdandroidn.utils.DpUtil;

/**
 * Created by MQ on 2017/5/8.
 */

public class CustomItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private List<AddressBookBean> mBeans;
    private static final int dividerHeight = 80;
    private Context mContext;
    private final Rect mBounds = new Rect();
    private String tagsStr;

    public void setDatas(List<AddressBookBean> mBeans, String tagsStr) {
        this.mBeans = mBeans;
        this.tagsStr = tagsStr;
    }


    public CustomItemDecoration(Context mContext) {
        this.mContext = mContext;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }


    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null) {
            return;
        }
        canvas.save();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = params.getViewLayoutPosition();
            if (mBeans == null || mBeans.size() == 0 || mBeans.size() <= position || position < 0) {
                continue;
            }
            if (position == 0) {
                //第一条数据有bar
                drawTitleBar(canvas, parent, child, mBeans.get(position), tagsStr.indexOf(mBeans.get(position).getIndexTag()));
            } else if (position > 0) {
                if (TextUtils.isEmpty(mBeans.get(position).getIndexTag())) continue;
                //与上一条数据中的tag不同时，该显示bar了
                if (!mBeans.get(position).getIndexTag().equals(mBeans.get(position - 1).getIndexTag())) {
                    drawTitleBar(canvas, parent, child, mBeans.get(position), tagsStr.indexOf(mBeans.get(position).getIndexTag()));
                }
            }
        }
        canvas.restore();
    }

    /**
     * 绘制bar
     *
     * @param canvas Canvas
     * @param parent RecyclerView
     * @param child  ItemView
     */
    private void drawTitleBar(Canvas canvas, RecyclerView parent, View child, AddressBookBean bean, int position) {
        final int left = 0;
        final int right = parent.getWidth();
        //返回一个包含Decoration和Margin在内的Rect
        parent.getDecoratedBoundsWithMargins(child, mBounds);
        final int top = mBounds.top;
        final int bottom = mBounds.top + Math.round(ViewCompat.getTranslationY(child)) + dividerHeight;
        mPaint.setColor(Color.parseColor("#F7F7F7"));
        canvas.drawRect(left, top, right, bottom, mPaint);
        //根据位置不断变换Paint的颜色
//        ColorUtil.setPaintColor(mPaint, position);
        mPaint.setTextSize(40);
        canvas.drawCircle(DpUtil.dp2px(mContext, 42.5f), bottom - dividerHeight / 2, 35, mPaint);
        mPaint.setColor(Color.parseColor("#999999"));
        canvas.drawText(bean.getIndexTag(), DpUtil.dp2px(mContext, 24.5f), bottom - dividerHeight / 3, mPaint);
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        //用来绘制悬浮框
        int position = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
        if (mBeans == null || mBeans.size() == 0 || mBeans.size() <= position || position < 0) {
            return;
        }
        final int bottom = parent.getPaddingTop() + dividerHeight;
        mPaint.setColor(Color.parseColor("#F7F7F7"));
        canvas.drawRect(parent.getLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + dividerHeight, mPaint);
//        ColorUtil.setPaintColor(mPaint, tagsStr.indexOf(mBeans.get(position).getIndexTag()));
        mPaint.setTextSize(40);
        canvas.drawCircle(DpUtil.dp2px(mContext, 42.5f), bottom - dividerHeight / 2, 35, mPaint);
        mPaint.setColor(Color.parseColor("#999999"));
        canvas.drawText(mBeans.get(position).getIndexTag(), DpUtil.dp2px(mContext, 24.5f), bottom - dividerHeight / 3, mPaint);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (mBeans == null || mBeans.size() == 0 || mBeans.size() <= position || position < 0) {
            super.getItemOffsets(outRect, view, parent, state);
            return;
        }
        if (position == 0) {
            //第一条数据有bar
            outRect.set(0, dividerHeight, 0, 0);
        } else if (position > 0) {
            if (TextUtils.isEmpty(mBeans.get(position).getIndexTag())) return;
            //与上一条数据中的tag不同时，该显示bar了
            if (!mBeans.get(position).getIndexTag().equals(mBeans.get(position - 1).getIndexTag())) {
                outRect.set(0, dividerHeight, 0, 0);
            }
        }
    }

}
