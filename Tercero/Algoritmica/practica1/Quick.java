package practica1;

public class Quick {
	public void Quicksort(int v[], int c, int f) {
		if (c < f) {
			int p = Partition(v, c, f);
			Quicksort(v, c, p - 1);
			Quicksort(v, p + 1, f);
		}
		
	}
	
	private void swap(int[] v, int i, int j) {
		int tmp = v[i];
		v[i] = v[j];
		v[j] = tmp;
	}
	
//	private int Partition(int v[], int c, int f) {
//		int piv = 
//		return i;
//	}
	private int Partition(int v[], int c, int f) {
		int piv = f;
		for (int i = c; i < f; i++) {
			if ((v[i] <= v[piv] && i > piv) || v[i] > v[piv]) {
				swap(v, i, f);
				i--;
			} else if (v[i] > v[piv]) {
				swap(v, i, piv);
				piv = i;
			}
		}
		return piv;
	}
	
	private int Partition2(int v[], int c, int f) {
		int piv = c;
		int nizq = f - c;
		int izq[], dch[];
		
		return piv;
	}
	
	
	public Quick() {
		int v[] = {4, 1, 5, 1};
		//int v[] = {1, 2, 3, 4, 5};
		//int v[] = {1, 2, 3, 4, 5};
		Quicksort(v, 0, v.length - 1);
		for (int i = 0; i < v.length; i++)
			System.out.print(v[i] + ", ");
		System.out.println();
	}
	
	public static void main(String args[]) {
		new Quick();
	}
}
