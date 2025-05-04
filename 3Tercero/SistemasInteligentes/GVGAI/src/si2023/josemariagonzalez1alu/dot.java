package si2023.josemariagonzalez1alu;

import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;

public class dot {

	public dot() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				System.out.println(new Posicion(i, j).hashCode());
			}
		}

	}

}
