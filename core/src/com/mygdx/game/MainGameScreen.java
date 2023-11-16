package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import actors.ActorJugador;
import actors.ActorRoca;

public class MainGameScreen extends BaseScreen {

    private Stage stage;
    private ActorJugador jugador;
    private ActorRoca roca;
    private Texture texturaJugador;
    private Texture texturaRoca;

    public MainGameScreen(MyGdxGame game) {
        super(game);
        texturaJugador = new Texture("dino.png");
        texturaRoca = new Texture("roca.png");
    }

    @Override
    public void show() {
//        super.show();
        this.stage = new Stage();
        this.stage.setDebugAll(true);
        this.jugador = new ActorJugador(texturaJugador);
        this.roca = new ActorRoca(texturaRoca);
        this.stage.addActor(jugador);
        this.stage.addActor(roca);
        this.jugador.setPosition(20, 100);
        this.roca.setPosition(400, 100);
    }

    private void compColisiones() {
        if (jugador.isAlive() && jugador.getX() + jugador.getWidth() > roca.getX()) {
            System.out.println("Colision intentada");
            jugador.setAlive(false);
            roca.setAlive(false);
        }
    }

    @Override
    public void hide() {
        //para liberar la pantalla
        stage.dispose();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //PINTA PANTALLA?
        stage.act(); //actualiza todos los actores al dibujar
        stage.draw(); //dibuja los actores
        compColisiones();
        //boundy boxes
        if(!jugador.isAlive()){
//            stage.clear();
//            stage.dispose();
            jugador.setVisible(false);
            roca.setX(jugador.getX() + 70);
//            roca.setVisible(false);
        }
        stage.draw();
    }



    @Override
    public void dispose() {
        super.dispose();
        texturaJugador.dispose();
        texturaRoca.dispose();
    }


}
