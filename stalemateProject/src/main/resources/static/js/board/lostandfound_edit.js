document.querySelector("#inputImg").addEventListener("change", (event) => {
  //
  const files = event.target.files;
  const label = document.querySelector(".custom-file-upload");

  if(files.length > 0) {

  }
  if(files.label > 1) {
    const fileName = files[0].name;
    label.textContent = `선택된 파일 : ${fileName}`;
  } else {
    label.textContent = "📷 사진 업로드 (클릭하여 파일 선택)";
  }
});