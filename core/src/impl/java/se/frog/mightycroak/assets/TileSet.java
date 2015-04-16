package se.frog.mightycroak.assets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileSet {
	TextureRegion[] mTiles;
	int mTileSize;

	public TileSet(TextureRegion[] tiles, int tileSize) {
		this.mTiles = tiles;
		this.mTileSize = tileSize;
	}

	public TextureRegion getTileTexture(int tile) {
		return this.mTiles[tile];
	}

	public int getTileSize() {
		return this.mTileSize;
	}

	public int getNumberTiles() {
		return this.mTiles.length;
	}
}
