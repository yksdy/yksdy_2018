package com.yksdy.desigin.patterns.structural.adapter;

public class Mp4Player implements AdvancedMediaPlayer{

	public void playVlc(String fileName) {
	}

	public void playMp4(String fileName) {
		System.out.println("Mp4Player playMp4, fileName = "+fileName);
		
	}

}
