#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <cstdlib>

int main() {

    std::ifstream myFile("input.txt");    
    if (!myFile.is_open()) {
        std::cerr << "Blad otwarcia pliku!";
        return 0;
    }

    std::vector<std::string> inputVec;

    std::string line;
    while(std::getline(myFile, line)) {
        inputVec.push_back(line);
    }

    // First part
    // std::vector<std::string> inputVec = {"1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"};

    // unsigned int finalSum;
    // for (std::string line : inputVec) {
    //     std::string number;
    //     bool foundFirst = false;
    //     char secondNum;
    //     for (int i = 0; i < line.length(); i++) {
    //         char current = line[i] - '0';
    //         if (current < 10 && current >= 0) {
    //             if (!foundFirst) {
    //                 foundFirst = true;
    //                 secondNum = line[i];
    //                 number = line[i];
    //             } else {
    //                 secondNum = line[i];
    //             }
    //         }
    //     }
    //     number += secondNum;
    //     finalSum += stoi(number);
    //     std::cout << number << std::endl;
    // }
    // std::cout << finalSum << std::endl;


    // Second part
    std::vector<std::string> numberTexts = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    std::vector<std::string> testVec = {"two1nine", "eightwothree", "abcone2threexyz", "xtwone3four", "4nineeightseven2", "zoneight234", "7pqrstsixteen"};

    unsigned int finalSum = 0;
    std::string finalNumber;
    for (std::string line : inputVec) {
        std::string number;
        size_t firstIndex, secondIndex = 0;
        char firstNum, secondNum;
        bool foundFirst = false;

        for (int i = 0; i < line.length(); i++) {
            char current = line[i];
            char currentNum = current - '0';
            if (currentNum >= 0 && currentNum < 10) {
                if (!foundFirst) {
                    firstNum = current;
                    secondNum = current;
                    firstIndex = i;
                    secondIndex = i;
                    foundFirst = true;
                } else { 
                    secondNum = current;
                    secondIndex = i;
                }
            }
        }

        // Sprawdzamy teraz tekstowe
        for (int i = 0; i < numberTexts.size(); i++) {
            size_t findIndex = line.find(numberTexts[i], 0);

            while (findIndex != std::string::npos) {
                // std::cout << "test: " << i << " " << findIndex << " " << firstIndex << " " << secondIndex << std::endl;
                if (!foundFirst) {
                    firstNum = i + '0';
                    firstIndex = findIndex;
                    secondNum = i + '0';
                    secondIndex = findIndex;
                    foundFirst = true;
                }
                if (findIndex < firstIndex) {
                    firstNum = i + '0';
                    firstIndex = findIndex;
                }
                if (findIndex > secondIndex) {
                    secondNum = i + '0'; 
                    secondIndex = findIndex;
                }
                findIndex = line.find(numberTexts[i], findIndex+1);
            }
        }
        number = firstNum;
        number += secondNum;
        finalSum += stoi(number);
        // std::cout << number << std::endl;
    }
    std::cout << finalSum;

    return 0;
}