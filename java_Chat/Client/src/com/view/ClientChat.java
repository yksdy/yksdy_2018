package com.view;

import javax.swing.*;
import java.awt.*;
public class ClientChat extends JFrame{
	public static void main(String[] args){
		new ClientChat("xiaoming");
	}
	JTextArea jta;
	JPanel jp;
	JTextField jtf;
	JButton jb;
	public ClientChat(String name){
		jta = new JTextArea();
		jp = new JPanel();
		jtf = new JTextField(20);
		jb = new JButton("·¢ËÍ");
		jp.add(jtf);
		jp.add(jb);
		this.setTitle("I'm talk with "+name);
		this.setIconImage(new ImageIcon("image/title.gif").getImage());
		this.add(jta,"Center");
		this.add(jp,"South");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(400,100);
		this.setSize(400,300);
	}
}
