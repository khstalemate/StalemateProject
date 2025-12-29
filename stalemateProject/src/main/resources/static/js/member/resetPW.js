const checkObj = {
  "memberCheck" : false,
  "authKey" : false
};


const memberId = document.querySelector("#memberId");
const memberPhone = document.querySelector("#memberPhone");
const sendAuthKeyBtn = document.querySelector("#sendAuthKeyBtn");

sendAuthKeyBtn.addEventListener("click", function() {

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

})