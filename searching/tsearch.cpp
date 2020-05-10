// ternary search
// similar to binary search except we now divide the array
// into three parts and calculate mid1 and mid2
// mid1 = l + (r - l) / 3
// mid2 = r - (r - l) / 3

#include <iostream>

using namespace std;

int ternarySearch(int*, int, int, int);

int main() {
	int arr[] = {1, 2, 5, 6, 7, 9, 12, 15, 17, 19, 20, 21,
		23, 25, 28, 29, 32, 36, 37, 41, 48, 50, 60, 62, 70};
	int len = sizeof(arr) / sizeof(arr[0]);
	int key = 2;
	int index = ternarySearch(arr, 0, len, key);
	if (index != -1) {
		cout << "Found at index: " << index << "\n";
	} else {
		cout << "Not found \n";
	}
	return 0;
}

int ternarySearch(int *arr, int l, int r, int key) {
	if (r >= l) {
		int mid1 = l + (r - l) / 3;
		int mid2 = r - (r - l) / 3;
		if (arr[mid1] == key)
			return mid1;
		if (arr[mid2] == key)
			return mid2;
		if (key < arr[mid1])
			return ternarySearch(arr, l, mid1 - 1, key);
		else if (key > arr[mid2])
			return ternarySearch(arr, mid2 + 1, r, key);
		else 
			return ternarySearch(arr, mid1 + 1, mid2 - 1, key);
	}
	return -1;
}
