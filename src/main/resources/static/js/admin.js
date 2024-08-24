console.log("admin user");

document.querySelector("#image_file_input").addEventListener("change", function (event) {
  // Get the selected file
  const file = event.target.files[0];
  
  // Ensure a file is selected
  if (file) {
    const reader = new FileReader();
    
    // Set up the onload event handler to update the image preview
    reader.onload = function (e) {
      document.querySelector("#upload_image_preview").setAttribute("src", e.target.result);
    };
    
    // Read the file as a data URL
    reader.readAsDataURL(file);
  } else {
    // Clear the preview if no file is selected
    document.querySelector("#upload_image_preview").setAttribute("src", "");
  }
});










// console.log("admin user");

// document
//   .querySelector("#image_file_input")
//   .addEventListener("change", function (event) {
//     let file = event.target.files[0];
//     let reader = new FileReader();
//     reader.onload = function () {
//       document
//         .querySelector("#upload_image_preview")
//         .setAttribute("src", reader.result);
//     };
//     reader.readAsDataURL(file);
//   });

 