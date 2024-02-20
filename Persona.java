public class Persona {

    private String name;
    private String lastname;
    private int age;

    private String email;

    private String colorFav;

    private String perfecto;
}



package org.example.model;

import java.util.Scanner;

public class TenisMatch {

    private static final int SETS_TO_WIN_MATCH = 2;
    private static final int DEUCE_POINTS = 3;
    private static final int VENTAGE_POINTS_TO_WIN = 2;
    private static final int NECESARY_GAMES = 6;
    private Player player1;
    private Player player2;

    public TenisMatch(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void playMatch() {
        while(!matchOver()){
            playGame();
        }
    }

    public void playGame() {
        while (!player1.hasWonGame() && !player2.hasWonGame()) {
            playPoint();
        }
        Player gameWinner = determineGameWinner();
        increaseGamesWon(gameWinner);
        calculateSets(gameWinner);
    }

    private void calculateSets(Player winnerPlayer){
        int gamesWon = winnerPlayer.getWinGames();
        if (gamesWon != NECESARY_GAMES) {
            displayGameMessage(winnerPlayer);
            resetPoints();
            return;
        }
        if (winnerPlayer.getTotalSet() > 0) {
            winnerPlayer.setTotalSet(SETS_TO_WIN_MATCH);
            displaySetMessage(winnerPlayer);
            return;
        }
        winnerPlayer.setTotalSet(1);
        displaySetMessage(winnerPlayer);
        resetPlayerStats();
    }

    public void playPoint() {
        int option =captureConsoleScanner();
        switch (option){
            case 1: player1.aumentedPoint();
            break;
            case 2: player2.aumentedPoint();
            break;
            default: throw new IllegalStateException();
        }
        if (isDeuce() && !advantage()) {
            while (Math.abs(player1.getTotalPoints() - player2.getTotalPoints()) != VENTAGE_POINTS_TO_WIN) {
                playPoint();
            }
        }
    }

    public int captureConsoleScanner(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public boolean advantage() {
        return Math.abs(player1.getTotalPoints() - player2.getTotalPoints()) == 1;
    }

    public boolean isDeuce() {
        return player1.getTotalPoints() == DEUCE_POINTS && player2.getTotalPoints() == DEUCE_POINTS;
    }

    public boolean matchOver(){
       return player1.getTotalSet() == SETS_TO_WIN_MATCH || player2.getTotalSet() == SETS_TO_WIN_MATCH;
    }

    private void resetPoints() {
        player1.setTotalPoints(0);
        player2.setTotalPoints(0);
    }

    private void resetPlayerStats() {
        player1.setWinGames(0);
        player1.setTotalPoints(0);
        player2.setWinGames(0);
        player2.setTotalPoints(0);
    }
    
    private void displaySetMessage(Player gameWinner) {
        System.out.println("Set for " + gameWinner.getName() + "  " + gameWinner.getTotalSet());
    }

    private void displayGameMessage(Player gameWinner) {
        System.out.println("Game for " + gameWinner.getName() + "  " + gameWinner.getWinGames());
    }

    private Player determineGameWinner() {
        return (player1.hasWonGame()) ? player1 : player2;
    }

    private void increaseGamesWon(Player gameWinner) {
        gameWinner.aumentedWinGames();
    }

}

package org.example.model;

public class Player {
    private String name;
    private int totalPoints;
    private int winGames;
    private int totalSet;

    public Player(String name) {
        this.name = name;
        this.totalPoints = 0;
        this.winGames = 0;
    }

    public String getName() {
        return name;
    }

    public void aumentedPoint() {
        totalPoints++;
    }
    public boolean hasWonGame() {
        return totalPoints > 3 ;
    }

    public void aumentedWinGames() {
        winGames++;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getWinGames() {
        return winGames;
    }

    public void setWinGames(int winGames) {
        this.winGames = winGames;
    }

    public int getTotalSet() {
        return totalSet;
    }

    public void setTotalSet(int totalSet) {
        this.totalSet = totalSet;
    }
}





package org.example.model;

import java.util.Scanner;

import static org.example.model.InputScanner.getOption;

public class TenisMatch {

    private static final int SETS_TO_WIN_MATCH = 2;
    private static final int DEUCE_POINTS = 3;
    private static final int VENTAGE_POINTS_TO_WIN = 2;
    private static final int NECESARY_GAMES = 6;
    private Player player1;
    private Player player2;


    public TenisMatch(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void playMatch() {
        while(!matchOver()){
            playGame();
        }
    }

    public void playGame() {
        while (!player1.hasWonGame() && !player2.hasWonGame()) {
            int option = getOption();
            playPoint(option);
        }
        Player gameWinner = determineGameWinner();
        increaseGamesWon(gameWinner);
        calculateSets(gameWinner);
    }

    private void calculateSets(Player winnerPlayer){
        int gamesWon = winnerPlayer.getWinGames();
        if (gamesWon != NECESARY_GAMES) {
            displayGameMessage(winnerPlayer);
            resetPoints();
            return;
        }
        if (winnerPlayer.getTotalSet() > 0) {
            winnerPlayer.setTotalSet(SETS_TO_WIN_MATCH);
            displaySetMessage(winnerPlayer);
            return;
        }
        winnerPlayer.setTotalSet(1);
        displaySetMessage(winnerPlayer);
        resetPlayerStats();
    }

    public void playPoint(int option) {

        switch (option){
            case 1: player1.aumentedPoint();
            break;
            case 2: player2.aumentedPoint();
            break;
            default: throw new IllegalStateException();
        }

        if (isDeuce() && !advantage()) {
            while (Math.abs(player1.getTotalPoints() - player2.getTotalPoints()) != VENTAGE_POINTS_TO_WIN) {
                
            }
        }
    }

    public int captureConsoleScanner(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public boolean advantage() {
        return Math.abs(player1.getTotalPoints() - player2.getTotalPoints()) == 1;
    }

    public boolean isDeuce() {
        return player1.getTotalPoints() == DEUCE_POINTS && player2.getTotalPoints() == DEUCE_POINTS;
    }

    public boolean matchOver(){
       return player1.getTotalSet() == SETS_TO_WIN_MATCH || player2.getTotalSet() == SETS_TO_WIN_MATCH;
    }

    private void resetPoints() {
        player1.setTotalPoints(0);
        player2.setTotalPoints(0);
    }

    private void resetPlayerStats() {
        player1.setWinGames(0);
        player1.setTotalPoints(0);
        player2.setWinGames(0);
        player2.setTotalPoints(0);
    }
    private String getPointsText (int points){
        return switch (points){
            case 0 -> "0";
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            default -> throw new IllegalStateException("Unexpected value: " + points);
        };
    }
    private void displaySetMessage(Player gameWinner) {
        System.out.println("Set for " + gameWinner.getName() + "  " + gameWinner.getTotalSet());
    }

    private void displayGameMessage(Player gameWinner) {
        System.out.println("Game for " + gameWinner.getName() + "  " + gameWinner.getWinGames());
    }

    private Player determineGameWinner() {
        return (player1.hasWonGame()) ? player1 : player2;
    }

    private void increaseGamesWon(Player gameWinner) {
        gameWinner.aumentedWinGames();
    }


