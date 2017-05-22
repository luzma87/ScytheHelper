package com.lzm.scythe.scythehelper;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzm.scythe.scythehelper.models.Game;
import com.lzm.scythe.scythehelper.models.Player;
import com.lzm.scythe.scythehelper.models.PlayerScore;
import com.lzm.scythe.scythehelper.models.PopularityScoringTier;

import java.util.ArrayList;
import java.util.List;

public class FinalScoreFragment extends Fragment {
    private static final String ARG_GAME = "game";

    private Game game;
    private int numberOfPlayers;

    private OnFinalScoreFragmentInteractionListener mListener;

    private List<LinearLayout> playerViews;

    private PopularityScoringTier tier1;
    private PopularityScoringTier tier2;
    private PopularityScoringTier tier3;
    private Context context;

    public FinalScoreFragment() {
    }

    public static FinalScoreFragment newInstance(Game game) {
        FinalScoreFragment fragment = new FinalScoreFragment();
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
            tier1 = new PopularityScoringTier(0, 6, 3, 2, 1);
            tier2 = new PopularityScoringTier(7, 12, 4, 3, 2);
            tier3 = new PopularityScoringTier(13, 18, 5, 4, 3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = this.getActivity();

        View view = inflater.inflate(R.layout.final_score_fragment, container, false);
        initializePlayerViews(view);

        return view;
    }

    private void initializePlayerViews(View view) {
        playerViews = new ArrayList<>();
        playerViews.add((LinearLayout) view.findViewById(R.id.final_score_player1_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.final_score_player2_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.final_score_player3_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.final_score_player4_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.final_score_player5_layout));

        CardView winnerCard = null;
        int highScore = 0;

        for (int i = 0; i < numberOfPlayers; i++) {
            LinearLayout playerLayout = playerViews.get(i);
            CardView playerCard = (CardView) playerLayout.findViewById(R.id.final_score_card);
            playerLayout.setVisibility(View.VISIBLE);

            TextView playerLabel = (TextView) playerLayout.findViewById(R.id.player_final_score_name);
            TextView playerColorTag = (TextView) playerLayout.findViewById(R.id.final_score_player_color_tag);
            TextView playerFinalScore = (TextView) playerLayout.findViewById(R.id.player_final_score_value);
            PlayerScore playerScore = game.getPlayers().get(i);
            Player player = playerScore.getPlayer();
            playerLabel.setText(player.getName());
            playerColorTag.setBackgroundResource(playerColor(player.getColor()));

            int finalScore = calculateFinalScore(playerScore);
            playerFinalScore.setText(String.valueOf(finalScore));

            if (finalScore > highScore) {
                highScore = finalScore;
                winnerCard = playerCard;
            }
        }
        if (winnerCard != null) {
            winnerCard.setBackgroundColor(ContextCompat.getColor(context, R.color.primary_light));
        }
    }

    private int calculateFinalScore(PlayerScore playerScore) {
        int finalScore = 0;

        finalScore += playerScore.getCoins();
        finalScore += getStructuresScore(playerScore.getStructures());
        finalScore += tier1.getScore(playerScore);
        finalScore += tier2.getScore(playerScore);
        finalScore += tier3.getScore(playerScore);

        return finalScore;
    }

    private int getStructuresScore(int structures) {
        switch (structures) {
            case 1:
                return 2;
            case 2:
            case 3:
                return 4;
            case 4:
            case 5:
                return 6;
            case 6:
                return 9;
            default:
                return 0;
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
        if (context instanceof OnFinalScoreFragmentInteractionListener) {
            mListener = (OnFinalScoreFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFinalScoreFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFinalScoreFragmentInteractionListener {
        void onAllDone(Uri uri);
    }
}
