package Bai2;

public class Circle {
	String color;
	double radius;
	public String toString() {
		return "Circle[radius=" + radius + "]";
	}
	public Circle() {
		color = "red";
		radius = 1.0;
	}
	
	public Circle(double r) {
		radius = r;
		color = "red";
	}
	public double getRadius(){
		return radius;
	}
	public double getArea() {
		return radius * radius * Math.PI;
	}
	public Object getCircumference() {
		return radius + radius;
	}
	public void setRadius(double d) {
		radius = d;
	}
}
