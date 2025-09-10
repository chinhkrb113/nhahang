# Restaurant Management System

## Overview

This is a comprehensive restaurant management system built with modern web technologies. The system provides a complete solution for managing restaurant operations including product management, order processing, table reservations, user management, blog management, and more. It features a responsive Angular frontend and a robust Spring Boot backend with JWT authentication and VNPay payment integration.

## Features

### Core Functionality
- **User Management**: Role-based access control (Admin, Moderator, Client)
- **Product Management**: Comprehensive product catalog with categories, colors, sizes, rooms
- **Order Management**: Complete order lifecycle from placement to delivery
- **Table Reservation**: Online booking system for restaurant tables
- **Blog System**: Content management for restaurant news and updates
- **Payment Integration**: VNPay payment gateway support
- **Email Notifications**: Automated email system for orders and confirmations
- **Analytics & Reporting**: Dashboard with charts and statistics
- **Excel Export**: Data export functionality for orders and products

### Technical Features
- JWT-based authentication and authorization
- Responsive design with Bootstrap and PrimeNG
- RESTful API architecture
- MySQL database with JPA/Hibernate
- File upload support for images
- OTP verification system
- Voucher/discount system
- Multi-language support preparation

## Technology Stack

### Frontend
- **Angular 15**: Modern web framework
- **TypeScript**: Type-safe JavaScript
- **Bootstrap 5**: Responsive CSS framework
- **PrimeNG**: Rich UI component library
- **Chart.js**: Data visualization
- **Summernote**: Rich text editor
- **FontAwesome**: Icon library

### Backend
- **Spring Boot 2.7.17**: Java web framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Database access layer
- **MySQL**: Relational database
- **JWT**: JSON Web Tokens for authentication
- **Apache POI**: Excel file processing
- **JavaMail**: Email functionality
- **Lombok**: Code generation

### Database
- **MySQL 8.0+**: Primary database
- **26 Tables**: Comprehensive data model including:
  - User management (users, roles, permissions)
  - Product catalog (products, categories, variants)
  - Order system (orders, order details, status tracking)
  - Reservation system (table bookings)
  - Content management (blogs, policies, settings)
  - Payment and voucher systems

## Project Structure

```
d:/hust/DATN/GT/
├── fe/                          # Frontend (Angular)
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/
│   │   │   │   ├── admin/       # Admin dashboard components
│   │   │   │   ├── client/      # Customer-facing components
│   │   │   │   └── moderator/   # Moderator components
│   │   │   ├── _service/        # Angular services
│   │   │   └── _class/          # TypeScript classes
│   │   └── assets/              # Static assets
│   ├── package.json
│   └── angular.json
├── nhahang/                     # Backend (Spring Boot)
│   ├── src/main/java/
│   │   └── com/example/nhahang/
│   │       ├── controller/      # REST controllers
│   │       ├── entity/          # JPA entities
│   │       ├── repository/      # Data repositories
│   │       ├── service/         # Business logic
│   │       ├── security/        # Security configuration
│   │       ├── model/           # Request/Response models
│   │       └── util/            # Utility classes
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
├── db/                          # Database files
│   └── nhahang.sql              # Database schema
├── diagram/                     # System diagrams
│   ├── ER diagrams
│   ├── UI wireframes
│   ├── Use case diagrams
│   └── Flow diagrams
└── report/                      # Documentation
    ├── Thesis presentation
    └── Project documentation
```

## Installation & Setup

### Prerequisites
- **Java 8+**: Backend runtime
- **Node.js 16+**: Frontend build tools
- **MySQL 8.0+**: Database server
- **Maven 3.6+**: Java dependency management
- **Angular CLI 15+**: Frontend development tools

### Backend Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd d:/hust/DATN/GT
   ```

2. **Database Setup**
   ```bash
   # Create MySQL database
   mysql -u root -p
   CREATE DATABASE nhahang;
   EXIT;

   # Import schema
   mysql -u root -p nhahang < db/nhahang.sql
   ```

3. **Configure Database Connection**
   Edit `nhahang/src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/nhahang
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   ```

4. **Configure Email Settings**
   Update email configuration in `application.properties`:
   ```properties
   spring.mail.username=your_email@gmail.com
   spring.mail.password=your_app_password
   ```

5. **Build and Run Backend**
   ```bash
   cd nhahang
   mvn clean install
   mvn spring-boot:run
   ```
   Backend will start on `http://localhost:8080`

