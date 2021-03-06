package com.gdx.samples;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;
import com.gdx.samples.common.SampleBase;
import com.gdx.samples.common.SampleInfo;

public class GdxModuleInfoSample extends SampleBase {


	public static final SampleInfo SAMPLE_INFO = new SampleInfo(GdxModuleInfoSample.class);
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
