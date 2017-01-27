package com.testco.football.data;

import java.util.ArrayList;

/**
 * Created by sergey on 1/26/17.
 */

public class Player {

    public final String name;
    private ArrayList<Game> games = new ArrayList<>();
    private int gamesWon, gamesLost, gamesPlayed;

    Player(String name) {
        this.name = name;
    }

    void addGame(Game game) {
        games.add(game);
        gamesPlayed++;
        if( !game.isDraw() ) {
            Player winner = game.getWinner();
            if (winner == this) {
                gamesWon++;
            } else {
                gamesLost++;
            }
        }
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }
}
