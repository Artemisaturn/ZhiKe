package com.example.ZhiKe.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.ZhiKe.R;
import com.example.ZhiKe.adapters.CourseListAdapter;
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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ListCourseActivity extends BaseActivity {

    private RecyclerView courseList;
    private String mUrl="http://27090p86e3.qicp.vip:27797/ZhiKe/listClass.do";//"http://10.16.149.3:9527/ZhiKe/listClass.do";
    private CourseListAdapter courseListAdapter;
    private TextView mtx;

    Handler myHandler = new Handler(){
        public void handleMessage(Message msg){
            List<RoomItems> courses;
            courses= JSONUtil.getTodayCourse((String)msg.obj);
            initView(courses);
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course);
        mtx=findViewById(R.id.course_list_title);
        Date date = new Date();
        String dayOfWeek=getWeekOfDate(date);
        checkCourseToday(mUrl,dayOfWeek);
        //initView();
    }

    private void initView(List<RoomItems> courses) {
        initNavBar(true, "知课", true);
        courseList=fd(R.id.course_list);
        courseList.setLayoutManager(new LinearLayoutManager(this));
        courseList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        courseList.setNestedScrollingEnabled(false);
        courseListAdapter=new CourseListAdapter(this,courseList,courses);
        courseList.setAdapter(courseListAdapter);
    }
    public void checkCourseToday(String mUrl,String dayOfWeek){
        HttpUtil.checkCourseToday(mUrl, dayOfWeek, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                L.e("onFailure："+e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String responseData = response.body().string();
                String json=responseData;
                System.out.println(json);
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
