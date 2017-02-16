package com.hjm.bottomnavigationviewsample.bottomnav;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.v4.util.Pools;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.hjm.bottomnavigationviewsample.R;

public class MyBottomNavigationMenuView extends ViewGroup implements MenuView {

    private final int mActiveItemMaxWidth;
    private final int mItemHeight;
    private final OnClickListener mOnClickListener;
    private static final Pools.Pool<MyBottomNavigationItemView> sItemPool =
            new Pools.SynchronizedPool<>(5);

    private MyBottomNavigationItemView[] mButtons;
    private int mActiveButton = 0;
    private ColorStateList mItemIconTint;
    private ColorStateList mItemTextColor;
    private int mItemBackgroundRes;
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
                R.dimen.design_bottom_navigation_active_item_max_width);
        mItemHeight = res.getDimensionPixelSize(R.dimen.design_bottom_navigation_height);

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
        mTempChildWidths = new int[MyBottomNavigationMenu.MAX_ITEM_COUNT];
    }

    @Override
    public void initialize(MenuBuilder menu) {
        mMenu = menu;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = MeasureSpec.getSize(widthMeasureSpec);
        final int count = getChildCount();

        final int heightSpec = MeasureSpec.makeMeasureSpec(mItemHeight, MeasureSpec.EXACTLY);

        // In this implementation, there is no shifting mode.
        final int maxAvailable = width / (count == 0 ? 1 : count);
        final int childWidth = Math.min(maxAvailable, mActiveItemMaxWidth);
        int extra = width - childWidth * count;
        // what is purpose of this looping?
        for (int i = 0; i < count; i++) {
            mTempChildWidths[i] = childWidth;
            if (extra > 0) {
                mTempChildWidths[i]++;
                extra--;
            }
        }

        int totalWidth = 0;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            child.measure(MeasureSpec.makeMeasureSpec(mTempChildWidths[i], MeasureSpec.EXACTLY), heightSpec);
            ViewGroup.LayoutParams params = child.getLayoutParams();
            params.width = child.getMeasuredWidth();
            totalWidth += child.getMeasuredWidth();
        }

        setMeasuredDimension(
                ViewCompat.resolveSizeAndState(totalWidth,
                        MeasureSpec.makeMeasureSpec(totalWidth, MeasureSpec.EXACTLY), 0),
                ViewCompat.resolveSizeAndState(mItemHeight, heightSpec, 0));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        final int width = right - left;
        final int height = bottom - top;
        int used = 0;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            if (ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                child.layout(width - used - child.getMeasuredWidth(), 0, width - used, height);
            } else {
                child.layout(used, 0, child.getMeasuredWidth() + used, height);
            }
            used += child.getMeasuredWidth();
        }
    }

    @Override
    public int getWindowAnimations() {
        return 0;
    }

    public void setIconTintList(ColorStateList tint) {
        mItemIconTint = tint;
        if (mButtons == null) {
            return;
        }
        for (MyBottomNavigationItemView item : mButtons) {
            item.setIconTintList(tint);
        }
    }

    public void setItemTextColor(ColorStateList color) {
        mItemTextColor = color;
        if (mButtons == null) {
            return;
        }
        for (MyBottomNavigationItemView item : mButtons) {
            item.setTextColor(color);
        }
    }

    public void setItemBackgroundRes(int background) {
        mItemBackgroundRes = background;
        if (mButtons == null) {
            return;
        }
        for (MyBottomNavigationItemView item : mButtons) {
            item.setItemBackground(background);
        }
    }

    public void setPresenter(MyBottomNavigationPresenter presenter) {
        mPresenter = presenter;
    }

    public void buildMenuView() {
        if (mButtons != null) {
            for (MyBottomNavigationItemView item : mButtons) {
                sItemPool.release(item);
            }
        }
        removeAllViews();
        if (mMenu.size() == 0) {
            mButtons = null;
            return;
        }
        mButtons = new MyBottomNavigationItemView[mMenu.size()];
        // In this implementation, there is no shifting mode.
        for (int i = 0; i < mMenu.size(); i++) {
            mPresenter.setUpdateSuspended(true);
            mMenu.getItem(i).setCheckable(true);
            mPresenter.setUpdateSuspended(false);
            MyBottomNavigationItemView child = getNewItem();
            mButtons[i] = child;
            child.setIconTintList(mItemIconTint);
            child.setTextColor(mItemTextColor);
            child.setItemBackground(mItemBackgroundRes);
            child.initialize((MenuItemImpl) mMenu.getItem(i), 0);
            child.setItemPosition(i);
            child.setOnClickListener(mOnClickListener);
            addView(child);
        }
        mActiveButton = Math.min(mMenu.size() - 1, mActiveButton);
        mMenu.getItem(mActiveButton).setChecked(true);
    }

    public void updateMenuView() {
        final int menuSize = mMenu.size();
        if (menuSize != mButtons.length) {
            buildMenuView();
            return;
        }

        for (int i = 0; i < menuSize; i++) {
            mPresenter.setUpdateSuspended(true);
            if (mMenu.getItem(i).isChecked()) {
                mActiveButton = i;
            }
            mButtons[i].initialize((MenuItemImpl) mMenu.getItem(i), 0);
            mPresenter.setUpdateSuspended(false);
        }
    }

    private void activateNewButton(int newButton) {
        if (mActiveButton == newButton) {
            return;
        }

        // TODO animation

        mMenu.getItem(newButton).setChecked(true);

        mActiveButton = newButton;
    }

    private MyBottomNavigationItemView getNewItem() {
        MyBottomNavigationItemView item = sItemPool.acquire();
        if (item == null) {
            item = new MyBottomNavigationItemView(getContext());
        }
        return item;
    }
}
