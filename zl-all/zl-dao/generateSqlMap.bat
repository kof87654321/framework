@echo off
call mvn -Dmaven.test.skip=true mybatis-generator:generate 
echo 文件生成完成
pause
