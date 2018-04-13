package ag.ifpb.teemp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import ag.ifpb.teemp.tweet.AuthenticatorOfTwitter;
import ag.ifpb.teemp.tweet.Credentials;
import ag.ifpb.teemp.tweet.EndpointInTwitter;

public class ReqTweetsTest {

	public static void main(String[] args) throws URISyntaxException, IOException {
		//
		String token = "236425551-3pJqattN2k3qMBECpccOoH0xm9ixgPofLxLthKNU";
        String verifier = "IIFp1asf6F2ydysQwo2OM4wZeJOu2VfK1JQ3QdBOqRlU8";
        Credentials credentialsAuthenticated = new Credentials(token, verifier);
        //
        String urlBase = "https://api.twitter.com/1.1/statuses/home_timeline.json";
		//
		AuthenticatorOfTwitter authenticator = new AuthenticatorOfTwitter(credentialsAuthenticated);
        EndpointInTwitter endpoint = new EndpointInTwitter("GET", urlBase);
        //
        //984923178995662848
        //984882819917688832
        String maxId = null;
        for (int i = 0; i < 2; i++){
        	//parametros para o twitter
	        Map<String, String> map = new HashMap<>();
	        map.put("count", "1");
	        if (i > 0) map.put("max_id", maxId);
	        //autenticar a method+url+params 
	        String headerAuthorization = authenticator.in(endpoint).authenticate(map);
	        //criar um client
	        Client builder = ClientBuilder.newClient();
	        WebTarget timelineTarget0 = builder.target(urlBase);
	        WebTarget timelineTarget1 = timelineTarget0.queryParam("count", "1");
	        WebTarget timelineTarget2;
	        if (i > 0) {
	        	timelineTarget2  = timelineTarget1.queryParam("max_id", maxId);
	        } else {
	        	timelineTarget2  = timelineTarget1;//984914629565603840
	        }
	        //
	        Response time = timelineTarget2.request().accept(MediaType.APPLICATION_ATOM_XML)
	                .header("Authorization", headerAuthorization)
	                .get();
	        //
	        String readEntity = time.readEntity(String.class);
	        System.out.println(readEntity);
	        //
	        JSONArray ja = new JSONArray(readEntity);
	        int l = ja.length();
	        System.out.println("Vieram: " + l);
	        //
	        JSONObject jo = ja.getJSONObject(l-1);
	        //retweets
	        System.out.println("Retweets: " + jo.getInt("retweet_count"));
	        if (jo.getBoolean("retweeted")){
	        	System.out.println("Retweeted---> " + jo.getJSONObject("user").getString("screen_name"));
	        }
	        //mentions
	        System.out.println("Mentions: " + jo.getJSONObject("entities").getJSONArray("user_mentions").length());
	        JSONArray mentions = jo.getJSONObject("entities").getJSONArray("user_mentions");
	        for(int j = 0; j < mentions.length(); j++){
	        	System.out.println("Mentioned---> " + mentions.getJSONObject(j).getString("screen_name"));
	        }
	        maxId = jo.getString("id_str");
        }
	}
}
