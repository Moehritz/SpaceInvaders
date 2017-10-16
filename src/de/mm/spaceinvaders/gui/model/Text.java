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

    public enum TextAlignment
	{
		LEFT, CENTER, RIGHT;
	}

}