### Frontend Setup

1. **Install Dependencies**
   ```bash
   cd fe
   npm install
   ```

2. **Configure API Endpoint**
   Update API base URL in services if needed (default: `http://localhost:8080`)

3. **Run Development Server**
   ```bash
   npm start
   ```
   Frontend will start on `http://localhost:4200`

## API Documentation

The system provides comprehensive REST API documentation accessible at:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Docs**: `http://localhost:8080/v3/api-docs`

### Key API Endpoints

#### Authentication
- `POST /api/auth/signin` - User login
- `POST /api/auth/signup` - User registration
- `POST /api/auth/signout` - User logout

#### Products
- `GET /api/products` - Get all products
- `POST /api/products` - Create product (Admin)
- `PUT /api/products/{id}` - Update product (Admin)
- `DELETE /api/products/{id}` - Delete product (Admin)

#### Orders
- `GET /api/orders` - Get user orders
- `POST /api/orders` - Create new order
- `PUT /api/orders/{id}/status` - Update order status (Admin/Moderator)

#### Reservations
- `GET /api/datban` - Get table reservations
- `POST /api/datban` - Create reservation
- `PUT /api/datban/{id}` - Update reservation

#### Users
- `GET /api/users` - Get all users (Admin)
- `PUT /api/users/{id}` - Update user profile
- `DELETE /api/users/{id}` - Delete user (Admin)

## User Roles & Permissions

### 1. Admin
- Full system access
- User management
- Product management
- Order management
- System configuration
- Analytics dashboard

### 2. Moderator
- Limited admin access
- Order status management
- Blog management
- Product moderation
- Reservation management

### 3. Client (Customer)
- Browse products
- Place orders
- Make reservations
- View order history
- Manage profile
- Access blog content

## Payment Integration

The system integrates with VNPay payment gateway for secure online payments:

- **Supported Payment Methods**: Credit/Debit cards, Bank transfer
- **Security**: PCI DSS compliant
- **Features**: Payment verification, Refund processing
- **Configuration**: Update VNPay credentials in application properties

## File Upload

- **Maximum File Size**: 50MB per file
- **Supported Formats**: Images (JPG, PNG, GIF)
- **Storage**: Local file system (`src/main/resources/static/photos/`)
- **Features**: Product images, Blog images, User avatars

## Security Features

- **JWT Authentication**: Stateless authentication
- **Password Encryption**: BCrypt hashing
- **CORS Configuration**: Cross-origin resource sharing
- **Input Validation**: Server-side validation
- **SQL Injection Protection**: Parameterized queries
- **XSS Protection**: Input sanitization

## Development

### Running Tests
```bash
# Backend tests
cd nhahang
mvn test

# Frontend tests
cd fe
npm test
```

### Building for Production
```bash
# Backend
cd nhahang
mvn clean package

# Frontend
cd fe
npm run build --prod
```

### Code Quality
- **Backend**: Spring Boot best practices, JPA specifications
- **Frontend**: Angular style guide, TypeScript strict mode
- **Database**: Normalized schema, Foreign key constraints

## Deployment

### Production Checklist
- [ ] Update database credentials
- [ ] Configure email settings
- [ ] Set JWT secret key
- [ ] Configure VNPay credentials
- [ ] Update API endpoints
- [ ] Enable HTTPS
- [ ] Configure file storage
- [ ] Set up monitoring

### Docker Support
The project can be containerized using Docker for easy deployment.

## Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

**Project Author**: Lê Thanh Chính
**Student ID**: 20198209
**Institution**: Hanoi University of Science and Technology (HUST)
**Course**: Software Engineering Thesis (DATN)

## Acknowledgments

- Spring Boot community
- Angular team
- PrimeNG component library
- Bootstrap framework
- MySQL database
- VNPay payment gateway

---

**Note**: This system was developed as part of a software engineering thesis project demonstrating modern web application development practices with full-stack JavaScript/TypeScript and Java technologies.