package AOP_Basic_Spring07;

public class NewCalc implements Calc {

	@Override
	public int ADD(int x, int y) {
		//보조(공통) 관심사(cross-cutting-concern)
		int sum = x + y; //core-concern
		//보조(공통) 관심사(cross-cutting-concern)
		return sum;
	}

	@Override
	public int MUL(int x, int y) {
		//보조(공통) 관심사(cross-cutting-concern)
		int mul = x * y;
		//보조(공통) 관심사(cross-cutting-concern)
		return mul;
	}

	@Override
	public int SUB(int x, int y) {
		//보조(공통) 관심사(cross-cutting-concern)
		int sub = x - y;
		//보조(공통) 관심사(cross-cutting-concern)
		return sub;
	}

}
