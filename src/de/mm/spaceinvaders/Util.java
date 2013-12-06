package de.mm.spaceinvaders;

public class Util
{
	public static double calcRotationAngleInDegrees(long x1, long y1, long x2, long y2)
	{
		return Math.toDegrees((Math.atan2(y2 - y1, x2 - x1) + (Math.PI * 2)));
	}
}
