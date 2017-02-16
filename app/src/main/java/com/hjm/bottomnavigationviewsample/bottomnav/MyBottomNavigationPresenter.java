package com.hjm.bottomnavigationviewsample.bottomnav;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.view.ViewGroup;

public class MyBottomNavigationPresenter implements MenuPresenter {

    public void setBottomNavigationMenuView(MyBottomNavigationMenuView menuView) {

    }

    @Override
    public void initForMenu(Context context, MenuBuilder menu) {

    }

    @Override
    public MenuView getMenuView(ViewGroup root) {
        return null;
    }

    @Override
    public void updateMenuView(boolean cleared) {

    }

    @Override
    public void setCallback(Callback cb) {

    }

    @Override
    public boolean onSubMenuSelected(SubMenuBuilder subMenu) {
        return false;
    }

    @Override
    public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {

    }

    @Override
    public boolean flagActionItems() {
        return false;
    }

    @Override
    public boolean expandItemActionView(MenuBuilder menu, MenuItemImpl item) {
        return false;
    }

    @Override
    public boolean collapseItemActionView(MenuBuilder menu, MenuItemImpl item) {
        return false;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return null;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
    }

    public void setUpdateSuspended(boolean updateSuspended) {
    }
}
