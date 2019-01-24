package cn.mkblog.www.mkbrowser.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * 收藏Util
 */
public class CollectionUtil {

    public static boolean collectOrNot(Context context, String url) {
        boolean collect = false;
        Set<String> collectSet = getCollectSet(context);
        if (isCollected(context, url)) {
            for (String collectUrl : collectSet) {
                if (url.equals(collectUrl)) {
                    collectSet.remove(collectUrl);
                    collect = false;
                    break;
                }
            }
        } else {
            collectSet.add(url);
            collect = true;
        }
        save(context, collectSet);
        return collect;
    }

    public static void collect(Context context, String url) {
        Set<String> collectSet = getCollectSet(context);
        if (!isCollected(context, url)) {
            collectSet.add(url);
            save(context, collectSet);
        }
    }
    public static void unCollect(Context context, String url) {
        Set<String> collectSet = getCollectSet(context);
        if (isCollected(context, url)) {
            collectSet.remove(url);
            save(context, collectSet);
        }
    }

    public static boolean isCollected(Context context, String url) {
        boolean isCollected = false;
        Set<String> collectSet = getCollectSet(context);
        if (collectSet != null) {
            for (String collectUrl : collectSet) {
                if (url.equals(collectUrl)) {
                    isCollected = true;
                    break;
                }
            }
        }
        return isCollected;
    }

    public static Set<String> getCollectSet(Context context) {
        SharedPreferences sp = context.getSharedPreferences("webview", Context.MODE_PRIVATE);
        return sp.getStringSet("collect", new HashSet<String>());
    }

    private static void save(Context context, Set<String> stringSet) {
        SharedPreferences sp = context.getSharedPreferences("webview", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putStringSet("collect", stringSet);
        edit.apply();
    }

}
