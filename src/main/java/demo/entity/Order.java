package demo.entity;

import java.io.Serializable;

public class Order implements Serializable {
	String owner;

	public Order(String string) {
		this.owner = string;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
