const checkObj = {
  "memberCheck" : false,
  "authKey" : false
};


const memberId = document.querySelector("#memberId");
const memberPhone = document.querySelector("#memberPhone");
const sendAuthKeyBtn = document.querySelector("#sendAuthKeyBtn");


sendAuthKeyBtn.addEventListener("click", function() {

  const inputId = memberId.value.trim();
  const inputPhone = memberPhone.value.trim();

  if(inputId.length === 0){

    alert("아이디(이메일)을 입력해주세요!");

    return;
  }

  const regExpId = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

  if(!regExpId.test(inputId)) {
    alert("알맞은 이메일 형식으로 입력해주세요!");

    return;
  }

  if(inputPhone === "") {
    alert("전화번호를 입력해주세요!");

    return;
  }

 const regExpPhone = /^\d{3}-\d{4}-\d{4}$/;

 if(!regExpPhone.test(inputPhone)) {
    alert("알맞은 전화번호 형식으로 입력해주세요!");
    
    return;
  }

  fetch("/member/userCheck", {
    method : "POST",
    headers : { "Content-Type": "application/json" },
    body : JSON.stringify({
      memberId : memberId.value,
      memberPhone : memberPhone.value,
    })
  })
  .then(resp => resp.text())
  .then(result => {
    if(result == 1) {
      alert("인증번호 발송! 이메일을 확인해주세요!");
      checkObj.memberCheck = true;

    }else{
      alert("존재하지 않는 회원정보 입니다.");
    }
  })

});



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



