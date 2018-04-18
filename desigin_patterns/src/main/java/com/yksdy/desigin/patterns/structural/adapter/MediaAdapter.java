package com.yksdy.desigin.patterns.structural.adapter;

public class MediaAdapter implements MediaPlayer{
	private AdvancedMediaPlayer advancedMediaPlayer = null;
	
	public MediaAdapter(String fileType){
		if(fileType.equalsIgnoreCase("vlc")) {
			advancedMediaPlayer = new VlcPlayer();
		}else if(fileType.equalsIgnoreCase("mp4")) {
			advancedMediaPlayer = new Mp4Player();
		}
	}
	public void play(String fileType, String fileName) {
		if(fileType.equalsIgnoreCase("vlc")) {
			advancedMediaPlayer.playVlc(fileName);
		}else if(fileType.equalsIgnoreCase("mp4")) {
			advancedMediaPlayer.playMp4(fileName);
		}
	}
}
