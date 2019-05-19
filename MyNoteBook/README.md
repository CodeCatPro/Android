# Android学习：完成NoteBook的制作

### 1.CursorLoader和CursorAdapter实现异步加载数据

**概念：**

CursorLoader是Google封装的很好的专门用于数据库读取获取Cursor的Loader类， 在Google源代码中被大量使用， 比如联系人、短信等系统应用， 因为这些应用内部都包含了大量的读取数据库的操作。CursorLoader的使用极大的简化了开发者对于Cursor的管理， 从而更专注于应用的开发。

CursorAdapter这个类是继承于BaseAdapter的，它是一个虚类，它为Cursor和ListView连接提供了桥梁。CursorAdapter是继承了BaseAdapter后覆盖它的getView方法在getView方法中调用了newView和bindView方法 。

**CursorLoader关键代码**

**//AllNotesFragment**

```
package com.example.yangtianrui.notebook.fragment;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.example.yangtianrui.notebook.R;
import com.example.yangtianrui.notebook.activity.NoteDetailActivity;
import com.example.yangtianrui.notebook.adapter.ShowNoteAdapter;
import com.example.yangtianrui.notebook.db.NoteDAO;

/**
 * Created by yangtianrui on 16-5-21.
 * 显示所有Note,使用Loader实现异步加载
 */
public class AllNotesFragment extends Fragment implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    private NoteDAO mNoteDAO;
    private ListView mLvNotes;
    private CursorAdapter mAdapter;
    private Cursor mCursor;
    private final static int CONTEXT_UPDATE_ORDER = 0;
    private final static int CONTEXT_DELETE_ORDER = 1;
    private View root;

    public AllNotesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNoteDAO = new NoteDAO(getActivity());
        // 查询所有行
        mCursor = mNoteDAO.queryNote(null, null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater
            , ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_all_note, container, false);
        mLvNotes = (ListView) root.findViewById(R.id.id_lv_all_note);
        mAdapter = new ShowNoteAdapter(getActivity(), mCursor);
        getLoaderManager().initLoader(0, null, this);
        mLvNotes.setAdapter(mAdapter);
        mLvNotes.setOnItemClickListener(this);
        registerForContextMenu(mLvNotes);
        return root;
    }

    /**
     * 此时重启Loader机制更新数据
     */
    @Override
    public void onResume() {
        super.onResume();
        mCursor = mNoteDAO.queryNote(null, null);
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mCursor.close();
    }


    /**
     * 上下文菜单的回调函数
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //int position = mAdapter.getItem(info.position);
        int position = info.position; // list中的位置
        Cursor c = (Cursor) mAdapter.getItem(position); // CursorAdapter中getItem()返回特定的cursor对象
        int itemID = c.getInt(c.getColumnIndex("_id"));
        switch (item.getOrder()) {
            case CONTEXT_UPDATE_ORDER: // 更新操作
                //Toast.makeText(getActivity(),"UPDATE",Toast.LENGTH_SHORT).show();
                break;
            case CONTEXT_DELETE_ORDER: // 删除操作
                //Toast.makeText(getActivity(),"DELETE",Toast.LENGTH_SHORT).show();
                mNoteDAO.deleteNote("_id=?", new String[]{itemID + ""});
                getLoaderManager().restartLoader(0, null, this);
                break;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * 创建上下文菜单
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Enter your choice:");
        menu.add(0, v.getId(), CONTEXT_UPDATE_ORDER, "UPDATE");
        menu.add(0, v.getId(), CONTEXT_DELETE_ORDER, "DELETE");
    }

    // 跳转到详情页
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor c = (Cursor) mAdapter.getItem(position); // CursorAdapter中getItem()返回特定的cursor对象
        int itemID = c.getInt(c.getColumnIndex("_id"));
//        Log.v("LOG", "AllNoteFragment itemID: " + itemID);
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);
        intent.putExtra(NoteDetailActivity.SENDED_NOTE_ID, itemID);
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse("content://com.terry.NoteBook");

        return new CursorLoader(getActivity(), uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
```

**CursorAdapter关键代码**

**//ShowNoteAdapter**

