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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class delete extends student_info{
	JFrame dif=new JFrame();
	String header[]= {"    이름  ","     학번     ","     성별    " ,"     학과        ","   휴대전화   ","   지도교수  "};
	DefaultTableModel model = new DefaultTableModel(header,0);
	JTable table = new JTable(model);
	String anu;
	
	void set_table() {
		model.setNumRows(0); //테이블 초기화
		DBcon();
		anu="SELECT s.name,s.s_num,s.gender,s.department,s.p_num,p.name FROM student s inner join professor p on s.profno=p.profno";
	    ResultSet rs;
		try {
			rs = st.executeQuery(anu);
			int i=0;
		    while(rs.next()) {
		    	String name=rs.getString(1);
		    	String s_num=rs.getString(2);
				String gender=rs.getString(3);
				String department=rs.getString(4);
				String p_num=rs.getString(5);
				String pro_name=rs.getString(6);
				
				Object datas[]= {name,s_num,gender,department,p_num,pro_name};
				model.addRow(datas);
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
	delete() { //학생정보삭제함수
		dif.setTitle("학생 정보 삭제");
		dif.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dif.setBounds(100, 100, 560, 346);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		dif.setContentPane(contentPane);
		contentPane.setLayout(null);
		dif.setVisible(true);
		String[] data=new String[6];//테이블에 값을 넣기위한 배열
		
		set_table();
		
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
		scrollPane.setBounds(12, 43, 521, 223);
		
		
		JButton btn_close = new JButton("\uB2EB    \uAE30");
		btn_close.setFont(new Font("굴림", Font.PLAIN, 13));
		btn_close.setBounds(453, 276, 80, 23);
		btn_close.addActionListener(new ActionListener() { //검색버튼 클릭 이벤트
			public void actionPerformed(ActionEvent e) {
				dif.dispose();
			}
		});
		contentPane.add(btn_close);
		
		JButton btn_delete = new JButton("\uC0AD \uC81C");
		btn_delete.setBounds(368, 10, 69, 23);
		contentPane.add(btn_delete);
		btn_delete.addActionListener(new ActionListener() {//삭제
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				Object value = table.getValueAt(row, col);
				
				JFrame nn=new JFrame();
				nn.setTitle("삭제확인");
				nn.setSize(300,150);
				nn.setLocation(500,300);
					
				JPanel p8 = new JPanel();
				p8.setLayout(null);
					
				JLabel l = new JLabel("정말 정보를 삭제하시겠습니까?");
				l.setBounds(60,15,200,32);
				p8.add(l);
					
				JButton c=new JButton("취 소");
				c.setFont(new Font("HY나무L",Font.BOLD,13));
				c.setBounds(50,65,90,32);
				p8.add(c);
					
				JButton e1=new JButton("삭제");
				e1.setFont(new Font("HY나무L",Font.BOLD,13));
				e1.setBounds(130,65,90,32);
				p8.add(e1);
					
				nn.add(p8);
				nn.setVisible(true);
					
				c.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						nn.dispose();
					}
				});
				e1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DBcon();
						anu="DELETE FROM student WHERE s_num='"+(String) value+"'";
					    int rss;
						try {
							rss = st.executeUpdate(anu);
						    conn.close();
						    JOptionPane.showMessageDialog(null, "해당 학생의 정보가 삭제되었습니다!");
							nn.dispose();
							set_table();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "정보 삭제에 실패하였습니다..");
							nn.dispose();
						}
					}
				});
			}
		});
		
		JButton btn_delete_all = new JButton("\uC804\uCCB4\uC0AD\uC81C");
		btn_delete_all.setBounds(449, 10, 84, 23);
		contentPane.add(btn_delete_all);
		btn_delete_all.addActionListener(new ActionListener() { //전체삭제
			public void actionPerformed(ActionEvent e) {
				JFrame nn=new JFrame();
				nn.setTitle("삭제확인");
				nn.setSize(300,150);
				nn.setLocation(500,300);
					
				JPanel p8 = new JPanel();
				p8.setLayout(null);
					
				JLabel l = new JLabel("정말 모든 정보를 삭제하시겠습니까?");
				l.setBounds(60,15,200,32);
				p8.add(l);
					
				JButton c=new JButton("취 소");
				c.setFont(new Font("HY나무L",Font.BOLD,13));
				c.setBounds(50,65,90,32);
				p8.add(c);
					
				JButton e1=new JButton("삭제");
				e1.setFont(new Font("HY나무L",Font.BOLD,13));
				e1.setBounds(130,65,90,32);
				p8.add(e1);
					
				nn.add(p8);
				nn.setVisible(true);
					
				c.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						nn.dispose();
					}
				});
				e1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DBcon();
						anu="DELETE FROM student";
						int rss;
						try {
							rss = st.executeUpdate(anu);
							conn.close();
							JOptionPane.showMessageDialog(null, "모든 학생의 정보가 삭제되었습니다!");
							nn.dispose();
							set_table();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "정보 삭제에 실패하였습니다..");
							nn.dispose();
						}
					}
				});
			}
		});
	}
}
