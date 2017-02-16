package com.hjm.bottomnavigationviewsample.bottomnav;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class MyBottomNavigationMenuView extends ViewGroup implements MenuView {

    public MyBottomNavigationMenuView(Context context) {
        this(context, null);
    }

    public MyBottomNavigationMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPresenter(MyBottomNavigationPresenter presenter) {
    }

    @Override
    public void initialize(MenuBuilder menu) {

    }

    @Override
    public int getWindowAnimations() {
        return 0;
    }

    public void setIconTintList(ColorStateList tint) {
    }

    public void setItemTextColor(ColorStateList color) {
    }

    public void setItemBackgroundRes(int background) {
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
