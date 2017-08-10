package utils;

import play.data.DynamicForm;

public class ParserUtil {

    public boolean hasKey(DynamicForm params, String key) {
        return params.get(key) != null;
    }

    public String getString(DynamicForm params, String key) {
        return params.get(key).trim();
    }

    public Integer getInt(DynamicForm params, String key) {
        String value = params.get(key);
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            LogUtil.printLogMessage("ParseUtil  getInt()", "error", e.getMessage());
        }
        return 0;
    }

    public Double getDouble(DynamicForm params, String key) {
        String value = params.get(key);
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            LogUtil.printLogMessage("ParseUtil  getDouble()", "error", e.getMessage());
        }
        return 0.0;
    }

    public Float getFloat(DynamicForm params, String key) {
        String value = params.get(key);
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            LogUtil.printLogMessage("ParseUtil  getFloat()", "error", e.getMessage());
        }
        return 0f;
    }

    public boolean getBoolean(DynamicForm params, String key) {
        String value = params.get(key);
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            LogUtil.printLogMessage("ParseUtil  getBoolean()", "error", e.getMessage());
        }
        return false;
    }

}
