package com.gdx.samples;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.samples.common.SampleBase;
import com.gdx.samples.common.SampleInfo;
import com.gdx.samples.utils.GdxUtils;

public class OrthographicCameraSample extends SampleBase {


	public  static final SampleInfo SAMPLE_INFO = new SampleInfo(OrthographicCameraSample.class);
	private static final Logger log = new Logger(OrthographicCameraSample.class.getName(), Logger.DEBUG);

	private static final float WORLD_WIDTH = 10.8f;
	private static final float WORLD_HEIGHT = 7.2f;

	private static final float CAMERA_SPEED = 2.0f;
	private static final float CAMERA_ZOOM_SPEED = 2.0f;

	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;

	private Texture texture;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		this.batch = new SpriteBatch();
		this.texture = new Texture(Gdx.files.internal("raw/level-bg.png"));

	}

	@Override
	public void render() {

		queryInput();
		GdxUtils.clearScreen();

		// render objects
		this.batch.setProjectionMatrix(camera.combined);
		batch.begin();
		draw();
		batch.end();

	}

	public void queryInput(){

		// time passed between two frames
		float deltaTime = Gdx.graphics.getDeltaTime();

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			this.camera.position.x -= CAMERA_SPEED * deltaTime;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			this.camera.position.x += CAMERA_SPEED * deltaTime;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			this.camera.position.y += CAMERA_SPEED * deltaTime;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			this.camera.position.y -= CAMERA_SPEED * deltaTime;
		}


		if(Gdx.input.isKeyPressed(Input.Keys.PAGE_UP)){ // zoom-in
			this.camera.zoom -= CAMERA_ZOOM_SPEED * deltaTime;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.PAGE_DOWN)){ // zoom-out
			this.camera.zoom += CAMERA_ZOOM_SPEED * deltaTime;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
			log.debug("camera position: " + camera.position);
			log.debug("camera zoom: " + camera.zoom);
		}

		this.camera.update();

	}

	public void draw(){
		this.batch.draw(
				texture, 0, 0, WORLD_WIDTH, WORLD_HEIGHT
		);
	}

	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height, true); // with center camera enabled
	}

	@Override
	public void dispose() {
		this.batch.dispose();
		this.texture.dispose();
	}
}
