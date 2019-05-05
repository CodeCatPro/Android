# Android学习(4):实现设置Activity                                    
## 实验关键、核心代码以及运行效果图
**CheckBoxPreference**

显示一个包含已启用或已停用设置复选框的项目。保存的值是布尔型（如果选中则为 true）。

**代码：**

    <PreferenceCategory
        android:title="In-line preferences">
    
        <CheckBoxPreference
            android:key="pref_sync"
            android:title="CheckBox Preference"
            android:summary="This is a checkbox"
            android:defaultValue="false" />
    
    </PreferenceCategory>


![CheckBoxPreference 效果图](https://github.com/Huanglei666/PreferenceTest/blob/master/%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/CheckboxPreference.png)

**EditTextPreference**

打开一个包含 EditText 小部件的对话框。保存的值是 String。

**代码：**

        <EditTextPreference
            android:title="Edit text preference"
            android:summary="An example that uses an edit text dialog"
            android:dialogTitle="Enter your favorite animal" />

![EditTextPreference 效果图](https://github.com/CodeCatPro/Preference/blob/master/%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/4_2.png)

**ListPreference**

打开一个包含单选按钮列表的对话框。保存的值可以是任一受支持的值类型。

**代码：**

        <ListPreference
            android:key="pref_syncConnectionType"
            android:title="List preference"
            android:summary="An example that uses an list dialog"
            android:dialogTitle="Choose one"
            android:entries="@array/choices"
            android:entryValues="@array/choices_items"
            android:defaultValue="1" />

![ListPreference 效果图](https://github.com/Huanglei666/PreferenceTest/blob/master/%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/4_3.png)

**PreferenceScreen-CheckBox**

若要将设置组放入子屏幕，则将 Preference 对象组放入 PreferenceScreen 内。<PreferenceScreen> 元素创建的项目选中后，即会打开一个单独的列表来显示嵌套设置。

**代码：**

        <PreferenceScreen
            android:title="Sreen Preference"
            android:summary="show another screen of preferences">
    
            <CheckBoxPreference
                android:key="inbox"
                android:title="Toggle preference"
                android:summary="Preference that is on the next screen but same hierarchy"
                android:defaultValue="false"/>
    
        </PreferenceScreen>

![PreferenceScreen-CheckBox 效果图](https://github.com/Huanglei666/PreferenceTest/blob/master/%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/4_4.png)

**PreferenceScreen-Intent**

在某些情况下，您可能需要首选项来打开不同的 Activity（而不是网络浏览器等设置屏幕）或查看网页。 要在用户选择首选项时调用 Intent，
则要将<intent> 元素添加为相应 <Preference> 元素的子元素。
如下面的代码所示：android:action存的值是它要分配的操作，android:data存的值则是本次操作要跳转的网址，本实验网址为百度官网。

**代码：**

        <PreferenceScreen
            android:title="Intent Preference"
            android:summary="Launch an Activity from an Intent">
    
            <intent
                android:action="android.intent.action.VIEW"
                android:data="@string/Baidu"/>
    
        </PreferenceScreen>

![PreferenceScreen-Intent 效果图](https://github.com/Huanglei666/PreferenceTest/blob/master/%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/4_5.png)

ps:因为模拟器DNS一直配置不好，所以域名解析错误导致无法显示页面。

**Parent-Child-CheckBox-Preference**

有时还要用到类似“父子”的复选框，就是只有选中了“父”复选框，“子”复选框才变成有效的，否则“子”复选框无效。
要实现以上功能，关键在于“子”复选框的android:dependency参数，它的值是“父”复选框的key值。如下：

**代码：**

        <CheckBoxPreference
            android:key="pref_sync1"
            android:title="Parent checkBox preference"
            android:summary="This is visually a parent"
            android:defaultValue="false"/>
    
        <CheckBoxPreference
            android:key="pref_sync2"
            android:title="Child checkBox preference"
            android:summary="This is visually a child"
            android:defaultValue="false"
            android:dependency="pref_sync1"/>

![Parent-Child-CheckBox-Preference 效果图](https://github.com/Huanglei666/PreferenceTest/blob/master/%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/4_6.png)

**Preference 调用**

不同于普通layout文件的加载调用：`setContentView(R.layout.activity_main)`

Preference.xml的加载的方式为：`addPreferencesFromResource(R.xml.preferences)`

**代码：**

        public class Preference_Activity  extends PreferenceActivity {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                addPreferencesFromResource(R.xml.preferences);
            }
        }
