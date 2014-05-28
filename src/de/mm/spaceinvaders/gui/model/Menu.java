package de.mm.spaceinvaders.gui.model;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import de.mm.spaceinvaders.gfx.Drawable;
import lombok.Getter;

@Getter
public class Menu implements Drawable
{

	private boolean initialized;
	private List<MenuObject> objects = new ArrayList<>();

	@Override
	public void draw()
	{
		if (!initialized)
		{
			init();
		}
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).update();
			objects.get(i).draw();
		}
		while (Keyboard.next());
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
