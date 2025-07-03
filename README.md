# 🤖 openai-kmp

**Kotlin Multiplatform library to interact with OpenAI APIs**, designed for use in Android, iOS, and other Kotlin-supported platforms with a clean and testable interface.

---

## ✨ Features

- ✅ Kotlin Multiplatform (KMP) support
- ✅ Built with Ktor + Kotlinx Serialization

---

## 📦 Installation

Add to your KMP project:

```kotlin
dependencies {
    implementation("com.github.alderfurtado:openai-kmp:<latest-version>")
}
```

---

## 🚀 Usage

First, create an instance of the `OpenAiInput` class. It consists of:

- `openAiModel`: The model type to use ([see available models](https://platform.openai.com/docs/models))
- `content`: The prompt or command you want the AI to respond to
- `instruction`: The way or tone the AI should respond in

```kotlin
val input = OpenAiInput(
    openAiModel = OpenAiModel.GPT_4O,
    content = "Who discovered Brazil?",
    instruction = "Respond like a history teacher"
)
```

Next, create an instance of the OpenAiKmp class. It provides the sendContent function, which requires:

- `openAiInput`: the input object you just created 👆
- `token`: your OpenAI API key [get your API key here](https://platform.openai.com/login)

```kotlin
val response = OpenAiKmp().sendContent(
    openAiInput = input,
    token = "your-openai-api-key"
)
```

Then, receive the responde which consinst of:

- `content`: result of content
- `error`: error if something goes wrong

```kotlin
result.content?.let { 
            // SUCCESS CODE FLOW
        }
        
result.error?.let { 
    // ERROR CODE FLOW
}
```
---

## 📄 Usage Example

```kotlin
val openAiInput = OpenAiInput(
    openAiModel = OpenAiModel.GPT_4O,
    content = "Who discovered Brazil",
    instruction = "Talks as History teacher"
)


val result = OpenAiKmp().sendContent(
    OpenAiInput(
        openAiModel = OpenAiModel.GPT_4O,
        content = "Quem descobriu o Brasil", instruction = "Talks as History teacher"
    ),
    "token" // put your openAi token
)


result.content?.let {
    // SUCCESS CODE FLOW
}

result.error?.let {
    // ERROR CODE FLOW
}
```
---
## License ##
This project is licensed under the MIT License.
