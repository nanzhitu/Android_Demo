package com.example.contectsdemo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView) this.findViewById(R.id.text);
        button1=(Button) this.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                StringBuilder sb=getContacts();
                text.setText(sb.toString());

            }
        });
    }

    private StringBuilder getContacts() {
        StringBuilder  sbLog = new StringBuilder();
        // 得到ContentResolver对象
        ContentResolver cr = this.getContentResolver();
        // 取得电话本中开始一项的光标,主要就是查询"contacts"表
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if(!cursor.moveToFirst()){
            sbLog.append("获取内容为空！");
            return sbLog;
        }
        if(cursor.moveToFirst())
        {


            // 取得联系人名字 (显示出来的名字)，实际内容在 ContactsContract.Contacts中
            int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(nameIndex);
            sbLog.append("name=" + name + ";");

            // 取得联系人ID
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

            // 根据联系人ID查询对应的电话号码
            Cursor phoneNumbers = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                    + contactId, null, null);
            // 取得电话号码(可能存在多个号码)
            while (phoneNumbers.moveToNext())
            {
                String strPhoneNumber = phoneNumbers.getString(phoneNumbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                sbLog.append("Phone=" + strPhoneNumber + ";");
            }
            phoneNumbers.close();

            // 根据联系人ID查询对应的email
            Cursor emails = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = "
                    + contactId, null, null);
            // 取得email(可能存在多个email)
            while (emails.moveToNext())
            {
                String strEmail = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                sbLog.append("Email=" + strEmail + ";");
            }
            emails.close();

        }
        cursor.close();
        Log.e("-------------------", sbLog.toString());
        return sbLog;
    }
}
