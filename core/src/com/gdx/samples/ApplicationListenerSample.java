package com.gdx.samples;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;


public class ApplicationListenerSample implements com.badlogic.gdx.ApplicationListener {

	private static Logger log = new Logger(ApplicationListenerSample.class.getName(), Logger.DEBUG);
	private boolean renderInterrupted = true;

	// Initializes the game and loads resources
	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		log.debug("create()");
	}

	// Handles screen size configurations
	@Override
	public void resize(int width, int height) {
		log.debug("resize() width="+ width +" height="+ height);
	}

	// Renders game elements (60fps)
	@Override
	public void render() {
		if(renderInterrupted){
			log.debug("render()");
			renderInterrupted = false;
		}
	}

	// Saves game state when it loses focus (gameplay does not pause, unless
	// that the developer wants it to pause).
	@Override
	public void pause() {
		log.debug("pause()");
		this.renderInterrupted = true;
	}

	// Restores the game state
	@Override
	public void resume() {
		log.debug("resume()");
		this.renderInterrupted = false;
	}

	// Frees resources and cleanup
	@Override
	public void dispose() {
		log.debug("dispose()");
	}
}
