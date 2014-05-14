package de.mm.spaceinvaders.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gamestate.Ingame;
import de.mm.spaceinvaders.gui.model.Menu;
import de.mm.spaceinvaders.gui.model.MenuStatusBar;
import de.mm.spaceinvaders.gui.model.MenuText;
import de.mm.spaceinvaders.gui.model.MenuText.TextAlignment;
import de.mm.spaceinvaders.protocol.packets.ResetGame;

public class IngameGui extends Menu
{
	private MenuText score;
	private MenuStatusBar statusBar;

	private Ingame ingame;

	public IngameGui(Ingame ingame)
	{
		this.ingame = ingame;
	}

	@Override
	public void init()
	{
		super.init();

		score = new MenuText(new Rectangle(7, 13, 200, 10), "", 25.0f, Color.white);
		updateScore(0);
		score.setAlignment(TextAlignment.LEFT);
		addObject(score);

		statusBar = new MenuStatusBar(
				new Rectangle(Display.getWidth() - 150, 10, 143, 10));
		statusBar.setPercent(100);
		statusBar.setRight(true);
		addObject(statusBar);
	}

	@Override
	public void draw()
	{
		super.draw();

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			SpaceInvaders.getInstance().switchToMainMenu();
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_R))
		{
			ingame.getConnection().sendPackets(
					new ResetGame(ingame.getThePlayer().getUuid()));
		}
	}

	public void updateScore(int newScore)
	{
		score.setText("Punkte: " + newScore);
	}

	public void updateAmmo(int curr, int max)
	{
		statusBar.setPercent(((float) curr / max) * 100);
	}

}
