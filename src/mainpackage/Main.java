package mainpackage;

public class Main {
	public static void main(String[] args) throws Exception {
		Controller controller = Controller.getIstance();
		controller.start("AVRO");
	}
	
}
