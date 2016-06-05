package arrol.com.xiaomi.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by User on 2016/5/9.
 *
 */
public class MonthTotalOut extends BmobObject{
    String time;
    float foodMoney;
    float clothMoney;
    float tripMoney;
    float rentMoney;
    float otherMoney;
    float totalMoney;
    BmobUser user;
    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BmobUser getUser() {
        return user;
    }

    public void setUser(BmobUser user) {
        this.user = user;
    }

    public float getOtherMoney() {

        return otherMoney;
    }

    public void setOtherMoney(float otherMoney) {
        this.otherMoney = otherMoney;
    }

    public float getRentMoney() {

        return rentMoney;
    }

    public void setRentMoney(float rentMoney) {
        this.rentMoney = rentMoney;
    }

    public float getTripMoney() {

        return tripMoney;
    }

    public void setTripMoney(float tripMoney) {
        this.tripMoney = tripMoney;
    }

    public float getClothMoney() {

        return clothMoney;
    }

    public void setClothMoney(float clothMoney) {
        this.clothMoney = clothMoney;
    }

    public float getFoodMoney() {

        return foodMoney;
    }

    public void setFoodMoney(float foodMoney) {
        this.foodMoney = foodMoney;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
