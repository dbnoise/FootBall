package com.testco.football.data;

/**
 * Created by sergey on 1/26/17.
 */

public class Game {

    public final Player player1, player2;
    public final int score1, score2;

    Game(Player player1, int score1, Player player2, int score2) {
        this.player1 = player1;
        this.player2 = player2;
        this.score1 = score1;
        this.score2 = score2;
        player1.addGame(this);
        player2.addGame(this);
    }

    public boolean isDraw() {
        return score1 == score2;
    }

    public Player getWinner() {
        if( score1 > score2 ) return player1;
        if( score1 < score2 ) return player2;
        return null;
    }
}
