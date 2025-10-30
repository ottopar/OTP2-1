FROM openjdk:21-slim

WORKDIR /app

# Install GUI libraries and build tools
RUN apt-get update && apt-get install -y \
    libx11-6 libxext6 libxrender1 libxtst6 libxi6 libgtk-3-0 mesa-utils wget unzip maven \
    && rm -rf /var/lib/apt/lists/*

# Download and unzip JavaFX Linux SDK
RUN mkdir -p /javafx-sdk \
    && wget -O javafx.zip https://download2.gluonhq.com/openjfx/20.0.1/openjfx-20.0.1_linux-x64_bin-sdk.zip \
    && unzip javafx.zip -d /javafx-sdk \
    && mv /javafx-sdk/javafx-sdk-20.0.1/lib /javafx-sdk/lib \
    && rm -rf /javafx-sdk/javafx-sdk-20.0.1 javafx.zip

# Install fonts for japanese
RUN apt-get update && \
    apt-get install -y fonts-noto-cjk && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy source and build
COPY pom.xml /app
COPY src /app/src/
RUN mvn package

# Set X11 display
ENV DISPLAY=:0

CMD ["java", "--module-path", "/javafx-sdk/lib", "--add-modules", "javafx.controls", "-cp", "target/OTP2-1-1.0-SNAPSHOT.jar", "ShoppingCartGUI"]