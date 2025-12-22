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
  })
})

const checkNameBtn = document.querySelector("#checkNameBtn");

checkNameBtn.addEventListener("click", function(e) {
  const inputName = memberName.value.trim();

  if(inputName.length === 0) {
    alert("닉네임을 입력해주세요.");
    
    return;
  }

})


