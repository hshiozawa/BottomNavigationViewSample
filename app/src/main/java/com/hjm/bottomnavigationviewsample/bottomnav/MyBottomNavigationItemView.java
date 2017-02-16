package com.hjm.bottomnavigationviewsample.bottomnav;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class MyBottomNavigationItemView extends FrameLayout implements MenuView.ItemView {

    public MyBottomNavigationItemView(@NonNull Context context) {
        this(context, null);
    }

    public MyBottomNavigationItemView(@NonNull Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBottomNavigationItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void initialize(MenuItemImpl itemData, int menuType) {

    }

    public void setItemPosition(int position) {
    }

    public int getItemPosition() {
        return 0;
    }

    @Override
    public MenuItemImpl getItemData() {
        return null;
    }

    @Override
    public void setTitle(CharSequence title) {

    }

    @Override
    public void setCheckable(boolean checkable) {

    }

    @Override
    public void setChecked(boolean checked) {

    }

    @Override
    public void setShortcut(boolean showShortcut, char shortcutKey) {

    }

    @Override
    public void setIcon(Drawable icon) {

    }

    @Override
    public boolean prefersCondensedTitle() {
        return false;
    }

    @Override
    public boolean showsIcon() {
        return false;
    }
}
