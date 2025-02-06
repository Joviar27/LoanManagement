# Loan Management Application
An application that allows users to view a list of available loans along with detailed information and supporting documents for each loan.

## IMPORTANT
<mark>**The application is built using the latest version of AGP (8.8.0) compatible with Android Studio LadyBug Feature Drop**</mark>

## APK
You can find the compiled apk on the **Release** section of the repository.

## Architecture
This project implements a combination of Clean Architecture and MVVM, separating the application into several layers: data, domain, and presentation. The primary objective is to enhance the application's testability and maintainability by minimizing tightly coupled components and modules.

## Tech Stack
Several tech stack implemented in this project:
- **Kotlin**: Programming language
- **XML**: Create UI imperatively
- **Retrofit**: Library to create a request and receive response from API
- **GsonConverter**: Converter from JSON into Java/Kotlin object
- **OkHttpClient**: Manage connection
- **LoggingInterceptor**: To log connection history on debug build
- **Glide** : Library to easily load image url into ImageView

## Features
- Display list of loan
- Sort the displayed loans based on either amount, purpose, term, or risk
- Display the loan detail like collateral repayment schedule
- Display supporting loan document if available
  
![Image](https://github.com/user-attachments/assets/4ed3b86b-1b15-4cc0-af95-044801512021)
