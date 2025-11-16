# 📧 Email Writer – Spring Boot + Gemini API

This project is a **Spring Boot application** that generates professional email replies using **Google Gemini (Generative AI)**.
Users send an email + tone, and the backend returns an AI-generated reply.

---

## 🚀 Features

* Generate professional email replies
* Integrates with **Google Gemini** using `WebClient`
* Structured request & response DTOs
* Clean service + client architecture
* Easily configurable with environment variables

---

## 📁 Project Structure

```
src/main/java/com/email/writer/
│
├── controller/
│   └── EmailGeneratorController.java     # REST endpoint: /api/email/generate
│
├── services/
│   └── EmailGeneratorService.java        # Business logic: prompt building, calling GeminiClient
│
├── client/
│   └── GeminiClient.java                 # Handles prompt formatting & calling Gemini API
│
├── dto/
│   ├── request/
│   │   └── EmailGeneratorRequest.java    # Request for email reply generation
│   ├── response/
│   │   └── GeminiResponse.java           # Maps the Gemini API response
│   └── gemini/
│       ├── Content.java
│       ├── Part.java
│       └── Candidates.java               # Internal Gemini mapping
│
└── EmailWriterApplication.java           # Main Spring Boot entry point
```

---

## ⚙️ Tech Stack

* **Java 17+**
* **Spring Boot 3+**
* **Spring WebFlux (`WebClient`)**
* **Lombok**
* **Google Gemini API (generateContent endpoint)**

---

## 🔧 Setup Instructions (Run Locally)

### **1. Clone the repository**

```bash
git clone https://github.com/<your-username>/email-writer.git
cd email-writer
```

---

### **2. Add Required Environment Variables**

Create a file:

```
email-writer/src/main/resources/application.properties
```

Add:

```properties
gemini.api.url=https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent
gemini.api.key=YOUR_API_KEY_HERE
```

---

### **3. Build the project**

```bash
mvn clean install
```

---

### **4. Run the project**

```bash
mvn spring-boot:run
```

Application will start at:

```
http://localhost:8080
```

---

## 🧪 Testing the API

### **POST /api/email/generate**

**Request Body**

```json
{
  "emailContent": "Please share the updated project status.",
  "tone": "formal"
}
```

**Response**

```
AI-generated professional reply text
```

---

## 🧱 How It Works Internally

### **1. Controller**

Receives request → forwards to service.

### **2. Service Layer**

* Builds prompt
* Builds Gemini JSON request
* Calls GeminiClient
* Extracts the generated reply
* Returns it back to controller

### **3. GeminiClient**

* Uses WebClient
* Sends POST request to Gemini API
* Maps response to `GeminiResponse` DTO

---

## 📦 Example Gemini Request Body Sent

```json
{
  "contents": [
    {
      "parts": [
        {
          "text": "Create a professional reply for..."
        }
      ]
    }
  ]
}
```

---

## 👤 Author

Tanay Kale

---
