package com.testco.football;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.testco.football.data.League;
import com.testco.football.data.Player;

import java.util.Comparator;

/**
 * Created by sergey on 1/26/17.
 */

public class PlayersFragment extends MyFragment implements League.Listener {

    class MyHolder extends RecyclerView.ViewHolder {
        public TextView name, lost, won, played;
        public MyHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            lost = (TextView) itemView.findViewById(R.id.games_lost);
            won = (TextView) itemView.findViewById(R.id.games_won);
            played = (TextView) itemView.findViewById(R.id.games_played);
        }
    };

    class MyAdapter extends RecyclerView.Adapter<MyHolder> {

        Player[] players;
        LayoutInflater inflater;

        public MyAdapter(Player[] players) {
            this.players = players;
        }

        LayoutInflater getInflater(View view) {
            if( inflater == null ) {
                inflater = LayoutInflater.from(view.getContext());
            }
            return inflater;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View res = getInflater(parent).inflate(R.layout.cell_player, parent, false);
            return new MyHolder(res);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            Player player = players[position];
            holder.name.setText(player.name);
            holder.lost.setText("lost: " + player.getGamesLost());
            holder.won.setText("won: " + player.getGamesWon());
            holder.played.setText("played: " + player.getGamesPlayed());
        }

        @Override
        public int getItemCount() {
            return players.length;
        }
    };

    static int compareInts( int v1, int v2 ) {
        if( v1 < v2 ) return 1;
        if( v1 > v2 ) return -1;
        return 0;
    }

    static String[] sorts = new String[] {"Sort By Name", "Sort By Games Played", "Sort By Games Won", "Sort By Games Lost" };
    static Comparator<Player>[] comps = new Comparator[] {
            new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p1.name.compareTo(p2.name);
                }
            },
            new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return compareInts(p1.getGamesPlayed(), p2.getGamesPlayed());
                }
            },
            new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return compareInts(p1.getGamesWon(), p2.getGamesWon());
                }
            },
            new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return compareInts(p1.getGamesLost(), p2.getGamesLost());
                }
            }
    };

    class SortAdapter extends BaseAdapter {

        String[] data;

        LayoutInflater inflater;

        LayoutInflater getInflater(View view) {
            if( inflater == null ) {
                inflater = LayoutInflater.from(view.getContext());
            }
            return inflater;
        }
        public SortAdapter(String[] data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int i) {
            return data[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View res = getInflater(viewGroup).inflate(R.layout.cell_sort, viewGroup, false);
            TextView text = (TextView) res.findViewById(R.id.main_text);
            text.setText(getItem(i).toString());
            return res;
        }
    }

    RecyclerView recyclerView;
    Spinner sortTypeSpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View res = inflater.inflate(R.layout.fragment_players, container, false);
        sortTypeSpinner = (Spinner) res.findViewById(R.id.sort_type);
        sortTypeSpinner.setAdapter(new SortAdapter(sorts));
        recyclerView = (RecyclerView) res.findViewById(R.id.main_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setSort(0);
        sortTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setSort(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getMainActivity().getLeague().addListener(this);
        return res;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getMainActivity().getLeague().removeListener(this);
    }

    void setSort(int index) {
        recyclerView.setAdapter(new MyAdapter(getMainActivity().getLeague().getPlayers(comps[index])));
    }

    @Override
    public void leagueUpdated(League league) {
        recyclerView.setAdapter(new MyAdapter(getMainActivity().getLeague().getPlayers(comps[sortTypeSpinner.getSelectedItemPosition()])));
    }

}
