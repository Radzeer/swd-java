
window.onload = function() {

    document.querySelector("#submit_btn").onclick = function() {
        const name = document.querySelector("#name").value;
        document.querySelector("#message_div").innerHTML = "Hello " + name + "!";
    }

}