package com.mercadolibre.weather.domain;

import org.junit.Assert;
import org.junit.Test;

import com.mercadolibre.weather.domain.Days;
import com.mercadolibre.weather.domain.Displacement;
import com.mercadolibre.weather.domain.Speed;

public class SpeedTest {

	@Test
	public void DisplacementAtSpeedAndDay() {
		Speed speed = GivenSpeed(3);
		Days daysElapsed = GivenDaysElapsed(5);

		Displacement displacement = WhenAskForDisplacement(speed, daysElapsed);

		ThenDisplacementIs(new Displacement(15), displacement);
	}

	private Speed GivenSpeed(int displacementPerDay) {
		Displacement displacement = new Displacement(displacementPerDay);
		return new Speed(displacement);
	}

	private Days GivenDaysElapsed(int days) {
		return new Days(days);
	}

	private Displacement WhenAskForDisplacement(Speed speed, Days days) {
		return speed.getDisplacement(days);
	}

	private void ThenDisplacementIs(Displacement expected, Displacement actual) {
		Assert.assertEquals(expected, actual);
	}

}
