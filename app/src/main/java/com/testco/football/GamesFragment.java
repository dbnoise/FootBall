package com.testco.football;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testco.football.data.Game;
import com.testco.football.data.League;

/**
 * Created by sergey on 1/26/17.
 */

public class GamesFragment extends MyFragment implements League.Listener {

    class MyHolder extends RecyclerView.ViewHolder {
        public TextView p1, p2, score;
        public MyHolder(View itemView) {
            super(itemView);
            p1 = (TextView) itemView.findViewById(R.id.player1);
            p2 = (TextView) itemView.findViewById(R.id.player2);
            score = (TextView) itemView.findViewById(R.id.score);
        }
    };

    class MyAdapter extends RecyclerView.Adapter<MyHolder> {

        Game[] games;
        LayoutInflater inflater;

        public MyAdapter(Game[] games) {
            this.games = games;
        }

        LayoutInflater getInflater(View view) {
            if( inflater == null ) {
                inflater = LayoutInflater.from(view.getContext());
            }
            return inflater;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View res = getInflater(parent).inflate(R.layout.cell_game, parent, false);
            return new MyHolder(res);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            Game game = games[position];
            holder.p1.setText(game.player1.name);
            holder.p2.setText(game.player2.name);
            holder.score.setText(game.score1 + ":" + game.score2);
        }

        @Override
        public int getItemCount() {
            return games.length;
        }
    };

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View res = inflater.inflate(R.layout.fragment_games, container, false);
        recyclerView = (RecyclerView) res.findViewById(R.id.main_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapter(getMainActivity().getLeague().getGames()));
        getMainActivity().getLeague().addListener(this);
        return res;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getMainActivity().getLeague().removeListener(this);
    }

    @Override
    public void leagueUpdated(League league) {
        recyclerView.setAdapter(new MyAdapter(getMainActivity().getLeague().getGames()));
    }

}
