package napodev.framework.bework.corebase.model.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import napodev.framework.bework.utils.Log;
import napodev.framework.bework.utils.helper.JSONHelper;

/**
 * Created by opannapo on 12/19/16.
 */
public abstract class BaseResponseModel implements Serializable {
    public boolean isSuccess;
    private Data data = null;
    private Pagination pagination = null;

    public boolean checkStatus(JSONObject object) {
        try {
            if (object.getBoolean("success")) {
                parseData(object);
                parsePagination(object);
                isSuccess = true;
                return true;
            } else {
                isSuccess = false;
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            isSuccess = false;
            return false;
        }
    }

    private void parseData(JSONObject object) {
        if (!object.isNull("data")) {
            this.data = new Data();
            try {
                if (object.get("data") instanceof JSONObject) {
                    data.setObject(object.getJSONObject("data"));
                } else if (object.get("data") instanceof JSONArray) {
                    data.setArray(object.getJSONArray("data"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void parsePagination(JSONObject object) {
        Log.d("IS pagination" + !object.isNull("pagination"));

        if (!object.isNull("pagination")) {

            this.pagination = new Pagination();
            try {
                JSONObject pagObj = object.getJSONObject("pagination");
                Log.d("pagObj " + pagObj);
                pagination.setPage_count(JSONHelper.getInt(pagObj, "page_count"));
                pagination.setCurrent_page(JSONHelper.getInt(pagObj, "current_page"));
                pagination.setHas_next_page(JSONHelper.getBool(pagObj, "has_next_page"));
                pagination.setHas_prev_page(JSONHelper.getBool(pagObj, "has_prev_page"));
                pagination.setCount(JSONHelper.getInt(pagObj, "count"));
                pagination.setLimit(JSONHelper.getInt(pagObj, "limit"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    public Data getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public abstract BaseResponseModel parse(JSONObject object);
}
