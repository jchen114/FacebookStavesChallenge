package stavesChallenge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			ArrayList<Integer> myStick = new ArrayList<Integer>();
			/*Integer input;
			
			while((input=Integer.parseInt(br.readLine()))!=null){
				if (input == 0) {
					System.out.println("terminate");
					break;
				} else {
					myStick.add(input);
				}
			}*/
			
			// 1 2 3 2 3 2 1 1 1 1 1 9 2 3 2 3 3 3 2 7 7 7 7 7 9 9 9
			myStick.add(1); myStick.add(2); myStick.add(3); myStick.add(2);
			myStick.add(3); myStick.add(2); myStick.add(1); myStick.add(1);
			myStick.add(1); myStick.add(1); myStick.add(1); myStick.add(9);
			myStick.add(2); myStick.add(3); myStick.add(2); myStick.add(3);
			myStick.add(3); myStick.add(3); myStick.add(2); myStick.add(7);
			myStick.add(7); myStick.add(7); myStick.add(7); myStick.add(7);
			myStick.add(9); myStick.add(9); myStick.add(9);
	
			// Now I have the stick, check if it is even or odd
			// if Even max stave length is just half, o.w it is (n-1)/2
			int maxStaveLength = (myStick.size()%2==1)?((myStick.size()-1) / 2):(myStick.size()/2);
			// Find all staves beginning with this maxStaveLength
			Integer[] answer = findStaves(myStick, maxStaveLength);
			System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
			
		//}catch(IOException io){
		//	io.printStackTrace();
		//}	
	}
	
	private static Integer[] findStaves(ArrayList<Integer> stick, Integer staveLength) {
		Integer[] toReturn = new Integer[3];
		// Iterate through staves
		Integer lastIndexForStave = stick.size() - staveLength*2 + 1;
		Integer currentIndex = 0;
		if (staveLength == 0) {
			toReturn[0] = 0;
			toReturn[1] = 1;
			toReturn[2] = 2;
			return toReturn;
		}
		while (currentIndex < lastIndexForStave) {
			// Get the reference stave
			ArrayList <Integer> stave = new ArrayList<Integer>();
			for (int i = 0; i < staveLength; i ++) {
				stave.add(stick.get(currentIndex + i));
			}
			// Get the rest of the staves to compare
			Integer startingIndexOfNextStave = currentIndex + staveLength;
			while (startingIndexOfNextStave < stick.size() - staveLength + 1) {
				ArrayList <Integer> staveToCompare = new ArrayList<Integer>();
				for (int i = 0; i < staveLength; i ++) {
					staveToCompare.add(stick.get(startingIndexOfNextStave + i));
				}
				// Compare 4 cases to see if center of mass is reached
				// Storing reversed staves
				ArrayList <Integer> reversedOriginalStave = new ArrayList<Integer>(stave);
				Collections.reverse(reversedOriginalStave);
				ArrayList <Integer> reversedStaveToCompare = new ArrayList<Integer>(staveToCompare);
				Collections.reverse(reversedStaveToCompare);
				// Build the staves
				ArrayList <Integer> firstCase = new ArrayList<Integer>(stave);
				firstCase.addAll(staveToCompare);
				ArrayList <Integer> secondCase = new ArrayList<Integer>(stave);
				secondCase.addAll(reversedStaveToCompare);
				ArrayList <Integer> thirdCase = new ArrayList<Integer>(reversedOriginalStave);
				thirdCase.addAll(staveToCompare);
				ArrayList <Integer> fourthCase =  new ArrayList<Integer>(reversedOriginalStave);
				fourthCase.addAll(reversedStaveToCompare);
				for (int i = 0; i < 4; i ++) {
					float weightedSum = 0;
					float unWeightedSum = 0;
					float centerOfMass = 0.0f;
					switch (i) {
						case 0:
						{
							for (int j = 0; j < firstCase.size(); j ++) {
								weightedSum +=firstCase.get(j) * (j+1);
								unWeightedSum += firstCase.get(j);
							}
						}
							break;
						case 1:
							for (int j = 0; j < secondCase.size(); j ++) {
								weightedSum += secondCase.get(j) * (j+1);
								unWeightedSum += firstCase.get(j);
							}
							break;
						case 2:
							for (int j = 0; j < thirdCase.size(); j ++) {
								weightedSum += thirdCase.get(j) * (j+1);
								unWeightedSum += firstCase.get(j);
							}
							break;
						case 3:
							for (int j = 0; j < fourthCase.size(); j ++) {
								weightedSum += fourthCase.get(j) * (j+1);
								unWeightedSum += firstCase.get(j);
							}
							break;
					}
					centerOfMass = weightedSum/unWeightedSum;
					System.out.println("unWeightedSum = " + unWeightedSum);
					System.out.println("weightedSum = " + weightedSum);
					System.out.println("center of mass = " + centerOfMass);
					System.out.println("staveLength = " + staveLength);
					if (centerOfMass == staveLength + 0.5f) {
						// Found the staves
						toReturn[0] = currentIndex;
						toReturn[1] = startingIndexOfNextStave;
						toReturn[2] = staveLength;
						return toReturn;
					}
				}
				// Increment to find next stave
				startingIndexOfNextStave ++;
			}
			currentIndex ++;
		}
		// Find the next smallest staveLength
		staveLength --;
		toReturn = findStaves(stick, staveLength);
		return toReturn;
	}
}
