import static org.junit.Assert.*;

import org.junit.Test;

public class CalorieCalculatorTest {
	
	CalorieCalculator cc= new CalorieCalculator();
	long a= cc.calorie(60,45,21);
	long test= 984;
	@Test
	public void test() {
		System.out.println("@Test calorie():"+a+"="+test);
		assertEquals(a,test);
		
	}

}
