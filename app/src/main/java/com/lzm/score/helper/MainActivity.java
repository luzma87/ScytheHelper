package com.lzm.score.helper;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.lzm.score.helper.helpers.FragmentsHelper;
import com.lzm.score.helper.models.Game;

public class MainActivity extends AppCompatActivity implements
        StartFragment.OnStartFragmentInteractionListener,
        PlayersSetupFragment.OnPlayersFragmentInteractionListener,
        PopularityFragment.OnPopularityFragmentInteractionListener,
        ScoringFragment.OnScoringFragmentInteractionListener,
        FinalScoreFragment.OnFinalScoreFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        this.setTitle(R.string.fragment_title_player_setup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment startFragment = StartFragment.newInstance();
        FragmentsHelper.openFragment(this, startFragment, getString(R.string.app_name), false);
    }

    @Override
    public void onStartCalculating() {
        Fragment playersFragment = PlayersSetupFragment.newInstance();
        FragmentsHelper.openFragment(this, playersFragment, getString(R.string.fragment_title_player_setup), false);
    }

    @Override
    public void onPlayersSetupFinished(Game game) {
        hideKeyboard();
        Fragment popularityFragment = PopularityFragment.newInstance(game);
        FragmentsHelper.openFragment(this, popularityFragment, getString(R.string.fragment_title_popularity), false);
    }

    @Override
    public void onPopularityDone(Game game) {
        hideKeyboard();
        Fragment scoringFragment = ScoringFragment.newInstance(game);
        FragmentsHelper.openFragment(this, scoringFragment, getString(R.string.fragment_title_scoring), false);
    }

    @Override
    public void onScoringDone(Game game) {
        hideKeyboard();
        Fragment finalScoreFragment = FinalScoreFragment.newInstance(game);
        FragmentsHelper.openFragment(this, finalScoreFragment, getString(R.string.fragment_title_final_score), false);
    }

    @Override
    public void onNewGame() {
        hideKeyboard();
        Fragment playersFragment = PlayersSetupFragment.newInstance();
        FragmentsHelper.openFragment(this, playersFragment, getString(R.string.fragment_title_player_setup), false);
    }

    private void hideKeyboard() {
        View v = findViewById(R.id.fragment_container);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
