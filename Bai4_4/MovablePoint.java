package Bai4_4;

public class MovablePoint extends Point  {
    private float xSpeed = 0.0f;
    private float ySpeed = 0.0f;

    // Default constructor
    public MovablePoint() {
        super();
    }

    public MovablePoint(float xSpeed, float ySpeed) {
        super(); // Calls Point's default constructor
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public MovablePoint(float x, float y, float xSpeed, float ySpeed) {
        super(x, y); // Calls Point's parameterized constructor
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public float getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void setSpeed(float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public float[] getSpeed() {
        return new float[]{xSpeed, ySpeed};
    }

    public MovablePoint move() {
        setX(getX() + xSpeed);
        setY(getY() + ySpeed);
        return this;
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + "), speed=(" + xSpeed + ", " + ySpeed + ")";
    }
}

