# CustomProgressBar

 
###  如何使用
#### 
* AndroidStudio 中引入ProgressBar的Library

* 在需要ProgressBar的xml文件中引入(分为两种情况)
1.  横向进度条
```
 <com.dangxy.progressbar.HorizontalProgressBar
        android:id="@+id/hpb_progress_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:progress="10"
        android:layout_marginTop="30dp"
        android:padding="5dp"
        dang:progress_text_color="#ffff0000"
        dang:progress_un_reach_color="#44ff0000"
        dang:progress_reach_color="#ffff0000"
        />
```
2. 原型进度条

```
  <com.dangxy.progressbar.CircleProgressBar
            android:id="@+id/cpb_progress_bar1"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:progress="40"
            dang:radius="60dp"
            android:layout_marginTop="30dp"
            android:padding="5dp"
            dang:progress_text_color="#ff000000"
            dang:progress_text_size="20sp"
            dang:progress_un_reach_color="#FFFF00"
            dang:progress_reach_color="#7D26CD"
            />
```
###  属性说明

|            属性            |     作用      |    类型     |
| :----------------------:  | :---------: | :-------: |
|   progress_text_color    |  设置进度的字体颜色  |   color   |
| progress_un_reach_color  |   未到达进度颜色   |   color   |
|   progress_reach_color   |   到达字体颜色    |   color   |
|          radius          |    圆角弧度     | dimension |
|    progress_text_size    |   进度字体大小    | dimension |
|   progress_text_offset   | 进度字体距离两边的宽度 | dimension |
| progress_un_reach_height |  进度条未到达的高度  | dimension |
|  progress_reach_height   |  进度条到达的高度   | dimension |

### 效果图

 ![Screenshots](https://github.com/dangxy/CustomProgressBar/blob/master/gif/ProgressBar.gif )






