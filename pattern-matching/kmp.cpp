/*
	Knuth-Morris-Pratt (KMP) Pattern Matching algorithm
	Time complexity: O(m+n), where m = length of text, n = length of pattern

	@author: Er. Prahlad Panthi
	May 12, 2020
*/

#include <iostream>
#include <cstring>
#include <vector>

using namespace std;

void prefixArray(char*, int*, int);
vector<int> kmp(char*, char*, int*, int, int);

int main() {
	char text[] = "This is a sample. This sample is not good. I will make this sample good.";
	char pattern[] = "This";
	int len = strlen(pattern);
	int arr[len];
	vector<int> index = kmp(text, pattern, arr, strlen(text), len);
	if (index.empty()) {
		cout << "Not found\n";
		return 0;
	}
	len = index.size();
	for (int i = 0; i < len; ++i) {
		cout << index[i] << ", ";
	}
	cout << "\n";
	return 0;
}

// O(m), m = length of pattern
void prefixArray(char *pattern, int *arr, int len) {
	arr[0] = 0; // first is always zero as there are no prefix before that character
	int j = 0, i = 1;
	while (i < len) {
		if (pattern[j] == pattern[i]) {
			arr[i] = j + 1;
			j++;
			i++;
		} else {
			if (j != 0) j = arr[j-1];
			else arr[i++] = 0;
		}
	}
} 

vector<int> kmp(char *text, char *pattern, int *arr, int lenText, int lenPatt) {
	prefixArray(pattern, arr, lenPatt);
	int iText = 0, iPattern = 0;
	vector<int> index;
	while (iText < lenText) {
		if (text[iText] == pattern[iPattern]) {
			iText++;
			iPattern++;
			if (iPattern == lenPatt) { // match found
				index.push_back(iText - lenPatt);
				iPattern = 0;
			}
		} else {
			if (iPattern != 0) iPattern = arr[iPattern - 1];
			else {
				iText++;
			}
		}
	}
	return index;
}