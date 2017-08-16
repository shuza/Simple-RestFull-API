package validators;

import models.UserModel;

public class UserValidator {

    public static boolean hasValidUserCreateData(UserModel model) {
        if (model.getEmail().isEmpty() || model.getName().isEmpty() || model.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean hasRequiredDataForLogin(UserModel model) {
        if (model.getEmail().isEmpty() || model.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }

}
