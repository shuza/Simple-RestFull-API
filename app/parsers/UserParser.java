package parsers;

import constants.ParamConstant;
import models.UserModel;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Http;
import utils.ParserUtil;

public class UserParser extends ParserUtil {

    public UserModel parseUser(Http.Request request) {
        DynamicForm params = Form.form().bindFromRequest(request);

        UserModel userModel = new UserModel();
        if (hasKey(params, ParamConstant.USER_EMAIL)) {
            userModel.setEmail(getString(params, ParamConstant.USER_EMAIL));
        }
        if (hasKey(params, ParamConstant.USER_NAME)) {
            userModel.setName(getString(params, ParamConstant.USER_NAME));
        }
        if (hasKey(params, ParamConstant.USER_SUR_NAME)) {
            userModel.setSurName(getString(params, ParamConstant.USER_SUR_NAME));
        }
        if (hasKey(params, ParamConstant.USER_PHONE)) {
            userModel.setPhone(getString(params, ParamConstant.USER_PHONE));
        }
        if (hasKey(params, ParamConstant.USER_COMPANY_ID)) {
            userModel.setCompanyId(getString(params, ParamConstant.USER_COMPANY_ID));
        }
        if (hasKey(params, ParamConstant.USER_PASSWORD)) {
            userModel.setPassword(getString(params, ParamConstant.USER_PASSWORD));
        }
        if (hasKey(params, ParamConstant.USER_ACCESS_ROLE)) {
            userModel.setAccessRole(getInt(params, ParamConstant.USER_ACCESS_ROLE));
        }

        return userModel;
    }

}
