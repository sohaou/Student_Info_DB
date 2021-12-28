package student_info;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class search extends student_info{
	String sql;
	
	search() { //모든 학생 정보 출력 함수
		JFrame pjf=new JFrame();
		pjf.setTitle("학생 정보 검색");
		pjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pjf.setBounds(100, 100, 565, 350);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		pjf.setContentPane(contentPane);
		contentPane.setLayout(null);
		pjf.setVisible(true);
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
		scrollPane.setBounds(12, 52, 521, 214);
		
		JButton btn_close = new JButton("\uB2EB    \uAE30");
		btn_close.setFont(new Font("굴림", Font.PLAIN, 13));
		btn_close.setBounds(453, 276, 80, 23);
		btn_close.addActionListener(new ActionListener() { //검색버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
				pjf.dispose();
			}
		});
		contentPane.add(btn_close);	
		
		JLabel lbl_st_name = new JLabel("* 학생 이름 : ");
		lbl_st_name.setFont(new Font("굴림", Font.BOLD, 13));
		lbl_st_name.setBounds(12, 19, 80, 23);
		contentPane.add(lbl_st_name);
		
		JTextField tF_stname = new JTextField();
		tF_stname.setBounds(103, 19, 107, 22);
		contentPane.add(tF_stname);
		tF_stname.setColumns(10);
		
		JButton btn_search = new JButton("검 색");
		btn_search.setBounds(229, 19, 63, 23);
		contentPane.add(btn_search);
		btn_search.addActionListener(new ActionListener() { //학생 검색
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0); //테이블 초기화
				DBcon();
			    sql="SELECT s.name,s.s_num,s.gender,s.department,s.p_num,p.name FROM student s inner join professor p on s.profno=p.profno WHERE s.name='"+tF_stname.getText()+"'";
			    ResultSet rs;
				try {
					rs = st.executeQuery(sql);
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
				    	model.setNumRows(0); //테이블 초기화
				    	JOptionPane.showMessageDialog(null, "해당 이름의 학생이 없습니다!\n");	
				    	tF_stname.setText("");
				    }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btn_search_all = new JButton("전체검색");
		btn_search_all.setBounds(429, 19, 84, 23);
		contentPane.add(btn_search_all);
		btn_search_all.addActionListener(new ActionListener() { //저장되어 있는 모든 정보 출력
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0); //테이블 초기화
				DBcon();
			    sql="SELECT s.name,s.s_num,s.gender,s.department,s.p_num,p.name FROM student s inner join professor p on s.profno=p.profno";
			    ResultSet rs;
				try {
					rs = st.executeQuery(sql);
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
				    	model.setNumRows(0); //테이블 초기화
				    	JOptionPane.showMessageDialog(null, "저장된 학생 정보가 없습니다!\n");	
				    }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
