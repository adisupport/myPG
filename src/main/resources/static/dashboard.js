const body=document.querySelector("body");
sidebar=body.querySelector('.sidebar');
toggle=body.querySelector(".toggle");
toggle=body.querySelector(".toggle");
modeSwitch=body.querySelector(".toggle-switch");
modeText=body.querySelector(".mode-text")


toggle.addEventListener("click" ,() =>{
    sidebar.classList.toggle('close');
})
modeSwitch.addEventListener("click",() =>{
body.classList.toggle("dark");
}
)
document.querySelectorAll('a').forEach(link => {
    link.addEventListener('click', function(event) {
        event.preventDefault(); // Prevent default behavior
        const href = link.getAttribute('href');
        window.location.href = href; // Redirect to the link's href
    });
});