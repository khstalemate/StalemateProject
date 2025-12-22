const checkObj = {
  "memberId" : false,
  "memberPw" : false,
  "memberName" : false,
  "memberPhone" : false,
  "authKey" : false
};

const sendAuthKeyBtn = document.querySelector("#sendAuthKeyBtn");
const checkIdBtn = document.querySelector("#checkIdBtn");
const memberId =  document.querySelector("#memberId");

checkIdBtn.addEventListener("click", function(e) {

  const inputId = memberId.value.trim();

  if(inputId.length === 0){

    alert("아이디(이메일)을 입력해주세요!");

    return;
  }

  const regExp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

  if(!regExp.test(inputId)) {
    alert("알맞은 이메일 형식으로 입력해주세요!");

    return;
  }

  fetch("/member/checkId", {
    method : "POST",
    headers : { "Content-Type" : "application/json"},
    body : JSON.stringify({ memberId : inputId })
  })
  .then( resp => resp.text())
  .then( result => {
    if( result == 0){
      alert("사용 가능한 아이디(이메일)입니다!");
      checkObj.memberId = true;

    }else{
      alert("중복된 아이디(이메일)입니다!");

      return;
    }
  }).catch( error => {
    console.log(error);
  });
});

const memberPw = document.querySelector("#memberPw");
const memberPwCheck = document.querySelector("#memberPwCheck");

memberPw.addEventListener("input", function(e) {

  const inputPw = memberPw.value.trim();

  memberPwCheck.innerText = "";

  if(inputPw.length === 0) {
    memberPwCheck.innerText = "비밀번호를 입력해주세요";
    memberPwCheck.classList.add("error");
    memberPwCheck.classList.remove("confirm");
    memberId.value = "";

    return;
  }

  const regExp = /^[가-힣A-Za-z0-9]{1,15}$/;

  if(!regExp.test(inputPw)) {
    memberPwCheck.innerText = "알맞은 형식으로 입력해주세요!";
    memberPwCheck.classList.add("error");
    memberPwCheck.classList.remove("confirm");
    memberId = "";

    return;
  }

  memberPwCheck.innerText = "유효한 비밀번호 형식입니다!";
  memberPwCheck.classList.add("confirm");
  memberPwCheck.classList.remove("error");
  checkObj.memberPw = true;

});


const checkNameBtn = document.querySelector("#checkNameBtn");
const memberName = document.querySelector("#memberName");

checkNameBtn.addEventListener("click", function(e) {
  const inputName = memberName.value.trim();

  if(inputName.length === 0) {
    alert("닉네임을 입력해주세요.");
    
    return;
  }

  const regExp = /^[가-힣A-Za-z0-9]{1,10}$/;

  if(!regExp.test(inputName)) {
    alert("알맞는 닉네임 형식으로 입력해주세요!");

    return;
  }

  fetch("/member/checkName?memberName=" + inputName)
  .then( resp => resp.text())
  .then( result => {

    if( result == 0) {
      alert("사용 가능한 닉네임 입니다!");
      checkObj.memberName = true;

    }else{
      alert("중복된 닉네임 입니다!");

      return;
    }
  }).catch( error => {
    console.log(error);
  });

});

const memberPhone = document.querySelector("#memberPhone");
const memberPhoneCheck = document.querySelector("#memberPhoneCheck");

memberPhone.addEventListener("input", function(e) {

  const inputPhone = memberPhone.value.trim();

  memberPhoneCheck.innerText = "";

  if(inputPhone.value === 0) {
    memberPhoneCheck.innerText = "전화번호를 입력해주세요";
    memberPhoneCheck.classList.add("error");
    memberPhoneCheck.classList.remove("confirm");
    memberPhone = "";

    return;
  }

  const regExp = /^\d{3}-\d{4}-\d{4}$/;

  if(!regExp.test(inputPhone)) {
    memberPhoneCheck.innerText = "알맞은 형식으로 입력해주세요!";
    memberPhoneCheck.classList.add("error");
    memberPhoneCheck.classList.remove("confirm");
    memberPhone = "";
    
    return;
  }

  memberPhoneCheck.innerText = "유효한 전화번호 형식입니다!";
  memberPhoneCheck.classList.add("confirm");
  memberPhoneCheck.classList.remove("error");
  checkObj.memberPhone = true;

});


