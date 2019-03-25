# Android学习（2）：布局的熟悉与使用

**六个布局类型：**

1. 线性布局（LinearLayout）
2. 绝对布局（RelativeLayout）
3. 帧布局（FrameLayout）
4. 表格布局（TableLayout）
5. 绝对布局（AbsoluteLayout）
6. 约束布局（ConstraintLayout）

**以下主要介绍并使用其中三个布局：**



### 一、线性布局

 **常用属性及解释：**

```android:orientation="vertical/horizontal"```:线性布局垂直或者水平显示。

```android:layout_width/height="wrap_content/match_parent/具体数值"```:设置控件长宽。

wrap_content:包裹内容让当前控件根据控件内容大小自动伸缩。

match_parent:填充父窗体由父容器大小决定控件大小。

```android:layout_weight="数字"```：设置控件所占权重。

```android:text="文字"```：设置控件显示文字。

```android:textSize="具体数值"```：设置显示字体的大小。

```android:textColor="#******(16进制颜色)/@android:color/已有颜色名"```：设置文字颜色。



**布局代码**

```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="#000000">

    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <Button
            android:id="@+id/btn_1one"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="0.8"
            android:text="One,One"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>

        <Button
            android:id="@+id/btn_1two"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="1.6"
            android:text="One,Two"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>

        <Button
            android:id="@+id/btn_1three"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="0.8"
            android:text="One,Three"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>

        <Button
            android:id="@+id/btn_1four"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="0.8"
            android:text="One,Four"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>
    </LinearLayout>

    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <Button
            android:id="@+id/btn_2one"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="0.8"
            android:text="Two,One"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>

        <Button
            android:id="@+id/btn_2two"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="1.6"
            android:text="Two,Two"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>

        <Button
            android:id="@+id/btn_2three"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="0.8"
            android:text="Two,Three"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>

        <Button
            android:id="@+id/btn_2four"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="0.8"
            android:text="Two,Four"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>
    </LinearLayout>

    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <Button
            android:id="@+id/btn_3one"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="1"
            android:text="Three,One"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>

        <Button
            android:id="@+id/btn_3two"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="1"
            android:text="Three,Two"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>

        <Button
            android:id="@+id/btn_3three"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="1"
            android:text="Three,Three"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>

        <Button
            android:id="@+id/btn_3four"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="1"
            android:text="Three,Four"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>
    </LinearLayout>

    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <Button
            android:id="@+id/btn_4one"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="0.8"
            android:text="Four,One"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>

        <Button
            android:id="@+id/btn_4two"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="1.6"
            android:text="Four,Two"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>

        <Button
            android:id="@+id/btn_4three"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="0.8"
            android:text="Four,Three"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>

        <Button
            android:id="@+id/btn_4four"
            android:layout_width="0dp"
            android:layout_height="55dp"
            android:layout_weight="0.8"
            android:text="Four,Four"
            android:textSize="7dp"
            android:textColor="#666666"
            android:background="@drawable/buttonstyle"
            android:layout_margin="1dp"/>
    </LinearLayout>


</LinearLayout>
```



**运行结果截图**

![linearlayout](E:\AndroidStudioProjects\HelloWorld\picture\linearlayout.png)



### 二、约束布局

**相对位置Relative positioning**

**作用：**设置控件与另一个控件的相对位置。 

**可在水平轴与垂直轴约束控件：**

*  水平轴：`Left、Right、Start、End`
* 垂直轴：`top、bottom、text baseline`

![relativepositioning](E:\AndroidStudioProjects\HelloWorld\picture\relativepositioning1.png)



