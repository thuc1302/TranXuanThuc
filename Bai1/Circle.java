package Bai1;

public class Circle {
	String color;
	double radius;
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
}
