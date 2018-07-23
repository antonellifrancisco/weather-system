package com.mercadolibre.weather.infraestructure.utils;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import javafx.util.Pair;

public class CombinatorTest {

	@Test
	public void combineIntegers() {
		List<Integer> objects = GivenThreeObjects();
		List<Pair<Integer, Integer>> combinations = WhenAskForCombinations(objects);
		ThenCombinationsAreAsExpected(combinations);

	}

	private List<Integer> GivenThreeObjects() {
		return List.of(1, 2, 3);
	}

	private List<Pair<Integer, Integer>> WhenAskForCombinations(List<Integer> objects) {
		return Combinator.execute(objects);
	}

	private void ThenCombinationsAreAsExpected(List<Pair<Integer, Integer>> combinations) {
		Assert.assertEquals(9, combinations.size());
		Assert.assertEquals(new Pair<>(1, 1), combinations.get(0));
		Assert.assertEquals(new Pair<>(1, 2), combinations.get(1));
		Assert.assertEquals(new Pair<>(1, 3), combinations.get(2));
		Assert.assertEquals(new Pair<>(2, 1), combinations.get(3));
		Assert.assertEquals(new Pair<>(2, 2), combinations.get(4));
		Assert.assertEquals(new Pair<>(2, 3), combinations.get(5));
		Assert.assertEquals(new Pair<>(3, 1), combinations.get(6));
		Assert.assertEquals(new Pair<>(3, 2), combinations.get(7));
		Assert.assertEquals(new Pair<>(3, 3), combinations.get(8));
	}
}
