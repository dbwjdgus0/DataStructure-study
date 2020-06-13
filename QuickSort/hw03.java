
// HW 203, QuickSort
// Name : Yoo JungHyun
// Student ID : 20151142

import java.util.*;

class QuickSort {
	int[] arr; // array
	int arrSize; // number of elements in arr

	QuickSort() {
		arr = new int[1024];
		arrSize = 0;
	}

	void Show(int s, int e) {
		// Show all the element in the arr
		if (s > e)
			return;
		String str = new String();
		str = "arr : ";

		// print all the nodes in the arr
		for (int i = 0; i < arrSize; i++) {
			if (i == s)
				str += "[";
			else
				str += " ";
			str += arr[i];
			if (i == e)
				str += "]";
			else
				str += " ";
		}
		System.out.println(str);
	}

	void Init(int[] es, int n) {
		// fill the arr array by the input
		arrSize = n;
		for (int i = 0; i < n; i++)
			arr[i] = es[i];
	}

	void Sort() {
		// sort arr[0:arrSize-1] into nonincreasing order
		// This is an invoking method to the Partition() and QSort()
		QSort(0, arrSize - 1); // quick sort from 0 to n-1
	}

	void QSort(int s, int e) {
		// sort arr[s:e] into nonincreasing order
		System.out.println("Sort in [" + s + "," + e + "]");
		Show(s, e);
		int piv;
		if (s >= e)
			return;
		else {
			piv = Partition(s, e);
			QSort(s, piv - 1);
			QSort(piv + 1, e);
		}

	}

	int Partition(int s, int e) {

		int ret = 0;

		if (e - s == 1) {

			int f = arr[s];
			int l = arr[e];
			if (f > l)
				return s;
			else {
				arr[s] = l;
				arr[e] = f;
				return e;
			}

		}
		setPivot(s, e);
		int pivot = arr[s];
		while (true) {
			int left = 0;
			int right = 0;
			int ind_l = 0;
			int ind_r = 0;

			for (int i = s + 1; i <= e; i++) {
				if (arr[i] <= pivot) {
					left = arr[i];
					ind_l = i;
					break;
				}
			}
			for (int j = e; j > s; j--) {
				if (arr[j] >= pivot) {
					right = arr[j];
					ind_r = j;
					break;
				}
			}

			if (ind_l >= ind_r) {
				arr[s] = right;
				arr[ind_r] = pivot;
				ret = ind_r;
				break;
			} else {
				arr[ind_l] = right;
				arr[ind_r] = left;
			}

		}
		return ret;
	}

	void setPivot(int s, int e) {

		if (s >= e)
			return;

		int f = arr[s];
		int l = arr[e];
		int m = arr[(s + e) / 2];
		if (f > l) {
			if (l > m) {
				arr[s] = l;
				arr[e] = f;
			}

			else {
				if (f > m) {
					arr[s] = m;
					arr[(s + e) / 2] = f;
				} else {
					// pivot is f (do nothing)
				}
			}
		} else {
			if (f > m) {
				// pivot is f (do nothing)
			} else {
				if (l > m) {
					// pivot is m
					arr[s] = m;
					arr[(s + e) / 2] = f;
				} else {
					// pivot is l
					arr[s] = l;
					arr[e] = f;
				}
			}
		}

	}

}
