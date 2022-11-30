#include "Algoritmos_EUF.h"

Algoritmos_EUF::Algoritmos_EUF() {}
Algoritmos_EUF::~Algoritmos_EUF() {}

int Algoritmos_EUF::busquedaK_menor_Dir(vector<int>& v, int k) {
	OrdenaQuickSort(v);
	return v[k];
}

int Algoritmos_EUF::busquedaK_menor_It(vector<int>& v, int k) {
	return SelectIt(v, 0, v.size() - 1, k);
}

int Algoritmos_EUF::SelectIt(vector<int>& A, int p, int r, int k) {
	while (p < r) {
		int q = Partition(A, p, r);
		int i = q - p + 1;
		if (k <= i) {
			r = q;
		} else {
			p = q + 1;
			k = k - i;
		}
	}
	return A[p];
}


int Algoritmos_EUF::busquedaK_menor_RC(vector<int>& v, int k) {
	return Select(v, 0, v.size() - 1, k);
}

int Algoritmos_EUF::Select(vector<int>& A, int p, int r, int k) {
	if (p == r)
		return A[p];

	int q = Partition(A, p, r);
	int i = q - p + 1;

	if (k <= i)
		return Select(A, p, q, k);
	else
		return Select(A, q + 1, r, k - i);
}

void Algoritmos_EUF::OrdenaQuickSort(vector<int>& v) {
	QuickSort(v, 0, v.size() - 1);
}

void Algoritmos_EUF::QuickSort(vector<int>& v, int c, int f) {
	if (c < f) {
		int p = Partition(v, c, f);
		QuickSort(v, c, p - 1);
		QuickSort(v, p + 1, f);
	}
}

int Algoritmos_EUF::Partition(vector<int>& v, int p, int r) {
	/*
	int piv = f;
	for (int i = c; i < f; i++) {
		if ((v[i] <= v[piv] && piv < i) || v[i] > v[piv]) {
			swap(v, i, f);
			i--;
		} else if (v[i] > v[piv]) {
			swap(v, i, piv);
			piv = i;
		}
	}

	return piv;*/

	/*
	int piv = v[p];
	int i = p;
	int j = r;
	while (j > i) {
		while (v[j] > piv)
			j--;

		while (v[i] < piv)
			i++;

		if (i < j) {
			int temp = v[j];
			v[j] = v[i];
			v[i] = temp;
		}
	}
	return j;*/

	int i = p;
	int j = p;
	int piv = r;

	while (i <= r) {
		if (v[i] > v[piv])
			++i;
		else {
			swap(v, i, j);
			i++;
			j++;
		}
	}
	return j - 1;
}

void Algoritmos_EUF::swap(vector<int>& v, int a, int b) {
	int temp = v[a];
	v[a] = v[b];
	v[b] = temp;
}