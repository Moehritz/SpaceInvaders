package de.mm.spaceinvaders.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.lwjgl.util.Rectangle;

import de.mm.spaceinvaders.gfx.Drawable;

@Getter
@Setter
@AllArgsConstructor
public abstract class MenuObject implements Drawable
{
	private Rectangle rect;
	
	public void update() {
		
	}
}