```
package com.example.yangtianrui.notebook.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.yangtianrui.notebook.R;
import com.example.yangtianrui.notebook.util.TextFormatUtil;

/**
 * Created by yangtianrui on 16-5-22.
 */
public class ShowNoteAdapter extends CursorAdapter {

    private Context context;
    private Cursor cursor;

    public ShowNoteAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
        this.cursor = cursor;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater1 = LayoutInflater.from(context);
        View view = inflater1.inflate(R.layout.item_note, null, false);
        ViewHolder holder = new ViewHolder();
        holder.mTvTitle = (TextView) view.findViewById(R.id.id_tv_note_title);
        holder.mTvContent = (TextView) view.findViewById(R.id.id_tv_note_summary);
        holder.mTvCreateTime = (TextView) view.findViewById(R.id.id_tv_note_create_time);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        String title = cursor.getString(cursor.getColumnIndex("title"));
        holder.mTvTitle.setText(title);
        holder.mTvContent.setText(TextFormatUtil.getNoteSummary(cursor.getString(cursor.getColumnIndex("content"))));
        holder.mTvCreateTime.setText("creation:" + cursor.getString(cursor.getColumnIndex("create_time")));
    }

    final class ViewHolder {
        TextView mTvTitle;
        TextView mTvContent;
        TextView mTvCreateTime;
    }
}
```

### 2.Toolbar的使用

**核心代码**

**//toolbar_main.xml**

~~~
<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.Toolbar
    xmlns:android="http://schemas.android.com/apk/res/android"

    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:background="@color/colorPrimaryDark" />
~~~

**MainActivity中相关代码**

```
private void initView() {
    mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
    setSupportActionBar(mToolbar);
    mToolbar.setOnMenuItemClickListener(this);
    mDlLayout = (DrawerLayout) findViewById(R.id.id_dl_main_layout);
    mFlContent = (FrameLayout) findViewById(R.id.id_fl_main_content);
    mLvNavi = (ListView) findViewById(R.id.id_lv_navi);
    NaviListAdapter adapter = new NaviListAdapter(this, Arrays.asList(mLabels));
    mToggle = new ActionBarDrawerToggle(this, mDlLayout, mToolbar, R.string.app_name, R.string.app_name);
    mToggle.syncState();
    mDlLayout.setDrawerListener(mToggle);
    mLvNavi.setAdapter(adapter);
    // 为每个ListItem添加点击事件,即启动相应的Fragment
    mLvNavi.setOnItemClickListener(this);
}
```

### 3.DrawerLayout和Fragment实现侧滑菜单

**核心代码**

**(1)Fragment**

**//AllNotesFragment**

~~~
package com.example.yangtianrui.notebook.fragment;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.example.yangtianrui.notebook.R;
import com.example.yangtianrui.notebook.activity.NoteDetailActivity;
import com.example.yangtianrui.notebook.adapter.ShowNoteAdapter;
import com.example.yangtianrui.notebook.db.NoteDAO;

/**
 * Created by yangtianrui on 16-5-21.
 * 显示所有Note,使用Loader实现异步加载
 */
public class AllNotesFragment extends Fragment implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    private NoteDAO mNoteDAO;
    private ListView mLvNotes;
    private CursorAdapter mAdapter;
    private Cursor mCursor;
    private final static int CONTEXT_UPDATE_ORDER = 0;
    private final static int CONTEXT_DELETE_ORDER = 1;
    private View root;

    public AllNotesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNoteDAO = new NoteDAO(getActivity());
        // 查询所有行
        mCursor = mNoteDAO.queryNote(null, null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater
            , ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_all_note, container, false);
        mLvNotes = (ListView) root.findViewById(R.id.id_lv_all_note);
        mAdapter = new ShowNoteAdapter(getActivity(), mCursor);
        getLoaderManager().initLoader(0, null, this);
        mLvNotes.setAdapter(mAdapter);
        mLvNotes.setOnItemClickListener(this);
        registerForContextMenu(mLvNotes);
        return root;
    }

    /**
     * 此时重启Loader机制更新数据
     */
    @Override
    public void onResume() {
        super.onResume();
        mCursor = mNoteDAO.queryNote(null, null);
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mCursor.close();
    }


    /**
     * 上下文菜单的回调函数
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //int position = mAdapter.getItem(info.position);
        int position = info.position; // list中的位置
        Cursor c = (Cursor) mAdapter.getItem(position); // CursorAdapter中getItem()返回特定的cursor对象
        int itemID = c.getInt(c.getColumnIndex("_id"));
        switch (item.getOrder()) {
            case CONTEXT_UPDATE_ORDER: // 更新操作
                //Toast.makeText(getActivity(),"UPDATE",Toast.LENGTH_SHORT).show();
                break;
            case CONTEXT_DELETE_ORDER: // 删除操作
                //Toast.makeText(getActivity(),"DELETE",Toast.LENGTH_SHORT).show();
                mNoteDAO.deleteNote("_id=?", new String[]{itemID + ""});
                getLoaderManager().restartLoader(0, null, this);
                break;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * 创建上下文菜单
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Enter your choice:");
        menu.add(0, v.getId(), CONTEXT_UPDATE_ORDER, "UPDATE");
        menu.add(0, v.getId(), CONTEXT_DELETE_ORDER, "DELETE");
    }

    // 跳转到详情页
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor c = (Cursor) mAdapter.getItem(position); // CursorAdapter中getItem()返回特定的cursor对象
        int itemID = c.getInt(c.getColumnIndex("_id"));
//        Log.v("LOG", "AllNoteFragment itemID: " + itemID);
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);
        intent.putExtra(NoteDetailActivity.SENDED_NOTE_ID, itemID);
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse("content://com.terry.NoteBook");

        return new CursorLoader(getActivity(), uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}

~~~

**//SearchNoteFragment**

