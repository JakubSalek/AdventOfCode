#include <iostream>
#include <fstream>
#include <vector>
#include <sstream>
#include <string>

class ColorCount{
public:
    int red;
    int blue;
    int green;
    ColorCount() {
        red = 0;
        green = 0;
        blue = 0;
    }
};

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

    std::vector<std::vector<ColorCount>> allGames;

    for (int i = 0; i < inputVec.size(); i++) {
        std::string gameLine = inputVec[i].substr(inputVec[i].find(':') + 2);


        std::istringstream sGameLine(gameLine);
        std::string iterations;
        std::vector<ColorCount> gameLineVector;
        while (std::getline(sGameLine, iterations, ';')) {
            std::istringstream sIterations(iterations);
            std::string iteration;
            ColorCount iteractionObj;
            while (std::getline(sIterations, iteration, ',')) {
                std::stringstream sIteration(iteration);
                std::string color = "x";
                int count = 0;
                sIteration >> count >> color;

                if (color.compare("red") == 0) {
                    iteractionObj.red = count;
                } else if (color.compare("green") == 0) {
                    iteractionObj.green = count;
                } else if (color.compare("blue") == 0) {
                    iteractionObj.blue = count;
                }

            }
            gameLineVector.push_back(iteractionObj);
        }
        allGames.push_back(gameLineVector);
    }

    int i = 1;
    int sum = 0;
    int partTwoSum = 0;
    for (auto gameLine : allGames) {
        bool isValid = true;
        int fewestRed = 0, fewestGreen = 0, fewestBlue = 0;
        for (auto game : gameLine) {
            if (game.red > 12 || game.blue > 14 || game.green > 13) {
                isValid = false;
            }
            if (game.red > fewestRed) {
                fewestRed = game.red;
            }
            if (game.blue > fewestBlue) {
                fewestBlue = game.blue;
            }
            if (game.green > fewestGreen) {
                fewestGreen = game.green;
            }
        }
        if (isValid) {
            sum += i;
        }
        partTwoSum += fewestBlue * fewestGreen * fewestRed;
        i++;
    }

    std::cout << sum << std::endl << partTwoSum << std::endl;

    return 0;
}