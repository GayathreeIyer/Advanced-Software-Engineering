import static org.junit.Assert.*;

import org.junit.Test;

public class CalorieCalculatorTest3 {
	
	CalorieCalculator cc= new CalorieCalculator();
	long a= cc.calorie(64,50,30);
	long test= 1002;
	@Test
	public void test() {
		System.out.println("@Test calorie():"+a+"="+test);
		assertEquals(a,test);
		
	}

}
