package arrol.com.xiaomi.biz.addRecordBiz;

import android.content.Context;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import arrol.com.xiaomi.bean.MonthTotalIn;
import arrol.com.xiaomi.bean.MonthTotalOut;
import arrol.com.xiaomi.bean.Record;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by User on 2016/5/9.
 *
 */
public class AddRecordBiz implements IAddRecordBiz{
    @Override
    public void postData(final Context context, final Record record, final IPostDataListener listener) {
        new Thread(){
            @Override
            public void run(){
                record.save(context, new SaveListener() {
                    @Override
                    public void onSuccess() {
                       Judge(context,record,listener);
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        listener.postFailed(s);
                    }
                });
            }
        }.start();
    }

    private void Judge(final Context context, final Record record, final IPostDataListener listener){
        Calendar calendar=Calendar.getInstance();
        final String str=new String(calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月");
        new Thread(){
            @Override
            public void run(){
                if (record.getKind().equals("支出")){
                    BmobQuery<MonthTotalOut> query1=new BmobQuery<MonthTotalOut>();
                    query1.addWhereEqualTo("user", BmobUser.getCurrentUser(context,BmobUser.class));
                    BmobQuery<MonthTotalOut> query2=new BmobQuery<MonthTotalOut>();
                    query2.addWhereEqualTo("time", str);
                    List<BmobQuery<MonthTotalOut>> queries=new ArrayList<BmobQuery<MonthTotalOut>>();
                    queries.add(query1);
                    queries.add(query2);
                    BmobQuery<MonthTotalOut> query =new BmobQuery<MonthTotalOut>();
                    query.and(queries);
                    query.setLimit(1);
                    query.findObjects(context, new FindListener<MonthTotalOut>() {
                        @Override
                        public void onSuccess(List<MonthTotalOut> lists) {
                            if(lists.size() == 1){
                                for (MonthTotalOut list:lists){
                                    updateTotalOut(context,record,list,listener);
                                }
                            }
                            else {
                                postTotalOut(context, record, str, listener);
                            }
                        }

                        @Override
                        public void onError(int i, String s) {
                            if(i == 101){
                                postTotalOut(context, record, str, listener);
                            }
                            else{
                                listener.postFailed(s);
                            }
                        }
                    });
                }
                else{
                    BmobQuery<MonthTotalIn> query1=new BmobQuery<>();
                    query1.addWhereEqualTo("user", BmobUser.getCurrentUser(context,BmobUser.class));
                    BmobQuery<MonthTotalIn> query2=new BmobQuery<>();
                    query2.addWhereEqualTo("time", str);

                    List<BmobQuery<MonthTotalIn>> queries=new ArrayList<>();
                    queries.add(query1);
                    queries.add(query2);
                    BmobQuery<MonthTotalIn> query =new BmobQuery<MonthTotalIn>();
                    query.and(queries);
                    query.setLimit(1);
                    query.findObjects(context, new FindListener<MonthTotalIn>() {
                        @Override
                        public void onSuccess(List<MonthTotalIn> list) {
                            if(list.size() == 1){
                                for (MonthTotalIn totalIn:list){
                                    updateTotalIn(context,record,totalIn,listener);
                                }
                            }
                            else{
                                postTotalIn(context,record,listener,str);
                            }
                        }

                        @Override
                        public void onError(int i, String s) {
                            if(i == 101){
                                postTotalIn(context,record,listener,str);
                            }
                            else {
                                listener.postFailed(s);
                            }
                        }
                    });
                }
            }
        }.start();
    }

    private void postTotalOut(final Context context, final Record record,
                              String str, final IPostDataListener listener){
        MonthTotalOut monthTotalOut=new MonthTotalOut();
        switch (record.getCategory()){
            case "饮食":
                monthTotalOut.setFoodMoney(record.getMoney());
                monthTotalOut.setClothMoney(0);
                monthTotalOut.setRentMoney(0);
                monthTotalOut.setTripMoney(0);
                monthTotalOut.setOtherMoney(0);
                break;
            case "服装":
                monthTotalOut.setClothMoney(record.getMoney());
                monthTotalOut.setFoodMoney(0);
                monthTotalOut.setRentMoney(0);
                monthTotalOut.setTripMoney(0);
                monthTotalOut.setOtherMoney(0);
                break;
            case "房租":
                monthTotalOut.setRentMoney(record.getMoney());
                monthTotalOut.setClothMoney(0);
                monthTotalOut.setFoodMoney(0);
                monthTotalOut.setTripMoney(0);
                monthTotalOut.setOtherMoney(0);
                break;
            case "旅途":
                monthTotalOut.setTripMoney(record.getMoney());
                monthTotalOut.setRentMoney(0);
                monthTotalOut.setClothMoney(0);
                monthTotalOut.setFoodMoney(0);
                monthTotalOut.setOtherMoney(0);
                break;
            case "其它":
                monthTotalOut.setOtherMoney(record.getMoney());
                monthTotalOut.setTripMoney(0);
                monthTotalOut.setRentMoney(0);
                monthTotalOut.setClothMoney(0);
                monthTotalOut.setFoodMoney(0);
                break;
        }
        monthTotalOut.setTotalMoney(record.getMoney());
        monthTotalOut.setUser(BmobUser.getCurrentUser(context, BmobUser.class));
        monthTotalOut.setTime(str);
        monthTotalOut.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                listener.postSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                listener.postFailed(s);
            }
        });
    }
    private void updateTotalOut(final Context context, final Record record,
                                MonthTotalOut totalOut, final IPostDataListener listener){
        MonthTotalOut out=new MonthTotalOut();
        /**
         * 由于Bmob的原因让下面代码多了些！！
         */
        switch (record.getCategory()) {
            case "饮食":
                out.setValue("foodMoney",record.getMoney() + totalOut.getFoodMoney());
                out.setValue("clothMoney",totalOut.getClothMoney());
                out.setValue("rentMoney",totalOut.getRentMoney());
                out.setValue("tripMoney",totalOut.getTripMoney());
                out.setValue("otherMoney",totalOut.getOtherMoney());
                break;
            case "服装":
                out.setValue("clothMoney",record.getMoney()+totalOut.getClothMoney());
                out.setValue("foodMoney", totalOut.getFoodMoney());
                out.setValue("rentMoney",totalOut.getRentMoney());
                out.setValue("tripMoney",totalOut.getTripMoney());
                out.setValue("otherMoney",totalOut.getOtherMoney());
                break;
            case "房租":
                out.setValue("rentMoney",record.getMoney()+totalOut.getRentMoney());
                out.setValue("clothMoney",totalOut.getClothMoney());
                out.setValue("foodMoney", totalOut.getFoodMoney());
                out.setValue("tripMoney",totalOut.getTripMoney());
                out.setValue("otherMoney",totalOut.getOtherMoney());
                break;
            case "旅途":
                out.setValue("tripMoney",record.getMoney()+totalOut.getTripMoney());
                out.setValue("rentMoney",totalOut.getRentMoney());
                out.setValue("clothMoney",totalOut.getClothMoney());
                out.setValue("foodMoney", totalOut.getFoodMoney());
                out.setValue("otherMoney",totalOut.getOtherMoney());
                break;
            case "其它":
                out.setValue("otherMoney",record.getMoney()+totalOut.getOtherMoney());
                out.setValue("tripMoney",totalOut.getTripMoney());
                out.setValue("rentMoney",totalOut.getRentMoney());
                out.setValue("clothMoney",totalOut.getClothMoney());
                out.setValue("foodMoney", totalOut.getFoodMoney());
                break;
        }
        out.setTotalMoney((totalOut.getTotalMoney() + record.getMoney()));
        out.update(context, totalOut.getObjectId(), new UpdateListener() {
            @Override
            public void onSuccess() {
                listener.postSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                listener.postFailed(s);
            }
        });
    }



    private void postTotalIn(final Context context, final Record record,
                             final IPostDataListener listener,String str){
        MonthTotalIn totalIn = new MonthTotalIn();
        totalIn.setTime(str);
        totalIn.setTotalMoney(record.getMoney());
        totalIn.setUser(BmobUser.getCurrentUser(context, BmobUser.class));
        totalIn.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
             listener.postSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                listener.postFailed(s);
            }
        });

    }

    private void updateTotalIn(final Context context, final Record record,
                               MonthTotalIn in, final IPostDataListener listener){
        MonthTotalIn totalIn=new MonthTotalIn();
        totalIn.setValue("totalMoney",in.getTotalMoney()+record.getMoney());
        totalIn.update(context, in.getObjectId(), new UpdateListener() {
            @Override
            public void onSuccess() {
                listener.postSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                listener.postFailed(s);
            }
        });
    }
}
