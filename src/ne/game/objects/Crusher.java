package ne.game.objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Crusher extends SpielObjekt {

    private Input input;
    private Rectangle shape;

    private float acceleration=0.00001f;


    public Crusher(int x, int y, Image image, Input input) {
        super(x, y, image);
        this.input = input;
        shape = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    @Override
    public void draw(Graphics g) {
        this.getImage().drawCentered(this.getX(), this.getY());
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void update(int delta) {
        boolean pressed = false;
        if (input.isKeyDown(Input.KEY_A)&&(this.getX() - (int) this.acceleration - this.getWith()/2) > 0) {
            this.setX(this.getX() - delta);
            pressed = true;
        }
        if (input.isKeyDown(Input.KEY_D)&&(this.getX() + (int) this.acceleration + this.getWith()/2) < 1024) {
            this.setX(this.getX() + delta);
            pressed = true;
        }

        if (pressed){
            acceleration+= delta;
            if(acceleration > 10) acceleration=10;
        } else {
            acceleration = 0.00001f;
        }
        shape.setCenterX(this.getX());
        shape.setCenterY(this.getY());
    }
}
