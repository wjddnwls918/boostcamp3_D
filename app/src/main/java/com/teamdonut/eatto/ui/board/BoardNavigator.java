package com.teamdonut.eatto.ui.board;

public interface BoardNavigator {

    default void onAddBoardClick(){};
    default void onTimePickerClick() {};
    default void onBoardSearchShowClick() {};
    default void onShowSnackBar() {};
    default void onBoardAddFinish() {};
}
