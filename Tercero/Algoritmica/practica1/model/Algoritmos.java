package practica1.model;

import java.util.ArrayList;

public class Algoritmos {
	
	public Algoritmos() {
		
		
	}
	
	public int[] BusquedaExhaustiva(Punto plano[]) {
		int n = plano.length;
		int result[] = {-1, -1, -1};
		
		if (n >= 3) {
			double distTotal = 0, dist;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int k = 0; k < n; k++) {
						if (i != j && i != k && j != k) {
							dist = plano[i].getDistancia(plano[j]) + plano[j].getDistancia(plano[k]);
							if (distTotal == 0 || distTotal > dist) {
								distTotal = dist;
								result[0] = i;
								result[1] = j;
								result[2] = k;
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	/*
	 * @param regOrden : Vector para registrar el orden de los indices
	 */
	public int[] BusquedaExhaustivaRC(Punto plano[]) {
		int n = plano.length;
		int result[] = {-1, -1, -1};
		if (n <= 3) {
			result[0] = n-3;
			result[1] = n-2;
			result[2] = n-1;
		} else {
			int regOrden[] = new int[n];
			for (int i = 0; i < plano.length; i++)
				regOrden[i] = i;
			ordenarPlano(plano, regOrden);
			exhaustivaRC(plano, 0, plano.length-1, result);
			System.out.print("Result = ");
			for (int i = 0; i < result.length; i++)
				System.out.print(result[i] + ", ");
			System.out.println();
			result[0] = regOrden[result[0]];
			result[1] = regOrden[result[1]];
			result[2] = regOrden[result[2]];
		}
		return result;
	}
	
	public double exhaustivaRC(Punto plano[], int c, int f, int result[]) {
		int n = f - c + 1;
		if (n > 3) {
			int p = n/2;
			System.out.println("c = " + c + " p = " + p + " f = " + f);
			if (c < p-1) {
				int resultI[] = new int[3], resultD[] = new int[3], result1[];
				double distMinI = exhaustivaRC(plano, c, p-1, resultI);
				double distMinD = exhaustivaRC(plano, p, f, resultD);
				double distMin = distMinI;
				result1 = resultI;
				if (distMinD < distMinI) {
					distMin = distMinD;
					result1 = resultD;
				}
				
				double distMinC = 0, dist;
				for (int i = c; i <= f; i++) {
					for (int j = c; j <= f; j++) {
						for (int k = c; k <= f; k++) {
							if (i != j && i != k && j != k) {
								dist = plano[i].getDistancia(plano[j]) + plano[j].getDistancia(plano[k]);
								if (distMinC == 0 || distMinC > dist) {
									distMinC = dist;
									result[0] = i;
									result[1] = j;
									result[2] = k;
								}
							}
						}
					}
				}
				if (distMinC < distMin) {
					return distMinC;
				}
				
				// else
				result = result1;
				return distMin;
			}
		} else if (n == 3) {
			result[0] = c;
			result[1] = c+1;
			result[2] = c+2;
			return plano[c].getDistancia(plano[c+1]) + plano[c+1].getDistancia(plano[c+2]);
		}
		return Double.MAX_VALUE;
	}
	
	private void ordenarPlano(Punto plano[], int regOrden[]) {
		Quicksort(plano, 0, plano.length - 1, regOrden);
	}
	
	public void Quicksort(Punto v[], int c, int f, int regOrden[]) {
		if (c < f) {
			int p = Partition(v, c, f, regOrden);
			Quicksort(v, c, p - 1, regOrden);
			Quicksort(v, p + 1, f, regOrden);
		}
	}
	
	private void swap(Punto v[], int i, int j, int regOrden[]) {
		Punto tmp = v[i];
		v[i] = v[j];
		v[j] = tmp;
		int tmp1 = regOrden[i];
		regOrden[i] = regOrden[j];
		regOrden[j] = tmp1;
	}
	
	private int Partition(Punto v[], int c, int f, int regOrden[]) {
		int piv = f;
		for (int i = c; i < f; i++) {
			if ((v[i].getX() <= v[piv].getX() && i > piv) || v[i].getX() > v[piv].getX()) {
				swap(v, i, f, regOrden);
				i--;
			} else if (v[i].getX() > v[piv].getX()) {
				swap(v, i, piv, regOrden);
				piv = i;
			}
		}
		return piv;
	}
}
