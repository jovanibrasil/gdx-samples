package com.gdx.samples;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.samples.common.SampleBase;
import com.gdx.samples.common.SampleInfo;
import com.gdx.samples.utils.GdxUtils;

public class ViewPortsSample extends SampleBase {


	public  static final SampleInfo SAMPLE_INFO = new SampleInfo(ViewPortsSample.class);
	private static final Logger log = new Logger(ViewPortsSample.class.getName(), Logger.DEBUG);

	private static final float WORLD_WIDTH = 800f;
	private static final float WORLD_HEIGHT = 600f;

	private static final float CAMERA_SPEED = 2.0f;
	private static final float CAMERA_ZOOM_SPEED = 2.0f;

	private OrthographicCamera camera;
	private Viewport currentViewport;
	private SpriteBatch batch;
	private Texture texture;
	private BitmapFont font;
	private String currentViewPortName;

	private ArrayMap<String, Viewport> viewPorts = new ArrayMap<>();

	private int currentViewPortIndex;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		this.camera = new OrthographicCamera();
		this.batch = new SpriteBatch();
		this.texture = new Texture(Gdx.files.internal("raw/level-bg.png"));
		this.font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));
		Gdx.input.setInputProcessor(this);

		createViewPorts();
		selectNextViewPort();
	}

	@Override
	public boolean touchDown(int i, int i1, int i2, int i3) {
		log.info("touchDown ...");
		selectNextViewPort();
		return true;
	}

	public void createViewPorts(){

		// Viewport are strategies to handle different resolutions

		// Dimensions are equal: virtual and real dimensions
		this.viewPorts.put(StretchViewport.class.getSimpleName(),
				new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
		// Supports virtual size
		// There are black bars
		this.viewPorts.put(FitViewport.class.getSimpleName(),
				new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
		// Supports virtual size
		// There are not black bars
		this.viewPorts.put(FillViewport.class.getSimpleName(),
				new FillViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
		// Keeps the word aspect ratio
		this.viewPorts.put(ExtendViewport.class.getSimpleName(),
				new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
		//  Without support to virtual size
		this.viewPorts.put(ScreenViewport.class.getSimpleName(),
				new ScreenViewport(camera));

		currentViewPortIndex = -1;

	}

	public void selectNextViewPort(){
		// circular array
		currentViewPortIndex = (currentViewPortIndex + 1) % viewPorts.size;
		currentViewport = viewPorts.getValueAt(currentViewPortIndex);
		currentViewPortName = viewPorts.getKeyAt(currentViewPortIndex);

		currentViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		log.debug("Selected view port="+currentViewPortName);
	}

	@Override
	public void render() {
		//queryInput();
		GdxUtils.clearScreen();
		// render objects
		this.batch.setProjectionMatrix(camera.combined);
		batch.begin();
		draw();
		batch.end();

	}

	public void draw(){
		this.batch.draw(texture, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
		this.font.draw(batch, currentViewPortName, 120, 200);
	}

	@Override
	public void resize(int width, int height) {
		this.currentViewport.update(width, height, true); // with center camera enabled
	}

	@Override
	public void dispose() {
		this.batch.dispose();
		this.texture.dispose();
		this.font.dispose();
	}
}
