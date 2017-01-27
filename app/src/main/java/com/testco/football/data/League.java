package com.testco.football.data;

import com.testco.football.PlayersFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by sergey on 1/26/17.
 */

public class League {

    public interface Listener {
        void leagueUpdated(League league);
    }

    final private HashMap<String, Player> players = new HashMap<>();
    final private ArrayList<Game> games = new ArrayList<>();
    final private ArrayList<Listener> listeners = new ArrayList<>();

    public void addListener(Listener l) {
        listeners.add(l);
    }

    public void removeListener(Listener l) {
        listeners.remove(l);
    }

    public Player getOrCreatePlayer(String name) {
        String lcName = name.toLowerCase();
        Player res = players.get(lcName);
        if( res == null ) {
            res = new Player(name);
            players.put(lcName, res);
        }
        return res;
    }

    public Game addGame(String player1, int score1, String player2, int score2) {
        return addGame( getOrCreatePlayer(player1), score1, getOrCreatePlayer(player2), score2 );
    }

    public Game addGame(Player player1, int score1, Player player2, int score2) {
        Game res = new Game(player1, score1, player2, score2);
        games.add(res);
        return res;
    }

    public Game[] getGames() {
        return games.toArray(new Game[games.size()]);
    }

    public Player[] getPlayers(Comparator<Player> comp) {
        ArrayList<Player> p = new ArrayList<Player>(players.values());
        Collections.sort(p, comp);
        return p.toArray(new Player[p.size()]);
    }

    public void notifyListeners() {
        for( Listener listener : listeners ) {
            listener.leagueUpdated(this);
        }
    }
}
