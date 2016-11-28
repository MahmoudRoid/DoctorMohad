package ir.unicodes.doctor.database;

import com.google.common.base.Strings;
import com.orm.SugarRecord;

/**
 * Created by soheil syetem on 11/25/2016.
 */

public class db_details extends SugarRecord {
    public int sId,paretnId;
    public String title,content,imageUrl;
    public boolean favorite;

    public db_details(){}

    public db_details(int sId, int paretnId, String title, String content, String imageUrl, boolean favorite) {
        this.sId = sId;
        this.paretnId = paretnId;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.favorite = favorite;
    }

    public int getsId() {
        return sId;
    }

    public int getParetnId() {
        return paretnId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
