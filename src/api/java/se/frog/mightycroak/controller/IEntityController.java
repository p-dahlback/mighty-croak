package se.frog.mightycroak.controller;

import se.frog.mightycroak.entity.IEntity;
import se.frog.mightycroak.entity.IUpdateable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;

public interface IEntityController extends IUpdateable, InputProcessor {
	public IEntity getEntity();
	public Game getGame();
	public boolean isMoving();
	public void setAcceptInput(boolean acceptInput);
	public boolean isAcceptingInput();
}
