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

    public static Object prepareJSONObjectToStatus(Object jsonObject) throws Exception {

        if(jsonObject instanceof JSONObject) {
            //System.out.println("Entrei,JsonUtils: " + jsonObject);
            JSONObject joo = new JSONObject();
            JSONObject jo=((JSONObject) jsonObject);
            JSONArray names = jo.names();
            JSONObject entitiesObj = new JSONObject();
            JSONArray indicesArray = new JSONArray();
            String[] nArray = jsonArrayToStringArray(names);

//            System.out.println("JSONObject: "+jo);

            for (int i = 0; i < nArray.length; i++) {
                    if (nArray[i].matches("^start") || nArray[i].matches("^end")) {
                        indicesArray.put(jo.get(names.get(i).toString()));
                        //System.out.println("JSONObject indices: " + indicesArray);
                        if (indicesArray.length() > 1)
                            joo.put("indices", indicesArray);
                    } else {
                        if (nArray[i].matches(".*-$")) {
                            System.out.println("nArray: " + nArray[i]);
                            String key = nArray[i].replace("-", "");
                            System.out.println("Entitie cenas: " + (jo.get((String) names.get(i))));
                            entitiesObj.put(key, prepareJSONObjectToStatus(jo.get(names.get(i).toString())));
                        } else {
                            joo.put(nArray[i], prepareJSONObjectToStatus(jo.get(names.get(i).toString())));
                        }
                    }
            }

            if(entitiesObj.length()>0) {
                joo.put("entities", prepareJSONObjectToStatus(entitiesObj));
//                System.out.println("JSONObject com Entities OBj: " + joo.get("entities"));
            }
            return joo;
        }

        if(jsonObject instanceof JSONArray){
            JSONArray ja=(JSONArray)jsonObject;
            JSONArray jan=new JSONArray();
            for(int i=0;i<ja.length();i++){
                System.out.println("Entities Array: " +ja.get(i));
                jan.put(prepareJSONObjectToStatus(ja.get(i)));
            }
            return jan;
        }
        return jsonObject;
    }

    private static String[] jsonArrayToStringArray(JSONArray jsonArray) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < jsonArray.length(); i++) {
            String key = jsonArray.opt(i).toString();
            if (key.matches(".*[A-Z].*")) {
                if(!key.contains("URL")) // match a comecar com URL
                    key = key.replaceAll("([A-Z])", "_$1");
                if (key.matches(".*Entities.*")) {
                    if (key.equals("URLEntities")) key = "url_Entities";
                    if (key.contains("media")) key = key.replaceFirst("_Entities", "-");
                    key = key.replaceFirst("_Entities", "s-");
                }
                list.add(key.toLowerCase());
            }else{
                list.add(key);
            }
        }

        String[] lString = new String[list.size()];
        list.toArray(lString);
        return lString;
    }







}