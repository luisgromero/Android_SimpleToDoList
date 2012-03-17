package com.luisgromero.todolist;

import java.util.ArrayList;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener, OnKeyListener , OnItemClickListener{
    /** Called when the activity is first created. */
	
	EditText txtItem;
	Button btnAdd;
	ListView listItems;
	
	ArrayList<String> toDoItems;
	ArrayAdapter<String> aa;
	
	int posItem=0;
    View viewItem;
    boolean IsSomeItemChecked=false;
    
    
    public void setIsSomeItemChecked(boolean checked){
    	this.IsSomeItemChecked=checked;
    }
    
    public boolean getIsSomeItemChecked(){
    	return IsSomeItemChecked;
    }
    
    /*Sets the position of the item checked*/
    public void setPosItem(int position){
    	this.posItem=position;
    }
    
    /*Sets the view in the item checked*/
    public void setViewItem(View view){
    	this.viewItem=view;
    }
    
    /*Gets the position in the item checked*/
    public int getPosItem(){
    	return posItem;
    }
    
    /*Gets the view in the item checked*/
    public View getViewItem(){
    	return viewItem;
    }
	
	
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
        aa= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked,toDoItems);
        listItems.setAdapter(aa);
        listItems.setOnItemClickListener(this);
    }
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	MenuInflater inflater= getMenuInflater();
    	inflater.inflate(R.menu.listmenu, menu);
    	return true;
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

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		CheckedTextView ListTextView = (CheckedTextView)view;
		/*If some item is checked others cannot be checked
		 * And only the one checked can be unchecked.
		 * */
		if(getIsSomeItemChecked()==false){
			ListTextView.setChecked(true);
			setPosItem(position);
			setIsSomeItemChecked(true);
		}else if(getIsSomeItemChecked()==true && position==getPosItem()){
			ListTextView.setChecked(false);
			setIsSomeItemChecked(false);
		}
		
	}
}