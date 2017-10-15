package de.mm.spaceinvaders.gui.model;

import lombok.*;
import org.newdawn.slick.Color;

import static org.lwjgl.opengl.GL11.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Text
{

	@NonNull
	private String content;
	private double textSize = 0.01;
	private Color color = Color.white;
	private TextAlignment align = TextAlignment.LEFT;

	public void writeToScreen(double x, double y, double width, double height) {

        glPushMatrix();
        glScaled(textSize, textSize, 1);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        MenuObject.fontx.drawString((float) (x * (1 / textSize)), (float) (y * (1 / textSize)), content);
        glPopMatrix();
    }

    public double drawZeichen(char s, double x, double y, double width, double height)
	{

		glBindTexture(GL_TEXTURE_2D, getTexture(s));

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
		return width;
	}

	public int getTexture(char s)
	{

		for(Zeichen z : Zeichen.values())
		{
			if(Character.toLowerCase(z.zeichen) == Character.toLowerCase(s))
				return z.texture;
		}
		return 0;
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
		ANFUHRUNGSZEICHEN('\''),
		AUSRUFEZEICHEN('!'),
		DOPPELPUNKT(':'),
		FRAGEZEICHEN('?'),
		KOMMA(','),
		PUNKT('.'),
		LEER(' ');

		private int texture;
		private char zeichen;

		private Zeichen()
		{
			zeichen = name().charAt(0);
		}

		Zeichen(char zeichen)
		{
			this.zeichen = zeichen;
		}

    }
}
