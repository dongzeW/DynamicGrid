package org.askerov.dynamicgid;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: alex askerov
 * Date: 9/7/13
 * Time: 10:49 PM
 */
public abstract class BaseDynamicGridAdapter extends AbstractDynamicGridAdapter {
    private Context mContext;

    private ArrayList<Object> mItems = new ArrayList<Object>();
    private int mColumnsCount;

    protected BaseDynamicGridAdapter(Context context, int columnCount) {
        this.mContext = context;
        this.mColumnsCount = columnCount;
    }

    public BaseDynamicGridAdapter(Context context, List<?> items, int columnCount) {
        mContext = context;
        mColumnsCount = columnCount;
        init(items);
    }

    private void init(List<?> items) {
        addAllStableId(items);
        this.mItems.addAll(items);
    }


    public void set(List<?> items) {
        clear();
        init(items);
        notifyDataSetChanged();
    }

    public void clear() {
        clearStableIdMap();
        mItems.clear();
        notifyDataSetChanged();
    }

    public void add(Object item) {
        addStableId(item);
        mItems.add(item);
        notifyDataSetChanged();
    }


    public void add(List<?> items) {
        addAllStableId(items);
        this.mItems.addAll(items);
        notifyDataSetChanged();
    }


    public void remove(Object item) {
        mItems.remove(item);
        removeStableID(item);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getColumnCount() {
        return mColumnsCount;
    }

    @Override
    public void reorderItems(int originalPosition, int newPosition) {
        DynamicGridUtils.reorder(mItems, originalPosition, newPosition);
        notifyDataSetChanged();
    }

    public List getItems() {
        return mItems;
    }

    protected Context getContext() {
        return mContext;
    }
}
