package de.mm.spaceinvaders.gui.model;

import java.awt.geom.Rectangle2D;

import lombok.Getter;
import lombok.Setter;

public class MenuText extends MenuObject
{
	@Setter
	@Getter
	private Text text;

	public MenuText(Rectangle2D.Double rect, Text text)
	{
		super(rect);
		this.setText(text);
	}

	@Override
	public void draw()
	{
		text.writeToScreen(getRect().x, getRect().y, getRect().width, getRect().height);
	}

}