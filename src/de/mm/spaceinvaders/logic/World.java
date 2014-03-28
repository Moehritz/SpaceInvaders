package de.mm.spaceinvaders.logic;

import lombok.Getter;
import lombok.Setter;
import de.mm.spaceinvaders.gfx.StarBackground;

@Getter
@Setter
public class World
{

	private float speed = 0.0f;
	private StarBackground background = new StarBackground(this);
	
}
