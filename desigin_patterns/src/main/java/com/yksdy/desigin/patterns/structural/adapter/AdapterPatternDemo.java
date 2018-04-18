package com.yksdy.desigin.patterns.structural.adapter;

public class AdapterPatternDemo {
	public static void main(String[] args) {
		AudioPlay audioPlayer = new AudioPlay();

		audioPlayer.play("mp3", "beyond the horizon.mp3");
		audioPlayer.play("mp4", "alone.mp4");
		audioPlayer.play("vlc", "far far away.vlc");
	}
}
