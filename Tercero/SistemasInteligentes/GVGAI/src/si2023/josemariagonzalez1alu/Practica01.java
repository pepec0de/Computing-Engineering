package si2023.josemariagonzalez1alu;

import java.util.Random;
import tools.Utils;
import tracks.ArcadeMachine;

public class Practica01 {

    public static void main(String[] args) {
    	
    	String p = "si2023.josemariagonzalez1alu.p01.Agente89";
    	String pintar = "si2023.josemariagonzalez.p03.agente89.Agente89_Pintar";
    	String agente = p;

		//Load available games
		String spGamesCollection =  "examples/all_games_sp.csv";
		String[][] games = Utils.readGames(spGamesCollection);
		Random rand = new Random(System.currentTimeMillis());
		
		//Game settings
//		boolean visuals = false;
				
		// Game and level to play
		int gameIdx  = 89;
		int levelIdx = 4; // level names from 0 to 4 (game_lvlN.txt).
		
		String gameName = games[gameIdx][1];
		String game = games[gameIdx][0];
		String level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);

		// 1. This starts a game, in a level, played by a human.
//		ArcadeMachine.playOneGame(game, level1, null, rand.nextInt());
		
		
		boolean rate = true;
		int seed = rand.nextInt();
		double time = 0.0;
		double score = 0.0;
		int n = 100;
		int winrate = 0;
		for (int i = 0; i < n; i++) {
			seed = rand.nextInt();
			double[] res = ArcadeMachine.runOneGame(game, level1, false, agente, null, seed, 0);
			System.out.println("Win: " + res[0] + " Score: " + res[1] + " Time: " + res[2]);
			if (res[0] == 1) {
				winrate++;
				score += res[1];
				time += res[2];
			}
		}
		System.out.println("Win rate: " + (double)winrate/(double)n);
		System.out.println("Score: " + (double)score/(double)winrate + "\nTime: " + (double)time/(double)winrate);
		System.out.println();
		
		System.exit(0);

    }
}
