package accounts;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.Border;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;

import com.mysql.cj.protocol.Resultset;

public class Main 
{
    public static void main(String[] args) 
    {
    	new most();
    }
}

class most extends JFrame
{
	public most()
	{
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) screensize.getWidth() / 2 - 200;
		int y = (int) screensize.getHeight() / 2 - 325;	
		this.setBackground(Color.white);
		this.setLocation(x, y);
		this.setLayout(null);
		ImageIcon icon = new ImageIcon("img\\jizhang2.jpg");
        setIconImage(icon.getImage());
        this.setTitle("叨叨记账助手");
		this.setSize(420,650);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setContentPane(new first(this));
		this.setVisible(true);
	}
	
}

class first extends JPanel
{
	JFrame mai;
	JPanel ac=new JPanel();
	JLabel lb1=new JLabel("叨叨记账小助手");
	JButton jz=new JButton();
	JButton cz=new JButton();
	JLabel jzlb=new JLabel("开始记账");
	JLabel czlb=new JLabel("查看账单");
	JButton tj=new JButton();
	JLabel tjlb=new JLabel("分类统计");
	JButton qs=new JButton();
	JLabel qslb=new JLabel("趋势走向");
	public first(JFrame p)
	{
		mai=p;
		this.setSize(420,650);
		this.setLayout(null);
		this.setBackground(Color.white);
		ac_set();
		jz_set();
		cz_set();
		tj_set();
		qs_set();
	}
	
	public void ac_set()
	{
		ac.setBounds(0, 20, 400, 70);
		Font ft=new Font("微软雅黑",Font.BOLD,40);
		lb1.setFont(ft);
		ac.setBackground(Color.white);
		ac.add(lb1);
		this.add(ac);
	}
	
	public void jz_set()
	{
		ImageIcon img=new ImageIcon("img\\jizhang.jpg");
		jz.setBounds(80,150,80,80);
		jzlb.setBounds(82,240,80,30);
		jzlb.setFont(new Font("微软雅黑",Font.BOLD,20));
		Image temp = img.getImage().getScaledInstance(jz.getWidth(), jz.getHeight(), img.getImage().SCALE_DEFAULT);
		img = new ImageIcon(temp);
		jz.setIcon(img);
		jz.setBorderPainted(false);
		jz.addActionListener(e->{
			mai.setContentPane(new keep(mai));
			});
		jzlb.addMouseListener(new cl(new keep(mai)));
		this.add(jz);
		this.add(jzlb);
	}
	
	public void cz_set()
	{
		ImageIcon img=new ImageIcon("img\\chazhang.jpg");
		cz.setBounds(250,150,80,80);
		czlb.setBounds(252,240,80,30);
		czlb.setFont(new Font("微软雅黑",Font.BOLD,20));
		Image temp = img.getImage().getScaledInstance(cz.getWidth(), cz.getHeight(), img.getImage().SCALE_DEFAULT);
		img = new ImageIcon(temp);
		cz.setIcon(img);
		cz.setBorderPainted(false);
		cz.addActionListener(e->{
			mai.setContentPane(new bill(mai));
			});
		czlb.addMouseListener(new cl(new bill(mai)));
		this.add(czlb);
		this.add(cz);
	}
	
	public void tj_set()
	{
		ImageIcon img=new ImageIcon("img\\bingzhuangtu.jpg");
		tj.setBounds(80,300,80,80);
		tjlb.setBounds(80,390,80,30);
		tjlb.setFont(new Font("微软雅黑",Font.BOLD,20));
		Image temp = img.getImage().getScaledInstance(tj.getWidth(), tj.getHeight(), img.getImage().SCALE_DEFAULT);
		img = new ImageIcon(temp);
		tj.setIcon(img);
		tj.setBorderPainted(false);
		tj.addActionListener(e->{
			mai.setContentPane(new stat(mai));
			});
		tjlb.addMouseListener(new cl(new stat(mai)));
		this.add(tjlb);
		this.add(tj);
	}

