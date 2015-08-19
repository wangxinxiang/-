package com.example.complain1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.complain1.dao.SearchHistoryDB;

import java.util.ArrayList;

/**
 * Created by wang on 2015/4/6.
 */
public class SearchActivity extends Activity{
    private ListView history;
    private MultiAutoCompleteTextView searchText;
    private ImageButton back,search_sure;
    private LinearLayout clearHistory;
    private Context context;
    private SearchHistoryDB db;
    private ArrayAdapter<String> historyAdapter;
    private ArrayAdapter<String> autoCompleteAdapter;
    private ArrayList<String> historyList = new ArrayList<String>();
    private String from;        //当from为tab3时点击商家列表直接获得商家名并跳转至tab3
//    private SharedPreferences preferences;
//    private  SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
//        preferences = this.getSharedPreferences("search_history",Activity.MODE_PRIVATE);
//        editor = preferences.edit();
        init();

    }

    private void init(){
        context = this;
        back = (ImageButton)this.findViewById(R.id.search_back);
        clearHistory = (LinearLayout)this.findViewById(R.id.search_clearHistory);
        search_sure = (ImageButton)this.findViewById(R.id.search_sure);
        db = new SearchHistoryDB(context);
        from = getIntent().getStringExtra("from");
        initListener();
        if(!from.equals("tab3")){
            initListView();
        }
        initMultiAutoComplete();

    }
//设置历史 查询记录列表
    private void initListView(){
        history = (ListView)this.findViewById(R.id.search_history);
        historyList = db.getHistory(historyList);
        historyAdapter = new ArrayAdapter<String>(context,R.layout.search_history_listview,historyList);
        history.setAdapter(historyAdapter);
        history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemInfo = ((TextView)view).getText().toString();
               intentToResult(itemInfo,from);
            }
        });
    }

    private void initListener(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        clearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.clearHistory();
                historyList = db.getHistory(historyList);
                historyAdapter.notifyDataSetChanged();
            }
        });
        search_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = searchText.getText().toString();
                if(!key.equals("")){
                    db.insertHistory(key);
//                    editor.putString("history",key);
//                    editor.commit();
                }
                intentToResult(key,from);
            }
        });
    }
//设置搜索输入栏
    private void initMultiAutoComplete(){
        searchText = (MultiAutoCompleteTextView)this.findViewById(R.id.search_edit);
        searchText.setThreshold(1);
        searchText.setAdapter(historyAdapter);      //应设置autoCompleteAdapter自动
        //用户必须提供一个MultiAutoCompleteTextView.Tokenizer用来区分不同的子串。
        searchText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private void intentToResult(String key,String from){
        Intent intent = new Intent(SearchActivity.this,SearchResultActivity.class);
        intent.putExtra("key",key);
        Log.d("from","SearchActivity_from:" + from);
        intent.putExtra("from",from);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        if(historyAdapter != null){
            historyList = db.getHistory(historyList);
            historyAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }
}
