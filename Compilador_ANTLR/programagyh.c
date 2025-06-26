#include<stdio.h>
#include<stdlib.h>

void main(){ 
 	int aux;
	int num1;
	int num3;
	int num2;
	int num4;
scanf(" %d",&num1);
scanf(" %d",&num2);
scanf(" %d",&num3);
if(num1>num2){
	aux=40000;
	num2=num1;
	num1=aux;
}
if(num1>num3 && num2<=num4 && num1>3 || num2!=num4){
	aux=num3;
	num3=num1;
	num1=aux;
}
else {
	aux=num3;
	num3=num2;
	num2=aux;
}
	printf("%d", num1);
	printf("%d", num2);
	printf("%d", num3);
} 
