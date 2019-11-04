package com.gdx.samples;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

public class GdxModuleInfoSample implements ApplicationListener {

	private static final Logger log = new Logger(GdxModuleInfoSample.class.getName(), Logger.DEBUG);

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		// Gdx modules
		log.debug("app= " + Gdx.app);
		log.debug("audio= " + Gdx.audio);
		log.debug("input= " + Gdx.input);
		log.debug("files= " + Gdx.files);
		log.debug("net= " + Gdx.net);
		log.debug("graphics= " + Gdx.graphics);

	}

	@Override
	public void resize(int width, int height) {	}

	@Override
	public void render() { }

	private void draw(){ }

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void dispose() {	}
}
