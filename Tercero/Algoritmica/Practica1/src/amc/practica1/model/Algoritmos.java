package amc.practica1.model;

import amc.practica1.types.Punto;

public class Algoritmos {
	
	public Algoritmos() {}
	
	public int[] BusquedaExhaustiva(Punto plano[]) {
		int n = plano.length;
		int result[] = {-1, -1, -1};
		
		if (n >= 3) {
			double distTotal = 0, dist;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j)
						continue;
					for (int k = i+1; k < n; k++) {
						if (j == k)
							continue;
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
		return result;
	}
	
	/*
	 * @param regOrden : Vector para registrar el orden de los indices
	 */
	public int[] BusquedaDyV(Punto plano[]) {
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
			mejorDist = Double.MAX_VALUE;
			DyVRecursivo(plano, plano[0].getX(), plano[plano.length-1].getX(), result);
			result[0] = regOrden[result[0]];
			result[1] = regOrden[result[1]];
			result[2] = regOrden[result[2]];
		}
		return result;
	}
	
	private double mejorDist;
	private double exhaustivo(Punto plano[], int start, int end, int result[]) {
		double distMin = Double.MAX_VALUE;
		if (end - start + 1 >= 3) {
			double dist;
			for (int i = start; i < end; i++) {
				for (int j = start; j < end; j++) {
					if (i == j)
						continue;
					for (int k = i+1; k < end; k++) {
						if (j == k)
							continue;
						dist = plano[i].getDistancia(plano[j]) + plano[j].getDistancia(plano[k]);
						if (distMin > dist && mejorDist > dist) {
							distMin = dist;
							mejorDist = dist;
							result[0] = i;
							result[1] = j;
							result[2] = k;
						}
					}
				}
			}
		}
		return distMin;
	}
	
	
	private int getStartIndexFromSet(Punto plano[], double minX) {
		int idxMin = 0;
		while (plano[idxMin].getX() < minX) idxMin++;
		return idxMin;
	}
	
	private int getEndIndexFromSet(Punto plano[], int minIdx, double maxX) {
		int idxEnd = minIdx;
		boolean decrementar = false;
		while (plano[idxEnd].getX() < maxX && (decrementar = idxEnd+1 < plano.length) ) idxEnd++;
		if (!decrementar) {
			idxEnd--;
		}
		return idxEnd;
	}
	
    private boolean puntosRepetidos(Punto plano[], int start, int end) {
        for (int i = start; i < end - 1; i++) {
            if (plano[i].getX() != plano[i + 1].getX()) {
                return false;
            }
        }
        return true;
    }
    
	public double DyVRecursivo(Punto plano[], double minX, double maxX, int result[]) {
		// Obtenemos el punto medio
		double mid = (minX + maxX)/2;
		
		// Tomamos los indices de los puntos que hacen de margen con respecto a minX y maxX
		int idxStart = getStartIndexFromSet(plano, minX);
		int idxEnd = getEndIndexFromSet(plano, idxStart, maxX);
		int n = idxEnd - idxStart + 1;
		
		if (n < 3 || puntosRepetidos(plano, idxStart, idxEnd))
			return -1;
		
		if (n < 6)
			exhaustivo(plano, idxStart, idxEnd, result);
		
		// Caso recursivo : n > 6
		double izq = DyVRecursivo(plano, minX, mid, result);
		double der = DyVRecursivo(plano, mid, maxX, result);
		
		// En caso de que el recursivo no haya hecho exhaustivo
		if (izq == -1 && der == -1) {
			return exhaustivo(plano, idxStart, idxEnd, result);
		}
		
        double distMin = izq;
        if (izq == -1) {
        	distMin = der;
        } else if (der != -1 && der < izq) {
        	distMin = der;
        }
        
        int newIdxStart = getStartIndexFromSet(plano, mid - mejorDist);
        int newIdxEnd = getEndIndexFromSet(plano, newIdxStart, mid + mejorDist);
        if (distMin < maxX - minX) {
        	double exh = exhaustivo(plano, newIdxStart, newIdxEnd, result);
        	return exh < distMin ? exh : distMin;
        }
        
        double exh = exhaustivo(plano, idxStart, idxEnd, result);
		return exh < distMin ? exh : distMin;
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