	public void qs_set()
	{
		ImageIcon img=new ImageIcon("img\\qushi.jpg");
		qs.setBounds(250,300,80,80);
		qslb.setBounds(250,390,80,30);
		qslb.setFont(new Font("微软雅黑",Font.BOLD,20));
		Image temp = img.getImage().getScaledInstance(qs.getWidth(), qs.getHeight(), img.getImage().SCALE_DEFAULT);
		img = new ImageIcon(temp);
		qs.setIcon(img);
		qs.setBorderPainted(false);
		qs.addActionListener(e->{
			mai.setContentPane(new tend(mai));
			});
		qslb.addMouseListener(new cl(new tend(mai)));
		this.add(qslb);
		this.add(qs);
	}
	
	class cl implements MouseListener
	{
		JPanel p;
		public cl(JPanel p)
		{
			this.p=p;
		}
		public void mouseClicked(MouseEvent arg0) 
		{
			mai.setContentPane(p);
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}

class bill extends JPanel
{
	JFrame main;
	JTextArea bill= new JTextArea();
	JScrollPane sp = new JScrollPane(bill,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	JTextField in =new JTextField(100);
	JButton search=new JButton();
	JButton back=new JButton();
	JLabel tit=new JLabel("账单记录");
	public bill(JFrame p)
	{
		main=p;
		tit.setBounds(170, 0, 235, 35);
		tit.setFont(new Font("微软雅黑",Font.BOLD,20));
		search.setBounds(370,35,35,35);
		search.setFont(new Font("微软雅黑",Font.BOLD,20));
		back.setBounds(0,0,35,35);
		ImageIcon img=new ImageIcon("img\\search.jpg");
		Image temp = img.getImage().getScaledInstance(search.getWidth(), search.getHeight(), img.getImage().SCALE_DEFAULT);
		img = new ImageIcon(temp);
		search.setIcon(img);
		ImageIcon img1=new ImageIcon("img\\back.jpg");
		Image temp1 = img1.getImage().getScaledInstance(back.getWidth(), back.getHeight(), img1.getImage().SCALE_DEFAULT);
		img1 = new ImageIcon(temp1);
		back.setIcon(img1);
		back.setBorderPainted(false);
		this.setBackground(Color.white);
	    this.setSize(420,650);
	    this.setLayout(null);
	    sp.setBounds(2, 70, 403, 540);
	    in.setBounds(2, 35, 368, 35);
	    back.addMouseListener(new cl());
	    search.setBorderPainted(false);
	    search.addActionListener(e->search_set());
	    this.add(sp);
		this.add(in);
		this.add(search);
		this.add(back);
		this.add(tit);
		bill_set();
	}
	
	public void bill_set()
	{
        bill.setFont(new Font("微软雅黑",Font.PLAIN,20));
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	    final String DB_URL = "jdbc:mysql://localhost:3306/newdb2?serverTimezone=UTC";
	    final String USER = "newuser";
	    final String PASS = "jasmine0705";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from accounts order by time asc");
            String text="";
            while(rs.next())
            {
            	String type=rs.getString("s_type");
            	String money=rs.getString("money");
            	String date=rs.getString("time");
            	text=text+date+" "+type+" "+money+"\r\n";
            }
            bill.setText(text);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
	}
	
	public void search_set()
	{
		String s=in.getText();
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	    final String DB_URL = "jdbc:mysql://localhost:3306/newdb2?serverTimezone=UTC";
	    final String USER = "newuser";
	    final String PASS = "jasmine0705";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs;
            if(s.charAt(0)>='0'&&s.charAt(0)<='9') {rs=stmt.executeQuery("select * from accounts where time='"+ s+"' order by id desc");}
            else { rs=stmt.executeQuery("select * from accounts where s_type='"+ s+"' order by id desc");}
            String text="";
            while(rs.next())
            {
            	String type=rs.getString("s_type");
            	String money=rs.getString("money");
            	String date=rs.getString("time");
            	text=text+date+" "+type+" "+money+"\r\n";
            }
            bill.setText(text);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
	}
	
	class cl implements MouseListener
	{
		public void mouseClicked(MouseEvent arg0) 
		{
			main.setContentPane(new first(main));
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}

class keep extends JPanel
{
	JFrame main;
	JButton back=new JButton();
	JLabel tit=new JLabel("开始记账");
	JLabel money=new JLabel("金额");
	JTextField money_in=new JTextField("请输入金额");
	JLabel type=new JLabel("类型");
	JComboBox<String> type_in1=new JComboBox<>();
	JComboBox<String> type_in2=new JComboBox<>();
	JLabel time=new JLabel("时间");
	JComboBox<String> time_year=new JComboBox<>();
	JComboBox<String> time_month=new JComboBox<>();
	JComboBox<String> time_day=new JComboBox<>();
	JButton commit=new JButton("开始记账!");
	JTextArea reply=new JTextArea();
	JTextArea bill= new JTextArea();
	JScrollPane sp = new JScrollPane(bill,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	public keep(JFrame f)
	{
		main=f;
		this.setBackground(Color.white);
	    this.setSize(420,650);
	    this.setLayout(null);
		tit.setBounds(180, 0, 235, 35);
		back.setBounds(0,0,35,35);
		tit.setFont(new Font("微软雅黑",Font.BOLD,20));
		ImageIcon img1=new ImageIcon("img\\back.jpg");
		Image temp1 = img1.getImage().getScaledInstance(back.getWidth(), back.getHeight(), img1.getImage().SCALE_DEFAULT);
		img1 = new ImageIcon(temp1);
		back.setIcon(img1);
		back.setBorderPainted(false);
		back.addMouseListener(new cl());
		insert_set();
		bill_set();
		this.add(commit);
		this.add(money);
		this.add(back);
		this.add(tit);
		this.add(money_in);
		this.add(type);
		this.add(type_in1);
		this.add(type_in2);
		this.add(time);
		this.add(time_year);
		this.add(time_month);
		this.add(time_day);
		this.add(reply);
		this.add(sp);
	}

	public void insert_set()
	{
		money.setBounds(20,70,70,35);
		money.setFont(new Font("微软雅黑",Font.PLAIN,20));
		money_in.setBounds(90,70,200,35);
		money_in.setFont(new Font("微软雅黑",Font.PLAIN,20));
		type.setBounds(20,125,70,35);
		type.setFont(new Font("微软雅黑",Font.PLAIN,20));
		type_in1.setBounds(90,125,140,35);
		type_in1.setFont(new Font("微软雅黑",Font.PLAIN,20));
		type_in1.setBackground(Color.white);
		type_in2.setBounds(240,125,140,35);
		type_in2.setFont(new Font("微软雅黑",Font.PLAIN,20));
		type_in2.setBackground(Color.white);
		time.setBounds(20,180,70,35);
		time.setFont(new Font("微软雅黑",Font.PLAIN,20));
		time_year.setBounds(90,180,80,35);
		time_year.setFont(new Font("微软雅黑",Font.PLAIN,20));
		time_year.setBackground(Color.white);
		time_month.setBounds(190,180,80,35);
		time_month.setFont(new Font("微软雅黑",Font.PLAIN,20));
		time_month.setBackground(Color.white);
		time_day.setBounds(290,180,80,35);
		time_day.setFont(new Font("微软雅黑",Font.PLAIN,20));
		time_day.setBackground(Color.white);
		type_in1.addItemListener(e->{
			type_set();
		});
		time_month.addActionListener(e->{
			time_day.removeAllItems();
			if(time_month.getSelectedItem().toString().equals("2")) {
				if(Double.parseDouble(time_year.getSelectedItem().toString())%4==0)
				{
					for(int i=1;i<30;i++) time_day.addItem(i+"");
				}
				else {
					for(int i=1;i<29;i++) time_day.addItem(i+"");
				}
			}
			else if(time_month.getSelectedItem().toString().equals("1")||time_month.getSelectedItem().toString().equals("3")||time_month.getSelectedItem().toString().equals("5")||time_month.getSelectedItem().toString().equals("7")||time_month.getSelectedItem().toString().equals("8")||time_month.getSelectedItem().toString().equals("10")||time_month.getSelectedItem().toString().equals("12"))
			{
				for(int i=1;i<32;i++) time_day.addItem(i+"");
			}
			else {
				for(int i=1;i<31;i++) time_day.addItem(i+"");
			}
		});
		commit.setFont(new Font("微软雅黑",Font.BOLD,15));
		commit.setBackground(Color.white);
		commit.setBounds(150, 240, 100, 35);
		commit.addActionListener(e->{
			insert();
			reply();
			bill_set();
			money_in.setText("");
		});
		reply.setBounds(30,290,340,70);
		reply.setFont(new Font("微软雅黑",Font.PLAIN,20));
		reply.setLineWrap(true);
		reply.setWrapStyleWord(true);
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	    final String DB_URL = "jdbc:mysql://localhost:3306/newdb2?serverTimezone=UTC";
	    final String USER = "newuser";
	    final String PASS = "jasmine0705";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery("select distinct(b_type) from type");
            while(rs.next())
            {
            	String t=rs.getString("b_type");
            	type_in1.addItem(t);
            }
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
	}
        for(int i=2000;i<2020;i++)
        {
        time_year.addItem(i+"");
        }
        for(int i=1;i<13;i++)
        {
        	time_month.addItem(i+"");
        }
        
	}

	public void type_set() 
	{
		type_in2.removeAllItems();
		String x=type_in1.getSelectedItem().toString();
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	    final String DB_URL = "jdbc:mysql://localhost:3306/newdb2?serverTimezone=UTC";
	    final String USER = "newuser";
	    final String PASS = "jasmine0705";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from type where b_type='"+ x+"' order by id desc");
            String text="";
            while(rs.next())
            {
            	String type=rs.getString("s_type");
            	type_in2.addItem(type);
            }
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
	}
	
	public void insert()
	{
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	    final String DB_URL = "jdbc:mysql://localhost:3306/newdb2?serverTimezone=UTC";
	    final String USER = "newuser";
	    final String PASS = "jasmine0705";
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String mo=money_in.getText();
            String ty1=type_in1.getSelectedItem().toString();
            String ty2=type_in2.getSelectedItem().toString();
            String ti=time_year.getSelectedItem().toString()+"-"+time_month.getSelectedItem().toString()+"-"+time_day.getSelectedItem().toString();
            String sql = "{call a_insert(?,?,?,?)}";
            CallableStatement cstm = conn.prepareCall(sql); //实例化对象cstm 
            cstm.setString(1, mo);
            cstm.setString(2, ty1);
            cstm.setString(3, ty2);
            cstm.setString(4,ti);
            cstm.execute(); 
            cstm.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
	}
	}

	public void reply()
	{
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	    final String DB_URL = "jdbc:mysql://localhost:3306/newdb2?serverTimezone=UTC";
	    final String USER = "newuser";
	    final String PASS = "jasmine0705";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String ty=type_in2.getSelectedItem().toString();
            ResultSet rs=stmt.executeQuery("select value from reply where s_type='"+ty+"'");
            while(rs.next())
            {
            	ty=rs.getString("value");
            }
            reply.setText(ty);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
	}
	}
	
	public void bill_set()
	{
		sp.setBounds(5, 380, 395,230);
        bill.setFont(new Font("微软雅黑",Font.PLAIN,16));
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	    final String DB_URL = "jdbc:mysql://localhost:3306/newdb2?serverTimezone=UTC";
	    final String USER = "newuser";
	    final String PASS = "jasmine0705";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from accounts order by id asc");
            String text="";
            while(rs.next())
            {
            	String type=rs.getString("s_type");
            	String money=rs.getString("money");
            	String date=rs.getString("time");
            	text=text+date+" "+type+" "+money+"\r\n";
            }
            bill.setText(text);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
	}
	
	class cl implements MouseListener
	{
		public void mouseClicked(MouseEvent arg0) 
		{
			main.setContentPane(new first(main));
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}

class stat extends JPanel
{
	JFrame main;
	JButton back=new JButton();
	JLabel tit=new JLabel("分类统计");
	JFreeChart chart;
	ChartPanel ch;
	DefaultPieDataset db=new DefaultPieDataset();
	Calendar cale=Calendar.getInstance();
	int mo=cale.get(Calendar.MONTH)+1;
	int ye=cale.get(Calendar.YEAR);
	JButton pre_month=new JButton();
	JButton	next_month=new JButton();
	JLabel sum=new JLabel();
	public stat(JFrame f)
	{
		main=f;
		this.setBackground(Color.white);
	    this.setSize(420,650);
	    this.setLayout(null);
		tit.setBounds(180, 0, 235, 35);
		back.setBounds(0,0,35,35);
		tit.setFont(new Font("微软雅黑",Font.BOLD,20));
		ImageIcon img1=new ImageIcon("img\\back.jpg");
		Image temp1 = img1.getImage().getScaledInstance(back.getWidth(), back.getHeight(), img1.getImage().SCALE_DEFAULT);
		img1 = new ImageIcon(temp1);
		back.setIcon(img1);
		back.setBorderPainted(false);
		back.addMouseListener(new cl());
		sum.setBounds(110,510,300,35);
		sum.setFont(new Font("微软雅黑",Font.BOLD, 20));
		db_set();
		chart_set();
		this.add(pre_month);
		this.add(next_month);
		this.add(back);
		this.add(tit);
		this.add(ch);
		this.add(sum);
	}
	public void db_set()
	{
		db.clear();
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	    final String DB_URL = "jdbc:mysql://localhost:3306/newdb2?serverTimezone=UTC";
	    final String USER = "newuser";
	    final String PASS = "jasmine0705";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery("select sum(money),b_type from accounts where month(time)='"+mo+"'and year(time)='"+ye+"' group by b_type");
            while(rs.next())
            {
            	db.setValue(rs.getString("b_type"), rs.getDouble("sum(money)"));
            }
            rs=stmt.executeQuery("select sum(money) from accounts where month(time)='"+mo+"' and year(time)='"+ye+"'");
            while(rs.next())
            {
            	sum.setText("本月总消费："+new DecimalFormat("#0.00").format(rs.getDouble("sum(money)"))+"元");
            }
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
	}
	public void chart_set() 
	{
		pre_month.setBounds(30,106,24,25);
		next_month.setBounds(346, 106, 24, 25);
		ImageIcon img2=new ImageIcon("img\\pre.jpg");
		Image temp2 = img2.getImage().getScaledInstance(pre_month.getWidth(), pre_month.getHeight(), img2.getImage().SCALE_DEFAULT);
		img2 = new ImageIcon(temp2);
		pre_month.setIcon(img2);
		pre_month.setBorderPainted(false);
		ImageIcon img3=new ImageIcon("img\\next.jpg");
		Image temp3 = img3.getImage().getScaledInstance(next_month.getWidth(), next_month.getHeight(), img3.getImage().SCALE_DEFAULT);
		img3 = new ImageIcon(temp3);
		next_month.setIcon(img3);
		next_month.setBorderPainted(false);
		pre_month.addActionListener(e->{
			if(mo!=1)mo--;
			else {
				ye--;
				mo=12;
			}
			chart.setTitle(new TextTitle(ye+"年"+mo+"月"+"开销比例", new Font("隶书", Font.BOLD, 26))); 
			db_set();
		});
		next_month.addActionListener(e->{
			if(mo!=12) mo++;
			else {
				ye++;
				mo=1;
			}
			chart.setTitle(new TextTitle(ye+"年"+mo+"月"+"开销比例", new Font("隶书", Font.BOLD, 26))); 
			db_set();
		});
		chart=ChartFactory.createPieChart("开销比例",db,true,true,false);
		chart.setTitle(new TextTitle(ye+"年"+mo+"月"+"开销比例", new Font("隶书", Font.BOLD, 26))); 
		PiePlot piePlot =(PiePlot)chart.getPlot();
		piePlot.setBackgroundPaint(Color.WHITE); //设置背景颜色为白色
	    piePlot.setOutlineVisible(false); //删除背景边框
	    piePlot.setLabelBackgroundPaint(Color.white);
	    piePlot.setLabelOutlinePaint(null);
	    piePlot.setLabelShadowPaint(null);
	    chart.getLegend().setItemFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 15));
	    piePlot.setLabelFont(new Font("宋体", Font.PLAIN, 15));
	    DecimalFormat df = new DecimalFormat("0.00%");
        NumberFormat nf = NumberFormat.getInstance();
	    StandardPieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator("{0} {2}",nf, df);
        piePlot.setLabelGenerator(generator);// 设置百分比
	    ch=new ChartPanel(chart);
		ch.setBackground(Color.white);
		ch.setBounds(2,100,400,400);
	}
    class cl implements MouseListener
	{
		public void mouseClicked(MouseEvent arg0) 
		{
			main.setContentPane(new first(main));
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}

class tend extends JPanel
{
	JFrame main;
	JButton back=new JButton();
	JLabel tit=new JLabel("趋势走向");
	JFreeChart chart;
	ChartPanel ch;
	DefaultCategoryDataset db=new DefaultCategoryDataset();
	public tend(JFrame f)
	{
		main=f;
		this.setBackground(Color.white);
	    this.setSize(420,650);
	    this.setLayout(null);
		tit.setBounds(180, 0, 235, 35);
		back.setBounds(0,0,35,35);
		tit.setFont(new Font("微软雅黑",Font.BOLD,20));
		ImageIcon img1=new ImageIcon("img\\back.jpg");
		Image temp1 = img1.getImage().getScaledInstance(back.getWidth(), back.getHeight(), img1.getImage().SCALE_DEFAULT);
		img1 = new ImageIcon(temp1);
		back.setIcon(img1);
		back.setBorderPainted(false);
		back.addMouseListener(new cl());
		db_set();
		ch_set();
		this.add(ch);
		this.add(back);
		this.add(tit);
	}
	public void db_set()
	{
		db.clear();
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	    final String DB_URL = "jdbc:mysql://localhost:3306/newdb2?serverTimezone=UTC";
	    final String USER = "newuser";
	    final String PASS = "jasmine0705";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery("select sum(money),month(time),year(time) from accounts group by month(time),year(time) order by year(time),month(time)");
            while(rs.next())
            {
            	db.addValue(rs.getDouble("sum(money)"), "月开销",rs.getString("year(time)")+"."+rs.getString("month(time)"));
            }
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
	}
	public void ch_set()
	{
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		mChartTheme.setLargeFont(new Font("微软雅黑", Font.PLAIN, 20));
		mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
		ChartFactory.setChartTheme(mChartTheme);		
		chart=ChartFactory.createLineChart("2019年月支出走向图","月份","金额",db,PlotOrientation.VERTICAL, true,true,false);
		chart.setTitle(new TextTitle("2019年月支出走向图",new Font("宋体", Font.BOLD, 22)));
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinePaint(Color.gray);
		plot.setOutlinePaint(null);
		LineAndShapeRenderer lasp = (LineAndShapeRenderer) plot.getRenderer();
		lasp.setBaseShapesVisible(true);
		lasp.setDrawOutlines(true);
		lasp.setUseFillPaint(true);
		lasp.setSeriesStroke(0, new BasicStroke(3F));
		ch=new ChartPanel(chart);
		ch.setBounds(0,100,400,400);
	}
	
    class cl implements MouseListener
	{
		public void mouseClicked(MouseEvent arg0) 
		{
			main.setContentPane(new first(main));
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}
