package com.example.ZhiKe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.ZhiKe.R;
import com.example.ZhiKe.message.L;
import com.example.ZhiKe.utils.HttpUtil;
import com.example.ZhiKe.utils.JSONUtil;
import com.example.ZhiKe.utils.LineChartManager;
import com.example.ZhiKe.utils.PieChartUtil;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ClassroomStatusActivity extends BaseActivity {

    private TextView status_test;
    private String roomPosition;
    private String roomName;
    private String mUrl="http://27090p86e3.qicp.vip:27797/ZhiKe/checkPeople.do";//"http://10.16.149.3:9527/ZhiKe/checkPeople.do";
    private String mUrl2="http://27090p86e3.qicp.vip:27797/ZhiKe/predict.do";//"http://10.16.149.3:9527/ZhiKe/predict.do";//本地10.16.149.3
    private int sum;
    private PieChart pieChart;
    private LineChart lineChart;
    private HashMap dataMap;


Handler myHandler = new Handler(){
    public void handleMessage(Message msg){
        switch (msg.what){
            case 1:
                String buildingName="";
                if(roomPosition.equals("1")) buildingName="逸夫楼";
                else if(roomPosition.equals("2")) buildingName="教学楼";
                else if(roomPosition.equals("3")) buildingName="机电楼";
                else if(roomPosition.equals("4")) buildingName="经管楼";
                status_test.setText(buildingName+roomName+"教室使用情况");
                createChart(msg.arg1);
                break;
            case 2:
                float []rates= JSONUtil.getPredictRate((String)msg.obj);
                createLineChart(rates);
                break;
        }

        super.handleMessage(msg);
    }
};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_status);

        System.out.println("aaaaa");
        initView();

}
    public void createLineChart(float [] rates){
        lineChart=fd(R.id.predictChart);
        showAlone(rates);

    }
    public void showAlone(float [] rates){
        LineChartManager lineChartManager=new LineChartManager(lineChart);
        lineChartManager.setYAxis(100,0,10);
        lineChartManager.setDescription("预测曲线");
        float temp;

        List<Float> xAxisValues=new ArrayList<>();
        List<Float> yAxisValues=new ArrayList<>();
        xAxisValues.add(0.0f);
        xAxisValues.add(1.0f);
        xAxisValues.add(2.0f);
        xAxisValues.add(3.0f);
        xAxisValues.add(4.0f);
        xAxisValues.add(5.0f);
        xAxisValues.add(6.0f);
        yAxisValues.add(0.0f);
        for(int i=0;i<rates.length;i++){
            temp=100*rates[i];
            yAxisValues.add(temp);
        }
//        yAxisValues.add(50f);
//        yAxisValues.add(40f);
//        yAxisValues.add(50f);
//        yAxisValues.add(80f);
//        yAxisValues.add(70f);
//        yAxisValues.add(60f);
        lineChartManager.showLineChart(xAxisValues,yAxisValues,"",Color.parseColor("#da6268"));

    }

    /**
     * 绘制饼图
     * @param sum
     */
    public void createChart(int sum){
    dataMap=new HashMap();
    System.out.println(sum);
    int leave=100-sum;
    String Leave=String.valueOf(leave);
    String total=String.valueOf(sum);
    dataMap.put("已占用",total);
    dataMap.put("未占用",Leave);

    pieChart=fd(R.id.pieChart);
    PieChartUtil.getPitChart().setPieChart(pieChart,dataMap,"座位情况",true);

    //点击事件
    pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
        @Override
        public void onValueSelected(Entry e, Highlight h) {
            PieEntry pieEntry=(PieEntry)e;
            pieChart.setCenterText(pieEntry.getLabel());
        }

        @Override
        public void onNothingSelected() {

        }
    });

}

    public void checkPeople(String Url,String position,String classroomname,String recordtime){
        System.out.println(position+" "+classroomname+" "+recordtime);
        HttpUtil.checkPeopleWithOkhttp(Url, position, classroomname, recordtime, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                L.e("onFailure："+e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String responseData = response.body().string();
                sum=Integer.parseInt(responseData);
                System.out.println("sum="+sum);
                Message msg =new Message();

               // msg.what=sum;
                msg.arg1=sum;
                msg.what=1;
                myHandler.sendMessage(msg);
                //System.out.println(sum);



            }
        });
    }

    private void initView() {
        initNavBar(true,"知课",true);
        status_test=fd(R.id.statusTitle);
        //status_test.setBackgroundColor(0x66000000);
        Intent intent=getIntent();

        roomPosition=intent.getStringExtra("position");
        roomName=intent.getStringExtra("classroomname");

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String recordTime=dateFormat.format(date);
        checkPeople(mUrl,roomPosition,roomName,recordTime);
        Date date2 = new Date();
        String dayOfWeek=getWeekOfDate(date2);
        predictRoom(mUrl2,roomPosition,roomName,dayOfWeek);
        System.out.println("position:"+roomPosition+"roomName:"+roomName+"day"+dayOfWeek);


       // System.out.println(dateFormat.format(date));
        }
        public void predictRoom(String url,String position,String classroomname,String dayofweek){
        System.out.println("predict");
            HttpUtil.predictPeopleWithOkhttp(url, position, classroomname, dayofweek, new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    L.e("onFailure："+e.getMessage());
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    final String responseData = response.body().string();
                    System.out.println("response"+responseData);
                    String json=responseData;
                    Message msg =new Message();
                    msg.what=2;
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
