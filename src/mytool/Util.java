/**
 * @author Fire Dragon
 * @Date 2014-5-13
 */

package mytool;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

public class Util {
	
	/*
	 * ��װ��getElementsByAttribute()�����³��������ȡԪ�س��ִ��󣬻��׳��쳣��
	 */
	public static HtmlElement getElementByAttribute(HtmlElement element, String tag, String attribute, String value) throws Exception{
		List<HtmlElement> l = element.getElementsByAttribute(tag, attribute, value);
		if(l.isEmpty()) return null;
		return l.get(0);
	}
	
	/*
	 * ��ȡĳ���ַ�ĵ�һ��������һ������С��ͳ�ȡС����򣬳�ȡ�������ַ����ʽ���ء�
	 */
	public static String cleanNumber(String number){
		if(number.matches(".*[0-9]+\\.[0-9]+.*")){
	        Pattern pattern = Pattern.compile("[0-9]+\\.[0-9]+");  
	        Matcher matcher = pattern.matcher(number);
	        while (matcher.find()) {
	            return matcher.group();
	        }
	        return null;
		}else{
			return number.replaceAll("[^0-9]+", "");
		}
	}
	/*
	 * �����ı����������ȥ����Զ�ȥ�����Ŀ��У�����Ŀո�ᱻ�ϲ�Ϊһ���ո�
	 */
	public static String cleanBlock(String block){
		return block.replaceAll("^[\r\n ]+", "").replaceAll("[\r\n ]+$", "").replaceAll("[ ]+", " ").replaceAll("[\r\n ]+", "\r\n");
	}
	/*
	 * ����һ�仰������ȥ�����еĻ��з�ᱻɾ��Ӷ�ϲ�Ϊһ�У�����Ŀո�ᱻ�ϲ�Ϊһ���ո�
	 */
	public static String cleanLine(String line){
		return line.replaceAll("[\r\n]+", "").replaceAll("^[ ]+", "").replaceAll("[ ]+$", "").replaceAll("[ ]+", " ");
	}
	/*
	 * ���ô˺���󣬳������ͣ����ͣʱ�������ģ������ᳬ��max_seconds�롣
	 */
	public static long sleep(long max_seconds){
		double a = Math.random();
		long t = (long)(a*(double)max_seconds*1000);
		System.out.println("sleep for "+(t/1000.0) + "s");
		try {
			Thread.sleep( t );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return t;
	}
}
