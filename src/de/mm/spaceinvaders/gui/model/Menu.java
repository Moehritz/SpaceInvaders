package de.mm.spaceinvaders.gui.model;

import java.util.ArrayList;
import java.util.List;

import de.mm.spaceinvaders.gfx.Drawable;
import de.mm.spaceinvaders.gfx.TextDrawable;
import lombok.Getter;

@Getter
public class Menu implements Drawable
{

	private boolean initialized;
	private List<MenuObject> objects = new ArrayList<>();

	@Override
	public void draw()
	{
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).update();
			objects.get(i).draw();
		}
		for (MenuObject object : objects)
		{
			if (object instanceof TextDrawable)
			{
				((TextDrawable) object).drawText();
			}
		}
	}

	protected void addObject(MenuObject object)
	{
		objects.add(object);
	}

	public void init()
	{
		this.initialized = true;
	}

	protected void removeObject(MenuObject object)
	{
		objects.remove(object);
	}

}
