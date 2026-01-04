// 로그인 상태인 경우에 글쓰기 버튼 보여주기
const insertBtn = document.querySelector("#insertBtn");

if (insertBtn != null) {
  insertBtn.addEventListener("click", () => {
    location.href = ``;
  });
}

// 실종, 분양 게시판의 총 게시글 개수 가져오기
const allPostCount = document.querySelector("#allPostCount");

function getAllPostCount() {
  fetch("/lostandfound/getAllPostCount")
    .then((resp) => resp.text())
    .then((count) => {
      allPostCount.innerText = count;
    });
}

// 상태가 실종인 게시글의 개수 가져오기
const lostPostCount = document.querySelector("#lostPostCount");

function getLostPostCount() {
  fetch("/lostandfound/getLostPostCount")
    .then((resp) => resp.text())
    .then((count) => {
      lostPostCount.innerText = count;
    });
}

// 상태가 목격인 게시글의 개수 가져오기
const witnessPostCount = document.querySelector("#witnessPostCount");

function getWitnessPostCount() {
  fetch("/lostandfound/getWitnessPostCount")
    .then((resp) => resp.text())
    .then((count) => {
      witnessPostCount.innerText = count;
    });
}

// 오늘 등록한 게시글의 개수 가져오기
const todayPostCount = document.querySelector("#todayPostCount");

function getTodayPostCount() {
  fetch("/lostandfound/getTodayPostCount")
    .then((resp) => resp.text())
    .then((count) => {
      todayPostCount.innerText = count;
    });
}

getAllPostCount();
getLostPostCount();
getWitnessPostCount();
getTodayPostCount();

// 게시글 제목, 게시글 내용 말줄임표
const characters = document.querySelectorAll(".character-limit");

characters.forEach((item) => {
  const limit = item.dataset.limit;
  const originalText = item.textContent.trim();
  if (originalText.length > limit) {
    const resultText = originalText.substring(0, limit - 3) + "...";
    item.textContent = resultText;
  }
});