| 序号 |                    属性                    |
| :--: | :----------------------------------------: |
|  1   |     **layout_constraintLeft_toLeftOf**     |
|  2   |    **layout_constraintLeft_toRightOf**     |
|  3   |    **layout_constraintRight_toLeftOf**     |
|  4   |    **layout_constraintRight_toRightOf**    |
|  5   |      **layout_constraintTop_toTopOf**      |
|  6   |    **layout_constraintTop_toBottomOf**     |
|  7   |    **layout_constraintBottom_toTopOf**     |
|  8   |   **layout_constraintBottom_toBottomOf**   |
|  9   | **layout_constraintBaseline_toBaselineOf** |
|  10  |     **layout_constraintStart_toEndOf**     |
|  11  |    **layout_constraintStart_toStartOf**    |
|  12  |     **layout_constraintEnd_toStartOf**     |
|  13  |      **layout_constraintEnd_toEndOf**      |

以上属性需要另一个控件的`id`或`parent`（父容器）作为参数。

![relativepositioning2](https://github.com/CodeCatPro/Android/tree/master/HelloWorld/picture/relativepositioning2.png)



**边距 Margins**

**作用：**设置`target`控件与`source`控件的边距。 

![margin](E:\AndroidStudioProjects\HelloWorld\picture\margin.png)

| 序号 |              属性               |
| :--: | :-----------------------------: |
|  1   | **android:layout_marginStart**  |
|  2   |  **android:layout_marginEnd**   |
|  3   |  **android:layout_marginLeft**  |
|  4   |  **android:layout_marginTop**   |
|  5   | **android:layout_marginRight**  |
|  6   | **android:layout_marginBottom** |



**居中设置 Centering positioning**

```java
app:layout_constraintLeft_toLeftOf="parent"
app:layout_constraintRight_toRightOf="parent"
```

![centerpositioning](E:\AndroidStudioProjects\HelloWorld\picture\centerpositioning.png)

这种情况下，约束的作用类似于相反的力牵引控件并均分边距。而控件最终会在父容器中处于居中位置。垂直约束雷同。

 

**偏置 Bias**

默认情况下，这种对立的约束会促使控件处于居中位置。此时，可以使用`Bias`属性来调整位置，以达到两侧边距不同的效果。

| 序号 |                 属性                 |
| :--: | :----------------------------------: |
|  1   | **layout_constraintHorizontal_bias** |
|  2   |  **layout_constraintVertical_bias**  |

**示例：**设置左侧边距为30％代替默认的50％，如图： 

![bias](E:\AndroidStudioProjects\HelloWorld\picture\bias.png)

```java
<android.support.constraint.ConstraintLayout ...>

    <Button 
        android:id="@+id/button" ...
        app:layout_constraintHorizontal_bias="0.3"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent/>
</>
```



**布局代码**

```java
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/constraintLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/background">

    <TextView
        android:id="@+id/RED"
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:gravity="center"
        android:text="RED"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:background="@drawable/redtextview"/>

    <TextView
        android:id="@+id/ORANGE"
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:gravity="center"
        android:text="ORANGE"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:background="@drawable/orangetextview"/>

    <TextView
        android:id="@+id/YELLOW"
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:gravity="center"
        android:text="YELLOW"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:background="@drawable/yellowtextview"/>

    <TextView
        android:id="@+id/BLUE"
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:layout_marginTop="10dp"
        android:gravity="center"
        android:text="BLUE"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/ORANGE"
        android:background="@drawable/bluetextview"/>

    <TextView
        android:id="@+id/GREEN"
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:layout_marginRight="10dp"
        android:gravity="center"
        android:text="GREEN"
        app:layout_constraintBaseline_toBaselineOf="@+id/BLUE"
        app:layout_constraintRight_toLeftOf="@+id/BLUE"
        android:background="@drawable/greentextview"/>

    <TextView
        android:id="@+id/INDIGO"
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:layout_marginLeft="10dp"
        android:gravity="center"
        android:text="INDIGO"
        app:layout_constraintBaseline_toBaselineOf="@+id/BLUE"
        app:layout_constraintLeft_toRightOf="@+id/BLUE"
        android:background="@drawable/indigotextview"/>

    <TextView
        android:id="@+id/VIOLET"
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:layout_marginTop="10dp"
        android:gravity="center"
        android:text="VIOLET"
        android:textAllCaps="false"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/BLUE"
        android:background="@drawable/violettextview"/>
</android.support.constraint.ConstraintLayout>
```



**运行结果截图**

![constraintlayout](E:\AndroidStudioProjects\HelloWorld\picture\constraintlayout.png)



### 三、表格布局

**用到的布局属性：**

1. ```android:stretchColumns```:设置该列被拉伸，列号从“0”开始。
2. ```android:shrinkColumns```:设置该列被收缩，列号从“0”开始。
3. ```android:collapseColumns```:设置该列被隐藏，列号从“0”开始。



**用到的控件属性：**

1. ```android:layout_column```:设置带单元显示位置，列号从“0”开始。
2. ```android:layout_span```:设置该单元占据几行。



**TableRow的数量决定表格的行数**



**布局代码**

```java
<?xml version="1.0" encoding="utf-8"?>
<TableLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/background"
    android:stretchColumns="5">

    <TableRow>

        <TextView
            android:id="@+id/open"
            android:layout_width="match_parent"
            android:text="Open..."
            android:textColor="#666666"
            android:layout_column="0"
            android:textSize="20dp"/>

        <TextView
            android:id="@+id/co"
            android:layout_width="match_parent"
            android:text="Ctrl-O"
            android:textColor="#666666"
            android:layout_column="6"
            android:gravity="right"
            android:textSize="20dp"/>
    </TableRow>

    <TableRow>
        <TextView
            android:id="@+id/save"
            android:layout_width="match_parent"
            android:text="Save..."
            android:textColor="#666666"
            android:layout_column="0"
            android:textSize="20dp"/>

        <TextView
            android:id="@+id/cs"
            android:layout_width="match_parent"
            android:text="Ctrl-S"
            android:textColor="#666666"
            android:layout_column="6"
            android:gravity="right"
            android:textSize="20dp"/>
    </TableRow>

    <TableRow>

        <TextView
            android:id="@+id/saveas"
            android:layout_width="match_parent"
            android:layout_column="0"
            android:text="Save As..."
            android:textColor="#666666"
            android:textSize="20dp"/>

        <TextView
            android:id="@+id/css"
            android:layout_width="match_parent"
            android:layout_column="6"
            android:gravity="right"
            android:text="Ctrl-Shift-S"
            android:textColor="#666666"
            android:textSize="20dp"/>
    </TableRow>

    <TableRow
        android:background="@drawable/tablerow">
        <TextView
            android:id="@+id/ximport"
            android:layout_width="match_parent"
            android:text="X Import..."
            android:textColor="#666666"
            android:layout_column="0"
            android:textSize="20dp"/>
    </TableRow>

    <TableRow>
        <TextView
            android:id="@+id/xexport"
            android:layout_width="match_parent"
            android:text="X Export..."
            android:textColor="#666666"
            android:layout_column="0"
            android:textSize="20dp"/>

        <TextView
            android:id="@+id/ce"
            android:layout_width="match_parent"
            android:text="Ctrl-E"
            android:textColor="#666666"
            android:layout_column="6"
            android:gravity="right"
            android:textSize="20dp"/>
    </TableRow>

    <TableRow
        android:background="@drawable/tablerow">
        <TextView
            android:id="@+id/quit"
            android:layout_width="match_parent"
            android:text="Quit"
            android:textColor="#666666"
            android:layout_column="0"
            android:textSize="20dp"/>
    </TableRow>
</TableLayout>
```



**运行结果截图**

![tablelayout](E:\AndroidStudioProjects\HelloWorld\picture\tablelayout.png)



### 四、小感想

虽然看起来很简单，但是因为是初次写Android代码，中间难免磕磕绊绊，继续积累吧。

