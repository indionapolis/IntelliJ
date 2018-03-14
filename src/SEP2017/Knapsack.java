package SEP2017;/*
 *	SEP2017.Knapsack.java
 *
 *  Created on: May 06, 2010
 *  Author: 	Matthew Coleman
 *  For: 		CS 330 Spring 2010, Bonus
 *  
 *  For use with CS330bonus.java.
 *
 */

import java.io.File;
import java.util.*;

public class Knapsack {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("in.txt"));
		Knapsack pac = new Knapsack(20);
		int[] weight = new int[20];
		int[] value = new int[20];

		List<Knapsack.KnapsackItem> pec = new LinkedList();
		Knapsack.Algorithm algorithm = Knapsack.Algorithm.GREEDY;

		for (int i = 0; i < 20; i++) {
			weight[i] = in.nextInt();
		}
		for (int i = 0; i < 20; i++) {
			value[i] = in.nextInt();
		}
		for (int i = 0; i < 20; i++) {
			Knapsack.KnapsackItem item = new Knapsack.KnapsackItem(0,0,0);
			item.index = i;
			item.weight = weight[i];
			item.value = value[i];
			pec.add(item);
		}
		System.out.println(pec.toString());


		System.out.println(pac.toString());
		System.out.println(pac.solveKnapsack(pec, algorithm));
	}



	public static enum Algorithm { DYNAMIC, RECURSION, GREEDY, HEURISTIC }
		// enumeration for solving algorithms' calls

	private int actualCapacity;								// original capacity
	private int currentCapacity = 0;						// mutuable capacity of the current knapsack
	private int value = 0;									// value of the current knapsack
	private List<Integer> items = new ArrayList<Integer>();	// array if item indecies relating to
															// the given item set

	List<KnapsackItem> globalItemSet;				// used for recursion
	int[][] globalTable;							// used for recursion

	/* SEP2017.Knapsack constructor */
	public Knapsack( int capacity) {
		actualCapacity = capacity;
		currentCapacity = actualCapacity;
	}
	
	/* 
	 * solveKnapsack
	 * 
	 * INPUT:	List<KnapsackItem> itemSet		set of candidate items for the knapsack
	 * 			Algorithm algorithmSig			specifies which algorithm to perform
	 * 
	 * OUTPUT:	running time of the algorithm
	 * 
	 * multiplexes to a solution method for constructing the selected items for
	 * this knapsack, based on the set of items itemSet and the current knapsack
	 * capacity, currentCapacity
	 * 
	 */
	public long solveKnapsack(List<KnapsackItem> itemSet, Algorithm algorithmSig) throws Exception {
				
		long start = System.currentTimeMillis();
			
		switch( algorithmSig) {
			case DYNAMIC:
				dynamic( itemSet);
				break;
			case RECURSION:
				recursion( itemSet);
				break;
			case GREEDY:
				greedy( itemSet);
				break;
			case HEURISTIC:
				heuristic( itemSet);
				break;
			default:
				throw new Exception( "Invalid algorithm.");
		}
		
		long end = System.currentTimeMillis();
		
		return (end - start);
	}

	/* 
	 * heuristic
	 * 
	 * INPUT:	List<KnapsackItem> itemSet		set of candidate items for the knapsack
	 * 
	 * produces a possibly suboptimal selction of items from itemSet for this knapsack 
	 * based on a heuristic method of producing value/weight ratios for each item and 
	 * greedily selecting them
	 * 
	 */
	private void heuristic(List<KnapsackItem> itemSet) {
		
		List<KnapsackItem> orderedItemSet = new ArrayList<KnapsackItem>();	// ordered set of items
		
		KnapsackItem nextItem;			// next knapsack item
		int nextIndex;					// next knapsack item index
		int nextWeight;					// next knapsack item weight
		double nextRatio;				// next knapsack ratio
		double nextComparitveRatio;		// next comparitave ratio
		
		// insertion sort based on value/weight ratios
		boolean added;
		
		orderedItemSet.add( itemSet.get(0));
		
		for( int i = 1; i < itemSet.size(); i++) {
			
			added = false;
									
			nextItem = itemSet.get(i);
			nextRatio = (double)nextItem.value / (double)nextItem.weight; 
			
			for( int j = 0; j < orderedItemSet.size(); j++) {
				nextComparitveRatio = (double)orderedItemSet.get( j).value / (double)orderedItemSet.get( j).weight;
				
				if( nextComparitveRatio >= nextRatio) {
					orderedItemSet.add( j, nextItem);
					added = true;
					break;
				}
			}
			
			if( !added) orderedItemSet.add( nextItem);
		}
			
		// for each item in orderedItemSet, find the biggest that will fit,
		while( currentCapacity > 0 && !orderedItemSet.isEmpty()) {
		
			// get next largest item and weight
			nextItem = orderedItemSet.remove( orderedItemSet.size() - 1);
			nextIndex = nextItem.index;
			nextWeight = nextItem.weight;
			
			// if the next weight is greater than the capacity, continue
			if( nextWeight > currentCapacity) continue;
			
			// add it to the knapsack
			items.add( nextIndex);
			value += nextItem.value;
			
			// set local capacity
			currentCapacity -= nextWeight;	
		}
	}

	/* 
	 * greedy
	 * 
	 * INPUT:	List<KnapsackItem> itemSet		set of candidate items for the knapsack
	 * 
	 * produces a possibly suboptimal selction of items from itemSet for this knapsack 
	 * based on a greedy method of selecting the closest possibility (under weight) with 
	 * the highest value
	 * 
	 */
	private void greedy( List<KnapsackItem> itemSet) {
		
		List<KnapsackItem> orderedItemSet = new ArrayList<KnapsackItem>();	// ordered set of items
		
		KnapsackItem nextItem;			// next knapsack item
		int nextIndex;					// next knapsack item index
		int nextValue;					// next knapsack item value
		int nextWeight;					// next knapsack item weight
				
		// insertion sort based on weight values
		boolean added;
		
		orderedItemSet.add( itemSet.get(0));
		
		for( int i = 1; i < itemSet.size(); i++) {
			
			added = false;
									
			nextItem = itemSet.get(i);
			nextValue = nextItem.value;
			
			for( int j = 0; j < orderedItemSet.size(); j++) {
				if( orderedItemSet.get( j).value >= nextValue) {
					orderedItemSet.add( j, nextItem);
					added = true;
					break;
				}
			}
			
			if( !added) orderedItemSet.add( nextItem);
		}
			
		// for each item in orderedItemSet, find the biggest that will fit,
		while( currentCapacity > 0 && !orderedItemSet.isEmpty()) {
					
			// get next largest item and weight
			nextItem = orderedItemSet.remove( orderedItemSet.size() - 1);
			nextIndex = nextItem.index;
			nextWeight = nextItem.weight;
									
			// if the next weight is greater than the capacity, continue
			if( nextWeight > currentCapacity) continue;
									
			// add it to the knapsack
			items.add( nextIndex);
			value += nextItem.value;
			
			// set local capacity
			currentCapacity -= nextWeight;	
		}
	}

	/* 
	 * dynamic
	 * 
	 * INPUT:	List<KnapsackItem> itemSet		set of candidate items for the knapsack
	 * 
	 * produces an optimal selction of items from itemSet for this knapsack based on a bottom-
	 * up, dynamic programming method of finding optimal solutions to subproblems based 
	 * on specific subproblems' maximum weight allowance w, where w < currentCapacity; 
	 * and set of items [0 .. k] where k < itemSet.size() 
	 * 
	 */
	private void dynamic(List<KnapsackItem> itemSet) {
		
		int nextWeight;			// next considered weight
		int nextValue;			// next considered value
		
		int nextWeightOffset;	// an offset weight compared to nextWeight
		
		// construct and initialize array
		int width = actualCapacity + 1;
		int height = itemSet.size() + 1;
		int[][] table = new int[width][height];
		
		for( int i = 0; i < width; i++) {
			table[i][0] = 0;
		}
		
		for( int i = 0; i < height; i++) {
			table[0][i] = 0;
		}
		
		// construct table 
		for( int i = 1; i < height; i++) {
			
			nextWeight = itemSet.get(i - 1).weight;
			nextValue = itemSet.get(i - 1).value;
			
			for( int j = 1; j < width; j++) {	
				
				if( nextWeight <= j) {
				
					nextWeightOffset = j - nextWeight;
					
					table[j][i] = table[j][i - 1] > ( table[nextWeightOffset][i - 1] + nextValue) ?
							table[j][i - 1] : ( table[nextWeightOffset][i - 1] + nextValue);
					
				}
				else {
					table[j][i] = table[j][i - 1];
				}
			}
		}
		
		// find knapsack items from the table results
		int j = width - 1;
		for( int i = height - 1; i > 0; i--) {
						
			if( table[j][i] != table[j][i - 1]) {
				// found an item to be added
				items.add( i - 1);
				j -= itemSet.get(i - 1).weight;
				value += itemSet.get(i - 1).value;
			}
			
			if( j < 0) break;
		}
	}
	
	/* 
	 * recursion
	 * 
	 * INPUT:	List<KnapsackItem> itemSet		set of candidate items for the knapsack
	 * 
	 * produces a selction of items from itemSet for this knapsack based on a top-down
	 * memory function algorithm which discovers which subproblems must be solved in order
	 * to derivate the optimal set of items
	 * 
	 */
	private void recursion(List<KnapsackItem> itemSet) {
		
		// set itemSet global for recursion
		globalItemSet = new ArrayList<KnapsackItem>( itemSet);
		
		// construct and initialize array
		int width = globalItemSet.size() + 1;
		int height = actualCapacity + 1;
		globalTable = new int[width][height];
		
		for( int i = 0; i < width; i++) {
			for( int j = 0; j < height; j++) {
				globalTable[i][j] = -1;
			}
		}
		
		for( int i = 0; i < width; i++) {
			globalTable[i][0] = 0;
		}
		
		for( int i = 0; i < height; i++) {
			globalTable[0][i] = 0;
		}
		
		// begin recursion		
		memoryFunction( width - 1, height - 1);
		
		// find knapsack items
		int k = height - 1;
		for( int i = width - 1; i > 0; i--) {
			if( globalTable[i][k] != globalTable[i - 1][k]) {
				items.add( i - 1);
				k -= itemSet.get(i - 1).weight;
				value += itemSet.get(i - 1).value;
			}
		}
			
	}

	/* 
	 * memoryFunction
	 * 
	 * INPUT:	int i		next item subproblem parameter
	 * 			int j		next weight subproblem parameter
	 * 
	 * OUTPUT:	int result	the next value to place in a cell
	 *
	 * produces recursive subproblem finding and solving for the recursion knapsack
	 * solver, filling in the appropriate subproblem results in a results table
	 * 
	 */
	private int memoryFunction( int i, int j) {
		
		int result = globalTable[i][j];
		int nextWeight;
		
		if( globalTable[i][j] < 0) {
			
			nextWeight = globalItemSet.get(i - 1).weight;
			
			if( j < nextWeight) {
				result = memoryFunction( i - 1, j);
			}
			else {
				result = Math.max( memoryFunction( i - 1, j), 
						globalItemSet.get(i - 1).value + memoryFunction( i - 1, j - nextWeight));
			}
			
			globalTable[i][j] = result;
		}
		
		return result;
	}
	
	/* 
	 * getValue
	 * 
	 * OUTPUT:	this.value		current recorded value of the items in this knapsack
	 * 
	 * 
	 */
	public int getValue() {
		
		return value;
		
	}
	
	/* 
	 * reset
	 * 
	 * resets the knapsack to empty, must be called between successive
	 * calls to knapsack solution algorithms
	 *  
	 */
	public void reset() {
		
		items.clear();
		value = 0;
		
		globalItemSet = null;
		globalTable = null;
		
		currentCapacity = actualCapacity;
		
	}
	
	/* 
	 * @Overloaded toString
	 * 
	 * returns a string representation of the items currently in this
	 * knapsack
	 * 
	 */
	public String toString() {
		
		return items.toString(); 
		
	}
	
	/*
	 * 
	 * inner class KnapsackItem
	 * - represents an item within a knapsack
	 * 
	 * holds index, weight, and value of items for knapsack
	 * solving algorithms' uses
	 *  
	 */
	public static class KnapsackItem {
		
		public int index;	// index within the original set of items
		public int weight;	// item weight
		public int value;	// item value
		
		public KnapsackItem( int index, int weight, int value) {
		
			this.index = index;
			this.weight = weight;
			this.value = value;
			
		}
		
		public String toString() {
			return "KnapsackItem [index=" + index + ", weight=" + weight + ", value=" + value + "]";
		}
	}
}