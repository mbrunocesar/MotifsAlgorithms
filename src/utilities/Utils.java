package utilities;

public class Utils {

	public static void printArray(int[] array) {
		printArray(array, " ");
	}
	
	public static void printArray(int[] array, String separator) {
		boolean isintFirst = false;
		for (Object element : array) {
			if (isintFirst) {
				System.out.print(separator+element);
			} else {
				System.out.print(element);
				isintFirst = true;
			}
		}
		System.out.println();
	}
	

}
