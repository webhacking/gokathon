package net.saltfactory.demo.gcm.ListView;

import android.graphics.drawable.Drawable;

public class ListViewBtnItem {
    private Drawable iconDrawable ;
    private int hour;
    private int min;
    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public void setMin(int min){this.min = min;}
    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTimeText() {
        if (hour < 10) {
            if(min < 10){
                return "0" + Integer.toString(hour) + ":0" + Integer.toString(min);
            }else{
                return "0" + Integer.toString(hour) + ":" + Integer.toString(min);
            }
        }else{
            if(min < 10){
                return Integer.toString(hour) + ":0" + Integer.toString(min);
            }else{
                return Integer.toString(hour) + ":" + Integer.toString(min);
            }
        }
    }
}