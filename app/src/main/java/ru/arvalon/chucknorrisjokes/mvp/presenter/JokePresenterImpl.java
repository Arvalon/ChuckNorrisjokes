package ru.arvalon.chucknorrisjokes.mvp.presenter;

import android.content.Intent;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.arvalon.chucknorrisjokes.mvp.model.Joke;
import ru.arvalon.chucknorrisjokes.mvp.model.JokeWrapper;
import ru.arvalon.chucknorrisjokes.mvp.views.JokeView;
import ru.arvalon.chucknorrisjokes.rest.ChuckNorrisAPI;
import ru.arvalon.chucknorrisjokes.rest.ChuckNorrisRestAPI;

/**
 * Created by arvalon on 06.11.2016.
 */

@InjectViewState
public class JokePresenterImpl extends MvpPresenter<JokeView> implements JokePresenter{

    public boolean isJokeSet;
    public boolean randomViewMode;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().vkLogin();
    }

    @Override
    public void postJoke(String jokeText) {

    }

    @Override
    public void getRundomJoke() {
        if (!isJokeSet){
            Log.d("happy","JokePresenterImpl getRundomJoke()");
            getViewState().showProgress();
            ChuckNorrisAPI api= ChuckNorrisRestAPI.getChuckNorrisRestAPI();
            Call<JokeWrapper> call=api.GetSingleJoke();
            call.enqueue(new Callback<JokeWrapper>() {
                @Override
                public void onResponse(Call<JokeWrapper> call, Response<JokeWrapper> response) {
                    getViewState().setJoke(response.body().getValue().getJoke());
                }

                @Override
                public void onFailure(Call<JokeWrapper> call, Throwable t) {
                    getViewState().showError();
                }
            });
            randomViewMode=true;
        }
        isJokeSet=true;

    }

    @Override
    public void setJoke(String jokeText) {

    }
}
