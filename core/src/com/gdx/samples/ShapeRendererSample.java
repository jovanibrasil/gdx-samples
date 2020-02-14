package com.gdx.samples;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.samples.common.SampleBase;
import com.gdx.samples.common.SampleInfo;
import com.gdx.samples.utils.GdxUtils;

public class ShapeRendererSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(ShapeRendererSample.class);

    private static final Logger log = new Logger(ShapeRendererSample.class.getName(), Logger.DEBUG);

    private static final float WORLD_WIDTH = 20f;
    private static final float WORLD_HEIGHT = 40f;

    private OrthographicCamera camera;
    private Viewport viewport;

    private ShapeRenderer renderer;

    private Texture texture;

    private int width = 1;
    private int height = 1;

    private boolean drawGrid = true;
    private boolean drawCircles = true;
    private boolean drawRectangles = true;
    private boolean drawPoints = true;


    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        this.texture = new Texture("raw/character.png");
        this.renderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        this.renderer.setProjectionMatrix(camera.combined);

        if(this.drawGrid){
            drawGrid();
        }
        if(this.drawCircles){
            drawCircles();
        }
        if(this.drawRectangles){
            drawRectangles();
        }
        if(this.drawPoints){
            drawPoints();
        }
    }

    public void drawCircles(){
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.circle(10, 10, 2, 30);
        renderer.circle(10, 20, 2, 5);
        renderer.end();
    }

    public void drawRectangles(){
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.rect(0, 0, 3, 1);
        renderer.end();
    }

    public void drawPoints(){
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.point(18, 18, 0);
        renderer.end();
    }

    public void drawGrid(){
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);

        int worldWidth = (int) WORLD_WIDTH;
        int worldHeight = (int) WORLD_HEIGHT;

        // vertical
        for(int x=-worldWidth; x< worldHeight; x++){
            renderer.line(x, -worldHeight, x, worldHeight);
        }

        // horizoltal
        for(int y=-worldHeight; y< worldHeight; y++){
            renderer.line(-worldWidth, y, worldWidth, y);
        }

        renderer.setColor(Color.RED);
        renderer.line(-worldWidth, worldHeight/2, +worldWidth, worldHeight/2);
        renderer.line(worldWidth/2, worldHeight, worldWidth/2, -worldHeight);


        renderer.end();
    }

    public void draw(){


    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.G){
            this.drawGrid = !this.drawGrid;
        }else if(keycode == Input.Keys.R){
            this.drawRectangles = !this.drawRectangles;
        }else if(keycode == Input.Keys.C){
            this.drawCircles = !this.drawCircles;
        }else if(keycode == Input.Keys.P){
            this.drawPoints = !this.drawPoints;
        }

        return true;
    }
}
