import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/mashup")
public class Mashup {
	public static void main(String args[]){}
	public Mashup(){}
	
	  public long height,weight,age,calorie,fat,protein,carb;
	  @GET
	  @Path("/caloriecalculation")
	  @Produces("application/xml")
	  public long calorie(long weight,long height,long age){
		CalorieCalculator object=new CalorieCalculator();
		calorie=object.calorie(weight,height,age);
		return calorie;
	  }
	  
	  @GET
	  @Path("/excesscalculation")
	  @Produces("application/xml")
	  
	  public long calculatefat(long calorie)
	  { ExcessCalculator object=new ExcessCalculator();
		fat=object.calculatefat(calorie);
		return fat;
	  	
	  }
	  public long calculateprotein(long calorie)
	  { 
		  ExcessCalculator object=new ExcessCalculator();
			protein=object.calculateprotein(calorie);
			return protein;
	  	
	  }
	  public long calculatecarb(long calorie)
	  { ExcessCalculator object=new ExcessCalculator();
		carb=object.calculatecarb(calorie);
		return carb;
	  }
	  
	  

	@Path("{w}/{h}/{a}")
	  @GET
	  @Produces("application/json")
	  public Response first(@PathParam("w")long weight,@PathParam("h")long height,@PathParam("a")long age) throws JSONException {
		JSONObject jsonObject = new JSONObject();
				
		jsonObject.put("weight", weight);
		jsonObject.put("height",height);
		jsonObject.put("age",age);
		jsonObject.put("calorie",calorie(weight,height,age));
		jsonObject.put("fat",calculatefat(calorie));
		jsonObject.put("protein",calculateprotein(calorie));
		jsonObject.put("carb",calculatecarb(calorie));
		

		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	  }
}

