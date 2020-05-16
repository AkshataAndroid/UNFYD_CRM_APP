package com.smartconnect.unfyd_crm_app.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.Vector;

public class TaskAdapter extends PagerAdapter {

    private Context mContext;
    private Vector<View> pages;
    String [] titlesArray =
            new String []{
                    "CurrentTask",
                    "PendingTask"

            };

    public TaskAdapter(Context context, Vector<View> pages) {
        this.mContext=context;
        this.pages=pages;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View page = pages.get(position);
        container.addView(page);
        return page;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
//    @Override
//    public CharSequence getPageTitle(int position) {
//
//        return "Current Task" + ( position + 1 );
//    }

}