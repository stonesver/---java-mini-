#include <stdio.h>
void test(){
int a=1;
    while(true){
        a++;
        printf("hello world!");
        if(a>10){
               break;
        }
    }
}
int main(){
    test();
    printf("hello world!");
    int a=1;
    int b=24;
    int c=a+b;
    return 0;
}