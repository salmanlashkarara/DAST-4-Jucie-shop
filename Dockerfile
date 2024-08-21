# Use an official Maven image as a parent image
FROM maven:3.8.7-openjdk-18

# Install needed tool for debuging
#RUN microdnf update -y && \
#    microdnf install -y curl iputils && \
#    microdnf clean all

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project's pom.xml and any other necessary files to the container
COPY pom.xml /app/

# Copy the entire source code to the container
COPY src /app/src

# Define the entry point to run the tests
#RUN mvn clean install site -DenvConfig=default -DsuiteFile=juiceShop-testsuit.xml -DremoteExecution=false -Dmaven.wagon.http.pool=false -Dhttp.keepAlive=false allure:report

# Keep container alive
#CMD ["tail", "-f", "/dev/null"]