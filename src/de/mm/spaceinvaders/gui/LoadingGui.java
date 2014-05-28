package de.mm.spaceinvaders.gui;

import java.awt.geom.Rectangle2D;

import de.mm.spaceinvaders.gui.model.Menu;
import de.mm.spaceinvaders.gui.model.MenuText;
import de.mm.spaceinvaders.gui.model.Text;
import de.mm.spaceinvaders.gui.model.Text.TextAlignment;

public class LoadingGui extends Menu
{
	private MenuText textfield;
	private String text;

	@Override
	public void init()
	{
		super.init();

		textfield = new MenuText(new Rectangle2D.Double(0, 0, 1, 1), new Text(text));
		textfield.getText().setAlign(TextAlignment.CENTER);
		addObject(textfield);
	}

	public void setText(String text)
	{
		this.text = text;
		if (textfield != null) textfield.getText().setContent(text);
	}

	public String getText()
	{
		return textfield.getText().getContent();
	}
}
