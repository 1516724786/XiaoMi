package arrol.com.xiaomi.customView;

/**
 * Created by User on 2016/5/4.
 */
public interface PieViewItemListener {
    /**
     *
     * @param position
     * item位置
     * @param itemColor
     * item颜色
     * @param value
     * item值
     * @param percent
     * item比重
     * @param rotateTime
     */
    void PieViewItemSelectListener(int position, String itemColor,
                             double value, float percent, float rotateTime);
}
