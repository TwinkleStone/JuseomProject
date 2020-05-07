package com.team.juseom.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.team.juseom.domain.Book;

public class InsertSearchService {
		public static String get(String apiUrl, Map<String, String> requestHeaders){
	      HttpURLConnection con = connect(apiUrl);
	      try {
	          con.setRequestMethod("GET");
	          for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	              con.setRequestProperty(header.getKey(), header.getValue());
	          }

	          int responseCode = con.getResponseCode();
	          if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	              return readBody(con.getInputStream());
	          } else { // 에러 발생
	              return readBody(con.getErrorStream());
	          }
	      } catch (IOException e) {
	          throw new RuntimeException("API 요청과 응답 실패", e);
	      } finally {
	          con.disconnect();
	      }
		}

		public static HttpURLConnection connect(String apiUrl){
	      try {
	          URL url = new URL(apiUrl);
	          return (HttpURLConnection)url.openConnection();
	      } catch (MalformedURLException e) {
	          throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	      } catch (IOException e) {
	          throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	      }
	  }

	  public static String readBody(InputStream body) throws UnsupportedEncodingException{
	      InputStreamReader streamReader = new InputStreamReader(body, "UTF-8");

	      try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	          StringBuilder responseBody = new StringBuilder();
	          String line;
	          while ((line = lineReader.readLine()) != null) {
	              responseBody.append(line.trim());
	          }
	         
	          return responseBody.toString();
	      } catch (IOException e) {
	          throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	      } catch(Exception e) {
	      	System.out.println("에러");
	      }
	      return null;
	  }
	  
	  public static List<Book> parse(String responseBody){
		  // xml 파싱하기
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      factory.setNamespaceAware(true);
	      Document doc = null;
	      InputSource is = new InputSource(new StringReader(responseBody.toString()));
	      List<Book> bookList = new ArrayList<Book>();
	      try {
		      DocumentBuilder builder = factory.newDocumentBuilder();
		      doc = builder.parse(is);
		      XPathFactory xpathFactory = XPathFactory.newInstance();
		      XPath xpath = xpathFactory.newXPath();
		      // XPathExpression expr = xpath.compile("/response/body/items/item");
		      XPathExpression expr = xpath.compile("//item");
		      NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		      for (int i = 0; i < nodeList.getLength(); i++) {
		          NodeList child = nodeList.item(i).getChildNodes();
		          Book book = new Book();
		          for (int j = 0; j < child.getLength(); j++) {
		              Node node = (Node) child.item(j);
		              String nodeResult = node.getTextContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
		              switch(node.getNodeName()) {
		              	case "title": 
		              		book.setName(nodeResult);
		              	case "author":
		              		book.setAuthor(nodeResult);
		              	case "publisher":
		              		book.setPublisher(nodeResult);
		              	case "pubdate":
		              		book.setDate(nodeResult);
		              	case "price":
		              		book.setPrice(Integer.parseInt(nodeResult));
		              	case "image":
		              		book.setImageUrl(nodeResult);  		
		              }
		          }
		          bookList.add(book);
		      }
		      return bookList;
	      }catch(Exception e) {
	    	  System.out.println("파싱 실패");
	      }
	      return bookList;
	  }
}
