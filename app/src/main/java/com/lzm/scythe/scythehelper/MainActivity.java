package com.lzm.scythe.scythehelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.lzm.scythe.scythehelper.models.Player;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner playersNumber;
    private List<LinearLayout> playerViews;
    private Button playersContinue;
    private Integer numberOfPLayers;
    private List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        this.setTitle(R.string.players_setup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializePlayerViews();

        playersNumber = (Spinner) findViewById(R.id.players_number_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.players_number, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playersNumber.setAdapter(adapter);


        Button playersSetup = (Button) findViewById(R.id.players_do_setup);
        playersSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupPlayers();
            }
        });

        playersContinue = (Button) findViewById(R.id.players_continue);
        playersContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < numberOfPLayers; i++) {
                    LinearLayout playerView = playerViews.get(i);
                    EditText nameInput = (EditText) playerView.findViewById(R.id.player_name);
                    String name = nameInput.getText().toString();
                    Spinner colorInput = (Spinner) playerView.findViewById(R.id.player_color_spinner);
                    String color = colorInput.getSelectedItem().toString();
                    Player player = new Player(i + 1, name, color);
                    players.add(player);
                }
            }
        });

    }

    private void setupPlayers() {
        resetPlayers();
        numberOfPLayers = Integer.valueOf(playersNumber.getSelectedItem().toString());
        playersContinue.setVisibility(View.VISIBLE);
        for (int i = 0; i < numberOfPLayers; i++) {
            initializePlayer(i + 1, playerViews.get(i));
        }
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
}
