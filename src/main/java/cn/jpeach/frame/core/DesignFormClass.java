package cn.jpeach.frame.core;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.jpeach.frame.entity.DocumentElement;
import cn.jpeach.frame.exception.DesignerException;

class DesignFormClass {
	static DocumentElement readFromPackage(Class<?> clazz) {
		// 获取与指定 Class 同目录的 XML 文件路径
		Document document = readFromClasspath(clazz);
		return toElement(document.getRootElement());
	}

	static Document readFromClasspath(Class<?> clazz) {
		String resourcePath = String.format("%s.xml", clazz.getSimpleName());
		try (InputStream inputStream = clazz.getResourceAsStream(resourcePath)) {
			if (inputStream == null) {
				throw new DesignerException(String.format("XML文件未找到:%s", resourcePath));
			}
			SAXReader reader = new SAXReader();
			reader.setEncoding(StandardCharsets.UTF_8.name());
			return reader.read(inputStream);
		} catch (DocumentException e) {
			throw new DesignerException(String.format("XML解析失败:%s", resourcePath), e);
		} catch (IOException e) {
			throw new DesignerException(String.format("读取XML文件异常:%s", resourcePath), e);
		}
	}

	private static DocumentElement toElement(Element root) {
		DocumentElement form = new DocumentElement(root.getName(), root.getTextTrim());
		List<Attribute> attributes = root.attributes();
		for (Attribute attribute : attributes) {
			form.putAttribute(attribute.getName(), attribute.getValue());
		}
		List<Element> elements = root.elements();
		for (Element element : elements) {
			form.addChild(toElement(element));
		}
		return form;
	}
}
