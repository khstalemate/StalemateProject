package com.stale.mate.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-28
	 * 사용자가 업로드한 파일 서버에 저장할 때 이름 수정
	 */
	public static int seqNum = 1;
	
	public static String fileRename(String originalFileName, int order) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new Date());
		String number = String.format("%03d", seqNum);
		
		seqNum++;
		if(seqNum == 100) seqNum = 1;
		String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		if(order > 0) {
			return date + "_" + number + "_" + order + ext;
		} else {
			return date + "_" + number + ext;
		}
	}
}
