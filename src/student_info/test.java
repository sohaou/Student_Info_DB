package student_info;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class test extends JFrame {

	private JPanel contentPane;
	private JTextField tF_stname;
	private JButton btn_close;
	private JTextField tF_id;
	private JTextField tF_pw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test() { //학생정보삭제함수
		String department[]= {"정보보안학과","정보통신공학과","컴퓨터공학과"}; //combobox에 입력될 학과들
		
		JFrame demodi=new JFrame();
		demodi.setTitle("학생 정보 수정_학과");
		demodi.setSize(335,250);
		demodi.setLocation(500,300);
		demodi.setVisible(true); 
		
		JPanel p8 = new JPanel();
		p8.setLayout(null);
			
		demodi.getContentPane().add(p8);
		
		JLabel lbl_de = new JLabel("> \uD604\uC7AC \uD559\uACFC : ");
		lbl_de.setFont(new Font("굴림", Font.BOLD, 14));
		lbl_de.setBounds(22, 21, 106, 22);
		p8.add(lbl_de);
		
		JLabel lblNewLabel_1 = new JLabel("> \uD604\uC7AC \uC9C0\uB3C4\uAD50\uC218 : ");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_1.setBounds(22, 53, 122, 22);
		p8.add(lblNewLabel_1);
		
		JLabel lbl_de_print = new JLabel("");
		lbl_de_print.setFont(new Font("굴림", Font.BOLD, 14));
		lbl_de_print.setBounds(163, 21, 135, 22);
		lbl_de_print.setText("컴퓨터공학과");
		p8.add(lbl_de_print);
		
		JLabel lbl_prof_print = new JLabel("");
		lbl_prof_print.setFont(new Font("굴림", Font.BOLD, 14));
		lbl_prof_print.setBounds(192, 53, 106, 22);
		lbl_prof_print.setText("나길동");
		p8.add(lbl_prof_print);
		
		JLabel lbl_modi_de = new JLabel("> \uC218\uC815 \uD6C4 \uD559\uACFC : ");
		lbl_modi_de.setFont(new Font("굴림", Font.BOLD, 14));
		lbl_modi_de.setBounds(22, 106, 116, 22);
		p8.add(lbl_modi_de);
		
		JLabel lbl = new JLabel("--------------------------------------");
		lbl.setFont(new Font("굴림", Font.PLAIN, 14));
		lbl.setBounds(12, 80, 301, 22);
		p8.add(lbl);
		
		JLabel lbl_modi_prof = new JLabel("> \uC218\uC815 \uD6C4 \uC9C0\uB3C4\uAD50\uC218 : ");
		lbl_modi_prof.setFont(new Font("굴림", Font.BOLD, 14));
		lbl_modi_prof.setBounds(22, 136, 143, 22);
		p8.add(lbl_modi_prof);
		
		JComboBox comboBox_d = new JComboBox();
		comboBox_d.setBounds(150, 105, 154, 23);
		p8.add(comboBox_d);
		
		JComboBox comboBox_p = new JComboBox();
		comboBox_p.setBounds(174, 135, 130, 23);
		p8.add(comboBox_p);
		
		DefaultComboBoxModel mo=new DefaultComboBoxModel(department);
		comboBox_d.setModel(mo);
		comboBox_d.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
			}
		});
		
		JButton btnm = new JButton("\uC218   \uC815"); //수정
		btnm.setBounds(65, 175, 80, 25);
		p8.add(btnm);
		btnm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton btnc= new JButton("\uCDE8   \uC18C"); //취소
		btnc.setBounds(174, 175, 80, 25);
		p8.add(btnc);
		btnc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				demodi.dispose();
			}
		});
	

		JButton btnsearch = new JButton("\uAC80   \uC0C9"); //검색버튼
		btnsearch.setBounds(245, 22, 70, 25);
		contentPane.add(btnsearch);
		btnsearch.addActionListener(new ActionListener() { //검색버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
	
			}
		});

		JButton btn_modify = new JButton("수   정"); //수정버튼
		btn_modify.setBounds(169, 309, 70, 25);
		contentPane.add(btn_modify);
		btn_modify.addActionListener(new ActionListener() { //수정버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
	
			}
		});

		JButton btn_close = new JButton("취   소");//취소버튼
		btn_close.setBounds(248, 309, 70, 25);
		contentPane.add(btn_close);
		btn_close.addActionListener(new ActionListener() { //취소버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
