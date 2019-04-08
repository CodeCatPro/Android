# Android学习（3）：UI组件

### ListView的用法：

**用SimpleAdapter实现如下界面效果**

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/hQkpLM7Qx6*3N*YNayLQe6pDWwOYbEBRIkwAMDSq3jM!/b/dLYAAAAAAAAA&bo=mgPPAZoDzwEDCSw!&rf=viewer_4)

**代码**

**Animal.java**

~~~
package com.fjnuse.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.widget.Button;

public class Animal extends AppCompatActivity {
    //用于显示布局里的动物名称
    private String[] names = new String[]{"Lion", "Tiger", "Monkey", "Dog", "Cat","Elephant"};
    private int[] image = new int[]{R.drawable.lion, R.drawable.tiger, R.drawable.monkey, R.drawable.dog, R.drawable.cat,R.drawable.elephant};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //此处引用布局文件
        setContentView(R.layout.animal);
    //    getSupportActionBar().hide();
        Button btn1=(Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final int color1 = 0xFFC5B5FF;
        final int color2 = 0xFFFFFFFF;
        //创建一个list集合，list集合的元素是Map
        List<Map<String, Object>> ListItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("header", names[i]);
            listItem.put("images", image[i]);
            //加入list集合
            ListItems.add(listItem);
        }
        //创建一个SimpleAdapter，此处严格按照定义数组names与image顺序,否则会出现程序build成功却运行失败且难以解决错误
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, ListItems, R.layout.simple_items, new String[]{"header", "images"}, new int[]{R.id.header, R.id.images});
        final ListView list = (ListView) findViewById(R.id.lv);
        //为ListView设置Adapter
        list.setAdapter(simpleAdapter);
        //对应点击事件
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int flag = 0;
                switch (flag) {
                    case 0:
                        CharSequence text = names[position];
                        //定义一个Toast表示哪一个图片所在item被点击
                        int duration = Toast.LENGTH_SHORT;  //LENGTH_SHORT是2秒，LONG是3.5秒
                        Toast toast = Toast.makeText(Animal.this, text, duration);
                        toast.show();
                        flag = 1;
                        break;
                    case 1:
                        CharSequence text1 = names[position];
                        int duration1 = Toast.LENGTH_SHORT;
                        Toast toast1 = Toast.makeText(Animal.this, text1, duration1);
                        toast1.show();
                        flag = 0;
                        break;
                }
            }
        });
        //选中函数
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(names[position] + "选中");
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
~~~

**animal.xml**

~~~
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@mipmap/background"
    tools:context=".MainActivity">
    <ListView
        android:id="@+id/lv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_margin="5dp"
        android:divider="#d9d9d9"
        android:dividerHeight="1dp">
    </ListView>
    <Button
        android:id="@+id/btn1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Back"
        app:layout_constraintBottom_toBottomOf="parent" />
</android.support.constraint.ConstraintLayout>
~~~

**simple_items.xml**

~~~
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <RelativeLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">
        <TextView
            android:id="@+id/header"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerVertical="true"
            android:paddingLeft="10dp"
            android:textColor="#000000"
            android:textSize="20dp" />
        <ImageView
            android:id="@+id/images"
            android:layout_width="50dp"
            android:layout_height="50dp"
            android:layout_alignParentRight="true"
            android:layout_margin="0dp" />
    </RelativeLayout>
</LinearLayout>
~~~

**运行结果截图**

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/k435Ge7u1964hI8nAUWQIolUqsJRtWYioBcQTMDgAgY!/b/dL8AAAAAAAAA&bo=ywE0A8sBNAMDCSw!&rf=viewer_4)

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/z3GtVOEnakwJclT97iHvqMNDtpDTlaCt7MhRkNFoEcI!/b/dFQBAAAAAAAA&bo=ywE0A8sBNAMDCSw!&rf=viewer_4)



### 创建自定义布局的AlertDialog:

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/N4YGthU0H.2jDw155.t55.R1o1gtjkU3x2ryK8OeuX4!/b/dL8AAAAAAAAA&bo=OAO6ATgDugEDCSw!&rf=viewer_4)

**代码**

**Login.java**

~~~
package com.fjnuse.ui;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.content.DialogInterface;
import android.widget.Button;

public class Login extends AppCompatActivity {
    private android.view.LayoutInflater LayoutInflater;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //此处引用布局文件
        setContentView(R.layout.login);
//        getSupportActionBar().hide();
        LayoutInflater=LayoutInflater.from(Login.this);
        view=LayoutInflater.inflate(R.layout.dialog, null);
        Button btn1=(Button)findViewById(R.id.lg);
        Button btn2=(Button)findViewById(R.id.bk);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater=LayoutInflater.from(Login.this);
                view=LayoutInflater.inflate(R.layout.dialog, null);

                AlertDialog.Builder builder=new AlertDialog.Builder(Login.this);
                builder.setView(view);
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", null);
                AlertDialog d=builder.create();
                d.show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
~~~

**login.xml**

~~~
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@mipmap/background"
    tools:context=".Login">
    <Button
        android:id="@+id/lg"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="登录"
        app:layout_constraintTop_toTopOf="parent"/>

