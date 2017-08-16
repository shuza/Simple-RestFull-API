package models;

import com.eclipsesource.json.JsonObject;
import constants.ParamConstant;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("sessions")
public class UserSessionModel extends CommonModel {
    @Id
    private ObjectId objectId;
    private String userId;
    private String token;
    private String startDateTime;
    private String endDateTime;
    private String duration;
    private int loginStatus;
    private String refreshToken;
    private int companyRouteId;

    public UserSessionModel() {
        userId = "";
        token = "";
        startDateTime = "";
        endDateTime = "";
        duration = "";
        loginStatus = 0;
        refreshToken = "";
        companyRouteId = 0;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getCompanyRouteId() {
        return companyRouteId;
    }

    public void setCompanyRouteId(int companyRouteId) {
        this.companyRouteId = companyRouteId;
    }

    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.add(ParamConstant.USER_ID, getUserId());
        obj.add(ParamConstant.USER_ACCESS_TOKEN, getToken());
        return obj;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
