package de.mm.spaceinvaders.gui.model;

import java.awt.geom.Rectangle2D;

import org.lwjgl.input.Keyboard;

public class MenuTextField extends MenuObject
{

	private StringBuilder text = new StringBuilder();

	private ObjectBorder border;
	private MenuText textContainer;

	public MenuTextField(Rectangle2D.Double rect)
	{
		super(rect);
		border = new ObjectBorder(rect);

		textContainer = new MenuText(rect, new Text(text.toString()));
	}

	public void setText(String text)
	{
		this.text = new StringBuilder(text);
		textContainer.getText().setContent(text);
	}

	public String getText()
	{
		return text.toString();
	}

	@Override
	public void update()
	{
		super.update();

		while (Keyboard.next())
		{
			if (Keyboard.getEventKeyState())
			{
				switch (Keyboard.getEventKey())
				{
				case Keyboard.KEY_BACK:
					try
					{
						text = new StringBuilder(text.substring(0, text.length() - 1));
					}
					catch (StringIndexOutOfBoundsException e)
					{
					}
					break;
				default:
					char c = Keyboard.getEventCharacter();
					// TODO Nicht immer URL-Limits nehmen (Nicknames etc)
					if (Character.isAlphabetic(c) || Character.isDigit(c) || '.' == c
							|| ':' == c)
					{
						text.append(Keyboard.getEventCharacter());
					}
				}
			}
		}
		textContainer.getText().setContent(text.toString());
	}

	@Override
	public void draw()
	{
		border.draw();

		textContainer.draw();
	}
}
