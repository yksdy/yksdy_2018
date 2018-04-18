package com.yksdy.desigin.patterns.structural.adapter;

public class VlcPlayer implements AdvancedMediaPlayer{

	public void playVlc(String fileName) {
		System.out.println("VlcPlayer playVlc, fileName = "+fileName);
		
	}

	public void playMp4(String fileName) {
		
	}

}