    <Button
        android:id="@+id/bk"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Back"
        app:layout_constraintBottom_toBottomOf="parent"/>
</android.support.constraint.ConstraintLayout>
~~~

**dialog.xml**

~~~
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <ImageView
        android:id="@+id/logo"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="@drawable/header_logo"
        android:layout_margin="5dp"/>
    <EditText
        android:id="@+id/username"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toBottomOf="@+id/logo"
        android:layout_margin="5dp"
        android:hint="Username"
        android:inputType="text"/>

    <EditText
        android:id="@+id/password"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="5dp"
        app:layout_constraintTop_toBottomOf="@+id/username"
        android:hint="Password"
        android:inputType="textPassword"
        android:fontFamily="sans-serif"/>

</android.support.constraint.ConstraintLayout>
~~~

**运行结果截图**

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/..FRJMsGDTUGg4ssewWvC2JsrBIkCY5M0i7XSz02jTE!/b/dFMBAAAAAAAA&bo=ywE0A8sBNAMDCSw!&rf=viewer_4)

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/tVky9pkUfB2trhpDOU8VA9hjFj1rLpOSU7Y9t2xTVbM!/b/dLYAAAAAAAAA&bo=ywE0A8sBNAMDCSw!&rf=viewer_4)

### 使用XML定义菜单：

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/8ue*GrBQpZo.8UV0*gOoSPQ*xEiDMoYf9ysKhRsWmYY!/b/dL4AAAAAAAAA&bo=HAOKARwDigEDCSw!&rf=viewer_4)

**代码**

**XMLMenu.java**

~~~
package com.fjnuse.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.RED;

public class XMLMenu extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmlmenu);
        Button btn1=(Button)findViewById(R.id.bk);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView=findViewById(R.id.tv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 利用菜单填充器将菜单资源文件映射成菜单
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.fontmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item:
                Toast.makeText(this, "你单击了[普通菜单选项]菜单项！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.red:
                textView.setTextColor(RED);
//                Toast.makeText(this, "你单击了[字体大小]菜单项！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.black:
                textView.setTextColor(BLACK);
//                Toast.makeText(this, "你单击了[字体大小]菜单项！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.small:
                textView.setTextSize(10);
                break;
            case R.id.middle:
                textView.setTextSize(16);
                break;
            case R.id.large:
                textView.setTextSize(20);
                break;
        }
        return true;
     }
}
~~~

**xmlmenu.xml**

~~~
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:background="@mipmap/background">
    <TextView
        android:id="@+id/tv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="大象～大象～你的鼻子为什么那么长！"
        android:textSize="20dp"
        app:layout_constraintTop_toTopOf="parent"/>

    <Button
        android:id="@+id/bk"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Back"
        app:layout_constraintBottom_toBottomOf="parent"/>
</android.support.constraint.ConstraintLayout>
~~~

**fontmenu,xml**

~~~
<?xml version="1.0" encoding="utf-8"?>
<menu
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">
    <item
        tools:ignore="AppCompatResource"
        android:id="@+id/font_menu"
        app:showAsAction="always"
        android:title="@string/file_menu">
        <menu>
            <item
                android:id="@+id/font_size"
                app:showAsAction="withText"
                android:title="@string/font_size">
                <menu>
                    <item
                        android:id="@+id/small"
                        app:showAsAction="withText"
                        android:title="小号"/>
                    <item
                        android:id="@+id/middle"
                        app:showAsAction="withText"
                        android:title="中号"/>
                    <item
                        android:id="@+id/large"
                        app:showAsAction="withText"
                        android:title="大号"/>
                </menu>
            </item>
            <item android:id="@+id/menu_item"
                app:showAsAction="withText"
                android:title="@string/menu_item">
            </item>
            <item android:id="@+id/font_color"
                app:showAsAction="withText"
                android:title="@string/font_color">
                <menu>
                    <item
                        android:id="@+id/black"
                        app:showAsAction="withText"
                        android:title="黑色"/>
                    <item
                        android:id="@+id/red"
                        app:showAsAction="withText"
                        android:title="红色"/>
                </menu>
            </item>
        </menu>
    </item>
</menu>
~~~

**运行结果截图**

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/hOq2Yykn0yCkKK5IX.TYIWcE4Pg8mNfee*69xO07aO0!/b/dFQBAAAAAAAA&bo=ywE0A8sBNAMDCSw!&rf=viewer_4)

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/BDn2LFQSZGKEaLJnols32*V1fh8fpPH89gIETiRkcQ8!/b/dDQBAAAAAAAA&bo=ywE0A8sBNAMDCSw!&rf=viewer_4)

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/EoLe*VgC5BwD*UroSKgpwdkMfX5W6u5edjjXBz3CYio!/b/dFIBAAAAAAAA&bo=ywE0A8sBNAMDCSw!&rf=viewer_4)

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/.TN1sTgjKdkbPQCyHcOANlKtKxXHZGODMdfAG*.YGnM!/b/dLYAAAAAAAAA&bo=ywE0A8sBNAMDCSw!&rf=viewer_4)

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/IUzcogIDr8pLApmhuqGGrqNTZ4EA*o7z.0rZbRlgcfI!/b/dLYAAAAAAAAA&bo=ywE0A8sBNAMDCSw!&rf=viewer_4)

