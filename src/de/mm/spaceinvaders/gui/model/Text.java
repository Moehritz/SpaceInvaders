package de.mm.spaceinvaders.gui.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.awt.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import static org.lwjgl.opengl.GL11.*;

import de.mm.spaceinvaders.gfx.Textures;

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
		
//		Rectangle r;
//		r = drawZeichen('w', 50, 50);
//		r = drawZeichen('a', r.x+r.width, 50);
//		r = drawZeichen('s', r.x+r.width, 50);
//		r = drawZeichen('?', r.x+r.width, 50);
	}
	
	public Rectangle drawZeichen(String s, double x, double y)
	{
		Texture texture = getTexture(s);
		double width = texture.getImageWidth()*(textSize/texture.getImageWidth());
		double height = textSize;
		
		texture.bind();
		
		glBegin(GL_QUADS);
		{
			glTexCoord2d(0.0f, 0.0f);
			glVertex2d(x, y);
			glTexCoord2d(1.0f, 0.0f);
			glVertex2d(x+width, y);
			glTexCoord2d(1.0f, 1.0f);
			glVertex2d(x+width, y+height);
			glTexCoord2d(0.0f, 1.0f);
			glVertex2d(x, y+height);
		}
		
		glEnd();
		Rectangle bounds = new Rectangle();
		bounds.setLocation((int)x, (int)y);
		bounds.setSize((int)width, (int)height);
		return bounds;
	}
	
	public Texture getTexture(String s)
	{
		s.toLowerCase();
		
		for(Zeichen z : Zeichen.values())
		{
			if(z.zeichen == s.charAt(0))
				return z.texture;
		}
		return null;
	}

	public enum TextAlignment
	{
		LEFT, CENTER, RIGHT;
	}
	
	@Getter
	public enum Zeichen
	{
		A(), B(), C(), D(), E(), F(), G(), 
		H(), I(), J(), K(), L(), M(), N(), 
		O(), P(), Q(), R(), S(), T(), U(), 
		V(), W(), X(), Y(), Z(), 
		ANFÜHRUNGSZEICHEN('\''),
		AUSRUFEZEICHEN('!'),
		DOPPELPUNKT(':'),
		FRAGEZEICHEN('?'),
		KOMME(','),
		PUNKT('.'),
		LEER(' ');
		
		private Texture texture;
		private char zeichen;
		
		private Zeichen()
		{
			zeichen = name().toLowerCase().charAt(0);
			texture = Textures.load("buchstaben/"+zeichen);
		}
		
		private Zeichen(char zeichen)
		{
			this.zeichen = zeichen;
			texture = Textures.load("buchstaben/"+name());
		}
	}
}
