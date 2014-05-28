package de.mm.spaceinvaders.gui.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.newdawn.slick.Color;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Text
{

	@NonNull
	private String content;
	private double textSize = 20;
	private Color color = Color.white;
	private TextAlignment align = TextAlignment.LEFT;

	public void writeToScreen(double x, double y, double width, double height)
	{
		// TODO
		// Text malen zwischen y und (y+height/2)
		// und je nach TextAlignment in die Mitte, Links oder Rechts im übergebenem Viereck
	}

	public enum TextAlignment
	{
		LEFT, CENTER, RIGHT;
	}
}
