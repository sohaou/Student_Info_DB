package student_info;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class modify extends student_info{
	String msql;
	String msql1;
	String modi_c;
	String md;
	String department1;
	String professor1;
	
	String []cv=new String[6];
	
	int aa;
	int bb;
	
	int mp=0;
	
	modify() { //수정함수
		String modi[]= {"이름","학과","휴대전화"};
		
		JFrame mjf=new JFrame();
		mjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mjf.setBounds(100, 100, 353, 385);
		mjf.setTitle("학생 정보 수정");
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mjf.setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setLayout(null);
		mjf.setVisible(true);
		
		JLabel lbl_snum = new JLabel(">> \uD559  \uBC88 : "); //학번 입력 라벨
		lbl_snum.setFont(new Font("굴림", Font.BOLD, 12));
		lbl_snum.setBounds(12, 24, 86, 25);
		contentPane.add(lbl_snum);
		
		JTextField tF_snum = new JTextField(); //검색하고자 하는 학생의 학번을 입력하는 필드
		tF_snum.setBounds(100, 22, 133, 25);
		contentPane.add(tF_snum);
		tF_snum.setColumns(10);
		
		JPanel panel = new JPanel(); //검색한 학생 정보를 띄우는 패널
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 59, 303, 207);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbln = new JLabel("> \uC774     \uB984 : "); //검색한 학생 이름
		lbln.setFont(new Font("굴림", Font.BOLD, 12));
		lbln.setBounds(12, 10, 86, 23);
		panel.add(lbln);
		
		JLabel lblstn = new JLabel("> \uD559     \uBC88 : "); //검색한 학생 학번
		lblstn.setFont(new Font("굴림", Font.BOLD, 12));
		lblstn.setBounds(12, 43, 86, 23);
		panel.add(lblstn);
		
		JLabel lblgen = new JLabel("> \uC131     \uBCC4 : "); //검색한 학생 성별
		lblgen.setFont(new Font("굴림", Font.BOLD, 12));
		lblgen.setBounds(12, 76, 86, 23);
		panel.add(lblgen);
		
		JLabel lblde = new JLabel("> \uD559     \uACFC : "); //검색한 학생 학과
		lblde.setFont(new Font("굴림", Font.BOLD, 12));
		lblde.setBounds(12, 109, 86, 23);
		panel.add(lblde);
		
		JLabel lblprof = new JLabel("> 지도교수 : ");//검색한 학생 지도교수
		lblprof.setFont(new Font("굴림", Font.BOLD, 12));
		lblprof.setBounds(12, 175, 86, 23);
		panel.add(lblprof);
		
		JLabel print_name = new JLabel("");//검색한 학생 이름 출력 라벨
		print_name.setFont(new Font("굴림", Font.BOLD, 12));
		print_name.setBounds(110, 10, 166, 23);
		panel.add(print_name);
		
		JLabel print_stnum = new JLabel("");//검색한 학생 학번 출력 라벨
		print_stnum.setFont(new Font("굴림", Font.BOLD, 12));
		print_stnum.setBounds(110, 43, 166, 23);
		panel.add(print_stnum);
		
		JLabel print_gender = new JLabel("");//검색한 학생 성별 출력 라벨
		print_gender.setFont(new Font("굴림", Font.BOLD, 12));
		print_gender.setBounds(110, 76, 166, 23);
		panel.add(print_gender);
		
		JLabel print_department = new JLabel("");//검색한 학생 학과 출력 라벨
		print_department.setFont(new Font("굴림", Font.BOLD, 12));
		print_department.setBounds(110, 109, 166, 23);
		panel.add(print_department);
		
		JLabel print_prof = new JLabel("");//검색한 학생 지도교수 출력 라벨
		print_prof.setFont(new Font("굴림", Font.BOLD, 12));
		print_prof.setBounds(110, 175, 166, 23);
		panel.add(print_prof);
		
		JLabel lblpnum = new JLabel("> 휴대전화 : ");
		lblpnum.setFont(new Font("굴림", Font.BOLD, 12));
		lblpnum.setBounds(12, 142, 86, 23);
		panel.add(lblpnum);

		JLabel print_pnum = new JLabel("");
		print_pnum.setFont(new Font("굴림", Font.BOLD, 12));
		print_pnum.setBounds(110, 142, 166, 23);
		panel.add(print_pnum);

		JLabel lbl_modify = new JLabel(">> \uC218\uC815\uD560 \uC815\uBCF4 : ");//수정할 정보 선택 라벨
		lbl_modify.setFont(new Font("굴림", Font.BOLD, 12));
		lbl_modify.setBounds(14, 276, 114, 23);
		contentPane.add(lbl_modify);
		
		JTextField tF_modify = new JTextField(); //수정 내용 입력 필드
		tF_modify.setColumns(10);
		tF_modify.setBounds(12, 309, 153, 25);
		contentPane.add(tF_modify);
		
		JComboBox comboBox_modi=new JComboBox(modi);
		comboBox_modi.setBounds(140, 276, 143, 23);  //수정 원하는 내용 선택 콤보박스
		contentPane.add(comboBox_modi);
		DefaultComboBoxModel model=new DefaultComboBoxModel(modi);
		comboBox_modi.setModel(model);
		comboBox_modi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox jc=(JComboBox)e.getSource();
				int index = jc.getSelectedIndex();
				modi_c=modi[index];
				if (index==1) {
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
					lbl_de.setFont(new Font("굴림", Font.BOLD, 12));
					lbl_de.setBounds(22, 21, 106, 22);
					p8.add(lbl_de);
					
					JLabel lblNewLabel_1 = new JLabel("> \uD604\uC7AC \uC9C0\uB3C4\uAD50\uC218 : ");
					lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
					lblNewLabel_1.setBounds(22, 53, 122, 22);
					p8.add(lblNewLabel_1);
					
					JLabel lbl_de_print = new JLabel("");
					lbl_de_print.setFont(new Font("굴림", Font.BOLD, 12));
					lbl_de_print.setBounds(163, 21, 135, 22);
					lbl_de_print.setText(department1);
					p8.add(lbl_de_print);
					
					JLabel lbl_prof_print = new JLabel("");
					lbl_prof_print.setFont(new Font("굴림", Font.BOLD, 12));
					lbl_prof_print.setBounds(192, 53, 106, 22);
					lbl_prof_print.setText(professor1);
					p8.add(lbl_prof_print);
					
					JLabel lbl_modi_de = new JLabel("> \uC218\uC815 \uD6C4 \uD559\uACFC : ");
					lbl_modi_de.setFont(new Font("굴림", Font.BOLD, 12));
					lbl_modi_de.setBounds(22, 106, 116, 22);
					p8.add(lbl_modi_de);
					
					JLabel lbl = new JLabel("  -----------------------------------");
					lbl.setFont(new Font("굴림", Font.PLAIN, 12));
					lbl.setBounds(12, 80, 301, 22);
					p8.add(lbl);
					
					JLabel lbl_modi_prof = new JLabel("> \uC218\uC815 \uD6C4 \uC9C0\uB3C4\uAD50\uC218 : ");
					lbl_modi_prof.setFont(new Font("굴림", Font.BOLD, 12));
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
							JComboBox jcb=(JComboBox)e.getSource();
							int indexx = jcb.getSelectedIndex();

							if(indexx==0) {//정보보안
								aa=0;
								int y=0;
								for(int i=0;i<secu.length;i++) {
									if(secu[i][0]!=null && secu[i][0].isEmpty()==false) {
										cv[y]=secu[i][0];
										y++;
									}
								}
								DefaultComboBoxModel mod=new DefaultComboBoxModel(cv);
								comboBox_p.setModel(mod);
							}
							else if(indexx==1) {//정보통신
								aa=1;
								int x=0;
								for(int i=0;i<tele.length;i++) {
									if(tele[i][0]!=null && tele[i][0].isEmpty()==false) {
										cv[x]=tele[i][0];
										x++;
									}
								}
								DefaultComboBoxModel model=new DefaultComboBoxModel(cv);
								comboBox_p.setModel(model);
							}
							else if(indexx==2) {//컴퓨터
								aa=2;
								int a=0;
								for(int i=0;i<com.length;i++) {
									if(com[i][0]!=null && com[i][0].isEmpty()==false) {
										cv[a]=com[i][0];
										a++;
									}
								}
								DefaultComboBoxModel model=new DefaultComboBoxModel(cv);
								comboBox_p.setModel(model);
							}
							comboBox_p.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									JComboBox jc=(JComboBox)e.getSource();
									int index = jc.getSelectedIndex();
									bb=index;
								}
							});
						}
					});
					
					JButton btnm = new JButton("\uC218   \uC815"); //수정
					btnm.setBounds(43, 175, 80, 25);
					p8.add(btnm);
					btnm.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(aa==0) {
								md="정보보안학과";
								for(int i=0;i<secu.length;i++) {
									if(cv[bb].equals(secu[i][0])) {
										mp=Integer.parseInt(secu[i][1]);
										break;
									}
								}
							}
							else if(aa==1) {
								md="정보통신공학과";
								for(int i=0;i<tele.length;i++) {
									if(cv[bb].equals(tele[i][0])) {
										mp=Integer.parseInt(tele[i][1]);
										break;
									}
								}
							}
							else if(aa==2) {
								md="컴퓨터공학과";
								for(int i=0;i<com.length;i++) {
									if(cv[bb].equals(com[i][0])) {
										mp=Integer.parseInt(com[i][1]);
										break;
									}
								}
							}
							msql1="UPDATE student set department='"+md+"',profno="+mp+" where s_num='"+tF_snum.getText()+"'";
						
			
							DBcon();
							@SuppressWarnings("unused")
							int rss;
							try {
								rss = st.executeUpdate(msql1);
							    conn.close();
							    JOptionPane.showMessageDialog(null, "정보 수정이 완료되었습니다!");
							    demodi.dispose();
							    mjf.dispose();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "정보 수정에 실패하였습니다..");
							}
						}
					});
					
					JButton btnc= new JButton("\uCDE8   \uC18C"); //취소
					btnc.setBounds(152, 175, 80, 25);
					p8.add(btnc);
					btnc.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							demodi.dispose();
						}
					});
				}
			}
		});
		
		JButton btnsearch = new JButton("\uAC80   \uC0C9"); //검색버튼
		btnsearch.setBounds(245, 22, 70, 25);
		contentPane.add(btnsearch);
		btnsearch.addActionListener(new ActionListener() { //검색버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
				DBcon();
				msql="SELECT s.name,s.s_num,s.gender,s.department,s.p_num,p.name FROM student s inner join professor p on s.profno=p.profno WHERE s.s_num='"+tF_snum.getText()+"'";
				ResultSet rs;
				try {
					rs = st.executeQuery(msql);
					int i=0;
				    while(rs.next()) {
				    	print_name.setText(rs.getString(1));
				    	print_stnum.setText(rs.getString(2));
				    	print_gender.setText(rs.getString(3));
				    	print_department.setText(rs.getString(4));
				    	print_pnum.setText(rs.getString(5));
				    	print_prof.setText(rs.getString(6));
				    	department1=rs.getString(4);
				    	professor1=rs.getString(6);
						i++;
				    }	
				    conn.close();
				    if(i==0) {
				    	print_name.setText("");
				    	print_stnum.setText("");
				    	print_gender.setText("");
				    	print_department.setText("");
				    	print_pnum.setText("");
				    	print_prof.setText("");
				    	JOptionPane.showMessageDialog(null, "해당 학번의 학생이 존재하지 않습니다!\n");	
				    }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btn_modify = new JButton("수   정"); //수정버튼
		btn_modify.setBounds(169, 309, 70, 25);
		contentPane.add(btn_modify);
		btn_modify.addActionListener(new ActionListener() { //수정버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
				if(tF_modify.getText().isEmpty()==true&&modi_c.contains("학과")==false) {
					JOptionPane.showMessageDialog(null, "수정 내용을 입력해주세요!\n");	
				}
				else {
					if(modi_c.equals("이름")) {
						DBcon();
						msql1="UPDATE student set name='"+tF_modify.getText()+"' where s_num='"+tF_snum.getText()+"'";
					    int rss;
						try {
							rss = st.executeUpdate(msql1);
						    conn.close();
						    JOptionPane.showMessageDialog(null, "정보 수정이 완료되었습니다!");
						    mjf.dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "정보 수정에 실패하였습니다..");
						}
					}
					else if(modi_c.equals("학과")) { 
						
					}
					else if(modi_c.equals("휴대전화")) {
						DBcon();
						msql1="UPDATE student set p_num='"+tF_modify.getText()+"' where s_num='"+tF_snum.getText()+"'";
					    int rss;
						try {
							rss = st.executeUpdate(msql1);
						    conn.close();
						    JOptionPane.showMessageDialog(null, "정보 수정이 완료되었습니다!");
						    mjf.dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "정보 수정에 실패하였습니다..");
						}
					}
				}
			}
		});
		
		JButton btn_close = new JButton("취   소");//취소버튼
		btn_close.setBounds(248, 309, 70, 25);
		contentPane.add(btn_close);
		btn_close.addActionListener(new ActionListener() { //취소버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
				mjf.dispose();
			}
		});
	}
}
