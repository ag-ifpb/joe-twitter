package ag.ifpb.teemp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ag.ifpb.teemp.tweet.AuthenticatorOfTwitter;
import ag.ifpb.teemp.tweet.Credentials;
import ag.ifpb.teemp.tweet.EndpointInTwitter;

public class TweetAuthTest {

	public static void main(String[] args) {
		Credentials c = new Credentials("", "");
        AuthenticatorOfTwitter authenticator = new AuthenticatorOfTwitter(c);
        EndpointInTwitter endpoint = new EndpointInTwitter("POST", "https://api.twitter.com/oauth/request_token");
        String authorization = authenticator.in(endpoint).authenticate();
        //
        System.out.println("authorization = " + authorization);
        //
        Client newBuilder = ClientBuilder.newBuilder().build();
        WebTarget target = newBuilder.target("https://api.twitter.com/oauth/request_token");

        Response post = target.request().accept(MediaType.APPLICATION_JSON)
                .header("Authorization", authorization)
                .post(Entity.json(""));

        String oauth_token = post.readEntity(String.class);
        System.out.println("readObject = " + oauth_token);
       
        System.out.println("https://api.twitter.com/oauth/authenticate?" + oauth_token);
        //response.sendRedirect("https://api.twitter.com/oauth/authenticate?" + oauth_token);
        //
        //http://localhost:8080/ProjetoTwitter/twitter?oauth_token=5EFfGgAAAAAA5dHBAAABYsB-raY&oauth_verifier=hrCm3AjprO4w6WPfDdAdRh8FgE3FOsaV
	}
}
