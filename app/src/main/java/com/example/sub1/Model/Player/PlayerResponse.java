package com.example.sub1.Model.Player;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerResponse{

	@SerializedName("player")
	private List<PlayerItem> player;

	public void setPlayer(List<PlayerItem> player){
		this.player = player;
	}

	public List<PlayerItem> getPlayer(){
		return player;
	}

	@Override
 	public String toString(){
		return 
			"PlayerResponse{" + 
			"player = '" + player + '\'' + 
			"}";
		}
}