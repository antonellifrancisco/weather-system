package com.mercadolibre.weather.infrastructure.utils;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javafx.util.Pair;

public class Combinator {

	public static <T> List<Pair<T, T>> execute(List<T> items) {
		List<Pair<T, T>> combinations = items.stream()
				.flatMap(item1 -> items.stream().map(item2 -> new Pair<>(item1, item2)))
				.collect(toList());
		return combinations;
	}
}
