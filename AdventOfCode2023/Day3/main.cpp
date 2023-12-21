#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <cstdlib>

class Star{
public:
    int x, y;
    int count = 0;
    int numOne;
    int numTwo;
    Star(int x, int y) {
        this->x = x;
        this->y = y;
    }
    bool isSame(int x, int y) {
        if (x == this->x) {
            if (y == this->y) {
                return true;
            }
        }
        return false;
    }
    int multiplied() {
        return numOne * numTwo;
    }
};

std::vector<Star> starsVec;

bool checkAdjForSymbols(std::vector<std::string> &inputVec, int row, int col) {
    bool answer = false;
    for (int i = row - 1; i <= row + 1; i++) {
        if (i >= 0 && i < inputVec.size()) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (j >= 0 && j < inputVec[i].length()) {
                    char current = inputVec[i][j];
                    if (!(current - '0' >= 0 && current - '0' < 10) && current != '.') {
                        answer = true;
                    }
                }
            }
        }
    }
    return answer;
}

bool checkAdjForSymbolsAndStars(std::vector<std::string> &inputVec, int row, int startCol, int endCol, int num) {
    bool answer = false;
    for (int i = row - 1; i <= row + 1; i++) {
        if (i >= 0 && i < inputVec.size()) {
            for (int j = startCol - 1; j <= endCol + 1; j++) {
                if (i == row) {
                    if (j >= startCol && j <= endCol) {
                        continue;
                    }
                }
                
                if (j >= 0 && j < inputVec[i].length()) {
                    char current = inputVec[i][j];
                    if (!(current - '0' >= 0 && current - '0' < 10) && current != '.') {
                        answer = true;
                        if (current == '*') {
                            bool wasFound = false;
                            for (Star &star : starsVec) {
                                if (star.isSame(j, i)) {
                                    wasFound = true;
                                    if (star.count == 1) {
                                        star.numTwo = num;
                                    }
                                    star.count++;
                                    break;   
                                }
                            }
                            if (!wasFound) {
                                Star star(j, i);
                                if (star.count == 0) {
                                    star.numOne = num;
                                }
                                star.count++;
                                starsVec.push_back(star);
                            }
                        }
                    }
                }
            }
        }
    }
    return answer;
}


int main () {
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

    std::vector<std::string> testVec = {"467..114..", "...*......", "..35..633.", "......#...", 
                                        "617*......", ".....+.58.", "..592.....", "......755.",
                                        "...$.*....", ".664.598.."};

    int firstPartSum = 0;
    int secondPartSum = 0;
    int lineNum = 0;
    for (auto line : inputVec) {
        std::string num;
        bool hasSymbol = false;
        bool firstNum = true;
        size_t startIndex;
        size_t endIndex;
        for (int i = 0; i < line.length(); i++) {
            char current = line[i];
            // First Part Code
            if (current - '0' >= 0 && current - '0' < 10) {
                // Add number to string
                if (firstNum) {
                    firstNum = false;
                    num = current;
                    startIndex = i;
                    endIndex = i;
                } else {
                    num += current;
                    endIndex = i;
                }
            } else {
                if (!firstNum && checkAdjForSymbolsAndStars(inputVec, lineNum, startIndex, endIndex, stoi(num))) {
                    firstPartSum += stoi(num);
                }
                hasSymbol = false;
                firstNum = true;
            }

        }
        if (!firstNum && checkAdjForSymbolsAndStars(inputVec, lineNum, startIndex, endIndex, stoi(num))) {
            firstPartSum += stoi(num);
        }
        hasSymbol = false;
        firstNum = true;
        lineNum++;
    }

    std::cout << firstPartSum << std::endl;
    
    for (Star &star : starsVec) {
        if (star.count == 2) {
            secondPartSum += star.multiplied();
        }
    }
    
    std::cout << secondPartSum << std::endl;


    return 0;
}