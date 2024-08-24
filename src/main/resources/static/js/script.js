console.log("hello world!");

let currentTheme = getTheme(); // Use let to allow reassignment

document.addEventListener("DOMContentLoaded", () => {
    changePageTheme(currentTheme); // Apply the theme

    const changeThemeButton = document.querySelector('#theme_change_button');
    if (changeThemeButton) {
        changeThemeButton.addEventListener("click", () => {
            console.log("changeThemeButton clicked");
            const newTheme = currentTheme === "dark" ? "light" : "dark";
            changePageTheme(newTheme); // Change theme
            currentTheme = newTheme; // Update current theme
        });
    }
});

function changePageTheme(theme) {
    console.log(`Changing theme to ${theme}`);
    setTheme(theme);

    const htmlElement = document.querySelector("html");
    if (htmlElement) {
        htmlElement.classList.remove("light", "dark"); // Remove both to avoid conflicts
        htmlElement.classList.add(theme); // Add the new theme class
    }

    changeThemeIcons(theme); // Update icons visibility
}

function changeThemeIcons(theme) {
    const lightIcon = document.querySelector("#light_icon");
    const darkIcon = document.querySelector("#dark_icon");

    if (lightIcon && darkIcon) {
        if (theme === "light") {
            lightIcon.classList.remove("hidden");
            darkIcon.classList.add("hidden");
        } else {
            lightIcon.classList.add("hidden");
            darkIcon.classList.remove("hidden");
        }
    }

    const themeButtonSpan = document.querySelector('#theme_change_button span');
    if (themeButtonSpan) {
        themeButtonSpan.textContent = theme === "light" ? "Dark" : "Light";
    }
}

function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}



// console.log("hello world!");

// let currentTheme = getTheme();
// document.addEventListener("DOMContentLoaded", () => {
//     changeTheme();
//     const changeThemeButton = document.querySelector('#theme_change_button');
//     changeThemeButton.addEventListener("click", () => {
//         console.log("changeThemeButton clicked");
//         const oldTheme = currentTheme;
//         if (currentTheme === "dark") {
//             currentTheme = "light";
//         } else {
//             currentTheme = "dark";
//         }
//         changePageTheme(currentTheme, oldTheme);
//     });
// });

// function changeTheme() {
//     changePageTheme(currentTheme, currentTheme);
//     const changeThemeButton = document.querySelector('#theme_change_button');
//     changeThemeButton.querySelector("span").textContent = currentTheme === "light" ? "Dark" : "Light";
// }

// function setTheme(theme) {
//     localStorage.setItem("theme", theme);
// }

// function getTheme() {
//     let theme = localStorage.getItem("theme");
//     return theme ? theme : "light";
// }

// function changePageTheme(theme, oldTheme) {
//     console.log(`Changing theme from ${oldTheme} to ${theme}`);
//     setTheme(theme);
//     document.querySelector("html").classList.remove(oldTheme);
//     document.querySelector("html").classList.add(theme);
//     document.querySelector("#theme_change_button").querySelector("span").textContent = theme === "light" ? "Dark" : "Light";
// }
