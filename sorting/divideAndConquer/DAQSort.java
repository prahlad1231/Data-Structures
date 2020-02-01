class DAQSort {

	/*
		Merge sort
		Worst Time Complexity - O(nlogn)
	*/
	public static void mergeSort(int[] arr, int l, int r) {
		if (l < r) {
			int mid = (l + r) / 2;
			mergeSort(arr, l, mid);
			mergeSort(arr, mid+1, r);
			merge(arr, l, mid, r);
		}
	}

	private static void merge(int[] arr, int l, int mid, int r) {
		// to find the sizes of sub-arrays that are to be merged
		// ls = size of left half, rs = size of right half
		int ls = mid - l + 1;
		int rs = r - mid;

		// create temp array to hold left and right half
		int[] arrL = new int[ls];
		int[] arrR = new int[rs];

		for (int i = 0; i < ls; ++i)
			arrL[i] = arr[l+i];
		for (int i = 0; i < rs; ++i)
			arrR[i] = arr[mid+1+i];

		/*
			i = current index of left subarray
			j = current index of right subarray
			k = current index of original array
		*/

		int i = 0, j = 0;
		int k = l;

		while (i < ls && j < rs) {
			if (arrL[i] < arrR[j])
				arr[k] = arrL[i++];
			else 
				arr[k] = arrR[j++];
			++k;
		}

		// copy the leftovers of either subarray in main array
		while (i < ls) 
			arr[k++] = arrL[i++];
		while (j < rs)
			arr[k++] = arrR[j++];
	}

	/*
		Quick Sort 
		Worst case Time complexity: O(nlogn)
	*/

	public static void qsort(int[] arr, int low, int high) {
		int pi; // pivot element
		if (low < high) {
			pi = partition(arr, low, high); // arr[pi] will be on right place of sorted array
			qsort(arr, low, pi-1);
			qsort(arr, pi+1, high);
		}
	}

	private static int partition(int[] arr, int low, int high) {
		/*
			i = index of latest smaller element than pivot
			j = current index in current iteration
			pivot = random element i.e. last element of array
		*/

		int i, j, pivot, temp;
		pivot = arr[high];
		i = low - 1;

		for (j = low; j <= high-1; ++j) {
			if (arr[j] < pivot) {
				++i;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;

		return i+1;
	}
}