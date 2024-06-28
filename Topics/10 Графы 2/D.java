#include <iostream>
#include <set>
#include <vector>
#include <sstream>

std::string solveTask(int rounds, const std::vector<int>& attack, const std::vector<int>& health);
bool isDead(const std::vector<int>& attacks, const std::vector<int>& healths, const std::set<int>& alive, int mob);

int main() {
   std::ios::sync_with_stdio(false);
   std::cin.tie(nullptr);
   std::cout.tie(nullptr);
    int rounds;
    std::cin >> rounds;
    std::vector<int> attacks(rounds), healths(rounds);
    for (int i = 0; i < rounds; ++i) {
      std::cin >> attacks[i];
    }
    for (int i = 0; i < rounds; ++i) {
       std::cin >>healths[i];
    }
    std::cout << solveTask(rounds, attacks, healths);
    return 0;
}

std::string solveTask(int rounds, const std::vector<int>& attack, const std::vector<int>& health) {
    int n = rounds;
    std::vector<int> attacks(n), healths(n);
    for (int i = 0; i < n; i++) {
        attacks[i] = attack[i];
        healths[i] = health[i];
    }
    std::set<int> alive, dead;
    for (int i = 0; i < n; i++) {
        alive.insert(i);
    }
    for (int mob : alive) {
        if (isDead(attacks, healths, alive, mob)) {
            dead.insert(mob);
        }
    }
    std::stringstream result;
    for (int i = 0; i < n; i++) {
        for (int mob : dead) {
            alive.erase(mob);
        }
        result << dead.size() << " ";
        std::set<int> temp;
        for (int mob : dead) {
            auto leftIt = alive.lower_bound(mob);
            auto rightIt = alive.upper_bound(mob);

            int leftAlive = (leftIt != alive.begin()) ? *std::prev(leftIt) : -1;
            int rightAlive = (rightIt != alive.end()) ? *rightIt : -1;

            if (leftAlive != -1 && isDead(attacks, healths, alive, leftAlive)) {
                temp.insert(leftAlive);
            }
            if (rightAlive != -1 && isDead(attacks, healths, alive, rightAlive)) {
                temp.insert(rightAlive);
            }
        }
        dead = std::move(temp);
    }
    return result.str();
}

bool isDead(const std::vector<int>& attacks, const std::vector<int>& healths, const std::set<int>& alive, int mob) {
    auto leftIt = alive.lower_bound(mob);
    auto rightIt = alive.upper_bound(mob);

    int left = (leftIt != alive.begin()) ? *std::prev(leftIt) : -1;
    int right = (rightIt != alive.end()) ? *rightIt : -1;

    int currHealth = healths[mob];

    if (left != -1) {
        currHealth -= attacks[left];
    }
    if (right != -1) {
        currHealth -= attacks[right];
    }

    return currHealth < 0;
}