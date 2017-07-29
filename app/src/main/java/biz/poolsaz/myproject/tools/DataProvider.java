package biz.poolsaz.myproject.tools;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import biz.poolsaz.myproject.io.InputFileReader;

public class DataProvider {

    public static List<Person> request(int lastId, int count) {
        // TODO
        ArrayList<Person> data = new ArrayList<>();
        int start = lastId + 1;
        int last = start + count;
        InputFileReader inputFileReader = new InputFileReader();

        String json =  inputFileReader.readURL("http://192.168.1.7:8080/sp/api/SP");

        for (; start < last; start++) {
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    int SPid = jsonObject.getInt("SPid");
                    int Cid = jsonObject.getInt("Cid");
                    String name = jsonObject.getString("name");
                    String profileImg = jsonObject.getString("profileImg");
                    String pictuers = jsonObject.getString("pictuers");
                    String startWorkTime = jsonObject.getString("startWorkTime");
                    String endWorkTime = jsonObject.getString("endWorkTime");
                    String discreption = jsonObject.getString("discreption");
                    String phoneNumber = jsonObject.getString("phoneNumber");
                    int vote = jsonObject.getInt("vote");
                    int busy = jsonObject.getInt("busy");
                    int status = jsonObject.getInt("status");
                    data.add(new Person(SPid,Cid,name,profileImg,pictuers,startWorkTime,endWorkTime,discreption,phoneNumber,vote,busy,status));
                }catch (Exception e){

                }
        }
        return data;
    }
}