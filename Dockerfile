# Используем официальный образ Tomcat
FROM tomcat:11.0.2

# Копируем WAR-файл в контейнер
COPY target/learning-session.war /usr/local/tomcat/webapps/ROOT.war

# Открываем порт для доступа к приложению
EXPOSE 8080

# Запускаем Tomcat
CMD ["catalina.sh", "run"]