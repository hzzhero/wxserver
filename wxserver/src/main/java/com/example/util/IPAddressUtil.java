package com.example.util;


import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;



public class IPAddressUtil {
	
	public static Map<String,String> ipMapCache;//IP映射关系map
	
	public static boolean isIPv6(String address) {
		boolean result = false;
		if (address != null) {
			String regHex = "(\\p{XDigit}{1,4})";
			// 没有双冒号
			String regIPv6Full = "^(" + regHex + ":){7}" + regHex + "$";

			// 双冒号在中间或者没有双冒号
			String regIPv6AbWithColon = "^(" + regHex + "(:|::)){0,6}" + regHex
					+ "$";
			// 双冒号开头
			String regIPv6AbStartWithDoubleColon = "^(" + "::(" + regHex
					+ ":){0,5}" + regHex + ")$";
			String regIPv6 = "^(" + regIPv6Full + ")|("
					+ regIPv6AbStartWithDoubleColon + ")|("
					+ regIPv6AbWithColon + ")$";

			// 下面还要处理地址为::的情形和地址包含多于一个的::的情况（非法）
			if (address.indexOf(":") != -1) {
				if (address.length() <= 39) {
					String addressTemp = address;
					int doubleColon = 0;
					if (address.equals("::"))
						return true;
					while (addressTemp.indexOf("::") != -1) {
						addressTemp = addressTemp.substring(addressTemp
								.indexOf("::") + 2, addressTemp.length());
						doubleColon++;
					}
					if (doubleColon == 0) {
						result = address.matches(regIPv6Full);
					} else if (doubleColon == 1) {
						result = address.matches(regIPv6);
					}
				}
			}
		}
		return result;
	}

	public static boolean isIPv4(String address) {
		if (address != null) {
			Pattern p = Pattern
					.compile("(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])\\."
							+ "(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])\\."
							+ "(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])\\."
							+ "(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])");
			Matcher m = p.matcher(address);
			return m.matches();
		}
		return false;
	}
	
	public static String replaceFirstIpAndPort(String address, String replacement){
		if (address != null && replacement != null) {
			Pattern p = Pattern
					.compile("(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])\\."
							+ "(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])\\."
							+ "(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])\\."
							+ "(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9]):[0-9]{1,}");
			Matcher m = p.matcher(address);
			if(m.find()){//有端口
				return m.replaceFirst(replacement);
			}else{//默认端口
				p = Pattern
				.compile("(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])\\."
						+ "(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])\\."
						+ "(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])\\."
						+ "(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])");
				m = p.matcher(address);
				return m.replaceFirst(replacement);
			}
		}
		return address;
	}
	
	public static String getIp(String address){
		if (address != null) {
			Pattern p = Pattern
					.compile("(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])\\."
							+ "(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])\\."
							+ "(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])\\."
							+ "(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])");
			Matcher m = p.matcher(address);
			String ip = "";
			while(m.find()){
				ip += m.group();
			}
			return ip;
		}
		return address;
	}
	
	public static String getPort(String address){
		if (address != null) {
			if(address.indexOf(":",address.indexOf("/")) == -1){//80端口，address里没有指明端口
				return "80";
			}
			return address.substring(address.indexOf(":",address.indexOf("/"))+1, address.indexOf("/", address.indexOf(":",address.indexOf("/"))));
		}
		return address;
	}


	public static String activeIPv6(String ip_address) {
		if (ip_address != null && !ip_address.equals("")) {
			if (isIPv6(ip_address))
				ip_address = "[" + ip_address + "]";
		}
		return ip_address;
	}

