package com.cycle;

public class Cycle {
	private String model;
	private String color;

//getter for model
	public String getModel() {
		return model;
	}

//setter for model
	public void setModel(String model) {
		this.model = model;
	}

//getter for color
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

// Override equals method
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Cycle) {
			Cycle objCycle = (Cycle) obj;
			if (this.color.equals(objCycle.color) && (this.model.equals(objCycle.model))) {
				return true;
			} else
				return false;
		} else
			return false;

	}
}
