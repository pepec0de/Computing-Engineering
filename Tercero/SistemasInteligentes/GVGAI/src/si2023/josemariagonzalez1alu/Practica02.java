package si2023.josemariagonzalez1alu;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import tools.Utils;
import tracks.ArcadeMachine;

public class Practica02 {

    public static void main(String[] args) {

    	Random rand = new Random(System.currentTimeMillis());
    	
		String agente = "si2023.josemariagonzalez.p04.agente89.Agente89";
		
		//Load available games
		String spGamesCollection =  "examples/all_games_sp.csv";
		String[][] games = Utils.readGames(spGamesCollection);

		//Game settings
		int seed;
				
		// Game and level to play
		int gameIdx  = 89;
		int levelIdx = 4; // level names from 0 to 4 (game_lvlN.txt).
		
		String gameName = games[gameIdx][1];
		String game = games[gameIdx][0];
		String level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);

		
		// 1. This starts a game, in a level, played by a human.
		//ArcadeMachine.playOneGame(game, level1, null, seed);
		
		System.out.print("Calcular win rate? (S/n): ");
		Scanner sc = new Scanner(System.in);
		String opc = sc.next();
		boolean rate = opc.toLowerCase().equals("s");
		if (rate) {
			ArrayList<Integer> seedsMalas = new ArrayList<>();
			int n = 100;
			int winrate = 0;
			for (int i = 0; i < n; i++) {
				seed = rand.nextInt();
				double[] res = ArcadeMachine.runOneGame(game, level1, false, agente, null, seed, 0);
				System.out.println("Win: " + res[0] + " Score: " + res[1] + " Time: " + res[2]);
				if (res[0] == 1)
					winrate++;
				else
					seedsMalas.add(seed);
			}
			System.out.println("Win rate: " + (double)winrate/(double)n);
			System.out.print("Seeds perdidas: ");
			for (int s : seedsMalas)
				System.out.print(s + ", ");
			System.out.println();
		} else {
			// 2. This plays a game in a level by the controller.
			seed = rand.nextInt();
			ArcadeMachine.runOneGame(game, level1, true, agente, null, seed, 0);
		}
				

		System.exit(0);

    }
}