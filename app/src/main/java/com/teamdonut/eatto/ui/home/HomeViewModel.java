package com.teamdonut.eatto.ui.home;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;
import com.teamdonut.eatto.data.Board;
import com.teamdonut.eatto.data.User;
import com.teamdonut.eatto.model.HomeAPI;
import com.teamdonut.eatto.model.ServiceGenerator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {
    public ObservableArrayList<User> users = new ObservableArrayList<>();
    public ObservableArrayList<Board> boards = new ObservableArrayList<>();
    public UserRankingAdapter userRankingAdapter = new UserRankingAdapter(users);
    public BoardRecommendAdapter boardRecommendAdapter = new BoardRecommendAdapter(boards);
    private CompositeDisposable disposables = new CompositeDisposable();
    private HomeAPI service = ServiceGenerator.createService(HomeAPI.class, ServiceGenerator.BASE);

    public void fetchRecommendBoardList() {
        disposables.add(
                service.getRecommendBoards()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(data -> {
                                    boards.addAll(data);
                                    boardRecommendAdapter.notifyDataSetChanged();
                                }, e -> {
                                    e.printStackTrace();
                                }
                        )
        );
    }

    public void fetchRankUsersList() {
        disposables.add(
                service.getTopTenUsers()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(data -> {
                                    users.addAll(data);
                                    userRankingAdapter.notifyDataSetChanged();
                                }, e -> {
                                    e.printStackTrace();
                                }
                        )
        );
    }

    public void onDestroyViewModel() {
        disposables.dispose();
    }
}
