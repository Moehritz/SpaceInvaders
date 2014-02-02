package de.mm.spaceinvaders.menu;

import java.util.ArrayList;
import java.util.List;

import de.mm.spaceinvaders.gfx.Drawable;
import de.mm.spaceinvaders.gfx.TextDrawable;
import lombok.Getter;

public class Menu implements Drawable
{

	@Getter
	private List<MenuObject> objects = new ArrayList<>();

	@Override
	public void draw()
	{
		for (MenuObject object : objects)
		{
			object.update();
			object.draw();
		}
		for (MenuObject object : objects)
		{
			if (object instanceof TextDrawable) ((TextDrawable) object).drawText();
		}
	}

	protected void addObject(MenuObject object)
	{
		objects.add(object);
	}

	public void init()
	{
	}

}
