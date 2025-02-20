package Bai1_1;

public class TestMain {
	public static void main(String[] args) {
		Circle c1 = new Circle();
		System.out.println("Radius is " + c1.getRadius() + " and Area is " + c1.getArea());
		Circle c2 = new Circle(2.0);
		System.out.println(("Radius is " + c2.getRadius() + " and Area is" + c2.getArea())); 
	}
}