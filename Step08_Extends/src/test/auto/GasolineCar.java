package test.auto;

public class GasolineCar extends Car{

	public GasolineCar(Engine engine) {
		super(engine);
	}
	
	public void refuel() {
		System.out.println("주유하다!");
	}
	
	public void stop() {
		System.out.println("멈추다.");
	}
}
