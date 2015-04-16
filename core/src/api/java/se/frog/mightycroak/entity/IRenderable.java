package se.frog.mightycroak.entity;

import se.frog.mightycroak.ICroakGame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface IRenderable {
	public void render(ICroakGame game, OrthographicCamera camera, SpriteBatch batch, ShapeRenderer shapes);
}
