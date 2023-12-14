package builder;

import com.google.gson.Gson;

public class BodyJSonBuilder {

    public static <T> String getJSONContent (T dataObject) {
        return new Gson().toJson(dataObject);
    }
}
