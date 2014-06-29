package de.mm.spaceinvaders.lustigerText;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Text 
{
	private float x,y;
	private String text;
	private Color color;
	private TrueTypeFont font;
	
	public Text(String text, float x, float y, float size, Color color) 
	{
		this.text = text;
		this.x = x;
		this.y = y;
		this.color = color;
				
		// load font from file
		try 
		{
			InputStream inputStream	= ResourceLoader.getResourceAsStream("de/mm/spaceinvaders/lustigerText/font.ttf");
			
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(size);
			font = new TrueTypeFont(awtFont2, true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void draw()
	{
		Color.white.bind();
		
		font.drawString(x, y, text, color);
	}
}
