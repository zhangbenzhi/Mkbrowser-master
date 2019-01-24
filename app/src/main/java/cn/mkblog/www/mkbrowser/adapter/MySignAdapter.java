package cn.mkblog.www.mkbrowser.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import cn.mkblog.www.mkbrowser.R;
import cn.mkblog.www.mkbrowser.utils.CollectionUtil;

/**
 * @author 张本志
 * @date 2019/1/22 下午1:29
 */
public class MySignAdapter extends RecyclerView.Adapter<MySignAdapter.MySignViewHolder> {


    private Activity activity;
    private Set<String> signs = new HashSet<>();
    private OnclickItemListener onclickItemListener;

    public MySignAdapter(Activity activity, OnclickItemListener onclickItemListener) {
        this.activity = activity;
        this.onclickItemListener = onclickItemListener;
        this.signs = CollectionUtil.getCollectSet(activity);
    }

    public void setData() {
        signs = CollectionUtil.getCollectSet(activity);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MySignViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MySignViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_sign, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MySignViewHolder mySignViewHolder, final int i) {
        mySignViewHolder.tv.setText((String) signs.toArray()[i]);
        mySignViewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String str : signs) {
                    if (str.equals(signs.toArray()[i])) {
                        signs.remove(str);
                        CollectionUtil.unCollect(activity,str);
                        notifyDataSetChanged();
                        break;
                    }
                }
            }
        });
        mySignViewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickItemListener.onClick(signs.toArray()[i] + "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return signs.size();
    }

    class MySignViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        TextView remove;

        public MySignViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_sign);
            remove = itemView.findViewById(R.id.tv_remove);
        }
    }

    public interface OnclickItemListener {
        void onClick(String url);
    }


}
