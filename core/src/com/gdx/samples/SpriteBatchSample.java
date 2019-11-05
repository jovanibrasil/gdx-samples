package com.gdx.samples;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.samples.common.SampleBase;
import com.gdx.samples.common.SampleInfo;
import com.gdx.samples.utils.GdxUtils;

public class SpriteBatchSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(SpriteBatchSample.class);

    private static final Logger log = new Logger(SpriteBatchSample.class.getName(), Logger.DEBUG);

    private static final float WORLD_WIDTH = 10.8f; // 10.8 * 100 = 1080
    private static final float WORLD_HEIGHT = 7.2f; // 7.2 * 100 = 720

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    private Texture texture;
    private Color oldColr;

    private int width = 1;
    private int height = 1;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        this.camera = new OrthographicCamera();
        this.batch = new SpriteBatch();
        this.viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        this.texture = new Texture("raw/character.png");
        this.oldColr = new Color();
    }


    @Override
    public void render() {
        GdxUtils.clearScreen();

        this.batch.setProjectionMatrix(camera.combined);
        batch.begin();
        draw();
        batch.end();

    }

    public void draw(){

        log.debug("Drawing blue monster ...");

        batch.draw(texture,
                0, 0, // position
                width/2f, height/2, // the origin is the sprite center
                width, height, // sprite dimensions
                1.0f, 1.0f, // scale
                0.0f, // rotation
                0, 0, // beginning of the texture
                texture.getWidth(), texture.getHeight(), // draw all the texture
                false, false); // flip options

        this.oldColr.set(this.batch.getColor());
        this.batch.setColor(Color.GREEN);

        log.debug("Drawing green monster ...");

        batch.draw(texture,
                3, 3, // position
                width/2f, height/2, // the origin is the sprite center
                width, height, // sprite dimensions
                1.0f, 1.0f, // scale
                0.0f, // rotation
                0, 0, // beginning of the texture
                texture.getWidth(), texture.getHeight(), // draw all the texture
                false, false); // flip options

        batch.setColor(oldColr);
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        this.texture.dispose();
        this.batch.dispose();
    }

}
