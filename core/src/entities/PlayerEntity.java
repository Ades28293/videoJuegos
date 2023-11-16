package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sun.org.apache.bcel.internal.Const;

public class PlayerEntity extends Actor {

    private Texture texture;
    private World world;
    private Boolean alive;
    private Body body;
    private Fixture fixture;

    public PlayerEntity(World world, Texture texture, Vector2 position) {
        this.world = world;
        this.texture = texture;

        //cuerpo
        BodyDef def = new BodyDef();
        def.position.set(position); //columna y fila
        def.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(def);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(1, 1); //dimensiones
        fixture = body.createFixture(polygonShape, 1);

        polygonShape.dispose();

        setSize(Constants.pixelInmed,Constants.pixelInmed);
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
            setPosition((body.getPosition().x ) * Constants.pixelInmed, (body.getPosition().y ) *Constants.pixelInmed);
            batch.draw(texture,getX(),getY(),getWidth(),getHeight());
    }
}