~~~
package com.example.yangtianrui.notebook.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangtianrui.notebook.R;
import com.example.yangtianrui.notebook.activity.NoteDetailActivity;
import com.example.yangtianrui.notebook.adapter.ShowNoteAdapter;
import com.example.yangtianrui.notebook.db.NoteDAO;

/**
 * Created by yangtianrui on 16-5-23.
 * 根据标题查找所有匹配的note
 */
public class SearchNoteFragment extends Fragment {

    private EditText mEtSearch;
    private ListView mLvResult;
    private Button mBtnQuery;
    private CursorAdapter mAdapter;
    private NoteDAO mNoteDAO;
    private Cursor mCursor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNoteDAO = new NoteDAO(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        mEtSearch = (EditText) root.findViewById(R.id.id_et_search_title);
        mLvResult = (ListView) root.findViewById(R.id.id_lv_found_note);
        mBtnQuery = (Button) root.findViewById(R.id.id_btn_search);
        // 查询操作
        mBtnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mEtSearch.getText().toString();
                if (title.length() > 0 && title != null) {
                    mCursor = mNoteDAO.queryNote("title like ? ", new String[]{"%" + title + "%"});
                }
                if (!mCursor.moveToNext()) {
                    Toast.makeText(getActivity(), "没有这个结果", Toast.LENGTH_SHORT).show();
                }
                mAdapter = new ShowNoteAdapter(getActivity(), mCursor);
                mLvResult.setAdapter(mAdapter);
            }
        });
        mLvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = (Cursor) mAdapter.getItem(position); // CursorAdapter中getItem()返回特定的cursor对象
                int itemID = c.getInt(c.getColumnIndex("_id"));
                Intent intent = new Intent(getActivity(), NoteDetailActivity.class);
                intent.putExtra(NoteDetailActivity.SENDED_NOTE_ID, itemID);
                startActivity(intent);
            }
        });
        return root;
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mCursor != null) {
            mCursor.close();
        }
    }
}
~~~

**（2）DrawerLayout**

**MainActivity.java**

~~~
package com.example.yangtianrui.notebook.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.yangtianrui.notebook.R;
import com.example.yangtianrui.notebook.adapter.NaviListAdapter;
import com.example.yangtianrui.notebook.fragment.AllNotesFragment;
import com.example.yangtianrui.notebook.fragment.SearchNoteFragment;
import com.example.yangtianrui.notebook.fragment.SettingFragment;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, AdapterView.OnItemClickListener {

    private Toolbar mToolbar;
    private ActionBarDrawerToggle mToggle;
    private FrameLayout mFlContent;
    private ListView mLvNavi;
    private DrawerLayout mDlLayout;
    private String[] mLabels = new String[]{
            "AllNodes", "SearchByTitle"
    };
    private Fragment mFragments[] = new Fragment[mLabels.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragments[0] = new AllNotesFragment();
        mFragments[1] = new SearchNoteFragment();
        initView();
        showFragment();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(this);
        mDlLayout = (DrawerLayout) findViewById(R.id.id_dl_main_layout);
        mFlContent = (FrameLayout) findViewById(R.id.id_fl_main_content);
        mLvNavi = (ListView) findViewById(R.id.id_lv_navi);
        NaviListAdapter adapter = new NaviListAdapter(this, Arrays.asList(mLabels));
        mToggle = new ActionBarDrawerToggle(this, mDlLayout, mToolbar, R.string.app_name, R.string.app_name);
        mToggle.syncState();
        mDlLayout.setDrawerListener(mToggle);
        mLvNavi.setAdapter(adapter);
        // 为每个ListItem添加点击事件,即启动相应的Fragment
        mLvNavi.setOnItemClickListener(this);
    }

    private void showFragment() {
        AllNotesFragment notesFragment = new AllNotesFragment();
        getFragmentManager().beginTransaction().replace(R.id.id_fl_main_content, notesFragment).commit();
    }


    /**
     * 添加菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * 设置添加事件
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_menu_add_note:
                Intent intent = new Intent(this, NoteDetailActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }

    /**
     * 切换到相应的Fragment
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        getFragmentManager().beginTransaction()
                .replace(R.id.id_fl_main_content, mFragments[position]).commit();
        mDlLayout.closeDrawers();
    }
}
~~~

### 4.SQLite 存储数据

**概念：**SQLite，是一款轻型的数据库，是遵守ACID的关系型数据库管理系统，它包含在一个相对小的C库中。它是D.RichardHipp建立的公有领域项目。它的设计目标是嵌入式的，而且目前已经在很多嵌入式产品中使用了它，它占用资源非常的低，在嵌入式设备中，可能只需要几百K的内存就够了。

**核心代码**

**//DBHelper**

~~~
package com.example.yangtianrui.notebook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yangtianrui on 16-5-21.
 */
public class DBHelper extends SQLiteOpenHelper {
    public final static String DB_NAME = "notes";
    public final static int DB_VERSON = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DB_NAME + "(_id integer primary key autoincrement" +
                ",title text, content text,create_time text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists" + DB_NAME);
        onCreate(db);
    }
}
~~~

