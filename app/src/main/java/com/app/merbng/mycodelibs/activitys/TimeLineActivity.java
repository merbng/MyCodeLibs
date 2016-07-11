package com.app.merbng.mycodelibs.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.adapters.TimelineAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 时间轴
 */
public class TimeLineActivity extends Activity {

    private ListView listView;
    List<String> data;
    private TimelineAdapter timelineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        listView = (ListView) this.findViewById(R.id.listview);
        listView.setDividerHeight(0);
        timelineAdapter = new TimelineAdapter(this, getData());
        listView.setAdapter(timelineAdapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", "想想这个" + i + "东西应该可以用");
            list.add(map);
        }
        return list;
    }

}
