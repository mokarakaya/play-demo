package controllers;

import java.util.List;

import play.libs.F.Promise;
import play.libs.WS.Response;
import play.libs.WS;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.F.Function;
import play.libs.F.Promise;

	public class Parallel extends Controller{
	
		public static Result index(){
			Promise<List<Timing>> all= Promise.waitAll(
					Timing.timedRequest("http://www.yahoo.com"),
					Timing.timedRequest("http://www.bing.com"),
					Timing.timedRequest("http://www.google.com")
			);
			
			return async(all.map(new play.libs.F.Function<List<Timing>,Result>(){
				public Result apply(List<Timing> timings){
					return ok (play.libs.Json.toJson(timings));
				}
			}));
		}
		
		public static Result callGoogle(){
			
			//non blocking http call
			play.libs.F.Promise<play.libs.WS.Response> response=WS.url("http://www.google.com").get();
			
            Promise<Result> result= response.map(toResult);
            return async(result);
		}
		
		//Transform response into a Result
		private static Function<Response,Result> toResult=
				new Function<Response,Result>(){
			public Result apply(Response response){
				return ok(response.getBody()).as("text/html");
			}
		};
		
		
	}
