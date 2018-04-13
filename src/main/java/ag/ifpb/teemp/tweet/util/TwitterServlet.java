package ag.ifpb.teemp.tweet.util;

/**
 *
 * @author Ricardo Job
 */
//@WebServlet(name = "Twitter", urlPatterns = {"/twitter"})
public class TwitterServlet {//extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//
////        readTimeline(credentialsAuthenticated, builder);
////        updateTimeline(credentialsAuthenticated, builder);
//    }
//
//    private String readAcessToken(Credentials credentials, Client builder) {
//        
//    }
//
//    private void readTimeline(Credentials c, Client builder) {
//        AuthenticatorOfTwitter authenticator = new AuthenticatorOfTwitter(c);
//        EndpointInTwitter endpoint = new EndpointInTwitter("GET", "https://api.twitter.com/1.1/statuses/user_timeline.json");
//        String headerAuthorization = authenticator.in(endpoint).authenticate();
//
//        WebTarget timelineTarget = builder.target("https://api.twitter.com/1.1/statuses/user_timeline.json");
//        Response time = timelineTarget.request().accept(MediaType.APPLICATION_JSON)
//                .header("Authorization", headerAuthorization)
//                .get();
//        String readEntity = time.readEntity(String.class);
//        System.out.println("---TIMELINE-----\n" + readEntity);
//    }
//
//    private void updateTimeline(Credentials credentials, Client builder) {
//        Map<String, String> map = new HashMap<>();
//        map.put("status", "exemplo em aula com @JoeNihon :)");
//
//        AuthenticatorOfTwitter authenticator = new AuthenticatorOfTwitter(credentials);
//        EndpointInTwitter endpoint = new EndpointInTwitter("POST", "https://api.twitter.com/1.1/statuses/update.json");
//        String headerAuthorization = authenticator.in(endpoint).authenticate(map);
//
//        Form status = new Form(new MultivaluedHashMap<>(map));
//        WebTarget updateTarget = builder.target("https://api.twitter.com/1.1/statuses/update.json");
//        Response update = updateTarget.request().accept(MediaType.APPLICATION_JSON)
//                .header("Authorization", headerAuthorization)
//                .post(Entity.form(status));
//        JsonObject ent = update.readEntity(JsonObject.class);
//        System.out.println("\n---UPDATE-----\n" + ent);
//    }
}
