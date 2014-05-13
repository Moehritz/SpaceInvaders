package de.mm.spaceinvaders.gui;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import de.mm.spaceinvaders.gui.model.Menu;
import de.mm.spaceinvaders.gui.model.MenuText;
import de.mm.spaceinvaders.gui.model.MenuText.TextAlignment;

public class LoadingGui extends Menu
{
	private MenuText textfield;

	@Override
	public void init()
	{
		super.init();

		textfield = new MenuText(new Rectangle(0, 0, Display.getWidth(),
				Display.getHeight()), "", 25.0f, Color.white);
		textfield.setAlignment(TextAlignment.CENTER);
		addObject(textfield);
	}

	public void setText(String text)
	{
		textfield.setText(text);
	}

	public String getText()
	{
		return textfield.getText();
	}
}
