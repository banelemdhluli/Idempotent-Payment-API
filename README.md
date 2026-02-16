🚀 Idempotent Payment API

👋 Welcome to the Idempotent Payment API — a production-ready Spring Boot REST application that guarantees safe, duplicate-proof payment processing.

----

👤 Author: Banele Mdhluli
💻 Language: Java
🧩 Framework: SpringBoot
🛠️ IDE: IntelliJ

---

## ❗ The Problem This Application Solves

In distributed systems, duplicate requests happen.

Common causes:

Network retries

Client resubmissions

Timeout recoveries

Load balancers retrying failed calls

Without idempotency:

💥 Customers get charged twice

💥 Financial records become corrupted

💥 Reconciliation becomes a nightmare

✅ This API guarantees:

If the same requestId is submitted multiple times:

Only one payment record is created

The same response is returned

No duplicate database entries occur

That is true idempotent payment processing.

---

## ✨ Features

🔐 Idempotent POST /payments endpoint

🗃 Unique database constraint on request_id

🔄 Safe retry handling

🧠 Business-layer validation logic

📦 DTO-based API structure

📊 MySQL persistence

🏗 Clean layered architecture

🧪 Easily testable via Postman or cURL

⚡ Transactional service layer

---

## 🛠 Tools & Technologies Used

☕ Java 17+

🌱 Spring Boot

📦 Spring Data JPA

🐬 MySQL

🧪 Postman

📐 Validation

🔧 Maven

💻 IntelliJ IDEA

---

## Create the Database
CREATE DATABASE payment_system;

## ▶️ HOW TO RUN

1. Download the Idempotent-payment-api file in my repository

2. Open the project in IntelliJ IDEA

3. Wait for Maven dependencies to finish loading

4. Run the application:

5. Open IdempotentPaymentApiApplication.java

Click ▶️ Run

Application will start on:

## http://localhost:8080

---

## 📬 Sample Usage


POST /payments

Request

{
  "requestId": "abc123",
  "amount": 500.00
}


Response

{
  "requestId": "abc123",
  "amount": 500.00,
  "status": "SUCCESS"
}

🔁 Idempotency Test

Send the same request again:

✅ Same response returned

✅ Same status

✅ No new row created in database

Verify:

SELECT * FROM payments;


You will see only one record.

---

## 📝 LICENSE

This project is for educational use and free to modify.
