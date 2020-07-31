package sist;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Example6 {

	public static void main(String[] args) {

		JFrame jf = new JFrame("학점 처리");
		
		JPanel jp = new JPanel();
		
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		
		JLabel jl1 = new JLabel("이름 : ");
		JTextField s = new JTextField(5);
		JLabel jl2 = new JLabel("수학점수 : ");
		JTextField s1 = new JTextField(5);
		JLabel jl3 = new JLabel("영어점수 : ");
		JTextField s2 = new JTextField(5);
		JLabel jl4 = new JLabel("국어점수 : ");
		JTextField s3 = new JTextField(5); 
		
		JTextField jtf = new JTextField(25);
		JTextArea jta = new JTextArea(5, 30);
		JScrollPane jsp = new JScrollPane(
				jta,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
				);
		
		jta.setLineWrap(true);   		// 자동 줄 바꿈 기능
		
		JButton jb1 = new JButton("계산");
		JButton jb2 = new JButton("종료");
		JButton jb3 = new JButton("취소");
		
		jp1.add(jl1); jp1.add(s); jp1.add(jl2); jp1.add(s1); jp1.add(jl3); jp1.add(s2); jp1.add(jl4); jp1.add(s3);
		jp2.add(jsp);
		jp3.add(jb1); jp3.add(jb2); jp3.add(jb3);
		
		jf.add(jp1, BorderLayout.NORTH);
		jf.add(jp2, BorderLayout.CENTER);
		jf.add(jp3, BorderLayout.SOUTH);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setBounds(100, 100, 300, 300);
		jf.setVisible(true);
		
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = s.getText();
				int mat = Integer.parseInt(s1.getText());
				int eng = Integer.parseInt(s2.getText());
				int kor = Integer.parseInt(s3.getText());
				
				int total = mat + eng + kor;
				int avg = total / 3;
				String grade = "";
				
				if (avg >= 90) {
					grade = "A";
				}else if (avg >= 80) {
					grade = "B";
				}else if (avg >= 70) {
					grade = "C";
				}else if (avg >= 60) {
					grade = "D";
				}else {
					grade = "F";
				}
				jta.append(name + " " + mat + " " + eng + " " + kor + " " + total + " " + avg + " " + grade );
				s.setText(""); s1.setText(""); s2.setText(""); s3.setText(""); 
				s1.requestFocus();
			}
		});
		
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});	
	}
}