/*
Title: Simple Calculator in C

Description: 
This program implements a simple calculator that performs addition, subtraction, multiplication, and division. 
The user is prompted to enter two numbers and select an operation. The program continues running until the user chooses to exit.
*/

#include <stdio.h>

int add(int a, int b) {
    return a + b;
}

int sub(int a, int b) {
    return a - b;
}

int mul(int a, int b) {
    return a * b;
}

int div(int a, int b) {
    if (b == 0) {
        printf("Error: Division by zero is not allowed.\n");
        return 0; // Return 0 or another appropriate value
    }
    return a / b;
}

int main() {
    char flag;
    do {
        int a, b, choice;
        printf("Welcome to Simple Calculator \n");
        printf("Enter the two values: \n");
        scanf("%d %d", &a, &b);
        printf("Please select which operation you like to do: \n");
        printf("1. Addition \n");
        printf("2. Subtraction \n");
        printf("3. Multiplication \n");
        printf("4. Division \n");
        printf("5. Exit \n\n");
        scanf("%d", &choice);

        if (choice == 5) {
            printf("Terminated Successfully \n");
            break;
        }

        switch (choice) {
            case 1:
                printf("Addition: %d \n", add(a, b));
                break;
            case 2:
                printf("Subtraction: %d \n", sub(a, b));
                break;
            case 3:
                printf("Multiplication: %d \n", mul(a, b));
                break;
            case 4:
                printf("Division: %d \n", div(a, b));
                break;
            default:
                printf("Invalid choice. Please select a valid option.\n");
                break;
        }

        // Asking if user wants to continue
        do {
            printf("Do you like to continue (Y/N): ");
            scanf(" %c", &flag); // Notice the space before %c to consume newline
            if (flag == 'Y' || flag == 'N') {
                break;
            }
            printf("Invalid input. Please enter either Y or N.\n");
        } while (1);

        printf("\n");

    } while (flag == 'Y');

    printf("Thank you for using Calculator \n");
    return 0;
}

/*
Sample Input/Output:
---------------------
Welcome to Simple Calculator 
Enter the two values: 
5 3
Please select which operation you like to do:
1. Addition
2. Subtraction
3. Multiplication
4. Division
5. Exit

1
Addition: 8 
Do you like to continue (Y/N): Y

Enter the two values: 
10 2
Please select which operation you like to do:
1. Addition
2. Subtraction
3. Multiplication
4. Division
5. Exit

4
Division: 5 
Do you like to continue (Y/N): N

Thank you for using Calculator
---------------------
*/
