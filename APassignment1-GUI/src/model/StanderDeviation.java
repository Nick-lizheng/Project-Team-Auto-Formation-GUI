package model;

/*
 * Reference: Dipto Pratyaksa
 */

public class StanderDeviation {
	public static void main(String[] args) {
		//{30,25,20,15} //goal
		double[]numArray = {9,9,7,8};
		double SD = calculateSD(numArray);
		
		double[]numberArrayGap= {1.5,0,3};
		double SDGap = calculateSD(numberArrayGap);
		
		System.out.format("Standard Deviation Overall skill= %.2f", SD);
		
	}
	
	public static double calculateSD(double numArray[]) {
		double sum = 0.0, standardDeviation = 0.0;
		int length = numArray.length;
		
		for(double num : numArray) {
			sum +=num;
		}
		
		double mean = sum/length;
		
		for(double num: numArray) {
			standardDeviation += Math.pow(num - mean,2);
		}
		
		return Math.sqrt(standardDeviation/length);
	}

}
