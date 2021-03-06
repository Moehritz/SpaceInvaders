package de.mm.spaceinvaders.gui;

import java.awt.geom.Rectangle2D;

import org.lwjgl.input.Keyboard;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gamestate.Ingame;
import de.mm.spaceinvaders.gui.model.Menu;
import de.mm.spaceinvaders.gui.model.MenuStatusBar;
import de.mm.spaceinvaders.gui.model.MenuText;
import de.mm.spaceinvaders.gui.model.Text;
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

		score = new MenuText(new Rectangle2D.Double(0.001, 0.001, 0.3, 0.05),
				new Text(""));
		updateScore(0);
		addObject(score);

		statusBar = new MenuStatusBar(new Rectangle2D.Double(0.7, 0.001, 0.299, 0.05));
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
		score.getText().setContent("Punkte: " + newScore);
	}

	public void updateAmmo(int curr, int max)
	{
		statusBar.setPercent(((float) curr / max) * 100);
	}

}
