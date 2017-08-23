package controllers;

import com.eclipsesource.json.JsonObject;
import constants.ResponseConstant;
import daos.MongoDbDao;
import daos.PostgreDao;
import daos.UserDao;
import daos.UserSessionDao;
import models.UserModel;
import models.UserSessionModel;
import parsers.UserParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import security.AuthService;
import security.Generator;
import utils.DateTimeUtil;
import utils.LogUtil;
import validators.UserValidator;

public class UserController extends Controller {
    private static UserParser userParser = new UserParser();

    public static Result createUser() {
        UserModel model = userParser.parseUser(request());

        JsonObject result = new JsonObject();
        if (UserValidator.hasValidUserCreateData(model)) {
            if (!UserDao.getInstance().isExistingUser(model.getEmail())) {
                String companyId = "company_" + Generator.createRandomCompanyId();
                PostgreDao.createDatabase(companyId);
                model.setCompanyId(companyId);
                if (UserDao.getInstance().createUser(model)) {

                    result.add(ResponseConstant.PARAMS_RESPONSE_CODE, ResponseConstant.RESPONSE_CODE_SUCCESS);
                    result.add(ResponseConstant.PARAMS_RESPONSE_MESSAGE, "user created successfully");
                } else {
                    result.add(ResponseConstant.PARAMS_RESPONSE_CODE, ResponseConstant.RESPONSE_CODE_FAILED);
                    result.add(ResponseConstant.PARAMS_RESPONSE_MESSAGE, "Can't create user");
                }
            } else {
                result.add(ResponseConstant.PARAMS_RESPONSE_CODE, ResponseConstant.RESPONSE_CODE_FAILED);
                result.add(ResponseConstant.PARAMS_RESPONSE_MESSAGE, "User already exist");
            }
        } else {
            result.add(ResponseConstant.PARAMS_RESPONSE_CODE, ResponseConstant.RESPONSE_CODE_FAILED);
            result.add(ResponseConstant.PARAMS_RESPONSE_MESSAGE, "Insufficient user data");
        }

        return ok(result.toString());
    }

    public static Result userLogIn() {
        UserModel model = userParser.parseUser(request());

        JsonObject result = new JsonObject();
        if (model != null && UserValidator.hasRequiredDataForLogin(model)) {
            UserModel userModel = UserDao.getInstance().logIn(model.getEmail(), model.getPassword());
            if (userModel != null) {
                UserSessionModel userSessionModel = new UserSessionModel();
                userSessionModel.setUserId(userModel.getEmail());
                userSessionModel.setToken(Generator.createAccessToken());
                userSessionModel.setRefreshToken(Generator.createRefreshToken());
                userSessionModel.setStartDateTime(DateTimeUtil.getCurrentDate());
                UserSessionDao.getInstance().save(userSessionModel);

                LogUtil.printLogMessage("login_user", "user_session", userSessionModel.toString());

                result.add(ResponseConstant.PARAMS_RESPONSE_CODE, ResponseConstant.RESPONSE_CODE_SUCCESS);
                result.add(ResponseConstant.PARAMS_RESPONSE_MESSAGE, "login success");
                result.add(ResponseConstant.PARAMS_RESPONSE_DATA, userSessionModel.toJson());
            } else {
                result.add(ResponseConstant.PARAMS_RESPONSE_CODE, ResponseConstant.RESPONSE_CODE_FAILED);
                result.add(ResponseConstant.PARAMS_RESPONSE_MESSAGE, "Invalid email or password");
            }
        } else {
            result.add(ResponseConstant.PARAMS_RESPONSE_CODE, ResponseConstant.RESPONSE_CODE_FAILED);
            result.add(ResponseConstant.PARAMS_RESPONSE_MESSAGE, "Email and password required");
        }

        return ok(result.toString());
    }

    @Security.Authenticated(AuthService.class)
    public static Result getUserInfo() {
        UserModel model = userParser.parseUser(request());
        JsonObject result = new JsonObject();
        if (model != null) {
            model = UserDao.getInstance().getUserInfo(model.getEmail());
            if (model != null) {
                result.add(ResponseConstant.PARAMS_RESPONSE_DATA, model.toJson());
                result.add(ResponseConstant.PARAMS_RESPONSE_CODE, ResponseConstant.RESPONSE_CODE_SUCCESS);
                result.add(ResponseConstant.PARAMS_RESPONSE_MESSAGE, "User found");
            } else {
                result.add(ResponseConstant.PARAMS_RESPONSE_CODE, ResponseConstant.RESPONSE_CODE_FAILED);
                result.add(ResponseConstant.PARAMS_RESPONSE_MESSAGE, "No user found with " + model.getEmail());
            }
        } else {
            result.add(ResponseConstant.PARAMS_RESPONSE_CODE, ResponseConstant.RESPONSE_CODE_FAILED);
            result.add(ResponseConstant.PARAMS_RESPONSE_MESSAGE, "Email and password required");
        }

        return ok(result.toString());
    }

}
