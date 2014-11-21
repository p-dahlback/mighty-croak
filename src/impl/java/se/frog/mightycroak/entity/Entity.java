package se.frog.mightycroak.entity;

import se.frog.mightycroak.ICroakGame;
import se.frog.mightycroak.controller.IEntityController;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity implements IEntity {

	protected final Vector2 pos;
	protected final Rectangle rect;
	protected float rotation;
	private EntityState mState;

	IEntityController mController;

	public Entity() {
		this.pos = new Vector2();
		this.rect = new Rectangle(0, 0, 0, 0);
	}

	@Override
	public void setPosition(float x, float y) {
		this.pos.x = x;
		this.pos.y = y;
	}

	@Override
	public void changePosition(float x, float y) {
		this.pos.x += x;
		this.pos.y += y;
	}

	@Override
	public void setBounds(float width, float height) {
		this.rect.width = width;
		this.rect.height = height;
	}

	@Override
	public void setBounds(float x, float y, float width, float height) {
		setPosition(x + width / 2, y);
		setBounds(width, height);
	}

	@Override
	public boolean overlaps(Rectangle rect) {
		return this.rect.overlaps(rect);
	}

	@Override
	public Vector2 getPosition() {
		return this.pos;
	}

	@Override
	public Rectangle getRect() {
		return this.rect;
	}

	@Override
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	@Override
	public float getRotation() {
		return this.rotation;
	}

	@Override
	public void setState(EntityState state) {
		this.mState = state;
	}

	@Override
	public EntityState getState() {
		return this.mState;
	}

	@Override
	public void update(float delta) {
		if(this.mState != EntityState.DISABLED && this.mController != null) {
			this.mController.update(delta);
		}
	}

	@Override
	public void render(ICroakGame game, OrthographicCamera camera, SpriteBatch batch, ShapeRenderer shapes) {

	}

	@Override
	public void setEntityController(IEntityController controller) {
		this.mController = controller;
	}

	@Override
	public IEntityController getController() {
		return this.mController;
	}

	@Override
	public void loadAssets(AssetManager assets) {

	}

	@Override
	public void dispose(AssetManager assets) {

	}

}
