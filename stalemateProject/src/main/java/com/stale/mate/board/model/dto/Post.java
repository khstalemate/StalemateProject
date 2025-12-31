package com.stale.mate.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
	private int postNo;
	private String postTitle;
	private String postDate;
	private String category;
	private String status;
	private String postDelete;
	private int views;
	private int adoptFee;
	private String missingDate;
	private String missingTime;
	private String location;
	private String detailLocation;
	private String species;
	private String gender;
	private int age;
	private int weight;
	private String content;
	private int menuNo;
	private int memberNo;
	private String memberName;
	private int replyCount;
	
	public String getSpeciesText() {
        if (species == null) return "알 수 없음";
        switch (species) {
            case "dog": return "개";
            case "cat": return "고양이";
            case "etc": return "기타";
            default: return "알 수 없음";
        }
    }

    public String getGenderText() {
        if (gender == null) return "알 수 없음";
        switch (gender) {
            case "M": return "수컷";
            case "F": return "암컷";
            case "neutering": return "중성화 완료";
            case "unknown": return "알 수 없음";
            default: return "알 수 없음";
        }
    }

    public String getAgeText() {
        switch (age) {
            case 0: return "알 수 없음";
            case 1: return "0~3개월";
            case 2: return "4~6개월";
            case 3: return "7~12개월(1살)";
            case 4: return "13~18개월";
            case 5: return "19~24개월(2살)";
            case 6: return "2살~3살";
            case 7: return "3살~4살";
            case 8: return "4살~5살";
            case 9: return "5살~6살";
            case 10: return "6살~7살";
            case 11: return "7살~8살";
            case 12: return "8살 이상";
            default: return "알 수 없음";
        }
    }

    public String getWeightText() {
        switch (weight) {
            case 0: return "알 수 없음";
            case 1: return "초소형(3kg 미만)";
            case 2: return "소형(3~8kg)";
            case 3: return "중형(8~15kg)";
            case 4: return "대형(25~40kg)";
            case 5: return "초대형(40kg 초과)";
            default: return "알 수 없음";
        }
    }
}