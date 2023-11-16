package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2dScreen extends BaseScreen {
    private World word;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

    private Body body, sueloBody, rocaBody;
    private Fixture fixture, sueloFixture, rocaFixture;

    private boolean colisionDetected = false;

    public Box2dScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //PINTA PANTALLA?

        if (Gdx.input.justTouched() && !colisionDetected) {
            saltar();
        }


        float movY = body.getLinearVelocity().y;
        float movX=2;


        if(colisionDetected){
           movX=0;
        }
        body.setLinearVelocity(movX,movY);

        word.step(delta, 6, 2); //por defecto.

        camera.update();
        renderer.render(word, camera.combined);


    }

    @Override
    public void show() {

        word = new World(new Vector2(0, -10), true);  //activo = true
        //primer componente es gravedad
        //y es gravedad, x es ?
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(32, 18);

       // camera.translate(0, -2);
        camera.translate(0, 1);
        //word
        word.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                //detecta autonomatica y almacena entre quienes esta en contacto
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                if (fixtureA == fixture && fixtureB == rocaFixture) {
                    colisionDetected = true;
                }

                if (fixtureB == fixture && fixtureA == rocaFixture) {
                    colisionDetected = true;
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        //cuerpo
        BodyDef def = createBody2dDef();
        body = word.createBody(def);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(1, 1); //dimensiones
        fixture = body.createFixture(polygonShape, 1);

        //suelo
        BodyDef def2 = createBody2dDefSuelo();
        sueloBody = word.createBody(def2);

        PolygonShape shape2 = new PolygonShape();
        shape2.setAsBox(500, 1); //dimensiones
        body.createFixture(shape2, 1);

        //roca
        BodyDef def3 = createBody2dDefRoca();
        rocaBody = word.createBody(def3);


        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f, -0.5f);
        vertices[1] = new Vector2(0.5f, -0.5f);
        vertices[2] = new Vector2(0, 0.5f);

        PolygonShape polygonRoca = new PolygonShape();
        polygonRoca.set(vertices);

        rocaFixture=rocaBody.createFixture(polygonRoca, 1);
    }
    private BodyDef createBody2dDef() {
        BodyDef def = new BodyDef();
        def.position.set(-2,-4);
        //def.position.set(-8, 0); //columna y fila
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }
    private BodyDef createBody2dDefRoca() {
        BodyDef def = new BodyDef();
        def.position.set(4.5f,-5.5f); //columna y fila
        //def.position.set(11f, 1.5f); //columna y fila
        def.type = BodyDef.BodyType.StaticBody;
        return def;
    }

    private BodyDef createBody2dDefSuelo() {
        BodyDef def = new BodyDef();
         def.position.set(0,-7);
        //def.position.set(0, 0); //columna y fila
        def.type = BodyDef.BodyType.StaticBody;
        return def;
    }



    @Override
    public void dispose() {
//        super.dispose();
        body.destroyFixture(fixture);
        word.destroyBody(body);
        word.dispose();
        renderer.dispose();
    }

    public void saltar() {
        Vector2 posicion = body.getPosition();
        //Impulso lineal
        body.applyLinearImpulse(5, 20, posicion.x, posicion.y, true); //salta en el eje de las Y
//        body.applyAngularImpulse(10,true);

    }
}
