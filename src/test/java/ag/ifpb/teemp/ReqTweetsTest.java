package ag.ifpb.teemp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        Map<String, String> map = new HashMap<>();
        //map.put("cursor", "-1");
        map.put("count", "1");
        //
        String headerAuthorization = authenticator.in(endpoint).authenticate(map);
        //
        Client builder = ClientBuilder.newClient();
        WebTarget timelineTarget0 = builder.target(urlBase);
        WebTarget timelineTarget1 = timelineTarget0.queryParam("count", "1");//.queryParam("cursor", "-1")
        //
        Response time = timelineTarget1.request().accept(MediaType.APPLICATION_ATOM_XML)
                .header("Authorization", headerAuthorization)
                .get();
        //
        String readEntity = time.readEntity(String.class);
        System.out.println(readEntity);
	}
}
