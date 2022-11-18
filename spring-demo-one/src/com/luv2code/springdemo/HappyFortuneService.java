package com.luv2code.springdemo;

public class HappyFortuneService implements FortuneService {

	@Override
	public String getFortue() {
		return "Today is your lucky day!";
	}

}