**//NoteDAO**

~~~
package com.example.yangtianrui.notebook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Selection;

/**
 * Created by yangtianrui on 16-5-21.
 * <p/>
 * 关于notes数据的CRUD
 */
public class NoteDAO {
    private DBHelper mHelper;
    private Context context;

    public NoteDAO(Context context) {
        mHelper = new DBHelper(context);
    }

    public long insertNote(ContentValues contentValues) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        long id = db.insert(DBHelper.DB_NAME, null, contentValues);
        db.close();
        return id;
    }

    public int updateNote(ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int count;
        count = db.update(DBHelper.DB_NAME, values, whereClause, whereArgs);
        db.close();
        return count;
    }

    public int deleteNote(String whereClause, String[] whereArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int count = db.delete(DBHelper.DB_NAME, whereClause, whereArgs);
        return count;
    }

    public Cursor queryNote(String selection, String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor c = db.query(false, DBHelper.DB_NAME, null, selection, selectionArgs
                , null, null, null, null);
        return c;
    }

}
~~~

**//NoteProvider**

~~~
package com.example.yangtianrui.notebook.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by yangtianrui on 16-5-23.
 * 提供数据,共loader更新数据
 */
public class NoteProvider extends ContentProvider {

    private NoteDAO mNoteDAO;

    @Override
    public boolean onCreate() {
 
 
 mNoteDAO = new NoteDAO(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.v("LOG","NoteProvider -- query()");
        Cursor c = mNoteDAO.queryNote(null, null);
//        Log.v("LOG", "id=" + c.getInt(1) + " content=" + c.getString(2));
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
~~~

### 5.时间戳

**相应代码**

```
private void initData() {
    Intent intent = getIntent();
    mNote = new Note("", "", TextFormatUtil.formatDate(new Date()));
    mNoteID = intent.getIntExtra(SENDED_NOTE_ID, -1);
    // 如果有ID参数,从数据库职工获取信息
    mNoteDAO = new NoteDAO(this);
    if (mNoteID != -1) {
        // 进行查询必须使用?匹配参数
        mCursor = mNoteDAO.queryNote("_id=?", new String[]{mNoteID + ""});
        if (mCursor.moveToNext()) {
            mNote.setTitle(mCursor.getString(mCursor.getColumnIndex("title")));
            mNote.setContent(mCursor.getString(mCursor.getColumnIndex("content")));
            mNote.setCreateTime(mCursor.getString(mCursor.getColumnIndex("create_time")));
        }
    }
}
```

### 5.效果截图

**主页**

![](https://github.com/CodeCatPro/Android/blob/master/MyNoteBook/picture/1.png?raw=true)

**创建笔记**

![](https://github.com/CodeCatPro/Android/blob/master/MyNoteBook/picture/2.png?raw=true)

**查看所有的笔记**

![](https://github.com/CodeCatPro/Android/blob/master/MyNoteBook/picture/4.png?raw=true)

![](https://github.com/CodeCatPro/Android/blob/master/MyNoteBook/picture/3.png?raw=true)

**搜索要查询的笔记**

![](https://github.com/CodeCatPro/Android/blob/master/MyNoteBook/picture/4.png?raw=true)

![](https://github.com/CodeCatPro/Android/blob/master/MyNoteBook/picture/5.png?raw=true)

![](https://github.com/CodeCatPro/Android/blob/master/MyNoteBook/picture/6.png?raw=true)

**更新或删除指定的笔记**

![](https://github.com/CodeCatPro/Android/blob/master/MyNoteBook/picture/7.png?raw=true)

![](https://github.com/CodeCatPro/Android/blob/master/MyNoteBook/picture/1.png?raw=true)