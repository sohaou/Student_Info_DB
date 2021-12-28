package student_info;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class student_info extends JFrame{
	//public JFrame si=new JFrame();
	private JPanel contentPane;
	JComboBox comboBox_p; 
	String gender; //성별
	String de=null; //학과
	int prof=0; //지도교수
	String sql;
	String[][] com=new String[40][2]; //컴퓨터공학과 교수들의 이름 저장 배열
	String[][] tele=new String[40][2]; //정보통신공학과 교수들의 이름 저장 배열
	String[][] secu=new String[40][2]; //정보보안학과 교수들의 이름 저장 배열
	Connection conn = null;
	ResultSet rs = null;
	Statement st = null; 
	PreparedStatement ps = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame login_in=new JFrame();
					login_in.setTitle("LOGIN");
					login_in.setSize(326,188);
					login_in.setLocation(500,300);
						
					JPanel p8 = new JPanel();
					p8.setLayout(null);
						
					login_in.getContentPane().add(p8);
					
					JLabel lblNewLabel = new JLabel("ID : ");
					lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
					lblNewLabel.setBounds(84, 30, 30, 25);
					p8.add(lblNewLabel);
					
					JLabel lblPassword = new JLabel("Password : ");
					lblPassword.setFont(new Font("굴림", Font.BOLD, 12));
					lblPassword.setBounds(32, 65, 102, 25);
					p8.add(lblPassword);
					
					JTextField tF_id = new JTextField();
					tF_id.setBounds(120, 30, 154, 21);
					p8.add(tF_id);
					tF_id.setColumns(10);
					
					JPasswordField tF_pw = new JPasswordField();
					tF_pw.setColumns(10);
					tF_pw.setBounds(120, 65, 154, 21);
					p8.add(tF_pw);
					
					JButton btnreset = new JButton("\uCD08\uAE30\uD654");
					btnreset.setBounds(72, 110, 78, 23);
					p8.add(btnreset);
					btnreset.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							tF_id.setText("");
							tF_pw.setText("");
						}
					});
					
					JButton btnlogin = new JButton("\uB85C\uADF8\uC778");
					btnlogin.setBounds(185, 110, 78, 23);
					p8.add(btnlogin);
					btnlogin.addActionListener(new ActionListener() {
						@SuppressWarnings("unlikely-arg-type")
						public void actionPerformed(ActionEvent e) {
							if(tF_id.getText().equals("seowon") && new String(tF_pw.getPassword()).equals("1234")) {
								JOptionPane.showMessageDialog(null, "로그인 성공..");
								login_in.dispose();
								student_info frame = new student_info();
								frame.setVisible(true);
							}
							else if(tF_id.getText().equals("seowon")==false){
								JOptionPane.showMessageDialog(null, "아이디가 일치하지 않습니다!\n로그인 실패..");
							}
							else if(new String(tF_pw.getPassword()).equals("1234")==false){
								JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다!\n로그인 실패..");
							}
							else if(tF_id.getText().equals("seowon")==false && new String(tF_pw.getPassword()).equals("1234")==false){
								JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다!\n로그인 실패..");
							}
						}
					});
					login_in.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public void DBcon() {//db연결함수
		try{
			String user = "root";
		    String pw = "1234";
		    String url = "jdbc:mysql://localhost:3306/studentinfo?characterEncoding=UTF-8&serverTimezone=UTC";
		         
		    Class.forName("com.mysql.jdbc.Driver");
		    // connection으로 db와 연결 (객체 생성)
		    conn = DriverManager.getConnection(url, user, pw);
		    st=conn.createStatement();
		    
		    } catch (ClassNotFoundException cnfe) {
		    	System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		    } catch (SQLException sqle) {
		    	System.out.println("DB 접속실패 : " + sqle.toString());
		    } catch (Exception e1) {
		        System.out.println("Unkonwn error");
		  e1.printStackTrace();
		}		
	}
	
	public void dbclose() {
		try {
			if (rs != null)
	           rs.close();
	        if (st != null)
	           st.close();
	        if (ps != null)
	           ps.close();
	    } catch (Exception e) {
	      System.out.println(e + "=> dbClose fail");
	    }
	}
	
	public void dbread() {
		try{
			DBcon();
		    sql="SELECT name, profno FROM professor WHERE department='컴퓨터공학과'";
			ResultSet rs=st.executeQuery(sql);
			int i=0;
		    while(rs.next()) {
		    	com[i][0]=rs.getString(1);
		    	com[i][1]=Integer.toString(rs.getInt(2));
		    	i++;
		    }	
		    conn.close();
		    } catch (SQLException sqle) {
		    	System.out.println("DB 접속실패 : " + sqle.toString());
		    } catch (Exception e) {
		        System.out.println("Unkonwn error");
		  e.printStackTrace();
		}
	}
	public void dbread_s() {//정보보안학과
		try{
			DBcon();
		    sql="SELECT name, profno FROM professor WHERE department='정보보안학과'";
			ResultSet rs=st.executeQuery(sql);
			int i=0;
		    while(rs.next()) {
		    	secu[i][0]=rs.getString(1);
		    	secu[i][1]=Integer.toString(rs.getInt(2));
		    	i++;
		    }	
		    conn.close();
		    } catch (SQLException sqle) {
		    	System.out.println("DB 접속실패 : " + sqle.toString());
		    } catch (Exception e) {
		        System.out.println("Unkonwn error");
		  e.printStackTrace();
		}
	}
	public void dbread_t() { //정보통신
		try{
			DBcon();
		    sql="SELECT name, profno FROM professor WHERE department='정보통신공학과'";
			ResultSet rs=st.executeQuery(sql);
			int i=0;
		    while(rs.next()) {
		    	tele[i][0]=rs.getString(1);
		    	tele[i][1]=Integer.toString(rs.getInt(2));
		    	i++;
		    }	
		    conn.close();
		    } catch (SQLException sqle) {
		    	System.out.println("DB 접속실패 : " + sqle.toString());
		    } catch (Exception e) {
		        System.out.println("Unkonwn error");
		  e.printStackTrace();
		}
	}
	
	student_info() {
		setTitle("학생 정보 관리 프로그램");
		//getContentPane().setLayout(cards);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//setVisible(true);
		dbread();
		dbread_s();
		dbread_t();
		
		JButton insert_bt = new JButton("등      록"); //등록버튼
		insert_bt.setBounds(155, 29, 166, 35);
		contentPane.add(insert_bt);
		insert_bt.addActionListener(new ActionListener() { //등록버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
				new insert();
			}
		});
		
		JButton search_bt = new JButton("검      색");
		search_bt.setBounds(155, 91, 166, 35);
		contentPane.add(search_bt);
		search_bt.addActionListener(new ActionListener() { //검색버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
				new search();
			}
		});
		
		JButton modify_bt = new JButton("수     정");
		modify_bt.setBounds(155, 151, 166, 35);
		contentPane.add(modify_bt);
		modify_bt.addActionListener(new ActionListener() { //수정버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
				new modify();
			}
		});
		
		JButton delete_bt = new JButton("삭      제");
		delete_bt.setBounds(155, 213, 166, 35);
		contentPane.add(delete_bt);
		delete_bt.addActionListener(new ActionListener() { //삭제버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
				new delete();
			}
		});
		
		JButton prof_bt = new JButton("각 교수 지도 학생 목룍");
		prof_bt.setBounds(155, 274, 166, 35);
		contentPane.add(prof_bt);
		prof_bt.addActionListener(new ActionListener() { //지도학생출력
			public void actionPerformed(ActionEvent e) {
				new print_prof();
			}
		});
		
		JButton exit_bt = new JButton("종    료");
		exit_bt.setBounds(396, 305, 80, 25);
		contentPane.add(exit_bt);
		exit_bt.addActionListener(new ActionListener() { //종료버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
				JFrame f3=new JFrame();

				f3.setTitle("종료확인");
				f3.setSize(300,150);
				f3.setLocation(500,300);
					
				JPanel p4 = new JPanel();
				p4.setLayout(null);
					
				JLabel l = new JLabel("정말 종료하시겠습니까?");
				l.setBounds(60,15,200,32);
				p4.add(l);
					
				JButton c=new JButton("취 소");
				c.setFont(new Font("HY나무L",Font.BOLD,13));
				c.setBounds(50,65,90,32);
				p4.add(c);
					
				JButton e1=new JButton("종 료");
				e1.setFont(new Font("HY나무L",Font.BOLD,13));
				e1.setBounds(130,65,90,32);
				p4.add(e1);
					
				f3.add(p4);
				f3.setVisible(true);
					
				c.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						f3.dispose();
					}
				});
				e1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						f3.dispose();
						dispose();
					}
				});
			}			
		});
	}
}
