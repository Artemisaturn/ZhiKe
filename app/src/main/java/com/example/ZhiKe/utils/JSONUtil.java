package com.example.ZhiKe.utils;

import com.example.ZhiKe.R;
import com.example.ZhiKe.models.RoomItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtil {

    public static float[] getPredictRate(String json){
        float[] rates={0,0,0,0,0,0};
        try {
            JSONArray jsonArray=new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String rate=jsonObject.getString("rate");
                float realRate=Float.parseFloat(rate);
                rates[i]=realRate;
                System.out.println("rate:"+rates[i]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rates;

    }

    /**
     * 解析json获取今日课表
     * @param json
     * @return
     */
    public static  List<RoomItems> getTodayCourse(String json){
        List<RoomItems> courses=new ArrayList<>();
        if(json.equals("[]")){
            courses.add(new RoomItems("哈哈！今日无课~","",R.mipmap.list));
        }
        else{
            try {
                JSONArray jsonArray= new JSONArray(json);
                for(int i=0;i<jsonArray.length();i++){

                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    String position=jsonObject.getString("position");
                    if(position.equals("1")){
                        position="逸夫楼";
                    }else if(position.equals("2")){
                        position="教学楼";
                    }else if(position.equals("3")){
                        position="机电楼";
                    }else if(position.equals("4")){
                        position="经管楼";
                    }
                    String roomName=jsonObject.getString("classroomname");
                    String classNode = jsonObject.getString("classnode");
                    if(classNode.equals("1")){
                        classNode="第一节 ";
                    }else if(classNode.equals("2")){
                        classNode="第二节 ";
                    }else if(classNode.equals("3")){
                        classNode="第三节 ";
                    }else if(classNode.equals("4")){
                        classNode="第四节 ";
                    }
                    else if(classNode.equals("5")){
                        classNode="第五节 ";
                    }
                    else if(classNode.equals("6")){
                        classNode="第六节 ";
                    }
                    String className=jsonObject.getString("classname");
                    courses.add(new RoomItems(classNode+className,position+roomName,R.mipmap.list));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return courses;
    }
    /**
     * 解析某时段的无课教室json
     * @param json
     * @return
     */
    public static List<RoomItems> getAvailableRoom(String json){
        List<RoomItems> rooms=new ArrayList<RoomItems>();
        //rooms.add(new RoomItems("ss","ss",R.mipmap.building));
        try {
            JSONArray jsonArray= new JSONArray(json);


            for(int i=0;i<jsonArray.length();i++)
            {
                //String a="44";
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String position=jsonObject.getString("position");
               // System.out.println("position1:"+position);
               // position="逸夫楼";
                if(position.equals("1")){
                    position="逸夫楼";
                }else if(position.equals("2")){
                    position="教学楼";
                }else if(position.equals("3")){
                    position="机电楼";
                }else if(position.equals("4")){
                    position="经管楼";
                }
                String roomName=jsonObject.getString("classroomname");
                //System.out.println("position2:"+position);
                if(position.equals("逸夫楼")){
                    rooms.add(new RoomItems(position,roomName,R.mipmap.building));
                }
                else if(position.equals("教学楼")){
                    rooms.add(new RoomItems(position,roomName,R.mipmap.room2));
                }
                else if(position.equals("机电楼")){
                    rooms.add(new RoomItems(position,roomName,R.mipmap.room3));
                }
                else if(position.equals("经管楼")){
                    rooms.add(new RoomItems(position,roomName,R.mipmap.room4));
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rooms;

    }
}
