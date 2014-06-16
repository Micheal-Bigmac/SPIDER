/**
 * @author Fire Dragon
 * @Date 2014-5-13
 */

/*
 * 本程序用来将爬取的网页写入数据库，示例网页：
 * 			http://www.120ask.com/user/p/4rit7riIakRlY5
 * 			http://www.120ask.com/user/p/4cpEwvpEnmitx###
 * 请运行本类的main函数查看输出结果�?
 * 
 * 本类的connect()用来连接数据库，请在函数中配置自己的用户名和密码
 * 本类的disconnect()用来断开与数据库的连�?
 * 本类的insert(DoctorPage dp)用来将解析完成的DoctorPage中的各属性�?存入数据库，测试时，请修改sql语句中的table�?
 */

package yourcode;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//import mytool.ValueTransfer;

public class DBqm {  //前面这些都不变，就改�?��main函数的名字，改成�?��类的名字
	
	// 存储数据库连接的上下文信�?
	Connection conn = null;
	
	// 加载mysql数据库驱�?
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 连接数据�?
	public Connection connect() throws Exception {
		Properties prop = new Properties();
		prop.put("charSet", "utf8");
		prop.put("user", "root");	// 设置用户�?
		prop.put("password", "mysql");	// 设置密码
		String db_url = "jdbc:mysql://localhost/test";   // �ǵø���ݿ���设置连接的url，如果使用了其他端口，请修改端口
		conn = DriverManager.getConnection(db_url, prop);
		if (conn != null) {
			System.out.println("Succeed to link database");
			return conn;
		}else{
			throw new Exception("Failed to link database");
		}
	}
	
	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert( ExtractPage dp ) {
		// 生成插入语句，ValueTransfer.sqlValueFor()会自动进行敏感字符转义，如果是字符串，会加上转义后的引号，这样可使命令拼接更加方便�?如果感兴趣，可以查看对应的函数实现细节�?
		String sql_command = "insert into `unknow` ( `root` , `url` , `path` , `date`, `time`, `topic`, `title`, `content`,'author','tag','source'," +
				") values ("
				+ dp.root
				+ ","
				+ dp.url
				+ ","
				+ dp.path
				+ ","
				+ (Date) dp.tempDate
				+ ","
				+ (Date) dp.tempDate
				+ ","
				+ dp.topic
				+ ","
				+ dp.category
				+ ","
				+ dp.content
				+ ","
				+ dp.reporter+ ","
				+ dp.tag+ ","
				+ ""+"," 
				+ "";
		System.out.println(dp.root);
		// 插入数据�?
		try {
			Statement st = conn.createStatement();
			// 插入属于�?��更新操作，所以需要用executeUpate函数
			st.executeUpdate(sql_command);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
	

