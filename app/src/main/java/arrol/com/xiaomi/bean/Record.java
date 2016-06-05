package arrol.com.xiaomi.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by User on 2016/5/5.
 *
 */
public class Record extends BmobObject{
    private String remark;
    private String time;
    private Float money;
    private BmobUser user;//记录的创建者
    private String kind;//记录的种类，支出或者收入
    private String category;//支出里面的种类

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKind() {

        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public BmobUser getUser() {

        return user;
    }

    public void setUser(BmobUser user) {
        this.user = user;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
