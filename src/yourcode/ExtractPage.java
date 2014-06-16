package yourcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yourcode.DBqm;
import mytool.HtmlListGenerator;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlBody;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import mytool.Util;
import java.text.DateFormat; //杞棩鏈熸牸寮忓紩鍏ョ殑鍖?
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtractPage {
	
	WebClient web_client = null; // htmlunit web client
	HtmlBody body = null;
	
	public String root=null;
	
	String url = null; // url
	String path=null;
	String category = null; // 类别
	String tag=null;
	
	String topic = null; // 科室
	String content = null; // 专长
	String gongxian = null; // 医院
	Date tempDate=null;
	String reporter;
	int like_count = -1; // 回答网友问题总数
	int hate_count = -1; // 被采纳总数
	int  click_count = -1; // 回答采纳率
	int share_count = -1; // 被网友好评数
	int favor_count = -1; // 网友满意度
	int reply_count=-1;

 
	public ExtractPage() {
		// 鍙彯褰撳綋		
		web_client = new WebClient();
		web_client.setAjaxController(new NicelyResynchronizingAjaxController());
		WebClientOptions opt = web_client.getOptions();
		opt.setCssEnabled(false);
		//闁绘鎳撻崺鍡椻枖閵婏箑澹堥柨娑樼焷椤懏绋夌弧绡磍se
		opt.setJavaScriptEnabled(false);
		opt.setThrowExceptionOnScriptError(false);
		opt.setRedirectEnabled(false);
		opt.setTimeout(60000);
	}

	public static void main(String[] args) {
		// 闁汇垻鍠愰崹姝╰ml闁汇劌瀚弸鍐╃鐠哄搫鐏欓悶娑虫嫹
		HtmlListGenerator.fileroot = "E:\\www.haodf.com";
		ArrayList<String> fileList = HtmlListGenerator.run();
		// 闁活枌鍔嬬花顒傛媼閺夎法绉块柡澶堝劥閸ゆ粓宕鍐殝闁轰胶澧楀畵浣糕攦閹板墎绀夐柛妤?－缂嶅绮╁▎蹇斿?闁挎冻鎷烽柡浣哄瀹撲焦鎯旈幘骞匡拷闁汇劌瀚悡鍥ㄧ▔椤忓嫮鎽熸繛鍫㈩暜缁辨繆绠涢崨娣拷濡炪倧鎷?
		
		// 闁告帗绋戠紓鎾讹拷绾懓鏉?
		ExtractPage extPage = new ExtractPage();
		extPage.root = HtmlListGenerator.fileroot.replace("E:\\www.haodf.com", "");
		
		DBqm m = new DBqm();//閸濆骸鎲栭崨锟芥潻娆撳劥閸掑棙鏂侀崷鈺゛in閸戣姤鏆熼柌宀嬬礉閸愭瑥婀猠xtracpage闁插ain閸戣姤鏆熸稉顓犳畱娴狅絿鐖滈敍鍫滅瑝缁犳鏁為柌濠忕礆缁楋拷鐞涘苯顦甸敍灞肩瘍鐏忚鲸妲竧ry閸撳秹娼伴敍鍫燁劃闁劌鍨庡锟筋瀶閿涳拷
		try{
			m.connect();
		}catch(Exception e){
			e.printStackTrace();
			return;
		}//閸濆骸鎲栭崨锟介弨鎯ф躬main閸戣姤鏆熼柌宀嬬礉閸愭瑥婀猠xtracpage閻ㄥ墖ry閸撳秹娼伴敍鍫燁劃闁劌鍨庣紒鎾存将閿涳拷
		try {
			// 闂侇剙绉村濠氬箥閿熻姤绠掗柣銊ュ缂嶅銇勯崹顐ｇ?濞寸姾顔愮槐婵嬫焻閹邦亪鍤嬮弶鈺傜椤㈡垶寰勯崟顓熷?
			for (int i = 0; i < fileList.size(); i++) {
				// 闊洤鎳橀妴蹇曪拷濡や緡鍞?
				String URL;
				// 闁哄倸娲ｅ▎銏㈡崉椤栨氨绐?
				String strFileName = fileList.get(i);
//				System.out.println(strFileName);
				// 闁哄倸娲ｅ▎銏ゅ触閿燂拷		
				String flName = strFileName.substring(strFileName.lastIndexOf(("\\"), strFileName.length()));
				// 闁兼儳鍢茶ぐ鍢L闁挎稑鐭傞敓鑺ユ交閸ャ劍鐎ù鐘烘硾閹洟骞忛崗鐓庡
			    strFileName = strFileName.replaceAll("\\\\", "/");
			    URL = "http://"+extPage.root + strFileName.substring(HtmlListGenerator.fileroot.length(),strFileName.length());
//				System.out.println("URL"+URL);
				extPage.url = URL;
				
				String localURL = "file:///" + strFileName;
//				System.out.println(localURL);
				// 閻熸瑱绲鹃悗鐣岀磾閹达负锟?
				extPage.Parse(localURL);//extpage闁哄嫷鍨划鍫熺▕閸剛鍚归柨娑虫嫹
				System.out.println("result: ");
//			    m.insert(extPage);
				//m.insert(dp);//閸濆骸鎲栭崨锟介幓鎺戝弳鏉╂瑤閲滅粈杞扮伐閿涘本濡竐xtracpage闁插ain閸戣姤鏆熸稉顓涳拷//閹绘帒鍙嗛弫鐗堝祦鎼存搫绱濋崣鍌濓拷main缁儵锟介崚鐘辩啊閿涘本顒濈悰灞煎敩閻焦鏂佹潻娑樺箵
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.disconnect();//閸濆骸鎲栭崨锟? 閸︹暋xtracpage闁插ain閸戣姤鏆熸稉锟絚atch缂佹挻娼崥搴礉閺?儳婀猣inally闁插矂娼?
		
	}

	private void Parse(String localURL) throws Exception {
		HtmlPage p = web_client.getPage(localURL);
		body = (HtmlBody) p.getBody();
		System.out.println(body);
		// 闁兼儳鍢茶ぐ鍣抜tle闁告粌鐣眔ntent

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
	
				String date=((DomNode) l.get(0)).asText().replace("全网发布：", "");
				SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd hh:mm");
				tempDate=format1.parse(date);
				System.out.println(tempDate);
	
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
		

}
