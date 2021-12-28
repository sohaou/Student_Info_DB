package student_info;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class print_prof extends student_info{
	String department[]= {"정보보안학과","정보통신공학과","컴퓨터공학과"}; //combobox에 입력될 학과들
	String sql;
	JComboBox comboBox_prof;
	JComboBox comboBox_de;
	
	print_prof(){
		JFrame pf=new JFrame();
		pf.setTitle("지도 학생 목록");
		pf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pf.setBounds(100, 100, 603, 350);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		pf.setContentPane(contentPane);
		contentPane.setLayout(null);
		pf.setVisible(true);
		String[] data=new String[6];//테이블에 값을 넣기위한 배열
		
		String header[]= {"    이름  ","     학번     ","     성별    " ,"     학과        ","   휴대전화   ","   지도교수  "};
		DefaultTableModel model = new DefaultTableModel(header,0);
		JTable table = new JTable(model);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(table);
		
		DefaultTableCellRenderer dtc=new DefaultTableCellRenderer();
		dtc.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tc=table.getColumnModel();
		for(int i=0;i<tc.getColumnCount();i++) {
			tc.getColumn(i).setCellRenderer(dtc);
		}
		contentPane.add(scrollPane);
		scrollPane.setBounds(12, 52, 565, 214);
		
		JButton btn_close = new JButton("\uB2EB    \uAE30");
		btn_close.setFont(new Font("굴림", Font.PLAIN, 12));
		btn_close.setBounds(497, 276, 80, 27);
		btn_close.addActionListener(new ActionListener() { //검색버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
				pf.dispose();
			}
		});
		contentPane.add(btn_close);	
		
		JLabel lbl_de = new JLabel("> 학과명 : ");
		lbl_de.setFont(new Font("굴림", Font.BOLD, 11));
		lbl_de.setBounds(27, 19, 54, 27);
		contentPane.add(lbl_de);
		
		JComboBox comboBox_prof = new JComboBox();
		comboBox_prof.setBounds(295, 19, 102, 27);
		contentPane.add(comboBox_prof);
		
		JComboBox comboBox_de = new JComboBox();
		comboBox_de.setBounds(85, 19, 109, 27);
		contentPane.add(comboBox_de);
		DefaultComboBoxModel mo=new DefaultComboBoxModel(department);
		comboBox_de.setModel(mo);
		comboBox_de.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb=(JComboBox)e.getSource();
				int indexx = jcb.getSelectedIndex();
				String []b=new String[5];
				
				if(indexx==0) {//정보보안
					int y=0;
					for(int i=0;i<secu.length;i++) {
						if(secu[i][0]!=null && secu[i][0].isEmpty()==false) {
							b[y]=secu[i][0];
							y++;
						}
					}
					DefaultComboBoxModel mod=new DefaultComboBoxModel(b);
					comboBox_prof.setModel(mod);
				}
				else if(indexx==1) {//정보통신
					int x=0;
					for(int i=0;i<tele.length;i++) {
						if(tele[i][0]!=null && tele[i][0].isEmpty()==false) {
							b[x]=tele[i][0];
							x++;
						}
					}
					DefaultComboBoxModel model=new DefaultComboBoxModel(b);
					comboBox_prof.setModel(model);
				}
				else if(indexx==2) {//컴퓨터
					int a=0;
					for(int i=0;i<com.length;i++) {
						if(com[i][0]!=null && com[i][0].isEmpty()==false) {
							b[a]=com[i][0];
							a++;
						}
					}
					DefaultComboBoxModel model=new DefaultComboBoxModel(b);
					comboBox_prof.setModel(model);
				}
				comboBox_prof.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JComboBox jc=(JComboBox)e.getSource();
						Object index = jc.getSelectedItem();
						sql="SELECT s.name,s.s_num,s.gender,s.department,s.p_num,p.name FROM student s inner join professor p on s.profno=p.profno WHERE p.name='"+index+"'";
					}
				});
			}
		});
    
		JLabel lbl_prof = new JLabel("> \uAD50\uC218\uBA85 : ");
		lbl_prof.setFont(new Font("굴림", Font.BOLD, 11));
		lbl_prof.setBounds(229, 19, 63, 27);
		contentPane.add(lbl_prof);
		
		JButton btn_search = new JButton("\uAC80    \uC0C9");
		btn_search.setBounds(497, 19, 80, 27);
		contentPane.add(btn_search);
		btn_search.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0); //테이블 초기화
				try{
					String user = "root";
				    String pw = "1234";
				    String url = "jdbc:mysql://localhost:3306/studentinfo?characterEncoding=UTF-8&serverTimezone=UTC";
				         
				    Class.forName("com.mysql.jdbc.Driver");
				    // connection으로 db와 연결 (객체 생성)
				    conn = DriverManager.getConnection(url, user, pw);
				    st=conn.createStatement();
				    
				    ResultSet rs=st.executeQuery(sql);
					int i=0;
				    while(rs.next()) {
				    	String name=rs.getString(1);
				    	String s_num=rs.getString(2);
						String gender=rs.getString(3);
						String department=rs.getString(4);
						String p_num=rs.getString(5);
						String pro_name=rs.getString(6);
						
						Object data[]= {name,s_num,gender,department,p_num,pro_name};
						model.addRow(data);
						table.setModel(model);
						i++;
				    }	
				    conn.close();
				    if(i==0) {
				    	JOptionPane.showMessageDialog(null, "해당 교수님은 지도하는 학생이 없습니다!\n");
				    	//comboBox_prof.setSelectedIndex(5);
				    }
				    } catch (ClassNotFoundException cnfe) {
				    	System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
				    } catch (SQLException sqle) {
				    	System.out.println("DB 접속실패 : " + sqle.toString());
				    } catch (Exception e1) {
				        System.out.println("Unkonwn error");
				  e1.printStackTrace();
				}		
			}
		});
		
	}
}

