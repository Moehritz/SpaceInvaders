package de.mm.spaceinvaders;

public class Util
{
	public static double calcRotationAngleInDegrees(Vector v1, Vector v2)
	{
		return Math.toDegrees(Math.atan2(v2.getY() - v1.getY(), v2.getX() - v1.getX())/* + (Math.PI * 2)*/);
	}

	public static double calcRotationAngleInDegrees(Vector v)
	{
		return calcRotationAngleInDegrees(v, new Vector(0, 0));
	}

	public static Vector calcVectorFromDegrees(double alpha)
	{
		double a = Math.toRadians(alpha);
		Vector v = new Vector(Math.cos(a), Math.sin(a));
		return v.normalize();
	}
}
