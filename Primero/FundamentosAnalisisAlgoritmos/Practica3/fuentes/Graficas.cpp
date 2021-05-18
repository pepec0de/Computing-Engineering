/*
 * Clase Graficas, contiene métodos para guardar las gráficas de los resultados, es decir, crea
 * los ficheros por lo lotes (comandos) para generar los ficheros gráficos que corresponda.
 */
#include "Graficas.h"

 /*
  * Método generarGraficaMEDIO, genera el fichero de comandos para GNUPlot y dibuja la gráfica
  * del caso medio de un método de ordenación y su ajuste a la función correspondiente.
  * param metodo: metodo de ordenacion.
  * param orden: Orden del metodo de ordenacion.
  */
void Graficas::generarGraficaMEDIO(string nombre_metodo,int orden)
{
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	ofstream f("CmdMedio.gpl");
	f << "# ESTE ES UN SCRIPT DE GNUPLOT PARA ESTUDIO DE 1 METODO\n"
		<< "set title \"" << nombre_metodo << "\"\n"
		<< "set key top left vertical inside\n"
		<< "set grid\n"
		<< "set xlabel \"Talla (n)\"\n"
		<< "set ylabel \"Tiempo (ms)\"\n";
	if (orden == CUADRADO) {
		f << "Cuadrado(x) = a*x*x +b*x + c\n"
			<< "fit Cuadrado(x) \"" << nombre_metodo << ".dat"
			<< "\" using 1:2 via a, b, c\n"
		<< "plot \"" << nombre_metodo << ".dat" << "\" using 1:2 title \"" << nombre_metodo << "\", Cuadrado(x)\n";
	} else if (orden == NlogN) {
		f << "xlog(x) = a*x * log(x) + b*x +c\n";
		f << "fit xlog(x) \"" << nombre_metodo << ".dat \" using 1:2 via a, b, c\n";
		f << "plot \"" << nombre_metodo << ".dat\" using 1:2 title \"" << nombre_metodo << "\", xlog(x)\n";
	} else if (orden == N) {
		f << "N(x) = a*x +b\n";
		f << "fit N(x) \"" << nombre_metodo << ".dat\" using 1:2 via a, b\n";
		f << "plot \"" << nombre_metodo << ".dat\" using 1:2 title \"" << nombre_metodo << "\", N(x)\n";
	} else if (orden == logN) {
		f << "LOG(x) = a + b*(log(x)/log(2))\n";
		f << "fit LOG(x) \"" << nombre_metodo << ".dat\" using 1:2 via a\n";
		f << "plot \"" << nombre_metodo << ".dat\" using 1:2 title \"" << nombre_metodo << "\", LOG(x)\n";
	} else if (orden == loglogN) {
		f << "LOGLOG(x) = a + b*(log(log(x)/log(2)))/log(2)\n";
		f << "fit LOGLOG(x) \"" << nombre_metodo << ".dat\" using 1:2 via a\n";
		f << "plot \"" << nombre_metodo << ".dat\" using 1:2 title \"" << nombre_metodo << "\", LOGLOG(x)\n";
	}
	f << "set terminal pdf\n"
		<< "set output \"" << nombre_metodo << ".pdf" << "\"\n"
		<< "replot\n"
		<< "pause 5 \"Pulsa Enter para continuar...\"";
	f.close();
	system("CmdMedio.gpl");
	cout << "Grafica guardada en el fichero " << nombre_metodo << ".pdf\n";
}	

/*
 * Método generarGraficaCMP, genera el fichero de comandos para GNUPlot.
 * param nombre1: es el nombre del fichero de datos del primer método de ordenación 
 * param nombre2: es el nombre del fichero de datos del segundo método de ordenación 
 */
void  Graficas::generarGraficaCMP(string nombre1, string nombre2)
{
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	ofstream f("CmdComparar.gpl");
	f << "# ESTE ES UN SCRIPT DE GNUPLOT PARA COMPARACION DE 2 METODOS\n"
		<< "set title \"Comparacion de tiempos: " << nombre1 << " y " << nombre2 << "\"\n"
		<< "set key top left vertical inside\n"
		<< "set grid\n"
		<< "set xlabel \"Talla (n)\"\n"
		<< "set ylabel \"Tiempo (ms)\"\n"
		<< "plot \"" << nombre1 << ".dat\" using 1:2 with lines title \"" << nombre1 <<  "\", \"" << nombre2 << ".dat\" using 1:2 with lines title \"" << nombre2 << "\"\n"
		<< "set terminal pdf\n"
		<< "set output \"" << nombre1 << nombre2 << ".pdf" << "\"\n"
		<< "replot\n"
		<< "pause 5 \"Pulsa Enter para continuar...\"";
	f.close();
	system("CmdComparar.gpl");
	cout << "Grafica guardada en el fichero " << nombre1 << nombre2 << ".pdf\n";
}

/*
 * Método generarGraficaCMP, genera el fichero de comandos para GNUPlot.
 * param nombre1: es el nombre del fichero de datos del primer método de ordenación
 * param nombre2: es el nombre del fichero de datos del segundo método de ordenación
 */
void  Graficas::generarGraficaCMP(vector<string> pNombreAlgoritmo) {
	string nombre = "";
	for (int i = 0; i < pNombreAlgoritmo.size(); i++) {
		nombre += pNombreAlgoritmo[i];
	}
	string titulo = nombre;
	nombre += ".gpl";
	ofstream f(nombre);
	f << "# ESTE ES UN SCRIPT DE GNUPLOT PARA COMPARACION DE 2 METODOS\n"
		<< "set title \"Comparacion de tiempos: " << titulo << "\"\n"
		<< "set key top left vertical inside\n"
		<< "set grid\n"
		<< "set xlabel \"Talla (n)\"\n"
		<< "set ylabel \"Tiempo (ms)\"\n"
		<< "plot ";
		for (int i = 0; i < pNombreAlgoritmo.size(); i++) {
			f << "\"" << pNombreAlgoritmo[i] << ".dat\" using 1:2 with lines title \"" << pNombreAlgoritmo[i] << "\"" << (i < pNombreAlgoritmo.size() - 1 ? ", " : "\n");
		}
		f << "set terminal pdf\n"
		<< "set output \"" << titulo << ".pdf" << "\"\n"
		<< "replot\n"
		<< "pause 5 \"Pulsa Enter para continuar...\"";
	f.close();
	system(nombre.c_str());
	cout << "Grafica guardada en el fichero " << titulo << ".pdf\n";
}

void Graficas::generarGraficaTeorico(string metodo)
{
	string nombre = "CmdMedioTeorico.gpl";
	ofstream f(nombre);
	f << "set title \"Tiempo teórico según diferentes casos del algoritmo: " << metodo << "\"\n"
		<< "set key top left vertical inside\n"
		<< "set grid\n"
		<< "set xlabel \"Talla (n)\"\n"
		<< "set ylabel \"Tiempo (OE)\"\n"
		<< "plot \""
		<< metodo << "Teorico.dat\" using 1:2 with lines title \"Caso Peor\", \""
		<< metodo << "Teorico.dat\" using 1:3 with lines title \"Caso Medio\", \""
		<< metodo << "Teorico.dat\" using 1:4 with lines title \"Caso Mejor\"\n"
		<< "set terminal pdf\n"
		<< "set output \"" << metodo << "Teorico.pdf\"\n"
		<< "replot\n"
		<< "pause 5 \"Pulsa Enter para continuar...\"\n";
	f.close();
	system(nombre.c_str());
	cout << "Gráfica guardada en el fichero " << metodo << "Teorico.pdf" << endl << endl;
}
