# Use Gradle with Java 17
FROM gradle:8.5-jdk17

# Set working directory
WORKDIR /app

# Copy all files
COPY . .

# Build the application
RUN gradle build --no-daemon -x test

# Expose port
EXPOSE 4567

# Run the application - make sure class name matches your file
CMD ["gradle", "run", "--no-daemon"]
