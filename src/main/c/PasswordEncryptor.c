#include <stdio.h>
#include <string.h>

// 加密函数：将每个字符的ASCII码加10
void encrypt_password(char *input, char *output) {
    int i;
    for (i = 0; input[i] != '\0'; i++) {
        output[i] = input[i] + 10;
    }
    output[i] = '\0'; // 确保字符串以 null 结尾
}
