package com.bigbreakfast.paulbearer.framework;

public enum Facing {
	
	Left(-1),
	Right(1),
	Up(2),
	Down(3);
	
	private int code;
	
	Facing(int code) {
		this.code = code;
	}
	
	public int getCode() {
        return code;
	}

    public void setCode(int code) {
        this.code = code;
    }	

}
