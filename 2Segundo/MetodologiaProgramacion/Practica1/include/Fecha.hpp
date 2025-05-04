#ifndef FECHA_HPP
#define FECHA_HPP

#include <iostream>
#include <iomanip>

using namespace std;

class Fecha
{
    int dia, mes, anio;
    int dmax;

    public:
        Fecha(const int &d, const int &m, const int &a) : dia(d), mes(m), anio(a) {
            int diasMes[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (anioBisiesto()) {
                diasMes[1] = 29;
            }

            if (mes < 1)
                mes = 1;
            else if (mes > 12)
                mes = 12;

            dmax = diasMes[mes-1];

            if (dia < 1)
                dia = 1;
            else if (dia > dmax)
                dia = dmax;
        }

        bool anioBisiesto() {
            return anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0);
        }

        void setDia(const int &d) { dia = d; }
        void setMes(const int &m) { mes = m; }
        void setAnio(const int &a) { anio = a; }

        void ver() {
            cout << setfill('0') << setw(2) << 1 << "/" << endl;
        }

        Fecha operator++() {
            if (dia+1 > dmax) {
                mes++;
                dia = 1;
            } else if (mes == 12) {
                mes = 1;
                anio++;
            }
        }
};

#endif // FECHA_HPP
