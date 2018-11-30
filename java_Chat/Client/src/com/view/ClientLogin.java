package com.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ClientLogin extends JFrame implements ActionListener{
	//North element
	JLabel jlab;
	//Center element
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4;
	JLabel jp2_jb1,jp2_jb2,jp2_jb3,jp2_jb4;
	JTextField jp2_jt;
	JPasswordField jp2_jp;
	JButton jp2_jb;
	JCheckBox jp2_jcb1,jp2_jcb2;
	//South element
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	public static void main(String[] args){
		new ClientLogin();
	}
	
	public ClientLogin(){
		//dispose North
		jlab = new JLabel(new ImageIcon("image/tou.gif"));
		this.add(jlab,"North");
		//dispose Center
		jp2_jb1 = new JLabel("号码",JLabel.CENTER);
		jp2_jb2 = new JLabel("密码",JLabel.CENTER);
		jp2_jb3 = new JLabel("忘记密码",JLabel.CENTER);
		jp2_jb3.setForeground(Color.blue);
		jp2_jb4 = new JLabel("密码保护",JLabel.CENTER);
		jp2_jt = new JTextField();
		jp2_jp = new JPasswordField();
		jp2_jb = new JButton(new ImageIcon("image/clear.gif"));
		jp2_jcb1 = new JCheckBox("隐身登陆");
		jp2_jcb2 = new JCheckBox("记住密码");
		jp2 = new JPanel(new GridLayout(3,3));
		jp2.add(jp2_jb1);
		jp2.add(jp2_jt);
		jp2.add(jp2_jb);
		jp2.add(jp2_jb2);
		jp2.add(jp2_jp);
		jp2.add(jp2_jb3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jb4);
		jtp = new JTabbedPane();
		jtp.add(jp2,"号码");
		jp3 = new JPanel();
		jp4 = new JPanel();	
		jtp.add(jp3,"手机号码");
		jtp.add(jp4,"电子邮件");
		this.add(jtp);
		//dispose South
		jp1 = new JPanel();
		jp1_jb1 = new JButton(new ImageIcon("image/denglu.gif"));
		jp1_jb2 = new JButton(new ImageIcon("image/quxiao.gif"));
		jp1_jb3 = new JButton(new ImageIcon("image/xiangdao.gif"));		
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);	
		this.add(jp1,"South");
			
		this.setLocation(500,100);
		this.setSize(420,480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jp1_jb1){
			
		}
	}
}

