const sendBtn = document.getElementById("sendBtn");
const messageInput = document.getElementById("messageInput");
const chatBox = document.getElementById("chatBox");
const clearBtn = document.getElementById("clearBtn");

sendBtn.addEventListener("click", sendMessage);

messageInput.addEventListener("keydown", function (event) {
if (event.key === "Enter") {
sendMessage();
}
});

async function sendMessage() {
const message = messageInput.value.trim();

if (!message) {
    return;
}

addMessage(message, "user");
messageInput.value = "";
sendBtn.disabled = true;

try {
    
const response = await fetch("/api/chat", {
    method: "POST",
    headers: {
        "Content-Type": "text/plain"
    },
    body: message
});

    const reply = await response.text();

    if (!response.ok) {
        throw new Error(reply || "Request failed");
    }

    addMessage(reply, "bot");

} catch (error) {
    addMessage(
        "Sorry, server se connection nahi ho pa raha. Please try again.",
        "bot"
    );
    console.error("Chat error:", error);

} finally {
    sendBtn.disabled = false;
    messageInput.focus();
}

}

function addMessage(message, sender) {
const messageDiv = document.createElement("div");
messageDiv.classList.add("message");

const label = document.createElement("strong");
label.textContent = sender === "user" ? "You:" : "CHATNOVA:";

const paragraph = document.createElement("p");
paragraph.textContent = message;

messageDiv.classList.add(
    sender === "user" ? "user-message" : "bot-message"
);

messageDiv.appendChild(label);
messageDiv.appendChild(paragraph);

chatBox.appendChild(messageDiv);
chatBox.scrollTop = chatBox.scrollHeight;

}

clearBtn.addEventListener("click", function () {
chatBox.innerHTML = "";

const welcomeDiv = document.createElement("div");
welcomeDiv.classList.add("message", "bot-message");

const label = document.createElement("strong");
label.textContent = "CHATNOVA:";

const paragraph = document.createElement("p");
paragraph.textContent = "Hello! 👋 I'm CHATNOVA. How can I help you?";

welcomeDiv.appendChild(label);
welcomeDiv.appendChild(paragraph);
chatBox.appendChild(welcomeDiv);

});