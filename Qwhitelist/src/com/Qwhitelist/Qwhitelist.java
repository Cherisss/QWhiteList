package com.Qwhitelist;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import java.sql.*;
public class Qwhitelist extends JavaPlugin implements Listener {
	FileConfiguration config = getConfig();
	@Override
	public void onEnable() {
		FileConfiguration config = getConfig();
		config.addDefault("qq", "��ʼqaq");
		config.options().copyDefaults(true);
		saveConfig();
		Bukkit.getPluginManager().registerEvents(new PlayerLogin(), this);
		Bukkit.getPluginManager().registerEvents(new blockbreak(), this);
		getLogger().info("=============================================");
		getLogger().info("                                   ");
		getLogger().info(" Qwhitelist ���������      ����:xingxing520");
		getLogger().info("         �汾��Beta0.9.8           ");
		getLogger().info("   QQȺȺ��:" + config.getString("qq"));
		getLogger().info("=============================================");
	}
	String command = "��8 Qwhitelist ��1 >>>   ";
	@Override
	public void onDisable() {
		getLogger().info("===========================================");
		getLogger().info("                                   ");
		getLogger().info("Qwhitelist �����ж��      ����:xingxing520");
		getLogger().info("          �汾��Beta0.9.8           ");
		getLogger().info("===========================================");
	}
	static class Mysql{ //���ID�����ݿ���Ŀ�����
		static int ToCheckId(String id,String FROMQQ) {
			final String mysql_JDBC = "com.mysql.jdbc.Driver";
			final String ToURL = "jdbc:mysql://106.14.64.250:3306/qwl";
			final String AWA = "qwl";
			final String QAQ = "qwl"; //��λDev�ã�����㿴����һ��ע�ͣ�˵����ɹ��ķ�������߿����ҵ�Դ���ˣ���Ϊjvav�Ŀ�Դ��ʩ����Ҳû��ʲô�򵥵ķ������԰����ݿ��������QAQ~	
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
    public class blockbreak implements Listener { 
	@EventHandler
    public void onblockbreak(BlockBreakEvent event) throws SQLException { 
		if (config.getString("qq") == "��ʼqaq") {
			getLogger().info(event.getPlayer().getName() + " �������������ǰQwhitelist�������δ���ã�");
			return;
		}
		int i=Mysql.ToCheckId(event.getPlayer().getName(),config.getString("qq"));
        if(event.getPlayer().isOp()) i=1;
		if (i == 0 ) {
			try {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"kick " + event.getPlayer().getName() + "[QWhiteList] \n ��û�а�����awa \n ���Ⱥ��"
						+ config.getString("qq") + "�˽�");	
				getLogger().info(event.getPlayer().getName() + " ��kick����������ԭ��û��û����!");
			} catch (Exception e) {
				event.getPlayer().sendMessage(command + "QWhiteList�����������~" + e);
				event.getPlayer().sendMessage(command + "error:Loginwhite404-1&" + e);

			} finally {
				event.setCancelled(true);       
			
			}
		}
		  
    }
}
	public class PlayerLogin implements Listener {
		@EventHandler
		public void onPlayerJoin(PlayerJoinEvent event) throws SQLException{
			int i=Mysql.ToCheckId(event.getPlayer().getName(),config.getString("qq"));
			if (config.getString("qq") == "��ʼqaq") {
				getLogger().info(event.getPlayer().getName() + " �������������ǰQwhitelist�������δ���ã�");
				i=1;
				return;
			}
			getLogger().info(event.getPlayer().getName() + " Login the server!");
            if(event.getPlayer().isOp()) 
            {
            	getLogger().info(event.getPlayer().getName() + " OP��¼��������");
            	i=1;
            }
			if (i == 0 ) {
				try {
				     Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"kick " + event.getPlayer().getName() + "[QWhiteList] \n ��û�а�����awa \n ���Ⱥ��"
								+ config.getString("qq") + "�˽�");
					getLogger().info(event.getPlayer().getName() + " ��kick����������ԭ��û��û����!");
				} catch (Exception e) {
					event.getPlayer().sendMessage(command + "QWhiteList�����������~" + e);
					event.getPlayer().sendMessage(command + "error:Loginwhite404-1" + e);

				} 
			}
		}
	}
}