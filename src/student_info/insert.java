package student_info;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class insert extends student_info{
	int xx;
	int yy;
	String []b=new String[6];
	
	insert() { //등록함수	
		String department[]= {"정보보안학과","정보통신공학과","컴퓨터공학과"}; //combobox에 입력될 학과들
		
		JFrame ijf=new JFrame();
		ijf.setTitle("학생 정보 등록");
		ijf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ijf.setBounds(100, 100, 353, 405);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		ijf.setContentPane(contentPane);
		contentPane.setLayout(null);
		ijf.setVisible(true);
		
		JLabel lbl_name = new JLabel("* \uC774     \uB984 : ");
		lbl_name.setFont(new Font("굴림", Font.PLAIN, 15));
		lbl_name.setBounds(27, 27, 84, 25);
		contentPane.add(lbl_name);
		
		JLabel lbl_gender = new JLabel("* \uC131     \uBCC4 : ");
		lbl_gender.setFont(new Font("굴림", Font.PLAIN, 15));
		lbl_gender.setBounds(27, 122, 84, 25);
		contentPane.add(lbl_gender);
		
		JLabel lbl_department = new JLabel("* 전     공 : ");
		lbl_department.setFont(new Font("굴림", Font.PLAIN, 15));
		lbl_department.setBounds(27, 170, 84, 25);
		contentPane.add(lbl_department);
		
		JLabel lbl_prof = new JLabel("* \uC9C0\uB3C4\uAD50\uC218 : ");
		lbl_prof.setFont(new Font("굴림", Font.PLAIN, 15));
		lbl_prof.setBounds(27, 218, 84, 25);
		contentPane.add(lbl_prof);
		
		JLabel lbl_student_num = new JLabel("* \uD559     \uBC88 : ");
		lbl_student_num.setFont(new Font("굴림", Font.PLAIN, 15));
		lbl_student_num.setBounds(27, 73, 84, 25);
		contentPane.add(lbl_student_num);
		
		JTextField tF_name = new JTextField();
		tF_name.setBounds(118, 25, 161, 25);
		contentPane.add(tF_name);
		tF_name.setColumns(10);
		
		JTextField tF_st_num = new JTextField();
		tF_st_num.setColumns(10);
		tF_st_num.setBounds(118, 73, 161, 25);
		contentPane.add(tF_st_num);
		
		JRadioButton rdbtn_m = new JRadioButton("\uB0A8");
		rdbtn_m.setFont(new Font("굴림", Font.PLAIN, 15));
		rdbtn_m.setBounds(129, 123, 39, 23);
		contentPane.add(rdbtn_m);
		
		JRadioButton rdbtn_w = new JRadioButton("\uC5EC");
		rdbtn_w.setFont(new Font("굴림", Font.PLAIN, 15));
		rdbtn_w.setBounds(199, 123, 39, 23);
		contentPane.add(rdbtn_w);
		
		ButtonGroup bg_gender=new ButtonGroup();//현금,카드 라디오 버튼 그룹화
		bg_gender.add(rdbtn_m); 
		bg_gender.add(rdbtn_w);
		
		comboBox_p = new JComboBox(); 
		comboBox_p.setBounds(118, 219, 161, 23);
		contentPane.add(comboBox_p);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(118, 168, 161, 23);
		contentPane.add(comboBox);
		DefaultComboBoxModel model3=new DefaultComboBoxModel(department);
		comboBox.setModel(model3);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb=(JComboBox)e.getSource();
				int indexx = jcb.getSelectedIndex();
				
				if(indexx==0) {//정보보안
					xx=0;
					int y=0;
					for(int i=0;i<secu.length;i++) {
						if(secu[i][0]!=null && secu[i][0].isEmpty()==false) {
							b[y]=secu[i][0];
							y++;
						}
					}
					DefaultComboBoxModel mod=new DefaultComboBoxModel(b);
					comboBox_p.setModel(mod);
				}
				else if(indexx==1) {//정보통신
					xx=1;
					int x=0;
					for(int i=0;i<tele.length;i++) {
						if(tele[i][0]!=null && tele[i][0].isEmpty()==false) {
							b[x]=tele[i][0];
							x++;
						}
					}
					DefaultComboBoxModel model=new DefaultComboBoxModel(b);
					comboBox_p.setModel(model);
				}
				else if(indexx==2) {//컴퓨터
					xx=2;
					int a=0;
					for(int i=0;i<com.length;i++) {
						if(com[i][0]!=null && com[i][0].isEmpty()==false) {
							b[a]=com[i][0];
							a++;
						}
					}
					DefaultComboBoxModel model=new DefaultComboBoxModel(b);
					comboBox_p.setModel(model);
				}
				comboBox_p.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JComboBox jc=(JComboBox)e.getSource();
						int index = jc.getSelectedIndex();
						yy=index;
					}
				});
			}
		});
		

		JLabel lbl_pnum = new JLabel("* \uD734\uB300\uC804\uD654 : ");
		lbl_pnum.setFont(new Font("굴림", Font.PLAIN, 15));
		lbl_pnum.setBounds(27, 266, 84, 25);
		contentPane.add(lbl_pnum);
		
		
		JLabel lbl_numset = new JLabel("010 - ");
		lbl_numset.setFont(new Font("굴림", Font.PLAIN, 15));
		lbl_numset.setBounds(118, 272, 50, 15);
		contentPane.add(lbl_numset);
		
		JTextField tF_pnum = new JTextField();
		tF_pnum.setColumns(10);
		tF_pnum.setBounds(160, 268, 50, 25);
		contentPane.add(tF_pnum);
		
		JLabel lbl_ = new JLabel(" - ");
		lbl_.setFont(new Font("굴림", Font.PLAIN, 15));
		lbl_.setBounds(210, 268, 50, 25);
		contentPane.add(lbl_);
		
		JTextField tF_pnum2 = new JTextField();
		tF_pnum2.setColumns(10);
		tF_pnum2.setBounds(230, 268, 50, 25);
		contentPane.add(tF_pnum2);
		
		JButton btn_insert = new JButton("\uC800     \uC7A5");
		btn_insert.setFont(new Font("굴림", Font.PLAIN, 15));
		btn_insert.setBounds(56, 318, 88, 26);
		contentPane.add(btn_insert);
		btn_insert.addActionListener(new ActionListener() { //등록버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
			
				if(tF_name.getText().equals("") || tF_st_num.getText().equals("") || rdbtn_m.isSelected()==false && rdbtn_w.isSelected()==false ||
						tF_pnum.getText().equals("")) {
					if(tF_name.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "이름을 입력해주세요!");
					}
					else if(tF_st_num.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "학번을 입력해주세요!");
					}
					else if(rdbtn_m.isSelected()==false && rdbtn_w.isSelected()==false) {
						JOptionPane.showMessageDialog(null, "성별을 선택해주세요!");
					}
					else if(tF_pnum.getText().equals("") || tF_pnum2.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "휴대전화를 입력해주세요!");
					}
				}
				else {
					if(xx==0) {
						de="정보보안학과";
						for(int i=0;i<secu.length;i++) {
							if(b[yy].equals(secu[i][0])) {
								prof=Integer.parseInt(secu[i][1]);
								break;
							}
						}
					}
					else if(xx==1) {
						de="정보통신공학과";
						for(int i=0;i<tele.length;i++) {
							if(b[yy].equals(tele[i][0])) {
								prof=Integer.parseInt(tele[i][1]);
								break;
							}
						}
					}
					else if(xx==2) {
						de="컴퓨터공학과";
						for(int i=0;i<com.length;i++) {
							if(b[yy].equals(com[i][0])) {
								prof=Integer.parseInt(com[i][1]);
								break;
							}
						}
					}
					if(rdbtn_m.isSelected()==true) {
						gender="남";
					}
					if(rdbtn_w.isSelected()==true) {
						gender="여";
					}

					DBcon();
					try {
				         String sql = "INSERT INTO student (name, s_num, gender, department, p_num, profno) values(?, ?, ?, ?, ?, ?)";
				         
				         ps = conn.prepareStatement(sql);
				         ps.setString(1, tF_name.getText());
				         ps.setString(2, tF_st_num.getText());
				         ps.setString(3, gender);
				         ps.setString(4, de);
				         ps.setString(5, "010-"+tF_pnum.getText()+"-"+tF_pnum2.getText());
				         ps.setInt(6, prof);
				         
				         ps.executeUpdate();
				         JOptionPane.showMessageDialog(null, "저장완료");
							ijf.dispose();//등록 프레임 닫기
				      } catch (SQLException e1) {
				         e1.printStackTrace();
				         JOptionPane.showMessageDialog(null, "등록실패");
				      } finally {
				         dbclose();
				      }
				}
			}
			
		});
		
		JButton btn_c = new JButton("\uCDE8     \uC18C"); //취소버튼
		btn_c.setFont(new Font("굴림", Font.PLAIN, 15));
		btn_c.setBounds(199, 318, 88, 26);
		contentPane.add(btn_c);
		btn_c.addActionListener(new ActionListener() { //취소버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
				ijf.dispose();//등록 프레임 닫기
			}
		});
	}
}
