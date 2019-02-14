package com.teamdonut.eatto.ui.map;

import android.view.View;

public class MapViewModel {
  
    private MapNavigator mNavigator;

    public MapViewModel(MapNavigator navigator) {
        this.mNavigator = navigator;
    }

    void onFragmentDestroyed() {
        mNavigator = null;
    }

    //검색 버튼 리스너
    public void onSearchClick() {
        mNavigator.startSearchActivity();
    }

    //게시물 추가 리스너
    public void onClickBoardAdd() {
        if (mNavigator != null) {
            mNavigator.addBoard();
        }
    }

    public void onClickSetMyPosition(View view) {
        mNavigator.startLocationUpdates();
    }
}
