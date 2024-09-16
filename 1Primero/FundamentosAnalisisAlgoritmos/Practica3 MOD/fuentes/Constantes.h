/* 
 *Definiciones de las Constantes para la pr�ctica 2 
 */
#ifndef _CONSTANTES
#define _CONSTANTES

/* Constantes simb�licas para indicar el metodo de ordenacion*/
/**** EXAJUN21****/
enum algOrdenacion {BURBUJA, INSERCION, SELECCION, JUNIO21};
/* Constantes simb�licas para indicar el metodo de busqueda*/
enum algBusqueda {SECUENCIALIT, BINARIAIT, INTERIT};
/* Constantes para indicar el Orden del metodo de ordenacion*/
enum ordenes {CUADRADO, NlogN, N, logN, loglogN};
/* Constantes para indicar el Numero de repeticiones para el caso medio de cada m�todo de b�squeda */
static const int NUMREPETICIONES=10;
/* Constantes para indicar la variacion de las tallas del vector */
enum valoresTallas { tallaIni = 100,tallaFin = 1000,incTalla = 100}; 

#endif