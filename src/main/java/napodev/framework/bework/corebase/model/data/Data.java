package napodev.framework.bework.corebase.model.data;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by opannapo on 1/1/17.
 */
public class Data {
    private JSONObject object;
    private JSONArray array;

    public JSONObject asObject() {
        return object;
    }

    public void setObject(Object data) {
        this.object = (JSONObject) data;
    }

    public JSONArray asArray() {
        return array;
    }

    public void setArray(JSONArray array) {
        this.array = array;
    }
}
