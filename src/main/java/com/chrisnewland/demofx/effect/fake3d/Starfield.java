/*
 * Copyright (c) 2015-2016 Chris Newland.
 * Licensed under https://github.com/chriswhocodes/demofx/blob/master/LICENSE-BSD
 */
package com.chrisnewland.demofx.effect.fake3d;

import com.chrisnewland.demofx.DemoConfig;
import com.chrisnewland.demofx.effect.AbstractEffect;

import javafx.scene.paint.Color;

public class Starfield extends AbstractEffect
{
	private double[] starX;
	private double[] starY;
	private double[] starZ;

	private double speed = 0.05;
	private static final double MAX_DEPTH = 5;

	private boolean spin = true;

	private Color starColour;

	public Starfield(DemoConfig config)
	{
		this(config, 5000, 0.05, Color.WHITE);
	}

	public Starfield(DemoConfig config, int itemCount, double speed, Color starColour)
	{
		super(config);

		this.itemCount = itemCount;

		this.speed = speed;

		this.starColour = starColour;

		init();
	}

	public Starfield(DemoConfig config, int starCount, long startMillis, long stopMillis)
	{
		super(config);

		this.itemCount = starCount;
		this.effectStartMillis = startMillis;
		this.effectStopMillis = stopMillis;

		init();
	}

	private void init()
	{
		buildStars();
	}

	private void buildStars()
	{
		starX = new double[itemCount];
		starY = new double[itemCount];
		starZ = new double[itemCount];

		for (int i = 0; i < itemCount; i++)
		{
			starX[i] = precalc.getSignedRandom() * halfWidth;
			starY[i] = precalc.getSignedRandom() * halfHeight;
			respawn(i);
		}
	}

	@Override public void renderForeground()
	{
		gc.setStroke(starColour);

		if (spin)
		{
			rotateCanvasAroundCentre(0.5);
		}

		for (int i = 0; i < itemCount; i++)
		{
			moveStar(i);

			plotStar(i);
		}
	}

	private final void moveStar(int i)
	{
		starZ[i] -= speed;
	}

	private final void respawn(int i)
	{
		starZ[i] = precalc.getUnsignedRandom() * MAX_DEPTH / 2;
	}

	private double translateX(int i)
	{
		return starX[i] / starZ[i];
	}

	private double translateY(int i)
	{
		return starY[i] / starZ[i];
	}

	private final void plotStar(int i)
	{
		double x = halfWidth + translateX(i);
		double y = halfHeight + translateY(i);

		if (isOnScreen(x, y))
		{
			gc.strokeLine(x, y, x + 1, y + 1);
		}
		else
		{
			respawn(i);
		}
	}

	private final boolean isOnScreen(double x, double y)
	{
		if (spin)
		{
			double max = 1.4 * Math.max(width, height);

			return x > -max && y > -max && x < max && y < max;
		}
		else
		{
			return x > 0 && y > 0 && x < width && y < height;
		}
	}
}