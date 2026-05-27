package cn.jpeach.frame.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.jpeach.frame.exception.FrameException;

public class JsonUtil {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T readValue(InputStream src, Class<T> valueType) throws IOException {
		return objectMapper.readValue(src, valueType);
	}
	
	public static <T> T readValue(String src, Class<T> valueType) throws IOException {
		return objectMapper.readValue(src, valueType);
	}

	public static String writeValueAsString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new FrameException("序列化为字符串失败", e);
		}
	}

	public static String EmptyObject() {
		try {
			return objectMapper.writeValueAsString(new HashMap<>());
		} catch (JsonProcessingException e) {
			return "{}";
		}
	}

	public static String EmptyArray() {
		try {
			return objectMapper.writeValueAsString(new ArrayList<>());
		} catch (JsonProcessingException e) {
			return "[]";
		}
	}
}
