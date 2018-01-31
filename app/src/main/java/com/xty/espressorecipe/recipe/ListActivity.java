package com.xty.espressorecipe.recipe;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xty.espressorecipe.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        ButterKnife.bind(this);
        showList();
    }

    private void showList(){
        List<String> datas = mockData();
        ListViewAdapter adatper = new ListViewAdapter(datas,this);
        mListView.setAdapter(adatper);
    }

    private List<String> mockData(){
        List<String> datas = new ArrayList<>();
        datas.add("hello");
        datas.add("world");
        datas.add("shit");
        return datas;
    }


    private static class ListViewAdapter extends BaseAdapter{

        private List<String> datas;
        private Context context;

        public ListViewAdapter(List<String> datas, Context context) {
            this.datas = datas;
            this.context = context;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv1, tv2;
            if (convertView == null){
                convertView = LayoutInflater.from(context)
                        .inflate(R.layout.list_item_layout, parent,false);
            }
            tv1 = convertView.findViewById(R.id.tv1);
            tv2 = convertView.findViewById(R.id.tv2);

            tv1.setText(datas.get(position));
            tv2.setText("发财");
            return convertView;
        }
    }
}
