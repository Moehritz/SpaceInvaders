package de.mm.spaceinvaders.menu;

import java.util.ArrayList;
import java.util.List;

import de.mm.spaceinvaders.gfx.Drawable;
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
	}

	protected void addObject(MenuObject object)
	{
		objects.add(object);
	}

	public void init()
	{
	}

}
