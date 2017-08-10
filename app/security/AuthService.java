package security;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class AuthService extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context ctx) {
        System.out.println("auth_service   ====/   getUserName called @ @ @");
        return super.getUsername(ctx);
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        System.out.println("auth_service    ====/   unauthorized user !!!");
        return super.onUnauthorized(ctx);
    }
}
