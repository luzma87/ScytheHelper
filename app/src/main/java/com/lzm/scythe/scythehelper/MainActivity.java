package com.lzm.scythe.scythehelper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lzm.scythe.scythehelper.helpers.FragmentsHelper;
import com.lzm.scythe.scythehelper.models.Game;

public class MainActivity extends AppCompatActivity implements PlayersFragment.OnPlayersFragmentInteractionListener {

    Game currentGame;

    String tag = "Luz - Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        this.setTitle(R.string.players_setup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment playersFragment = PlayersFragment.newInstance();
        FragmentsHelper.openFragment(this, playersFragment, getString(R.string.players_setup), false);
    }

    @Override
    public void onPlayersSetupFinished(Game game) {
        currentGame = game;

    }
}
