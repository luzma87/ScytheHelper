package com.lzm.scythe.scythehelper;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzm.scythe.scythehelper.models.Game;
import com.lzm.scythe.scythehelper.models.Player;

import java.util.ArrayList;
import java.util.List;

public class PopularityFragment extends Fragment {

    private static final String ARG_GAME = "game";

    private int numberOfPlayers;

    private Game currentGame;

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
            currentGame = getArguments().getParcelable(ARG_GAME);
            if (currentGame != null) {
                numberOfPlayers = currentGame.getPlayers().size();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popularity_fragment, container, false);
        initializePlayerViews(view);
        return view;
    }

    private void initializePlayerViews(View view) {
        playerViews = new ArrayList<>();
        playerViews.add((LinearLayout) view.findViewById(R.id.popularity_player1_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.popularity_player2_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.popularity_player3_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.popularity_player4_layout));
        playerViews.add((LinearLayout) view.findViewById(R.id.popularity_player5_layout));

        for (int i = 0; i < numberOfPlayers; i++) {
            LinearLayout playerLayout = playerViews.get(i);
            playerLayout.setVisibility(View.VISIBLE);

            TextView playerLabel = (TextView) playerLayout.findViewById(R.id.popularity_player_label);
            TextView playerColorTag = (TextView) playerLayout.findViewById(R.id.popularity_player_color_tag);
            Player player = currentGame.getPlayers().get(i).getPlayer();
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
        void onFragmentInteraction(Uri uri);
    }
}