### 创建上下文模式的上下文菜单

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/BtCZCu36.bqEUoxre1vnLKDIKhHh3jk4btfdpqUHbF0!/b/dFQBAAAAAAAA&bo=FwOBAQAAAAADB7Y!&rf=viewer_4)

**代码**

**ActionModeMenu.java**

~~~
package com.fjnuse.ui;
import android.app.LauncherActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ActionModeMenu extends AppCompatActivity {

    String [] number = new String[]{"One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen" };
    MySimpleAdapter sim;
    List<Map<String,Object>> listItems;
    //重写适配器getView
    class MySimpleAdapter extends SimpleAdapter {
        public MySimpleAdapter(Context context, List<Map<String,Object>> data, int resource, String[] from, int[] to){
            super(context, data, resource, from, to);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            ListView temp=(ListView)findViewById(R.id.lv);
            if (view != null) {
                //若被选中
                if (temp.isItemChecked(position)) {
                    view.setBackgroundColor(Color.parseColor("#33B5E5"));
                } else {
                    view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
            }
            return view;
        }

        public void deleteSelectedItems() {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contextmenu);

        Button btn1=(Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //创建List合集，元素是Map
        listItems=new ArrayList<Map<String,Object>>();
        for(int i=0;i<number.length;i++)
        {
            Map<String,Object> listItem=new HashMap<String,Object>();
            listItem.put("title",number[i]);
            listItem.put("image",R.drawable.ic_launcher_foreground);
            listItems.add(listItem);
        }
        //创建SimpleAdapter
        sim=new MySimpleAdapter(this,listItems,R.layout.actionmode_items,
                new String[] {"title","image"},
                new int[]{R.id.numbers ,R.id.images});
        final ListView l=(ListView)findViewById(R.id.lv);
        l.setAdapter(sim);
        l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        l.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                //当列表中的项目选中或取消勾选时，这个方法会被触发
                //可以在这个方法中做一些更新操作，比如更改上下文操作栏的标题
                //这里显示已选中的项目数
                mode.setTitle(l.getCheckedItemCount()+" selected");
                //通知样式变化
                sim.notifyDataSetChanged();
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater=mode.getMenuInflater();
                inflater.inflate(R.menu.options,menu);
                return true;
            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                boolean ret = false;
                if (item.getItemId() == R.id.delete) {
                    sim.deleteSelectedItems();
                    mode.finish();
                    ret = true;
                }
                return  ret;
            }
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                //可以对上下文操作栏做一些更新操作（会被ActionMode的invalidate方法触发）
                return false;
            }
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                //在上下文操作栏被移除时会触发，可以对Activity做一些必要的更新
                //默认情况下，此时所有的选中项将会被取消选中
            }
        });
    }
}
~~~

**contextmenu.xml**

~~~
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@mipmap/background"
    tools:context=".MainActivity">
    <ListView
        android:id="@+id/lv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_margin="5dp"
        android:divider="#d9d9d9"
        android:dividerHeight="1dp"
        android:fastScrollEnabled="true">
    </ListView>
    <Button
        android:id="@+id/btn1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Back"
        app:layout_constraintBottom_toBottomOf="parent" />
</android.support.constraint.ConstraintLayout>
~~~

**actionmode_items.xml**

~~~
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">
    <RelativeLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">
        <ImageView
            android:id="@+id/robots"
            android:layout_width="50dp"
            android:layout_height="50dp"
            android:layout_margin="0dp"
            />
        <TextView
            android:id="@+id/numbers"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerVertical="true"
            android:layout_marginLeft="70dp"
            android:textColor="#000000"
            android:textSize="20dp"
            android:paddingLeft="10dp"
            />
    </RelativeLayout>
</LinearLayout>
~~~

**options.xml**

~~~
<?xml version="1.0" encoding="utf-8"?>
<menu
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">
    <item
        tools:ignore="AppCompatResource"
        android:id="@+id/delete"
        app:showAsAction="always"
        android:title="删除">
    </item>
</menu>
~~~

**运行结果截图**

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/E.aWSOdz.v4JaRuFunlfbBMfArg59zerR*GDOrZzTLg!/b/dLkAAAAAAAAA&bo=ywE0A8sBNAMDCSw!&rf=viewer_4)

![](http://m.qpic.cn/psb?/V13zmT7A05rvTZ/S9KDibg*swOtr4NekjrdrvXYz96VVMIxoLVYS7uAUfo!/b/dLYAAAAAAAAA&bo=ywE0A8sBNAMDCSw!&rf=viewer_4)

### 个人心得

整体做下来，真的很难受，因为真的不太熟练，多学多打吧。

