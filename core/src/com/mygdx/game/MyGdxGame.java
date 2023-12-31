package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends Game {

	private AssetManager manager;

	public AssetManager getManager() {
		return manager;
	}

	public void setManager(AssetManager manager) {
		this.manager = manager;
	}

	@Override
	public void create() {
		manager =new AssetManager();
		manager.load("dino.png",Texture.class);
		manager.load("roca.png",Texture.class);
		manager.load("suelohierba.png",Texture.class);

		manager.finishLoading();

		setScreen(new GameScreen(this));

		//setScreen(new Box2DScreen(this));
	}
}
