package com.lynx.geo.util;

import com.lynx.geo.entity.*;
import com.lynx.geo.entity.Cell.CellType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 * @addtime 13-8-9 上午9:37
 */
public class FormatUtil {

	/**
	 * parse cell from incoming data
	 * 
	 * @param type
	 * @param data
	 * @return
	 */
	public static List<Cell> parseCells(CellType type, String data) {
		List<Cell> cells = null;
		switch (type) {
		case CDMA:
			try {
				cells = new ArrayList<Cell>();
				String[] tmp = data.split("\\,");
				int mcc = Integer.parseInt(tmp[0]);
				int sid = Integer.parseInt(tmp[1]);
				int nid = Integer.parseInt(tmp[2]);
				int bid = Integer.parseInt(tmp[3]);
				long lat = Long.parseLong(tmp[4]);
				long lng = Long.parseLong(tmp[5]);
				CDMACell cdmaCell = new CDMACell(mcc, sid, nid, bid, lat, lng);
				cells.add(cdmaCell);
			} catch (Exception e) {
				cells = null;
			}
			break;
		case GSM:
			try {
				cells = new ArrayList<Cell>();
				String[] tmp1 = data.split("\\|");
				for (String tmp2 : tmp1) {
					String[] tmp3 = tmp2.split("\\,");
					int mcc = Integer.parseInt(tmp3[0]);
					int mnc = Integer.parseInt(tmp3[1]);
					int lac = Integer.parseInt(tmp3[2]);
					int cid = Integer.parseInt(tmp3[3]);
					int asu = Integer.parseInt(tmp3[4]);
					GSMCell gsmCell = new GSMCell(mcc, mnc, lac, cid, asu);
					cells.add(gsmCell);
				}
			} catch (Exception e) {
				cells = null;
			}
			break;
		default:
			cells = null;
			break;
		}
		return cells;
	}

	/**
	 * parse wifi from incoming data
	 * 
	 * @param data
	 * @return
	 */
	public static List<Wifi> parseWifi(String data) {
		List<Wifi> wifis = null;
		try {
			String[] tmp1 = data.split("\\|");
			wifis = new ArrayList<Wifi>();
			for (String tmp : tmp1) {
				String[] tmp2 = tmp.split("\\,");
				int dbm = Integer.parseInt(tmp2[2]);
				Wifi wifi = new Wifi(tmp2[0], tmp2[1], dbm);
				wifis.add(wifi);
			}
		} catch (Exception e) {
			wifis = null;
		}
		return wifis;
	}

	/**
	 * convert str with format province|city|region|street|streeNo to Address
	 * object
	 * 
	 * @param str
	 * @return
	 */
	public static Address str2address(String str) {
		try {
			String[] tmp = str.split("\\|");
			return new Address(tmp[0], tmp[1], tmp[2], tmp[3], tmp[4]);
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * convert address object to str with format
	 * province|city|region|street|streeNo
	 * 
	 * @param addr
	 * @return
	 */
	public static String address2str(Address addr) {
		return String.format("%s|%s|%s|%s|%s", addr.getProvince(),
				addr.getCity(), addr.getRegion(), addr.getStreet(),
				addr.getNum());
	}

	public static String stream2string(InputStream instream, String encoding)
			throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = instream.read()) != -1) {
			baos.write(i);
		}

		return new String(baos.toByteArray(), encoding);
	}

	public static String fromUnicode(String unicode) {
		char ch;
		int len = unicode.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			ch = unicode.charAt(x++);
			if (ch == '\\') {
				ch = unicode.charAt(x++);
				if (ch == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						ch = unicode.charAt(x++);
						switch (ch) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + ch - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + ch - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + ch - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed      encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (ch == 't') {
						ch = '\t';
					} else if (ch == 'r') {
						ch = '\r';
					} else if (ch == 'n') {
						ch = '\n';
					} else if (ch == 'f') {
						ch = '\f';
					}
					outBuffer.append(ch);
				}
			} else {
				outBuffer.append(ch);
			}
		}
		return outBuffer.toString();
	}

	/**
	 * 把中文转成Unicode码
	 * 
	 * @param str
	 * @return
	 */
	public static String chinaToUnicode(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int chr1 = (char) str.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
				result += "\\u" + Integer.toHexString(chr1);
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}

	/**
	 * 判断是否为中文字符
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 将string转为unicode字串
	 * 
	 * @param s
	 * @return
	 */
	public static String string2unicode(String s) {
		try {
			StringBuffer out = new StringBuffer("");
			byte[] bytes = s.getBytes("unicode");
			for (int i = 2; i < bytes.length - 1; i += 2) {
				out.append("u");
				String str = Integer.toHexString(bytes[i + 1] & 0xff);
				for (int j = str.length(); j < 2; j++) {
					out.append("0");
				}
				String str1 = Integer.toHexString(bytes[i] & 0xff);

				out.append(str);
				out.append(str1);
				out.append(" ");
			}
			return out.toString().toUpperCase();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将unicode字串转为string
	 * 
	 * @param unicodeStr
	 * @return
	 */
	public static String unicode2string(String unicodeStr) {
		StringBuffer sb = new StringBuffer();
		String str[] = unicodeStr.toUpperCase().split("U");
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals(""))
				continue;
			char c = (char) Integer.parseInt(str[i].trim(), 16);
			sb.append(c);
		}
		return sb.toString();
	}

	public static double format(double dout, int n) {
		double p = Math.pow(10, n);
		return Math.round(dout * p) / p;
	}
}
