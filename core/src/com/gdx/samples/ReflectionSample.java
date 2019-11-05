package com.gdx.samples;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.samples.common.SampleBase;
import com.gdx.samples.common.SampleInfo;
import com.gdx.samples.utils.GdxUtils;

import java.util.Arrays;

public class ReflectionSample extends SampleBase	 {

	public static final SampleInfo SAMPLE_INFO = new SampleInfo(ReflectionSample.class);
	private static final Logger log = new Logger(ReflectionSample.class.getName(), Logger.DEBUG);

	private static final int MAX_MESSAGE_COUNT = 15;
	private final Array<String> messages = new Array<>();

	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private BitmapFont font;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		camera = new OrthographicCamera();
		viewport = new FitViewport(1080, 720, camera);
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

		debugReflection(ReflectionSample.class);

	}

	public void debugReflection(Class<?> clazz){
		Field[] fields = ClassReflection.getDeclaredFields(clazz);
		Method[] methods = ClassReflection.getMethods(clazz);

		log.debug("Class name:" + clazz.getName());
		log.debug(fields.length + " fields");

		for (Field field: fields) {
			log.debug("name: " + field.getName() + " type: " + field.getType());
		}

		log.debug(methods.length + " methods");

		for (Method method: methods) {
			log.debug("name: " + method.getName() + " parameter types: " +
					Arrays.asList(method.getParameterTypes()));
		}

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
		for (int i = 0; i < this.messages.size; i++){
			font.draw(batch,
					"["+i+"] "+ messages.get(i),
					20f,
					720-40f * (i + 1));

		}
	}

	public void addMessage(String message){
		this.messages.add(message);
		log.debug(messages.size + "");
		if(messages.size >= MAX_MESSAGE_COUNT){
			this.messages.removeIndex(0);
		}

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
