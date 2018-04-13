package ag.ifpb.teemp;

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

public class ReqTokenTest {
	
	private static String readAcessToken(Credentials credentials, Client builder) {
        Credentials c = new Credentials(credentials.token(), credentials.verifier());
        AuthenticatorOfTwitter authenticator = new AuthenticatorOfTwitter(c);
        EndpointInTwitter endpoint = new EndpointInTwitter("POST", "https://api.twitter.com/oauth/access_token");
        String authorization = authenticator.in(endpoint).authenticate();
        WebTarget target = builder.target("https://api.twitter.com/oauth/access_token");
        Form form = new Form("oauth_verifier", credentials.verifier());
        Response post = target.request()
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", authorization)
                .post(Entity.form(form));
        return post.readEntity(String.class);
    }

	public static void main(String[] args) {
		String oauth_token = "jeZO_wAAAAAA5dHBAAABYsCG3h4";
        String oauth_verifier = "M0vnLKFXrEOF2sHb9wX13itATdwBSkWD";
        //
        Client builder = ClientBuilder.newBuilder().build();
        //
        Credentials c = new Credentials(oauth_token, oauth_verifier);
        String access_token = readAcessToken(c, builder);
        //
        System.out.println(access_token);
        //
        String token = access_token.split("&")[0].split("=")[1];
        String verifier = access_token.split("&")[1].split("=")[1];
        Credentials credentialsAuthenticated = new Credentials(token, verifier);
        //
        //request.getSession().setAttribute("token", credentialsAuthenticated);
        //response.sendRedirect("http://localhost:8080/ProjetoTwitter/faces/timeline.xhtml");
	}
}
