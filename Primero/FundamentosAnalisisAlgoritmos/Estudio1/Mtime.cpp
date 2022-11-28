#include "Mtime.h"

double Mtime::performancecounter_diff(LARGE_INTEGER* a, LARGE_INTEGER* b)
{
	LARGE_INTEGER freq;
	QueryPerformanceFrequency(&freq);
	return (double)(a->QuadPart - b->QuadPart) / (double)freq.QuadPart;
}