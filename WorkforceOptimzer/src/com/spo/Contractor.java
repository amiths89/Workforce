package com.spo;

public class Contractor {
	
	int[] rooms;
	int seniors;
	int juniors;
	public Contractor(int[] rooms, int seniors, int juniors) {
		super();
		this.rooms = rooms;
		this.seniors = seniors;
		this.juniors = juniors;
	}
	public int[] getRooms() {
		return rooms;
	}
	public void setRooms(int[] rooms) {
		this.rooms = rooms;
	}
	public int getSeniors() {
		return seniors;
	}
	public void setSeniors(int seniors) {
		this.seniors = seniors;
	}
	public int getJuniors() {
		return juniors;
	}
	public void setJuniors(int juniors) {
		this.juniors = juniors;
	}

}
