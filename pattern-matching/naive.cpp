/*
	Naive implementation of pattern matching
	Worst case time complexity: O(m+(n-m+1)), where n = length of text, m = length of pattern
	
	@author Er. Prahlad Panthi
	May 11, 2020
*/

#include <iostream>
#include <cstring>
#include <vector>

using namespace std;

vector<int> naiveSearch(char*, char*, int, int);

int main() {
	char text[] = "This is a nice Apple. I like it. Apple is tasty.";
	char pattern[] = "Apple";
	vector<int> index = naiveSearch(text, pattern, strlen(text), strlen(pattern));
	if (index.empty()) {
		cout << "Not found\n";
	} else {
		cout << "Found in index: ";
		for (int i = 0; i < index.size(); ++i) {
			cout << index[i] << ", ";
		}
		cout << "\n";
	}
	return 0;
}

vector<int> naiveSearch(char *text, char *pattern, int len1, int len2) {
	int len = len1 - len2;
	vector<int> index;
	for (int t = 0; t < len; ++t) {
		int p;
		for (p = 0; p < len2; ++p) {
			if (text[t+p] != pattern[p]) break;
		}
		if (p == len2) index.push_back(t);
	}
	return index;
}