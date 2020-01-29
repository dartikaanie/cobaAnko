package com.example.sub1.Model.klasemen;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class TableItem{

	@SerializedName("loss")
	private int loss;

	@SerializedName("total")
	private int total;

	@SerializedName("goalsfor")
	private int goalsfor;

	@SerializedName("goalsagainst")
	private int goalsagainst;

	@SerializedName("teamid")
	private String teamid;

	@SerializedName("goalsdifference")
	private int goalsdifference;

	@SerializedName("name")
	private String name;

	@SerializedName("draw")
	private int draw;

	@SerializedName("played")
	private int played;

	@SerializedName("win")
	private int win;

	public void setLoss(int loss){
		this.loss = loss;
	}

	public int getLoss(){
		return loss;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setGoalsfor(int goalsfor){
		this.goalsfor = goalsfor;
	}

	public int getGoalsfor(){
		return goalsfor;
	}

	public void setGoalsagainst(int goalsagainst){
		this.goalsagainst = goalsagainst;
	}

	public int getGoalsagainst(){
		return goalsagainst;
	}

	public void setTeamid(String teamid){
		this.teamid = teamid;
	}

	public String getTeamid(){
		return teamid;
	}

	public void setGoalsdifference(int goalsdifference){
		this.goalsdifference = goalsdifference;
	}

	public int getGoalsdifference(){
		return goalsdifference;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDraw(int draw){
		this.draw = draw;
	}

	public int getDraw(){
		return draw;
	}

	public void setPlayed(int played){
		this.played = played;
	}

	public int getPlayed(){
		return played;
	}

	public void setWin(int win){
		this.win = win;
	}

	public int getWin(){
		return win;
	}

	@NotNull
	@Override
 	public String toString(){
		return 
			"TableItem{" + 
			"loss = '" + loss + '\'' + 
			",total = '" + total + '\'' + 
			",goalsfor = '" + goalsfor + '\'' + 
			",goalsagainst = '" + goalsagainst + '\'' + 
			",teamid = '" + teamid + '\'' + 
			",goalsdifference = '" + goalsdifference + '\'' + 
			",name = '" + name + '\'' + 
			",draw = '" + draw + '\'' + 
			",played = '" + played + '\'' + 
			",win = '" + win + '\'' + 
			"}";
		}
}