@echo off
rd /Q /S "./generate_source/mapper"
rd /Q /S "./generate_source/java"
mkdir "./generate_source/mapper"
mkdir "./generate_source/java"
call mvn -Dmaven.test.skip=true mybatis-generator:generate 
echo 文件生成完成
pause
