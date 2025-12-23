/*
    작성자 : 유건우
    작성일자 : 2025/12/18
    마이페이지 로드 시, 경로 및 기능에 따른 CSS, js 컨트롤
*/

//사이드바 CSS 수정
window.onload = function() {
    let path = window.location.pathname;
    let sidebar = document.querySelectorAll(".sidebar-item");

    if(path==("/mypage")) {
        sidebar[0].classList.add("active");
    }
    else if(path==("/mypageedit")) {
        sidebar[1].classList.add("active");
    }
    else if(path==("/mypagepw")) {
        sidebar[2].classList.add("active");
    }
    else if(path==("/mypagereport")) {
        sidebar[3].classList.add("active");
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

    const preview = document.getElementById("profilePreview");
    const imageUrl = URL.createObjectURL(file);
    preview.src = imageUrl;
}

//내정보 수정
function chageProfile(){
    //내정보 수정 로직작성 필요
    alert("프로필이 변경되었습니다.");
}

//비밀번호 변경
function changePW(){
    //비밀번호 변경 로직작성 필요
    alert("비밀번호가 변경되었습니다.");
}

//회원탈퇴
function memberexit() {
    if(confirm("탈퇴 신청시 즉시 탈퇴 처리됩니다.\n정말로 회원 탈퇴를 하시겠습니까?")){
        //탈퇴 로직작성 필요
        alert("회원탈퇴가 완료되었습니다. 감사합니다.");
    }
    else{
        alert("회원탈퇴 취소");
    }
}