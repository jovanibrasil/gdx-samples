package com.gdx.samples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.codeandweb.physicseditor.PhysicsShapeCache;
import com.gdx.samples.common.SampleBase;
import com.gdx.samples.common.SampleInfo;

import java.util.HashMap;
import java.util.Random;

public class Box2DFruitsExample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(Box2DFruitsExample.class);

    static final int COUNT = 10;
    static final float STEP_TIME = 1f / 60f;
    static final int VELOCITY_ITERATIONS = 6;
    static final int POSITION_ITERATIONS = 2;
    static final float SCALE = 0.05f;

    OrthographicCamera camera;
    ExtendViewport viewport;
    SpriteBatch batch; // used to draw sprites
    //Texture img;
    TextureAtlas textureAtlas;
    Sprite banana;
    World world;

    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
    Body[] fruitBodies = new Body[COUNT];
    String[] names = new String[COUNT];

    float accumulator = 0;
    Body ground;
    PhysicsShapeCache physicsBodies;


    @Override
    public void create () {
        Box2D.init();
        world = new World(new Vector2(0, -10), true); // Direction that gravity pulls in our world
        physicsBodies = new PhysicsShapeCache("physics.xml");
        batch = new SpriteBatch();
        //img = new Texture("badlogic.jpg");
        textureAtlas = new TextureAtlas("sprites.txt");
        banana = textureAtlas.createSprite("banana");
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800, 600, camera);
        viewport = new ExtendViewport(50, 50, camera);
        addSprites();
        generateFruit();
    }

    private void addSprites() {
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();

        for (TextureAtlas.AtlasRegion region : regions) {
            Sprite sprite = textureAtlas.createSprite(region.name);

            float width = sprite.getWidth() * SCALE;
            float height = sprite.getHeight() * SCALE;

            sprite.setSize(width, height);
            sprite.setOrigin(0, 0);

            sprites.put(region.name, sprite);
        }
    }

    private void generateFruit() {
        String[] fruitNames = new String[]{"banana", "cherries", "orange"};

        Random random = new Random();

        for (int i = 0; i < fruitBodies.length; i++) {
            String name = fruitNames[random.nextInt(fruitNames.length)];

            float x = random.nextFloat() * 50;
            float y = random.nextFloat() * 50 + 50;

            names[i] = name;
            fruitBodies[i] = createBody(name, x, y, 0);
        }
    }

    private Body createBody(String name, float x, float y, float rotation) {
        Body body = physicsBodies.createBody(name, world, SCALE, SCALE);
        body.setTransform(x, y, rotation);

        return body;
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //banana.draw(batch);
        //batch.draw(img, 0, 0);
//        drawSprite("banana", 0, 0);
//        drawSprite("cherries", 100, 100);

        for (int i = 0; i < fruitBodies.length; i++) {
            Body body = fruitBodies[i];
            String name = names[i];

            Vector2 position = body.getPosition();
            // Te Box2D give us the sprite angle in radians
            float degrees = (float) Math.toDegrees(body.getAngle());
            drawSprite(name, position.x, position.y, degrees);
        }

        batch.end();

        stepWorld();
    }

    private void stepWorld() {
        float delta = Gdx.graphics.getDeltaTime();

        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;

            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }

    public void drawSprite(String name, float x, float y){
        Sprite sprite = sprites.get(name);
        sprite.setPosition(x, y);
        sprite.draw(batch);
    }

    private void drawSprite(String name, float x, float y, float degrees) {
        Sprite sprite = sprites.get(name);
        sprite.setPosition(x, y);
        sprite.setRotation(degrees);
        sprite.draw(batch);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
        createGround();
    }

    /**
     * Every time the screen is resized, the ground will have to be resized as well
     *
     */
    private void createGround() {
        if (ground != null) world.destroyBody(ground); // destroy old ground

        BodyDef bodyDef = new BodyDef();
        // A static body can only be moved manually
        bodyDef.type = BodyDef.BodyType.StaticBody;

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 1;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(camera.viewportWidth, 1); // ground has the cam width
        fixtureDef.shape = shape;

        ground = world.createBody(bodyDef);
        ground.createFixture(fixtureDef);
        ground.setTransform(0, 0, 0);

        shape.dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        //img.dispose();
        textureAtlas.dispose();
    }

}
