package com.jeecms.cms.rest2.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

public class JaxbTool {

	public static String getXmlString(Object o) {
		String str = null;

		try {
			JAXBContext jc = JAXBContext.newInstance(o.getClass());
			Marshaller m = jc.createMarshaller();

			StringWriter sw = new StringWriter();

			m.marshal(o, sw);

			str = sw.toString();
			sw.close();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	@SuppressWarnings({"rawtypes" })
	public static Object getObject(Class c, String xml) {
		Object o = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller u = context.createUnmarshaller();
			StringBuffer xmlStr = new StringBuffer(xml);
			o = u.unmarshal(new StreamSource(new StringReader(xmlStr.toString().trim())));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return o;
	}

	@SuppressWarnings({"rawtypes" })
	public static String getXmlCDATAString(Object o, List<Class> classlist) {
		String s = null;
		try {
			JAXBContext context = JAXBContext.newInstance(o.getClass());
			Marshaller m = context.createMarshaller();
			StringWriter sw = new StringWriter();

			XMLSerializer serializer = getXMLSerializer(sw, classlist);

			m.marshal(o, serializer.asContentHandler());

			s = sw.toString();

			sw.close();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return s;
	}

	public static String getXmlCDATAStringByTags(Object o, String[] tags) {
		String s = null;
		try {
			JAXBContext context = JAXBContext.newInstance(o.getClass());
			Marshaller m = context.createMarshaller();
			StringWriter sw = new StringWriter();

			OutputFormat of = new OutputFormat();

			for (int i = 0; i < tags.length; i++) {
				tags[i] = "^" + tags[i];
			}
			of.setCDataElements(tags);

			of.setPreserveSpace(true);
			of.setIndenting(true);

			XMLSerializer serializer = new XMLSerializer(of);
			serializer.setOutputCharStream(sw);

			m.marshal(o, serializer.asContentHandler());

			s = sw.toString();

			sw.close();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return s;
	}

	@SuppressWarnings("rawtypes")
    private static XMLSerializer getXMLSerializer(Writer writer, List<Class> cl) {
		OutputFormat of = new OutputFormat();
		String temp = "";
		for (Class c : cl) {
			Field[] fields = c.getDeclaredFields();

			for (Field f : fields) {
				System.out.println(f.getName());
				temp += "^" + f.getName() + "/";
			}
		}

		of.setCDataElements(temp.split("/"));

		of.setPreserveSpace(true);
		of.setIndenting(true);

		XMLSerializer serializer = new XMLSerializer(of);
		serializer.setOutputCharStream(writer);

		return serializer;
	}
	
	
	private static ConcurrentMap<Class<?>, JAXBContext> jaxbContexts = new ConcurrentHashMap<Class<?>, JAXBContext>();
	   
	public static String parseBean2Xml(Object obj, String encoding, boolean bool) {
		if (obj == null) {
			return null;
		}
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			createMarshaller(obj.getClass(), encoding, bool).marshal(obj, os);
			return os.toString("UTF-8");
		} catch (JAXBException e) {
			throw new RuntimeException("Could not parse [" + obj + "] to xml " + e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	private static Marshaller createMarshaller(Class<?> clazz, String encoding, boolean bool) {
		try {
			JAXBContext jaxbContext = getJaxbContext(clazz);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);// 是否格式化生成的xml串
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, bool);// 是否省略xml头信息
			if (StringUtils.isNotBlank(encoding)) {
				marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			}
			return marshaller;
		} catch (JAXBException e) {
			throw new RuntimeException("Could not createMarshaller for class [" + clazz + "]: " + e.getMessage(), e);
		}
	}
	private static JAXBContext getJaxbContext(Class<?> clazz) {
		Validate.notNull(clazz, "'clazz' must not be null");
		JAXBContext jaxbContext = jaxbContexts.get(clazz);
		try {
			if (jaxbContext == null) {
				jaxbContext = JAXBContext.newInstance(clazz);
				JAXBContext j = jaxbContexts.putIfAbsent(clazz, jaxbContext);
				if (j != null) {
					jaxbContext = j;
				}
			}
		} catch (JAXBException e) {
			throw new RuntimeException(
					"Could not instantiate JAXBContext for class [" + clazz + "]: " + e.getMessage(), e);
		}
		return jaxbContext;
	}
}
