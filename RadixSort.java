package RadixSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*******************************************************************************************************
 
 Radix sort is a famous integer sorting algorithm.
 
 The sorting model used by it involves successive comparisons at the digit level, 
 	the whole collection of numbers being ordered at each step according to the values ​​of the digits on a certain position
 	(i.e. corresponding to the current step / iteration).
 
 
 The model implemented uses 10 queues (i.e. one for each digit from 0 to 9) 
 	and works by sorting the numbers step by step, depending on the values ​​of the digits on a certain position, namely:

		> in the first iteration the numbers will be sorted by the number of units, then,
		> in the second after the number of tens,
		> in the thirt after the number of hundreds,
		> etc
		
 After each sorting step (i.e. iteration), 
	the numbers are returned to the "pseudo-ordered" collection according to the value of the digits on the sorted position
 	at the current step. Let's further analyze an example.
 
 *******************************************************************************************************/

public class RadixSort {

	private static List<String> zeroPadCollection(List<Integer> collection, int digits) {

		String paddingFormat = "%0" + digits + "d";
		List<String> paddedCollection = new ArrayList<>();
		for (Integer number : collection) {
			String paddedString = String.format(paddingFormat, number).replace(' ', '0');
			paddedCollection.add(paddedString);
		}
		return paddedCollection;
	}
	
	public static void main(String[] args) {

		boolean ascending = false;
		List<Integer> collection = Arrays.asList(1000, 4, 25, 319, 88, 51, 3430, 8471, 701, 1, 2989, 657, 713);

		int digits = String.valueOf(Collections.max(collection)).length();

		List<String> paddedCollection = zeroPadCollection(collection, digits);

		for (int digit = 1; digit <= digits; digit++) { 
			List<Queue<String>> queues = new ArrayList<>();
			for (int i = 0; i <= 10; i++) {
				queues.add(new LinkedList<>());
			}

			for (String number : paddedCollection) {
				for (int queueNo = 0; queueNo < queues.size(); queueNo++) {
					if (Character.getNumericValue(number.charAt(number.length() - digit)) == queueNo) {
						queues.get(queueNo).add(number);
						break;
					}
				}
			}

			paddedCollection = new ArrayList<>();
			for (Queue<String> queue : queues) {
				paddedCollection.addAll(queue);
			}
			System.out.println(paddedCollection);
		}

		if (ascending) {
			System.out.println(paddedCollection);
		} else {
			Collections.reverse(paddedCollection);
			System.out.println(paddedCollection);
		}
	}

}
