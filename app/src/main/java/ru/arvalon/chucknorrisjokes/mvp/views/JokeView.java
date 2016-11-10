package ru.arvalon.chucknorrisjokes.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by arvalon on 06.11.2016.
 */

public interface JokeView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void vkLogin();
    void setJoke(String jokeText);
    void showError();
    void showProgress();
    void PostJoke();
}
