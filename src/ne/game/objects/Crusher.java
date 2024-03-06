package ne.game.objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Crusher extends SpielObjekt {

    private Input input;
    private Rectangle shape;

    private float acceleration = 0.0001f;
    private float speed = 5f;
    private int direction = 0;


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
        int olddirection = this.direction;
        if (input.isKeyDown(Input.
                KEY_A
        )) {
            this.setX(this.getX() - (int) this.speed);
            if ((this.getX() < this.getWith() / 2)) this.setX(this.getWith() / 2);
            this.direction = -1;
        }
        if (input.isKeyDown(Input.
                KEY_D
        )) {
            this.setX(this.getX() + (int) this.speed);
            if ((this.getX() > (1024 - this.getWith() / 2))) this.setX(1024 - this.getWith() / 2);
            this.direction = 1;
        }
        if (this.direction == olddirection) {
            speed += acceleration;
        } else {
            speed = 20f;
        }
        shape.setCenterX(this.getX());
        shape.setCenterY(this.getY());
    }

    public boolean intersects(Shape shape) {
        if (shape != null) {
            return this.getShape().intersects(shape);
        }
            return false;
    }
}

