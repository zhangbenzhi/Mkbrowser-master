package cn.mkblog.www.mkbrowser;

import android.support.design.widget.BottomSheetBehavior;

/**
 * @author 张本志
 * @date 2019/1/19 下午2:05
 */
public class Utils {

    public static void showBottomSheet(BottomSheetBehavior behavior) {
        if (behavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }
}
