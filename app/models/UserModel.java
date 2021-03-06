package models;

import com.eclipsesource.json.JsonObject;
import constants.ParamConstant;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("users")
public class UserModel {

    @Id
    private ObjectId objectId;
    private String email;
    private String name;
    private String surName;
    private String phone;
    private String companyId;
    private String password;
    private int accessRole;

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessRole() {
        return accessRole;
    }

    public void setAccessRole(int accessRole) {
        this.accessRole = accessRole;
    }

    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.add(ParamConstant.USER_ID, getEmail());
        obj.add(ParamConstant.USER_NAME, getName());
        obj.add(ParamConstant.USER_SUR_NAME, getSurName());
        obj.add(ParamConstant.USER_COMPANY_ID, getCompanyId());
        obj.add(ParamConstant.USER_PHONE, getPhone());
        return obj;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
