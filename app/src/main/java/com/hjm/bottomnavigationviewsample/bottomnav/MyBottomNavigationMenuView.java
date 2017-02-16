package com.hjm.bottomnavigationviewsample.bottomnav;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.design.internal.BottomNavigationMenu;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyBottomNavigationMenuView extends ViewGroup implements MenuView {

    private final int mActiveItemMaxWidth;
    private final int mItemHeight;
    private final OnClickListener mOnClickListener;

    private int mActiveButton = 0;
    private int[] mTempChildWidths;

    private MyBottomNavigationPresenter mPresenter;
    private MenuBuilder mMenu;

    public MyBottomNavigationMenuView(Context context) {
        this(context, null);
    }

    public MyBottomNavigationMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        final Resources res = getResources();

        mActiveItemMaxWidth = res.getDimensionPixelSize(
                android.support.design.R.dimen.design_bottom_navigation_active_item_max_width);
        mItemHeight = res.getDimensionPixelSize(android.support.design.R.dimen.design_bottom_navigation_height);

        // TODO animation

        mOnClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                final MyBottomNavigationItemView itemView = (MyBottomNavigationItemView) v;
                final int itemPosition = itemView.getItemPosition();
                if (!mMenu.performItemAction(itemView.getItemData(), mPresenter, 0)) {
                    activateNewButton(itemPosition);
                }
            }
        };
        mTempChildWidths = new int[BottomNavigationMenu.MAX_ITEM_COUNT];
    }

    @Override
    public void initialize(MenuBuilder menu) {
        mMenu = menu;
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

    public void setPresenter(MyBottomNavigationPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    private void activateNewButton(int newButton) {
        if (mActiveButton == newButton) {
            return;
        }

        // TODO animation

        mMenu.getItem(newButton).setChecked(true);

        mActiveButton = newButton;
    }
}
