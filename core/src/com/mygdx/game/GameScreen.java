package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import entities.PlayerEntity;
import entities.RocaEntity;
import entities.SueloEntity;

public class GameScreen extends BaseScreen {

    private Stage stage;

    private World world;

    private PlayerEntity player;

    private RocaEntity roca;
    private SueloEntity suelo;

    public GameScreen(MyGdxGame game) {
        super(game);
        stage = new Stage(new FillViewport(640, 360));
        world = new World(new Vector2(0, -10), true);  //activo = true

    }

    @Override
    public void show() {

        Texture playerTexture = game.getManager().get("dino.png");
        Texture playerTexture2 = game.getManager().get("roca.png");
        Texture playerTexture3 = game.getManager().get("suelohierba.png");

        /*
        player=new PlayerEntity(world, playerTexture, new Vector2(1,3));
        roca = new RocaEntity(world, playerTexture2, new Vector2(5, 1));
        suelo = new SueloEntity(world, playerTexture3, new Vector2(0,-1));
        */
        player = new PlayerEntity(world, playerTexture, new Vector2(1, 2));
        roca = new RocaEntity(world, playerTexture2, new Vector2(2, 4));
        suelo = new SueloEntity(world, playerTexture3, new Vector2(0,-1));

        suelo.setSize(2000,190);
        stage.addActor(player);
        stage.addActor(roca);
        stage.addActor(suelo);
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();


    }

    @Override
    public void render(float delta) {
//        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //PINTA PANTALLA?

        stage.act();
        world.step(delta, 6, 2);
        stage.draw();
    }

}
