package com.gdx.samples;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.samples.common.SampleBase;
import com.gdx.samples.common.SampleInfo;
import com.gdx.samples.utils.GdxUtils;

public class InputPollingSample extends SampleBase {


	public static final SampleInfo SAMPLE_INFO = new SampleInfo(InputPollingSample.class);
	private static final Logger log = new Logger(InputPollingSample.class.getName(), Logger.DEBUG);

	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private BitmapFont font;

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

		camera = new OrthographicCamera();
		viewport = new FitViewport(1080, 720, camera);
		batch = new SpriteBatch();
		font = new BitmapFont();//Gdx.files.internal("fonts/oswald-32.fnt"));

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

	@Override
	public void render() {
		GdxUtils.clearScreen();
		batch.setProjectionMatrix(camera.combined);
		batch.begin(); // draw command block

		draw();

		batch.end();

	}

	private void draw(){
		// get mouse/touch x and y
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();

		// buttons
		boolean leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		boolean rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);

		font.draw(batch,
				"Mouse/Touch : x= " + mouseX + " y= " + mouseY,
				20f,
				720-20f);

		font.draw(batch,
				leftPressed ? "Left button pressed" : "Left button not pressed",
				20f,
				720-50f);

		font.draw(batch,
				rightPressed ? "Right button pressed" : "Right button not pressed",
				20f,
				720-80f);

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
