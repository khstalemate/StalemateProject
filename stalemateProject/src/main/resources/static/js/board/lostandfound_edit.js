document.querySelector("#uploadImg").addEventListener("change", (e) => {
  const files = e.target.files;
  const label = document.querySelector(".custom-file-upload");

  if(files.length > 0) {
    // 파일 이름들을 가져와 배열로 만들기
    // .map() : 메서드는 호출한 배열의 모든 요소에 주어진 함수를 호출한 결과로 채운 새로운 배열 생성
    const fileNameList = Array.from(files).map(file => file.name);
    label.innerText = `선택된 파일 : ${fileNameList.join(', ')}`;
  } else {
    label.innerText = "📷 사진 업로드 (클릭하여 파일 선택)";
  }
});