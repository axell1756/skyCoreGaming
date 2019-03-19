package com.cg.Constructors;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

/**
 * Constructor for randomly weighted probability rolls.
 * 
 * @author Peter
 *         Lawrey @https://stackoverflow.com/questions/6409652/random-weighted-selection-in-java/30362366
 *         
 */
public class RandomValues<Integer> {

	// TreeMap is used for its storing algorithm which sorts values depending on
	// their value in relation to other elements
	private final NavigableMap<Double, Integer> map = new TreeMap<Double, Integer>();
	private final Random random;
	private double total = 0;
	
	public RandomValues() {
		this(new Random());
	}

	public RandomValues(Random random) {
		this.random = random;
	}

	/**
	 * Adds elements coupled with their probability for successful roll
	 * @param weight probability of a roll
	 * @param result element to be added
	 * @return instance of itself
	 */
	public RandomValues<Integer> add(double weight, Integer result) {
		if (weight <= 0)
			return this;
		total += weight;
		map.put(total, result);
		return this;
	}

	/**
	 * Returns value, depending on it's chance to be rolled
	 */
	public Integer next() {
		double value = random.nextDouble() * total;
		return map.higherEntry(value).getValue();
	}
}