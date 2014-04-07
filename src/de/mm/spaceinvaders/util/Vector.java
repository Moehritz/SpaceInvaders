package de.mm.spaceinvaders.util;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a mutable vector. Because the components of Vectors are mutable, storing Vectors long
 * term may be dangerous if passing code modifies the Vector later. If you want to keep around a
 * Vector, it may be wise to call <code>clone()</code> in order to get a copy.
 */
@Getter
@Setter
public class Vector implements Cloneable
{

	/**
	 * Threshold for fuzzy equals().
	 */
	private static final double epsilon = 0.000001;

	private double x;
	private double y;

	/**
	 * Construct the vector with all components as 0.
	 */
	public Vector()
	{
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Construct the vector with provided integer components.
	 * 
	 * @param x
	 *            X component
	 * @param y
	 *            Y component
	 */
	public Vector(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Construct the vector with provided double components.
	 * 
	 * @param x
	 *            X component
	 * @param y
	 *            Y component
	 */
	public Vector(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Construct the vector with provided float components.
	 * 
	 * @param x
	 *            X component
	 * @param y
	 *            Y component
	 */
	public Vector(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Adds a vector to this one
	 * 
	 * @param vec
	 *            The other vector
	 * @return the same vector
	 */
	public Vector add(Vector vec)
	{
		x += vec.x;
		y += vec.y;
		return this;
	}

	/**
	 * Subtracts a vector from this one.
	 * 
	 * @param vec
	 *            The other vector
	 * @return the same vector
	 */
	public Vector subtract(Vector vec)
	{
		x -= vec.x;
		y -= vec.y;
		return this;
	}

	/**
	 * Multiplies the vector by another.
	 * 
	 * @param vec
	 *            The other vector
	 * @return the same vector
	 */
	public Vector multiply(Vector vec)
	{
		x *= vec.x;
		y *= vec.y;
		return this;
	}

	/**
	 * Divides the vector by another.
	 * 
	 * @param vec
	 *            The other vector
	 * @return the same vector
	 */
	public Vector divide(Vector vec)
	{
		x /= vec.x;
		y /= vec.y;
		return this;
	}

	/**
	 * Copies another vector
	 * 
	 * @param vec
	 *            The other vector
	 * @return the same vector
	 */
	public Vector copy(Vector vec)
	{
		x = vec.x;
		y = vec.y;
		return this;
	}

	/**
	 * Gets the magnitude of the vector, defined as sqrt(x^2+y^2). The value of this method is not
	 * cached and uses a costly square-root function, so do not repeatedly call this method to get
	 * the vector's magnitude. NaN will be returned if the inner result of the sqrt() function
	 * overflows, which will be caused if the length is too long.
	 * 
	 * @return the magnitude
	 */
	public double length()
	{
		return Math.sqrt(NumberConversions.square(x) + NumberConversions.square(y));
	}

	/**
	 * Gets the magnitude of the vector squared.
	 * 
	 * @return the magnitude
	 */
	public double lengthSquared()
	{
		return NumberConversions.square(x) + NumberConversions.square(y);
	}

	/**
	 * Get the distance between this vector and another. The value of this method is not cached and
	 * uses a costly square-root function, so do not repeatedly call this method to get the vector's
	 * magnitude. NaN will be returned if the inner result of the sqrt() function overflows, which
	 * will be caused if the distance is too long.
	 * 
	 * @param o
	 *            The other vector
	 * @return the distance
	 */
	public double distance(Vector o)
	{
		return Math.sqrt(NumberConversions.square(x - o.x)
				+ NumberConversions.square(y - o.y));
	}

	/**
	 * Get the squared distance between this vector and another.
	 * 
	 * @param o
	 *            The other vector
	 * @return the distance
	 */
	public double distanceSquared(Vector o)
	{
		return NumberConversions.square(x - o.x) + NumberConversions.square(y - o.y);
	}

	/**
	 * Gets the angle between this vector and another in radians.
	 * 
	 * @param other
	 *            The other vector
	 * @return angle in radians
	 */
	public float angle(Vector other)
	{
		double dot = dot(other) / (length() * other.length());

		return (float) Math.acos(dot);
	}

	/**
	 * Sets this vector to the midpoint between this vector and another.
	 * 
	 * @param other
	 *            The other vector
	 * @return this same vector (now a midpoint)
	 */
	public Vector midpoint(Vector other)
	{
		x = (x + other.x) / 2;
		y = (y + other.y) / 2;
		return this;
	}

	/**
	 * Gets a new midpoint vector between this vector and another.
	 * 
	 * @param other
	 *            The other vector
	 * @return a new midpoint vector
	 */
	public Vector getMidpoint(Vector other)
	{
		double x = (this.x + other.x) / 2;
		double y = (this.y + other.y) / 2;
		return new Vector(x, y);
	}

	/**
	 * Performs scalar multiplication, multiplying all components with a scalar.
	 * 
	 * @param m
	 *            The factor
	 * @return the same vector
	 */
	public Vector multiply(int m)
	{
		x *= m;
		y *= m;
		return this;
	}

	/**
	 * Performs scalar multiplication, multiplying all components with a scalar.
	 * 
	 * @param m
	 *            The factor
	 * @return the same vector
	 */
	public Vector multiply(double m)
	{
		x *= m;
		y *= m;
		return this;
	}

	/**
	 * Performs scalar multiplication, multiplying all components with a scalar.
	 * 
	 * @param m
	 *            The factor
	 * @return the same vector
	 */
	public Vector multiply(float m)
	{
		x *= m;
		y *= m;
		return this;
	}

	/**
	 * Calculates the dot product of this vector with another. The dot product is defined as
	 * x1*x2+y1*y2. The returned value is a scalar.
	 * 
	 * @param other
	 *            The other vector
	 * @return dot product
	 */
	public double dot(Vector other)
	{
		return x * other.x + y * other.y;
	}

	/**
	 * Converts this vector to a unit vector (a vector with length of 1).
	 * 
	 * @return the same vector
	 */
	public Vector normalize()
	{
		double length = length();

		x /= length;
		y /= length;

		return this;
	}

	/**
	 * Zero this vector's components.
	 * 
	 * @return the same vector
	 */
	public Vector zero()
	{
		x = 0;
		y = 0;
		return this;
	}

	/**
	 * Returns whether this vector is in an axis-aligned bounding box. The minimum and maximum
	 * vectors given must be truly the minimum and maximum X and Y components.
	 * 
	 * @param min
	 *            Minimum vector
	 * @param max
	 *            Maximum vector
	 * @return whether this vector is in the AABB
	 */
	public boolean isInAABB(Vector min, Vector max)
	{
		return x >= min.x && x <= max.x && y >= min.y && y <= max.y;
	}

	/**
	 * Returns whether this vector is within a sphere.
	 * 
	 * @param origin
	 *            Sphere origin.
	 * @param radius
	 *            Sphere radius
	 * @return whether this vector is in the sphere
	 */
	public boolean isInSphere(Vector origin, double radius)
	{
		return (NumberConversions.square(origin.x - x) + NumberConversions
				.square(origin.y - y)) <= NumberConversions.square(radius);
	}

	/**
	 * Checks to see if two objects are equal.
	 * <p>
	 * Only two Vectors can ever return true. This method uses a fuzzy match to account for floating
	 * point errors. The epsilon can be retrieved with epsilon.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Vector))
		{
			return false;
		}

		Vector other = (Vector) obj;

		return Math.abs(x - other.x) < epsilon && Math.abs(y - other.y) < epsilon
				&& (this.getClass().equals(obj.getClass()));
	}

	/**
	 * Get a new vector.
	 * 
	 * @return vector
	 */
	@Override
	public Vector clone()
	{
		try
		{
			return (Vector) super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new Error(e);
		}
	}

	/**
	 * Returns this vector's components as x,y,z.
	 */
	@Override
	public String toString()
	{
		return x + "," + y;
	}

	/**
	 * Get the threshold used for equals().
	 * 
	 * @return The epsilon.
	 */
	public static double getEpsilon()
	{
		return epsilon;
	}

	/**
	 * Gets the minimum components of two vectors.
	 * 
	 * @param v1
	 *            The first vector.
	 * @param v2
	 *            The second vector.
	 * @return minimum
	 */
	public static Vector getMinimum(Vector v1, Vector v2)
	{
		return new Vector(Math.min(v1.x, v2.x), Math.min(v1.y, v2.y));
	}

	/**
	 * Gets the maximum components of two vectors.
	 * 
	 * @param v1
	 *            The first vector.
	 * @param v2
	 *            The second vector.
	 * @return maximum
	 */
	public static Vector getMaximum(Vector v1, Vector v2)
	{
		return new Vector(Math.max(v1.x, v2.x), Math.max(v1.y, v2.y));
	}
}