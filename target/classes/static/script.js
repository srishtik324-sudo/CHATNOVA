const sendBtn = document.getElementById("sendBtn");
const messageInput = document.getElementById("messageInput");
const chatBox = document.getElementById("chatBox");
const clearBtn = document.getElementById("clearBtn");

sendBtn.addEventListener("click", sendMessage);

messageInput.addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        sendMessage();
    }
});

function sendMessage() {

    const message = messageInput.value.trim();

    if (message === "") {
        return;
    }

    addMessage(message, "user");

    messageInput.value = "";

    // Temporary response
    setTimeout(function() {

        addMessage(
            "I'm currently being connected to the AI system. 🤖",
            "bot"
        );

    }, 500);
}

function addMessage(message, sender) {

    const messageDiv = document.createElement("div");

    messageDiv.classList.add("message");

    if (sender === "user") {
        messageDiv.classList.add("user-message");
        messageDiv.innerHTML = `<strong>You:</strong><p>${message}</p>`;
    } else {
        messageDiv.classList.add("bot-message");
        messageDiv.innerHTML = `<strong>CHATNOVA:</strong><p>${message}</p>`;
    }

    chatBox.appendChild(messageDiv);

    chatBox.scrollTop = chatBox.scrollHeight;
}

clearBtn.addEventListener("click", function() {

    chatBox.innerHTML = `
        <div class="message bot-message">
            <strong>CHATNOVA:</strong>
            <p>Hello! 👋 I'm CHATNOVA. How can I help you?</p>
        </div>
    `;

});