package eventprocessing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MyFrame extends JFrame {
	JLabel lbl;
	ImageIcon icon;
	JButton btn;
	//�ִϸ��̼��� ������ ������ ����
	Thread th;
	public MyFrame() {
		//�ܼ��� �޽��� ��� - ���ϰ��� �����ϴ�.
		/*
		JOptionPane.showMessageDialog(
			this, "�޽���", "����", JOptionPane.ERROR_MESSAGE);
		*/
		
		//2�� �̻��� ��ư�� ��ġ�ؼ� confirm�� �޴� �޼ҵ�
		/*
		int result = JOptionPane.showConfirmDialog(this, "������ ����", "����", 
				JOptionPane.YES_NO_CANCEL_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			
		}else if(result == JOptionPane.NO_OPTION) {
			
		}else if(result == JOptionPane.CANCEL_OPTION) {
			
		}
		*/
		
		//�� ���� �ؽ�Ʈ�� �Է¹޴� ��ȭ���ڸ� ���
		//�Է��� ������ ���� ���¿��� Ȯ���� ������ ""
		String name = JOptionPane.showInputDialog(this, "�̸��� �Է��ϼ���", "");
		System.out.println(name);
		
		JPanel p = new JPanel();
		//JLabel lbl = new JLabel("���ڿ�");
		//�̹����� ������ �������� ����
		//�̹��� ���� ũ�� �״�� �����˴ϴ�.
		icon = new ImageIcon("C:\\Users\\admin\\Documents\\heli1.png");
		lbl = new JLabel(icon);
		p.add(lbl);
		
		//��ư�� ���� �гο� �߰�
		btn = new JButton("����");
		p.add(btn);
		
		//��ư�� ���� �� �̺�Ʈ ó���� ���� �ν��Ͻ��� ����
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				switch(arg0.getActionCommand()) {
				case "����":
					btn.setText("����");
					//�����带 �����ؼ� ����
					th = new Thread() {
						String [] images = {"heli1.png", "heli2.png", "heli3.png"};
						public void run() {
							try {
								int i=0;
								while(true) {
									//0.1�ʸ��� ���
									Thread.sleep(100);
									//������ ����
									icon = new ImageIcon(
										"C:\\Users\\admin\\Documents\\" + 
									images[i%images.length]);
									lbl.setIcon(icon);
									
									i=i+1;
								}
							}catch(InterruptedException e) {
								return;
							}
						}
					};
					th.start();
					
					break;
				case "����":
					btn.setText("����");
					//�����带 ����
					th.interrupt();
					break;
				}
				
			}
		};
		btn.addActionListener(listener);
		
		
		//���̺� ����� �÷��̸� �迭
		String [] columnNames = {"�̸�", "��ȭ��ȣ", "�ּ�"};
		//���̺� ����� ������ �迭
		String [][] data = {{"�ڹ���", "01037901997", "����� ��õ�� ��"},
				{"����ī", "01031391997", "�������� ũ���̽�óġ"}};
				
		//�����͸� ������ ���̺� ���� ����
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
				
		//���̺� ���� ������ ���̺� ����
		JTable table = new JTable(model);
		
		//���̺��� �����Ͱ� ������ �þ �� �־ ���� �����̳��� ũ�⸦ �Ѿ� �� �� �ֽ��ϴ�.
		//�ٷ� �г��̳� �����ӿ� ��ġ���� ���� JScrollPane�� ��ġ�� �� ��ġ�ϴ� ���� �����մϴ�.
		JScrollPane sp = new JScrollPane(table);
		p.add(sp);
				
		
		add(p);
		
		//�޴��� -> �޴� -> �޴�������
		JMenuBar bar = new JMenuBar();
		
		JMenu menu = new JMenu("�޴�(T)");
		//ALT + t�� ������ ����Ű�� ������ �ϸ� ����Ű�� �������� �ʽ��ϴ�.
		
		//����Ű ����
		menu.setMnemonic('t');
		
		//�޴� �׸� �����
		JMenuItem item = new JMenuItem("�޽��� ���");
		//�޴� �������� �̺�Ʈ ó���� ��ư�� �����ϴ�. ActionListener �̿�
		//Anonymous Class �ȿ��� this�� �ڱ� �ڽ��Դϴ�.
		ActionListener itemListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//���� Ŭ�������� �ܺ� Ŭ������ �ν��Ͻ��� ȣ���� ���� 
				//�ܺ�Ŭ�����̸�.this �� �մϴ�.
				//�ȵ���̵忡���� �̺�Ʈ ó���� ���� �ϰ� �޸� ������ ���ؼ�  anonymous�� ���� 
				//����ϱ� ������ �ܺ� Ŭ������ �ν��Ͻ��� ȣ���ؾ� �ϴ� ��Ȳ�� ���Ƽ�
				//�� ������ ����ؾ� �մϴ�.
				JOptionPane.showMessageDialog(MyFrame.this, "�޴��� ����");
			}
		};
		item.addActionListener(itemListener);
		item.setToolTipText("�ȳ��ϼ���");
		
		menu.add(item);
		bar.add(menu);
		this.setJMenuBar(bar);
		
		setTitle("�̹��� ���̺�");
		setLocation(100,100);
		setSize(1000,1000);
		setVisible(true);
		//���� ��� �ο�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			
	}
}








