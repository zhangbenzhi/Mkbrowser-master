package cn.mkblog.www.mkbrowser.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * 浏览历史Util
 */
public class BrowseUtil {

    public static boolean browseOrNot(Context context, String url) {
        boolean collect = false;
        Set<String> collectSet = getBrowseSet(context);
        if (isBrowsed(context, url)) {
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

    public static void browse(Context context, String url) {
        Set<String> collectSet = getBrowseSet(context);
        if (!isBrowsed(context, url)) {
            collectSet.add(url);
            save(context, collectSet);
        }
    }

    public static void unBrowse(Context context, String url) {
        Set<String> collectSet = getBrowseSet(context);
        if (isBrowsed(context, url)) {
            collectSet.remove(url);
            save(context, collectSet);
        }
    }

    public static boolean isBrowsed(Context context, String url) {
        boolean isCollected = false;
        Set<String> collectSet = getBrowseSet(context);
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

    public static Set<String> getBrowseSet(Context context) {
        SharedPreferences sp = context.getSharedPreferences("webview", Context.MODE_PRIVATE);
        return sp.getStringSet("browse", new HashSet<String>());
    }

    private static void save(Context context, Set<String> stringSet) {
        SharedPreferences sp = context.getSharedPreferences("webview", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putStringSet("browse", stringSet);
        edit.apply();
    }

}
