class Driver {
	public static void main(String[] args) {
		int[] a = {43,12,23,0,1,3,2,5,4,6,10,12,11,100,50,87,67,87,88,
					102, 561, 91, 76, 123,345,543,13,9,30,41,45,67,89,90,54,45,43,23,233,
					211,212,323,3456,5433,9999,1231};
		Sort.qsort(a, 0, a.length-1);
		for (int i : a) 
			System.out.print(i + ", ");
		System.out.println();
	}
}