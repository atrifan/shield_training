package com.oneandone.fixture.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;

import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.JsonPath;

public class Tools {

	public static String toHtml(String text) {
		return text.replaceAll("<pre>", "").replaceAll("</pre>", "")
				.replaceAll("<", "&lt;").replaceAll(">", "&gt;")
				.replaceAll("\n", "<br/>").replaceAll("\t", "    ")
				.replaceAll(" ", "&nbsp;").replaceAll("-----", "<hr/>");
	}

	/**
	 * @param c
	 *            some text
	 * @return the text within <code>&lt;code></code> tags.
	 */
	public static String toCode(String c) {
		return "<code>" + c + "</code>";
	}

	/**
	 * @param somethingWithinATag
	 *            some text enclosed in some html tag.
	 * @return the text within the tag.
	 */ 
	public static String fromSimpleTag(String somethingWithinATag) {
		return somethingWithinATag.replaceAll("<[^>]+>", "").replace(
				"</[^>]+>", "");
	}

	/**
	 * @param text some html
	 * @return the text stripped out of all tags.
	 * 
	 */
	public static String fromHtml(String text) {
		String ls = "\n";
		return text.replaceAll("<br[\\s]*/>", ls).replaceAll("<BR[\\s]*/>", ls)
				.replaceAll("<span[^>]*>", "").replaceAll("</span>", "")
				.replaceAll("<pre>", "").replaceAll("</pre>", "")
				.replaceAll("&nbsp;", " ").replaceAll("&gt;", ">")
				.replaceAll("&amp;", "&").replaceAll("&lt;", "<")
				.replaceAll("&nbsp;", " ");
	}

	/**
	 * @param string a string
	 * @return the string htmlified as a fitnesse label.
	 */
	public static String toHtmlLabel(String string) {
		return "<i><span class='fit_label'>" + string + "</span></i>";
	}

	/**
	 * @param href
	 *            a string ending up in the anchor href.
	 * @param text
	 *            a string within anchors.
	 * @return the string htmlified as a html link.
	 */
	public static String toHtmlLink(String href, String text) {
		return "<a href='" + href + "'>" + text + "</a>";
	}
	
	
	/**
	 * Procedure that returns the value from a JSON String, using a JSON Path
	 * |setVariable|myVal|response.body|$.domain|
	 * @param variableSource
	 * @param variablePath
	 * @return 
	 */
	public static String extractJsonValue (String variableSource, String variablePath) throws InvalidPathException{			
		String value = null;
		if (JsonPath.read(variableSource, variablePath) != null) {
			value = JsonPath.read(variableSource, variablePath).toString();	
		}
		return value;
	}
	
	/**
	 * 
	 * @param json
	 * @return
	 */
	public static ArrayList<String> getAllJsonKeys(String json, String currentPath){
		ArrayList<String> fields = new ArrayList<String>();
		try {
			if (json.trim().charAt(0) == '{') {
				JSONObject jo = new JSONObject(json);
				Iterator<?> keys = jo.keys();

				while( keys.hasNext() ){
					String key = (String)keys.next();
					if (jo.get(key) instanceof JSONArray) {
						JSONArray ja = (JSONArray)jo.get(key);
						for (int i = 0; i<ja.length(); i++) {
							fields.addAll(getAllJsonKeys(ja.get(i).toString(), currentPath + key + "."));
						}
					}
					if ((jo.get(key) instanceof JSONObject)){
						fields.addAll(getAllJsonKeys(jo.get(key).toString(), currentPath + key + "."));
					} else {
						fields.add(currentPath + key);
					}
				}
			}
			if (json.trim().charAt(0) == '[') {
				JSONArray ja = new JSONArray(json);
				for (int i = 0; i<ja.length(); i++) {
					fields.addAll(getAllJsonKeys(ja.get(i).toString(), currentPath));
				}
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return fields;
	}

	/**
	 * Procedure that returns the value from an XML String, using a XPath
	 * |setVariable|value|xml|/howto/topic[1]/@name|
	 * @param xml
	 * @param xPath
	 * @return
	 * @throws Exception
	 */
	public static String extractXmlValue (String xml, String xPath) throws Exception{		
		String value = null;
		InputStream is = new ByteArrayInputStream(xml.getBytes("UTF8"));
		XPath xpath = XPathFactory.newInstance().newXPath();
		InputSource inputSource = new InputSource(is);
		value = xpath.evaluate(xPath, inputSource);
		return value;
	}
	
	/**
	 * @return modified
	 */
	public static String variableReplacer (String input) {
		String output = input;
		String regex = "\\$\\#[a-zA-Z0-9]+\\#\\#";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(output);

		while (matcher.find()){
			String replace = matcher.group();
			String variableName = replace.replace("##", "").replace("$#", "");
			if (System.getProperties().containsKey(variableName)) {
				String variableValue = System.getProperty(variableName);
				output = output.replaceAll(Pattern.quote(replace), variableValue);
			}
		}
		return output;
	}
	
	public static <T> List<String> listDiff (List<String> list1, List<String> list2) {
		List<String> extra = new ArrayList<String>();
		List<String> test = new ArrayList<String>(list1);
		for (String a: test){
			if (list2.contains(a)){
				list1.remove(a);	
				list2.remove(a);
			}
		}
		for (String a: list1) {
			extra.add("-"+a);
		}
		for (String e: list2) {
			extra.add("+"+e);
		}
		return extra;
	}
	
}
