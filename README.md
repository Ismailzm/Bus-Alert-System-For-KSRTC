# 🚌 BusAlert System

A full-stack web application that allows passengers to search KSRTC buses, create bus alerts, and receive notifications when a matching bus becomes available.

## 📌 Project Overview

BusAlert System is designed to improve the passenger experience by reducing the need to repeatedly check for bus availability. Users can register, log in, search buses, create alerts for unavailable routes, and receive notifications when matching buses are added to the system.

---

## ✨ Features

- 👤 User Registration
- 🔐 Secure User Login (BCrypt Password Hashing)
- 📊 Dashboard with Live Statistics
- 🔍 Search Buses by Source & Destination
- 🚨 Create Bus Availability Alerts
- 🔔 Automatic Notification Generation
- 🗺️ Interactive Map with Simulated Bus Movement
- 🚪 Logout Functionality

---

## 🛠️ Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- BCrypt Password Encryption

### Frontend
- HTML5
- CSS3
- JavaScript
- Leaflet.js (Interactive Maps)

### Tools
- Postman
- Git
- GitHub
- MySQL Workbench
- Eclipse / Spring Tool Suite (STS)

---

## 📂 Project Structure

```
BusAlertSystem
│
├── Backend
│   ├── Controller
│   ├── Service
│   ├── Repository
│   ├── Entity
│   ├── DTO
│   └── Configuration
│
├── Frontend
│   ├── HTML
│   ├── CSS
│   ├── JS
│   └── Images
│
└── README.md
```

---

## 🚀 Modules

### User Module
- Register
- Login
- Logout

### Dashboard
- Total Users
- Total Buses
- Matched Alerts
- Notifications

### Bus Module
- Add Bus
- Search Bus
- View Bus Details

### Alert Module
- Create Alert
- Match Available Buses

### Notification Module
- Automatic Notification Generation
- View Notifications

---

## 🗄️ Database

Main Tables:

- users
- bus
- alerts
- notifications

---

## 🔄 Application Workflow

1. User registers.
2. User logs in.
3. Dashboard displays live statistics.
4. User searches for buses.
5. User creates an alert if no suitable bus is available.
6. When a matching bus is added, the system automatically generates a notification.
7. User views notifications.

---

## 📸 Screenshots

- Login Page
- Registration Page
- Dashboard
- Search Bus
- Notifications
- Interactive Map

---

## 🔮 Future Enhancements

- Admin Dashboard
- Live GPS Bus Tracking
- Email & SMS Notifications
- Push Notifications
- Online Ticket Booking
- AI-Based Bus Recommendations

---

## 👨‍💻 Developed By

**Mohammed Ismail**

Bachelor of Engineering (Information Science)

---

## ⭐ GitHub

If you found this project useful, please consider giving it a ⭐ on GitHub.