package se.frog.mightycroak.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class TileSetLoader extends AsynchronousAssetLoader<TileSet, TileSetLoader.TileSetParameter> {
	public TileSetLoader(FileHandleResolver resolver) {
		super(resolver);
	}

	@Override
	public void loadAsync(AssetManager manager, String fileName,
			FileHandle file, TileSetParameter parameter) {
	}

	@Override
	public TileSet loadSync(AssetManager manager, String fileName,
			FileHandle file, TileSetParameter parameter) {
		Texture texture = new Texture(file);
		TextureRegion[] tiles = new TextureRegion[parameter.tilesHorizontal * parameter.tilesVertical];
		for(int tileY = 0; tileY < parameter.tilesVertical; tileY++) {
			for(int tileX = 0; tileX < parameter.tilesHorizontal; tileX++) {
				tiles[tileX + tileY * parameter.tilesHorizontal] = new TextureRegion(texture,
						tileX * (parameter.tileSize + parameter.dividerSize),
						tileY * (parameter.tileSize + parameter.dividerSize),
						parameter.tileSize, parameter.tileSize);
			}
		}
		return new TileSet(tiles, parameter.tileSize);
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName,
			FileHandle file, TileSetParameter parameter) {
		return null;
	}

	public static class TileSetParameter extends AssetLoaderParameters<TileSet> {
		public int tilesHorizontal;
		public int tilesVertical;
		public int tileSize;
		public int dividerSize;

		public TileSetParameter(int tilesHorizontal, int tilesVertical, int tileSize, int dividerSize) {
			this.tilesHorizontal = tilesHorizontal;
			this.tilesVertical = tilesVertical;
			this.tileSize = tileSize;
			this.dividerSize = dividerSize;
		}
	}
}
