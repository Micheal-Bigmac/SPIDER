/**
 * @author Fire Dragon
 * @Date 2014-5-13
 */

/*
 * ������������ȡ120ask��ҽ����ҳ��Ϣ��ʾ����ҳ��
 * 			http://www.120ask.com/user/p/4rit7riIakRlY5
 * 			http://www.120ask.com/user/p/4cpEwvpEnmitx###
 * �����б����main����鿴������
 * 
 * ��������Զ�Ӧ��Ҫ��ȡ����ҳ������
 * �����get(String url)�����������ǻ�ȡһ����ҳ
 * �����parse()������Ҫ��get()����֮����ã������ǽ�����ҳ�����ݣ���������Ը�ֵ��
 */


package demo_2;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.bcel.generic.NEW;

import mytool.Util;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlBody;
import com.gargoylesoftware.htmlunit.html.HtmlDefinitionDescription;
import com.gargoylesoftware.htmlunit.html.HtmlDefinitionList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlEmphasis;
import com.gargoylesoftware.htmlunit.html.HtmlInlineFrame;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTitle;
import com.gargoylesoftware.htmlunit.html.HtmlUnorderedList;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLIFrameElement;

public class DoctorPage {
	
	WebClient web_client = null;	// htmlunit web client
	HtmlBody body = null;			// �洢��ȡ��������ҳ�����body
	
	String url = null; // url
	String path=null;
	String category = null; // 类别
	String tag=null;
	
	String topic = null; // 科室
	String content = null; // 专长
	String gongxian = null; // 医院
	String  tempDate;
	String reporter;
	int reply_count = -1; // 回答网友问题总数
	int accept_count = -1; // 被采纳总数
	float accept_rate = -1; // 回答采纳率
	int good_count = -1; // 被网友好评数
	float satisfy_rate = -1; // 网友满意度
	
	public DoctorPage(){
		// �˴�����webclient������
		web_client = new WebClient();
		web_client.setAjaxController(new NicelyResynchronizingAjaxController());
		WebClientOptions opt = web_client.getOptions();
		opt.setCssEnabled(false);
		opt.setJavaScriptEnabled(false);
		opt.setThrowExceptionOnScriptError(false);
		opt.setRedirectEnabled(false);
		opt.setTimeout(60000);
	}
	
	public void get(String url) throws Exception {
		HtmlPage p = web_client.getPage(url);
		body = (HtmlBody) p.getBody();
	}
	
	public void parse(){
		if(body==null) {
			System.out.println("����������ҳ��Ȼ���ڽ�����");
			return;
		}
		try {

			HtmlDivision div_name = (HtmlDivision) Util.getElementByAttribute(
					body, "div", "class", "space_b_title");
			if(div_name!=null){
			// 获取url
				try {
					url = div_name.getElementsByTagName("span").get(0).asText();
					url = Util.cleanLine(url); // 去除某句话的噪声，参看Util.cleanLine()函数
					System.out.println(url);
				} catch (Exception e) {
					System.out.println("没有获取到医生的url！");
					e.printStackTrace();
				}
			}

			// 获取存储统计信息的<div>
			HtmlDivision div = (HtmlDivision) Util.getElementByAttribute(body,
					"div", "class", "bg_w mb20");
			if(div!=null){
				// 分类
				try {
					category = div.getElementsByTagName("p").get(0).asText();
					category = Util.cleanLine(category); // 去除某句话的噪声，请看Util类的cleanLine的函数说明
					System.out.println(category);
				} catch (Exception e) {
					System.out.println("没有获取到医生的分类！");
	//				e.printStackTrace();
				}
				// 大标题 题目
				try {
					topic = div.getElementsByTagName("p").get(1).asText();
					topic = Util.cleanLine(topic); // 去除某句话的噪声，请看Util类的cleanLine的函数说明
					System.out.println(topic);
				} catch (Exception e) {
					System.out.println("没有获取到医生的大标题 题目！");
					e.printStackTrace();
				}
			}
			// 获取存储统计信息的很多个<span>
			//
			HtmlDivision div_path= (HtmlDivision) Util.getElementByAttribute(
					body, "div", "class", "crumbs_box");
			if(div_path!=null){
				path=div_path.asText();
				System.out.println(path);
			}
			HtmlDivision div_p = (HtmlDivision) Util.getElementByAttribute(
					body, "div", "class", "pb20");
		
			if(div_p!=null){
				List l = div_p.getElementsByTagName("span");
				System.out.println(l.size()+"size of span");
	
				tempDate=((DomNode) l.get(0)).asText().replace("全网发布：", "");
				System.out.println(tempDate);
	//			SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd hh:mm");
			/*	tempDate=format1.parse(date);
				System.out.println(tempDate);*/
	
			/*	for(String time : date.split(" ")){
					System.out.println(time);
				}*/
				reporter=((DomNode) l.get(1)).asText().replace("发表者：", "");
				// 解析第一个<li>，其中存储着reply_count
				System.out.println(reporter);
			}
			HtmlDivision div_content = (HtmlDivision) Util
					.getElementByAttribute(body, "div", "class",
							"pb20 article_detail");
			if(div_content!=null){
				content=div_content.asText();
	//			System.out.println(content);
				HtmlElement value=Util
						.getElementByAttribute(body, "ul", "class",
								"doc_info_ul");
				HtmlElement element=Util.getElementByAttribute(value, "a", "class", "blue");
				gongxian=element.asText();
				System.out.println(gongxian);
			}

		} catch (Exception e) {
			System.out
					.println("没有获取到医生的department, major, hospital和introduction");
			e.printStackTrace();
		}
	}
	
	public void print(){
		System.out.println(""+reply_count);
		System.out.println(""+accept_count);
		System.out.println(""+accept_rate);
		System.out.println(""+good_count);
		System.out.println(""+satisfy_rate);
	
	}

	public static void main(String[] args) throws IOException {
		try {
			String url = "file:///e:/sample_haodf.htm";
//			String url = "http://www.120ask.com/user/p/4rit7riIakRlY5";
			DoctorPage p = new DoctorPage();
			p.get(url);
			p.parse();
			p.print();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
