package Bai1_3;

public class Rectangle {
	float length;
	float width;
	public String toString() {
		return "Rectangle[length="+ length +"width =" + width +"]";
	}
	public Rectangle(float f, float g) {
		length = f;
		width = g;
	}
	public Rectangle() {
		length = (float) 1.1;
		width = (float) 1.2;
	}
	
	public float getLength() {
		return length;
	}
	public float getWidth() {
		return width;
	}
	public double getArea() {
		return length * width;
	}
	public double getPerimeter() {
		return (length + width)*2;
	}
	public void setLength(float f) {
		// TODO Auto-generated method stub
		
	}
	public void setWidth(float f) {
		// TODO Auto-generated method stub
		
	}
}
