package test.ui;

public class TestString {

	public static void main(String[] args) {
		System.out.println("".trim().isEmpty());
		System.out.println("  ".trim().isEmpty());
		System.out.println("doano cac".trim().isEmpty());
		System.out.println("ccesk".trim().isEmpty());
		
		String s = null;
		
		System.out.println(s != null? String.format("'%s'", s):s);
		System.out.println(s == null);
	}

}
