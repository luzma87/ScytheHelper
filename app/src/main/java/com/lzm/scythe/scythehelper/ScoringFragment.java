package com.lzm.scythe.scythehelper;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzm.scythe.scythehelper.models.Game;
import com.lzm.scythe.scythehelper.models.Player;
import com.lzm.scythe.scythehelper.models.PlayerScore;

import java.util.ArrayList;
import java.util.List;

public class ScoringFragment extends Fragment {
    private static final String ARG_GAME = "game";
    private int numberOfPlayers;
    private List<LinearLayout> playerViews;
    private Game game;

    private OnScoringFragmentInteractionListener mListener;

    public ScoringFragment() {
    }

    public static ScoringFragment newInstance(Game game) {
        ScoringFragment fragment = new ScoringFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_GAME, game);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            game = getArguments().getParcelable(ARG_GAME);
            if (game != null) {
                numberOfPlayers = game.getPlayers().size();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scoring_fragment, container, false);
        initializePlayerViews(view);

        Button popularityContinue = (Button) view.findViewById(R.id.popularity_continue);
        popularityContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishScoringInput();
            }
        });
        return view;
    }

    private void finishScoringInput() {
        for (int i = 0; i < numberOfPlayers; i++) {
            PlayerScore playerScore = game.getPlayers().get(i);
            LinearLayout playerLayout = playerViews.get(i);

            int playerCoins = getPlayerStat(playerLayout, R.id.scoring_coins_value);
            playerScore.setCoins(playerCoins);

            int playerStars = getPlayerStat(playerLayout, R.id.scoring_stars_value);
            playerScore.setStars(playerStars);

            int playerTerritories = getPlayerStat(playerLayout, R.id.scoring_territories_value);
            playerScore.setTerritories(playerTerritories);

            int playerResources = getPlayerStat(playerLayout, R.id.scoring_resources_value);
            playerScore.setResources(playerResources);

            int playerStructures = getPlayerStat(playerLayout, R.id.scoring_structures_value);
            playerScore.setStructures(playerStructures);
        }
        if (mListener != null) {
            mListener.onScoringDone(game);
        }
    }

    private int getPlayerStat(LinearLayout playerLayout, int inputId) {
        EditText playerStarsInput = (EditText) playerLayout.findViewById(inputId);
        return Integer.parseInt(playerStarsInput.getText().toString());
    }

    private void initializePlayerViews(View view) {
        playerViews = new ArrayList<>();
        playerViews.add((LinearLayout) view.findViewById(R.id.player1_scoring_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.player2_scoring_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.player3_scoring_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.player4_scoring_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.player5_scoring_layout));

        for (int i = 0; i < numberOfPlayers; i++) {
            LinearLayout playerLayout = playerViews.get(i);
            playerLayout.setVisibility(View.VISIBLE);

            TextView playerLabel = (TextView) playerLayout.findViewById(R.id.scoring_player_name);
            TextView playerColorTag = (TextView) playerLayout.findViewById(R.id.scoring_player_color_tag);
            Player player = game.getPlayers().get(i).getPlayer();
            playerLabel.setText(player.getName());
            playerColorTag.setBackgroundResource(playerColor(player.getColor()));
        }
    }

    private int playerColor(String playerColor) {
        switch (playerColor) {
            case "Blue - Nordic":
                return R.color.player_blue;
            case "Black - Saxony":
                return R.color.player_black;
            case "White - Polania":
                return R.color.player_white;
            case "Yellow - Crimea":
                return R.color.player_yellow;
            case "Red - Rusviet":
                return R.color.player_red;
            default:
                return 0;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnScoringFragmentInteractionListener) {
            mListener = (OnScoringFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnScoringFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnScoringFragmentInteractionListener {
        void onScoringDone(Game game);
    }
}
