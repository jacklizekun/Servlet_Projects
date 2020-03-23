package utils;

import java.util.UUID;

public class UploadUtils {

	public static String getUUIDName(String realName){
		int index = realName.lastIndexOf(".");
		if(index==-1){
			return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		}else{
			return UUIDUtils.getId()+realName.substring(index);
		}
	}

	public static String getRealName(String name){
		int index = name.lastIndexOf("\\");
		return name.substring(index+1);
	}

	public static String getDir(String name){
		int i = name.hashCode();
		String hex = Integer.toHexString(i);
		System.out.println(hex);
		int j=hex.length();
		for(int k=0;k<8-j;k++){
			hex="0"+hex;
		}
		return "/"+hex.charAt(0)+"/"+hex.charAt(1)+"/"+hex.charAt(2)+"/"+hex.charAt(3)+"/"+hex.charAt(4)+"/"+hex.charAt(5)+"/"+hex.charAt(6)+"/"+hex.charAt(7);
	}

}
