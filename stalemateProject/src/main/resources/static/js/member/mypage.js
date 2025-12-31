/*
    작성자 : 유건우
    작성일자 : 2025/12/18
    마이페이지 로드 시, 경로 및 기능에 따른 CSS, js 컨트롤
*/

//사이드바 CSS 수정
window.onload = function() {
    let path = window.location.pathname;
    let sidebar = document.querySelectorAll(".sidebar-item");

    if(path==("/myPage/info")) {
        sidebar[0].classList.add("active");
    }
    else if(path==("/myPage/edit")) {
        sidebar[1].classList.add("active");
        pageImageLoad();
    }
    else if(path==("/myPage/changePW")) {
        sidebar[2].classList.add("active");
    }
    else if(path==("/myPage/report")) {
        sidebar[3].classList.add("active");
    }
}


const checkObj = {
    "memberName" : false,
    "memberPhone" : false
};


function pageImageLoad(){
    const preview = document.getElementById("profilePreview").getAttribute("src");
    if (preview !== "") {
        document.getElementById("profilePreview").style.visibility="visible";
        document.getElementsByClassName("profile-delete-icon")[0].style.display = "block";
        document.getElementsByClassName("default-profile")[0].style.visibility = "hidden";
    }
}



//프로필 이미지 미리 보여주기
function previewImage(input){
    const file = input.files[0];
    if (!file) return;

    // 이미지 파일인지 확인
    if (!file.type.startsWith("image/")) {
        alert("이미지 파일만 선택 가능합니다.");
        input.value = "";
        return;
    }
    
    // 구분값 변경 (변경)
    document.getElementById("profileImgStatus").value = "change";

    const preview = document.getElementById("profilePreview");
    const imageUrl = URL.createObjectURL(file);
    preview.src = imageUrl;

    //이미지 활성화
    document.getElementById("profilePreview").style.visibility="visible";
    //삭제 버튼 활성화
    document.getElementsByClassName("profile-delete-icon")[0].style.display = "block";
    //기본 이미지 비활성화
    document.getElementsByClassName("default-profile")[0].style.visibility = "hidden";
}

//프로필 이미지 삭제
function deleteImage(){
    const preview = document.getElementById("profilePreview");
    preview.setAttribute("src", "");

    // 구분값 변경 (삭제)
    document.getElementById("profileImgStatus").value = "delete";

    //해당 영역이 보이면 흰 배경이 보이기에, 비활성화
    document.getElementById("profilePreview").style.visibility="hidden";
    //삭제 버튼 비활성화
    document.getElementsByClassName("profile-delete-icon")[0].style.display = "none";
    //기본 이미지 활성화
    document.getElementsByClassName("default-profile")[0].style.visibility = "visible";
};


//닉네임 중복검사
function checkName(){
    const inputName = memberName.value.trim();
    const nickNameMessage = document.querySelector("#nickNameMessage");

    if(inputName.length === 0) {
        nickNameMessage.innerText = "닉네임을 입력해주세요.";
        checkObj.memberName = false;
        nickNameMessage.style.color = "red";
        
        return;
    }

    const regExp = /^[가-힣A-Za-z0-9]{8,15}$/;

    if(!regExp.test(inputName)) {
        nickNameMessage.innerText = "알맞는 닉네임 형식으로 입력해주세요!";
        checkObj.memberName = false;
        nickNameMessage.style.color = "red";

        return;
    }

    fetch("/myPage/checkName?memberName=" + inputName)
    .then( resp => resp.text())
    .then( result => {
        if( result == 0) {
            nickNameMessage.innerText = "사용 가능한 닉네임 입니다.";
            checkObj.memberName = true;
            nickNameMessage.style.color = "green";
        }
        else{
            nickNameMessage.innerText = "중복된 닉네임 입니다.";
            checkObj.memberName = false;
            nickNameMessage.style.color = "red";
            return;
        }
    }).catch( error => {
        console.log(error);
    });
}

//전화번호 형식 검증
function checkPhoneNumber(){
    const memberPhone = document.querySelector("#memberPhone");
    const memberPhoneCheck = document.querySelector("#memberPhoneCheck");
    const inputPhone = memberPhone.value.trim();

    memberPhoneCheck.innerText = "";

    if(inputPhone === "") {
        memberPhoneCheck.innerText = "전화번호를 입력해주세요.";
        memberPhoneCheck.classList.add("error");
        memberPhoneCheck.style.color = "red";
        checkObj.memberPhone = false;
        return;
    }

    const regExp = /^\d{3}-\d{4}-\d{4}$/;

    if(!regExp.test(inputPhone)) {
        memberPhoneCheck.innerText = "알맞은 형식으로 입력해주세요.";
        memberPhoneCheck.classList.add("error");
        memberPhoneCheck.style.color = "red";
        checkObj.memberPhone = false;
        return;
    }

    memberPhoneCheck.innerText = "유효한 전화번호 형식입니다.";
    memberPhoneCheck.classList.add("confirm");
    memberPhoneCheck.style.color = "green";
    checkObj.memberPhone = true;
}


//내정보 수정
function chageProfile(){
    for(let key in checkObj) {

        if(!checkObj[key]) {
            let str;
        
            switch(key) {
                case "memberName" :
                str = "유효한 닉네임이 아닙니다";
                document.getElementById("memberName").focus();
                break;
                
                case "memberPhone" :
                str = "유효한 전화번호가 아닙니다";
                document.getElementById("memberPhone").focus();
                break;
            }
        
            alert(str);
            return false;
        }
    }
    return true;
}


//변경할 비밀번호 확인
function chagePW(){
    const newPw = document.getElementsByName("newPw")[0].value;
    const newPwConfirm = document.getElementsByName("newPwConfirm")[0].value;
    if( newPw != newPwConfirm) {
        alert("변경할 비밀번호와 비밀번호 확인이 값이 서로 다릅니다.");
        return false;
    }
}


//회원탈퇴
function memberexit() {
    const isConfirmed = confirm("탈퇴 신청시 즉시 탈퇴 처리됩니다.\n정말로 회원 탈퇴를 하시겠습니까?");

    if (isConfirmed) {
        document.getElementById("deleteForm").submit();
    }
    else {
        alert("취소되었습니다.");
    }
}