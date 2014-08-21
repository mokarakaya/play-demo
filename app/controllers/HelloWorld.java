package controllers;

import play.mvc.Result;
import play.mvc.Controller;

public class HelloWorld extends Controller{
	
	public static Result index(){
        return ok("Hello World" );
		
    }
	
	public static Result helloName(String name){
        return ok("Hello World: "+ name);
		
    }
	
    public static Result helloNameAge(String name, int age){
        return ok("Hello World "+name +"you are "+age);
    }
    
    public static Result helloView(String name, int age){
        return ok(views.html.helloWorld.render(name,age));
    }



}
