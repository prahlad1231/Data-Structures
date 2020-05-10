// interpolatiom search
// O(log(log(n)))
// mid = low + (((key - arr[low]) * (high - low)) / (h-l))
// better version of binary search (also jump search)
// @author Er. Prahlad Panthi

#include <iostream>

using namespace std;

int interpolationSearch(int*, int, int);

int main() {
	int arr[] = {1, 3, 5, 7, 8, 9, 11, 12, 14, 16, 17, 
		19, 21, 23, 25, 28, 30, 35, 40, 41, 42, 45, 50};
	int len = sizeof(arr) / sizeof(arr[0]);
	int key = 10;
	int index = interpolationSearch(arr, key, len);
	if (index != -1) {
		cout << "Found at index: " << index << "\n";
	} else {
		cout << "Not found!\n";
	}
	return 0;
}

int interpolationSearch(int *arr, int key, int len) {
	int low = 0, mid = 0, high = len - 1;
	while (key >= arr[low] && key <= arr[high]) {
		mid = low + (((key - arr[low]) * (high - low)) / (arr[high] - arr[low]));
		if (key > arr[mid])
			low = mid + 1;
		else if (key < arr[mid])
			high = mid - 1;
		else 
			return mid;
	}
	//if (key == arr[low]) return low;
	return -1;
}
