package de.mm.spaceinvaders.logic;

import org.newdawn.slick.opengl.Texture;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gamestate.Ingame;
import de.mm.spaceinvaders.util.Util;
import de.mm.spaceinvaders.util.Vector;

public class Enemy extends Entity
{

	private static double defaultSpeed = 1.5d;

	public Enemy(Texture texture)
	{
		super(texture);
		updateDirection();
	}

	@Override
	public boolean updatePosition(long delta)
	{
		updateDirection();
		return super.updatePosition(delta);
	}

	private void updateDirection()
	{
		Entity p = ((Ingame) SpaceInvaders.getInstance().getGameState()).getThePlayer();
		Vector v = new Vector(p.getX() - getX(), p.getY() - getY()).normalize();
		double speed = getSpeed().length();
		if (speed != Double.NaN)
		{
			speed = defaultSpeed;
		}
		setRotation(Util.calcRotationAngleInDegrees(v) + 180);
		setSpeed(v.multiply(speed));
	}

}
