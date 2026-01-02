document.querySelector("#goToList").addEventListener("click", () => {
  location.href = "/lostandfound/";
});

// 상태 수정
const statusChangeBtn = document.querySelector("#statusChangeBtn");
const statusSelect = document.querySelector("#statusSelect");
statusChangeBtn.addEventListener("click", ()=>{
  if (statusSelect.style.display === "none" || statusSelect.style.display === "") {
        statusSelect.style.display = "inline-block";
        statusSelect.focus();
      } else {
        statusSelect.style.display = "none";
      }
}); 

// 댓글 등록
const insert = document.querySelector("#insertReplyBtn");
const replyContent = document.querySelector("#replyContent");

insert.addEventListener("click", e => {
  if(loginMemberNo == null) {
    alert("로그인 후 이용해주세요.")
    return "common/main";
  }

  const data = {
    "replyContent" : replyContent.value,
    "postNo" : postNo,
    "memberNo" : loginMemberNo
  }

  fetch("/comment", {
    method: "POST",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(data)
  })
  .then(response => response.text())
  .then(result => {
    if(result > 0) {
      alert("댓글이 등록되었습니다.");
      replyContent.value = "";
      // 댓글 목록 조회
    } else {
      alert("댓글 등록에 실패했습니다.");
    }
  })
  .catch(err => console.log(err));
})

// // 댓글 수정
// const updateReply = (postNo, btn) => {
//   const temp = document.querySelector(".update-textare");

// }

// 댓글 삭제
const deleteReply = replyNo => {
  if(!confirm("댓글을 삭제하시겠습니까?")) return;

  fetch("/cooment", {
    method:"DELETE",
    headers : {"Content-Type" : "application/json"},
    body : commentNo
  })
  .then(resp => resp.text())
  .then(result => {
    if (result > 0) {
      alert("댓글이 삭제되었습니다.");
      // 댓글 목록 조회
    } else {
      alert("삭제에 실패하였습니다.");
    } 
  })
  .catch(err => console.log(err));
}