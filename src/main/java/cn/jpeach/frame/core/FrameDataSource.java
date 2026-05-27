package cn.jpeach.frame.core;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import cn.jpeach.frame.conf.ConfigCache;
import cn.jpeach.frame.conf.DataSourceConfig;

public final class FrameDataSource {
	private static final Logger logger = LoggerFactory.getLogger(FrameDataSource.class);
	private static DataSource source;

	public static Connection getConnect() throws SQLException {
		return source.getConnection();
	}

	static void init() {
		DataSourceConfig dsc = ConfigCache.getDataSourceConfig();
		if ("0".equals(dsc.getDatabaseType())) {
			logger.info("数据源未启用！！");
			return;
		}
		HikariConfig config = new HikariConfig();
		// 基础配置
		config.setJdbcUrl(dsc.getJdbcUrl());
		config.setUsername(dsc.getUsername());
		config.setPassword(dsc.getPassword());
		config.setDriverClassName(dsc.getDriverClass());

		// 连接池大小配置
		config.setMaximumPoolSize(dsc.getMaximumPoolSize()); // 最大连接数
		config.setMinimumIdle(dsc.getMinimumIdle()); // 最小空闲连接数
		config.setIdleTimeout(dsc.getIdleTimeout()); // 空闲超时：10分钟
		config.setMaxLifetime(dsc.getMaxLifetime()); // 连接最大生命周期：30分钟

		// 连接超时配置
		config.setConnectionTimeout(dsc.getConnectionTimeout()); // 获取连接超时：30秒

		// 【推荐】主动保活配置（4.0.3 新特性）
		// 每5分钟检测一次空闲连接，避免被网关或防火墙断开
		config.setKeepaliveTime(dsc.getKeepaliveTime()); // 5分钟

		// 可选：JDBC 驱动级优化
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		// 开启 JMX 监控（可选）
		config.setRegisterMbeans(true);
		if (!StringUtils.isBlank(dsc.getConnectionTestQuery())) {
			config.setConnectionTestQuery(dsc.getConnectionTestQuery());
		}
		source = new HikariDataSource(config);
	}
}
