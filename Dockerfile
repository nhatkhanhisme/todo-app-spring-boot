# Dockerfile

# SỬA LỖI: Dùng Image đã cài sẵn Maven và JDK
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Đặt thư mục làm việc bên trong container
WORKDIR /app

# Copy file pom.xml để tải dependencies (tối ưu hóa lớp cache)
COPY pom.xml .

# Tải dependencies
RUN mvn dependency:go-offline

# Copy toàn bộ mã nguồn
COPY src /app/src

# Biên dịch ứng dụng và tạo file JAR
RUN mvn clean package -DskipTests

# Stage 2: Create the final production image (Sử dụng JRE nhẹ hơn)
FROM eclipse-temurin:17-jre-focal

# Đặt file JAR đã biên dịch từ Stage 1 vào thư mục gốc của Image
COPY --from=build /app/target/*.jar app.jar

# Khai báo cổng ứng dụng sẽ sử dụng
EXPOSE 8080

# Lệnh chạy ứng dụng khi container được khởi động
ENTRYPOINT ["java", "-jar", "app.jar"]
