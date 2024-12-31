# Указываем базовый образ - официальный Tomcat 11
FROM tomcat:11.0.2-jdk21

# Копируем файлы нашего веб-приложения в директорию Tomcat
COPY target/learning-session.war /usr/local/tomcat/webapps/ROOT.war

# Expose указывает порт, на котором работает Tomcat (8080)
EXPOSE 8080

# Указываем команду запуска Tomcat
CMD ["catalina.sh", "run"]