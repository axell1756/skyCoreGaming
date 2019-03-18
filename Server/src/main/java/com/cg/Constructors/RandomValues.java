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
 * @param <E>
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

	public RandomValues<Integer> add(double weight, Integer result) {
		if (weight <= 0)
			return this;
		total += weight;
		map.put(total, result);
		return this;
	}

	public Integer next() {
		double value = random.nextDouble() * total;
		return map.higherEntry(value).getValue();
	}
}