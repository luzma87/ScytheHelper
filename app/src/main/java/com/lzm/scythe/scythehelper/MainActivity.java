package com.lzm.scythe.scythehelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner playersNumber;
    private List<LinearLayout> playerViews;
    private Button playersContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializePlayerViews();

        playersNumber = (Spinner) findViewById(R.id.players_number_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.players_number, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playersNumber.setAdapter(adapter);

        playersContinue = (Button) findViewById(R.id.players_continue);

        Button playersSetup = (Button) findViewById(R.id.players_do_setup);
        playersSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPlayers();
                Integer players = Integer.valueOf(playersNumber.getSelectedItem().toString());
                playersContinue.setVisibility(View.VISIBLE);
                for (int i = 0; i < players; i++) {
                    initializePlayer(i + 1, playerViews.get(i));
                }
            }
        });

    }


    private void initializePlayerViews() {
        playerViews = new ArrayList<>();
        playerViews.add((LinearLayout) findViewById(R.id.player1_layout));
        playerViews.add((LinearLayout) findViewById(R.id.player2_layout));
        playerViews.add((LinearLayout) findViewById(R.id.player3_layout));
        playerViews.add((LinearLayout) findViewById(R.id.player4_layout));
        playerViews.add((LinearLayout) findViewById(R.id.player5_layout));
    }

    private void resetPlayers() {
        for (int i = 0; i < 5; i++) {
            LinearLayout playerLayout = playerViews.get(i);
            playerLayout.setVisibility(View.GONE);
        }
    }

    private void initializePlayer(int playerNumber, LinearLayout playerLayout) {
        playerLayout.setVisibility(View.VISIBLE);
        String playerLabel = getString(R.string.player_name, playerNumber);

        EditText playerName = (EditText) playerLayout.findViewById(R.id.player_name);
        playerName.setHint(playerLabel);

        Spinner spinner = (Spinner) playerLayout.findViewById(R.id.player_color_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.player_colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
