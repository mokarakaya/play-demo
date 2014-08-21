package controllers;

import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.WS;
import play.libs.WS.Response;

public class Timing {
	
	public String url;
	public long latency;
	public long start;
	
	public Timing(String url,long latency,long start){
		this.url=url;
		this.latency=latency;
		this.start=start;
	}
	
	public static Promise<Timing> timedRequest(final String url){
		final long start = System.currentTimeMillis();
		Promise<Response> res= WS.url(url).get();
		
		return res.map(new Function<Response,Timing>(){
			public Timing apply (Response response){
				long latency=System.currentTimeMillis()-start;
				return new Timing(url,latency,start);
			}
		});
	}
	
}
