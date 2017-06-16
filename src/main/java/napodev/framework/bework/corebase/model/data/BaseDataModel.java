package napodev.framework.bework.corebase.model.data;

import org.json.JSONObject;

/**
 * Created by opannapo on 11/17/16.
 */
public abstract class BaseDataModel {
    public abstract BaseDataModel parse(JSONObject object);
}
