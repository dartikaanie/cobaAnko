package com.example.sub1.Model.klasemen;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KlasemenResponse{

	@SerializedName("table")
	private List<TableItem> table;

	public void setTable(List<TableItem> table){
		this.table = table;
	}

	public List<TableItem> getTable(){
		return table;
	}

	@Override
 	public String toString(){
		return 
			"KlasemenResponse{" + 
			"table = '" + table + '\'' + 
			"}";
		}
}