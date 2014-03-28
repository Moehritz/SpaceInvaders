package de.mm.spaceinvaders.logic;

import lombok.Getter;
import de.mm.spaceinvaders.SpaceInvaders;

public class ScoreManager
{

	private int ticks;
	@Getter
	private int currentScore = 0;
	private int scorePerSecond = 1;
	private Level level;

	public ScoreManager(Level level)
	{
		this.level = level;
	}

	public void addScore(int score)
	{
		currentScore += score;
		SpaceInvaders.getInstance().getIngameMenu().updateScore(currentScore);
		level.onScoreChange();
	}

	public void tick()
	{
		ticks++;
		if (ticks == Ticker.tps)
		{
			ticks = 0;
			addScore(scorePerSecond);
		}
	}

}
