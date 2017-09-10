public class Lab03_CastroRolfSheehan{
	//TODO MAXIMUM-SUBARRAY USING BRUTE FORCE TAKING THETA(n^2).
	//TODO MAXIMUM-SUBARRAY USING DIVIDE-AND-CONQUER ALGORITHM TAKING THETA(nlog(n)).
	
	//TODO Implement maxSubarray.
	public static void main(String[] args){
		//int[] list = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

		//XXX TEST.
		int[] list = {-9, 2, -88, 70, -99, 4, 10, 13, 0, -5};
		//int[] list = {99, 100, 101, 102, 103, 104, 105};

		int[] values = new int[3];
		values = maxSubarray(list, 0, 9);
		//XXX TEST.
		//values = maxSubarray(list, 0, 6);

		//After maxSubarray.
		System.out.println("The Maximum-Subarray starts at index " + values[0] + " and ends at " + values[1] + " with a sum of " + values[2] + ".");
	}

	//XXX Brute Force [Theta(n^2)].
	//public static int[] bruteForce(int[] arr, int low, int high){
		//XXX Should have a level 2 nested loop.
	//}

	//XXX Divide and Conquer algorithm [Theta(nlog(n))].
	//XXX Takes array and keeps halving until only 1 element.
	public static int[] maxSubarray(int[] arr, int low, int high){
		/**
		 * Index 0 = low
		 * Index 1 = high
		 * Index 2 = sum
		 */
		int[] leftValues = new int[arr.length];
		int[] rightValues = new int[arr.length];
		int[] crossValues = new int[arr.length];
		
		//Base case.
		if(high == low){
			crossValues[0] = low;
			crossValues[1] = high;
			crossValues[2] = arr[low];
			return crossValues;
		}else{
			int mid = (int)((low + high)/2);

			//int leftLow = leftValues[0];
                        //int leftHigh = leftValues[1];
                        //int leftSum = leftValues[2];
                        //                   
                        //int rightLow = rightValues[0];
                        //int rightHigh = rightValues[1];
                        //int rightSum = rightValues[2];
                        //                   
                        //int crossLow = crossValues[0];
                        //int crossHigh = crossValues[1];
                        //int crossSum = crossValues[2];

			leftValues = maxSubarray(arr, low, mid);
			rightValues = maxSubarray(arr, (mid + 1), high);
			crossValues = maxCrossingSubarray(arr, low, mid, high);

			//if(leftSum > rightSum && leftSum > crossSum){
			//XXX TEST.
			if(leftValues[2] >= rightValues[2] && leftValues[2] >= crossValues[2]){
				return leftValues;
			//}else if(rightSum > leftSum && rightSum > crossSum){
			//XXX TEST.
			}else if(rightValues[2] >= leftValues[2] && rightValues[2] >= crossValues[2]){
				return rightValues;
			}else{
				return crossValues;
			}
		}
	}

	//XXX Find array of highest value.
	public static int[] maxCrossingSubarray(int[] arr, int low, int mid, int high){
		int[] values = new int[arr.length];
		int sum = 0;
		int maxLeft = 0;
		//Integer.MAX_VALUE equates, more or less, infinity.
		int leftSum = -(Integer.MAX_VALUE);
		//XXX TEST.
		System.out.println("Left sum: " + leftSum);

		for(int i = mid; i > low; i--){
			sum = sum + arr[i];
			//XXX TEST.
			System.out.println(sum);

			if(sum > leftSum){
				leftSum = sum;
				maxLeft = i;
			}
		}

		int maxRight = 0;
		//Integer.MAX_VALUE equates, more or less, infinity.
		int rightSum = -(Integer.MAX_VALUE);
		//XXX TEST.
		System.out.println("Right sum: " + rightSum);
		sum = 0;

		for(int j = mid + 1; mid < high; mid++){
			sum = sum + arr[j];
			//XXX TEST.
			System.out.println(sum);

			if(sum > rightSum){
				rightSum = sum;
				maxRight = j;
			}
		}

		values[0] = maxLeft;
		values[1] = maxRight;
		values[2] = leftSum + rightSum;

		return values;
	}
}
