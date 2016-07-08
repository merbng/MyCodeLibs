package com.app.merbng.mycodelibs.showtips;
import android.content.Context;
import android.content.SharedPreferences;
/**提示引导页
 * Created by wxp on 2016/3/31.
 */
public class StoreUtils {
    private Context context;

    public StoreUtils(Context context) {
        this.context = context;

    }

   public boolean hasShown(int id) {
        return context.getSharedPreferences("showtips", Context.MODE_PRIVATE).getBoolean("id" + id, false);
    }

  public   void storeShownId(int id) {
        SharedPreferences internal = context.getSharedPreferences("showtips", Context.MODE_PRIVATE);
        internal.edit().putBoolean("id" + id, true).apply();
    }
}
