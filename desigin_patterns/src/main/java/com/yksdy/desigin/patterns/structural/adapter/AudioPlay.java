package com.yksdy.desigin.patterns.structural.adapter;

public class AudioPlay implements MediaPlayer{
	private MediaAdapter mediaAdapter;

	public void play(String fileType, String fileName) {
		
		if(fileType.equalsIgnoreCase("mp3")) {
			System.out.println("AudioPlay play, fileName = "+fileName);
		}else if(fileType.equalsIgnoreCase("mp4")||fileType.equalsIgnoreCase("vlc")) {
			mediaAdapter = new MediaAdapter(fileType);
			mediaAdapter.play(fileType, fileName);
		}
		
	}
}