	public static boolean isIpAddress(String ip_address) {
		if (isIPv4(ip_address) || isIPv6(ip_address)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ipv6 地址转有符号byte[16]
	 */
	public static byte[] ipv6ToBytes(String ipv6) {
		byte[] ret = new byte[16];
		int ib = 16 - 1;
		boolean comFlag = false;// ipv4混合模式标记
		if (ipv6.startsWith(":"))// 去掉开头的冒号
			ipv6 = ipv6.substring(1);
		String groups[] = ipv6.split(":");
		for (int ig = groups.length - 1; ig > -1; ig--) {// 反向扫描
			if (groups[ig].contains(".")) {
				// 出现ipv4混合模式
				byte[] temp = ipv4ToBytes(groups[ig]);
				ret[ib--] = temp[4];
				ret[ib--] = temp[3];
				ret[ib--] = temp[2];
				ret[ib--] = temp[1];
				comFlag = true;
			} else if ("".equals(groups[ig])) {
				// 出现零长度压缩,计算缺少的组数
				int zlg = 9 - (groups.length + (comFlag ? 1 : 0));
				while (zlg-- > 0) {// 将这些组置0
					ret[ib--] = 0;
					ret[ib--] = 0;
				}
			} else {
				int temp = Integer.parseInt(groups[ig], 16);
				ret[ib--] = (byte) temp;
				ret[ib--] = (byte) (temp >> 8);
			}
		}
		return ret;
	}

	/**
	 * ipv4地址转有符号byte[4]
	 */
	public static byte[] ipv4ToBytes(String ipv4) {
		byte[] ret = new byte[4];
		// 先找到IP地址字符串中.的位置
		int position1 = ipv4.indexOf(".");
		int position2 = ipv4.indexOf(".", position1 + 1);
		int position3 = ipv4.indexOf(".", position2 + 1);
		// 将每个.之间的字符串转换成整型
		ret[0] = (byte) Integer.parseInt(ipv4.substring(0, position1));
		ret[1] = (byte) Integer.parseInt(ipv4.substring(position1 + 1,
				position2));
		ret[2] = (byte) Integer.parseInt(ipv4.substring(position2 + 1,
				position3));
		ret[3] = (byte) Integer.parseInt(ipv4.substring(position3 + 1));
		return ret;
	}

	public static List<String> getAllLocalHostIP() {
		List<String> list = new ArrayList<String>();
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface
					.getNetworkInterfaces();
			
			if(netInterfaces!=null){
			 while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					list.add(ips.nextElement().getHostAddress());
				}
			 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Collection<InetAddress> getAllHostAddress()
	{
		try
		{
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			Collection<InetAddress> addresses = new ArrayList<InetAddress>();

			while (networkInterfaces.hasMoreElements())
			{
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
				while (inetAddresses.hasMoreElements())
				{
					InetAddress inetAddress = inetAddresses.nextElement();
					addresses.add(inetAddress);
				}
			}

			return addresses;
		}
		catch (SocketException e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static Collection<String> getAllNoLoopbackAddresses()
	{
		Collection<String> noLoopbackAddresses = new ArrayList<String>();
		Collection<InetAddress> allInetAddresses = getAllHostAddress();

		for (InetAddress address : allInetAddresses)
		{
			if (!address.isLoopbackAddress() && address instanceof Inet4Address)
			{
				noLoopbackAddresses.add(address.getHostAddress());
			}
		}

		return noLoopbackAddresses;
	}
	
	public static boolean isInnerIP(String ipAddress)
	{
		boolean isInnerIp = false;
		long ipNum = getIpNum(ipAddress);
		/**
		 * 私有IP：A类 10.0.0.0-10.255.255.255 B类 172.16.0.0-172.31.255.255 C类
		 * 192.168.0.0-192.168.255.255 当然，还有127这个网段是环回地址
		 **/
		long aBegin = getIpNum("10.0.0.0");
		long aEnd = getIpNum("10.255.255.255");
		long bBegin = getIpNum("172.16.0.0");
		long bEnd = getIpNum("172.31.255.255");
		long cBegin = getIpNum("192.168.0.0");
		long cEnd = getIpNum("192.168.255.255");
		isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd) || ipAddress.equals("127.0.0.1");
		return isInnerIp;
	}
	
	public static boolean CmdExcute(String cmdIp) {
		boolean result = false;
		try {
			if (InetAddress.getByName(cmdIp).isReachable(3000)) {
				result = true;
			} else {
				if (isIPv4(cmdIp)) {
					result = InetAddress.getByAddress(ipv4ToBytes(cmdIp))
							.isReachable(3000);
				} else if (isIPv6(cmdIp)) {
					result = InetAddress.getByAddress(ipv6ToBytes(cmdIp))
							.isReachable(3000);
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
	// 模拟telnet
	public static String simuTelnetGetIp(String targetIp, int targetPort) {
		Socket server = new Socket();
		try {
			server.connect(new InetSocketAddress(targetIp, targetPort), 3000); // 等待3秒超时
			return server.getLocalAddress().getHostAddress();
		} catch (IOException e) {
			return null;
		}
	}
	
	private static long getIpNum(String ipAddress)
	{
		String[] ip = ipAddress.split("\\.");
		long a = Integer.parseInt(ip[0]);
		long b = Integer.parseInt(ip[1]);
		long c = Integer.parseInt(ip[2]);
		long d = Integer.parseInt(ip[3]);

		long ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
		return ipNum;
	}
	
	private static boolean isInner(long userIp, long begin, long end)
	{
		return (userIp >= begin) && (userIp <= end);
	}
	
	public static void main(String arg[]) {
		// String ipv6 = "::";
		// String ipv4 = "10.18.13.244";
		// System.out.println("isIPv6=" + isIPv6(ipv6));
		// System.out.println("isIPv4=" + isIPv4(ipv4));
		// System.out.println("activeIPv6=" + activeIPv6(ipv4));
		// List<String> list = getAllLocalHostIP();
		// for(String s:list){
		// System.out.println("local IP=" + s);
		// }
//		CmdExcute("10.10.3.220");
//		System.err.println(getIp("http://10.23.22.12:8080/sdfk/10.22.22.22"));
		replaceFirstIpAndPort("http://10.23.22.12/sdfk/10.22.22.22","aaaaaa");
	}

	/**
	 * 检查是否需要做ip映射转换
	 * @param requestIpAndPort
	 * @return
	 */
	public static boolean needTransform(String requestIpAndPort) {
		if(!StringUtils.isNullOREmpty(requestIpAndPort) && ipMapCache.containsValue(requestIpAndPort)){//请求的IP属于公安网，需要转换
			return true;
		}
		return false;
	}
	
	
	public static String getIpAddr(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取本机ip，通用win、linux
	 * @return
	 */
	public static String getLocalhostIp(){
		String retIp = "";
		InetAddress ip = null;
		String os = System.getProperty("os.name");  
		if(os.toLowerCase().startsWith("win")){  //如果是win系统
			InetAddress addr;
			try {
				addr = InetAddress.getLocalHost();
				retIp = addr.getHostAddress().toString();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			
		}else {//否则linux
			try {
				 boolean bFindIP = false;
			       Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
			         .getNetworkInterfaces();
			       while (netInterfaces.hasMoreElements()) {
			    	   if(bFindIP)
			    		   break;
			    	   NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
			    	   //----------特定情况，可以考虑用ni.getName判断
			    	   //遍历所有ip
			    	   Enumeration<InetAddress> ips = ni.getInetAddresses();
			    	   while (ips.hasMoreElements()) {
			    		   ip = (InetAddress) ips.nextElement();
			    		   if( ip.isSiteLocalAddress() && !ip.isLoopbackAddress()   //127.开头的都是lookback地址
			                    && ip.getHostAddress().indexOf(":")==-1){
			    			   bFindIP = true;
			    			   break; 
			    		   }
			    	   }
			  
			       }
			     }catch (Exception e) {
			    	  e.printStackTrace();
			     }
			  
			     if(null != ip){
			    	 retIp = ip.getHostAddress();
			     }

		}
       System.out.println("[!]oprate system is " + os + ";ip is " + retIp+"[!]");
        return retIp;
	}
}
