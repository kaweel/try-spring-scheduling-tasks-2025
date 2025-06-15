# try-spring-scheduling-tasks-2025

curl --location 'http://localhost:8080/re-schedule' \
--header 'Content-Type: application/json' \
--data '{
    "name": "JOB-B",
    "expression": "* 15-30 16 * * *",
    "enabled": true
}'