package cn.jpeach.frame.conf;

import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigCache {
	private static final String FILE_NAME = "peach-config.xml";
	private static final Logger logger = LoggerFactory.getLogger(ConfigCache.class);
	private static DataSourceConfig dataSourceConfig;
	private static SystemConifg systemConifg;

	public static void parse() throws Exception {
		logger.debug("解析【{}】", FILE_NAME);
		InputStream inputStream = ConfigCache.class.getClassLoader().getResourceAsStream(FILE_NAME);
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 获取根元素
		Element root = document.getRootElement();
		dataSourceConfig = initDataSource(root);
		systemConifg = initApplication(root);
		inputStream.close();
	}

	public static DataSourceConfig getDataSourceConfig() {
		return dataSourceConfig;
	}

	public static SystemConifg getApplicationConifg() {
		return systemConifg;
	}

	public static boolean isDev() {
		return StringUtils.isNotBlank(systemConifg.getSysteMode()) && "dev".equalsIgnoreCase(systemConifg.getSysteMode());
	}

	private static SystemConifg initApplication(Element root) {
		SystemConifg conifg = new SystemConifg();
		Element app = root.element("app");
		conifg.setSystemName(app.elementText("name"));
		conifg.setSysteMode(app.elementText("mode"));
		conifg.setMainClass(app.elementText("main-class"));
		return conifg;
	}

	private static DataSourceConfig initDataSource(Element root) {
		DataSourceConfig dsc = new DataSourceConfig();
		Element datasource = root.element("datasource");
		dsc.setDatabaseType(datasource.elementText("databaseType"));
		dsc.setJdbcUrl(datasource.elementText("jdbcurl"));
		dsc.setUsername(datasource.elementText("username"));
		dsc.setPassword(datasource.elementText("password"));
		dsc.setDriverClass(datasource.elementText("driverClass"));
		Element pool = datasource.element("pool");
		dsc.setMaximumPoolSize(Integer.parseInt(pool.elementText("maxSize")));
		dsc.setMinimumIdle(Integer.parseInt(pool.elementText("minIdle")));
		dsc.setIdleTimeout(Long.parseLong(pool.elementText("timeout")));
		dsc.setMaxLifetime(Long.parseLong(pool.elementText("maxLifetime")));
		dsc.setConnectionTimeout(Long.parseLong(pool.elementText("connectionTimeout")));
		dsc.setKeepaliveTime(Long.parseLong(pool.elementText("keepaliveTime")));
		dsc.setConnectionTestQuery(datasource.elementText("connectionTestQuery"));
		return dsc;
	}
}
