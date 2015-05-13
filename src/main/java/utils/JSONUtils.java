package utils;

import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Dirirrara
 * Created by carlaregadas on 11-05-2015.
 */
public class JSONUtils {

    public static Object prepareJSONObjectToStatus(Object jsonObject) throws Exception{

        if(jsonObject instanceof JSONObject) {
            System.out.println("Entrei,JsonUtils: " + jsonObject);
            JSONObject joo = new JSONObject();
            JSONObject jo=((JSONObject) jsonObject);
            JSONArray names = jo.names();
            String[] nArray = jsonArrayToStringArray(names);
            for (int i = 0; i < nArray.length; i++) {
                joo.put(nArray[i], prepareJSONObjectToStatus(jo.get((String) names.get(i))));
            }
            return joo;
        }
        if(jsonObject instanceof JSONArray){
            JSONArray ja=(JSONArray)jsonObject;
            JSONArray jan=new JSONArray();
            for(int i=0;i<ja.length();i++){
                jan.put(prepareJSONObjectToStatus(ja.get(i)));
            }
            return jan;
        }
        return jsonObject;
    }

    private static String[] jsonArrayToStringArray(JSONArray jsonArray) {
        List<String> list = new ArrayList<String>();
        for(int i=0;i<jsonArray.length();i++) {
            String key=(String)jsonArray.opt(i);
            if(key.matches(".*[A-Z].*")){
                if(key.equals("URLEntities")) {
                    list.add("url_entities");
                }else{
                    key = key.replaceAll("([A-Z])", "_$1");
                    list.add(key.toLowerCase());
                }
            }else {
                list.add(key);
            }
        }
        String[] lString = new String[list.size()];
        list.toArray(lString);
        return lString;
    }
}