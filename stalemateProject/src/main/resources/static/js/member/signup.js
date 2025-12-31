const checkObj = {
  "memberId" : false,
  "memberPw" : false,
  "memberPwPermit" : false,
  "memberName" : false,
  "memberPhone" : false,
  "authKey" : false,
  "agreeTerms" : false

};

const sendAuthKeyBtn = document.querySelector("#sendAuthKeyBtn");
const memberId =  document.querySelector("#memberId");
const emailMessage = document.querySelector("#emailMessage");

memberId.addEventListener("input", function() {

  const inputId = memberId.value.trim();

  if(inputId.length === 0){
    emailMessage.innerText = "아이디(이메일)을 입력해주세요!";
    checkObj.memberId = false;
    emailMessage.classList.add("error");
    emailMessage.classList.remove("confirm");
    return;
  }

  const regExp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

  if(!regExp.test(inputId)) {
    emailMessage.innerText = "알맞은 아이디(이메일) 형식으로 입력해주세요!";
    checkObj.memberId = false;
    emailMessage.classList.add("error");
    emailMessage.classList.remove("confirm");
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
      emailMessage.innerText = "사용 가능한 아이디(이메일) 입니다.";
      emailMessage.classList.add("confirm");
      emailMessage.classList.remove("error");
      checkObj.memberId = true;
    }
    else{
      emailMessage.innerText = "중복된 아이디(이메일) 입니다.";
      emailMessage.classList.add("error");
      emailMessage.classList.remove("confirm");
      checkObj.memberId = false;
      return;
    }
  }).catch( error => {
    console.log(error);
  });
});

const memberPw = document.querySelector("#memberPw");
const memberPwCheck = document.querySelector("#memberPwCheck");

memberPw.addEventListener("input", function() {

  const inputPw = memberPw.value.trim();

  memberPwCheck.innerText = "";

  if(inputPw.length === 0) {
    memberPwCheck.innerText = "비밀번호를 입력해주세요";
    memberPwCheck.classList.add("error");
    memberPwCheck.classList.remove("confirm");
    return;
  }

  const regExp = /^[가-힣A-Za-z0-9]{8,15}$/;

  if(!regExp.test(inputPw)) {
    memberPwCheck.innerText = "알맞은 형식으로 입력해주세요!";
    memberPwCheck.classList.add("error");
    memberPwCheck.classList.remove("confirm");
    return;
  }

  memberPwCheck.innerText = "유효한 비밀번호 형식입니다!";
  memberPwCheck.classList.add("confirm");
  memberPwCheck.classList.remove("error");
  checkObj.memberPw = true;

});

const memberPwConfirm = document.querySelector("#memberPwConfirm");
const memberPwConfirmCheck = document.querySelector("#memberPwConfirmCheck");

memberPwConfirm.addEventListener("input", function() {

  const inputPw = memberPw.value.trim();
  const inputPwConfirm = memberPwConfirm.value.trim();

  memberPwConfirmCheck.innerText = "";

  if(inputPw === inputPwConfirm) {
    memberPwConfirmCheck.innerText = "비밀번호가 일치합니다.";
    memberPwConfirmCheck.classList.add("confirm");
    memberPwConfirmCheck.classList.remove("error");
    checkObj.memberPwPermit = true;

    return;
  }

  memberPwConfirmCheck.innerText = "비밀번호가 일치하지 않습니다.";
  memberPwConfirmCheck.classList.add("error");
  memberPwConfirmCheck.classList.remove("confirm");
  checkObj.memberPwPermit = false;

});


const memberName = document.querySelector("#memberName");
const nickNameMessage = document.querySelector("#nickNameMessage");

memberName.addEventListener("input", function() {
  const inputName = memberName.value.trim();

  if(inputName.length === 0) {
    nickNameMessage.innerText = "닉네임을 입력해주세요.";
    checkObj.memberName = false;
    nickNameMessage.style.color = "red";
    return;
  }

  const regExp = /^[가-힣A-Za-z0-9]{1,10}$/;

  if(!regExp.test(inputName)) {
    nickNameMessage.innerText = "알맞는 닉네임 형식으로 입력해주세요!";
    checkObj.memberName = false;
    nickNameMessage.style.color = "red";
    return;
  }

  fetch("/member/checkName?memberName=" + inputName)
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

});

const memberPhone = document.querySelector("#memberPhone");
const memberPhoneCheck = document.querySelector("#memberPhoneCheck");

memberPhone.addEventListener("input", function() {

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
});

