
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/excesscalculation")
public class ExcessCalculator {
	public long fat,protein,carb;
@GET
@Produces("application/xml")

public long calculatefat(long calorie)
{ 
	  long fat = Math.round((calorie*0.4)/9);
	  return fat;
	
}
public long calculateprotein(long calorie)
{ 
	 
	  long protein = Math.round((calorie*0.5)/4);
      return protein;
	
}
public long calculatecarb(long calorie)
{ 
	 long carb = Math.round((calorie*0.1)/4);
	 return carb;
	
}
}

