#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(){
    int i;
    pid_t processId;
    
    for (i = 0; i < 1000 ; i++){
        printf("==> %d\n",i);
    
    }
    printf("-------------\n");
    
    processId = fork();
    for (i = 0; i < 1000 ; i++){
        if (processId ==0){
        printf("자식 프로세스 : --> %d\n", i);
        }else{
            printf("부 프로세스 : ~~~~~> %d\n",i);
        }
        }
    return 0;
}