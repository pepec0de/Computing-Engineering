package si2023.josemariagonzalez1alu;

import java.util.*;

import tools.Utils;
import tracks.ArcadeMachine;

public class Practica04 {


    public static void main(String[] args) {

		String p0 = "si2023.josemariagonzalez1alu.p04.MasterAgent";
		
		//Load available games
		String spGamesCollection =  "examples/all_games_sp.csv";
		String[][] games = Utils.readGames(spGamesCollection);

		//Game settings
		boolean visuals = true;
		int seed = new Random().nextInt();
		
		// Game to play
		int gameIdx  = 50;
		int lastLevel = 4; // max 4

		
		
		String gameName = games[gameIdx][1];
		String game = games[gameIdx][0];
		
		int levelIdx = 4;
		String level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);
		
		// 1. This starts a game, in a level, played by a human.
		//ArcadeMachine.playOneGame(game, level1, null, seed);
		
		double s = 0;
		for (levelIdx = 4; levelIdx <= lastLevel; levelIdx++) {
			level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);
			// 2. This plays a game in a level by the controller.
			double r[] = ArcadeMachine.runOneGame(game, level1, true, p0, null, seed, 0);
			s += r[2];
		}
		
		//System.out.println(s);
		System.exit(0);

    }
}
