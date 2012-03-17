package com.luisgromero.todolist;

import java.util.ArrayList;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener, OnKeyListener {
    /** Called when the activity is first created. */
	
	EditText txtItem;
	Button btnAdd;
	ListView listItems;
	
	ArrayList<String> toDoItems;
	ArrayAdapter<String> aa;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        txtItem = (EditText) findViewById(R.id.txtItem);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        listItems= (ListView) findViewById(R.id.listItems);
        
        btnAdd.setOnClickListener(this);
        txtItem.setOnClickListener(this);
        toDoItems = new ArrayList<String>();
        aa= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,toDoItems);
        listItems.setAdapter(aa);
    }
    
    private void addItem(String item){
    	if(item.length()>0){
    		this.toDoItems.add(item);
    		this.aa.notifyDataSetChanged();
    		this.txtItem.setText("");
    	}
    }

	public void onClick(View v) {
		if(v==this.btnAdd){
			this.addItem(this.txtItem.getText().toString());
		}
		
	}

	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		if(event.getAction()==KeyEvent.ACTION_DOWN & keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
			this.addItem(this.txtItem.getText().toString());
		}
		
		return false;
	}
}