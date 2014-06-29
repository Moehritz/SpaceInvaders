package de.mm.spaceinvaders.lustigerText;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;

import static org.lwjgl.opengl.GL11.*;
 
public class Fontinator 
{
	private Text text;
	
	public Fontinator() 
	{
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
 
		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_LIGHTING);                    
 
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
        glClearDepth(1);                                       
 
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
 
        glViewport(0,0,Display.getWidth(),Display.getHeight());
		glMatrixMode(GL_MODELVIEW);
 
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(),Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		text = new Text("Spaciger Text", 250f, 20f, 24f, Color.blue);
		
		while (!Display.isCloseRequested()) 
		{
			glClear(GL_COLOR_BUFFER_BIT);
			
			text.draw();
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		System.exit(0);
	}
	
	public static void main(String[] argv)
	{
		new Fontinator();
	}
}