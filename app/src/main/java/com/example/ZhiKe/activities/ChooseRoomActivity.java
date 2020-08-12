package com.example.ZhiKe.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.ZhiKe.R;
import com.example.ZhiKe.adapters.RoomListAdapter;
import com.example.ZhiKe.message.L;
import com.example.ZhiKe.models.RoomItems;
import com.example.ZhiKe.utils.HttpUtil;
import com.example.ZhiKe.utils.JSONUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.PropertyResourceBundle;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChooseRoomActivity extends BaseActivity {
    private RecyclerView roomList;
    private String position;
    private RoomListAdapter roomListAdapter;
    private TextView mtx;
    private String mUrl="http://27090p86e3.qicp.vip:27797/ZhiKe/ClassroomAvailable.do";//"http://10.16.149.3:9527/ZhiKe/ClassroomAvailable.do";//10.16.149.3  服务器用:http://106.54.216.80:9527/ZhiKe/login.do


    Handler myHandler = new Handler(){
        public void handleMessage(Message msg){
            List<RoomItems> rooms;
            rooms= JSONUtil.getAvailableRoom((String)msg.obj);
            initView(rooms,position);
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_room);
        Intent intent=getIntent();

        String mPosition=intent.getStringExtra("position");
        String mFreeTime=intent.getStringExtra("freeTime");//接收到position、freeTime


        mtx=findViewById(R.id.choose_room_title);
        mtx.setText(mPosition+mFreeTime+"无课教室");
        //System.out.println(mPosition+mFreeTime+"无课教室");
        //对地点和时段赋值
        position="1";
        String freeTime="1";
        if(mPosition.equals("逸夫楼")){
            position="1";
        }
        else if(mPosition.equals("教学楼")){
            position="2";
        }
        else if(mPosition.equals("机电楼")){
            position="3";
        }
        else if(mPosition.equals("经管楼")){
            position="4";
        }
        if(mFreeTime.equals("第一节课")){
            freeTime="1";
        }
        else if (mFreeTime.equals("第二节课")) {
            freeTime="2";
        }
        else if (mFreeTime.equals("第三节课")) {
            freeTime="3";
        }else if (mFreeTime.equals("第四节课")) {
            freeTime="4";
        }
        else if (mFreeTime.equals("第五节课")) {
            freeTime="5";
        }
        else if (mFreeTime.equals("第六节课")) {
            freeTime="6";
        }
        Date date = new Date();
        String dayOfWeek=getWeekOfDate(date);
        checkAvailableRoom(mUrl,dayOfWeek,freeTime,position);

//        List<RoomItems> rooms;
//        rooms= JSONUtil.getAvailableRoom(json);
//        System.out.println("here"+getJsonText());
//        initView(rooms);
    }
    private void initView(List<RoomItems> rooms,String position) {
        initNavBar(true, "知课", true);
        roomList=fd(R.id.room_list);
        roomList.setLayoutManager(new LinearLayoutManager(this));
        roomList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        roomList.setNestedScrollingEnabled(false);
        roomListAdapter=new RoomListAdapter(this,roomList,rooms,position);
        roomList.setAdapter(roomListAdapter);
    }

    /**
     * 查看某日某时段的无课教室
     * @param mUrl
     * @param dayOfWeek
     * @param freeTime
     * @param position
     * @return
     */
    public void checkAvailableRoom(String mUrl, String dayOfWeek, String freeTime, final String position){
         //final String jsonText;
        HttpUtil.checkAvailableRoomWithOkHttp(mUrl, dayOfWeek, freeTime, position, new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                L.e("onFailure："+e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String responseData = response.body().string();
                //System.out.println(responseData);
                String json=responseData;
//                List<RoomItems> rooms;
//                rooms= JSONUtil.getAvailableRoom(json);
//                initView(rooms,position);
//
                //System.out.println(responseData);
                Message msg =new Message();
                msg.obj=json;
                myHandler.sendMessage(msg);
            }
        });



    }

    /**

     * 获取当前日期是星期几
     * @param date
     * @return 当前日期是星期几
     */

    public String getWeekOfDate(Date date) {

        String[] weekDays = { "7", "1", "2", "3", "4", "5", "6" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

}
