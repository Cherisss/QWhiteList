package com.Qwhitelist;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;
public class Qwhitelist extends JavaPlugin implements Listener {
	FileConfiguration config = getConfig();
	@Override
	public void onEnable() {
		FileConfiguration config = getConfig();
		config.addDefault("qq", "初始qaq");
		config.options().copyDefaults(true);
		saveConfig();
		Bukkit.getPluginManager().registerEvents(new PlayerLogin(), this);
	//	Bukkit.getPluginManager().registerEvents(new blockbreak(), this);
		getLogger().info("=============================================");
		getLogger().info("                                   ");
		getLogger().info(" Qwhitelist 插件已载入      作者:xingxing520");
		getLogger().info("         版本：Beta1.1.2.1           ");
		getLogger().info("   QQ群群号:" + config.getString("qq"));
		getLogger().info("=============================================");
	}
	String command = " Qwhitelist >>>   ";
	@Override
	public void onDisable() {
		getLogger().info("===========================================");
		getLogger().info("                                   ");
		getLogger().info("Qwhitelist 插件已卸载      作者:xingxing520");
		getLogger().info("          版本：Beta1.1.2.1         ");
		getLogger().info("===========================================");
	}
	static class Mysql{ //检测ID在数据库里的可用性
/*		static void ToGet(String qq,String Join,String Remove) {
			final String mysql_JDBC = "com.mysql.jdbc.Driver";
			final String ToURL = "jdbc:mysql://106.14.64.250:3306/qwl";
			final String AWA = "qwl";
			final String QAQ = "qwl"; //各位Dev好，如果你看到这一段注释，说明你成功的反编译或者看到我的源码了，因为jvav的开源措施，我也没有什么简单的方法可以把数据库密码加密QAQ~	
			Connection conn = null;
			Statement stmt = null;
			int i = 0;
			try {
				Class.forName(mysql_JDBC);
				conn = DriverManager.getConnection(ToURL, AWA, QAQ);
				stmt = conn.createStatement();
				String sql;
				sql = "select * from QQsetting where QQ='" + qq + "'";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) i = 1;
			} catch (ClassNotFoundException | SQLException e1) {

				e1.printStackTrace();
			}
		    if(i>0)
		    {
		    	try {
		    	Class.forName(mysql_JDBC);
				conn = DriverManager.getConnection(ToURL, AWA, QAQ);
				stmt = conn.createStatement();
				String sql;
				sql = "DELETE FROM `QQsetting` WHERE QQ='"+qq+"'";
				ResultSet rs = stmt.executeQuery(sql);
				sql = "Insert Into QQsetting(Join, Remove,QQ) Values('"+Join+"','"+Remove+"','"+qq+"'";
		    	} 
		    	catch(ClassNotFoundException | SQLException e1){
		    		e1.printStackTrace();}
		    }else {
				    String sql;
					sql = "Insert Into QQsetting(Join, Remove,QQ) Values('"+Join+"','"+Remove+"','"+qq+"'";
					try {
						ResultSet rs = stmt.executeQuery(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
			    	
			    	
		    }
		    	
		} */
		static int ToCheckId(String id,String FROMQQ) {
			final String mysql_JDBC = "com.mysql.jdbc.Driver";
			final String ToURL = "jdbc:mysql://api.amongus.axing6.cn:3306/qwl";
			final String AWA = "qwl";
			final String QAQ = "qwl"; //各位Dev好，如果你看到这一段注释，说明你成功的反编译或者看到我的源码了，因为jvav的开源措施，我也没有什么简单的方法可以把数据库密码加密QAQ~	
			Connection conn = null;
			Statement stmt = null;
			int i = 0;
			try {
				Class.forName(mysql_JDBC);
				conn = DriverManager.getConnection(ToURL, AWA, QAQ);
				stmt = conn.createStatement();
				String sql;
				sql = "select * from qwl where name='" + id + "' and fromqq='"
						+ FROMQQ + "'";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) i = 1;
			} catch (ClassNotFoundException | SQLException e1) {

				e1.printStackTrace();
				
			}
			return i;
		}
	}
  /*  public class blockbreak implements Listener { 
	@EventHandler
    public void onblockbreak(BlockBreakEvent event) throws SQLException { 
    new Thread(){
    	@Override
		public void run() {
    
		     if (config.getString("qq") == "初始qaq") {
			getLogger().info(event.getPlayer().getName() + " 加入服务器！当前Qwhitelist插件配置未设置！");
			return;
		}
		int i=Mysql.ToCheckId(event.getPlayer().getName(),config.getString("qq"));
        if(event.getPlayer().isOp()) i=1;
		if (i == 0 ) {
			try {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"kick " + event.getPlayer().getName() + " [QWhiteList] \n 你没有白名单awa \n 请加群："
						+ config.getString("qq") + "了解");	
				getLogger().info(event.getPlayer().getName() + " 被kick出服务器，原因：没有没名单!");
			} catch (Exception e) {
				event.getPlayer().sendMessage(command + "QWhiteList插件出问题了~" + e);
				event.getPlayer().sendMessage(command + "error:Login& " + e);

			} finally {
				event.setCancelled(true);       
			
			}
		}
    	}
    }.start();
}} */
	public class PlayerLogin implements Listener {
		@EventHandler
		public void onPlayerJoin(AsyncPlayerPreLoginEvent event) throws SQLException{
			int i;
			i=Mysql.ToCheckId(event.getName(),config.getString("qq"));
			getLogger().info(event.getName() + " Login the server!");
            if(i!=0) 
            	event.allow();
			if (i == 0 ) {
				     event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST, "[QWhiteList] \n 你没有白名单 \n 详细请加"+config.getString("qq")+"群了解");		    
					getLogger().info(event.getName() + " 被kick出服务器，原因：没有没名单!");			
			}
		}}}
    
