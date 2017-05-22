package com.lzm.score.helper;

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

import com.lzm.score.helper.models.Game;
import com.lzm.score.helper.models.Player;
import com.lzm.score.helper.models.PlayerScore;

import java.util.ArrayList;
import java.util.List;

import static com.lzm.score.helper.helpers.PlayerColorHelper.playerColor;

public class PopularityFragment extends Fragment {

    private static final String ARG_GAME = "game";

    private int numberOfPlayers;

    private Game game;

    private OnPopularityFragmentInteractionListener mListener;

    private List<LinearLayout> playerViews;

    public PopularityFragment() {
    }

    public static PopularityFragment newInstance(Game game) {
        PopularityFragment fragment = new PopularityFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popularity_fragment, container, false);
        initializePlayerViews(view);

        Button popularityContinue = (Button) view.findViewById(R.id.popularity_continue);
        popularityContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishPopularityInput();
            }
        });

        return view;
    }

    private void finishPopularityInput() {
        for (int i = 0; i < numberOfPlayers; i++) {
            PlayerScore playerScore = game.getPlayers().get(i);
            LinearLayout playerLayout = playerViews.get(i);

            EditText playerPopularityInput = (EditText) playerLayout.findViewById(R.id.popularity_value);
            int playerPopularity = Integer.parseInt(playerPopularityInput.getText().toString());

            playerScore.setPopularity(playerPopularity);
        }
        if (mListener != null) {
            mListener.onPopularityDone(game);
        }
    }

    private void initializePlayerViews(View view) {
        playerViews = new ArrayList<>();
        playerViews.add((LinearLayout) view.findViewById(R.id.player1_popularity_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.player2_popularity_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.player3_popularity_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.player4_popularity_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.player5_popularity_layout));

        for (int i = 0; i < numberOfPlayers; i++) {
            LinearLayout playerLayout = playerViews.get(i);
            playerLayout.setVisibility(View.VISIBLE);

            TextView playerLabel = (TextView) playerLayout.findViewById(R.id.popularity_player_name);
            TextView playerColorTag = (TextView) playerLayout.findViewById(R.id.popularity_player_color_tag);
            Player player = game.getPlayers().get(i).getPlayer();
            playerLabel.setText(player.getName());
            playerColorTag.setBackgroundResource(playerColor(player.getColor()));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPopularityFragmentInteractionListener) {
            mListener = (OnPopularityFragmentInteractionListener) context;
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

    public interface OnPopularityFragmentInteractionListener {
        void onPopularityDone(Game game);
    }
}
