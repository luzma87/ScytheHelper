package com.lzm.scythe.scythehelper;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.lzm.scythe.scythehelper.models.Game;
import com.lzm.scythe.scythehelper.models.Player;
import com.lzm.scythe.scythehelper.models.PlayerScore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayersSetupFragment extends Fragment {

    private Spinner playersNumber;
    private List<LinearLayout> playerViews;
    private Button playersContinue;
    private Integer numberOfPLayers;
    private List<PlayerScore> players;
    private Game game;

    private OnPlayersFragmentInteractionListener mListener;
    private Context context;

    public PlayersSetupFragment() {
    }

    public static PlayersSetupFragment newInstance() {
        return new PlayersSetupFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        players = new ArrayList<>();

        context = this.getActivity();

        View view = inflater.inflate(R.layout.players_setup_fragment, container, false);

        initializePlayerViews(view);

        playersNumber = (Spinner) view.findViewById(R.id.players_number_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.players_number, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playersNumber.setAdapter(adapter);


        Button playersSetup = (Button) view.findViewById(R.id.players_do_setup);
        playersSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupPlayers();
            }
        });

        playersContinue = (Button) view.findViewById(R.id.players_continue);
        playersContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishPlayersSetup();
            }
        });

        return view;
    }

    private void finishPlayersSetup() {
        for (int i = 0; i < numberOfPLayers; i++) {
            LinearLayout playerView = playerViews.get(i);
            EditText nameInput = (EditText) playerView.findViewById(R.id.player_name);
            String name = nameInput.getText().toString();
            Spinner colorInput = (Spinner) playerView.findViewById(R.id.player_color_spinner);
            String color = colorInput.getSelectedItem().toString();
            Player player = new Player(i + 1, name, color);
            PlayerScore playerScore = new PlayerScore(player);
            players.add(playerScore);
        }
        game = new Game(new Date(), players);
        if (mListener != null) {
            mListener.onPlayersSetupFinished(game);
        }
    }

    private void setupPlayers() {
        resetPlayers();
        numberOfPLayers = Integer.valueOf(playersNumber.getSelectedItem().toString());
        playersContinue.setVisibility(View.VISIBLE);
        for (int i = 0; i < numberOfPLayers; i++) {
            initializePlayer(i + 1, playerViews.get(i));
        }
    }

    private void initializePlayerViews(View view) {
        playerViews = new ArrayList<>();
        playerViews.add((LinearLayout) view.findViewById(R.id.player1_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.player2_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.player3_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.player4_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.player5_layout));
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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.player_colors, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachAction(context);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachAction(activity);
        }
    }

    private void onAttachAction(Context context) {
        if (context instanceof OnPlayersFragmentInteractionListener) {
            mListener = (OnPlayersFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPlayersFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnPlayersFragmentInteractionListener {
        void onPlayersSetupFinished(Game game);
    }
}
