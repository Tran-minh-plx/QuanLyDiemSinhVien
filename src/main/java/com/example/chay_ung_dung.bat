@echo off
chcp 65001 > nul
cls
cd /d "%~dp0"

echo Đang biên dịch mã nguồn...
javac -encoding UTF-8 -cp "mssql-jdbc-12.4.2.jre11.jar" -d bin src/main/java/com/example/*.java

if errorlevel 1 (
    echo.
    echo [LOI] Bien dich that bai! Vui long kiem tra lai code.
    pause
    exit /b
)

echo Biên dịch thành công! Đang khởi chạy ứng dụng...
java -cp "bin;mssql-jdbc-12.4.2.jre11.jar" main.java.com.example.MainForm

pause