const emailAuthMessage = document.querySelector("#emailAuthMessage");
const initMin = 5; 
const initSec = 0; 
const initTime = "05:00";

let min = initMin;
let sec = initSec;
let authTimer;

sendAuthKeyBtn.addEventListener("click", function() {

  checkObj.authKey = false;
  emailAuthMessage.innerText = "";

  if(!checkObj.memberId) {
    alert("유효한 이메일을 작성하세요!");
    
    return;
  }

  min = initMin;
  sec = initSec;

  fetch("/email/signup", {
    method : "POST",
    headers : { "Content-Type": "application/json" },
    body: JSON.stringify({
      email: memberId.value
    })

  })
    .then( resp => resp.text())
    .then( result => {
      if(result == 1) {
        console.log("인증 번호 전송");

      }else{
        console.log("인증 번호 실패");
      } 

    });

  sendAuthKeyBtn.disabled = true;

  emailAuthMessage.innerText = initTime;

  alert("인증번호 발송완료! 시간안에 입력해주세요");

  clearInterval(authTimer);

  authTimer = setInterval(function() {

    emailAuthMessage.innerText = `${addZero(min)}:${addZero(sec)}`;

    if(min == 0 && sec == 0) {
      checkObj.authKey = false;
      clearInterval(authTimer);
      emailAuthMessage.classList.add("error");
      emailAuthMessage.classList.remove("confirm");
      alert("인증 제한시간 초과! 다시 번호를 발급받아주세요")
      sendAuthKeyBtn = false;

      return;
    }

    if(sec == 0) {
      sec = 60;
      min--;
    }

    sec--;
    emailAuthMessage.classList.remove("error");

  }, 1000);

});

function addZero(number) {
  if (number < 10) return "0" + number;
  else return number;
}

const inputAuthkey = document.querySelector("#inputAuthKey");

inputAuthkey.addEventListener("input", function() {

  if(checkObj.authKey) return;

  const inputKey = inputAuthkey.value.trim();

  if(inputKey.length !== 6) {
    emailAuthMessage.innerText = "이메일 인증 오류";
    emailAuthMessage.classList.add("error");
    emailAuthMessage.classList.remove("confirm");
    checkObj.authKey = false;

    return;
  }

  fetch("/email/checkAuthKey", {
    method : "POST",
    headers : { "Content-Type": "application/json" },
    body : JSON.stringify({
      email : memberId.value,
      authKey : inputKey
    })
  })
  .then( resp => resp.text())
  .then( result => {
    if(result == 1){
      emailAuthMessage.innerText = "이메일 인증 성공!";
      emailAuthMessage.classList.add("confirm");
      emailAuthMessage.classList.remove("error");
      clearInterval(authTimer);
      checkObj.authKey = true;

      inputAuthkey.disabled = true;

    }else {
      emailAuthMessage.innerText = "이메일 인증 오류";   
      emailAuthMessage.classList.add("error");
      emailAuthMessage.classList.remove("confirm");
      checkObj.authKey = false;

    }

  })

});

const agreeTerms = document.querySelector("#agreeTerms");

agreeTerms.addEventListener("change", function() {

  checkObj.agreeTerms = true;
});

const signupForm = document.querySelector("#signup-form");

signupForm.addEventListener("submit", function(e){

  for(let key in checkObj) {

    if(!checkObj[key]) {
      
      let str;

      switch(key) {
        case "memberId" : 
          str = "유효한 아이디가 아닙니다"; 
          document.getElementById("memberId").focus();
          break;
        
        case "memberPw" :
          str = "유효한 비밀번호가 아닙니다";
          document.getElementById("memberPw").focus();
          break;

        case "memberPwPermit" :
          str = "비밀번호가 일치하지 않습니다";
          document.getElementById("memberPwConfirm").focus();
          break;

        case "memberName" :
          str = "유효한 닉네임이 아닙니다";
          document.getElementById("memberName").focus();
          break;
        
        case "memberPhone" :
          str = "유효한 전화번호가 아닙니다";
          document.getElementById("memberPhone").focus();
          break;
          
        case "authKey" :
          str = "유효한 인증번호가 아닙니다";
          document.getElementById("inputAuthKey").focus();
          sendAuthKeyBtn.disabled = false;
          break;

        case "agreeTerms" :
          str = "이용약관 및 개인정보처리방침에 동의해주세요";
          document.getElementById("agreeTerms").focus();
          break;
      }

      alert(str);

      e.preventDefault();
      
      return;
    }
  }
});

