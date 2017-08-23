package security;

import com.eclipsesource.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import constants.ResponseConstant;
import daos.UserSessionDao;
import models.UserSessionModel;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import utils.LogUtil;

public class AuthService extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context context) {
        System.out.println("auth_service   ====/   getUserName called @ @ @");
        try {
            JsonNode params = context.request().body().asJson();
            String token = params.get("token").asText();
            UserSessionModel model = UserSessionDao.getInstance().getUserSession(token);
            if (model != null) {
                return model.getToken();
            } else {
                return super.getUsername(context);
            }
        } catch (Exception e) {
            LogUtil.printLogMessage("Auth service", "params error", e.getMessage());
            e.printStackTrace();
            return super.getUsername(context);
        }
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        JsonObject result = new JsonObject();
        result.add(ResponseConstant.PARAMS_RESPONSE_CODE, ResponseConstant.RESPONSE_CODE_UNAUTHORIZE);
        result.add(ResponseConstant.PARAMS_RESPONSE_MESSAGE, "unauthorized user");
        return ok(result.toString());
    }
}
