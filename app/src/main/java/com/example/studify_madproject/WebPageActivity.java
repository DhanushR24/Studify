package com.example.studify_madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebPageActivity extends AppCompatActivity {
    WebView page;
    Intent intent4;
    String unitname,subname,type,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);

        page=(WebView) findViewById(R.id.webView);
        page.setWebViewClient(new WebViewClient());
        WebSettings ws = page.getSettings();
        ws.setJavaScriptEnabled(true);
        page.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        page.clearCache(true);
        page.clearHistory();
        intent4=getIntent();
        unitname=intent4.getStringExtra("unitName");
        subname=intent4.getStringExtra("subName");
        type=intent4.getStringExtra("type");
        url="https://www.google.com";
        Toast.makeText(this, "Subject : "+subname+"\nUnit : "+unitname+"\nType : "+type, Toast.LENGTH_SHORT).show();
        if(subname.equals("Machine Learning"))
        {
            if(type.equals("Theory"))
            {
                switch(unitname)
                {
                    case "unit1":url="https://drive.google.com/drive/folders/1Z7y-zIOO494I71FozzCcnhUsBgiafotZ?usp=sharing";
                        break;
                    case "unit2":url="https://drive.google.com/drive/folders/1rwuW6xateAX-gChGDT_p-TrsiRYQ4FpH?usp=sharing";
                        break;
                    case "unit3":url="https://drive.google.com/drive/folders/1SXLZnwvukqVR1ExuiFZy0-aE5d0e9AMz?usp=sharing";
                        break;
                    case "unit4":url="https://drive.google.com/drive/folders/1B1JOvRnE1SOjlAyF8cBddafRZQjNYkb2?usp=sharing";
                        break;
                    case "unit5":url="https://drive.google.com/drive/folders/1GFUq4F3q1ewbF7fhsh7x91BCRQRI-gD5?usp=sharing";
                        break;
                }
            }else if(type.equals("Video"))
            {
                switch(unitname)
                {
                    case "unit1":
                    case "unit2":
                    case "unit3":
                    case "unit4":
                    case "unit5":
                        url ="https://www.youtube.com/watch?v=GwIo3gDZCVQ&ab_channel=edureka%21";
                        break;
                }
            }
        }
        else if(subname.equals("Computer Networks"))
        {
            if(type.equals("Theory"))
            {
                switch(unitname)
                {
                    case "unit1": url="https://drive.google.com/drive/folders/1X10b81Mx38FjZwwfPtrVzAGhZPoW5b8m?usp=sharing";
                        //break;
                    case "unit2": url="https://drive.google.com/drive/folders/1Wq5ke9ZYRbDMQjzCbcXr2e21yf1Tv47H?usp=sharing";
                        //break;
                    case "unit3": url="https://drive.google.com/drive/folders/1WnC-wskWjUApOiiNuEHVnQQ00_QPcyeZ?usp=sharing";
                        // break;
                    case "unit4": url="https://drive.google.com/drive/folders/1X1s3WmK-wL9c-FW03vgwvCEgx1vGDHxC?usp=sharing";
                        // break;
                    case "unit5":url="https://drive.google.com/drive/folders/1X7647ntCeakcj7wAOJaBgP5jZtQ3AUKG?usp=sharing";
                        break;
                }

            }else if(type.equals("Video"))
            {
                switch(unitname)
                {
                    case "unit1":
                    case "unit2":
                    case "unit3":
                    case "unit4":
                    case "unit5":
                        url = "https://youtube.com/playlist?list=PLBlnK6fEyqRgMCUAG0XRw78UA8qnv6jEx";
                        break;
                }

            }
        }
        else if(subname.equals("Computer Graphics"))
        {
            if(type.equals("Theory"))
            {
                switch(unitname)
                {
                    case "unit1": url="https://drive.google.com/drive/folders/1s0ZEFr21cTWoI_6gZoLOPwWuNNozh08-?usp=sharing";
                        break;
                    case "unit2": url="https://drive.google.com/drive/folders/1K4u2SXX6KNQPdBVi448zFM0MCxENyq4h?usp=sharing";
                        break;
                    case "unit3": url="https://drive.google.com/drive/folders/1wGl0jjhsYr6EWPbyZAGr1wnMkGsQJP5l?usp=sharing";
                        break;
                    case "unit4": url="https://drive.google.com/drive/folders/1YkNV7Z3dVYubLCh1RzyweWpe0jLjS3lN?usp=sharing";
                        break;
                    case "unit5": url="https://drive.google.com/drive/folders/10yN663l6ltXNhS8LpuDBanNiyu3xWsj2?usp=sharing";
                        break;
                }

            }else if(type.equals("Video"))
            {
                switch(unitname)
                {
                    case "unit1": //break;
                    case "unit2": //break;
                    case "unit3":// break;
                    case "unit4": //break;
                    case "unit5":
                        url = "https://youtube.com/playlist?list=PLYwpaL_SFmcAtxMe7ahYC4ZYjQHun_b-T";
                        break;
                }

            }
        }
        else if(subname.equals("Formal Language and Automata Theory"))
        {
            if(type.equals("Theory"))
            {
                switch(unitname)
                {
                    case "unit1":url="https://drive.google.com/drive/folders/1s0ZEFr21cTWoI_6gZoLOPwWuNNozh08-?usp=sharing";
                        break;
                    case "unit2":url="https://drive.google.com/drive/folders/1K4u2SXX6KNQPdBVi448zFM0MCxENyq4h?usp=sharing";
                        break;
                    case "unit3":url="https://drive.google.com/drive/folders/1wGl0jjhsYr6EWPbyZAGr1wnMkGsQJP5l?usp=sharing";
                        break;
                    case "unit4":url="https://drive.google.com/drive/folders/1YkNV7Z3dVYubLCh1RzyweWpe0jLjS3lN?usp=sharing";
                        break;
                    case "unit5":url="https://drive.google.com/drive/folders/10yN663l6ltXNhS8LpuDBanNiyu3xWsj2?usp=sharing";
                        break;
                }

            }else if(type.equals("Video"))
            {
                switch(unitname)
                {
                    case "unit1": //break;
                    case "unit2":// break;
                    case "unit3":// break;
                    case "unit4":// break;
                    case "unit5":
                        url = "https://youtube.com/playlist?list=PLBlnK6fEyqRgp46KUv4ZY69yXmpwKOIev";
                        break;
                }

            }
        }
        else if(subname.equals("Cloud Computing"))
        {
            if(type.equals("Theory"))
            {
                switch(unitname)
                {
                    case "unit1":url="https://drive.google.com/drive/folders/1W9Fg9t8iIGKN0WLObA6qaUs5P_E90JFy?usp=sharing";
                        break;
                    case "unit2":url="https://drive.google.com/drive/folders/1W0Bvf_DgiOrDJWyBeknojL0QEtN6LqP3?usp=sharing";
                        break;
                    case "unit3":url="https://drive.google.com/drive/folders/1WCM8Wm0yf25Zlhvu-dK5lIcyCUf7_1DM?usp=sharing";
                        break;
                }


            }else if(type.equals("Video"))
            {
                switch(unitname)
                {
                    case "unit1":// break;
                    case "unit2": //break;
                    case "unit3":
                        url ="https://youtube.com/playlist?list=PLEiEAq2VkUUIJ3o1tehvtux0_Ynf42CBN";
                        break;
                }

            }
        }
        else if(subname.equals("Cryptography"))
        {
            if(type.equals("Theory"))
            {
                switch(unitname)
                {
                    case "unit1": url="https://drive.google.com/drive/folders/1WPZTpZKtA4HwV6Etcd4dF9ceUBU3qwbd?usp=sharing";
                        break;
                    case "unit2": url="https://drive.google.com/drive/folders/1WUbJKXXuQxsJuYh93JbkIb7YAUSzZBhT?usp=sharing";
                        break;
                    case "unit3": url="https://drive.google.com/drive/folders/1W_DVHq50gBvlNc7ub71GUXh7LzMy3uY0?usp=sharing";
                        break;
                }

            }else if(type.equals("Video"))
            {
                switch(unitname)
                {
                    case "unit1":// break;
                    case "unit2":// break;
                    case "unit3":
                        url = "https://youtube.com/playlist?list=PLBlnK6fEyqRgJU3EsOYDTW7m6SUmW6kII";
                        break;
                }

            }
        }
        page.loadUrl(url);
    }
}