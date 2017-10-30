---
title: UIBestPractise<CODE 2 chaper3>
date: 2017-7-22 20:13:10
tags: CODE 2
---

<h3>学习目的：1.编写简单聊天软件</h3>
![Markdown](http://i2.kiimg.com/595109/879c808e45a4c3da.png)
思路：1.主界面：输入框 发送按钮 2.新建Msg类 3.编写RecyclerView的子布局 4.可见属性（使发送 接受的消息在同一布局内）
5.创建RecycleView的适配类

<h3>1.主界面(简单 ，不赘述)</h3>
```Android
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#d8e0e8" >

    <android.support.v7.widget.RecyclerView   //用于显示聊天内容
        android:id="@+id/msg_recycler_view"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >

        <EditText
            android:id="@+id/input_text"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:hint="Type something here"
            android:maxLines="2" />

        <Button
            android:id="@+id/send"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Send" />

    </LinearLayout>

</LinearLayout>
```
![Markdown](http://i4.piimg.com/595109/2f0a4b4d453ed914.png)

<h3>2.新建Msg类</h3>
```Android
ublic class Msg {

    public static final int TYPE_RECEIVED = 0;

    public static final int TYPE_SENT = 1;

    private String content;//消息内容

    private int type;//消息类型（TYPE_RECEIVED or TYPE_SENT  ）

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

}
```

<h3>3.编写RecyclerView的子布局</h3>
```Android
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="10dp" >

    <LinearLayout
        android:id="@+id/left_layout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="left"
        android:background="@drawable/message_left" >

        <TextView
            android:id="@+id/left_msg"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_margin="10dp"
            android:textColor="#fff" />

    </LinearLayout>

    <LinearLayout
        android:id="@+id/right_layout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="right"
        android:background="@drawable/message_right" >

        <TextView
            android:id="@+id/right_msg"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_margin="10dp" />

    </LinearLayout>

</LinearLayout>
```
![Markdown](http://i4.piimg.com/595109/9c75d9c251aeaecd.png)

<h3>5.创建RecycleView的适配类MsgAdaper</h3>
```Android
public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Msg> mMsgList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout leftLayout;

        LinearLayout rightLayout;

        TextView leftMsg;

        TextView rightMsg;

        public ViewHolder(View view) {
            super(view);
            leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            leftMsg = (TextView) view.findViewById(R.id.left_msg);
            rightMsg = (TextView) view.findViewById(R.id.right_msg);
        }
    }

    public MsgAdapter(List<Msg> msgList) {
        mMsgList = msgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg = mMsgList.get(position);
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            // 如果是收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
        } else if(msg.getType() == Msg.TYPE_SENT) {
            // 如果是发出的消息，则显示右边的消息布局，将左边的消息布局隐藏
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
        }
    }
```

<h3>6.MainActivity类</h3>
```Android

private List<Msg> msgList = new ArrayList<Msg>();

    private EditText inputText;

    private Button send;

    private RecyclerView msgRecyclerView;

    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsgs(); // 初始化消息数据
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);//都
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);//是
        msgRecyclerView.setLayoutManager(layoutManager);//套
        adapter = new MsgAdapter(msgList);//路
        msgRecyclerView.setAdapter(adapter);//啊
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1); // 当有新消息时，刷新ListView中的显示
                    //不是RecycleView吗？
                    msgRecyclerView.scrollToPosition(msgList.size() - 1); // 将ListView定位到最后一行
                    //不是RecycleView吗？
                    inputText.setText(""); // 清空输入框中的内容
                }
            }
        });
```        
