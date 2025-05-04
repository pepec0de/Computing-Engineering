#include <iostream>
#include <math.h>

using namespace std;

float NacciDV(int n) {
    if (n == 1)
        return 1;
    else if (n == 2)
        return 3;

	return (3.0/2.0)*NacciDV(n - 1) + NacciDV(n - 2);
}

float Nacci(int n) {
    if (n == 1)
        return 1;
    else if (n == 2)
        return 3;

    float a = 3, b = 1, c;
    for (int i = 2; i < n; i++) {
        c = (3.0/2.0)*a + b;
        b = a;
        a = c;
    }
    return c;
}

float NacciD(int n) {
    return (0.7 * pow(2, n) + 0.8*pow(-0.5, n));
}

float potencia(float a, int n) {
    float r = 1;
    for (int i = 0; i < n; i++)
        r = r*a;
    return r;
}


float fibonacci(int n) {
    if (n == 1)
        return 1;
    else if (n == 2)
        return 1;

    float a = 1, b = 1, c;
    for (int i = 2; i < n; i++) {
        c = a + b;
        b = a;
        a = c;
    }
    return c;
}

int main() {
    for (int i = 1; i <= 10; i++) {
        cout << NacciDV(i) << " " << Nacci(i) << " ";
        cout << NacciD(i) << endl;
    }
    return 0;
}
