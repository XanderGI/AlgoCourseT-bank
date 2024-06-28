#include <iostream>
#include <stack>
#include <vector>

using namespace std;

typedef long long ll;

struct Pair {
   int value, index;
   Pair(int value, int index) : value(value), index(index) {}
};

ll findSubArray(const vector<int> &numbers, const vector<ll> &prefSum) {
   int len = numbers.size();
   stack<Pair> leftStack, rightStack;
   vector<int> left(len, -1), right(len, len);
   ll result = 0;

   for (int i = 0; i < len; ++i) {
      while (!leftStack.empty() && numbers[leftStack.top().index] >= numbers[i])
         leftStack.pop();
      if (!leftStack.empty())
         left[i] = leftStack.top().index;
      leftStack.push({numbers[i], i});

      while (!rightStack.empty() && numbers[rightStack.top().index] > numbers[len - i - 1])
         rightStack.pop();
      if (!rightStack.empty())
         right[len - i - 1] = rightStack.top().index;
      rightStack.push({numbers[len - i - 1], len - i - 1});
   }

   for (int i = 0; i < len; ++i) {
      ll sum = prefSum[right[i]] - (left[i] == -1 ? 0 : prefSum[left[i] + 1]);
      result = max(result, sum * numbers[i]);
   }
    return result;
}

int main() {
   ios::sync_with_stdio(false);
   cin.tie(nullptr);
   cout.tie(nullptr);

    int len;
    cin >> len;
    vector<int> numbers(len);
    vector<ll> prefSum(len + 1, 0);

    for (int i = 0; i < len; ++i) {
        cin >> numbers[i];
        prefSum[i + 1] = prefSum[i] + numbers[i];
    }

    cout << findSubArray(numbers, prefSum) << '\n';
    return 0;
}
