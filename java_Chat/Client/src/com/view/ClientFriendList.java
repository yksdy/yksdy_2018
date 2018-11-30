package com.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ClientFriendList extends JFrame implements MouseListener,ActionListener{
	public static void main(String[] args){
		new ClientFriendList();
	}
	//card
	CardLayout cl = new CardLayout();
	// first card
	JPanel jphy1,jphy2,jphy3;
	JScrollPane jsphy2;
	JButton jphy1_jb1,jphy3_jb2,jphy3_jb3;
	JLabel[] jphy2_jlb;
	// second card
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JScrollPane jspmsr2;
	JButton jpmsr3_jb1,jpmsr3_jb2,jpmsr1_jb3;
	JLabel[] jpmsr2_jlb;
	public ClientFriendList(){
			
		//dispose first card	
		jphy1 = new JPanel(new BorderLayout());
		jphy2 = new JPanel(new GridLayout(50,1,4,4));
		jphy3 = new JPanel(new GridLayout(2,1,2,2));
		jsphy2 = new JScrollPane(jphy2);
		jphy1_jb1 = new JButton("我的好友");
		jphy3_jb2 = new JButton("陌生人");
		jphy3_jb2.addActionListener(this);
		jphy3_jb3 = new JButton("黑名单");
		jphy2_jlb = new JLabel[50];
		jphy1.add(jphy1_jb1,"North");
		jphy1.add(jsphy2,"Center");
		jphy1.add(jphy3,"South");
		for(int i=0;i<jphy2_jlb.length;i++){
			jphy2_jlb[i] = new JLabel(new String(""+i),new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jphy2_jlb[i].addMouseListener(this);
			jphy2.add(jphy2_jlb[i]);
		}
		jphy3.add(jphy3_jb2);
		jphy3.add(jphy3_jb3);
		//this.add(jphy1,"hy");
		
		//dispose second card
		jpmsr1 = new JPanel(new BorderLayout());
		jpmsr2 = new JPanel(new GridLayout(20,1,4,4));
		jpmsr3 = new JPanel(new GridLayout(2,1,2,2));
		jspmsr2 = new JScrollPane(jpmsr2);
		jpmsr3_jb1 = new JButton("我的好友");
		jpmsr3_jb1.addActionListener(this);
		jpmsr3_jb2 = new JButton("陌生人");
		jpmsr1_jb3 = new JButton("黑名单");
		jpmsr2_jlb = new JLabel[22];
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jspmsr2,"Center");
		jpmsr1.add(jpmsr1_jb3,"South");
		for(int i=0;i<jpmsr2_jlb.length;i++){
			jpmsr2_jlb[i] = new JLabel(new String(""+i),new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jpmsr2_jlb[i].addMouseListener(this);
			jpmsr2.add(jpmsr2_jlb[i]);
		}
		jpmsr3.add(jpmsr3_jb1);
		jpmsr3.add(jpmsr3_jb2);
//		this.add(jpmsr1,"msr"); 
		
		//dispose card
		cl = new CardLayout();
		this.setLayout(cl);
		this.add(jphy1,"hy");
		this.add(jpmsr1,"msr"); 
	
		this.setLocation(300,60);
		this.setSize(240,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==2){
			JLabel jl = (JLabel)e.getSource();
			String name = jl.getText();
			ClientChat cc = new ClientChat(name);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		System.out.println(" in ");
		jl.setForeground(Color.red);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.black);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jphy3_jb2){
			cl.show(this.getContentPane(), "msr");
			System.out.println(" msr ");
		}
		else
		if(e.getSource()==jpmsr3_jb1){
			cl.show(this.getContentPane(), "hy");	
		}	
	}
}
