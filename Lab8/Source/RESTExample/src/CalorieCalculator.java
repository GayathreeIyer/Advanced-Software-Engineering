


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/caloriecalculation")
public class CalorieCalculator {
	
	public long calorie;
	@GET
@Produces("application/xml")

public long calorie(long weight,long height, long age )
{
		calorie = (66 + (14 * weight) + (5 * height) - (7 * age));
	return calorie;
}
}