package vtiger_practice;

public class GenericMethod {

	public static void main(String[] args) {
		// Caller
		int sum =add(3825,4032);
		System.out.println(sum);

	}
	
	public static int add(int a, int b) //called method
	{
		int c = a+b;
		return c;
	}

}
