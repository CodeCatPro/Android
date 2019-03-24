# Android学习（1）：Activity的生命周期

### 一、Activity生命周期的五状态、七方法

​	**五状态：**

​			1.启动状态；

​			2.运行状态；

​			3.暂停状态；

​			4.停止转台；

​			5.销毁状态。



​	**七方法：**

​			1.onCreate()方法：

```java
@Override
protected void onCreate(Bundle saveInstanceState){
	super.onCreate(saveInstanceState);
    setContentView(R.layout.activity_main);
}
```

​			2.onStart()方法：

```java
@Override
protected void onStart(){
    super.onStart();
}
```

​			3.onResume()方法：

```java
@Override
protected void onResume(){
    super.onResume();
}
```

​			4.onPause()方法:

```java
@Override
protected void onPause(){
    super.onPause();
}
```

​			5.onStop()方法:

```java
@Override
protected void onStop(){
    super.onStop();
}
```

​			6.onDestroy()方法:

```java
@Override
protected void onDestroy(){
    super.onDestroy();
}
```

​			7.onRestart()方法:

```java
@Override
protected void onRestart(){
    super.onRestart();
}
```



### 二、Activity生命周期图

![点击查看源网页](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553436913370&di=af9617545decb7bc650fc8258de58695&imgtype=0&src=http%3A%2F%2Fs13.sinaimg.cn%2Fmw690%2Fb639be08gd5ba3f540eac%26690)



### 三、Activity生命周期验证及验证结果

**验证代码：**

~~~java
package com.fjnuse.activitylife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivityLife","调用onCreate()");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("MainActivityLife","调用onStart()");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("MainActivityLife","调用onResume()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("MainActivityLife","调用onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("MainActivityLife","调用onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("MainActivityLife","调用onDestroy()");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i("MainActivityLife","调用onRestart()");
    }
}
~~~



**验证结果：**

![TIM图片20190324195859](C:\Users\王道长\Pictures\TIM图片20190324195859.png)

![TIM图片20190324200405](C:\Users\王道长\Pictures\TIM图片20190324200405.png)



### 四、验证过程出现问题及解决方法

**问题：**发现```log.i("MainActivityLife","调用onCreate()");```飘红。

**解决方法：**导入对应包，即```import android.util.Log;```,并且方法首字母一定要大写。