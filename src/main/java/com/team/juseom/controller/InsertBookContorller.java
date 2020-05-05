package com.team.juseom.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.team.juseom.domain.Book;
import com.team.juseom.domain.Share;
import com.team.juseom.service.JuseomFacade;

@Controller
public class InsertBookContorller {
	@ModelAttribute("book")
	public Book formData() {
		return new Book();
	}
	
	@RequestMapping("/shop/insertBook/step1")
	public String insertBookStep1() {
		return "Insert";
	}
	
	/*@RequestMapping(value="/shop/insertBook/step2", method=RequestMethod.GET)
	public String insertBookStep1ForHand() {
		return "bookInsertHand";
	}*/ //수동으로 입력 차후 추가
	
	@RequestMapping("/shop/insertBook/step2")
	public String insertBookStep2(
			@RequestParam("name") String name,
			ModelMap model) {
		String clientId = "";
		String clientSecret = "";
		String text = null;
		try {
			text = URLEncoder.encode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패",e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/book.xml?query="+ text; // xml 결과

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL,requestHeaders);
		
		PagedListHolder<Book> bookList = new PagedListHolder<Book>();
		bookList.setPageSize(4);
		model.put("shareList", bookList);
		return "InsertSearchResult";		
  }

  private static String get(String apiUrl, Map<String, String> requestHeaders){
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

  private static HttpURLConnection connect(String apiUrl){
      try {
          URL url = new URL(apiUrl);
          return (HttpURLConnection)url.openConnection();
      } catch (MalformedURLException e) {
          throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
      } catch (IOException e) {
          throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
      }
  }

  private static String readBody(InputStream body) throws UnsupportedEncodingException{
      InputStreamReader streamReader = new InputStreamReader(body, "UTF-8");

      try (BufferedReader lineReader = new BufferedReader(streamReader)) {
          StringBuilder responseBody = new StringBuilder();
          String line;
          while ((line = lineReader.readLine()) != null) {
              responseBody.append(line.trim());
          }
          // xml 파싱하기
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
          factory.setNamespaceAware(true);
          Document doc = null;
          InputSource is = new InputSource(new StringReader(responseBody.toString()));
          DocumentBuilder builder = factory.newDocumentBuilder();
          doc = builder.parse(is);
          XPathFactory xpathFactory = XPathFactory.newInstance();
          XPath xpath = xpathFactory.newXPath();
          // XPathExpression expr = xpath.compile("/response/body/items/item");
          XPathExpression expr = xpath.compile("//item");
          NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

          for (int i = 0; i < nodeList.getLength(); i++) {
              NodeList child = nodeList.item(i).getChildNodes();
              for (int j = 0; j < child.getLength(); j++) {
                  Node node = (Node) child.item(j);
                  System.out.println("현재 노드 이름 : " + node.getNodeName());
                  System.out.println("현재 노드 타입 : " + node.getNodeType());
                  System.out.println("현재 노드 값 : " + node.getTextContent());
                  System.out.println("현재 노드 네임스페이스 : " + node.getPrefix());
                  System.out.println("현재 노드의 다음 노드 : " + node.getNextSibling());
                  System.out.println("");
              }
          }

          return responseBody.toString();
      } catch (IOException e) {
          throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
      } catch(Exception e) {
      	System.out.println("에러");
      }
      return null;
  }
}