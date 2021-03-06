package com.ntil.habiture;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.habiture.Group;
import com.habiture.GroupHistory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends BaseAdapter{
    private List<GroupHistory> groupHistories;
    protected LayoutInflater inflater;

    public HistoryAdapter(Context context, List<GroupHistory> groupHistories){
        inflater = LayoutInflater.from(context);
        this.groupHistories = new ArrayList<GroupHistory>(groupHistories);
        // 取得外部儲存裝置路徑

        try {
            String path = Environment.getExternalStorageDirectory().toString ();
            // 開啟檔案
            File file = new File( path, "Image.png");
            // 開啟檔案串流
            FileOutputStream out = new FileOutputStream(file );
            // 將 Bitmap壓縮成指定格式的圖片並寫入檔案串流
            this.groupHistories.get(0).getImage().compress ( Bitmap. CompressFormat.JPEG , 90 , out);
            // 刷新並關閉檔案串流
            out.flush ();
            out.close ();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() { return groupHistories.size(); }

    @Override
    public Object getItem(int position)  {
        return groupHistories.get(position);
    }

    @Override
    public long getItemId(int position)  {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.singleitem_history, parent, false);
            holder = new ViewHolder();
            holder.ivPicture = (ImageView) convertView.findViewById(R.id.ivPicture);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        GroupHistory groupHistory = (GroupHistory) getItem(position);
        // 設定照片圖案
        holder.ivPicture.setImageBitmap(groupHistory.getImage());
        // 設定日期
        holder.tvDate.setText(groupHistory.getDate());

        return convertView;
    }

    private class ViewHolder {
        ImageView ivPicture;
        TextView tvDate;
    }
}
