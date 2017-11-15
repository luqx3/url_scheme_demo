package com.example.zzb.url_scheme_demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zzb on 2017/11/14.
 */

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.tv);
        btn=(Button)findViewById(R.id.btn);
        textView.setText(this.toString());
        btn.setText("跳转到乐盈车");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri data = Uri.parse("android://lexing:80/car");
                Intent intent = new Intent(Intent.ACTION_VIEW,data);
                //保证新启动的APP有单独的堆栈，如果希望新启动的APP和原有APP使用同一个堆栈则去掉该项
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    startActivityForResult(intent, RESULT_OK);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(SecondActivity.this, "没有匹配的APP，请下载安装", Toast.LENGTH_SHORT).show();
                }

            }
        });
        WebView bodyHtml=(WebView)findViewById(R.id.mail_open_html);
        //能够的调用JavaScript代码
        bodyHtml.getSettings().setJavaScriptEnabled(true);
        //加载HTML字符串进行显示
        bodyHtml.loadData("<html xmlns=\"http://www.w3.org/1999/xhtml\" >\n" +
                        "    <head>\n" +
                        "        <title>通过URL Scheme启动Android应用</title>\n" +
                        "    </head>\n" +
                        "    <body>\n" +
                        "        <form>\n" +
                        "            <a href=\"android://lexing:80/car\">start_up_leying_car</a>\n" +
                        "        </form>\n" +
                        "    </body>\n" +
                        "</html>",
                "text/html", "utf-8");
    }
}
